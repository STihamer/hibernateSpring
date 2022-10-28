DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS review;

CREATE TABLE `course`
(
    `id`                int AUTO_INCREMENT PRIMARY KEY,
    `name`              varchar(100) NOT NULL,
    `last_updated_date` DATETIME DEFAULT NULL,
    `created_date`      DATETIME DEFAULT NULL,
    `is_deleted`        BOOLEAN  default false
);

CREATE TABLE `review`
(
    `id`          int AUTO_INCREMENT PRIMARY KEY,
    `rating`      varchar(100) NOT NULL,
    `description` varchar(200) NOT NULL,
    `course_id`   int          not null
);


CREATE TABLE `passport`
(
    `id`     int AUTO_INCREMENT PRIMARY KEY,
    `number` varchar(100) NOT NULL
);

CREATE TABLE `student`
(
    `id`          int AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(100) NOT NULL,
    `passport_id` int          DEFAULT NULL
);

CREATE TABLE `student_course`
(
    `student_id` int not null,
    `course_id`  int not null

);



INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10001, 'Jpa', CURDATE(), CURDATE());

INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10002, 'Jpa 50', curdate(), CURDATE());

INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10003, 'Jpa 60 steps', CURDATE(), CURDATE());

INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10004, 'Jpa 60 steps', CURDATE(), CURDATE());
INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10005, 'Jpa 70 steps', CURDATE(), CURDATE());
INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10006, 'Jpa 80 steps', CURDATE(), CURDATE());
INSERT INTO `course` (`id`, `name`, `created_date`, `last_updated_date`)
VALUES (10007, 'Jpa 90 steps', CURDATE(), CURDATE());



INSERT INTO `review` (`id`, `rating`, `description`, `course_id`)
VALUES (50001, 'Four', 'Great course', 10001);

INSERT INTO `review` (`id`, `rating`, `description`, `course_id`)
VALUES (50002, 'Five', 'Wonderful course', 10001);


INSERT INTO `review` (`id`, `rating`, `description`, `course_id`)
VALUES (50003, 'Four', 'Awesome course', 10003);

INSERT INTO `passport` (`id`, `number`)
VALUES (40001, 'Ro33344');

INSERT INTO `passport` (`id`, `number`)
VALUES (40002, '4RO555666');

INSERT INTO `passport` (`id`, `number`)
VALUES (40003, 'RO777888');


INSERT INTO `student` (`id`, `name`, `passport_id`)
VALUES (20001, 'Ranga', 40001);

INSERT INTO `student` (`id`, `name`, `passport_id`)
VALUES (20002, 'Adam', 40002);

INSERT INTO `student` (`id`, `name`, `passport_id`)
VALUES (20003, 'Jane', 40003);


INSERT INTO `student_course` (`student_id`, `course_id`)
VALUES (20001, 10001);

INSERT INTO `student_course` (`student_id`, `course_id`)
VALUES (20002, 10001);

INSERT INTO `student_course` (`student_id`, `course_id`)
VALUES (20003, 10001);

INSERT INTO `student_course` (`student_id`, `course_id`)
VALUES (20001, 10003);
