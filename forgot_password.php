<?php
session_start();

// Simulated user storage (you'll eventually replace this with a database)
$users = [
    "admin" => "1234",
    "user" => "1234"
];

// Password change logic
if (isset($_POST['change_password'])) {
    $username = $_POST['cp_username'];
    $old_password = $_POST['old_password'];
    $new_password = $_POST['new_password'];

    if (isset($users[$username]) && $users[$username] === $old_password) {
        // Password updated (simulated)
        $users[$username] = $new_password;
        $success = "Password changed successfully (temporary simulation).";
    } else {
        $cp_error = "Invalid username or current password.";
    }
}

// Login logic (kept here in case you're using the same file for login)
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['login'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    if (isset($users[$username]) && $users[$username] === $password) {
        $_SESSION['user'] = $username;
        if ($username == "admin") {
            header("Location: home.php");
        } else {
            header("Location: userhome.php");
        }
        exit();
    } else {
        $error = "Invalid Username or Password!";
    }
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url(https://blog.1password.com/posts/2022/should-you-change-passwords-every-90-days/header.png);
            background-size: cover;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-box {
            background: rgba(68, 38, 219, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }

        input[type="text"],
        input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #f7f1f1;
            border-radius: 5px;
        }

        button {
            background-color: #2c0aed;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        button:hover {
            background-color: #1e0899;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #444;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: background 0.3s;
        }

        .back-button:hover {
            background-color: #333;
        }

        .error {
            color: red;
            margin-top: 10px;
        }

        .success {
            color: green;
            margin-top: 10px;
        }

        .footer {
            text-align: center;
            padding: 10px;
            background-color: #2d3e50;
            color: white;
            font-size: 1.1em;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>CHANGE PASSWORD</h2>
        <form method="POST">
            <input type="text" name="cp_username" placeholder="Enter Username" required><br>
            <input type="password" name="old_password" placeholder="Current Password" required><br>
            <input type="password" name="new_password" placeholder="New Password" required><br>
            <button type="submit" name="change_password">Change Password</button>
        </form>

        <?php
        if (!empty($cp_error)) {
            echo "<p class='error'>$cp_error</p>";
        }
        if (!empty($success)) {
            echo "<p class='success'>$success</p>";
        }
        ?>

        <!-- Corrected Back Button -->
        <a href="index.php" class="back-button">⬅ Back to Login</a>
    </div>
</body>
</html>
