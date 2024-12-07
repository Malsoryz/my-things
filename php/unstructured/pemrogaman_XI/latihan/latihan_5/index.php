<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Latihan5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script>
        function editData(nis, nama, kelas, jenis_kelamin){
            document.getElementById('form_update').action = "update.php?nis=" + nis;
            document.getElementById('nis').value = nis;
            document.getElementById('nama').value = nama;
            selectKelas(kelas);
            radioJenisKelamin(jenis_kelamin);
        }
        function selectKelas(nilai){
            let element = document.getElementById('kelas');
            element.value = nilai;
        }
        function radioJenisKelamin(nilai){
            if(nilai == "L"){document.getElementById("rb1").checked = true;}
            else if(nilai == "P"){document.getElementById("rb2").checked = true;}
        }
    </script>
  </head>
  <body>
    <div class="container mx-auto my-3">
        <h1>Latihan 5</h1><hr>
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Tambahkan Data Baru</button>
            <a href="print_data_siswa.php">
                <button type="button" class="btn btn-secondary">Cetak Data</button>
            </a>
        <table class="table table-bordered table-striped" id="mytables">
            <thead class="text-center table-success">
                <tr>
                    <th>Nis</th>
                    <th>Nama</th>
                    <th>Kelas</th>
                    <th>Jenis Kelamin</th>  
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    include "koneksi.php";
                    $query = $conn->query('select * from siswa');
                    while ($result = $query->fetch_array()):
                ?>
                <tr>
                    <td><?= $result['nis']?></td>
                    <td><?= $result['nama']?></td>
                    <td><?= $result['kelas']?></td>
                    <td><?= $result['jenis_kelamin']?></td>
                    <td class="text-center">
                        <?php
                            $nis = $result['nis'];
                            $nama = $result['nama'];
                            $kelas = $result['kelas'];
                            $jenis_kelamin = $result['jenis_kelamin'];
                        ?>
                        <button class="btn btn-primary" onclick="editData('<?= $nis ?>','<?= $nama ?>','<?= $kelas ?>','<?= $jenis_kelamin ?>')" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button>
                        <a href="delete.php?nis=<?=$result['nis']?>">
                            <button class="btn btn-danger" onclick="return confirm('yakin ingin menghapus data?')">Hapus</button>
                        </a>
                    </td>
                </tr>
                <?php endwhile; ?>
            </tbody>
        </table>
        <div class="modal" id="exampleModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Input Biodata</h4>
                    </div>
                    <div class="modal-body">
                        <form action="insert.php" method="post">
                            <label for="">NIS</label><br>
                            <input class="form-control form-control-sm" type="text" name="nis" id=""><br>
                            <label for="">Nama</label><br>
                            <input class="form-control form-control-sm" type="text" name="nama" id=""><br>

                            <label for="">Kelas</label>
                            <select class="form-control" name="kelas" id="">
                                <option value="X">X</option>
                                <option value="XI">XI</option>
                                <option value="XII">XII</option>
                            </select><br>

                            <label for="">Jenis Kelamin</label>
                            <div class="form-check">
                                <input type="radio" name="jenis_kelamin" value="L" class="form-check-input">
                                <label for="input">Laki-laki</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="jenis_kelamin" value="P" class="form-check-input">
                                <label for="input">Perempuan</label>
                            </div><br>
                            <input class="btn btn-warning" type="submit" value="Submit">
                        </form>
                    </div>
                    <div class="modal-footer"></div>
                </div>
            </div>
        </div>
        <div class="modal" id="editModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Edit Biodata</h4>
                    </div>
                    <div class="modal-body">
                        <form action="" method="post" id="form_update">
                            <label for="nis">NIS</label><br>
                            <input class="form-control form-control-sm" type="text" name="nis" id="nis" readonly><br>
                            <label for="nama">Nama</label><br>
                            <input class="form-control form-control-sm" type="text" name="nama" id="nama"><br>

                            <label for="kelas">Kelas</label>
                            <select class="form-control" name="kelas" id="kelas">
                                <option value="X">X</option>
                                <option value="XI">XI</option>
                                <option value="XII">XII</option>
                            </select><br>

                            <label for="jenis_kelamin">Jenis Kelamin</label>
                            <div class="form-check">
                                <input type="radio" name="jenis_kelamin" value="L" id="rb1" class="form-check-input">
                                <label for="input">Laki-laki</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="jenis_kelamin" value="P" id="rb2" class="form-check-input">
                                <label for="input">Perempuan</label>
                            </div>
                            <input class="btn btn-warning" type="submit" value="submit">
                        </form>
                    </div>
                    <div class="modal-footer"></div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/2.1.5/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/2.1.5/js/dataTables.bootstrap5.js"></script>
    <script>new DataTable('#mytables');</script>
  </body>
</html>