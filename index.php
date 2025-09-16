<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Temporary login (later you can connect with DB)
    if ($username == "admin" && $password == "1234") {
    $_SESSION['user'] = $username;
    header("Location: home.php"); 
    exit();
} elseif ($username == "user" && $password == "1234") {
    $_SESSION['user'] = $username;
    header("Location: home.php"); 
    exit();
} else {
    $error = "Invalid Username or Password!";
    // You can display this error in your HTML below
    echo $error;}
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image:url(https://png.pngtree.com/background/20230527/original/pngtree-an-old-bookcase-in-a-library-picture-image_2760144.jpg );
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-box {
            background: rgba(164, 72, 72, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 8px rgba(0,0,0,0.2);
            width: 400px;
            text-align: center;
        }
        input[type="text"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #f7f1f1ff;
            border-radius: 5px;
        }
        button {
            background: #ff3700ff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background: #0056b3;
        }
        .error {
            color: red;
        }
         forgot-password {
            display: block;
            margin-top: 10px;
            color: #ffffff;
            text-decoration: underline;
            font-size: 14px;
        }
        forgot-password:hover {
            color: #e0e0e0;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>LOGIN</h2>
        <form method="POST">
            <input type="text" name="username" placeholder="Enter Username" required><br>
            <input type="password" name="password" placeholder="Enter Password" required><br>
            <button type="submit">Login</button>
        </form>
        <?php if (!empty($error)) { echo "<p class='error'>$error</p>"; } ?>
    </div>
</body>
</html>
    