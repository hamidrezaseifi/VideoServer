-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jvideo
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblfolders`
--

DROP TABLE IF EXISTS `tblfolders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfolders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `state` smallint(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfolders`
--

LOCK TABLES `tblfolders` WRITE;
/*!40000 ALTER TABLE `tblfolders` DISABLE KEYS */;
INSERT INTO `tblfolders` VALUES (1,'Film','D:\\Media\\Film',1),(2,'TV','D:\\Media\\TV',1),(3,'Cartoon','D:\\Media\\Cartoon',1);
/*!40000 ALTER TABLE `tblfolders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpathsubtitle`
--

DROP TABLE IF EXISTS `tblpathsubtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblpathsubtitle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(1000) NOT NULL,
  `suburl` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpathsubtitle`
--

LOCK TABLES `tblpathsubtitle` WRITE;
/*!40000 ALTER TABLE `tblpathsubtitle` DISABLE KEYS */;
INSERT INTO `tblpathsubtitle` VALUES (1,'D:\\Media\\TV\\The Blacklist','https://subscene.com/subtitles/the-blacklist-fifth-season/farsi_persian'),(2,'D:\\Media\\TV\\The Good Doctor','https://subscene.com/subtitles/the-good-doctor-us-first-season/farsi_persian'),(3,'D:\\Media\\TV\\Counterpart','https://subscene.com/subtitles/counterpart-first-season'),(4,'D:\\Media\\TV\\The Alienist','https://subscene.com/subtitles/the-alienist-first-season'),(5,'D:\\Media\\TV\\The.X-Files','https://subscene.com/subtitles/the-x-files-eleventh-season-2018'),(11,'D:\\Media\\TV\\Homeland','https://subscene.com/subtitles/homeland-seventh-season'),(20,'D:\\Media\\TV\\Deception','https://subscene.com/subtitles/deception-first-season'),(21,'D:\\Media\\TV\\The Crossing','https://subscene.com/subtitles/the-crossing-first-season'),(22,'D:\\Media\\TV\\Krypton','https://subscene.com/subtitles/krypton-first-season'),(23,'D:\\Media\\TV\\Legion','https://subscene.com/subtitles/legion-second-season'),(24,'D:\\Media\\TV\\Instinct','https://subscene.com/subtitles/instinct-first-season'),(25,'D:\\Media\\TV\\Deep State','https://subscene.com/subtitles/deep-state-first-season'),(26,'D:\\Media\\TV\\Killing Eve','https://subscene.com/subtitles/killing-eve'),(27,'D:\\Media\\TV\\Into the Badlands','https://subscene.com/subtitles/into-the-badlands-third-season'),(31,'D:\\Media\\TV\\Westworld','https://subscene.com/subtitles/westworld-second-season-tv'),(37,'D:\\Media\\TV\\Suits','https://subscene.com/subtitles/suits-eighth-season'),(30,'D:\\Media\\TV\\Patrick Melrose','https://subscene.com/subtitles/patrick-melrose'),(32,'D:\\Media\\TV\\Reverie','https://subscene.com/subtitles/reverie-first-season-2018'),(35,'D:\\Media\\TV\\Impulse','https://subscene.com/subtitles/impulse-first-season'),(36,'D:\\Media\\TV\\Strange Angel','https://subscene.com/subtitles/strange-angel'),(38,'D:\\Media\\TV\\Castle Rock','https://subscene.com/subtitles/castle-rock/farsi_persian'),(39,'D:\\Media\\TV\\The Deuce','https://subscene.com/subtitles/the-deuce-second-season'),(40,'D:\\Media\\TV\\The First','https://subscene.com/subtitles/the-first-first-season/farsi_persian'),(41,'D:\\Media\\TV\\A Discovery Of Witches','https://subscene.com/subtitles/a-discovery-of-witches/farsi_persian'),(42,'D:\\Media\\TV\\Babylon Berlin','https://subscene.com/subtitles/babylon-berlin-first-season'),(43,'D:\\Media\\TV\\Titans','https://subscene.com/subtitles/taitans');
/*!40000 ALTER TABLE `tblpathsubtitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsettings`
--

DROP TABLE IF EXISTS `tblsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblsettings` (
  `settingname` varchar(45) NOT NULL,
  `settingvalue` varchar(500) NOT NULL,
  PRIMARY KEY (`settingname`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsettings`
--

LOCK TABLES `tblsettings` WRITE;
/*!40000 ALTER TABLE `tblsettings` DISABLE KEYS */;
INSERT INTO `tblsettings` VALUES ('subscene_search_path','https://subscene.com/subtitles/title?q=%1');
/*!40000 ALTER TABLE `tblsettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltabs`
--

DROP TABLE IF EXISTS `tbltabs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltabs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `tabindex` int(11) NOT NULL DEFAULT '0',
  `url` varchar(200) NOT NULL DEFAULT '-',
  `state` smallint(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltabs`
--

LOCK TABLES `tbltabs` WRITE;
/*!40000 ALTER TABLE `tbltabs` DISABLE KEYS */;
INSERT INTO `tbltabs` VALUES (1,'General',1,'general',1),(2,'Process',2,'process',1),(3,'Tools',10,'tools',1);
/*!40000 ALTER TABLE `tbltabs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-25 12:13:39
