<?php
    include "koneksi.php";

    $nis = $_POST['nis'];
    $nama = $_POST['nama'];
    $kelas = $_POST['kelas'];
    $jenis_kelamin = $_POST['jenis_kelamin'];

    $result = $conn->query("insert into siswa values('".$nis."','".$nama."','".$kelas."','".$jenis_kelamin."')");
    if($result){
        echo "<script>alert('Berhasil Menambahkan!');</script>";
        header('location:index.php');
    }
?>