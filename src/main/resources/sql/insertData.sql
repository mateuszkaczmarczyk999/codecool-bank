INSERT INTO account_type (name, description) VALUES ('Saving Account', 'Saving Description');
INSERT INTO account_type (name, description) VALUES ('Credit Account', 'Credit Description');
INSERT INTO transaction_types (name, description) VALUES ('testName', 'testDescription');
INSERT INTO transaction_types (name, description) VALUES ('transfer', 'transferDescription');
INSERT INTO account_status (name, description) VALUES ('Valid', 'Valid Description');
INSERT INTO account_status (name, description) VALUES ('Invalid', 'Invalid Description');
INSERT INTO transaction_statuses (name, description) VALUES ('testName', 'testDescription');
INSERT INTO transaction_statuses (name, description) VALUES ('pending', 'pendingDescription');
INSERT INTO customers (firstname, lastname, login, password, createdate, isactive, lastlogin)
VALUES ('Jan', 'Kowalski', 'janko', '12345', 1496926140, 1, 1496926140);
INSERT INTO accounts (customerID, number, accounttypeID, accountstatusID, opendate, balance, debitline, interest)
VALUES (1, '00 9999 8888', 1, 1, 1496926140, 10000, 1000, 10);