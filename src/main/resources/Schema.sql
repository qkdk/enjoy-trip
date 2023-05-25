-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `enjoytrip`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_pw` VARCHAR(300) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_email` VARCHAR(10) NOT NULL,
  `user_domain` VARCHAR(20) NOT NULL,
  `user_role` VARCHAR(5) NOT NULL DEFAULT 'user',
  `user_img_src` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`follow` (
  `follower_id` VARCHAR(20) NOT NULL,
  `followee_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`followee_id`, `follower_id`),
  INDEX `follower_id_fk_idx` (`follower_id` ASC) VISIBLE,
  CONSTRAINT `followee_id_fk`
    FOREIGN KEY (`followee_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `follower_id_fk`
    FOREIGN KEY (`follower_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice` (
  `user_id` VARCHAR(20) NOT NULL,
  `notice_no` INT NOT NULL AUTO_INCREMENT,
  `notice_title` VARCHAR(45) NOT NULL,
  `notice_content` TEXT NULL DEFAULT NULL,
  `notice_createtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `notice_hit` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`notice_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`place` (
  `user_id` VARCHAR(20) NOT NULL,
  `place_no` INT NOT NULL AUTO_INCREMENT,
  `place_title` VARCHAR(45) NOT NULL,
  `place_content` TEXT NULL DEFAULT NULL,
  `place_createtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hit` INT NOT NULL DEFAULT '0',
  `recommend_count` INT NOT NULL DEFAULT '0',
  `city` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`place_no`),
  INDEX `place_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `place_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`place_img`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`place_img` (
  `place_no` INT NOT NULL,
  `place_img_src` VARCHAR(300) NULL DEFAULT NULL,
  INDEX `place_no_idx` (`place_no` ASC) VISIBLE,
  CONSTRAINT `place_no`
    FOREIGN KEY (`place_no`)
    REFERENCES `enjoytrip`.`place` (`place_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`place_recommend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`place_recommend` (
  `user_id` VARCHAR(20) NOT NULL,
  `place_no` INT NOT NULL,
  PRIMARY KEY (`user_id`, `place_no`),
  INDEX `fk_place_plane_no_idx` (`place_no` ASC) VISIBLE,
  CONSTRAINT `fk_place_plane_no`
    FOREIGN KEY (`place_no`)
    REFERENCES `enjoytrip`.`place` (`place_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_place_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`place_reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`place_reply` (
  `reply_id` INT NOT NULL AUTO_INCREMENT,
  `reply_content` VARCHAR(500) NOT NULL,
  `reply_createtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reply_user_id` VARCHAR(20) NOT NULL,
  `place_no` INT NOT NULL,
  `parent_id` INT NOT NULL,
  PRIMARY KEY (`reply_id`, `place_no`),
  INDEX `reply_user_id_idx` (`reply_user_id` ASC) VISIBLE,
  INDEX `fk_place_reply_place1` (`place_no` ASC) VISIBLE,
  CONSTRAINT `fk_place_reply_place1`
    FOREIGN KEY (`place_no`)
    REFERENCES `enjoytrip`.`place` (`place_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reply_user_id`
    FOREIGN KEY (`reply_user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan` (
  `user_id` VARCHAR(20) NOT NULL,
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `plan_title` VARCHAR(20) NOT NULL,
  `recommend_count` INT NOT NULL DEFAULT '0',
  `hit` INT NOT NULL DEFAULT '0',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `user_id_plan_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_plan`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`plan_recommend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan_recommend` (
  `user_id` VARCHAR(20) NOT NULL,
  `plan_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `plan_id`),
  INDEX `fk_plan_id_idx` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_id_plan`
    FOREIGN KEY (`plan_id`)
    REFERENCES `enjoytrip`.`trip_plan` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id_plan`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trail` (
  `trail_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `estimated_time` VARCHAR(45) NULL DEFAULT NULL,
  `start_name` VARCHAR(45) NULL DEFAULT NULL,
  `start_sido_code` INT NULL DEFAULT NULL,
  `start_gugun_code` INT NULL DEFAULT NULL,
  `end_name` VARCHAR(45) NULL DEFAULT NULL,
  `end_sido_code` INT NULL DEFAULT NULL,
  `end_gugun_code` INT NULL DEFAULT NULL,
  `route` TEXT NULL DEFAULT NULL,
  `tel` VARCHAR(45) NULL DEFAULT NULL,
  `manage_name` VARCHAR(45) NULL DEFAULT NULL,
  `add_date` VARCHAR(45) NULL DEFAULT NULL,
  `provide_code` VARCHAR(20) NULL DEFAULT NULL,
  `provide_name` VARCHAR(45) NULL DEFAULT NULL,
  `start_detail_addr` VARCHAR(100) NULL DEFAULT NULL,
  `end_detail_addr` VARCHAR(45) NULL DEFAULT NULL,
  `recomend` INT NULL DEFAULT '0',
  `total_length` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`trail_id`),
  INDEX `fk_start_sido_code_idx` (`start_sido_code` ASC) VISIBLE,
  INDEX `fk_end_sido_code_idx` (`end_sido_code` ASC) VISIBLE,
  INDEX `fk_start_gugun_code_idx` (`start_gugun_code` ASC) VISIBLE,
  INDEX `fk_end_gugun_code_idx` (`end_gugun_code` ASC) VISIBLE,
  CONSTRAINT `fk_end_gugun_code`
    FOREIGN KEY (`end_gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_end_sido_code`
    FOREIGN KEY (`end_sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_start_gugun_code`
    FOREIGN KEY (`start_gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_start_sido_code`
    FOREIGN KEY (`start_sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7573
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trail_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trail_board` (
  `trail_board_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NULL DEFAULT NULL,
  `trail_board_title` VARCHAR(45) NULL DEFAULT NULL,
  `trail_board_content` TEXT NULL DEFAULT NULL,
  `trail_board_start_time` DATE NULL DEFAULT NULL,
  `trail_board_end_time` DATE NULL DEFAULT NULL,
  `trail_board_create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `trail_board_trail_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`trail_board_no`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_board_trail_id_idx` (`trail_board_trail_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_trail_id`
    FOREIGN KEY (`trail_board_trail_id`)
    REFERENCES `enjoytrip`.`trail` (`trail_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trail_board_reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trail_board_reply` (
  `trail_reply_no` INT NOT NULL AUTO_INCREMENT,
  `trail_reply_board_no` INT NOT NULL,
  `trail_reply_user_id` VARCHAR(20) NOT NULL,
  `trail_reply_content` VARCHAR(500) NULL DEFAULT NULL,
  `trail_reply_createtime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trail_reply_no`, `trail_reply_board_no`),
  INDEX `fk_trail_reply_user_id_idx` (`trail_reply_user_id` ASC) VISIBLE,
  INDEX `fk_trail_reply_board_no_idx` (`trail_reply_board_no` ASC) VISIBLE,
  CONSTRAINT `fk_trail_reply_board_no`
    FOREIGN KEY (`trail_reply_board_no`)
    REFERENCES `enjoytrip`.`trail_board` (`trail_board_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_trail_reply_user_id`
    FOREIGN KEY (`trail_reply_user_id`)
    REFERENCES `enjoytrip`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trail_party`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trail_party` (
  `trail_party_id` INT NOT NULL,
  `trail_party_member_id` VARCHAR(20) NOT NULL,
  `trail_board_max_member` INT NOT NULL,
  `trail_board_member_count` INT NOT NULL,
  PRIMARY KEY (`trail_party_id`, `trail_party_member_id`),
  CONSTRAINT `fk_trail_board_no`
    FOREIGN KEY (`trail_party_id`)
    REFERENCES `enjoytrip`.`trail_board` (`trail_board_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_detail` (
  `plan_id` INT NOT NULL,
  `content_id` INT NOT NULL,
  `priority` INT NOT NULL,
  PRIMARY KEY (`plan_id`, `content_id`),
  INDEX `paln_id_idx` (`plan_id` ASC) VISIBLE,
  INDEX `content_id_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `content_id`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `paln_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `enjoytrip`.`trip_plan` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
