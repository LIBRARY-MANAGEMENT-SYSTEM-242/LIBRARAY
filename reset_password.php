<?php
require 'db.php';
session_start();

$token = $_GET['token'] ?? '';
$message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $token = $_POST['token'];
    $new_password = $_POST['new_password'];
    $hashed_password = password_hash($new_password, PASSWORD_DEFAULT);

    // Check token validity
    $stmt = $conn->prepare("SELECT * FROM users WHERE reset_token = ? AND token_expiry > NOW()");
    $stmt->bind_param("s", $token);
    $stmt->execute();
    $user = $stmt->get_result()->fetch_assoc();

    if ($user) {
        // Update password
        $update = $conn->prepare("UPDATE users SET password = ?, reset_token = NULL, token_expiry = NULL WHERE id = ?");
        $update->bind_param("si", $hashed_password, $user['id']);
        $update->execute();

        $message = "Password changed successfully. <a href='login.php'>Login</a>";
    } else {
        $message = "Invalid or expired token.";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; }
        .reset-box {
            background: #fff; padding: 40px; border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0,0,0,0.1); text-align: center;
        }
        input { width: 90%; padding: 10px; margin: 10px 0; border-radius: 5px; border: 1px solid #ccc; }
        button { padding: 10px 20px; border: none; background: #0056b3; color: white; border-radius: 5px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="reset-box">
        <h2>Set New Password</h2>
        <?php if (!empty($message)) {
            echo "<p>$message</p>";
        } elseif (!empty($token)) { ?>
            <form method="POST">
                <input type="hidden" name="token" value="<?php echo htmlspecialchars($token); ?>">
                <input type="password" name="new_password" placeholder="Enter New Password" required><br>
                <button type="submit">Change Password</button>
            </form>
        <?php } else {
            echo "<p>Invalid request.</p>";
        } ?>
    </div>
</body>
</html>
