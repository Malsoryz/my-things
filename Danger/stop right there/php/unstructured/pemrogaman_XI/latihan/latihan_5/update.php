<?php
    include "koneksi.php";

    $nis = $_GET['nis'];
    $nama = $_POST['nama'];
    $kelas = $_POST['kelas'];
    $jk = $_POST['jenis_kelamin'];
    $sql = "update siswa set nama='$nama', kelas='$kelas', jenis_kelamin='$jk' where nis='$nis'";

    $result = $conn->query($sql);
    if($result === true){
        echo "<script>alert('Berhasil Mengedit Data!');</script>";
        header('location:index.php');
    }else{
        echo "Error : " . $conn->error;
    }

?>