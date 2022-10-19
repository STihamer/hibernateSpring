create table PERSON
(
   id integer not null auto_increment,
   name varchar(255) not null,
   location varchar(255),
   birth_date timestamp,
   primary key(id)
);

INSERT INTO PERSON ( NAME, LOCATION, BIRTH_DATE )
VALUES('Ranga', 'Hyderabad',curdate());
INSERT INTO PERSON  ( NAME, LOCATION, BIRTH_DATE )
VALUES('James', 'New York',curdate());
INSERT INTO PERSON ( NAME, LOCATION, BIRTH_DATE )
VALUES('Pieter', 'Amsterdam',curdate());

