SET FOREIGN_KEY_CHECKS = 0;
-- ustaw na 1 jak juz dropniesz

DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS action_location;
DROP TABLE IF EXISTS action_item;
DROP TABLE IF EXISTS action_action;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS location_connection;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS save;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS detective_case;

-- tego raczej nie potrzebujesz usuwac
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
