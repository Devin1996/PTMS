-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2019 at 04:15 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ptms_sql`
--

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `b_id` int(11) NOT NULL,
  `b_reg_no` int(11) NOT NULL,
  `b_brand` varchar(20) NOT NULL,
  `b_class` varchar(20) NOT NULL,
  `b_root_no` int(11) NOT NULL,
  `b_root_name` varchar(20) NOT NULL,
  `b_driver_id` int(11) NOT NULL,
  `b_driver_name` varchar(20) NOT NULL,
  `b_con_id` int(11) NOT NULL,
  `b_con_name` varchar(20) NOT NULL,
  `b_driver_img` longblob NOT NULL,
  `b_con_img` longblob NOT NULL,
  `b_co_id` int(11) NOT NULL,
  `b_co_name` varchar(20) NOT NULL,
  `b_co_logo` longblob NOT NULL,
  `b_img` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `conductor`
--

CREATE TABLE `conductor` (
  `c_id` int(11) NOT NULL,
  `c_uname` varchar(20) NOT NULL,
  `c_fname` varchar(20) NOT NULL,
  `c_lname` varchar(20) NOT NULL,
  `c_address` varchar(200) NOT NULL,
  `c_image` longblob NOT NULL,
  `c_email` varchar(200) NOT NULL,
  `c_no` int(11) NOT NULL,
  `c_company_id` int(11) NOT NULL,
  `c_company_name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `d_id` int(11) NOT NULL,
  `d_uname` varchar(20) NOT NULL,
  `d_fname` varchar(20) NOT NULL,
  `d_lname` varchar(20) NOT NULL,
  `d_address` varchar(200) NOT NULL,
  `d_image` longblob NOT NULL,
  `d_email` varchar(200) NOT NULL,
  `d_no` int(11) NOT NULL,
  `d_company_id` int(11) NOT NULL,
  `d_company_name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE `passenger` (
  `p_id` int(11) NOT NULL,
  `p_uname` varchar(20) NOT NULL,
  `p_fname` varchar(20) NOT NULL,
  `p_lname` varchar(20) NOT NULL,
  `p_address` varchar(200) NOT NULL,
  `p_image` longblob NOT NULL,
  `p_email` varchar(200) NOT NULL,
  `p_no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `root`
--

CREATE TABLE `root` (
  `root_no` int(11) NOT NULL,
  `root_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `conductor`
--
ALTER TABLE `conductor`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`p_id`);

--
-- Indexes for table `root`
--
ALTER TABLE `root`
  ADD PRIMARY KEY (`root_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conductor`
--
ALTER TABLE `conductor`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `passenger`
--
ALTER TABLE `passenger`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `root`
--
ALTER TABLE `root`
  MODIFY `root_no` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
