<?php
    include "php/navbar.php";
?>

<script>
  function hitung() {
    let golongan = document.getElementById("golongan").value;
    let gapok = parseInt(document.getElementById("gaji_pokok").value);
    let tunjab = parseInt(document.getElementById("tunjangan_jabatan").value);
    let tunjangan1 = parseInt(document.getElementById("tunjangan_nikah").value);

    let tunjangan2 = 0;
    let anak = document.querySelector("input[name='tunjangan_anak']:checked");
    if (anak) {
      tunjangan2 = parseInt(anak.value);
    }

    let bpjs = 34000;

    let pendapatan = Number(gapok + tunjab + tunjangan1 + tunjangan2);
    let bersih = 0;
    let potongan = 0;

    const arr = golongan.split("/");
    let gol = arr[0];
    if(gol == "III") {
      potongan = pendapatan * 0.05;
      bersih = pendapatan - (potongan + bpjs);
    }
    else if(gol == "IV") {
      potongan = pendapatan * 0.15;
      bersih = pendapatan - (potongan + bpjs);
    }

    document.getElementById("gaji").value = "Rp." + (new Intl.NumberFormat().format(gapok));
    document.getElementById("jabatan").value = "Rp." + (new Intl.NumberFormat().format(tunjab));
    document.getElementById("nikah").value = "Rp." + (new Intl.NumberFormat().format(tunjangan1));
    document.getElementById("anak").value = "Rp." + (new Intl.NumberFormat().format(tunjangan2));
    document.getElementById("bpjs").value = "Rp." + (new Intl.NumberFormat().format(bpjs));
    document.getElementById("pajak").value = "Rp." + (new Intl.NumberFormat().format(potongan));
    document.getElementById("gaji_bersih").value = "Rp." + (new Intl.NumberFormat().format(bersih));
  }
</script>

<div class="container px-4 mt-4">
  <h3>Penggajian Karyawan</h3><br>
  <div class="card">
    <div class="card-reader bg-secondary text-light">
      Buat Slip Gaji
    </div>
    <div class="card-body">
      <form action="php/print_pdf.php" method="POST">
        <label for="">Bulan</label>
        <select name="bulan" class="form-control mb-2">
          <option value="januari">januari</option>
          <option value="februari">februari</option>
          <option value="maret">maret</option>
        </select>
        
        <label for="">Nama Karyawan</label>
        <select class="form-control mb-2" name="karyawan_id" id="">
          <?php
            $sql = "select * from karyawan join golongan on karyawan.kode_golongan = golongan.kode_golongan;";
            $result = $conn->query($sql);
            while ($data = $result->fetch_array(MYSQLI_ASSOC)):
          ?>
          <option value="<?=$data['nip']?>"><?=$data['nama']?> (Golongan : <?=$data['golongan']?>)</option>
          <?php
            endwhile;
          ?>
        </select>

        <input type="hidden" name="golongan" id="golongan" value="III/A">

        <label for="">Gaji pokok</label>
        <input type="number" name="gaji_pokok" id="gaji_pokok" class="form_control mb-2">
        <label for="">Tunjangan Jabatan</label>
        <select class="form_control mb-2" name="tunjangan_jabatan" id="tunjangan_jabatan">
          <option value="0">Tidak ada</option>
          <option value="500000">Kepala Bagian</option>
          <option value="250000">Staff</option>
        </select>
        <hr>
        <p class="fw-bold">Tunjangan Lainnya</p>
        <label for="">Menikah</label>
        <select class="form-control mb-2" name="tunjangan_nikah" id="tunjangan_nikah">
          <option value="0">Belum</option>
          <option value="250000">Sudah</option>
        </select>

        <label for="">Tunjangan Anak</label>
        <input class="form-check-input" type="radio" name="tunjangan_anak" value="0">
        <label for="">Tidak ada</label>

        <input class="form-check-input" type="radio" name="tunjangan_anak" value="200000">
        <label for="">1 Anak</label>

        <input class="form-check-input" type="radio" name="tunjangan_anak" value="400000">
        <label for="">2 Anak</label>
      
        <hr>
        <p class="fw-bold">Potongan</p>
        <blockquote style="font-family: monospace;" class="fw-bold fst-italic text-danger">
          * Potongan BPJS Kesehatan Sebesar Rp.34.000.00,- <br>
          * Potongan Pajak 15% bagi Golongan IV, dan 5% bagi Golongan III
        </blockquote>

        <hr>
        <p class="fw-bold">Rincian Slip Gaji</p>
        <table class="table">
          <tr>
            <td>Gaji</td>
            <td><input type="text" name="gaji" id="gaji" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr>
            <td>Tunjangan Jabatan</td>
            <td><input type="text" name="jabatan" id="jabatan" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr>
            <td>Tunjangan Suami</td>
            <td><input type="text" name="nikah" id="nikah" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr>
            <td>Tunjangan Anak</td>
            <td><input type="text" name="anak" id="anak" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr>
            <td>Potongan BPJS</td>
            <td><input type="text" name="bpjs" id="bpjs" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr>
            <td>Potongan Pajak</td>
            <td><input type="text" name="pajak" id="pajak" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
          <tr class="fw-bold">
            <td>Gaji Bersih</td>
            <td><input type="text" name="gaji_bersih" id="gaji_bersih" style="all: unset;" value="Rp.0" readonly></td>
          </tr>
        </table>

        <button type="button" class="form-control btn btn-info mt-1" onclick="hitung()">
          Hitung Pendapatan
        </button>
        <input type="submit" class="form-control btn btn-success mb-3 mt-1" value="Buat Slip">
      </form>
    </div>
  </div>
</div>

<?php
    include "php/footer.php";
?>
