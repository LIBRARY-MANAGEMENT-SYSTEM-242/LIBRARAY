<?php
session_start();

// Redirect to login if not logged in
if (!isset($_SESSION['user'])) {
    header("Location: index.php");
    exit();
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #333;
        }
        .menu {
            margin-top: 30px;
        }
        .menu a {
            display: inline-block;
            padding: 15px 25px;
            margin: 10px;
            background: #007bff;
            color: white;
            text-decoration: none;
            font-size: 18px;
            border-radius: 5px;
        }
        .menu a:hover {
            background: #0056b3;
        }
        .logout {
            margin-top: 30px;
        }
        .logout a {
            color: red;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <h1>📚 Library Management System</h1>
    <p>Welcome, <?php echo $_SESSION['user']; ?>! Choose an option below:</p>

    <div class="menu">
        <a href="library.php">All Books</a>
        <a href="issued_books.php">Issued Books</a>
        <a href="overdue_books.php">Overdue Books</a>
    </div>

    <div class="logout">
        <a href="logout.php">Logout</a>
    </div>

</body>
</html>

   
   