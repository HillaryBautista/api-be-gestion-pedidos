CREATE DATABASE  IF NOT EXISTS `order_management_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `order_management_db`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: order_management_db
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `short_name` varchar(60) NOT NULL,
  `full_name` varchar(260) NOT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'ELEC','Electrónica','1'),(2,'FOOD','Alimentos y Bebidas','1'),(3,'TOOLS','Herramientas','1'),(4,'MED','Suministros Médicos','1');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `country_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ruc` varchar(11) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `business_name` varchar(260) NOT NULL,
  `address` varchar(400) NOT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9q9cx7hq8nsr8c3kxfi344dj0` (`ruc`),
  KEY `FKevdwlrxhbct07e6dighauj6er` (`country_id`),
  CONSTRAINT `FKevdwlrxhbct07e6dighauj6er` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,1,'10101010101','54111234','Andes Tech','123 Calle Andes, Buenos Aires, Argentina','1'),(2,2,'20202020202','55115678','Amazon Agro','456 Camino Amazonas, São Paulo, Brasil','1'),(3,3,'30303030303','56129012','Minería Atacama','789 Avenida Desierto, Santiago, Chile','1'),(4,4,'40404040404','59323456','Soluciones Andinas','321 Avenida Andes, Quito, Ecuador','1');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `common_name` varchar(250) NOT NULL,
  `notes` varchar(250) DEFAULT NULL,
  `official_name` varchar(250) NOT NULL,
  `iso_code` varchar(255) NOT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Argentina','América del Sur','República Argentina','ARG','1'),(2,'Brasil','América del Sur','República Federativa de Brasil','BRA','1'),(3,'Chile','América del Sur','República de Chile','CHL','1'),(4,'Colombia','América del Sur','República de Colombia','COL','1'),(5,'Ecuador','América del Sur','República del Ecuador','ECU','1'),(6,'Bolivia','América del Sur','Estado Plurinacional de Bolivia','BOL','1'),(7,'Paraguay','América del Sur','República del Paraguay','PRY','1'),(8,'Perú','América del Sur','República del Perú','PER','1'),(9,'Uruguay','América del Sur','República Oriental del Uruguay','URY','1'),(10,'Venezuela','América del Sur','República Bolivariana de Venezuela','VEN','1');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `igv` decimal(10,2) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `status` char(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK6peh8jk0s1pmhx6dbs0vtdkri` (`order_id`),
  KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
  CONSTRAINT `FK6peh8jk0s1pmhx6dbs0vtdkri` FOREIGN KEY (`order_id`) REFERENCES `order_header` (`id`),
  CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (36.00,199.99,1,199.99,235.99,1,1,1,'1');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_header`
--

DROP TABLE IF EXISTS `order_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_header` (
  `igv` decimal(5,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `client_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) NOT NULL,
  `status_id` bigint NOT NULL,
  `vendor_id` bigint NOT NULL,
  `gloss` varchar(240) NOT NULL,
  `status` char(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKrq6ji1qvrjtevuajm7ovnc3wh` (`client_id`),
  KEY `FKpo79mfb0nmuedbssiuki25pjb` (`status_id`),
  KEY `FK4ti8o0ggvm67pgyksyk4bg6ig` (`vendor_id`),
  CONSTRAINT `FK4ti8o0ggvm67pgyksyk4bg6ig` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`id`),
  CONSTRAINT `FKpo79mfb0nmuedbssiuki25pjb` FOREIGN KEY (`status_id`) REFERENCES `status_order` (`id`),
  CONSTRAINT `FKrq6ji1qvrjtevuajm7ovnc3wh` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_header`
--

LOCK TABLES `order_header` WRITE;
/*!40000 ALTER TABLE `order_header` DISABLE KEYS */;
INSERT INTO `order_header` VALUES (36.00,199.99,235.99,1,1,'2025-02-10 23:27:45.490075',1,1,'glosa 11/02/2025, 23:38:30','1');
/*!40000 ALTER TABLE `order_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_logs`
--

DROP TABLE IF EXISTS `order_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_logs` (
  `creation_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `status_name` varchar(50) NOT NULL,
  `comment` text,
  PRIMARY KEY (`id`),
  KEY `FKdcj81ny73h34g9e2v0j05j9gq` (`order_id`),
  CONSTRAINT `FKdcj81ny73h34g9e2v0j05j9gq` FOREIGN KEY (`order_id`) REFERENCES `order_header` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_logs`
--

LOCK TABLES `order_logs` WRITE;
/*!40000 ALTER TABLE `order_logs` DISABLE KEYS */;
INSERT INTO `order_logs` VALUES ('2025-02-10 23:27:45.490075',1,1,'Pendiente','Se creó el pedido');
/*!40000 ALTER TABLE `order_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `status` varchar(1) NOT NULL,
  `category_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `warranty` varchar(20) DEFAULT NULL,
  `name` varchar(360) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (199.99,50,'1',1,1,'100%','Smartphone X1'),(12.99,200,'1',2,2,'100%','Café Orgánico'),(89.99,30,'1',3,3,'100%','Taladro Eléctrico'),(29.99,100,'1',4,4,'100%','Kit de Primeros Auxilios');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_authority`
--

DROP TABLE IF EXISTS `seg_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_authority` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_authority`
--

LOCK TABLES `seg_authority` WRITE;
/*!40000 ALTER TABLE `seg_authority` DISABLE KEYS */;
INSERT INTO `seg_authority` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `seg_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_user`
--

DROP TABLE IF EXISTS `seg_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_user`
--

LOCK TABLES `seg_user` WRITE;
/*!40000 ALTER TABLE `seg_user` DISABLE KEYS */;
INSERT INTO `seg_user` VALUES (1,'administrator','$2a$10$d9m3ly9wsppztzaenfinguwr/rgp1bfdxmqguwd/wenz3000d','admin'),(2,'demo','$2a$10$d9m3ly9wsppztzaenfinguwr/rgp1bfdxmqguwd/wenz3000d','demo');
/*!40000 ALTER TABLE `seg_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seg_user_authority`
--

DROP TABLE IF EXISTS `seg_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seg_user_authority` (
  `authority_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`authority_id`,`user_id`),
  KEY `FKxdrvdr4rc0ia40ofnlhbfuk5` (`user_id`),
  CONSTRAINT `FKca74r9dsriu17sudgob7r7xf5` FOREIGN KEY (`authority_id`) REFERENCES `seg_authority` (`id`),
  CONSTRAINT `FKxdrvdr4rc0ia40ofnlhbfuk5` FOREIGN KEY (`user_id`) REFERENCES `seg_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seg_user_authority`
--

LOCK TABLES `seg_user_authority` WRITE;
/*!40000 ALTER TABLE `seg_user_authority` DISABLE KEYS */;
INSERT INTO `seg_user_authority` VALUES (1,1),(2,1),(2,2);
/*!40000 ALTER TABLE `seg_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_order`
--

DROP TABLE IF EXISTS `status_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `short_name` varchar(60) NOT NULL,
  `full_name` varchar(260) NOT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_order`
--

LOCK TABLES `status_order` WRITE;
/*!40000 ALTER TABLE `status_order` DISABLE KEYS */;
INSERT INTO `status_order` VALUES (1,'PEND','Pendiente','1'),(2,'PROC','Procesando','1'),(3,'SHIPPED','Enviada','1'),(4,'DELIV','Entregada','1'),(5,'CANCEL','Cancelada','1'),(6,'RETURN','Devuelta','1'),(7,'REFUND','Reembolsada','1'),(8,'HOLD','En espera','1'),(9,'PAID','Pagada','1');
/*!40000 ALTER TABLE `status_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `status` varchar(1) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document_number` varchar(15) NOT NULL,
  `email` varchar(150) NOT NULL,
  `maternal_surname` varchar(150) DEFAULT NULL,
  `paternal_surname` varchar(150) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `document_type` enum('CE','DNI','PASSPORT') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK4rtbru4hxy3sa11e8kpvrh0ok` (`document_number`),
  UNIQUE KEY `UKjyjmopegfp4iape655lu75sml` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES ('1',1,'08911558','john.smith@tech.com','A.','Smith','John','DNI'),('1',2,'18457923','jane.doe@healthcare.com','B.','Doe','Jane','DNI'),('1',3,'48159623','carlos.gomez@agro.com','A.','Gomez','Carlos','DNI'),('1',4,'15247859','hans.schmidt@tools.de','A.','Schmidt','Hans','DNI');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_orders`
--

DROP TABLE IF EXISTS `view_orders`;
/*!50001 DROP VIEW IF EXISTS `view_orders`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_orders` AS SELECT 
 1 AS `id`,
 1 AS `gloss`,
 1 AS `order_date`,
 1 AS `subtotal`,
 1 AS `igv`,
 1 AS `total`,
 1 AS `status_order`,
 1 AS `client`,
 1 AS `vendor`,
 1 AS `items`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'order_management_db'
--

--
-- Final view structure for view `view_orders`
--

/*!50001 DROP VIEW IF EXISTS `view_orders`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_orders` AS select `ohea`.`id` AS `id`,`ohea`.`gloss` AS `gloss`,`ohea`.`order_date` AS `order_date`,`ohea`.`subtotal` AS `subtotal`,`ohea`.`igv` AS `igv`,`ohea`.`total` AS `total`,concat(`sord`.`full_name`) AS `status_order`,concat(concat(`clie`.`ruc`,' - '),`clie`.`business_name`) AS `client`,concat(concat(`vend`.`first_name`,' '),concat(`vend`.`paternal_surname`,' '),`vend`.`maternal_surname`) AS `vendor`,count(`odet`.`id`) AS `items` from ((((`order_header` `ohea` join `client` `clie` on((`ohea`.`client_id` = `clie`.`id`))) join `vendor` `vend` on((`ohea`.`vendor_id` = `vend`.`id`))) join `order_detail` `odet` on((`ohea`.`id` = `odet`.`order_id`))) join `status_order` `sord` on((`ohea`.`status_id` = `sord`.`id`))) where ((`ohea`.`gloss` like '%%') and (concat(`sord`.`full_name`) like '%%') and (concat(concat(`clie`.`ruc`,' - '),`clie`.`business_name`) like '%%') and (concat(concat(`vend`.`first_name`,' '),concat(`vend`.`paternal_surname`,' '),`vend`.`maternal_surname`) like '%%') and (`ohea`.`status` = '1') and (`odet`.`status` = '1')) group by `ohea`.`id`,`ohea`.`gloss`,`ohea`.`order_date`,`ohea`.`subtotal`,`ohea`.`igv`,`ohea`.`total`,concat(`sord`.`full_name`),concat(concat(`vend`.`first_name`,' '),concat(`vend`.`paternal_surname`,' '),`vend`.`maternal_surname`),concat(concat(`clie`.`ruc`,' - '),`clie`.`business_name`) order by `ohea`.`id` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-13 18:58:54
