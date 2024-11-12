-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: test01_ikmal
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bayarcicilan`
--

DROP TABLE IF EXISTS `bayarcicilan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bayarcicilan` (
  `nomorbyr` varchar(10) NOT NULL,
  `tanggalbyr` date DEFAULT NULL,
  `kodekredit` varchar(10) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `sisa` double DEFAULT NULL,
  `cicilan` double DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nomorbyr`),
  KEY `kodekredit` (`kodekredit`),
  CONSTRAINT `bayarcicilan_ibfk_1` FOREIGN KEY (`kodekredit`) REFERENCES `belikredit` (`kodekredit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bayarcicilan`
--

LOCK TABLES `bayarcicilan` WRITE;
/*!40000 ALTER TABLE `bayarcicilan` DISABLE KEYS */;
/*!40000 ALTER TABLE `bayarcicilan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belicash`
--

DROP TABLE IF EXISTS `belicash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belicash` (
  `kodecash` varchar(10) NOT NULL,
  `tanggalcash` date DEFAULT NULL,
  `kodecust` varchar(10) DEFAULT NULL,
  `kodemobil` varchar(10) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `bayar` double DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kodecash`),
  KEY `kodecust` (`kodecust`),
  KEY `kodemobil` (`kodemobil`),
  CONSTRAINT `belicash_ibfk_1` FOREIGN KEY (`kodecust`) REFERENCES `pelanggan` (`kodecust`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `belicash_ibfk_2` FOREIGN KEY (`kodemobil`) REFERENCES `mobil` (`kodemobil`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belicash`
--

LOCK TABLES `belicash` WRITE;
/*!40000 ALTER TABLE `belicash` DISABLE KEYS */;
/*!40000 ALTER TABLE `belicash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belikredit`
--

DROP TABLE IF EXISTS `belikredit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belikredit` (
  `kodekredit` varchar(10) NOT NULL,
  `tanggalkredit` date DEFAULT NULL,
  `kodecust` varchar(10) DEFAULT NULL,
  `kodemobil` varchar(10) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `uangmuka` double DEFAULT NULL,
  `bunga` float DEFAULT NULL,
  `lamacicilan` int(11) DEFAULT NULL,
  `angsuranke` int(11) DEFAULT NULL,
  `telahbayar` double DEFAULT NULL,
  `sisa` double DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kodekredit`),
  KEY `kodemobil` (`kodemobil`),
  KEY `kodecust` (`kodecust`),
  CONSTRAINT `belikredit_ibfk_1` FOREIGN KEY (`kodemobil`) REFERENCES `mobil` (`kodemobil`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `belikredit_ibfk_2` FOREIGN KEY (`kodecust`) REFERENCES `pelanggan` (`kodecust`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belikredit`
--

LOCK TABLES `belikredit` WRITE;
/*!40000 ALTER TABLE `belikredit` DISABLE KEYS */;
/*!40000 ALTER TABLE `belikredit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mobil`
--

DROP TABLE IF EXISTS `mobil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mobil` (
  `kodemobil` varchar(10) NOT NULL,
  `merk` varchar(20) DEFAULT NULL,
  `warna` varchar(20) DEFAULT NULL,
  `gambar` varchar(200) DEFAULT NULL,
  `Harga` double DEFAULT NULL,
  PRIMARY KEY (`kodemobil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mobil`
--

LOCK TABLES `mobil` WRITE;
/*!40000 ALTER TABLE `mobil` DISABLE KEYS */;
INSERT INTO `mobil` VALUES ('m001','toyota','silver','glossy',129000000),('m002','hyundai','hitam','matte',250000000),('m003','honda','merah','matte',259000000),('m004','suzuki','hitam','glossy',199000000),('m005','wuling','putih','glossy',150000000);
/*!40000 ALTER TABLE `mobil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelanggan`
--

DROP TABLE IF EXISTS `pelanggan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelanggan` (
  `kodecust` varchar(10) NOT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `telepon` varchar(15) DEFAULT NULL,
  `hp` varchar(15) DEFAULT NULL,
  `noktp` varchar(20) DEFAULT NULL,
  `kk` varchar(20) DEFAULT NULL,
  `slipgaji` double DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kodecust`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelanggan`
--

LOCK TABLES `pelanggan` WRITE;
/*!40000 ALTER TABLE `pelanggan` DISABLE KEYS */;
INSERT INTO `pelanggan` VALUES ('01','ikmal','sungai andai','85387261315','infinix','1','53564665',2000000,'beli mobil secara cash'),('02','yadi','kuin','85738471234','oppo','3','53587655',2500000,'ingin beli mobil baru'),('03','firdaus','kuin','85738471234','oppo','3','53587655',2500000,'ingin beli mobil baru'),('04','rafei','alalak','88263545362','xiaomi','5','53876543',3000000,'ingin beli mobil secara kredit'),('05','helmawan','hksn','85754324534','xiaomi','4','53587654',5000000,'ingin beli mobil terbaru'),('06','ihsan','kelayan','85435646435','infinix','6','53876567',3500000,'beli mobil secara cash'),('07','echa','sungai gampa','85765435437','huawei','7','53865467',3500000,'ingin beli secara kredit'),('08','nindya','hksn','83235653513','realme','8','53876567',2500000,'ingin beli mobil secara cash'),('09','ratna','sungai sungai','85908765437','huawei','7','53876567',2500000,'ingin beli mobil bekas'),('10','aisyah','sungai miai','86543587657','vivo','7','53475667',4500000,'ingin beli mobil baru');
/*!40000 ALTER TABLE `pelanggan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-01 13:41:07
