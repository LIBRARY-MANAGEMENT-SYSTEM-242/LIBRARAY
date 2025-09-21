<?php
// Database connection settings
$host = "localhost";
$username = "root"; // Change if not default
$password = "1234";     // Change if there's a password
$dbname = "library_db";

// Create connection
$conn = new mysqli($host, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Initialize variables
$success = "";
$error = "";

// Handle form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $book_id = trim($_POST["book_id"]);
    $book_name = trim($_POST["book_name"]);
    $author = trim($_POST["author"]);
    $year = intval($_POST["year"]);

    // Validate inputs
    if (empty($book_id) || empty($book_name) || empty($author) || empty($year)) {
        $error = "All fields are required.";
    } else {
        // Prepare and bind
        $stmt = $conn->prepare("INSERT INTO books (book_id, book_name, author, year) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("sssi", $book_id, $book_name, $author, $year);

        if ($stmt->execute()) {
            $success = "Book added successfully!";
        } else {
            $error = "Error: " . $stmt->error;
        }

        $stmt->close();
    }
}

$conn->close();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <style>
        body {
             
            font-family: Arial, sans-serif;
            background-image:url(https://www.dorsetonline.co.uk/theme-content/uploads/2020/07/library-books.jpg);
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            
        }
        .container {
            width: 500px;
            margin: auto;
            background: #9e6f4bff ;
            padding: 25px;
            width: 400px;
            border-radius: 10px;
            
            
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 8px 0 16px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }
        .message {
            padding: 10px;
            margin-bottom: 15px;
        }
        .success { background: #d4edda; color: #155724; }
        .error { background: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
<div class="container">
    <h2>ADD A NEW BOOK</h2>

    <?php if ($success): ?>
        <div class="message success"><?= $success ?></div>
    <?php endif; ?>

    <?php if ($error): ?>
        <div class="message error"><?= $error ?></div>
    <?php endif; ?>

    <form method="post" action="">
        <label for="book_id">BOOK ID:</label>
        <input type="text" name="book_id" placeholder="ENTER THE ID" required>

        <label for="book_name">BOOK NAME:</label>
        <input type="text" name="book_name"placeholder="ENTER THE NAME OF THE BOOK"  required>

        <label for="author">AUTHOR:</label>
        <input type="text" name="author" placeholder="ENTER THE NAME OF AUTHOR" required>

        <label for="year">YEAR:</label><input type="number" name="year" min="1000" max="9999"placeholder="ENTER THE YEAR"  required>

        <input type="submit" value="Add Book">
    </form>
</div>
</body>
</html>
