CREATE DATABASE  IF NOT EXISTS `desafio-01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `desafio-01`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: desafio-01
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `tb_autores`
--

DROP TABLE IF EXISTS `tb_autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_autores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `dataNascimento` varchar(45) NOT NULL,
  `nacionalidade` varchar(45) NOT NULL,
  `biografia` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_autores`
--

LOCK TABLES `tb_autores` WRITE;
/*!40000 ALTER TABLE `tb_autores` DISABLE KEYS */;
INSERT INTO `tb_autores` VALUES (1,'Lucas Rengel','2005-04-12','brasileiro','tem 19 anos e escreve historias');
/*!40000 ALTER TABLE `tb_autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_emprestimos`
--

DROP TABLE IF EXISTS `tb_emprestimos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_emprestimos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livro` int NOT NULL,
  `id_membro` int NOT NULL,
  `dataEmprestimo` varchar(45) NOT NULL,
  `dataDevolucao` varchar(45) DEFAULT NULL,
  `estado` varchar(45) NOT NULL,
  `multa` decimal(65,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_livro` (`id_livro`),
  KEY `id_membro` (`id_membro`),
  CONSTRAINT `tb_emprestimos_ibfk_1` FOREIGN KEY (`id_livro`) REFERENCES `tb_livros` (`id`),
  CONSTRAINT `tb_emprestimos_ibfk_2` FOREIGN KEY (`id_membro`) REFERENCES `tb_membros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_emprestimos`
--

LOCK TABLES `tb_emprestimos` WRITE;
/*!40000 ALTER TABLE `tb_emprestimos` DISABLE KEYS */;
INSERT INTO `tb_emprestimos` VALUES (1,1,1,'2024-10-01',NULL,'ATRASADO',25),(2,1,1,'2024-10-01',NULL,'ATIVO',0);
/*!40000 ALTER TABLE `tb_emprestimos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_livros`
--

DROP TABLE IF EXISTS `tb_livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_livros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `id_autor` int NOT NULL,
  `dataPublicacao` varchar(45) NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `quantidade` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `tb_livros_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `tb_autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_livros`
--

LOCK TABLES `tb_livros` WRITE;
/*!40000 ALTER TABLE `tb_livros` DISABLE KEYS */;
INSERT INTO `tb_livros` VALUES (1,'como treinar seu dragao',1,'2024-08-08','999999999','historia',1000);
/*!40000 ALTER TABLE `tb_livros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_membros`
--

DROP TABLE IF EXISTS `tb_membros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_membros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `dataAssociacao` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_membros`
--

LOCK TABLES `tb_membros` WRITE;
/*!40000 ALTER TABLE `tb_membros` DISABLE KEYS */;
INSERT INTO `tb_membros` VALUES (1,'Sarah','2024-07-07','sarah@gmail.com','4899113','rua joao');
/*!40000 ALTER TABLE `tb_membros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-08 11:31:30
