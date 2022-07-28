-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 30, 2022 at 12:52 AM
-- Server version: 8.0.29-0ubuntu0.22.04.2
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlineBiddingProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `profile_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`profile_id`) VALUES
(11);

-- --------------------------------------------------------

--
-- Table structure for table `auction`
--

CREATE TABLE `auction` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` int NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  `price_start` double DEFAULT '0',
  `price_step` double DEFAULT '0',
  `highest_price` double DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `auction`
--

INSERT INTO `auction` (`id`, `user_id`, `category_id`, `description`, `status`, `time_start`, `time_end`, `price_start`, `price_step`, `highest_price`, `created_at`, `updated_at`) VALUES
(64, 6, 3, 'asd', 4, '2022-07-22 22:17:00', '2022-07-23 22:15:00', 123123, 123, 500000, '2022-07-22 22:15:21', '2022-07-22 23:42:30'),
(66, 6, 3, 'asdf', -1, '2022-07-23 23:42:00', '2022-07-23 23:46:00', 12344, 1234, 0, '2022-07-23 23:39:35', '2022-07-23 23:41:12'),
(67, 6, 2, 'asd', -1, '2022-07-23 23:44:00', '2022-07-24 23:43:00', 123, 123, 0, '2022-07-23 23:42:23', '2022-07-23 23:42:47'),
(71, 8, 4, 'asd', 4, '2022-07-25 17:27:00', '2022-07-26 17:23:00', 123123, 213, 123123, '2022-07-25 17:23:36', '2022-07-25 18:06:11'),
(73, 9, 2, 'asd', -1, '2022-07-27 17:21:00', '2022-07-28 17:19:00', 123123, 123123, 0, '2022-07-27 17:20:13', '2022-07-27 17:20:31'),
(75, 9, 2, 'asd', 4, '2022-07-27 17:36:00', '2022-07-27 17:37:00', 100000, 50000, 123000, '2022-07-27 17:34:41', '2022-07-27 17:36:33'),
(79, 6, 3, 'asd', -1, '2022-07-29 22:56:00', '2022-07-30 22:55:00', 100000, 50000, 0, '2022-07-29 22:55:37', '2022-07-29 22:55:40'),
(83, 6, 1, 'a', 4, '2022-07-30 00:30:00', '2022-07-30 00:31:00', 100000, 50000, 200000, '2022-07-30 00:28:50', '2022-07-30 00:30:26'),
(84, 8, 1, 'a', -1, '2022-07-30 00:43:00', '2022-07-30 00:44:00', 100000, 50000, 0, '2022-07-30 00:42:14', '2022-07-30 00:42:17'),
(85, 8, 1, 'a', -1, '2022-07-30 00:46:00', '2022-07-30 00:47:00', 100000, 50000, 0, '2022-07-30 00:45:17', '2022-07-30 00:45:20'),
(86, 9, 1, 'a', 4, '2022-07-30 00:48:00', '2022-07-30 00:49:00', 100000, 50000, 100000, '2022-07-30 00:47:20', '2022-07-30 00:48:35');

-- --------------------------------------------------------

--
-- Table structure for table `auction_user`
--

CREATE TABLE `auction_user` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `auction_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `auction_user`
--

INSERT INTO `auction_user` (`id`, `user_id`, `auction_id`) VALUES
(14, 6, 71),
(19, 6, 75),
(13, 8, 64),
(25, 8, 83),
(27, 8, 86),
(26, 9, 83);

-- --------------------------------------------------------

--
-- Table structure for table `bid`
--

CREATE TABLE `bid` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `auction_id` int NOT NULL,
  `price` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bid`
--

INSERT INTO `bid` (`id`, `user_id`, `auction_id`, `price`, `created_at`, `updated_at`) VALUES
(97, 8, 64, 123123, '2022-07-22 22:22:55', '2022-07-22 22:22:55'),
(98, 8, 64, 123321, '2022-07-22 22:23:01', '2022-07-22 22:23:01'),
(99, 8, 64, 300000, '2022-07-22 23:42:10', '2022-07-22 23:42:10'),
(100, 8, 64, 500000, '2022-07-22 23:42:30', '2022-07-22 23:42:30'),
(101, 6, 71, 123123, '2022-07-25 18:06:11', '2022-07-25 18:06:11'),
(113, 6, 75, 123000, '2022-07-27 17:36:33', '2022-07-27 17:36:33'),
(120, 8, 83, 100000, '2022-07-30 00:30:13', '2022-07-30 00:30:13'),
(121, 8, 83, 150000, '2022-07-30 00:30:18', '2022-07-30 00:30:18'),
(122, 9, 83, 200000, '2022-07-30 00:30:27', '2022-07-30 00:30:27'),
(123, 8, 86, 100000, '2022-07-30 00:48:35', '2022-07-30 00:48:35');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Đồ điện tử'),
(2, 'Thời trang'),
(3, 'Đồ nội thất'),
(4, 'Giải trí'),
(5, 'Nhà cửa'),
(6, 'Sức khoẻ'),
(7, 'Thú cưng'),
(8, 'Nghệ thuật'),
(9, 'Trang sức'),
(10, 'Thực phẩm'),
(11, 'Khoa học');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int NOT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `amount` int NOT NULL,
  `auction_id` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `name`, `description`, `amount`, `auction_id`, `created_at`, `updated_at`) VALUES
(54, 'asd', 'asd', 1, 64, '2022-07-22 22:15:21', '2022-07-22 22:15:21'),
(56, 'asdf', 'asdf', 1, 66, '2022-07-23 23:39:35', '2022-07-23 23:39:35'),
(57, 'asd', 'asd', 1, 67, '2022-07-23 23:42:23', '2022-07-23 23:42:23'),
(61, 'asd', 'asd', 1, 71, '2022-07-25 17:23:36', '2022-07-25 17:23:36'),
(63, 'asd', 'asd', 1, 73, '2022-07-27 17:20:13', '2022-07-27 17:20:13'),
(65, 'a', 'a', 1, 75, '2022-07-27 17:34:41', '2022-07-27 17:34:41'),
(69, 'a', 'a', 1, 79, '2022-07-29 22:55:37', '2022-07-29 22:55:37'),
(73, 'a', 'a', 1, 83, '2022-07-30 00:28:50', '2022-07-30 00:28:50'),
(74, 'a', 'a', 1, 84, '2022-07-30 00:42:14', '2022-07-30 00:42:14'),
(75, 'a', 'a', 1, 85, '2022-07-30 00:45:17', '2022-07-30 00:45:17'),
(76, 'a', 'a', 1, 86, '2022-07-30 00:47:20', '2022-07-30 00:47:20');

-- --------------------------------------------------------

--
-- Table structure for table `item_image`
--

CREATE TABLE `item_image` (
  `id` int NOT NULL,
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `public_id` varchar(1000) DEFAULT NULL,
  `item_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_image`
--

INSERT INTO `item_image` (`id`, `image_url`, `public_id`, `item_id`) VALUES
(36, 'https://res.cloudinary.com/metanoia/image/upload/v1658917208/onlinebiddingproject/oxdytkzyeasz8bots07h.jpg', 'onlinebiddingproject/oxdytkzyeasz8bots07h', 63);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int NOT NULL,
  `profile_id` int DEFAULT NULL,
  `notification_type` varchar(50) NOT NULL,
  `entity_type` varchar(25) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `profile_id`, `notification_type`, `entity_type`, `created_at`, `updated_at`) VALUES
(15, 11, 'REVIEW_AUCTION', 'AUCTION', '2022-07-30 00:29:03', '2022-07-30 00:29:03'),
(16, 9, 'NEW_BID_AUCTION', 'AUCTION', '2022-07-30 00:29:03', '2022-07-30 00:30:27'),
(17, NULL, 'START_AUCTION', 'AUCTION', '2022-07-30 00:30:00', '2022-07-30 00:30:00'),
(18, NULL, 'END_AUCTION', 'AUCTION', '2022-07-30 00:31:00', '2022-07-30 00:31:00'),
(22, 11, 'REVIEW_AUCTION', 'AUCTION', '2022-07-30 00:47:32', '2022-07-30 00:47:32'),
(23, NULL, 'START_AUCTION', 'AUCTION', '2022-07-30 00:48:00', '2022-07-30 00:48:00'),
(24, 8, 'NEW_BID_AUCTION', 'AUCTION', '2022-07-30 00:48:00', '2022-07-30 00:48:35'),
(25, NULL, 'END_AUCTION', 'AUCTION', '2022-07-30 00:49:00', '2022-07-30 00:49:00');

-- --------------------------------------------------------

--
-- Table structure for table `notification_auction`
--

CREATE TABLE `notification_auction` (
  `id` int NOT NULL,
  `auction_id` int NOT NULL,
  `notification_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification_auction`
--

INSERT INTO `notification_auction` (`id`, `auction_id`, `notification_id`) VALUES
(13, 83, 15),
(14, 83, 16),
(15, 83, 17),
(16, 83, 18),
(20, 86, 22),
(21, 86, 23),
(22, 86, 24),
(23, 86, 25);

-- --------------------------------------------------------

--
-- Table structure for table `notification_notified`
--

CREATE TABLE `notification_notified` (
  `id` int NOT NULL,
  `notification_id` int NOT NULL,
  `profile_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification_notified`
--

INSERT INTO `notification_notified` (`id`, `notification_id`, `profile_id`) VALUES
(14, 15, 6),
(15, 16, 6),
(16, 17, 6),
(17, 16, 8),
(18, 16, 9),
(19, 18, 6),
(20, 18, 8),
(21, 18, 9),
(25, 22, 9),
(26, 23, 9),
(27, 24, 9),
(28, 24, 8),
(29, 25, 9),
(30, 25, 8);

-- --------------------------------------------------------

--
-- Table structure for table `notification_report`
--

CREATE TABLE `notification_report` (
  `id` int NOT NULL,
  `report_id` int NOT NULL,
  `notification_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` int NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role_id` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `username`, `password`, `name`, `status`, `email`, `image_url`, `role_id`, `created_at`, `updated_at`) VALUES
(6, 'nguoidung1', '$2a$10$krqgee7paXEfPRq/6RaCkOAm/U/IkRfVOaAdfIzuKxxND3sh74hP2', 'thanh', 2, 'thanhthanhthanh@gmail.com', NULL, 2, '2022-07-05 14:08:36', '2022-07-25 10:24:17'),
(8, 'nguoidung3', '$2a$10$2s/6CA8LQnptxgaLslGQrupfBbf8LdKGQXaC9kS0Cxe4pjj8Qu7GK', 'nguoidung', 2, NULL, NULL, 2, '2022-07-06 12:08:27', '2022-07-06 12:08:27'),
(9, 'nguoidung4', '$2a$10$5cebCC/r0VADhNvcT7MluOgXmzYTAOCyLxmPjr7owOh.reR6JA1Mu', 'nguoidung', 2, NULL, NULL, 2, '2022-07-06 13:52:04', '2022-07-06 13:52:04'),
(10, 'nguoidung5', '$2a$10$DU4mDl6z.rh/tGUZWHvQQe5uMaQwM5WcdJzY5oaRLsllaVXPlq2hm', 'nguoidung', 2, NULL, NULL, 2, '2022-07-06 13:54:14', '2022-07-06 13:54:14'),
(11, 'adminadmin', '$2a$10$n7rgvfnOhz8MesOsHpHwJ.Djuz4/ohBXg1pV7RonC683erIQHsu8m', 'admin', 2, NULL, NULL, 1, '2022-07-06 22:38:52', '2022-07-06 22:38:52'),
(12, 'nguoidung', '$2a$10$xpqqLaxYLxRB79mMlLOBTu8XthDx6h2fhO.dWGq2MDF0kHCgXV6L2', 'thanhthanh', 2, NULL, NULL, 2, '2022-07-08 23:19:15', '2022-07-08 23:19:15'),
(13, 'thanhng260588', '$2a$10$pUd9A3Sf16Z8VdS6lWRO0.yMAxuOjONINLz69Ua27SzQSczgO6klm', 'thanhthanh', 2, 'thanhng260588@gmail.com', NULL, 2, '2022-07-25 09:50:40', '2022-07-25 17:35:52');

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_CALENDARS`
--

CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
('quartzScheduler', 'END76', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #76', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000004c7800),
('quartzScheduler', 'END77', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #77', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000004d7800),
('quartzScheduler', 'END78', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #78', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000004e7800),
('quartzScheduler', 'END80', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #80', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000507800),
('quartzScheduler', 'END81', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #81', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000517800),
('quartzScheduler', 'END82', 'END AUCTIONS', 'SCHEDULING ENDING AUCTION #82', 'com.ghtk.onlinebiddingproject.jobs.EndAuctionJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000961756374696f6e4964737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000527800);

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_LOCKS`
--

CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `QRTZ_LOCKS`
--

INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES
('quartzScheduler', 'TRIGGER_ACCESS');

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

INSERT INTO `QRTZ_SIMPLE_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `REPEAT_COUNT`, `REPEAT_INTERVAL`, `TIMES_TRIGGERED`) VALUES
('quartzScheduler', 'END76', 'DEFAULT', 0, 0, 0),
('quartzScheduler', 'END77', 'DEFAULT', 0, 0, 0),
('quartzScheduler', 'END78', 'DEFAULT', 0, 0, 0),
('quartzScheduler', 'END80', 'DEFAULT', 0, 0, 0),
('quartzScheduler', 'END81', 'DEFAULT', 0, 0, 0),
('quartzScheduler', 'END82', 'DEFAULT', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `QRTZ_TRIGGERS`
--

CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
('quartzScheduler', 'END76', 'DEFAULT', 'END76', 'END AUCTIONS', NULL, 1659243960000, -1, 5, 'WAITING', 'SIMPLE', 1659243960000, 0, NULL, 1, ''),
('quartzScheduler', 'END77', 'DEFAULT', 'END77', 'END AUCTIONS', NULL, 1659193020000, -1, 5, 'WAITING', 'SIMPLE', 1659193020000, 0, NULL, 1, ''),
('quartzScheduler', 'END78', 'DEFAULT', 'END78', 'END AUCTIONS', NULL, 1659193560000, -1, 5, 'WAITING', 'SIMPLE', 1659193560000, 0, NULL, 1, ''),
('quartzScheduler', 'END80', 'DEFAULT', 'END80', 'END AUCTIONS', NULL, 1659197280000, -1, 5, 'WAITING', 'SIMPLE', 1659197280000, 0, NULL, 1, ''),
('quartzScheduler', 'END81', 'DEFAULT', 'END81', 'END AUCTIONS', NULL, 1659200640000, -1, 5, 'WAITING', 'SIMPLE', 1659200640000, 0, NULL, 1, ''),
('quartzScheduler', 'END82', 'DEFAULT', 'END82', 'END AUCTIONS', NULL, 1659201840000, -1, 5, 'WAITING', 'SIMPLE', 1659201840000, 0, NULL, 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int NOT NULL,
  `user_reporter_id` int NOT NULL,
  `user_reported_id` int NOT NULL,
  `auction_id` int DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `user_reporter_id`, `user_reported_id`, `auction_id`, `description`, `created_at`, `updated_at`) VALUES
(2, 8, 6, NULL, 'asdf', '2022-07-22 23:34:41', '2022-07-22 23:34:41'),
(3, 8, 6, NULL, 'asd', '2022-07-22 23:34:53', '2022-07-22 23:34:53'),
(4, 8, 6, NULL, 'asd', '2022-07-22 23:36:58', '2022-07-22 23:36:58'),
(5, 8, 6, NULL, 'asd', '2022-07-22 23:37:42', '2022-07-22 23:37:42'),
(7, 8, 6, NULL, 'adu thang nay xam l', '2022-07-23 20:15:11', '2022-07-23 20:15:11'),
(9, 8, 6, NULL, 'asd', '2022-07-23 20:17:08', '2022-07-23 20:17:08'),
(17, 8, 9, NULL, 'aqsdasd', '2022-07-23 22:35:41', '2022-07-23 22:35:41');

-- --------------------------------------------------------

--
-- Table structure for table `report_image`
--

CREATE TABLE `report_image` (
  `id` int NOT NULL,
  `report_id` int NOT NULL,
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `public_id` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `report_image`
--

INSERT INTO `report_image` (`id`, `report_id`, `image_url`, `public_id`) VALUES
(4, 2, 'https://scontent.fhan15-1.fna.fbcdn.net/v/t39.30808-6/295248025_5619158588102826_8311340490183514443_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5cd70e&_nc_ohc=KBVsdYCE5xUAX-qV-0X&_nc_ht=scontent.fhan15-1.fna&oh=00_AT_ISR_GYjgEsOtP6zU8Si3Xh5wjSGVMHEpmudLIEYRMOQ&oe=62E1B08C', 'https://scontent.fhan15-1.fna.fbcdn.net/v/t39.30808-6/295248025_5619158588102826_8311340490183514443_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5cd70e&_nc_ohc=KBVsdYCE5xUAX-qV-0X&_nc_ht=scontent.fhan15-1.fna&oh=00_AT_ISR_GYjgEsOtP6zU8Si3Xh5wjSGVMHEpmudLIEYRMOQ&oe=62E1B08C'),
(5, 7, 'https://res.cloudinary.com/metanoia/image/upload/v1658582103/onlinebiddingproject/wkx6mjfvepoxvskgid2y.jpg', 'onlinebiddingproject/wkx6mjfvepoxvskgid2y');

-- --------------------------------------------------------

--
-- Table structure for table `report_result`
--

CREATE TABLE `report_result` (
  `id` int NOT NULL,
  `admin_id` int NOT NULL,
  `report_id` int NOT NULL,
  `result` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `report_result`
--

INSERT INTO `report_result` (`id`, `admin_id`, `report_id`, `result`, `created_at`, `updated_at`) VALUES
(3, 11, 2, 1, '2022-07-23 13:20:40', '2022-07-23 13:20:40'),
(4, 11, 3, 0, '2022-07-23 13:27:39', '2022-07-23 13:27:39'),
(5, 11, 4, 0, '2022-07-23 13:28:13', '2022-07-23 13:28:13'),
(6, 11, 5, 1, '2022-07-23 20:14:17', '2022-07-23 20:14:17'),
(7, 11, 9, 0, '2022-07-23 20:35:29', '2022-07-23 20:35:29'),
(8, 11, 7, 0, '2022-07-23 22:12:53', '2022-07-23 22:12:53'),
(9, 11, 17, 1, '2022-07-26 17:28:17', '2022-07-26 17:28:17');

-- --------------------------------------------------------

--
-- Table structure for table `review_result`
--

CREATE TABLE `review_result` (
  `id` int NOT NULL,
  `admin_id` int NOT NULL,
  `auction_id` int NOT NULL,
  `result` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `review_result`
--

INSERT INTO `review_result` (`id`, `admin_id`, `auction_id`, `result`, `created_at`, `updated_at`) VALUES
(9, 11, 64, 1, '2022-07-22 22:15:37', '2022-07-22 22:15:37'),
(15, 11, 71, 1, '2022-07-25 17:24:26', '2022-07-25 17:24:26'),
(17, 11, 73, 1, '2022-07-27 17:20:31', '2022-07-27 17:20:31'),
(19, 11, 75, 1, '2022-07-27 17:34:54', '2022-07-27 17:34:54'),
(26, 11, 83, 1, '2022-07-30 00:29:03', '2022-07-30 00:29:03'),
(30, 11, 86, 1, '2022-07-30 00:47:32', '2022-07-30 00:47:32');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `profile_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`profile_id`) VALUES
(6),
(8),
(9),
(10),
(12),
(13);

-- --------------------------------------------------------

--
-- Table structure for table `verification_token`
--

CREATE TABLE `verification_token` (
  `id` int NOT NULL,
  `expiration_time` datetime NOT NULL,
  `token` varchar(1000) NOT NULL,
  `profile_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `winner`
--

CREATE TABLE `winner` (
  `id` int NOT NULL,
  `bid_id` int NOT NULL,
  `auction_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `winner`
--

INSERT INTO `winner` (`id`, `bid_id`, `auction_id`) VALUES
(675, 100, 64),
(676, 101, 71),
(677, 113, 75),
(678, 122, 83),
(679, 123, 86);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`profile_id`),
  ADD UNIQUE KEY `user_id` (`profile_id`);

--
-- Indexes for table `auction`
--
ALTER TABLE `auction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `auction_user`
--
ALTER TABLE `auction_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nguoidung_id` (`user_id`,`auction_id`),
  ADD KEY `baidaugia_id` (`auction_id`);

--
-- Indexes for table `bid`
--
ALTER TABLE `bid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `baidaugia_id` (`auction_id`),
  ADD KEY `nguoidung_id` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auction_id` (`auction_id`);

--
-- Indexes for table `item_image`
--
ALTER TABLE `item_image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sanpham_id` (`item_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profile_id` (`profile_id`);

--
-- Indexes for table `notification_auction`
--
ALTER TABLE `notification_auction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auction_id` (`auction_id`),
  ADD KEY `notification_id` (`notification_id`);

--
-- Indexes for table `notification_notified`
--
ALTER TABLE `notification_notified`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notification_id` (`notification_id`),
  ADD KEY `profile_id` (`profile_id`);

--
-- Indexes for table `notification_report`
--
ALTER TABLE `notification_report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `report_id` (`report_id`),
  ADD KEY `notification_id` (`notification_id`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tai_khoan` (`username`),
  ADD KEY `vaitro_id` (`role_id`);

--
-- Indexes for table `QRTZ_BLOB_TRIGGERS`
--
ALTER TABLE `QRTZ_BLOB_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  ADD KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_CALENDARS`
--
ALTER TABLE `QRTZ_CALENDARS`
  ADD PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`);

--
-- Indexes for table `QRTZ_CRON_TRIGGERS`
--
ALTER TABLE `QRTZ_CRON_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_FIRED_TRIGGERS`
--
ALTER TABLE `QRTZ_FIRED_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  ADD KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  ADD KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  ADD KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  ADD KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  ADD KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  ADD KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_JOB_DETAILS`
--
ALTER TABLE `QRTZ_JOB_DETAILS`
  ADD PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  ADD KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  ADD KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`);

--
-- Indexes for table `QRTZ_LOCKS`
--
ALTER TABLE `QRTZ_LOCKS`
  ADD PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`);

--
-- Indexes for table `QRTZ_PAUSED_TRIGGER_GRPS`
--
ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_SCHEDULER_STATE`
--
ALTER TABLE `QRTZ_SCHEDULER_STATE`
  ADD PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`);

--
-- Indexes for table `QRTZ_SIMPLE_TRIGGERS`
--
ALTER TABLE `QRTZ_SIMPLE_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_SIMPROP_TRIGGERS`
--
ALTER TABLE `QRTZ_SIMPROP_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`);

--
-- Indexes for table `QRTZ_TRIGGERS`
--
ALTER TABLE `QRTZ_TRIGGERS`
  ADD PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  ADD KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  ADD KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  ADD KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  ADD KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  ADD KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  ADD KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  ADD KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  ADD KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  ADD KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  ADD KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  ADD KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  ADD KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `baidaugia_id` (`auction_id`),
  ADD KEY `nguoidung_id_reported` (`user_reported_id`),
  ADD KEY `nguoidung_id_reporter` (`user_reporter_id`);

--
-- Indexes for table `report_image`
--
ALTER TABLE `report_image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `phieubaocao_id` (`report_id`);

--
-- Indexes for table `report_result`
--
ALTER TABLE `report_result`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `admin_id` (`admin_id`,`report_id`),
  ADD KEY `phieubaocao_id` (`report_id`);

--
-- Indexes for table `review_result`
--
ALTER TABLE `review_result`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `admin_id` (`admin_id`,`auction_id`),
  ADD KEY `baidaugia_id` (`auction_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`profile_id`),
  ADD UNIQUE KEY `user_id` (`profile_id`);

--
-- Indexes for table `verification_token`
--
ALTER TABLE `verification_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `verification_token_ibfk_1` (`profile_id`);

--
-- Indexes for table `winner`
--
ALTER TABLE `winner`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auction_id` (`auction_id`),
  ADD KEY `lenhdaugia_id` (`bid_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auction`
--
ALTER TABLE `auction`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT for table `auction_user`
--
ALTER TABLE `auction_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `bid`
--
ALTER TABLE `bid`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `item_image`
--
ALTER TABLE `item_image`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `notification_auction`
--
ALTER TABLE `notification_auction`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `notification_notified`
--
ALTER TABLE `notification_notified`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `notification_report`
--
ALTER TABLE `notification_report`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `report_image`
--
ALTER TABLE `report_image`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `report_result`
--
ALTER TABLE `report_result`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `review_result`
--
ALTER TABLE `review_result`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `verification_token`
--
ALTER TABLE `verification_token`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `winner`
--
ALTER TABLE `winner`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=680;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `auction`
--
ALTER TABLE `auction`
  ADD CONSTRAINT `auction_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `auction_ibfk_6` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT;

--
-- Constraints for table `auction_user`
--
ALTER TABLE `auction_user`
  ADD CONSTRAINT `auction_user_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `auction_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `item_image`
--
ALTER TABLE `item_image`
  ADD CONSTRAINT `item_image_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `notification_auction`
--
ALTER TABLE `notification_auction`
  ADD CONSTRAINT `notification_auction_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `notification_auction_ibfk_2` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `notification_notified`
--
ALTER TABLE `notification_notified`
  ADD CONSTRAINT `notification_notified_ibfk_1` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `notification_notified_ibfk_2` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `notification_report`
--
ALTER TABLE `notification_report`
  ADD CONSTRAINT `notification_report_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `notification_report_ibfk_2` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `QRTZ_BLOB_TRIGGERS`
--
ALTER TABLE `QRTZ_BLOB_TRIGGERS`
  ADD CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- Constraints for table `QRTZ_CRON_TRIGGERS`
--
ALTER TABLE `QRTZ_CRON_TRIGGERS`
  ADD CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- Constraints for table `QRTZ_SIMPLE_TRIGGERS`
--
ALTER TABLE `QRTZ_SIMPLE_TRIGGERS`
  ADD CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- Constraints for table `QRTZ_SIMPROP_TRIGGERS`
--
ALTER TABLE `QRTZ_SIMPROP_TRIGGERS`
  ADD CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`);

--
-- Constraints for table `QRTZ_TRIGGERS`
--
ALTER TABLE `QRTZ_TRIGGERS`
  ADD CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`);

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `report_ibfk_3` FOREIGN KEY (`user_reported_id`) REFERENCES `user` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `report_ibfk_4` FOREIGN KEY (`user_reporter_id`) REFERENCES `user` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `report_image`
--
ALTER TABLE `report_image`
  ADD CONSTRAINT `report_image_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `report_result`
--
ALTER TABLE `report_result`
  ADD CONSTRAINT `report_result_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `report_result_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `review_result`
--
ALTER TABLE `review_result`
  ADD CONSTRAINT `review_result_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`profile_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `review_result_ibfk_2` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `verification_token`
--
ALTER TABLE `verification_token`
  ADD CONSTRAINT `verification_token_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Constraints for table `winner`
--
ALTER TABLE `winner`
  ADD CONSTRAINT `winner_ibfk_1` FOREIGN KEY (`bid_id`) REFERENCES `bid` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `winner_ibfk_2` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
