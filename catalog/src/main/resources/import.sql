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

-- Authors (Persons + Authors due to JOINED inheritance)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (1, 'Stephen', 'King', 'American author of horror, supernatural fiction, suspense, and fantasy novels.', '1947-09-21', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (2, 'J.K.', 'Rowling', 'British author best known for the Harry Potter fantasy series.', '1965-07-31', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (3, 'George', 'Orwell', 'English novelist, essayist, and critic famous for 1984 and Animal Farm.', '1903-06-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (4, 'Gabriel', 'García Márquez', 'Colombian novelist and Nobel Prize winner, pioneer of magical realism.', '1927-03-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (5, 'Haruki', 'Murakami', 'Japanese writer known for his surrealist and postmodern fiction.', '1949-01-12', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (6, 'Victor', 'Hugo', 'French poet, novelist, and dramatist of the Romantic movement.', '1802-02-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (7, 'Jane', 'Austen', 'English novelist known for her social commentary and wit.', '1775-12-16', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (8, 'Franz', 'Kafka', 'German-speaking Bohemian novelist known for surrealist works.', '1883-07-03', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (9, 'Paulo', 'Coelho', 'Brazilian lyricist and novelist, author of The Alchemist.', '1947-08-24', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (10, 'Umberto', 'Eco', 'Italian medievalist, philosopher, and novelist.', '1932-01-05', CURRENT_TIMESTAMP);

INSERT INTO t_authors (id, preferred_language, website) VALUES (1, 'ENGLISH', 'https://stephenking.com');
INSERT INTO t_authors (id, preferred_language, website) VALUES (2, 'ENGLISH', 'https://www.jkrowling.com');
INSERT INTO t_authors (id, preferred_language, website) VALUES (3, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website) VALUES (4, 'SPANISH', NULL);
INSERT INTO t_authors (id, preferred_language, website) VALUES (5, 'JAPANESE', 'https://www.harukimurakami.com');
INSERT INTO t_authors (id, preferred_language, website) VALUES (6, 'FRENCH', NULL);
INSERT INTO t_authors (id, preferred_language, website) VALUES (7, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website) VALUES (8, 'GERMAN', NULL);
INSERT INTO t_authors (id, preferred_language, website) VALUES (9, 'PORTUGUESE', 'https://paulocoelhoblog.com');
INSERT INTO t_authors (id, preferred_language, website) VALUES (10, 'ITALIAN', NULL);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_persons_seq RESTART WITH 11;
