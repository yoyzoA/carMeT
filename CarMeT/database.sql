/* 
 SQLyog v3.7
 Host - 192.168.1.104 : Database - wn_pro_mysql
 **************************************************************
 Server version 4.0.13-standard-log
 */
create database carmet;
USE carmet;
-- Accessing the DB 
CREATE TABLE IF NOT EXISTS USER (
  userID MEDIUMINT NOT NULL AUTO_INCREMENT,
  username UNIQUE VARCHAR(50) NOT NULL,
  userEmail VARCHAR(320) NOT NULL,
  userPassword VARCHAR(100) NOT NULL,
  registrationDate DATE DEFAULT GETDATE(),
  phoneNumber DECIMAL(11, 0) CHECK(
    phoneNumber BETWEEN 1 AND 99999999999
  ),
  PRIMARY KEY (userID)
);
CREATE TABLE IF NOT EXISTS CARMAKE (
  carMakeID MEDIUMINT NOT NULL AUTO_INCREMENT,
  makeName VARCHAR(50),
  model VARCHAR(50),
  PRIMARY KEY (carMakeID)
);
CREATE TABLE IF NOT EXISTS CAR (
  carID MEDIUMINT NOT NULL AUTO_INCREMENT,
  carMakeID MEDIUMINT,
  userID MEDIUMINT,
  color VARCHAR(20) NOT NULL,
  price DECIMAL(7, 0) CHECK (
    price BETWEEN 1 AND 9999999
  ),
  vin VARCHAR(17) NOT NULL,
  carDescription VARCHAR(9999),
  odometer DECIMAL(6, 0) CHECK (odometer >= 0),
  FOREIGN KEY (carMakeID) REFERENCES CARMAKE(carMakeID),
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (carID)
);
CREATE TABLE IF NOT EXISTS ORDERS (
  orderID MEDIUMINT NOT NULL AUTO_INCREMENT,
  date DATE,
  status VARCHAR(50),
  totalAmount DECIMAL(10, 2),
  PRIMARY KEY (orderID)
);
CREATE TABLE IF NOT EXISTS PART (
  partID MEDIUMINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  description VARCHAR(255),
  price DECIMAL(10, 2),
  supplierID MEDIUMINT,
  PRIMARY KEY (partID),
  FOREIGN KEY (supplierID) REFERENCES USER(userID)
);
CREATE TABLE IF NOT EXISTS REVIEW (
  reviewID MEDIUMINT NOT NULL AUTO_INCREMENT,
  rating DECIMAL(1, 0) CHECK (
    rating BETWEEN 0 AND 5
  ),
  comment VARCHAR(200),
  reviewDate DATE,
  supplierID MEDIUMINT,
  PRIMARY KEY (reviewID),
  FOREIGN KEY (supplierID) REFERENCES USER(userID)
);
CREATE TABLE IF NOT EXISTS WORKSON (
  partID MEDIUMINT,
  carMakeID MEDIUMINT,
  FOREIGN KEY (carMakeID) REFERENCES CARMAKE(carMakeID),
  FOREIGN KEY (partID) REFERENCES PART(partID)
);
CREATE TABLE IF NOT EXISTS LEAVES (
  customerID MEDIUMINT,
  reviewID MEDIUMINT,
  supplierID MEDIUMINT,
  PRIMARY KEY (customerID, reviewID, supplierID),
  FOREIGN KEY (customerID) REFERENCES USER(userID),
  FOREIGN KEY (reviewID) REFERENCES REVIEW(reviewID),
  FOREIGN KEY (supplierID) REFERENCES USER(userID)
);
CREATE TABLE IF NOT EXISTS PARTORDER (
  customerID MEDIUMINT,
  supplierID MEDIUMINT,
  partID MEDIUMINT,
  orderID MEDIUMINT,
  PRIMARY KEY (customerID, supplierID, partID, orderID),
  FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
  FOREIGN KEY (customerID) REFERENCES USER(userID),
  FOREIGN KEY (supplierID) REFERENCES USER(userID),
  FOREIGN KEY (partID) REFERENCES PART(partID)
);
CREATE TABLE IF NOT EXISTS CARORDER (
  customerID MEDIUMINT,
  supplierID MEDIUMINT,
  carID MEDIUMINT,
  orderID MEDIUMINT,
  PRIMARY KEY (customerID, supplierID, carID, orderID),
  FOREIGN KEY (customerID) REFERENCES USER(userID),
  FOREIGN KEY (supplierID) REFERENCES USER(userID),
  FOREIGN KEY (carID) REFERENCES CAR(carID),
  FOREIGN KEY (orderID) REFERENCES ORDERS(orderID)
);