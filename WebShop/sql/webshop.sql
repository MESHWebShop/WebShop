-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: meshwebshop
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` varchar(45) CHARACTER SET utf8 NOT NULL,
  `categoryName` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryId_UNIQUE` (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `customerFirstName` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `customerLastName` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `customerEmail` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `customerPhoneNumber` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `customerPersNumber` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `customerPassword` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `customerId_UNIQUE` (`customerId`),
  UNIQUE KEY `customerPassword_UNIQUE` (`customerPassword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lager`
--

DROP TABLE IF EXISTS `lager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lager` (
  `productId` varchar(45) DEFAULT NULL,
  `saldo` varchar(45) DEFAULT NULL,
  KEY `productId_idx` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lager`
--

LOCK TABLES `lager` WRITE;
/*!40000 ALTER TABLE `lager` DISABLE KEYS */;
/*!40000 ALTER TABLE `lager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufactuer`
--

DROP TABLE IF EXISTS `manufactuer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufactuer` (
  `manufactureId` int(11) NOT NULL,
  `manufactureName` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`manufactureId`),
  UNIQUE KEY `manufactureId_UNIQUE` (`manufactureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufactuer`
--

LOCK TABLES `manufactuer` WRITE;
/*!40000 ALTER TABLE `manufactuer` DISABLE KEYS */;
/*!40000 ALTER TABLE `manufactuer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderId` int(11) NOT NULL,
  `orderCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `customerId_idx` (`orderCustomerId`),
  CONSTRAINT `customerId` FOREIGN KEY (`orderCustomerId`) REFERENCES `customer` (`customerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderedproduct`
--

DROP TABLE IF EXISTS `orderedproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderedproduct` (
  `orderedProductId` int(11) NOT NULL,
  `porductId` int(11) DEFAULT NULL,
  `productName` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `productPrice` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `orderedId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderedProductId`),
  UNIQUE KEY `orderedProductId_UNIQUE` (`orderedProductId`),
  KEY `orderId_idx` (`orderedId`),
  CONSTRAINT `orderID` FOREIGN KEY (`orderedId`) REFERENCES `order` (`orderId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderedproduct`
--

LOCK TABLES `orderedproduct` WRITE;
/*!40000 ALTER TABLE `orderedproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderedproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ProductId` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `ProductName` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `ProductDescription` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `ProductPrice` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `ProductManufactureId` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `TaxClassId` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `ManufactuerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductId`),
  KEY `ManufacuerID_idx` (`ManufactuerId`),
  CONSTRAINT `ManufactuerId` FOREIGN KEY (`ManufactuerId`) REFERENCES `manufactuer` (`manufactureId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productimage`
--

DROP TABLE IF EXISTS `productimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productimage` (
  `productImageId` int(11) NOT NULL,
  `productId` int(11) DEFAULT NULL,
  `image` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `htmlContent` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`productImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productimage`
--

LOCK TABLES `productimage` WRITE;
/*!40000 ALTER TABLE `productimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `productimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxclass`
--

DROP TABLE IF EXISTS `taxclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxclass` (
  `taxClassId` int(11) NOT NULL AUTO_INCREMENT,
  `taxClassTitle` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `taxClassDescription` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`taxClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxclass`
--

LOCK TABLES `taxclass` WRITE;
/*!40000 ALTER TABLE `taxclass` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxrate`
--

DROP TABLE IF EXISTS `taxrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxrate` (
  `taxRateCode` int(11) NOT NULL,
  `taxClassId` int(11) DEFAULT NULL,
  PRIMARY KEY (`taxRateCode`),
  UNIQUE KEY `taxTateId_UNIQUE` (`taxRateCode`),
  KEY `taxClassId_idx` (`taxClassId`),
  CONSTRAINT `taxClassId` FOREIGN KEY (`taxClassId`) REFERENCES `taxclass` (`taxClassId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxrate`
--

LOCK TABLES `taxrate` WRITE;
/*!40000 ALTER TABLE `taxrate` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxrate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-05 13:25:06
