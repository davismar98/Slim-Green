<?php
$servername = "localhost"; //replace it with your database server name
$username = "root";  //replace it with your database username
$password = "";  //replace it with your database password
$dbname = "slimgreen";
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
mysqli_set_charset($conn, 'utf8mb4'); 	
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>