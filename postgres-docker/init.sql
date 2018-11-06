CREATE TABLE Customer (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE Invoice (
  id BIGSERIAL PRIMARY KEY,
  due DATE,
  customerId BIGINT REFERENCES Customer(id)
);

CREATE TABLE InvoiceLine (
  id BIGSERIAL PRIMARY KEY,
  description VARCHAR(255),
  price DECIMAL,
  itemCount INT,
  invoiceId BIGINT REFERENCES Invoice(id)
);