
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "library_db"; // your database name

// Connect to database
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Fetch issued books (where return_date is not NULL or issued)
$sql = "SELECT * FROM books WHERE issue_date IS NOT NULL";
$result = $conn->query($sql);

// Page heading
echo "<h1 style='text-align:center; font-family:Arial; margin-top:20px;'>
        <img src='https://img.icons8.com/ios-filled/30/000000/books.png' 
             style='vertical-align:middle; margin-right:10px;'/> 
        Issued Books
      </h1>";

if ($result->num_rows > 0) {
    // Center table wrapper
    echo "<div style='display:flex; justify-content:center; margin-top:20px;'>";

    // Table
    echo "<table border='1' cellpadding='10' cellspacing='0' 
                 style='border-collapse:collapse; text-align:center; width:70%; 
                 box-shadow:0 4px 8px rgba(0,0,0,0.1); font-family:Arial;'>";

    // Table header row
    echo "<tr style='background:#007bff; color:white;'>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Year</th>
            <th>Issue Date</th>
            <th>Return Date</th>
          </tr>";

    // Data rows
    while($row = $result->fetch_assoc()) {
        echo "<tr style='background:#f9f9f9;'>
                <td>{$row['id']}</td>
                <td>{$row['title']}</td>
                <td>{$row['author']}</td>
                <td>{$row['year']}</td>
                <td>{$row['issue_date']}</td>
                <td>{$row['return_date']}</td>
              </tr>";
    }

    echo "</table>";
    echo "</div>";
} else {
    echo "<p style='text-align:center; font-family:Arial;'>No issued books found.</p>";
}

$conn->close();
?>
