SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `javaweb` ;
CREATE SCHEMA IF NOT EXISTS `javaweb` DEFAULT CHARACTER SET utf8 ;
USE `javaweb` ;

-- -----------------------------------------------------
-- Table `javaweb`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaweb`.`categoria` ;

CREATE TABLE IF NOT EXISTS `javaweb`.`categoria` (
  `id_Categoria` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NULL DEFAULT NULL,
  `descripcion` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`id_Categoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaweb`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaweb`.`producto` ;

CREATE TABLE IF NOT EXISTS `javaweb`.`producto` (
  `idProducto` BIGINT(20) NOT NULL,
  `Categoria_id_Categoria` BIGINT(20) NOT NULL,
  `nombre` VARCHAR(128) NULL DEFAULT NULL,
  `descripcion` VARCHAR(256) NULL DEFAULT NULL,
  `precio` DECIMAL(10,0) NULL DEFAULT NULL,
  `stock` INT(11) NULL DEFAULT NULL,
  `importancia` INT(11) NULL DEFAULT NULL,
  `imagen` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Categoria_idx` (`Categoria_id_Categoria` ASC),
  CONSTRAINT `fk_Producto_Categoria`
    FOREIGN KEY (`Categoria_id_Categoria`)
    REFERENCES `javaweb`.`categoria` (`id_Categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaweb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaweb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `javaweb`.`usuario` (
  `id_usuario` VARCHAR(20) NOT NULL,
  `clave` VARCHAR(20) NULL,
  `nombres` VARCHAR(64) NULL,
  `paterno` VARCHAR(32) NULL,
  `materno` VARCHAR(32) NULL,
  `correo` VARCHAR(64) NULL,
  `direccion` VARCHAR(128) NULL,
  `telefono` VARCHAR(32) NULL,
  `estado` CHAR(1) NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javaweb`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaweb`.`pedido` ;

CREATE TABLE IF NOT EXISTS `javaweb`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` VARCHAR(20) NOT NULL,
  `fecha` DATETIME NULL,
  `estado` CHAR(1) NULL,
  `total` DOUBLE NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedido_usuario1_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_pedido_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `javaweb`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javaweb`.`detalle_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaweb`.`detalle_pedido` ;

CREATE TABLE IF NOT EXISTS `javaweb`.`detalle_pedido` (
  `id_pedido` INT NOT NULL,
  `id_detalle_pedido` INT NOT NULL DEFAULT 1,
  `idProducto` BIGINT(20) NOT NULL,
  `precio` DOUBLE NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`id_detalle_pedido`),
  INDEX `fk_detalle_pedido_pedido1_idx` (`id_pedido` ASC),
  INDEX `fk_detalle_pedido_producto1_idx` (`idProducto` ASC),
  CONSTRAINT `fk_detalle_pedido_pedido1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `javaweb`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_pedido_producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `javaweb`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
