CREATE DATABASE IF NOT EXISTS Dypen ;
USE Dypen;


DROP TABLE IF EXISTS Client;
CREATE TABLE Client (
  clientName varchar(25) NOT NULL,
  balance decimal(10,2) NOT NULL,
  isAdmin bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (clientName),
  CONSTRAINT ClientCHKbalance CHECK(balance > 0.0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS Stock;
CREATE TABLE Stock(
  stockName varchar(7) NOT NULL,
  price decimal(10,2) NOT NULL,
  remaining INT NOT NULL,
  PRIMARY KEY (stockName),
  CONSTRAINT StockCHKprice CHECK(price > 0.0),
  CONSTRAINT StockCHKremaining CHECK(remaining > 0)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
DROP TABLE IF EXISTS ClientBuy;
CREATE TABLE ClientBuy (
	id INT NOT NULL AUTO_INCREMENT,
  clientName varchar(25) NOT NULL,
   stockName varchar(7) NOT NULL,
  quantity INT NOT NULL,
 price decimal(10,2) NOT NULL,
 
	PRIMARY KEY (id),
	CONSTRAINT ClientBuyFKclientName FOREIGN KEY (clientName) REFERENCES Client (clientName) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ClientBuyFKstockName FOREIGN KEY (stockName) REFERENCES Stock (stockName) ON UPDATE CASCADE ON DELETE RESTRICT,
   CONSTRAINT ClientBuyCHKprice CHECK(price > 0.0),
  CONSTRAINT ClientBuyCHKquantity CHECK(quantity >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE ClientBuy AUTO_INCREMENT = 100;

DROP TABLE IF EXISTS ClientSell;
CREATE TABLE ClientSell (
id INT NOT NULL AUTO_INCREMENT,
   clientName varchar(25) NOT NULL,
   stockName varchar(7) NOT NULL,
   quantity INT NOT NULL,
   price decimal(10,2) NOT NULL,
 
	PRIMARY KEY (id),
	CONSTRAINT ClientSellFKclientName FOREIGN KEY (clientName) REFERENCES Client (clientName) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ClientSellFKstockName FOREIGN KEY (stockName) REFERENCES Stock (stockName) ON UPDATE CASCADE ON DELETE RESTRICT,
   CONSTRAINT ClientSellCHKprice CHECK(price > 0.0),
  CONSTRAINT ClientSellCHKquantity CHECK(quantity >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE ClientSell AUTO_INCREMENT = 100;



