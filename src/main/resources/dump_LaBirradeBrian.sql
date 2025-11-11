-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: LaBirradeBrian
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cervezas`
--

DROP TABLE IF EXISTS `Cervezas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cervezas` (
  `idCerveza` int NOT NULL AUTO_INCREMENT,
  `nombreCerveza` text NOT NULL,
  `graduacionCerveza` double NOT NULL,
  `tipoCerveza` text NOT NULL,
  `colorCerveza` text NOT NULL,
  `origenCerveza` text NOT NULL,
  `precioCerveza` double NOT NULL,
  `cantidadCerveza` int DEFAULT NULL,
  PRIMARY KEY (`idCerveza`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cervezas`
--

LOCK TABLES `Cervezas` WRITE;
/*!40000 ALTER TABLE `Cervezas` DISABLE KEYS */;
INSERT INTO `Cervezas` VALUES (1,'Guinness',5.6,'Stout','Rubi oscuro','Dublin, Irlanda',8.7,8),(2,'Voll Damm',7.2,'Marzenbier','Roble','Barcelona, España',7.3,9),(3,'Franziskaner',5,'Weissbier','Anaranjado','Alemania',6.5,7),(4,'Heineken',5,'Pale lager','Verde','Paises Bajos',3.2,1),(5,'Budweiser',4.8,'Lager','Dorado palido','America',2.1,2),(6,'Gulden Draak 9000',10.5,'Strong Belgian Ale','Ambar','Bélgica',9.1,5);
/*!40000 ALTER TABLE `Cervezas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proveedores`
--

DROP TABLE IF EXISTS `Proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Proveedores` (
  `idProveedor` int NOT NULL AUTO_INCREMENT,
  `nombreProveedor` text NOT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proveedores`
--

LOCK TABLES `Proveedores` WRITE;
/*!40000 ALTER TABLE `Proveedores` DISABLE KEYS */;
INSERT INTO `Proveedores` VALUES (1,'Cruzcampo'),(2,'Cervezas del Norte'),(3,'Lúpulo Dorado'),(4,'Malta Suprema'),(5,'La Espuma Artesanal'),(6,'Barril & Trigo');
/*!40000 ALTER TABLE `Proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Registro`
--

DROP TABLE IF EXISTS `Registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Registro` (
  `idRegistro` int NOT NULL AUTO_INCREMENT,
  `idCerveza` int DEFAULT NULL,
  `cantidadCerveza` int DEFAULT NULL,
  `idTapa` int DEFAULT NULL,
  `cantidadTapa` int DEFAULT NULL,
  `sumaResta` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `idProveedor` int DEFAULT NULL,
  `fechaDia` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`idRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Registro`
--

LOCK TABLES `Registro` WRITE;
/*!40000 ALTER TABLE `Registro` DISABLE KEYS */;
INSERT INTO `Registro` VALUES (1,1,7,NULL,NULL,'Suma',3,'2025-11-06 18:54:17'),(2,2,6,NULL,NULL,'Suma',1,'2025-11-06 18:56:59'),(3,1,4,NULL,NULL,'Resta',NULL,'2025-11-06 19:09:49');
/*!40000 ALTER TABLE `Registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tapas`
--

DROP TABLE IF EXISTS `Tapas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tapas` (
  `idTapa` int NOT NULL AUTO_INCREMENT,
  `nombreTapa` text NOT NULL,
  `precioTapa` double NOT NULL,
  `cantidadTapa` int DEFAULT NULL,
  PRIMARY KEY (`idTapa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tapas`
--

LOCK TABLES `Tapas` WRITE;
/*!40000 ALTER TABLE `Tapas` DISABLE KEYS */;
INSERT INTO `Tapas` VALUES (1,'Tortilla Española',3.5,6),(2,'Croquetas de Jamón',4,9),(3,'Patatas Bravas',3.2,3),(4,'Calamares a la Romana',5,7),(5,'Pimientos de Padrón',3.8,3);
/*!40000 ALTER TABLE `Tapas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'LaBirradeBrian'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_total_valor_cerveza` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` FUNCTION `fn_total_valor_cerveza`(p_idCerveza INT) RETURNS double
    DETERMINISTIC
RETURN COALESCE(
  (SELECT cantidadCerveza * precioCerveza FROM Cervezas WHERE idCerveza = p_idCerveza),
  0
) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_total_valor_tapa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` FUNCTION `fn_total_valor_tapa`(p_id_tapa INT) RETURNS double
    DETERMINISTIC
RETURN COALESCE(
  (SELECT cantidadTapa * precioTapa FROM Tapas WHERE idTapa = p_id_tapa),
  0
) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrar_stock_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` PROCEDURE `sp_mostrar_stock_total`()
BEGIN
    -- Mostrar Cervezas
    SELECT 
        'Cerveza' AS Tipo,
        nombreCerveza AS Nombre,
        cantidadCerveza AS Cantidad,
        precioCerveza AS PrecioUnidad,
        (cantidadCerveza * precioCerveza) AS PrecioTotal
    FROM Cervezas

    UNION ALL

    -- Mostrar Tapas
    SELECT 
        'Tapa' AS Tipo,
        nombreTapa AS Nombre,
        cantidadTapa AS Cantidad,
        precioTapa AS PrecioUnidad,
        (cantidadTapa * precioTapa) AS PrecioTotal
    FROM Tapas

    ORDER BY Tipo, Nombre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_restar_cerveza` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` PROCEDURE `sp_restar_cerveza`(
    IN p_idCerveza INT,
    IN p_cantidad INT,
    IN p_sumaResta VARCHAR(10)
)
BEGIN
    DECLARE v_nueva_cantidad INT;

    -- 1️⃣ Resta cantidad, pero evita negativos
    UPDATE Cervezas
    SET cantidadCerveza = GREATEST(cantidadCerveza - p_cantidad, 0)
    WHERE idCerveza = p_idCerveza;

    -- 2️⃣ Obtiene la nueva cantidad
    SELECT cantidadCerveza INTO v_nueva_cantidad
    FROM Cervezas WHERE idCerveza = p_idCerveza;

    -- 3️⃣ Inserta registro
    INSERT INTO Registro (idCerveza, cantidadCerveza, sumaResta, fechaDia)
    VALUES (p_idCerveza, p_cantidad, p_sumaResta, NOW());

    -- 4️⃣ Devuelve mensaje
    SELECT CONCAT('Cerveza actualizada. Nueva cantidad: ', v_nueva_cantidad) AS mensaje;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_restar_tapa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` PROCEDURE `sp_restar_tapa`(
    IN p_idTapa INT,
    IN p_cantidad INT,
    IN p_sumaResta VARCHAR(10)
)
BEGIN
    DECLARE v_cantidad_actual INT;
    DECLARE v_nueva_cantidad INT;

    -- Obtener cantidad actual
    SELECT cantidadTapa INTO v_cantidad_actual
    FROM Tapas
    WHERE idTapa = p_idTapa;

    -- Validar stock suficiente
    IF v_cantidad_actual >= p_cantidad THEN
        UPDATE Tapas
        SET cantidadTapa = cantidadTapa - p_cantidad
        WHERE idTapa = p_idTapa;

        SELECT cantidadTapa INTO v_nueva_cantidad
        FROM Tapas WHERE idTapa = p_idTapa;

        INSERT INTO Registro (idTapa, cantidadTapa, sumaResta, fechaDia)
        VALUES (p_idTapa, p_cantidad, p_sumaResta, NOW());

        SELECT CONCAT('Stock actualizado. Nueva cantidad: ', v_nueva_cantidad) AS mensaje;
    ELSE
        SELECT CONCAT('Error: stock insuficiente. Stock actual: ', v_cantidad_actual) AS mensaje;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_sumar_cerveza` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` PROCEDURE `sp_sumar_cerveza`(
    IN p_idCerveza INT,
    IN p_cantidad INT,
    IN p_sumaResta VARCHAR(10),
    IN p_idProveedor INT
)
BEGIN
    DECLARE v_nueva_cantidad INT;

    -- 1️⃣ Actualiza la cantidad de cervezas en el inventario
    UPDATE Cervezas
    SET cantidadCerveza = cantidadCerveza + p_cantidad
    WHERE idCerveza = p_idCerveza;

    -- 2️⃣ Obtiene la nueva cantidad
    SELECT cantidadCerveza INTO v_nueva_cantidad
    FROM Cervezas WHERE idCerveza = p_idCerveza;

    -- 3️⃣ Inserta el registro del movimiento
    INSERT INTO Registro (idCerveza, cantidadCerveza, sumaResta, idProveedor, fechaDia)
    VALUES (p_idCerveza, p_cantidad, p_sumaResta, p_idProveedor, NOW());

    -- 4️⃣ Devuelve un mensaje
    SELECT CONCAT('Cerveza actualizada. Nueva cantidad: ', v_nueva_cantidad) AS mensaje;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_sumar_tapa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`AlvaroGM`@`%` PROCEDURE `sp_sumar_tapa`(
    IN p_idTapa INT,
    IN p_cantidad INT,
    IN p_sumaResta VARCHAR(10),
    IN p_idProveedor INT
)
BEGIN
    DECLARE v_nueva_cantidad INT;

    -- Aumentar el stock de la tapa
    UPDATE Tapas
    SET cantidadTapa = cantidadTapa + p_cantidad
    WHERE idTapa = p_idTapa;

    -- Obtener la nueva cantidad
    SELECT cantidadTapa INTO v_nueva_cantidad
    FROM Tapas WHERE idTapa = p_idTapa;

    -- Registrar el movimiento
    INSERT INTO Registro (idTapa, cantidadTapa, sumaResta, idProveedor, fechaDia)
    VALUES (p_idTapa, p_cantidad, p_sumaResta, p_idProveedor, NOW());

    SELECT CONCAT('Tapa actualizada. Nueva cantidad: ', v_nueva_cantidad) AS mensaje;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-11 17:40:52
