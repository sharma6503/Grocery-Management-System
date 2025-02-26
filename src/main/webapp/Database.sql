CREATE TABLE customer (
	customer_id Integer primary key  AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    phoneNumber  varchar (10) not null,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL CHECK (length(password)>=8)
);

CREATE TABLE Admin (
	admin_id Integer primary key   AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    phoneNumber  varchar(10) not null ,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL CHECK (length(password)>=8)
);

CREATE TABLE Product (
    product_id INT PRIMARY KEY,  -- Integer, primary key (unique identifier)
    name VARCHAR(255) NOT NULL unique, -- String, can't be null
    price DECIMAL(10, 2) NOT NULL, -- Decimal, 10 digits total, 2 decimal places.  Suitable for monetary values.
    quantity INT NOT NULL CHECK (quantity >= 0) -- Integer, can't be null, and must be non-negative.
);

INSERT INTO Product (product_id, name, price, quantity)
VALUES (1, 'Laptop', 1200.99, 50);

-- Inserting another product
INSERT INTO Product (product_id, name, price, quantity)
VALUES (2, 'Wireless Mouse', 25.50, 100);

-- Inserting a product with a quantity of zero
INSERT INTO Product (product_id, name, price, quantity)
VALUES (3, 'Keyboard', 75.00, 0);

truncate table cart
select * from cart;

CREATE TABLE Cart (
    cart_id INTEGER PRIMARY KEY AUTOINCREMENT,
    customer_id INTEGER,
    product_id INTEGER,
    p_name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1,
    price DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE Orders (
    order_id INTEGER PRIMARY KEY AUTOINCREMENT,
    customer_id INTEGER,
    product_id INTEGER,
    p_name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1,
    price DECIMAL(10, 2),
    order_time TEXT DEFAULT (DATE('now')),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

drop table customer


SELECT ci.product_id,p.name,ci.quantity,ci.price_per_unit
from cart_items ci join product p on
p.product_id=ci.product_id join cart ca 
on ca.cart_id=ci.cart_id join customer c 
on c.customer_id=ca.customer_id   where c.customer_id=? ;

drop table ACCOUNT
select * from orders 

