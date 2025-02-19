<?php
    session_start();
    session_destroy();
    echo "<script>alert('anda keluar');location.href='../login.php'</script>";