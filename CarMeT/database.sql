/* 
SQLyog v3.7
Host - 192.168.1.104 : Database - wn_pro_mysql
**************************************************************
Server version 4.0.13-standard-log
*/



USE carmet;                  -- Accessing the DB 
 
CREATE TABLE IF NOT EXISTS USER   
(
 
  userID DECIMAL(6,0) CHECK(userID BETWEEN 1 AND 999999), 
  username VARCHAR(50) NOT NULL,
  userEmail VARCHAR(320) NOT NULL,
  userPassword VARCHAR(100) NOT NULL,
  registrationDate DATE,
  phoneNumber DECIMAL(11,0) CHECK(phoneNumber BETWEEN 1 AND 99999999999),
  PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS SUPPLIER
(

  supplierID VARCHAR(6) CHECK (supplierID LIKE 'S[0-9][0-9][0-9][0-9][0-9]'),
  userID DECIMAL(6,0) CHECK(userID BETWEEN 1 AND 999999),
  address VARCHAR(300) NOT NULL,
  transactionCount DECIMAL(6,0) CHECK(transactionCount BETWEEN 1 AND 999999), 
  rating DECIMAL(1, 0) CHECK (rating BETWEEN 0 AND 5),
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (supplierID)
);

CREATE TABLE IF NOT EXISTS CUSTOMER
(

  customerID VARCHAR(6) CHECK (customerID LIKE 'C[0-9][0-9][0-9][0-9][0-9]'),
  userID DECIMAL(6,0) CHECK(userID BETWEEN 1 AND 999999),
  purchaseCount DECIMAL(6,0) CHECK(purchaseCount BETWEEN 1 AND 999999), 
  reviewCount DECIMAL(6,0) CHECK(reviewCount BETWEEN 1 AND 999999), 
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (customerID)
);

CREATE TABLE IF NOT EXISTS CARMAKE 
(
  carMakeID VARCHAR(6) CHECK (carMakeID LIKE 'M[0-9][0-9][0-9][0-9][0-9]'),
  makeName VARCHAR(50),
  model VARCHAR(50) ,
  modelYear YEAR,
  PRIMARY KEY (carMakeID)
);

CREATE TABLE IF NOT EXISTS CAR
(
  carID VARCHAR(6) CHECK (carID LIKE 'V[0-9][0-9][0-9][0-9][0-9]'),
  carMakeID VARCHAR(6) CHECK (carMakeID LIKE 'M[0-9][0-9][0-9][0-9][0-9]'),
  userID DECIMAL(6,0) CHECK(userID BETWEEN 1 AND 99999),
  color VARCHAR(20) NOT NULL,
  price DECIMAL(7,0) CHECK (price BETWEEN 1 AND 9999999),
  vin VARCHAR(17) NOT NULL,
  carDescription VARCHAR(9999),
  odometer DECIMAL(6,0) CHECK (odometer >= 0),
  FOREIGN KEY (carMakeID) REFERENCES CARMAKE(carMakeID),
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (carID)
);


INSERT INTO CARMAKE VALUES ('M12345', 'Toyota', 'Camry', '2022');
/*INSERT INTO CARMAKE VALUES ('M01','TEST','TEST',NULL);*/
/*INSERT INTO USER VALUES (12345, 'TEST1','USEREMAIL1','PASS1', '2021-05-10',11);*/







