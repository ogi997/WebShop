-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ipdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ipdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ipdb` ;
USE `ipdb` ;

-- -----------------------------------------------------
-- Table `ipdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `pin_code` INT NULL,
  `deleted` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `fk_category` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `condition_product` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `fk_user` INT NOT NULL,
  `contact` VARCHAR(255) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_1_idx` (`fk_category` ASC) VISIBLE,
  INDEX `fk_user_idx` (`fk_user` ASC) VISIBLE,
  CONSTRAINT `fk_category`
    FOREIGN KEY (`fk_category`)
    REFERENCES `ipdb`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user`
    FOREIGN KEY (`fk_user`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`attribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`attribute` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `fk_category_id` INT NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_category_idx` (`fk_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_category_attribute`
    FOREIGN KEY (`fk_category_id`)
    REFERENCES `ipdb`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`attribute_value`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`attribute_value` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(255) NOT NULL,
  `fk_attribute_id` INT NOT NULL,
  `fk_proizvod` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attribute_idx` (`fk_attribute_id` ASC) VISIBLE,
  INDEX `fk_proizvod_idx` (`fk_proizvod` ASC) VISIBLE,
  CONSTRAINT `fk_attribute_category`
    FOREIGN KEY (`fk_attribute_id`)
    REFERENCES `ipdb`.`attribute` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proizvod`
    FOREIGN KEY (`fk_proizvod`)
    REFERENCES `ipdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`supply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`supply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_prod` INT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_prod_idx` (`fk_prod` ASC) VISIBLE,
  CONSTRAINT `fk_prod`
    FOREIGN KEY (`fk_prod`)
    REFERENCES `ipdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`payment_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`payment_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`purchase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_use` INT NOT NULL,
  `fk_payment` INT NOT NULL,
  `card_number` VARCHAR(255) NULL,
  `fk_product_purchase` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_use_idx` (`fk_use` ASC) VISIBLE,
  INDEX `fk_payment_idx` (`fk_payment` ASC) VISIBLE,
  INDEX `fk_purchase_1_idx` (`fk_product_purchase` ASC) VISIBLE,
  CONSTRAINT `fk_use`
    FOREIGN KEY (`fk_use`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment`
    FOREIGN KEY (`fk_payment`)
    REFERENCES `ipdb`.`payment_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_1`
    FOREIGN KEY (`fk_product_purchase`)
    REFERENCES `ipdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_u` INT NOT NULL,
  `text` VARCHAR(255) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_u_idx` (`fk_u` ASC) VISIBLE,
  CONSTRAINT `fk_u`
    FOREIGN KEY (`fk_u`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`support_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`support_admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`product_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_product_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `cover` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_id_idx` (`fk_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`fk_product_id`)
    REFERENCES `ipdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`avatar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`avatar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_user_avatar` INT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_avatar_idx` (`fk_user_avatar` ASC) VISIBLE,
  CONSTRAINT `fk_user_avatar`
    FOREIGN KEY (`fk_user_avatar`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_product` INT NOT NULL,
  `fk_user_ko` INT NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  `fk_user_kome` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_idx` (`fk_product` ASC) VISIBLE,
  INDEX `fk_user_idx` (`fk_user_ko` ASC) VISIBLE,
  INDEX `fk_user_kome_idx` (`fk_user_kome` ASC) VISIBLE,
  CONSTRAINT `fk_product_question`
    FOREIGN KEY (`fk_product`)
    REFERENCES `ipdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_ko`
    FOREIGN KEY (`fk_user_ko`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_kome`
    FOREIGN KEY (`fk_user_kome`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ipdb`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ipdb`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_question` INT NOT NULL,
  `fk_user_ans_ko` INT NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  `fk_user_ans_kome` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_idx` (`fk_question` ASC) VISIBLE,
  INDEX `fk_user_idx` (`fk_user_ans_ko` ASC) VISIBLE,
  INDEX `fk_user_ans_kome_idx` (`fk_user_ans_kome` ASC) VISIBLE,
  CONSTRAINT `fk_question_answer`
    FOREIGN KEY (`fk_question`)
    REFERENCES `ipdb`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_ans_ko`
    FOREIGN KEY (`fk_user_ans_ko`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_ans_kome`
    FOREIGN KEY (`fk_user_ans_kome`)
    REFERENCES `ipdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
