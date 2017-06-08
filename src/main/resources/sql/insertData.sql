INSERT INTO account_type (name, description) VALUES ('testName', 'testDescription');
INSERT INTO account_type (name, description) VALUES ('saving', 'savingDescription');
INSERT INTO transaction_types (name, description) VALUES ('testName', 'testDescription');
INSERT INTO transaction_types (name, description) VALUES ('transfer', 'transferDescription');
INSERT INTO account_status (name, description) VALUES ('testName', 'testDescription');
INSERT INTO account_status (name, description) VALUES ('blocked', 'blockedDescription');
INSERT INTO transaction_statuses (name, description) VALUES ('testName', 'testDescription');
INSERT INTO transaction_statuses (name, description) VALUES ('pending', 'pendingDescription');
INSERT INTO customers (firstname, lastname, login, password, createdate, isactive, lastlogin)
VALUES ('Jan', 'Kowalski', 'janko', '12345', 1496926140, 1, 1496926140);