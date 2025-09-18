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
    <title>LIBRARY MANAGEMENT SYSTEM</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image:url(https://clipart-library.com/images/6cy5qedxi.jpg);
            background-size: cover;
    
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #f3efefff;
            padding:50px;
        }
        .menu {
            margin-top: 50px;
        }
        logout-box {
            background: rgba(164, 72, 72, 0.9);
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0px 4px 8px rgba(0,0,0,0.2);
            width: 400px;
            text-align: center;
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
           
            display: inline-block;
            padding: 15px 25px;
            margin: 10px;
            background: #007bff;
            color: white;
            text-decoration: none;
            font-size: 18px;
            border-radius: 5px;
}
        
    </style>
</head>
<body>

    <h1>📚 LIBRARY MANAGEMENT SYSTEM</h1>
    

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
