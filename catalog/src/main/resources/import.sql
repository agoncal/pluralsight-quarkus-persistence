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

-- Musicians (Persons + Musicians due to JOINED inheritance)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (11, 'John', 'Lennon', 'English singer, songwriter, and peace activist who co-founded the Beatles.', '1940-10-09', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (12, 'Freddie', 'Mercury', 'British singer and songwriter, lead vocalist of Queen.', '1946-09-05', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (13, 'David', 'Bowie', 'English singer-songwriter and actor, pioneer of glam rock.', '1947-01-08', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (14, 'Miles', 'Davis', 'American jazz trumpeter, bandleader, and composer.', '1926-05-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (15, 'Ella', 'Fitzgerald', 'American jazz singer known as the First Lady of Song.', '1917-04-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (16, 'Bob', 'Dylan', 'American singer-songwriter and Nobel Prize winner in Literature.', '1941-05-24', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (17, 'Aretha', 'Franklin', 'American singer known as the Queen of Soul.', '1942-03-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (18, 'Jimi', 'Hendrix', 'American guitarist, singer, and songwriter.', '1942-11-27', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (19, 'Nina', 'Simone', 'American singer, songwriter, and civil rights activist.', '1933-02-21', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date) VALUES (20, 'Ludwig', 'van Beethoven', 'German composer and pianist of the Classical and Romantic eras.', '1770-12-17', CURRENT_TIMESTAMP);

INSERT INTO t_musicians (id, stage_name, instrument) VALUES (11, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (12, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (13, 'Ziggy Stardust', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (14, NULL, 'Trumpet');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (15, 'Lady Ella', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (16, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (17, 'Queen of Soul', 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (18, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (19, 'High Priestess of Soul', 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (20, NULL, 'Piano');

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_persons_seq RESTART WITH 21;
