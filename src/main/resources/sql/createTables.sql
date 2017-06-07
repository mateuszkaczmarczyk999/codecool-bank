CREATE TABLE IF NOT EXISTS cards (
  cardID            INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  cardnumber        VARCHAR(50)                              NOT NULL,
  cardtypeID        INTEGER                                  NOT NULL,
  validity          DATETIME                                 NOT NULL,
  buyinglimit       BIGINT                                   NOT NULL,
  cashwithdrawlimit BIGINT                                   NOT NULL,
  cardlimit         BIGINT,
  accountID         INTEGER                                  NOT NULL
);

CREATE TABLE IF NOT EXISTS card_types (
  cardtypeID  INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name        VARCHAR(50)                              NOT NULL,
  description VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS account_status (
  accountstatusID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name            VARCHAR(50)                              NOT NULL,
  description     VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS accounts (
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

CREATE TABLE IF NOT EXISTS account_type (
  accounttypeID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name          VARCHAR(50)                              NOT NULL,
  description   VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS customers (
  customerID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  firstname  VARCHAR(50)                              NOT NULL,
  lastname   VARCHAR(50)                              NOT NULL,
  login      VARCHAR(50)                              NOT NULL,
  password   VARCHAR(100)                             NOT NULL,
  createdate DATETIME                                 NOT NULL,
  isactive   BOOLEAN                                  NOT NULL,
  lastlogin  DATETIME
);

CREATE TABLE IF NOT EXISTS transactions (
  transactionID        INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  dateoftransaction    DATETIME                                 NOT NULL,
  transactiontypeID    INTEGER                                  NOT NULL,
  value                BIGINT                                   NOT NULL,
  description          VARCHAR(250)                             NOT NULL,
  transactionstatusID  INTEGER                                  NOT NULL,
  sourceaccountID      INTEGER                                  NOT NULL,
  sourcecardID         INTEGER,
  destinationaccountID INTEGER                                  NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction_types (
  transactiontypeID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name              VARCHAR(50)                              NOT NULL,
  description       VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS transaction_statuses (
  transactionstatusID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name                TEXT                                     NOT NULL,
  description         TEXT
);