CREATE TABLE cards (
  cardID            INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  cardnumber        VARCHAR(50)                              NOT NULL,
  cardtypeID        INTEGER                                  NOT NULL,
  validity          DATETIME                                 NOT NULL,
  buyinglimit       BIGINT                                   NOT NULL,
  cashwithdrawlimit BIGINT                                   NOT NULL,
  cardlimit         BIGINT,
  accountID         INTEGER                                  NOT NULL
);

CREATE TABLE card_types (
  cardtypeID  INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name        VARCHAR(50)                              NOT NULL,
  description VARCHAR(250)
);

CREATE TABLE account_status (
  accountstatusID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name            VARCHAR(50)                              NOT NULL,
  description     VARCHAR(250)
);

CREATE TABLE accounts (
  accountID       INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  customerID      INTEGER                                  NOT NULL,
  number          VARCHAR(20)                              NOT NULL,
  accounttypeID   INTEGER                                  NOT NULL,
  accountstatusID INTEGER                                  NOT NULL,
  opendate        DATETIME                                 NOT NULL,
  balance         BIGINT                                   NOT NULL,
  debitline       BIGINT,
  interest        INTEGER                                  NOT NULL
);

CREATE TABLE account_type (
  accounttypeID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name          VARCHAR(50)                              NOT NULL,
  description   VARCHAR(250)
);

CREATE TABLE customers (
  customerID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  firstname  VARCHAR(50)                              NOT NULL,
  lastname   VARCHAR(50)                              NOT NULL,
  login      VARCHAR(50)                              NOT NULL,
  password   VARCHAR(100)                             NOT NULL,
  createdate DATETIME                                 NOT NULL,
  isactive   BOOLEAN                                  NOT NULL,
  lastlogin  DATETIME
);

CREATE TABLE transactions (
  transactionID       INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  dateoftransaction   DATETIME                                 NOT NULL,
  transactiontypeID   INTEGER                                  NOT NULL,
  value               BIGINT                                   NOT NULL,
  description         VARCHAR(250)                             NOT NULL,
  transactionstatusID INTEGER                                  NOT NULL,
  accountID           INTEGER                                  NOT NULL,
  cardID              INTEGER
);

CREATE TABLE transaction_types (
  transactiontypeID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name              VARCHAR(50)                              NOT NULL,
  description       VARCHAR(250)
);

CREATE TABLE transaction_types (
  transactiontypeID INTEGER AUTOINCREMENT PRIMARY KEY UNIQUE NOT NULL,
  name              VARCHAR(50)                              NOT NULL,
  description       VARCHAR(250)
);