-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema otp_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema otp_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `otp_db` DEFAULT CHARACTER SET utf8 ;
USE `otp_db` ;

-- -----------------------------------------------------
-- Table `otp_db`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `otp_db`.`tipo` (
  `idTipo` INT(11) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `otp_db`.`ativo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `otp_db`.`ativo` (
  `idAtivo` INT(11) NOT NULL,
  `idTipo` INT(11) NOT NULL,
  `ultimoPreco` FLOAT NOT NULL,
  `change` VARCHAR(45) NULL DEFAULT NULL,
  `changePercentagem` VARCHAR(45) NULL DEFAULT NULL,
  `volume` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idAtivo`),
  INDEX `idTipo_idx` (`idTipo` ASC),
  CONSTRAINT `idTipo`
    FOREIGN KEY (`idTipo`)
    REFERENCES `otp_db`.`tipo` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `otp_db`.`trader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `otp_db`.`trader` (
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `plafond` FLOAT NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `otp_db`.`contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `otp_db`.`contrato` (
  `idContrato` INT(11) NOT NULL AUTO_INCREMENT,
  `idAtivo` INT(11) NOT NULL,
  `idVendedor` VARCHAR(45) NOT NULL,
  `idComprador` VARCHAR(45) NULL DEFAULT NULL,
  `precoVenda` INT(11) NOT NULL,
  `precoCompra` INT(11) NULL DEFAULT NULL,
  `dataFechoContrato` DATE NOT NULL,
  `takeProfit` INT(11) NOT NULL,
  `stopLoss` INT(11) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `numeroContratos` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idContrato`),
  INDEX `idAtivo_idx` (`idAtivo` ASC),
  INDEX `idVendedor_idx` (`idVendedor` ASC),
  INDEX `idComprador_idx` (`idComprador` ASC),
  CONSTRAINT `idAtivo`
    FOREIGN KEY (`idAtivo`)
    REFERENCES `otp_db`.`ativo` (`idAtivo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idComprador`
    FOREIGN KEY (`idComprador`)
    REFERENCES `otp_db`.`trader` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idVendedor`
    FOREIGN KEY (`idVendedor`)
    REFERENCES `otp_db`.`trader` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
