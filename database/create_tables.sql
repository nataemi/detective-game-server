CREATE SCHEMA IF NOT EXISTS detective;

USE  detective;

CREATE  TABLE IF NOT EXISTS users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
  
  CREATE TABLE IF NOT EXISTS user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
  CREATE TABLE IF NOT EXISTS detective_case (
  case_id VARCHAR(45) NOT NULL,
  creator VARCHAR(45) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  ready BOOL DEFAULT FALSE,
  time INT NOT NULL,
  PRIMARY KEY (case_id),
  CONSTRAINT fk_creator FOREIGN KEY (creator) REFERENCES users (username));
  
  CREATE TABLE IF NOT EXISTS save (
  save_id VARCHAR(45) NOT NULL,
  player VARCHAR(45) NOT NULL ,
  case_id VARCHAR(45) NOT NULL,
  last_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  score INT,
  PRIMARY KEY (save_id),
  CONSTRAINT fk_player FOREIGN KEY (player) REFERENCES users (username));
  
  CREATE TABLE IF NOT EXISTS location(
  location_id VARCHAR(45) NOT NULL,
  case_id VARCHAR(45) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  PRIMARY KEY (location_id),
  CONSTRAINT fk_case_location FOREIGN KEY (case_id) REFERENCES detective_case(case_id)); 
  
 CREATE TABLE IF NOT EXISTS location_connection(
  from_id VARCHAR(45) NOT NULL,
  to_id VARCHAR(45) NOT NULL,
  time int NOT NULL,
  PRIMARY KEY (from_id,to_id),
  CONSTRAINT fk_from FOREIGN KEY (from_id) REFERENCES location(location_id),
  CONSTRAINT fk_to FOREIGN KEY (to_id) REFERENCES location(location_id)); 
  
  CREATE TABLE IF NOT EXISTS item(
  item_id VARCHAR(45) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  examResult VARCHAR(2000),
  type_of_item CHAR,  
  PRIMARY KEY (item_id));
  
  CREATE TABLE IF NOT EXISTS action(
  action_id VARCHAR(45) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  time INT, 
  PRIMARY KEY (action_id));
  
CREATE TABLE IF NOT EXISTS action_location(
  action_id VARCHAR(45) NOT NULL,
  location_id VARCHAR(45) NOT NULL,
  PRIMARY KEY (action_id,location_id),
  CONSTRAINT fk_action FOREIGN KEY (action_id) REFERENCES action(action_id),
  CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(location_id)); 
  
  
  