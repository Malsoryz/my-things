<?php
include "function.php";
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
    <form method="post">
        <h2>Login Formulir Siswa/Siswi</h2>
        <div class="username text-area">
            <label for="username">Username</label>
            <input type="text" name="user" placeholder="username" required>
        </div>
        <div class="password text-area">
            <label for="password">Password</label>
            <input type="password" name="pwd" placeholder="password" required>
        </div>
        <button type="submit" name="masuk" class="button">Masuk</button>
    </form>
</body>
</html>