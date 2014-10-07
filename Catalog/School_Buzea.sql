CREATE DATABASE  IF NOT EXISTS `school_buzea` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `school_buzea`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: school_buzea
-- ------------------------------------------------------
-- Server version	5.5.32

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
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog` (
  `ids` int(10) unsigned NOT NULL,
  `idcurs` int(11) NOT NULL,
  `data` date NOT NULL,
  `nota` int(11) NOT NULL,
  PRIMARY KEY (`ids`,`idcurs`,`data`),
  KEY `curs_idx` (`idcurs`),
  CONSTRAINT `stud` FOREIGN KEY (`ids`) REFERENCES `student` (`nr_matricol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `curs` FOREIGN KEY (`idcurs`) REFERENCES `curs` (`id_curs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,1,'2013-11-20',10),(1,1,'2013-12-01',10),(3,2,'2013-11-18',7);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curs`
--

DROP TABLE IF EXISTS `curs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curs` (
  `id_curs` int(11) NOT NULL AUTO_INCREMENT,
  `zi` varchar(45) DEFAULT NULL,
  `ora` int(11) DEFAULT NULL,
  `idg` int(11) NOT NULL,
  `idprofesor` int(11) NOT NULL,
  `idsala` int(11) NOT NULL,
  PRIMARY KEY (`id_curs`),
  KEY `grupa_idx` (`idg`),
  KEY `prof_idx` (`idprofesor`),
  KEY `sala_idx` (`idsala`),
  CONSTRAINT `grupa` FOREIGN KEY (`idg`) REFERENCES `grupa` (`idg`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prof` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sala` FOREIGN KEY (`idsala`) REFERENCES `sala` (`idsala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curs`
--

LOCK TABLES `curs` WRITE;
/*!40000 ALTER TABLE `curs` DISABLE KEYS */;
INSERT INTO `curs` VALUES (1,'Vineri',12,5,1,10),(2,'Marti',16,5,2,197),(4,'Luni',11,1,5,197);
/*!40000 ALTER TABLE `curs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departament`
--

DROP TABLE IF EXISTS `departament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departament` (
  `idd` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  PRIMARY KEY (`idd`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departament`
--

LOCK TABLES `departament` WRITE;
/*!40000 ALTER TABLE `departament` DISABLE KEYS */;
INSERT INTO `departament` VALUES (1,'Math'),(2,'DataBases'),(3,'Assembly'),(4,'ADC');
/*!40000 ALTER TABLE `departament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupa`
--

DROP TABLE IF EXISTS `grupa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupa` (
  `idg` int(11) NOT NULL AUTO_INCREMENT,
  `sectie` varchar(45) DEFAULT 'AC',
  `an` int(11) DEFAULT '1',
  PRIMARY KEY (`idg`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupa`
--

LOCK TABLES `grupa` WRITE;
/*!40000 ALTER TABLE `grupa` DISABLE KEYS */;
INSERT INTO `grupa` VALUES (1,'AC',1),(2,'AC',1),(3,'AC',1),(4,'AC',1),(5,'AC',2),(6,'AC',2),(7,'AC',2),(8,'AC',2);
/*!40000 ALTER TABLE `grupa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor` (
  `idprofesor` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  `prenume` varchar(45) NOT NULL,
  `idd` int(11) NOT NULL,
  PRIMARY KEY (`idprofesor`),
  KEY `idd_idx` (`idd`),
  CONSTRAINT `idd` FOREIGN KEY (`idd`) REFERENCES `departament` (`idd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'Calin','Cenan',2),(2,'Mircea','Ivan',1),(3,'Emil','Cebuc',3),(4,'Vasile','Dadarlat',4),(5,'Ioan','Rasa',1);
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `idsala` int(11) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(45) NOT NULL,
  PRIMARY KEY (`idsala`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (10,'Baritiu 26'),(197,'Baritiu 25'),(202,'Observator'),(206,'Observator');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `nr_matricol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prenume` varchar(45) NOT NULL,
  `nume` varchar(45) NOT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `idg` int(11) NOT NULL,
  PRIMARY KEY (`nr_matricol`),
  UNIQUE KEY `NrMatricol_UNIQUE` (`nr_matricol`),
  KEY `idg_idx` (`idg`),
  CONSTRAINT `idg` FOREIGN KEY (`idg`) REFERENCES `grupa` (`idg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Vlad','Buzzea','',5),(2,'Tudor','Citlos',NULL,5),(3,'Raul','Jantea',NULL,5),(4,'Andreea','Iepure',NULL,5),(5,'Ana','Palihovici',NULL,1),(6,'Ioana','Deac',NULL,3),(7,'Anda','Khreiss',NULL,4),(8,'Silvia','Campean',NULL,2),(9,'Sebastian','Mihalache',NULL,6),(10,'Remus','Varga',NULL,7),(11,'Ariel','Miculas',NULL,8);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-22 22:56:34
