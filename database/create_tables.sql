CREATE SCHEMA IF NOT EXISTS detective;

USE  detective;

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `username` varchar(15) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_username` (`username`),
  UNIQUE KEY `uk_users_email` (`email`)
);

CREATE TABLE IF NOT EXISTS roles (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_roles_name (name)
);

CREATE TABLE IF NOT EXISTS user_roles (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_role_id` (`role_id`),
  CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
  
  CREATE TABLE IF NOT EXISTS detective_case (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  creator bigint(20) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  modified timestamp DEFAULT CURRENT_TIMESTAMP,
  image VARCHAR(255),
  ready BOOL DEFAULT FALSE,
  time INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_creator FOREIGN KEY (creator) REFERENCES users (id));
  
  CREATE TABLE IF NOT EXISTS save (
  save_id bigint(20) NOT NULL AUTO_INCREMENT,
  player bigint(20) NOT NULL ,
  case_id bigint(20) NOT NULL,
  last_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  save_json JSON,
  score INT,
  PRIMARY KEY (save_id),
  CONSTRAINT fk_player FOREIGN KEY (player) REFERENCES users (id),
  CONSTRAINT fk_save_case FOREIGN KEY (case_id) REFERENCES detective_case (id));
  
  CREATE TABLE IF NOT EXISTS location(
  location_id bigint(20) NOT NULL AUTO_INCREMENT,
  case_id bigint(20) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  PRIMARY KEY (location_id),
  CONSTRAINT fk_case_location FOREIGN KEY (case_id) REFERENCES detective_case(id)); 
  
 CREATE TABLE IF NOT EXISTS location_connection(
  from_id bigint(20) NOT NULL,
  to_id bigint(20) NOT NULL,
  time int NOT NULL,
  PRIMARY KEY (from_id,to_id),
  CONSTRAINT fk_from FOREIGN KEY (from_id) REFERENCES location(location_id),
  CONSTRAINT fk_to FOREIGN KEY (to_id) REFERENCES location(location_id)); 
  
  CREATE TABLE IF NOT EXISTS item(
  item_id bigint(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  examResult VARCHAR(2000),
  type_of_item CHAR,  
  PRIMARY KEY (item_id));
  
  CREATE TABLE IF NOT EXISTS action(
  action_id bigint(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(2000),
  image VARCHAR(255),
  time INT, 
  PRIMARY KEY (action_id));
  
CREATE TABLE IF NOT EXISTS action_location(
  action_id bigint(20) NOT NULL,
  location_id bigint(20) NOT NULL,
  PRIMARY KEY (action_id,location_id),
  CONSTRAINT fk_action FOREIGN KEY (action_id) REFERENCES action(action_id),
  CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(location_id)); 
  
  CREATE TABLE IF NOT EXISTS action_item(
  action_id bigint(20) NOT NULL AUTO_INCREMENT,
  item_id bigint(20) NOT NULL,
  PRIMARY KEY (action_id,item_id),
  CONSTRAINT fk_action_item FOREIGN KEY (action_id) REFERENCES action(action_id),
  CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES item(item_id)); 
  
  CREATE TABLE IF NOT EXISTS action_action(
  action_id bigint(20) NOT NULL,
  revealed_id bigint(20) NOT NULL,
  PRIMARY KEY (action_id,revealed_id),
  CONSTRAINT fk_action_action FOREIGN KEY (action_id) REFERENCES action(action_id),
  CONSTRAINT fk_revealed FOREIGN KEY (revealed_id) REFERENCES action(action_id)); 
  
  CREATE TABLE IF NOT EXISTS question(
  question_id bigint(20) NOT NULL AUTO_INCREMENT,
  case_id bigint(20) NOT NULL,
  text VARCHAR(400) NOT NULL,
  PRIMARY KEY (question_id),
  CONSTRAINT fk_case FOREIGN KEY (case_id) REFERENCES detective_case(id)); 
  
  CREATE TABLE IF NOT EXISTS answer(
  answer_id bigint(20) NOT NULL AUTO_INCREMENT,
  question_id bigint(20) NOT NULL,
  text VARCHAR(400) NOT NULL,
  correct boolean,
  PRIMARY KEY (answer_id),
  CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES question(question_id)); 
  
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

ALTER TABLE location ADD COLUMN isStart boolean;
ALTER TABLE detective_case ADD COLUMN bgnDate timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE action ADD COLUMN case_id bigint(20) NOT NULL;
ALTER TABLE action ADD CONSTRAINT fk_case_action FOREIGN KEY (case_id) REFERENCES detective_case(id) ;
