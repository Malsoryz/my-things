<?php
    include 'koneksi.php';
    require 'dompdf/autoload.inc.php';
    use Dompdf\Dompdf;
    $dompdf = new Dompdf();

    $query = $conn->query("select * from siswa order by nama asc");
    $html = '<center><h3>Daftar Nama Siswa</h3></center><hr/><br/>';
    $html .= '<table border="1" width="100%">
            <tr>
                <th>No</th>
                <th>Nama</th>
                <th>Kelas</th>
                <th>Jenis Kelamin</th>
            </tr>';
            $no = 1;
            while($row = mysqli_fetch_array($query)){
            $html .= "<tr>
                <td>".$no."</td>
                <td>".$row['nama']."</td>
                <td>".$row['kelas']."</td>
                <td>".$row['jenis_kelamin']."</td>
            </tr>";
            $no++;
            }

    $html .= "</table>";
    $dompdf->loadHtml($html);
    $dompdf->setPaper('A4');
    $dompdf->render();
    $dompdf->stream('laporan_siswa.pdf');
?>