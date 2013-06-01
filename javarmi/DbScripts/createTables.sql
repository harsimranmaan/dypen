CREATE DATABASE IF NOT EXISTS Dypen ;
USE Dypen;


DROP TABLE IF EXISTS Client;
CREATE TABLE Client (
  clientName varchar(25) NOT NULL,
  balance INT NOT NULL,
  isAdmin bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (clientName),
  CONSTRAINT ClientCHKbalance CHECK(balance > 0)
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
  clientName varchar(25) NOT NULL,
   stockName varchar(7) NOT NULL,
  quantity INT NOT NULL,
 price decimal(10,2) NOT NULL,
 
	PRIMARY KEY (clientName,stockName),
	CONSTRAINT ClientBuyFKclientName FOREIGN KEY (clientName) REFERENCES Client (clientName) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ClientBuyFKstockName FOREIGN KEY (stockName) REFERENCES Stock (stockName) ON UPDATE CASCADE ON DELETE RESTRICT,
   CONSTRAINT ClientBuyCHKprice CHECK(price > 0.0),
  CONSTRAINT ClientBuyCHKquantity CHECK(quantity > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ClientSell;
CREATE TABLE ClientSell (
   clientName varchar(25) NOT NULL,
   stockName varchar(7) NOT NULL,
   quantity INT NOT NULL,
   price decimal(10,2) NOT NULL,
 
	PRIMARY KEY (clientName,stockName),
	CONSTRAINT ClientSellFKclientName FOREIGN KEY (clientName) REFERENCES Client (clientName) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ClientSellFKstockName FOREIGN KEY (stockName) REFERENCES Stock (stockName) ON UPDATE CASCADE ON DELETE RESTRICT,
   CONSTRAINT ClientSellCHKprice CHECK(price > 0.0),
  CONSTRAINT ClientSellCHKquantity CHECK(quantity > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

