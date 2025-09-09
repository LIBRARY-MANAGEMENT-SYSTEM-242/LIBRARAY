<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "library_db";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f7fa;
            padding: 30px;
            text-align: center;
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
        }
        table {
            margin: auto;
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
            background: #ffffff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
            border: 2px solid #000; /* Outer border */
        }
        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #000;
            border-right: 1px solid #000;
        }
        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            border-bottom: 2px solid #000;
            border-right: 1px solid #000;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #eaf4ff;
        }
        /* Remove right border from last column */
        td:last-child, th:last-child {
            border-right: none;
        }
        /* Remove bottom border from last row */
        tr:last-child td {
            border-bottom: none;
        }
    </style>
</head>
<body>
    <h1>Library Books</h1>
    <?php
    $sql = "SELECT * FROM books";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "<table>";
        echo "<tr><th>ID</th><th>Title</th><th>Author</th><th>Year</th><th>Available</th><th>Issue Date</th><th>Return Date</th></tr>";
        while($row = $result->fetch_assoc()) {
            echo "<tr>
                <td>{$row['id']}</td>
                <td>{$row['title']}</td>
                <td>{$row['author']}</td>
                <td>{$row['year']}</td>
                <td>" . ($row['available'] ? 'Yes' : 'No') . "</td>
                <td>{$row['issue_date']}</td>
                <td>{$row['return_date']}</td>
            </tr>";
        }
        echo "</table>";
    } else {
        echo "<p>No books found.</p>";
    }

    $conn->close();
    ?>
</body>
</html>
