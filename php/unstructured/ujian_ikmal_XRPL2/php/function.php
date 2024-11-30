<?php
    session_start();
    if(isset($_POST['masuk'])){
        $username=$_POST['user'];
        $password=$_POST['pwd'];
        if($username=="admin" && $password=="123"){
            $_SESSION['login']=true;
            echo "<script>alert('Selamat Datang !!!');
            location.href='index.php';</script>";
        }
        else{
            echo "<script>alert('Nah...');</script>";
        }
    }
    ?>