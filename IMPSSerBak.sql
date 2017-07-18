-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: eMpower
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `PaysoMO_IMPS_Req`
--

DROP TABLE IF EXISTS `PaysoMO_IMPS_Req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaysoMO_IMPS_Req` (
  `Code` varchar(100) NOT NULL,
  `InputParameters` varchar(100) NOT NULL,
  `TraceNo` varchar(50) DEFAULT NULL,
  `BeneAccNo` varchar(20) DEFAULT NULL,
  `BeneIFSC` varchar(11) DEFAULT NULL,
  `BeneAccType` varchar(4) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaysoMO_IMPS_Req`
--

LOCK TABLES `PaysoMO_IMPS_Req` WRITE;
/*!40000 ALTER TABLE `PaysoMO_IMPS_Req` DISABLE KEYS */;
INSERT INTO `PaysoMO_IMPS_Req` VALUES ('-12600773812017-07-14','8845256556!ICIC0000985!10','-1260077381','8845256556','ICIC0000985','10','2017-07-14'),('-15667552652017-07-14','8845256156!ICIC0000985!10','-1566755265','8845256156','ICIC0000985','10','2017-07-14'),('-5476026932017-07-14','8845256776!ICIC0000985!10','-547602693','8845256776','ICIC0000985','10','2017-07-14'),('2235938922017-07-14','-1260077381','223593892','-','-','-','2017-07-14');
/*!40000 ALTER TABLE `PaysoMO_IMPS_Req` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaysoMO_IMPS_Response`
--

DROP TABLE IF EXISTS `PaysoMO_IMPS_Response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaysoMO_IMPS_Response` (
  `ResCode` varchar(150) NOT NULL,
  `Code` varchar(100) DEFAULT NULL,
  `ResParameters` varchar(400) DEFAULT NULL,
  `TraceNo` varchar(50) DEFAULT NULL,
  `ResponseCode` varchar(100) DEFAULT NULL,
  `ErrorReason` varchar(100) DEFAULT NULL,
  `BeneName` varchar(50) DEFAULT NULL,
  `AuthzStatus` varchar(1) DEFAULT NULL,
  `TranDate` date DEFAULT NULL,
  PRIMARY KEY (`ResCode`),
  KEY `Code` (`Code`),
  CONSTRAINT `PaysoMO_IMPS_Response_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `PaysoMO_IMPS_Req` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaysoMO_IMPS_Response`
--

LOCK TABLES `PaysoMO_IMPS_Response` WRITE;
/*!40000 ALTER TABLE `PaysoMO_IMPS_Response` DISABLE KEYS */;
INSERT INTO `PaysoMO_IMPS_Response` VALUES ('3667821672017-07-14','2235938922017-07-14','6320|14072017152247|PAYSO|223593892|719515620767|KMB0000019211|1|N|KPY42|Card-Issuer inoperative(Uriser and CSIS is down)|2338827860','223593892','KPY42','Card-Issuer inoperative(Uriser and CSIS is down)','-','N','2017-07-14'),('7687571802017-07-14','-12600773812017-07-14','6220|14072017152205|PAYSO|-1260077381|KPY42|Card-Issuer inoperative(Uriser and CSIS is down)|719515620767|KMB0000019211|14072017152500||3041770945','-1260077381','KPY42','Card-Issuer inoperative(Uriser and CSIS is down)','','-','2017-07-14');
/*!40000 ALTER TABLE `PaysoMO_IMPS_Response` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-14 15:40:14
