<?php
    include "function.php";
    include "ceksession.php";
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biodata</title>
    <link rel="stylesheet" href="styleikmal.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
    <form method="post" action="tampil.php" id="form">
        <h1>Formulir Siswa/Siswi</h1>
        <div class="nama">Nama : <input type="text" name="nm" placeholder="Masukkan Nama" required></div>
        <div class="kelamin">Jenis kelamin :
            <input type="radio" name="jk" value="laki-laki" required>Laki-Laki
            <input type="radio" name="jk" value="Perempuan" required>Perempuan
        </div>
        <div class="jurusan">
            <select name="Jurusan">
                <option selected="selected" value="">Jurusan</option>
                <option>Rekayasa Perangkat Lunak</option>
                <option>Usaha Layanan Wisata</option>
                <option>Seni Musik</option>
            </select>
        </div>
        <button type="submit" name="kirim">KIRIM</button>
        <a href="logout.php">keluar</a>
    </form>
</body>
</html>