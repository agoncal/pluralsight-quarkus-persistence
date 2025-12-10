-- Users (using IDENTITY strategy, so we let the database auto-generate IDs)
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('admin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'admin@vintagestore.com', 'ADMIN', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('manager', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'manager@vintagestore.com', 'MANAGER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('johndoe', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'john.doe@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('janesmith', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'jane.smith@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('bobwilson', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'bob.wilson@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('alicejones', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'alice.jones@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('charlieB', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'charlie.brown@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('dianamiller', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'diana.miller@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('evandavis', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'evan.davis@email.com', 'CUSTOMER', false, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date)
VALUES ('fionagarcia', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'fiona.garcia@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);

-- Customers (linked to users, using IDENTITY strategy)
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Admin', 'User', '+1-555-000-0001', '1 Admin Plaza', 'New York', 'NY', '10001', 'USA', '1 Admin Plaza', 'New York', 'NY', '10001', 'USA', 1,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Manager', 'User', '+1-555-000-0002', '2 Manager Ave', 'Los Angeles', 'CA', '90001', 'USA', '2 Manager Ave', 'Los Angeles', 'CA', '90001', 'USA', 2,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('John', 'Doe', '+1-555-123-4567', '123 Main Street', 'Chicago', 'IL', '60601', 'USA', '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', 3,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Jane', 'Smith', '+1-555-234-5678', '789 Elm Road', 'Houston', 'TX', '77001', 'USA', '789 Elm Road', 'Houston', 'TX', '77001', 'USA', 4,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Bob', 'Wilson', '+1-555-345-6789', '321 Pine Lane', 'Phoenix', 'AZ', '85001', 'USA', '654 Maple Drive', 'Phoenix', 'AZ', '85002', 'USA', 5,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Alice', 'Jones', '+1-555-456-7890', '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA', '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA',
        6, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Charlie', 'Brown', '+1-555-567-8901', '890 Birch Boulevard', 'San Antonio', 'TX', '78201', 'USA', '890 Birch Boulevard', 'San Antonio', 'TX', '78201',
        'USA', 7, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Diana', 'Miller', '+1-555-678-9012', '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', 8,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Evan', 'Davis', '+1-555-789-0123', '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', 9,
        CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street,
                         shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date)
VALUES ('Fiona', 'Garcia', '+1-555-890-1234', '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', 10,
        CURRENT_TIMESTAMP);

-- Purchase Orders (linked to customers, using IDENTITY strategy)
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-01-15 10:30:00', 'DELIVERED', 29.98, 3, '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-01-20 14:45:00', 'DELIVERED', 42.97, 4, '789 Elm Road', 'Houston', 'TX', '77001', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-02-05 09:15:00', 'SHIPPED', 27.98, 5, '654 Maple Drive', 'Phoenix', 'AZ', '85002', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-02-10 16:20:00', 'CONFIRMED', 55.96, 3, '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-02-15 11:00:00', 'PENDING', 14.99, 6, '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-02-20 13:30:00', 'DELIVERED', 38.97, 7, '890 Birch Boulevard', 'San Antonio', 'TX', '78201', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-03-01 08:45:00', 'SHIPPED', 25.98, 8, '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-03-05 15:10:00', 'CANCELLED', 16.99, 9, '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-03-10 10:00:00', 'CONFIRMED', 47.97, 10, '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country,
                               created_date)
VALUES ('2024-03-15 12:30:00', 'PENDING', 31.98, 4, '789 Elm Road', 'Houston', 'TX', '77001', 'USA', CURRENT_TIMESTAMP);

-- Suppliers
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (1, 'Acme Publishing', 'John Smith', 'john.smith@acmepub.com', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (2, 'Global Music Distribution', 'Maria Garcia', 'maria@globalmusicdist.com', 'Spain', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (3, 'Nordic Books AB', 'Erik Johansson', 'erik@nordicbooks.se', 'Sweden', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (4, 'Tokyo Media Corp', 'Yuki Tanaka', 'y.tanaka@tokyomedia.jp', 'Japan', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (5, 'British Publishing Ltd', 'James Wilson', 'jwilson@britishpub.co.uk', 'UK', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (6, 'Vinyl Records Inc', 'Sarah Johnson', 'sarah@vinylrecords.com', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (7, 'Deutsche Medien GmbH', 'Hans Mueller', 'h.mueller@deutschemedien.de', 'Germany', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (8, 'Paris Entertainment SA', 'Sophie Dubois', 'sophie@parisentertainment.fr', 'France', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (9, 'Sydney Distributors Pty', 'Michael Brown', 'mbrown@sydneydist.com.au', 'Australia', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (id, company_name, contact_name, contact_email, country, created_date)
VALUES (10, 'Maple Leaf Media', 'Jennifer Taylor', 'jtaylor@mapleleafmedia.ca', 'Canada', CURRENT_TIMESTAMP);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_suppliers_seq RESTART WITH 11;
