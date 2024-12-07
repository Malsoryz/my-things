<?php
    include "koneksi.php";

    $nis = $_GET['nis'];
    $sql = "delete from siswa where nis='$nis'";

    $result = $conn->query($sql);
    if($result === true){
        echo "<script>alert('Berhasil Menghapus Data!');</script>";
        header('location:index.php');
    }else{
        echo "Error : " . $conn->error;
    }
?>