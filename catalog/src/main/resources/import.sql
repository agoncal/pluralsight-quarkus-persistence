-- Publishers
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (1, 'Penguin Random House', 'USA', 'https://www.penguinrandomhouse.com', 1927, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (2, 'HarperCollins', 'USA', 'https://www.harpercollins.com', 1989, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (3, 'Simon & Schuster', 'USA', 'https://www.simonandschuster.com', 1924, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (4, 'Hachette Livre', 'France', 'https://www.hachette.com', 1826, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (5, 'Macmillan Publishers', 'UK', 'https://www.macmillan.com', 1843, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (6, 'Scholastic', 'USA', 'https://www.scholastic.com', 1920, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (7, 'Pearson', 'UK', 'https://www.pearson.com', 1844, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (8, 'Wiley', 'USA', 'https://www.wiley.com', 1807, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (9, 'Oxford University Press', 'UK', 'https://www.oup.com', 1586, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date) VALUES (10, 'Bloomsbury', 'UK', 'https://www.bloomsbury.com', 1986, CURRENT_TIMESTAMP);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_publishers_seq RESTART WITH 11;
