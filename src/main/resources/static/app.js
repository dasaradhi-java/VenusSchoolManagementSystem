// app.js
angular.module('adminApp', [])
	.controller('AdminController', ['$scope', '$http', function($scope, $http) {
		$scope.adminName = 'Admin';
		$scope.showStudentForm = false;
		$scope.showFindStudentForm = false;
		$scope.showStudentInfo = false;
		$scope.students = [];
		$scope.student = {};
		$scope.studentId = '';
		$scope.isEditing = false;

		// List of classes
		$scope.classes = ['Nursery', 'LKG', 'UKG', '1st', '2nd', '3rd', '4th', '5th', '6th', '7th', '8th', '9th', '10th'];

		// Fetch admin details
		$http.get('http://localhost:8080/api/user')
			.then(function(response) {
				$scope.adminName = response.data.username;
			}, function(error) {
				console.error('Error fetching admin details:', error);
				$scope.adminName = 'Admin';
			});

		// Show registration form
		$scope.showRegisterForm = function() {
			$scope.isEditing = false;
			$scope.student = {};
			$scope.showStudentForm = true;
			$scope.showFindStudentForm = false;
			$scope.showStudentInfo = false;
		};

		// Register or update a student
		$scope.registerStudent = function() {
			if ($scope.isEditing) {
				// Update student
				$http.put(`http://localhost:8080/updateStudent/${$scope.student.rollNumber}`, $scope.student)
					.then(function(response) {
						alert('Student updated successfully!');
						$scope.student = {}; // Clear the form
						$scope.showStudentForm = false;
						// Refresh the student list
						$scope.getAllStudents();
					}, function(error) {
						console.error('Error updating student:', error);
						alert('Failed to update student');
					});
			} else {
				// Register student
				$http.post('http://localhost:8080/api/studentregister', $scope.student)
					.then(function(response) {
						alert('Student registered successfully!');
						//                        $scope.students.push(response.data); // Add the new student to the list
						$scope.student = {}; // Clear the form
						$scope.showStudentForm = false;
						$scope.showStudentInfo = true; // Show student info section
						$http.get(`http://localhost:8080/api/studentByRollNumber/${response.data.rollNumber}`)
							.then(function(response) {
								$scope.students = [response.data]; // Update students array with the new student
							}, function(error) {
								console.error('Error fetching student details:', error);
								alert('Failed to fetch student details');
							});
					}, function(error) {
						console.error('Error registering student:', error);
						alert('Failed to register student');
					});
			}
		};

		// Show find student by ID form
		$scope.showFindStudentById = function() {
			$scope.showFindStudentForm = true;
			$scope.showStudentForm = false;
			$scope.showStudentInfo = false;
		};

		// Find a student by ID
		$scope.findStudentById = function() {
			$http.get(`http://localhost:8080/api/studentByRollNumber/${$scope.rollNumber}`)
				.then(function(response) {
					$scope.students = [response.data];
					$scope.showStudentInfo = true;
					$scope.showFindStudentForm = false;
				}, function(error) {
					console.error('Error finding student:', error);
					alert('Failed to find student');
				});
		};

		// Get all students
		$scope.getAllStudents = function() {
			$http.get('http://localhost:8080/GetAllStudents')
				.then(function(response) {
					$scope.students = response.data;
					$scope.showStudentInfo = true;
					$scope.showStudentForm = false;
					$scope.showFindStudentForm = false;
				}, function(error) {
					console.error('Error fetching student details:', error);
					alert('Failed to fetch student details');
				});
		};

		// Delete selected students
		$scope.deleteSelected = function() {
			const selectedRollNumbers = $scope.students.filter(student => student.selected).map(student => student.rollNumber);

			if (selectedRollNumbers.length === 0) {
				alert('Please select at least one record to delete.');
				return;
			}

			if (confirm(`Are you sure you want to delete ${selectedRollNumbers.length} record(s)?`)) {
				selectedRollNumbers.forEach(rollNumber => {
					$http.delete(`http://localhost:8080/api/deleteStudent/${rollNumber}`)
						.then(function() {
							$scope.students = $scope.students.filter(student => student.rollNumber !== rollNumber);
						}, function(error) {
							console.error('Error deleting record:', error);
							alert('Failed to delete record');
						});
				});
			}
		};

		// Edit student
		$scope.editStudent = function(student) {
			$scope.isEditing = true;
			$scope.student = angular.copy(student);
			$scope.showStudentForm = true;
			$scope.showFindStudentForm = false;
			$scope.showStudentInfo = false;
		};
	}]);
