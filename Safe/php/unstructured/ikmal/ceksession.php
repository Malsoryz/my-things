<?php
    if(!isset($_SESSION['login'])){
        echo "<script>alert('Silahkan Login Terlebih Dahulu!!!');location.href='index.php'</script>";
    }
?>