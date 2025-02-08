-- Create database
CREATE DATABASE order_management_db;

-- Use the created database
USE order_management_db;

-- Table: country
CREATE TABLE country (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    official_name VARCHAR(250) NOT NULL,
    common_name VARCHAR(250) NOT NULL,
    iso_code CHAR(3) NOT NULL,
    notes VARCHAR(250) NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')) -- Active/Inactive
);

-- Table: client
CREATE TABLE client (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    business_name VARCHAR(260) NOT NULL,
    tax_id CHAR(11) NOT NULL UNIQUE, -- Unique tax identifier
    address VARCHAR(400) NOT NULL,
    phone_number VARCHAR(15) NULL,
    country_id INT NOT NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')), -- Active/Inactive
    CONSTRAINT fk_client_country FOREIGN KEY (country_id) REFERENCES country(country_id)
);

-- Table: vendor
CREATE TABLE vendor (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paternal_surname VARCHAR(150) NOT NULL,
    maternal_surname VARCHAR(150) NULL,
    first_name VARCHAR(250) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')) -- Active/Inactive
);

-- Table: status
CREATE TABLE status_order (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    short_name VARCHAR(60) NOT NULL,
    full_name VARCHAR(260) NOT NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')) -- Active/Inactive
);

-- Table: category
CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    short_name VARCHAR(60) NOT NULL,
    full_name VARCHAR(260) NOT NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')) -- Active/Inactive
);

-- Table: product
CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(360) NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0), -- Ensures positive price
    quantity DECIMAL(10,2) NOT NULL CHECK (quantity >= 0), -- Ensures positive quantity
    category_id INT NOT NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')), -- Active/Inactive
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Table: order_header
CREATE TABLE order_header (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    vendor_id INT NOT NULL,
    gloss VARCHAR(240) NOT NULL,
    order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),
    igv DECIMAL(5,2) NOT NULL CHECK (tax >= 0),
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    status_id INT NOT NULL,
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')), -- Active/Inactive
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES client(client_id),
    CONSTRAINT fk_order_vendor FOREIGN KEY (vendor_id) REFERENCES vendor(vendor_id),
    CONSTRAINT fk_order_status FOREIGN KEY (status_id) REFERENCES status_order(status_id)
);

-- Table: order_detail
CREATE TABLE order_detail (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity DECIMAL(10,2) NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),
    igv DECIMAL(5,2) NOT NULL CHECK (tax >= 0),
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    status CHAR(1) DEFAULT '1' CHECK (status IN ('1', '0')), -- Active/Inactive
    CONSTRAINT fk_order_detail_order FOREIGN KEY (order_id) REFERENCES order_header(order_id),
    CONSTRAINT fk_order_detail_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Add indexes for faster query performance
CREATE INDEX idx_client_country_id ON client(country_id);
CREATE INDEX idx_product_category_id ON product(category_id);
CREATE INDEX idx_order_header_client_id ON order_header(client_id);
CREATE INDEX idx_order_header_vendor_id ON order_header(vendor_id);
CREATE INDEX idx_order_detail_order_id ON order_detail(order_id);

drop view view_orders;
create view view_orders
as
SELECT 
    ohea.id,
    ohea.gloss,
    ohea.order_date,
    ohea.subtotal,
    ohea.igv,
    ohea.total,
    CONCAT(sord.full_name) AS status_order,
    CONCAT(CONCAT(clie.ruc, " - "), clie.business_name) AS client,
    CONCAT(CONCAT(vend.first_name, " "), CONCAT(vend.paternal_surname, " "), vend.maternal_surname) AS vendor,
    COUNT(odet.id) AS items
FROM 
    order_header ohea
    INNER JOIN client clie ON ohea.client_id = clie.id
    INNER JOIN vendor vend ON ohea.vendor_id = vend.id
    INNER JOIN order_detail odet ON ohea.id = odet.order_id
    INNER JOIN status_order sord ON ohea.status_id = sord.id
WHERE 
    ohea.gloss LIKE '%%'
    AND CONCAT(sord.full_name) LIKE '%%'
    AND CONCAT(CONCAT(clie.ruc, " - "), clie.business_name) LIKE '%%'
    AND CONCAT(CONCAT(vend.first_name, " "), CONCAT(vend.paternal_surname, " "), vend.maternal_surname) LIKE '%%'
    AND ohea.status = '1'
    AND odet.status = '1'
GROUP BY
    ohea.id,
    ohea.gloss,
    ohea.order_date,
    ohea.subtotal,
    ohea.igv,
    ohea.total,
    CONCAT(sord.full_name),
    CONCAT(CONCAT(vend.first_name, " "), CONCAT(vend.paternal_surname, " "), vend.maternal_surname),
    CONCAT(CONCAT(clie.ruc, " - "), clie.business_name)
ORDER BY
    ohea.id DESC;
select * from view_orders;
select * from view_orders where gloss like '%v2%';

create table seg_user (
    id int not null,
    name varchar(255) default null unique,
    password varchar(255) default null,
    user_name varchar(255) default null unique,
    primary key (id)
);

create table seg_authority (
    id int not null,
    name varchar(255) default null unique,
    primary key (id)
);

create table seg_user_authority (
    user_id int not null,
    authority_id int not null,
    primary key (user_id, authority_id),
    constraint fk_user_authority_authority foreign key (authority_id) references seg_authority (id),
    constraint fk_user_authority_user foreign key (user_id) references seg_user (id)
);
