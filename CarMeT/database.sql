/* 
 SQLyog v3.7
 Host - 192.168.1.104 : Database - wn_pro_mysql
 **************************************************************
 Server version 4.0.13-standard-log
 */
USE carmet;
-- Accessing the DB 
CREATE TABLE IF NOT EXISTS USER (
  userID INT,
  username VARCHAR(50) NOT NULL,
  userEmail VARCHAR(320) NOT NULL,
  userPassword VARCHAR(100) NOT NULL,
  registrationDate DATE,
  phoneNumber DECIMAL(11, 0) CHECK(
    phoneNumber BETWEEN 1 AND 99999999999
  ),
  PRIMARY KEY (userID)
);
CREATE TABLE IF NOT EXISTS SUPPLIER (
  supplierID VARCHAR(6),
  userID INT,
  address VARCHAR(300) NOT NULL,
  transactionCount DECIMAL(6, 0) CHECK(
    transactionCount BETWEEN 1 AND 999999
  ),
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (supplierID)
);
CREATE TABLE IF NOT EXISTS CUSTOMER (
  customerID VARCHAR(6),
  userID INT,
  purchaseCount DECIMAL(6, 0) CHECK(
    purchaseCount BETWEEN 1 AND 999999
  ),
  reviewCount DECIMAL(6, 0) CHECK(
    reviewCount BETWEEN 1 AND 999999
  ),
  FOREIGN KEY (userID) REFERENCES USER(userID),
  PRIMARY KEY (customerID)
);
CREATE TABLE IF NOT EXISTS CARMAKE (
  carMakeID VARCHAR(6),
  makeName VARCHAR(50),
  model VARCHAR(50),
  modelYear YEAR,
  PRIMARY KEY (carMakeID)
);
CREATE TABLE IF NOT EXISTS CAR (
  carID VARCHAR(6),
  carMakeID VARCHAR(6),
  userID INT,
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
  orderID DECIMAL(8, 0),
  date DATE,
  status VARCHAR(50),
  totalAmount DECIMAL(10, 2),
  PRIMARY KEY (orderID)
);
CREATE TABLE IF NOT EXISTS PART (
  partID VARCHAR(6),
  name VARCHAR(255),
  description VARCHAR(255),
  price DECIMAL(10, 2),
  inStockQty DECIMAL(4, 0),
  supplierID VARCHAR(6),
  PRIMARY KEY (partID),
  FOREIGN KEY (supplierID) REFERENCES SUPPLIER(supplierID)
);
CREATE TABLE IF NOT EXISTS REVIEW (
  reviewID VARCHAR(6),
  rating DECIMAL(1, 0) CHECK (
    rating BETWEEN 0 AND 5
  ),
  comment VARCHAR(200),
  reviewDate DATE,
  supplierID VARCHAR(6),
  PRIMARY KEY (reviewID),
  FOREIGN KEY (supplierID) REFERENCES SUPPLIER(supplierID)
);
CREATE TABLE IF NOT EXISTS WORKSON (
  partID VARCHAR(6),
  carMakeID VARCHAR(6),
  PRIMARY KEY (partID),
  FOREIGN KEY (carMakeID) REFERENCES CARMAKE(carMakeID),
  FOREIGN KEY (partID) REFERENCES PART(partID)
);
CREATE TABLE IF NOT EXISTS LEAVES (
  customerID VARCHAR(6),
  reviewID VARCHAR(6),
  supplierID VARCHAR(6),
  PRIMARY KEY (customerID, reviewID, supplierID),
  FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID),
  FOREIGN KEY (reviewID) REFERENCES REVIEW(reviewID),
  FOREIGN KEY (supplierID) REFERENCES SUPPLIER(supplierID)
);
CREATE TABLE IF NOT EXISTS PARTORDER (
  customerID VARCHAR(6),
  supplierID VARCHAR(6),
  partID VARCHAR(6),
  orderID DECIMAL(8, 0),
  PRIMARY KEY (customerID, supplierID, partID, orderID),
  FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
  FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID),
  FOREIGN KEY (supplierID) REFERENCES SUPPLIER(supplierID),
  FOREIGN KEY (partID) REFERENCES PART(partID)
);
CREATE TABLE IF NOT EXISTS CARORDER (
  customerID VARCHAR(6),
  supplierID VARCHAR(6),
  carID VARCHAR(6),
  orderID DECIMAL(8, 0),
  PRIMARY KEY (customerID, supplierID, carID, orderID),
  FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID),
  FOREIGN KEY (supplierID) REFERENCES SUPPLIER(supplierID),
  FOREIGN KEY (carID) REFERENCES CAR(carID),
  FOREIGN KEY (orderID) REFERENCES ORDERS(orderID)
);