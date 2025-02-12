-- Insertar en la tabla category
INSERT INTO category (short_name, full_name) VALUES ('ELEC', 'Electrónica');
INSERT INTO category (short_name, full_name) VALUES ('FOOD', 'Alimentos y Bebidas');
INSERT INTO category (short_name, full_name) VALUES ('TOOLS', 'Herramientas');
INSERT INTO category (short_name, full_name) VALUES ('MED', 'Suministros Médicos');

-- Insertar en la tabla product
INSERT INTO product (name, price, quantity, warranty, category_id, status) VALUES ('Smartphone X1', 199.99, 50,'100%', 1, '1');
INSERT INTO product (name, price, quantity, warranty, category_id, status) VALUES ('Café Orgánico', 12.99, 200,'100%', 2, '1');
INSERT INTO product (name, price, quantity, warranty, category_id, status) VALUES ('Taladro Eléctrico', 89.99, 30,'100%', 3, '1');
INSERT INTO product (name, price, quantity, warranty, category_id, status) VALUES ('Kit de Primeros Auxilios', 29.99, 100,'100%', 4, '1');

-- Insertar en la tabla vendor
INSERT INTO vendor (paternal_surname, maternal_surname, first_name, email, document_type, document_number, status) VALUES ('Smith', 'A.', 'John', 'john.smith@tech.com', 'dni', '08911558', '1');
INSERT INTO vendor (paternal_surname, maternal_surname, first_name, email, document_type, document_number,  status) VALUES ('Doe', 'B.', 'Jane', 'jane.doe@healthcare.com', 'dni', '18457923', '1');
INSERT INTO vendor (paternal_surname, maternal_surname, first_name, email, document_type, document_number, status) VALUES ('Gomez', 'A.', 'Carlos', 'carlos.gomez@agro.com', 'dni', '48159623', '1');
INSERT INTO vendor (paternal_surname, maternal_surname, first_name, email, document_type, document_number,  status) VALUES ('Schmidt', 'A.', 'Hans', 'hans.schmidt@tools.de', 'dni', '15247859', '1');

-- Insertar en la tabla country
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República Argentina', 'Argentina', 'ARG', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República Federativa de Brasil', 'Brasil', 'BRA', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República de Chile', 'Chile', 'CHL', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República de Colombia', 'Colombia', 'COL', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República del Ecuador', 'Ecuador', 'ECU', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('Estado Plurinacional de Bolivia', 'Bolivia', 'BOL', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República del Paraguay', 'Paraguay', 'PRY', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República del Perú', 'Perú', 'PER', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República Oriental del Uruguay', 'Uruguay', 'URY', 'América del Sur', '1');
INSERT INTO country (official_name, common_name, iso_code, notes, status) VALUES ('República Bolivariana de Venezuela', 'Venezuela', 'VEN', 'América del Sur', '1');

-- Insertar en la tabla client
INSERT INTO client (business_name, ruc, address, phone_number, country_id, status) VALUES ('Andes Tech', '10101010101', '123 Calle Andes, Buenos Aires, Argentina', '54111234', 1, '1');
INSERT INTO client (business_name, ruc, address, phone_number, country_id, status) VALUES ('Amazon Agro', '20202020202', '456 Camino Amazonas, São Paulo, Brasil', '55115678', 2, '1');
INSERT INTO client (business_name, ruc, address, phone_number, country_id, status) VALUES ('Minería Atacama', '30303030303', '789 Avenida Desierto, Santiago, Chile', '56129012', 3, '1');
INSERT INTO client (business_name, ruc, address, phone_number, country_id, status) VALUES ('Soluciones Andinas', '40404040404', '321 Avenida Andes, Quito, Ecuador', '59323456', 4, '1');

INSERT INTO status_order (short_name, full_name, status) VALUES ('PEND', 'Pendiente', '1'); -- La orden ha sido creada pero no procesada aún.
INSERT INTO status_order (short_name, full_name, status) VALUES ('PROC', 'Procesando', '1'); -- La orden está en proceso de preparación.
INSERT INTO status_order (short_name, full_name, status) VALUES ('SHIPPED', 'Enviada', '1'); -- La orden ha sido enviada al cliente.
INSERT INTO status_order (short_name, full_name, status) VALUES ('DELIV', 'Entregada', '1'); -- La orden ha sido entregada al cliente.
INSERT INTO status_order (short_name, full_name, status) VALUES ('CANCEL', 'Cancelada', '1'); -- La orden fue cancelada por el cliente o el vendedor.
INSERT INTO status_order (short_name, full_name, status) VALUES ('RETURN', 'Devuelta', '1'); -- La orden fue devuelta por el cliente.
INSERT INTO status_order (short_name, full_name, status) VALUES ('REFUND', 'Reembolsada', '1'); -- Se realizó un reembolso por la orden.
INSERT INTO status_order (short_name, full_name, status) VALUES ('HOLD', 'En espera', '1'); -- La orden está en espera por algún problema (por ejemplo, falta de pago).
INSERT INTO status_order (short_name, full_name, status) VALUES ('PAID', 'Pagada', '1'); -- La orden ha sido pagada completamente.

INSERT INTO seg_authority(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO seg_authority(id, name) VALUES (2, 'ROLE_USER');

INSERT INTO seg_user (id, user_name, password, name) VALUES (1, 'admin', '$2a$10$d9m3ly9wsppztzaenfinguwr/rgp1bfdxmqguwd/wenz3000d', 'administrator');
INSERT INTO seg_user (id, user_name, password, name) VALUES (2, 'demo', '$2a$10$d9m3ly9wsppztzaenfinguwr/rgp1bfdxmqguwd/wenz3000d', 'demo');

INSERT INTO seg_user_authority(user_id, authority_id) VALUES (1, 1);
INSERT INTO seg_user_authority(user_id, authority_id) VALUES (1, 2);
INSERT INTO seg_user_authority(user_id, authority_id) VALUES (2, 2);

-- INSERT INTO order_header(id, igv, subtotal, total, client_id, order_date, status_id, vendor_id, gloss, status) VALUES (1, 126.00, 825.99);

