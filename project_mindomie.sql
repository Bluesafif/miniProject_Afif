-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2021 at 04:42 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_mindomie`
--

-- --------------------------------------------------------

--
-- Table structure for table `bahan`
--

CREATE TABLE `bahan` (
  `idBahan` varchar(100) NOT NULL,
  `namaBahan` varchar(50) DEFAULT NULL,
  `hargaBahan` int(40) DEFAULT NULL,
  `qty` float DEFAULT NULL,
  `statusBahan` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bahan`
--

INSERT INTO `bahan` (`idBahan`, `namaBahan`, `hargaBahan`, `qty`, `statusBahan`) VALUES
('1', 'A', 2000, 8000, 0),
('119a0f1b-1373-49d4-95b3-143c02b90155', 'Telur Ayam', 9000, 1000, 1),
('7f4a98f5-f221-415c-9319-e469580b22f3', 'Minyak', 9000, 1000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ekspedisi`
--

CREATE TABLE `ekspedisi` (
  `idEkspedisi` varchar(100) NOT NULL,
  `namaEkspedisi` varchar(50) DEFAULT NULL,
  `hargaEkspedisi` int(40) DEFAULT NULL,
  `statusEkspedisi` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ekspedisi`
--

INSERT INTO `ekspedisi` (`idEkspedisi`, `namaEkspedisi`, `hargaEkspedisi`, `statusEkspedisi`) VALUES
('1', 'JNT', 11000, 1),
('2', 'GrabExpress', 11000, 1),
('6ca01b1a-2b50-4b27-b0d1-994e0047216c', 'JNE', 9000, 1),
('7742a0e2-9250-49af-88fa-a1a64fe3e492', 'GoSend', 9000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `packaging`
--

CREATE TABLE `packaging` (
  `idPackaging` varchar(100) NOT NULL,
  `namaPackaging` varchar(50) DEFAULT NULL,
  `hargaPackaging` int(40) DEFAULT NULL,
  `statusPackaging` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `packaging`
--

INSERT INTO `packaging` (`idPackaging`, `namaPackaging`, `hargaPackaging`, `statusPackaging`) VALUES
('1', 'Plastik', 5000, 0),
('2', 'B', 5000, 0),
('5fe0dcb4-c80a-431d-b957-e5b69f7335f0', 'Box', 9000, 1),
('8787e7e1-5958-4aa5-8215-f1909d58fa6a', 'Kardus', 9000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `produksi`
--

CREATE TABLE `produksi` (
  `idBOP` varchar(100) NOT NULL,
  `tglTransaksi` date DEFAULT NULL,
  `totalKm` float DEFAULT NULL,
  `idEkspedisi` varchar(100) DEFAULT NULL,
  `idPackaging` varchar(100) DEFAULT NULL,
  `statusProduksi` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produksi`
--

INSERT INTO `produksi` (`idBOP`, `tglTransaksi`, `totalKm`, `idEkspedisi`, `idPackaging`, `statusProduksi`) VALUES
('1', '2020-12-23', 78.5, '1', '1', 1),
('376b569c-4239-4d09-a541-0006ab063ec1', '2020-12-23', 78.5, '1', '1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `produksidetail`
--

CREATE TABLE `produksidetail` (
  `idDetail` varchar(100) NOT NULL,
  `idBOP` varchar(100) DEFAULT NULL,
  `idBahan` varchar(100) DEFAULT NULL,
  `qtyPemakaian` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produksidetail`
--

INSERT INTO `produksidetail` (`idDetail`, `idBOP`, `idBahan`, `qtyPemakaian`) VALUES
('9efa2db4-1773-4670-9f71-8c711f1e905b', '1', '1', 1000),
('ea1a7610-952c-439a-8fff-b38e21098a64', '376b569c-4239-4d09-a541-0006ab063ec1', '7f4a98f5-f221-415c-9319-e469580b22f3', 1000),
('efd3f0dd-f38d-4250-abcf-0294d93ada87', '376b569c-4239-4d09-a541-0006ab063ec1', '119a0f1b-1373-49d4-95b3-143c02b90155', 1000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bahan`
--
ALTER TABLE `bahan`
  ADD PRIMARY KEY (`idBahan`);

--
-- Indexes for table `ekspedisi`
--
ALTER TABLE `ekspedisi`
  ADD PRIMARY KEY (`idEkspedisi`);

--
-- Indexes for table `packaging`
--
ALTER TABLE `packaging`
  ADD PRIMARY KEY (`idPackaging`);

--
-- Indexes for table `produksi`
--
ALTER TABLE `produksi`
  ADD PRIMARY KEY (`idBOP`);

--
-- Indexes for table `produksidetail`
--
ALTER TABLE `produksidetail`
  ADD PRIMARY KEY (`idDetail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
