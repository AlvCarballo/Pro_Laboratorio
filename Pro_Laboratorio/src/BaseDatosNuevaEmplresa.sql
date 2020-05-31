CREATE DATABASE  IF NOT EXISTS `empresanueva` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `empresanueva`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: empresanueva
-- ------------------------------------------------------
-- Server version        8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCliente` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Fulanito','2020-01-10'),(2,'Menganito','2019-12-24'),(3,'Ahora sí','2020-04-29'),(5,'Nuevo2','2020-04-29'),(6,'Correcto2','2020-12-20'),(8,'Nuevo Cliente','2020-05-04'),(9,'Luisa Romero','2020-02-02'),(10,'zfsgsfg','2020-01-01');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `idFactura` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFactura` date DEFAULT NULL,
  `totalFactura` decimal(10,2) DEFAULT NULL,
  `idClienteFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `idClienteFK` (`idClienteFK`),
  CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`idClienteFK`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1,'2020-05-04',0.00,1),(2,'2020-05-03',0.00,3),(3,'2020-05-04',0.00,3),(4,'2020-05-04',0.00,3),(5,'2020-05-04',0.00,3),(6,'2020-05-04',0.00,3),(7,'2020-05-04',0.00,3),(8,'2020-05-04',0.00,2),(9,'2020-05-04',0.00,5),(10,'2020-05-04',0.00,6),(11,'2020-05-04',0.00,8),(12,'2020-05-04',0.00,3),(13,'2020-05-04',0.00,2),(14,'2020-05-04',0.00,5),(15,'2020-05-04',0.00,3),(16,'2020-05-04',0.00,2),(17,'2020-05-04',8.50,6),(18,'2020-05-04',1.75,3),(19,'2020-05-04',0.00,3),(20,'2020-05-04',45.00,3),(21,'2020-05-04',0.00,3),(22,'2020-05-04',0.00,2),(23,'2020-05-04',-0.50,3);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineasfactura`
--

DROP TABLE IF EXISTS `lineasfactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineasfactura` (
  `idFacturaFK` int(11) NOT NULL,
  `idServicioFK` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFacturaFK`,`idServicioFK`),
  KEY `idServicioFK` (`idServicioFK`),
  CONSTRAINT `lineasfactura_ibfk_1` FOREIGN KEY (`idFacturaFK`) REFERENCES `facturas` (`idFactura`),
  CONSTRAINT `lineasfactura_ibfk_2` FOREIGN KEY (`idServicioFK`) REFERENCES `servicios` (`idServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineasfactura`
--

LOCK TABLES `lineasfactura` WRITE;
/*!40000 ALTER TABLE `lineasfactura` DISABLE KEYS */;
INSERT INTO `lineasfactura` VALUES (16,2,1),(16,3,5),(17,1,100),(17,2,2),(17,3,3),(18,1,35),(20,2,45),(23,3,-1);
/*!40000 ALTER TABLE `lineasfactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `descripcionServicio` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `precioServicio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,'Fotocopia',0.05),(2,'Encuadernación',1.00),(3,'Fax',0.50);
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-06  9:40:59
