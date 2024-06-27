-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 26, 2024 at 02:26 PM
-- Server version: 5.7.33
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffe_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `ban`
--

CREATE TABLE `ban` (
  `soBan` int(11) NOT NULL,
  `trangThai` tinyint(1) DEFAULT '0',
  `avalable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `ban`
--

INSERT INTO `ban` (`soBan`, `trangThai`, `avalable`) VALUES
(1, 0, 1),
(2, 1, 1),
(3, 1, 1),
(4, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `ID` int(11) NOT NULL,
  `soBan` int(11) DEFAULT NULL,
  `checkIn` datetime DEFAULT NULL,
  `checkOut` datetime DEFAULT NULL,
  `idNhanVien` int(11) DEFAULT NULL,
  `isPayed` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`ID`, `soBan`, `checkIn`, `checkOut`, `idNhanVien`, `isPayed`) VALUES
(1, 2, '2018-12-12 23:50:55', '2024-06-19 17:24:13', 2, 1),
(2, 1, '2023-12-15 22:03:00', '2023-12-15 22:03:54', 2, 1),
(3, 3, '2023-12-15 22:04:04', '2024-06-19 17:24:16', 2, 1),
(4, 4, '2023-12-15 22:04:20', '2024-06-19 17:24:18', 2, 1),
(5, 1, '2024-06-19 17:22:15', '2024-06-19 17:24:09', 2, 1),
(6, 1, '2024-06-19 20:52:19', '2024-06-19 20:52:25', 2, 1),
(7, 1, '2024-06-19 20:52:45', '2024-06-19 20:52:46', 2, 1),
(8, 1, '2024-06-19 20:52:53', NULL, NULL, 0),
(9, 2, '2024-06-22 13:30:30', '2024-06-22 13:30:35', 2, 1),
(10, 2, '2024-06-22 13:37:09', '2024-06-22 13:37:11', 2, 1),
(11, 2, '2024-06-22 13:37:23', '2024-06-22 13:37:34', 2, 1),
(12, 4, '2024-06-22 13:40:43', '2024-06-22 13:40:45', 4, 1),
(13, 2, '2024-06-22 14:16:15', '2024-06-22 14:16:24', 2, 1),
(14, 4, '2024-06-22 14:16:41', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `IDHoaDon` int(11) DEFAULT NULL,
  `IDVatPham` int(11) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `thoiGianGoiMon` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `orderitem`
--

INSERT INTO `orderitem` (`IDHoaDon`, `IDVatPham`, `soLuong`, `thoiGianGoiMon`) VALUES
(1, 2, 2, '06:45:00'),
(1, 3, 1, '06:45:00'),
(1, 1, 2, '06:45:00'),
(2, 1, 3, '22:02:42'),
(2, 2, 2, '22:02:49'),
(2, 3, 1, '22:02:52'),
(3, 1, 1, '22:04:00'),
(3, 2, 1, '22:04:02'),
(4, 2, 1, '22:04:16'),
(4, 3, 1, '22:04:18'),
(1, 2, 1, '17:21:36'),
(1, 3, 1, '17:21:40'),
(5, 2, 1, '17:22:10'),
(5, 3, 1, '17:22:13'),
(6, 1, 5, '20:51:33'),
(6, 2, 2, '20:52:12'),
(7, 1, 1, '20:52:43'),
(8, 2, 1, '20:52:49'),
(9, 3, 1, '13:30:26'),
(10, 5, 15, '13:36:35'),
(10, 3, 4, '13:36:51'),
(10, 2, 4, '13:37:03'),
(11, 5, 12, '13:37:20'),
(12, 5, 1, '13:40:41'),
(13, 5, 3, '14:16:03'),
(13, 3, 5, '14:16:09'),
(14, 6, 1, '14:16:39');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `ID` int(11) NOT NULL,
  `tenQuyen` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`ID`, `tenQuyen`) VALUES
(1, 'admin'),
(2, 'Nhân viên');

-- --------------------------------------------------------

--
-- Table structure for table `thongtinnguoidung`
--

CREATE TABLE `thongtinnguoidung` (
  `ID` int(11) NOT NULL,
  `hoVaTen` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `diaChi` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `taiKhoan` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `matKhau` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `IDQuyen` int(11) DEFAULT NULL,
  `available` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `thongtinnguoidung`
--

INSERT INTO `thongtinnguoidung` (`ID`, `hoVaTen`, `ngaySinh`, `diaChi`, `taiKhoan`, `matKhau`, `IDQuyen`, `available`) VALUES
(1, 'admin', '2024-06-10', 'HaNoi', 'admin', 'admin', 1, 1),
(2, 'Van A', '2003-12-12', 'soc son', 'danThuong', 'danThuong', 2, 1),
(3, 'Van AB', '2003-12-12', 'soc son', '1', '1', 2, 1),
(4, 'Quân', '2003-12-12', 'Thanh Hoa', 'quan', '1', 2, 1),
(6, 'jung', '2003-12-12', 'hanoi', 'jung', 'jung', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `vatpham`
--

CREATE TABLE `vatpham` (
  `ID` int(11) NOT NULL,
  `tenVatPham` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `donGia` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `available` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `vatpham`
--

INSERT INTO `vatpham` (`ID`, `tenVatPham`, `donGia`, `available`) VALUES
(1, 'cà phê đen', '20000', 1),
(2, 'cà phê sữa 3', '25000', 0),
(3, 'ca pô chi nô', '40000', 1),
(5, 'Kem dừa', '270000', 1),
(6, 'trà chanh', '20000', 0),
(7, 'test', '20000', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ban`
--
ALTER TABLE `ban`
  ADD PRIMARY KEY (`soBan`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `soBan` (`soBan`),
  ADD KEY `idNhanVien` (`idNhanVien`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD KEY `IDVatPham` (`IDVatPham`),
  ADD KEY `IDHoaDon` (`IDHoaDon`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `thongtinnguoidung`
--
ALTER TABLE `thongtinnguoidung`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDQuyen` (`IDQuyen`);

--
-- Indexes for table `vatpham`
--
ALTER TABLE `vatpham`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ban`
--
ALTER TABLE `ban`
  MODIFY `soBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `thongtinnguoidung`
--
ALTER TABLE `thongtinnguoidung`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `vatpham`
--
ALTER TABLE `vatpham`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`soBan`) REFERENCES `ban` (`soBan`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`idNhanVien`) REFERENCES `thongtinnguoidung` (`ID`);

--
-- Constraints for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`IDVatPham`) REFERENCES `vatpham` (`ID`),
  ADD CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`IDHoaDon`) REFERENCES `hoadon` (`ID`);

--
-- Constraints for table `thongtinnguoidung`
--
ALTER TABLE `thongtinnguoidung`
  ADD CONSTRAINT `thongtinnguoidung_ibfk_1` FOREIGN KEY (`IDQuyen`) REFERENCES `quyen` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
