
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "library_db";

// Connect to database
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Fetch overdue books
$sql = "SELECT id, title, author, year, issue_date 
        FROM books 
        WHERE return_date IS NULL AND issue_date < CURDATE()";
$result = $conn->query($sql);

echo "
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
    }
    h1 {
        text-align: center;
        color: #333;
        margin-top: 20px;
        padding-bottom: 10px;
        border-bottom: 3px solid #007bff;
        display: inline-block;
    }
    .table-container {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
    table {
        width: 80%;
        border-collapse: collapse;
        box-shadow: 0 0 10px rgba(0,0,0,0.2);
    }
    th, td {
        border: 1px solid #333;
        padding: 10px;
        text-align: center;
    }
    th {
        background-color: #007bff;
        color: white;
        text-transform: uppercase;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #d6e4ff;
    }
</style>
";

echo "<div style='text-align:center;'><h1>📚 Overdue Books</h1></div>";

if ($result->num_rows > 0) {
    echo "<div class='table-container'>";
    echo "<table>";
    echo "<tr><th>ID</th><th>Title</th><th>Author</th><th>Year</th><th>Issue Date</th></tr>";

    while ($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>{$row['id']}</td>
                <td>{$row['title']}</td>
                <td>{$row['author']}</td>
                <td>{$row['year']}</td>
                <td>{$row['issue_date']}</td>
              </tr>";
    }
    echo "</table>";
    echo "</div>";
} else {
    echo "<p style='color:red; text-align:center;'>No overdue books 🎉</p>";
}

$conn->close();
?>
