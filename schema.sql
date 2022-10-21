SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS image_likes;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE category (
category_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL);

INSERT INTO category (category_name)
VALUES ("Portrait"), ("Landscape");

CREATE TABLE app_user (
user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(250) NOT NULL,
email VARCHAR(50) NOT NULL,
role VARCHAR(50) NOT NULL,
password_hash VARCHAR(250) NOT NULL);

INSERT INTO app_user (username, email, role, password_hash)
VALUES ("admin", "admin@gmail.com", "ADMIN", "$2a$10$Xp67oEDHyODcnTzkIIp9z.SpmmpZg33mqZe/jvaSHMnpWtEQGov5e"),
("user", "user@usermail.com", "USER", "$2a$10$Rc25Yhstdcr9Ce3WcQFKLeHT3nN1Yr.ud6M0AywXA8Q1tidWcdvqy");

CREATE TABLE image (
image_id BIGINT NOT NULL AUTO_INCREMENT,
file_name VARCHAR(250) NOT NULL,
file_type VARCHAR(50) NOT NULL,
file_data LONGBLOB,
image_title VARCHAR(50) NOT NULL,
image_desc VARCHAR(500),
image_date DATE,
user_id BIGINT,
category_id BIGINT,
PRIMARY KEY (image_id),
FOREIGN KEY (user_id) REFERENCES app_user(user_id),
FOREIGN KEY (category_id) REFERENCES category(category_id)); 

CREATE TABLE image_likes (
user_id BIGINT NOT NULL,
image_id BIGINT NOT NULL,
PRIMARY KEY(user_id, image_id),
CONSTRAINT fk_image_likes_app_user FOREIGN KEY (user_id) REFERENCES app_user (user_id),
CONSTRAINT fk_image_likes_image FOREIGN KEY (image_id) REFERENCES image (image_id));