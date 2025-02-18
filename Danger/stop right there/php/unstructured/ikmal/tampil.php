<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style/ikmal.css">
</head>
<body>
<?php
    if(isset($_POST['kirim'])){
        echo "Nama Anda Adalah ".$_POST['nm']."<br>";
        echo "Jenis Kelamin Anda Adalah ".$_POST['jk']."<br>";
        echo "Jurusan Anda Adalah ".$_POST['Jurusan']."<br>";
    }
?>
</body>
</html>