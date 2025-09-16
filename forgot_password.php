<?php
// Start session if needed
session_start();

// Define variable for messages
$message = '';

// If the form was submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];

    // ✅ In real-world apps: Check if email exists in DB
    // For now, we'll assume it does and simulate email sending

    // Simulated reset link (for testing/demo purposes)
    $message = "If an account with email <strong>$email</strong> exists, a password reset link has been sent.";
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f3f3;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .forgot-box {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.2);
            width: 400px;
            text-align: center;
        }
        input[type="email"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background: #0056b3;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background: #003d80;
        }
        .message {
            color: green;
            margin-top: 20px;
            font-size: 14px;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            font-size: 14px;
            text-decoration: underline;
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="forgot-box">
        <h2>Forgot Password</h2>
        <form method="POST" action="">
            <input type="email" name="email" placeholder="Enter your registered email" required><br>
            <button type="submit">Send Reset Link</button>
        </form>

        <?php if (!empty($message)) : ?>
            <p class="message"><?php echo $message; ?></p>
        <?php endif; ?>

        <a href="login.php" class="back-link">Back to Login</a>
    </div>
</body>
</html>
