<?php
$host = 'localhost';
$db   = 'librarymanagementsystem';
$user = 'root'; // your MySQL username
$pass = '1234';     // your MySQL password

try {
    $pdo = new PDO("mysql:host=$host;dbname=$db", $user, $pass);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}
?>

