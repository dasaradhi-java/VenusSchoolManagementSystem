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

        // Fetch admin details
        $http.get('http://localhost:8080/api/user')
            .then(function(response) {
                $scope.adminName = response.data.username;
            }, function(error) {
                console.error('Error:', error);
                $scope.adminName = 'Admin';
            });

        // Show registration form
        $scope.showRegisterForm = function() {
            $scope.showStudentForm = true;
            $scope.showFindStudentForm = false;
            $scope.showStudentInfo = false;
        };


        // Register a student
        $scope.registerStudent = function() {
            $http.post('http://localhost:8080/api/studentregister', $scope.student)
                .then(function(response) {
                    alert('Student registered successfully!');
                    $scope.student = {}; // Clear the form
                    $scope.showStudentForm = false;
                    // Fetch the updated student list
                    $scope.getAllStudents();
                }, function(error) {
                    console.error('Error:', error);
                    alert('Failed to register student');
                });
        };

        // Show find student by ID form
        $scope.showFindStudentById = function() {
            $scope.showFindStudentForm = true;
            $scope.showStudentForm = false;
            $scope.showStudentInfo = false;
        };

        // Find a student by ID
        $scope.findStudentById = function() {
            $http.get(`http://localhost:8080/api/student/${$scope.studentId}`)
                .then(function(response) {
                    $scope.students = [response.data];
                    $scope.showStudentInfo = true;
                    $scope.showFindStudentForm = false;
                }, function(error) {
                    console.error('Error:', error);
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
                    console.error('Error:', error);
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
                            console.error('Error:', error);
                            alert('Failed to delete record');
                        });
                });
            }
        };
    }]);