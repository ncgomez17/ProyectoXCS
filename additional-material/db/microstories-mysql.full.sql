-- MySQL dump 10.13  Distrib 5.5.55, for Win64 (AMD64)
--
-- Host: localhost    Database: dgss2223_teama_microstories
-- ------------------------------------------------------
-- Server version	5.5.55

DROP DATABASE IF EXISTS `dgss2223_teamA_microstories`;
CREATE DATABASE `dgss2223_teamA_microstories` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `dgss2223_teamA_microstories`;

--
-- User creation
--

-- Below MySql 5.7.6, doesn't work in MySQL 8
GRANT ALL ON `dgss2223_teamA_microstories`.* TO 'microstories'@'localhost' IDENTIFIED BY 'microstoriespass';

-- MySql 5.7.6 and above
-- CREATE USER microstories@'localhost' IDENTIFIED BY 'microstoriespass';

GRANT ALL PRIVILEGES ON dgss2223_teamA_microstories.* TO microstories@'localhost';
FLUSH PRIVILEGES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `login`    varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
    `password` varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
    `role` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
    PRIMARY KEY (`login`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
INSERT INTO `user`
VALUES ('user1', '827ccb0eea8a706c4c34a16891f84e7b', 'author'),
       ('user2', '827ccb0eea8a706c4c34a16891f84e7b', 'author'),
       ('user3', '827ccb0eea8a706c4c34a16891f84e7b', 'author'),
       ('user4', '827ccb0eea8a706c4c34a16891f84e7b', 'author'),
       ('user5', '827ccb0eea8a706c4c34a16891f84e7b', 'author');
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;

CREATE TABLE `story`
(
    `id`             int(11)                                NOT NULL AUTO_INCREMENT,
    `author`         varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `content`        varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `date`           datetime                                 DEFAULT NULL,
    `genre`          varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `mainTheme`      varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `published`      bit(1)                                 NOT NULL,
    `secondaryTheme` varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `title`          varchar(70) COLLATE utf8mb4_unicode_ci   DEFAULT NULL,
    `login`          varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKm6topghg00xrv2ulrwsv354wg` (`login`),
    CONSTRAINT `FKm6topghg00xrv2ulrwsv354wg` FOREIGN KEY (`login`) REFERENCES `user` (`login`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
INSERT INTO `story`
VALUES (1, 'autor',
        'Nam vel semper lacus. Pellentesque a turpis odio. Aliquam cursus massa id diam feugiat, sed rutrum massa hendrerit. Nam eget placerat lacus, nec lobortis nibh. Mauris a orci et risus convallis blandit quis id est. Aliquam ex nulla, vestibulum ac elit non, bibendum euismod orci. Mauris lectus tortor, pulvinar vel sem at, dictum ullamcorper lorem. In sed dolor in diam iaculis pretium vel sed leo. Etiam nec mollis risus, ac ornare erat.',
        '2022-11-10 00:00:00', 'STORY', 'SUSPENSE', '', 'HISTORIC', 'Titulo1', 'user1'),
       (2, 'escritor',
        'Nunc volutpat vehicula dui vitae imperdiet. Nam quis sapien tortor. Vivamus dignissim mauris a nisi imperdiet varius. Proin a urna ullamcorper risus luctus malesuada. Morbi non posuere est.',
        '2022-11-11 00:00:00', 'STORY', 'ADVENTURE', '', 'ROMANCE', 'Titulo2', 'user1'),
       (3, 'escritora',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sagittis, tortor vitae accumsan imperdiet, massa massa sagittis sapien, quis varius erat nisi et erat.',
        '2022-11-12 00:00:00', 'NANOSTORY', 'HORROR', '', 'HISTORIC', 'Titulo3', 'user1'),
       (4, 'autora',
        'Maecenas et fringilla lectus, ut molestie elit. Nulla sagittis tellus vehicula accumsan dignissim. Proin vulputate lectus lacus. ',
        '2022-11-13 00:00:00', 'NANOSTORY', 'ADVENTURE', '', 'ROMANCE', 'Titulo4', 'user2'),
       (5, 'poetisa',
        'Nulla varius cursus volutpat. Aliquam et ipsum lacus. Vivamus dictum ullamcorper turpis nec feugiat. Quisque posuere lectus eget dui molestie, eget interdum nulla laoreet',
        '2022-11-14 00:00:00', 'POETRY', 'SCIENCE_FICTION', '', 'HISTORIC', 'Titulo5', 'user2'),
       (6, 'poeta',
        'Morbi tempus quam at felis efficitur, sed iaculis sapien feugiat. Nulla vehicula eleifend sapien, non.',
        '2022-11-15 00:00:00', 'POETRY', 'ADVENTURE', '', 'ROMANCE', 'Titulo6', 'user2'),
       (7, 'autor',
        'Nam vel semper lacus. Pellentesque a turpis odio. Aliquam cursus massa id diam feugiat, sed rutrum massa hendrerit. Nam eget placerat lacus, nec lobortis nibh. Mauris a orci et risus convallis blandit quis id est. Aliquam ex nulla, vestibulum ac elit non, bibendum euismod orci. Mauris lectus tortor, pulvinar vel sem at, dictum ullamcorper lorem. In sed dolor in diam iaculis pretium vel sed leo. Etiam nec mollis risus, ac ornare erat.',
        '2022-10-08 00:00:00', 'STORY', 'SUSPENSE', '', 'HISTORIC', 'Titulo7', 'user2'),
       (8, 'escritor',
        'Nunc volutpat vehicula dui vitae imperdiet. Nam quis sapien tortor. Vivamus dignissim mauris a nisi imperdiet varius. Proin a urna ullamcorper risus luctus malesuada. Morbi non posuere est.',
        '2022-11-05 00:00:00', 'STORY', 'ADVENTURE', '', 'ROMANCE', 'Titulo8', 'user2'),
       (9, 'escritora',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sagittis, tortor vitae accumsan imperdiet, massa massa sagittis sapien, quis varius erat nisi et erat.',
        '2021-10-12 00:00:00', 'NANOSTORY', 'HORROR', '', 'HISTORIC', 'Titulo9', 'user3'),
       (10, 'autora',
        'Maecenas et fringilla lectus, ut molestie elit. Nulla sagittis tellus vehicula accumsan dignissim. Proin vulputate lectus lacus. ',
        '2022-05-01 00:00:00', 'NANOSTORY', 'ADVENTURE', '', 'ROMANCE', 'Titulo10', 'user4'),
       (11, 'poetisa',
        'Nulla varius cursus volutpat. Aliquam et ipsum lacus. Vivamus dictum ullamcorper turpis nec feugiat. Quisque posuere lectus eget dui molestie, eget interdum nulla laoreet',
        '2022-11-17 00:00:00', 'POETRY', 'SCIENCE_FICTION', '', 'HISTORIC', 'Titulo11', 'user4'),
       (12, 'poeta',
        'Morbi tempus quam at felis efficitur, sed iaculis sapien feugiat. Nulla vehicula eleifend sapien, non.',
        '2022-11-20 00:00:00', 'POETRY', 'ADVENTURE', '', 'ROMANCE', 'Titulo12', 'user4');
UNLOCK TABLES;



--
-- Table structure for table `visitdate`
--

DROP TABLE IF EXISTS `visitdate`;
CREATE TABLE `visitdate`
(
    `storyId`   int(11) NOT NULL,
    `visitDate` datetime DEFAULT NULL,
    KEY `FKmn7sg89g0ojyhsc0o5ftinlkp` (`storyId`),
    CONSTRAINT `FKmn7sg89g0ojyhsc0o5ftinlkp` FOREIGN KEY (`storyId`) REFERENCES `story` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

--
-- Dumping data for table `visitdate`
--

LOCK TABLES `visitdate` WRITE;
INSERT INTO `visitdate`
VALUES (1, '2022-02-10 23:31:01'),
       (1, '2022-02-10 23:32:01'),
       (1, '2022-02-10 23:33:01'),
       (1, '2022-02-10 23:34:01'),
       (1, '2022-02-10 23:35:01'),
       (1, '2022-02-10 23:36:01'),
       (1, '2022-02-10 23:37:01'),
       (2, '2022-02-10 23:38:01'),
       (2, '2022-05-01 10:01:01'),
       (2, '2022-05-22 11:40:01'),
       (3, '2022-09-06 06:11:22'),
       (4, '2022-05-30 01:01:01'),
       (4, '2022-10-21 02:20:01'),
       (5, '2022-11-01 01:01:11'),
       (5, '2022-12-05 08:45:07'),
       (6, '2022-06-08 15:01:30');
UNLOCK TABLES;