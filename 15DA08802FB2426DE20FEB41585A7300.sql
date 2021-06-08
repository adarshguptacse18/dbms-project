-- MySQL dump 10.13  Distrib 5.6.50, for Linux (x86_64)
--
-- Host: localhost    Database: heroku_803240ac282ea28
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `street_no` varchar(255) DEFAULT NULL,
  `locality_and_city` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'20','1','1','1','1'),(2,3,'1','1','1','1','1'),(3,3,'2','2','2','2','2'),(5,14,'145','7','Arjangarh','110047','New Delhi'),(6,14,'14','7','Sheetla Colony, Gurugram','122001','Haryana'),(9,14,'13','11','Test123','111001','Delhi'),(11,16,'11','11','11','11','11'),(12,15,'House No 14','Gali No 7','Sheetla Colony, Gurugram','122001','Haryana'),(13,15,'123','123','123','123','123');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `customer` (`cart_id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,1,4),(6,14,1),(7,31,7);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Handmade Bowl'),(2,'Sea Grass Basked'),(3,'Willow Basket'),(11,'Handmade Lamps');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `is_resolved` tinyint(1) DEFAULT '0',
  `message` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `complaints_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1,17,'adarshgupta.cse18@iitbhu.ac.in','2020-11-09',1,'hello world\r\n'),(2,15,'adarshgupta564444@gmail.com','2020-11-15',0,'Hey'),(11,15,'addy@0335g.com','2020-11-17',0,'Hey, Can you please tell me how to order in bulk?\r\n');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `GSTIN_NUMBER` int(11) DEFAULT NULL,
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `trust_value` double DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `cart_id` (`cart_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (14,0,6,1),(15,1928731,7,1),(16,12312,8,1),(30,0,19,1),(31,0,20,1),(33,0,22,1),(34,0,23,1),(35,0,24,1),(37,0,25,1),(38,0,26,1),(71,0,41,1),(81,0,51,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `product_id` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`,`image_path`),
  CONSTRAINT `images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (14,'/static/uploads/259925631rattan-basekt-500x500.jpg'),(15,'/static/uploads/151product-500x500.jpeg'),(19,'/static/uploads/882404product-jpeg-500x500.jpg'),(20,'/static/uploads/247fruit-basket-500x500.jpeg'),(21,'/static/uploads/206247fruit-basket-500x500.jpeg'),(31,'/static/uploads/632decor-cane-lamps-500x500.jpeg');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT '0',
  PRIMARY KEY (`order_id`,`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) DEFAULT 'Processing',
  `order_date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `is_gift` tinyint(1) DEFAULT '0',
  `phone_number` varchar(15) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `street_no` varchar(255) DEFAULT NULL,
  `locality_and_city` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_numbers`
--

DROP TABLE IF EXISTS `phone_numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_numbers` (
  `user_id` int(11) NOT NULL,
  `number` varchar(15) NOT NULL,
  PRIMARY KEY (`user_id`,`number`),
  CONSTRAINT `phone_numbers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_numbers`
--

LOCK TABLES `phone_numbers` WRITE;
/*!40000 ALTER TABLE `phone_numbers` DISABLE KEYS */;
INSERT INTO `phone_numbers` VALUES (14,'8384010120'),(14,'8881112060'),(15,'8812838182'),(17,'9919219298');
/*!40000 ALTER TABLE `phone_numbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(16000) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT '0',
  `rating` double DEFAULT NULL,
  `purchased_cnt` int(11) DEFAULT '0',
  `hide` tinyint(1) DEFAULT '0',
  `supplier_id` int(11) DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `vendor` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (14,'Sea Grass Basket','These basket are made up of sea grass and leaf . These basket are made by poor village ladies . Completely handmade and natural. Its size and shape can be customized as per need',1009,1,9,3.25,5,0,18),(15,'Cane Lamps','These lamps are made of completely natural cane . They can customise as compared to clients need with size and color',100,1,10,5,1,0,17),(19,'Cane Lamps','These lamps are made of completely natural cane . They can customise as compared to clients need with size and color',100,1,10,5,1,1,41),(20,'Fruit Basket','ECO-FRIENDLY, HAND-WOVEN AND DURABLE: This natural colored multi-utility hand-woven, Eco-friendly, sustainable, wicker The Classic Wicker Basket is as functional as it is beautiful. This exquisite handcrafted, Eco-friendly, sustainable, wicker-willow basket doubles up as a chic storage basket when at home. These Multi-Purpose baskets are made from high quality Wicker, Willow and other finish materials.',350,3,10,5,1,0,18),(23,'Test123','Test123',100,2,10,3.5,4,1,18),(31,'Decor Cane Lamps','Decor Cane Lamps',2500,11,1,0,2,0,51);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (14,3,'Bad Product'),(14,5,'Very Nice Product'),(14,14,'aslkdjflksdj'),(14,14,'Very Nice Product'),(14,14,'Going to buy one more product'),(14,14,'This was amazing'),(14,14,'JSKjdljflkajsd'),(15,15,'Good Product'),(20,15,'Good product');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplies`
--

DROP TABLE IF EXISTS `supplies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplies` (
  `supplier_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`supplier_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `supplies_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `vendor` (`supplier_id`),
  CONSTRAINT `supplies_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplies`
--

LOCK TABLES `supplies` WRITE;
/*!40000 ALTER TABLE `supplies` DISABLE KEYS */;
INSERT INTO `supplies` VALUES (51,14),(17,15),(51,15),(41,20),(41,23),(51,31);
/*!40000 ALTER TABLE `supplies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `is_enabled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `username_2` (`username`),
  KEY `email_2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,'admin','ROLE_ADMIN','$2a$10$1uNFmxfZJ2olu1z.P/vWsuhlJLqree3Wl5lxEm1yFzhRwEhxtyaQK','adarshgupta.cse18@iitbhu.ac.in','Addy','Daddy',1),(15,'addy','ROLE_USER','$2a$10$RCfjmt0wcTWK4otoXhv5tO5zQlrEvo6OtM9.bAHU4b.1N6TA99NiO','adarshg@gmail.com','AdarshG','Gupta',1),(16,'Cyber_Wizard','ROLE_USER','$2a$10$8X0IwqZz5abLc0pycEO5xOR823qZvXBsBSZra4yRU7lq5qwCAV0PG','adarshgupta5644@gmail.com','Adarsh','Gupta',1),(17,'addyUrDaddy','ROLE_VENDOR','$2a$10$ej.NXYVYUC447.377OQGUejROFUJWu6TwqMp2rkhZRMACkFM4xpT.','hagocij102@pidouno.com','Addy','Daddy',1),(18,'1234','ROLE_VENDOR','$2a$10$H4kgDhFP3hokh/SfJQMfzOEVVXni9dDc8VBT1qPcjQ6Sw00FDsRfq','sksksk@pidouno.com','Adarsh','Gupta',1),(34,'not_again','ROLE_USER','$2a$10$OIu3oMfssEBKcR.ljGd5vOomwso0k.ycFlZmJcWItTqcf8jPStYu6','adarshgupta56444@gmail.com','salkdjf','alskjdlf',0),(37,'adarshgupta.cse18@iitbhu.ac.in','ROLE_USER','$2a$10$sLSH2YKNbn7K.20qbcUxoudvtQ.ZrfQddzewM8MdfAoIG3smuy4Ge','adarshgupta.cse18@itbhu.ac.in','adarshgupta.cse18@iitbhu.ac.in','adarshgupta.cse18@iitbhu.ac.in',0),(40,'kiroh111233@wgraj.com','ROLE_VENDOR','$2a$10$YmAEcxOQQtDmSxKi5FGlqeB2dBVWxfq9sx.74MtJWy53/rIdvJVQK','kiroh111233@wgraj.com','kiroh111233@wgraj.com','kiroh111233@wgraj.com',0),(41,'joxok71813@pidouno.com','ROLE_VENDOR','$2a$10$.AjZYDtHElhP//ehnICkG.qwxjkywbRoysDdj9oDOQPZHvudIYocC','joxok71813@pidouno.com','joxok71813@pidouno.com','joxok71813@pidouno.com',1),(51,'AddyTheVendor','ROLE_VENDOR','$2a$10$3qCEG30I1VACdQa2lJ2AUeFJDMNBElljlVqCoO5HPBR6vM.yRqygy','addy@0335g.com','Addy','TheVendor',1),(71,'sahil','ROLE_USER','$2a$10$8nNZbjo/p7ne6gOtMlJ.6u3s27OBGNTJpKTWcJO2GQ7NS2ODYyy7W','webappsahil@gmail.com','Kk','Ss',1),(81,'Sahil123','ROLE_USER','$2a$10$c2Kdyz4h3rLzQC3stwUNVeo5mhoSdHapfvCWVtRMK96MLIL36WBqa','Sahilchaudhari238@gmail.com','sahil','chaudhari ',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_token` (
  `user_id` int(11) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `token` (`token`),
  CONSTRAINT `user_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
INSERT INTO `user_token` VALUES (71,'47ec9af4-253c-4308-aaa2-02f562bba7f0'),(16,'93474da7-a897-45d5-871d-bd2071610de4'),(14,'b7504a61-2702-4649-812b-9618158f1443');
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `supplier_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `pancard_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  CONSTRAINT `vendor_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (17,'Addy The Daddy','1234'),(18,'a','0'),(40,'kiroh111233@wgraj.com','0'),(41,'joxok71813@pidouno.com','0'),(51,'addy@0335g.com','91283');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'heroku_803240ac282ea28'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-08  6:21:58
