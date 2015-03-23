-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 11, 2015 at 07:46 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `smart_events`
--

-- --------------------------------------------------------

--
-- Table structure for table `Event`
--

CREATE TABLE IF NOT EXISTS `Event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `event_time` time DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Event_User` (`User_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `Event`
--

INSERT INTO `Event` (`id`, `title`, `description`, `event_date`, `event_time`, `User_id`) VALUES
(19, 'liga sampiona', 'bla bla bla', '2014-04-03', '12:00:00', NULL),
(20, 'liga sampiona 2', 'bla bla bla', '2014-04-10', '12:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Event_Location`
--

CREATE TABLE IF NOT EXISTS `Event_Location` (
  `Event_id` int(11) NOT NULL,
  `Location_id` int(11) NOT NULL,
  PRIMARY KEY (`Event_id`,`Location_id`),
  KEY `fk_Event_has_Location_Location1` (`Location_id`),
  KEY `fk_Event_has_Location_Event1` (`Event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Event_Tag`
--

CREATE TABLE IF NOT EXISTS `Event_Tag` (
  `Event_id` int(11) NOT NULL,
  `Tag_id` int(11) NOT NULL,
  PRIMARY KEY (`Event_id`,`Tag_id`),
  KEY `fk_Event_has_Tag_Tag1` (`Tag_id`),
  KEY `fk_Event_has_Tag_Event1` (`Event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Location`
--

CREATE TABLE IF NOT EXISTS `Location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Message`
--

CREATE TABLE IF NOT EXISTS `Message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(85) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Tag`
--

CREATE TABLE IF NOT EXISTS `Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `role`) VALUES
(1, 'Mima', 'Vasiljevic', 'mimavasiljevic', 'mima.vasiljevic.370@metropolitan.ac.rs', 'PasZ', 1),
(2, 'Mima', 'Vasiljevic', 'admin', 'mima.vasiljevic.370@metropolitan.ac.rs', 'PasZ', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Event`
--
ALTER TABLE `Event`
  ADD CONSTRAINT `fk_Event_User` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Event_Location`
--
ALTER TABLE `Event_Location`
  ADD CONSTRAINT `fk_Event_has_Location_Event1` FOREIGN KEY (`Event_id`) REFERENCES `Event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Event_has_Location_Location1` FOREIGN KEY (`Location_id`) REFERENCES `Location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Event_Tag`
--
ALTER TABLE `Event_Tag`
  ADD CONSTRAINT `fk_Event_has_Tag_Event1` FOREIGN KEY (`Event_id`) REFERENCES `Event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Event_has_Tag_Tag1` FOREIGN KEY (`Tag_id`) REFERENCES `Tag` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
