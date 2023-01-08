DROP DATABASE IF EXISTS `dgss2223_teamA_microstories`;
CREATE DATABASE `dgss2223_teamA_microstories` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `dgss2223_teamA_microstories`;

--
-- User creation
--
CREATE USER microstories@'%' IDENTIFIED BY 'microstoriespass';
GRANT ALL PRIVILEGES ON dgss2223_teamA_microstories.* TO microstories@'%';
FLUSH PRIVILEGES;


--
-- Tables creation
--

-- Write here the table creation queries.


--
-- Data insertion
--

-- Write here the data insertion queries.
