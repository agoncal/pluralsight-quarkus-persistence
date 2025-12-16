-- Publishers
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (1, 'Penguin Random House', 'USA', 'https://www.penguinrandomhouse.com', 1927, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (2, 'HarperCollins', 'USA', 'https://www.harpercollins.com', 1989, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (3, 'Simon & Schuster', 'USA', 'https://www.simonandschuster.com', 1924, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (4, 'Hachette Livre', 'France', 'https://www.hachette.com', 1826, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (5, 'Macmillan Publishers', 'UK', 'https://www.macmillan.com', 1843, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (6, 'Scholastic', 'USA', 'https://www.scholastic.com', 1920, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (7, 'Pearson', 'UK', 'https://www.pearson.com', 1844, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (8, 'Wiley', 'USA', 'https://www.wiley.com', 1807, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (9, 'Oxford University Press', 'UK', 'https://www.oup.com', 1586, CURRENT_TIMESTAMP);
INSERT INTO t_publishers (id, name, country, website, founded_year, created_date)
VALUES (10, 'Bloomsbury', 'UK', 'https://www.bloomsbury.com', 1986, CURRENT_TIMESTAMP);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_publishers_seq RESTART WITH 11;

-- Authors (Persons + Authors due to JOINED inheritance)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (1, 'Stephen', 'King', 'American author of horror, supernatural fiction, suspense, and fantasy novels.', '1947-09-21', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (2, 'J.K.', 'Rowling', 'British author best known for the Harry Potter fantasy series.', '1965-07-31', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (3, 'George', 'Orwell', 'English novelist, essayist, and critic famous for 1984 and Animal Farm.', '1903-06-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (4, 'Gabriel', 'García Márquez', 'Colombian novelist and Nobel Prize winner, pioneer of magical realism.', '1927-03-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (5, 'Haruki', 'Murakami', 'Japanese writer known for his surrealist and postmodern fiction.', '1949-01-12', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (6, 'Victor', 'Hugo', 'French poet, novelist, and dramatist of the Romantic movement.', '1802-02-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (7, 'Jane', 'Austen', 'English novelist known for her social commentary and wit.', '1775-12-16', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (8, 'Franz', 'Kafka', 'German-speaking Bohemian novelist known for surrealist works.', '1883-07-03', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (9, 'Paulo', 'Coelho', 'Brazilian lyricist and novelist, author of The Alchemist.', '1947-08-24', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (10, 'Umberto', 'Eco', 'Italian medievalist, philosopher, and novelist.', '1932-01-05', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (11, 'John', 'Lennon', 'English singer, songwriter, and peace activist who co-founded the Beatles.', '1940-10-09', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (12, 'Freddie', 'Mercury', 'British singer and songwriter, lead vocalist of Queen.', '1946-09-05', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (13, 'David', 'Bowie', 'English singer-songwriter and actor, pioneer of glam rock.', '1947-01-08', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (14, 'Miles', 'Davis', 'American jazz trumpeter, bandleader, and composer.', '1926-05-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (15, 'Ella', 'Fitzgerald', 'American jazz singer known as the First Lady of Song.', '1917-04-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (16, 'Bob', 'Dylan', 'American singer-songwriter and Nobel Prize winner in Literature.', '1941-05-24', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (17, 'Aretha', 'Franklin', 'American singer known as the Queen of Soul.', '1942-03-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (18, 'Jimi', 'Hendrix', 'American guitarist, singer, and songwriter.', '1942-11-27', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (19, 'Nina', 'Simone', 'American singer, songwriter, and civil rights activist.', '1933-02-21', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (20, 'Ludwig', 'van Beethoven', 'German composer and pianist of the Classical and Romantic eras.', '1770-12-17', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (21, 'Paul', 'McCartney', 'English singer, songwriter, and bassist for the Beatles.', '1942-06-18', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (22, 'George', 'Harrison', 'English musician and lead guitarist of the Beatles.', '1943-02-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (23, 'Ringo', 'Starr', 'English musician and drummer for the Beatles.', '1940-07-07', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (24, 'Brian', 'May', 'English musician and lead guitarist of Queen.', '1947-07-19', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (25, 'Roger', 'Taylor', 'English musician and drummer for Queen.', '1949-07-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (26, 'John', 'Deacon', 'English musician and bassist for Queen.', '1951-08-19', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (27, 'John', 'Coltrane', 'American jazz saxophonist and composer.', '1926-09-23', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (28, 'Bill', 'Evans', 'American jazz pianist and composer.', '1929-08-16', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (29, 'Cannonball', 'Adderley', 'American jazz alto saxophonist.', '1928-09-15', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (30, 'Louis', 'Armstrong', 'American trumpeter and vocalist, one of the most influential figures in jazz.', '1901-08-04', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (31, 'Noel', 'Redding', 'English musician, bassist for the Jimi Hendrix Experience.', '1945-12-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (32, 'Mitch', 'Mitchell', 'English drummer for the Jimi Hendrix Experience.', '1946-07-09', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (33, 'Terry', 'Pratchett', 'English author known for his Discworld fantasy series.', '1948-04-28', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (34, 'Neil', 'Gaiman', 'English author of novels, comics, and films including Sandman and American Gods.', '1960-11-10', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (35, 'Douglas', 'Adams', 'English author and humorist, creator of The Hitchhiker''s Guide to the Galaxy.', '1952-03-11', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (36, 'Isaac', 'Asimov', 'American writer and professor, prolific author of science fiction.', '1920-01-02', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (37, 'Agatha', 'Christie', 'English writer known as the Queen of Crime for her detective novels.', '1890-09-15', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (38, 'Ernest', 'Hemingway', 'American novelist and journalist, Nobel Prize winner in Literature.', '1899-07-21', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (39, 'Larry', 'Niven', 'American science fiction author known for Ringworld.', '1938-04-30', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (40, 'Jerry', 'Pournelle', 'American science fiction writer and journalist.', '1933-08-07', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (41, 'William', 'Gibson', 'American-Canadian author, pioneer of the cyberpunk genre.', '1948-03-17', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (42, 'Bruce', 'Sterling', 'American science fiction author and futurist.', '1954-04-14', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (43, 'Michael', 'Jackson', 'American singer and dancer known as the King of Pop.', '1958-08-29', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (44, 'Roger', 'Waters', 'English musician, co-founder and bassist of Pink Floyd.', '1943-09-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (45, 'David', 'Gilmour', 'English guitarist and vocalist of Pink Floyd.', '1946-03-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (46, 'Stevie', 'Nicks', 'American singer-songwriter, vocalist of Fleetwood Mac.', '1948-05-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (47, 'Lindsey', 'Buckingham', 'American guitarist and vocalist of Fleetwood Mac.', '1949-10-03', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (48, 'Angus', 'Young', 'Australian guitarist and co-founder of AC/DC.', '1955-03-31', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (49, 'Brian', 'Johnson', 'English singer, lead vocalist of AC/DC since 1980.', '1947-10-05', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (50, 'Bob', 'Marley', 'Jamaican singer-songwriter, pioneer of reggae music.', '1945-02-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (51, 'Marvin', 'Gaye', 'American singer-songwriter known as the Prince of Soul.', '1939-04-02', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (52, 'Lou', 'Reed', 'American musician, guitarist and vocalist of The Velvet Underground.', '1942-03-02', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (53, 'Christa', 'Päffgen', 'German singer, songwriter, and model known as Nico, collaborated with Velvet Underground.', '1938-10-16', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (54, 'Joni', 'Mitchell', 'Canadian singer-songwriter known for confessional lyrics.', '1943-11-07', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (55, 'Brian', 'Wilson', 'American musician, co-founder and leader of The Beach Boys.', '1942-06-20', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (56, 'Mike', 'Love', 'American singer, co-founder and vocalist of The Beach Boys.', '1941-03-15', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (57, 'Robert', 'Plant', 'English singer, lead vocalist of Led Zeppelin.', '1948-08-20', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (58, 'Jimmy', 'Page', 'English guitarist, founder of Led Zeppelin.', '1944-01-09', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (59, 'John Paul', 'Jones', 'English musician, bassist and keyboardist of Led Zeppelin.', '1946-01-03', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (60, 'John', 'Bonham', 'English drummer for Led Zeppelin, considered one of the greatest rock drummers.', '1948-05-31', CURRENT_TIMESTAMP);


INSERT INTO t_authors (id, preferred_language, website)
VALUES (1, 'ENGLISH', 'https://stephenking.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (2, 'ENGLISH', 'https://www.jkrowling.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (3, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (4, 'SPANISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (5, 'JAPANESE', 'https://www.harukimurakami.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (6, 'FRENCH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (7, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (8, 'GERMAN', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (9, 'PORTUGUESE', 'https://paulocoelhoblog.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (10, 'ITALIAN', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (33, 'ENGLISH', 'https://www.terrypratchettbooks.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (34, 'ENGLISH', 'https://www.neilgaiman.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (35, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (36, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (37, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (38, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (39, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (40, 'ENGLISH', NULL);
INSERT INTO t_authors (id, preferred_language, website)
VALUES (41, 'ENGLISH', 'https://www.williamgibsonbooks.com');
INSERT INTO t_authors (id, preferred_language, website)
VALUES (42, 'ENGLISH', NULL);


INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (11, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (12, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (13, 'Ziggy Stardust', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (14, NULL, 'Trumpet');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (15, 'Lady Ella', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (16, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (17, 'Queen of Soul', 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (18, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (19, 'High Priestess of Soul', 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (20, NULL, 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (21, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (22, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (23, NULL, 'Drums');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (24, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (25, NULL, 'Drums');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (26, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (27, 'Trane', 'Saxophone');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (28, NULL, 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (29, NULL, 'Saxophone');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (30, 'Satchmo', 'Trumpet');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (31, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (32, NULL, 'Drums');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (43, 'King of Pop', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (44, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (45, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (46, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (47, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (48, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (49, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (50, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (51, 'Prince of Soul', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (52, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (53, 'Nico', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (54, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (55, NULL, 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (56, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (57, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (58, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (59, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument)
VALUES (60, 'Bonzo', 'Drums');

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_persons_seq RESTART WITH 61;

-- Books (Items with dtype='BOOK')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (1, 'BOOK', 'The Shining', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence.', 14.99, 50, CURRENT_TIMESTAMP, '9780385121675', 447, '1977-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (2, 'BOOK', 'Harry Potter and the Philosopher''s Stone', 'A young wizard discovers his magical heritage and attends Hogwarts School of Witchcraft and Wizardry.', 12.99, 100, CURRENT_TIMESTAMP, '9780747532699', 223, '1997-06-26', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (3, 'BOOK', '1984', 'A dystopian novel set in a totalitarian society under constant surveillance.', 11.99, 75, CURRENT_TIMESTAMP, '9780451524935', 328, '1949-06-08', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (4, 'BOOK', 'One Hundred Years of Solitude', 'The multi-generational story of the Buendía family in the fictional town of Macondo.', 15.99, 40, CURRENT_TIMESTAMP, '9780060883287', 417, '1967-05-30', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (5, 'BOOK', 'Norwegian Wood', 'A nostalgic story of loss and sexuality set in Tokyo during the late 1960s.', 13.99, 0, CURRENT_TIMESTAMP, '9780375704024', 296, '1987-09-04', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (6, 'BOOK', 'Les Misérables', 'An epic tale of redemption, justice, and love in 19th century France.', 18.99, 30, CURRENT_TIMESTAMP, '9780140444308', 1232, '1862-01-01', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (7, 'BOOK', 'Pride and Prejudice', 'A witty commentary on society and romance in Regency-era England.', 9.99, 80, CURRENT_TIMESTAMP, '9780141439518', 279, '1813-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (8, 'BOOK', 'The Metamorphosis', 'A traveling salesman wakes up one morning to find himself transformed into a giant insect.', 8.99, 0, CURRENT_TIMESTAMP, '9780805209991', 55, '1915-01-01', 'GERMAN', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (9, 'BOOK', 'The Alchemist', 'A philosophical story about a shepherd boy who travels in search of treasure and discovers the true meaning of life.', 14.99, 120, CURRENT_TIMESTAMP, '9780061122415', 208, '1988-01-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (10, 'BOOK', 'The Name of the Rose', 'A medieval murder mystery set in an Italian monastery.', 16.99, 35, CURRENT_TIMESTAMP, '9780151446476', 536, '1980-01-01', 'ITALIAN', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (21, 'BOOK', 'Good Omens', 'The Nice and Accurate Prophecies of Agnes Nutter, Witch. A comedic novel about the apocalypse.', 14.99, 65, CURRENT_TIMESTAMP, '9780060853983', 400, '1990-05-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (22, 'BOOK', 'The Hitchhiker''s Guide to the Galaxy', 'A comedic science fiction series following the misadventures of Arthur Dent.', 12.99, 90, CURRENT_TIMESTAMP, '9780345391803', 224, '1979-10-12', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (23, 'BOOK', 'Foundation', 'The first novel in the Foundation series, about the fall and rise of galactic civilization.', 15.99, 55, CURRENT_TIMESTAMP, '9780553293357', 244, '1951-06-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (24, 'BOOK', 'Murder on the Orient Express', 'Hercule Poirot investigates a murder aboard the famous train.', 11.99, 0, CURRENT_TIMESTAMP, '9780062693662', 256, '1934-01-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (25, 'BOOK', 'The Old Man and the Sea', 'The story of an aging Cuban fisherman and his epic battle with a giant marlin.', 10.99, 85, CURRENT_TIMESTAMP, '9780684801223', 128, '1952-09-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (26, 'BOOK', 'The Mote in God''s Eye', 'First contact with an alien species leads to complex political and military dilemmas.', 16.99, 40, CURRENT_TIMESTAMP, '9780671741921', 537, '1974-01-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (27, 'BOOK', 'Neuromancer', 'A washed-up computer hacker is hired for one last job in this cyberpunk classic.', 13.99, 60, CURRENT_TIMESTAMP, '9780441569595', 271, '1984-07-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (28, 'BOOK', 'The Difference Engine', 'An alternate history where Charles Babbage''s mechanical computer was built in Victorian England.', 14.99, 45, CURRENT_TIMESTAMP, '9780440423621', 429, '1990-02-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (29, 'BOOK', 'American Gods', 'A modern fantasy about old gods struggling to survive in contemporary America.', 15.99, 0, CURRENT_TIMESTAMP, '9780063081918', 541, '2001-06-19', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (30, 'BOOK', 'Small Gods', 'A Discworld novel satirizing religion and belief.', 12.99, 50, CURRENT_TIMESTAMP, '9780062237378', 389, '1992-01-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (41, 'BOOK', 'It', 'A group of children face a terrifying evil in their small town.', 17.99, 45, CURRENT_TIMESTAMP, '9781501142970', 1138, '1986-09-15', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (42, 'BOOK', 'The Stand', 'A post-apocalyptic tale of good versus evil after a pandemic.', 18.99, 35, CURRENT_TIMESTAMP, '9780307743688', 1153, '1978-10-03', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (43, 'BOOK', 'Misery', 'A famous author is held captive by his number one fan.', 13.99, 55, CURRENT_TIMESTAMP, '9781501143106', 338, '1987-06-08', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (44, 'BOOK', 'Harry Potter and the Chamber of Secrets', 'Harry returns to Hogwarts where a dark force threatens students.', 12.99, 0, CURRENT_TIMESTAMP, '9780439064873', 341, '1998-07-02', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (45, 'BOOK', 'Harry Potter and the Prisoner of Azkaban', 'A dangerous prisoner escapes and seems to be hunting Harry.', 13.99, 90, CURRENT_TIMESTAMP, '9780439136365', 435, '1999-07-08', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (46, 'BOOK', 'Harry Potter and the Goblet of Fire', 'Harry competes in a dangerous magical tournament.', 14.99, 85, CURRENT_TIMESTAMP, '9780439139601', 734, '2000-07-08', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (47, 'BOOK', 'Animal Farm', 'A satirical allegory about power and corruption.', 9.99, 110, CURRENT_TIMESTAMP, '9780451526342', 112, '1945-08-17', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (48, 'BOOK', 'Kafka on the Shore', 'A surreal tale of a runaway teenager and an elderly man.', 15.99, 50, CURRENT_TIMESTAMP, '9781400079278', 467, '2002-09-12', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (49, 'BOOK', '1Q84', 'A love story set in a parallel universe Tokyo.', 19.99, 40, CURRENT_TIMESTAMP, '9780307593313', 1157, '2009-05-29', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (50, 'BOOK', 'The Hunchback of Notre-Dame', 'A tragic tale set in medieval Paris around the famous cathedral.', 12.99, 0, CURRENT_TIMESTAMP, '9780140443530', 560, '1831-01-14', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (51, 'BOOK', 'Sense and Sensibility', 'Two sisters navigate love and society in Regency England.', 10.99, 70, CURRENT_TIMESTAMP, '9780141439662', 352, '1811-10-30', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (52, 'BOOK', 'Emma', 'A young woman meddles in the romantic lives of others.', 11.99, 65, CURRENT_TIMESTAMP, '9780141439587', 474, '1815-12-23', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (53, 'BOOK', 'The Trial', 'A man is arrested and prosecuted by an inaccessible authority.', 11.99, 55, CURRENT_TIMESTAMP, '9780805209990', 255, '1925-04-26', 'GERMAN', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (54, 'BOOK', 'The Castle', 'A land surveyor struggles to gain access to a mysterious castle.', 13.99, 45, CURRENT_TIMESTAMP, '9780805211061', 316, '1926-01-01', 'GERMAN', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (55, 'BOOK', 'Brida', 'A young Irish girl searches for knowledge and her destiny.', 12.99, 80, CURRENT_TIMESTAMP, '9780061578953', 249, '1990-01-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (56, 'BOOK', 'Veronika Decides to Die', 'A young woman wakes up in a mental hospital after a suicide attempt.', 13.99, 0, CURRENT_TIMESTAMP, '9780061124266', 210, '1998-08-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (57, 'BOOK', 'Foucaults Pendulum', 'Three editors create a conspiracy theory that takes on a life of its own.', 17.99, 35, CURRENT_TIMESTAMP, '9780156032971', 641, '1988-10-01', 'ITALIAN', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (58, 'BOOK', 'Love in the Time of Cholera', 'A love story spanning more than fifty years.', 14.99, 55, CURRENT_TIMESTAMP, '9780307389732', 348, '1985-01-01', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (59, 'BOOK', 'Chronicle of a Death Foretold', 'A murder is reconstructed through multiple perspectives.', 11.99, 75, CURRENT_TIMESTAMP, '9781400034710', 120, '1981-01-01', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (60, 'BOOK', 'Colour of Magic', 'The first Discworld novel following the wizard Rincewind.', 11.99, 65, CURRENT_TIMESTAMP, '9780062225672', 288, '1983-11-24', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (61, 'BOOK', 'Mort', 'Death takes on an apprentice in this Discworld comedy.', 12.99, 60, CURRENT_TIMESTAMP, '9780062225719', 272, '1987-11-12', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (62, 'BOOK', 'Coraline', 'A young girl discovers a sinister parallel world.', 10.99, 85, CURRENT_TIMESTAMP, '9780380807345', 162, '2002-07-02', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (63, 'BOOK', 'The Graveyard Book', 'A boy raised by ghosts in a cemetery.', 11.99, 75, CURRENT_TIMESTAMP, '9780060530945', 312, '2008-09-30', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (64, 'BOOK', 'Restaurant at the End of the Universe', 'The sequel to Hitchhikers Guide.', 12.99, 0, CURRENT_TIMESTAMP, '9780345391810', 250, '1980-10-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (65, 'BOOK', 'Life, the Universe and Everything', 'Third book in the Hitchhikers trilogy.', 12.99, 65, CURRENT_TIMESTAMP, '9780345391827', 227, '1982-08-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (66, 'BOOK', 'Foundation and Empire', 'The second Foundation novel.', 14.99, 50, CURRENT_TIMESTAMP, '9780553293371', 247, '1952-01-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (67, 'BOOK', 'Second Foundation', 'The third book in Asimovs Foundation series.', 14.99, 48, CURRENT_TIMESTAMP, '9780553293364', 279, '1953-01-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (68, 'BOOK', 'And Then There Were None', 'Ten strangers are lured to an island where they are murdered one by one.', 12.99, 0, CURRENT_TIMESTAMP, '9780062073488', 272, '1939-11-06', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (69, 'BOOK', 'The ABC Murders', 'Hercule Poirot hunts a serial killer using the alphabet.', 11.99, 70, CURRENT_TIMESTAMP, '9780062073587', 256, '1936-01-06', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (70, 'BOOK', 'A Farewell to Arms', 'A love story set during World War I.', 13.99, 60, CURRENT_TIMESTAMP, '9780684801469', 332, '1929-09-27', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (71, 'BOOK', 'For Whom the Bell Tolls', 'An American fights in the Spanish Civil War.', 15.99, 55, CURRENT_TIMESTAMP, '9780684803357', 471, '1940-10-21', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (72, 'BOOK', 'Count Zero', 'The second book in Gibsons Sprawl trilogy.', 13.99, 55, CURRENT_TIMESTAMP, '9780441117734', 256, '1986-03-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (73, 'BOOK', 'Mona Lisa Overdrive', 'The conclusion to the Sprawl trilogy.', 13.99, 50, CURRENT_TIMESTAMP, '9780553281743', 251, '1988-11-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (74, 'BOOK', 'Ringworld', 'Explorers discover an artificial ring around a distant star.', 14.99, 60, CURRENT_TIMESTAMP, '9780345333926', 342, '1970-10-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (75, 'BOOK', 'The Wind-Up Bird Chronicle', 'A man searches for his missing cat and wife in surreal Tokyo.', 16.99, 0, CURRENT_TIMESTAMP, '9780679775430', 607, '1994-01-01', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (76, 'BOOK', 'South of the Border, West of the Sun', 'A man reflects on lost love and choices.', 13.99, 55, CURRENT_TIMESTAMP, '9780679767398', 213, '1992-10-01', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (77, 'BOOK', 'Pet Sematary', 'A family discovers a burial ground with the power to resurrect the dead.', 15.99, 50, CURRENT_TIMESTAMP, '9781501156701', 416, '1983-11-14', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (78, 'BOOK', 'Salems Lot', 'A writer returns to his hometown to find it overrun by vampires.', 14.99, 55, CURRENT_TIMESTAMP, '9780307743671', 439, '1975-10-17', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (79, 'BOOK', 'Carrie', 'A bullied girl with telekinetic powers seeks revenge.', 12.99, 70, CURRENT_TIMESTAMP, '9780307743664', 199, '1974-04-05', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (80, 'BOOK', 'Harry Potter and the Order of the Phoenix', 'Harry faces the return of Voldemort and a hostile Ministry.', 16.99, 0, CURRENT_TIMESTAMP, '9780439358071', 870, '2003-06-21', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (81, 'BOOK', 'Harry Potter and the Half-Blood Prince', 'Harry learns about Voldemorts past.', 15.99, 75, CURRENT_TIMESTAMP, '9780439785969', 652, '2005-07-16', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (82, 'BOOK', 'Harry Potter and the Deathly Hallows', 'The final battle against Voldemort.', 17.99, 85, CURRENT_TIMESTAMP, '9780545139700', 759, '2007-07-21', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (83, 'BOOK', 'Going Postal', 'A con man is put in charge of the postal service.', 13.99, 55, CURRENT_TIMESTAMP, '9780060502935', 471, '2004-09-25', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (84, 'BOOK', 'Night Watch', 'Commander Vimes travels back in time.', 14.99, 50, CURRENT_TIMESTAMP, '9780060013127', 480, '2002-11-07', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (85, 'BOOK', 'Stardust', 'A young man ventures into a magical realm.', 12.99, 65, CURRENT_TIMESTAMP, '9780061689246', 256, '1999-02-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (86, 'BOOK', 'Neverwhere', 'A man falls through the cracks into London Below.', 13.99, 0, CURRENT_TIMESTAMP, '9780060557812', 370, '1996-09-16', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (87, 'BOOK', 'I Robot', 'A collection of robot stories exploring ethics and AI.', 12.99, 70, CURRENT_TIMESTAMP, '9780553382563', 224, '1950-12-02', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (88, 'BOOK', 'The Caves of Steel', 'A detective and robot partner solve a murder on Earth.', 13.99, 55, CURRENT_TIMESTAMP, '9780553293401', 206, '1954-01-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (89, 'BOOK', 'Death on the Nile', 'Poirot investigates a murder on a Nile cruise.', 12.99, 65, CURRENT_TIMESTAMP, '9780062073556', 320, '1937-11-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (90, 'BOOK', 'The Sun Also Rises', 'American expatriates wander through Europe.', 13.99, 50, CURRENT_TIMESTAMP, '9780743297332', 251, '1926-10-22', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (91, 'BOOK', 'Autumn of the Patriarch', 'The life of a Caribbean dictator told through memories.', 14.99, 0, CURRENT_TIMESTAMP, '9780060882860', 269, '1975-03-01', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (92, 'BOOK', 'Northanger Abbey', 'A satire of Gothic novels and coming-of-age story.', 10.99, 60, CURRENT_TIMESTAMP, '9780141439792', 230, '1817-12-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (93, 'BOOK', 'Mansfield Park', 'A poor relation navigates life among the wealthy.', 11.99, 55, CURRENT_TIMESTAMP, '9780141439808', 455, '1814-07-01', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (94, 'BOOK', 'Persuasion', 'A woman gets a second chance at love.', 10.99, 65, CURRENT_TIMESTAMP, '9780141439686', 249, '1817-12-20', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (95, 'BOOK', 'The Zahir', 'A famous author searches for his missing wife.', 14.99, 50, CURRENT_TIMESTAMP, '9780060825218', 336, '2005-04-05', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (96, 'BOOK', 'Eleven Minutes', 'A Brazilian prostitute in Geneva searches for love.', 13.99, 55, CURRENT_TIMESTAMP, '9780060589288', 302, '2003-05-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (97, 'BOOK', 'Baudolino', 'A medieval storyteller creates myths and history.', 15.99, 0, CURRENT_TIMESTAMP, '9780156029063', 528, '2000-09-01', 'ITALIAN', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (98, 'BOOK', 'The Island of the Day Before', 'A 17th-century nobleman is shipwrecked.', 14.99, 35, CURRENT_TIMESTAMP, '9780156030373', 515, '1994-01-01', 'ITALIAN', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (99, 'BOOK', 'Toilers of the Sea', 'A fisherman battles the sea and a giant octopus.', 13.99, 45, CURRENT_TIMESTAMP, '9780679600626', 488, '1866-03-01', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (100, 'BOOK', 'The Man Who Laughs', 'A disfigured man navigates society in 17th-century England.', 14.99, 0, CURRENT_TIMESTAMP, '9780140436927', 720, '1869-04-01', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (151, 'BOOK', 'Firestarter', 'A young girl with pyrokinetic powers is hunted by a government agency.', 14.99, 55, CURRENT_TIMESTAMP, '9781501192302', 426, '1980-09-29', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (152, 'BOOK', 'Cujo', 'A rabid dog terrorizes a mother and son trapped in their car.', 13.99, 45, CURRENT_TIMESTAMP, '9781501192319', 319, '1981-09-08', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (153, 'BOOK', 'Christine', 'A possessed 1958 Plymouth Fury wreaks havoc on its owner.', 14.99, 50, CURRENT_TIMESTAMP, '9781501192326', 526, '1983-04-29', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (154, 'BOOK', 'Pet Sematary', 'A family discovers a burial ground with dark powers.', 15.99, 0, CURRENT_TIMESTAMP, '9781501156700', 416, '1983-11-14', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (155, 'BOOK', 'Foundation', 'A mathematician predicts the fall of a galactic empire.', 14.99, 0, CURRENT_TIMESTAMP, '9780553293358', 244, '1951-05-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (156, 'BOOK', 'Foundation and Empire', 'The Foundation faces threats from the dying Empire.', 13.99, 55, CURRENT_TIMESTAMP, '9780553293372', 247, '1952-01-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (157, 'BOOK', 'Second Foundation', 'The search for the secret Second Foundation.', 13.99, 50, CURRENT_TIMESTAMP, '9780553293365', 210, '1953-01-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (158, 'BOOK', 'Murder on the Orient Express', 'Poirot investigates a murder on a snowbound train.', 12.99, 75, CURRENT_TIMESTAMP, '9780062693663', 256, '1934-01-01', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (159, 'BOOK', 'And Then There Were None', 'Ten strangers are lured to an island and murdered one by one.', 12.99, 80, CURRENT_TIMESTAMP, '9780062073489', 272, '1939-11-06', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (160, 'BOOK', 'The ABC Murders', 'Poirot hunts a serial killer using the alphabet.', 11.99, 65, CURRENT_TIMESTAMP, '9780062073588', 256, '1936-01-06', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (161, 'BOOK', 'A Farewell to Arms', 'An American ambulance driver falls in love during WWI.', 13.99, 0, CURRENT_TIMESTAMP, '9780684801460', 332, '1929-09-27', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (162, 'BOOK', 'For Whom the Bell Tolls', 'An American fights in the Spanish Civil War.', 14.99, 50, CURRENT_TIMESTAMP, '9780684803358', 471, '1940-10-21', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (163, 'BOOK', 'Good Omens', 'An angel and demon team up to prevent the apocalypse.', 15.99, 65, CURRENT_TIMESTAMP, '9780060853984', 432, '1990-05-10', 'ENGLISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (164, 'BOOK', 'The Difference Engine', 'An alternate Victorian era where computers exist.', 14.99, 0, CURRENT_TIMESTAMP, '9780440423622', 429, '1990-03-01', 'ENGLISH', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (165, 'BOOK', 'Ringworld', 'Explorers discover an artificial ring-shaped world.', 13.99, 55, CURRENT_TIMESTAMP, '9780345333927', 342, '1970-10-01', 'ENGLISH', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (166, 'BOOK', 'The Mote in Gods Eye', 'Humanity makes first contact with an alien species.', 15.99, 45, CURRENT_TIMESTAMP, '9780671741922', 537, '1974-01-01', 'ENGLISH', 3);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (167, 'BOOK', 'Count Zero', 'Cyberspace cowboys navigate a world of AIs and corporations.', 13.99, 50, CURRENT_TIMESTAMP, '9780441013678', 308, '1986-03-01', 'ENGLISH', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (168, 'BOOK', 'Mona Lisa Overdrive', 'Multiple storylines converge in a cyberpunk future.', 13.99, 0, CURRENT_TIMESTAMP, '9780553281744', 308, '1988-10-01', 'ENGLISH', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (169, 'BOOK', 'The Colour of Magic', 'The first adventure on the Discworld.', 12.99, 70, CURRENT_TIMESTAMP, '9780062225673', 288, '1983-11-24', 'ENGLISH', 2);

-- Book-Author relationships
INSERT INTO t_book_authors (book_id, author_id)
VALUES (1, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (2, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (3, 3);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (4, 4);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (5, 5);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (6, 6);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (7, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (8, 8);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (9, 9);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (10, 10);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (21, 33); -- Terry Pratchett
INSERT INTO t_book_authors (book_id, author_id)
VALUES (21, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (22, 35);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (23, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (24, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (25, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (26, 39); -- Larry Niven
INSERT INTO t_book_authors (book_id, author_id)
VALUES (26, 40);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (27, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (28, 41); -- William Gibson
INSERT INTO t_book_authors (book_id, author_id)
VALUES (28, 42);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (29, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (30, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (41, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (42, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (43, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (44, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (45, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (46, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (47, 3);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (48, 5);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (49, 5);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (50, 6);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (51, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (52, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (53, 8);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (54, 8);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (55, 9);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (56, 9);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (57, 10);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (58, 4);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (59, 4);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (60, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (61, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (62, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (63, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (64, 35);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (65, 35);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (66, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (67, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (68, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (69, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (70, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (71, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (72, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (73, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (74, 39);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (75, 5);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (76, 5);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (77, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (78, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (79, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (80, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (81, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (82, 2);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (83, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (84, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (85, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (86, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (87, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (88, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (89, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (90, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (91, 4);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (92, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (93, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (94, 7);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (95, 9);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (96, 9);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (97, 10);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (98, 10);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (99, 6);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (100, 6);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (151, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (152, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (153, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (154, 1);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (155, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (156, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (157, 36);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (158, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (159, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (160, 37);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (161, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (162, 38);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (163, 33);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (163, 34);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (164, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (164, 42);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (165, 39);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (166, 39);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (166, 40);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (167, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (168, 41);
INSERT INTO t_book_authors (book_id, author_id)
VALUES (169, 33);

-- CDs (Items with dtype='CD')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (11, 'CD', 'Abbey Road', 'The eleventh studio album by the Beatles, featuring iconic tracks like Come Together and Here Comes the Sun.', 14.99, 60, CURRENT_TIMESTAMP, '5099969945120', 'Apple Records', 'ROCK', 2826000000000, '1969-09-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (12, 'CD', 'A Night at the Opera', 'The fourth studio album by Queen, featuring the legendary Bohemian Rhapsody.', 13.99, 45, CURRENT_TIMESTAMP, '0602527644165', 'EMI', 'ROCK', 2586000000000, '1975-11-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (13, 'CD', 'The Rise and Fall of Ziggy Stardust', 'David Bowie''s fifth studio album, a concept album about a fictional rock star.', 12.99, 0, CURRENT_TIMESTAMP, '0724352190003', 'RCA Records', 'ROCK', 2334000000000, '1972-06-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (14, 'CD', 'Kind of Blue', 'Miles Davis'' masterpiece and the best-selling jazz album of all time.', 11.99, 70, CURRENT_TIMESTAMP, '5099706527220', 'Columbia', 'JAZZ', 2754000000000, '1959-08-17');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (15, 'CD', 'Ella and Louis', 'A duet album featuring Ella Fitzgerald and Louis Armstrong.', 10.99, 35, CURRENT_TIMESTAMP, '0602498840382', 'Verve', 'JAZZ', 2220000000000, '1956-10-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (16, 'CD', 'Blood on the Tracks', 'Bob Dylan''s fifteenth studio album, widely regarded as one of his greatest works.', 12.99, 50, CURRENT_TIMESTAMP, '5099751234524', 'Columbia', 'COUNTRY', 3126000000000, '1975-01-20');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (17, 'CD', 'I Never Loved a Man the Way I Love You', 'Aretha Franklin''s breakthrough album that established her as the Queen of Soul.', 11.99, 0, CURRENT_TIMESTAMP, '0081227965730', 'Atlantic', 'BLUES', 1962000000000, '1967-03-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (18, 'CD', 'Are You Experienced', 'The debut album by the Jimi Hendrix Experience, a landmark in rock history.', 13.99, 55, CURRENT_TIMESTAMP, '0886976340728', 'Track Records', 'ROCK', 2406000000000, '1967-05-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (19, 'CD', 'I Put a Spell on You', 'Nina Simone''s seventh studio album, featuring jazz and blues standards.', 10.99, 30, CURRENT_TIMESTAMP, '0600753458488', 'Philips', 'JAZZ', 2100000000000, '1965-06-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (20, 'CD', 'Symphony No. 9', 'Beethoven''s final complete symphony, featuring the famous Ode to Joy.', 15.99, 25, CURRENT_TIMESTAMP, '0028947753223', 'Deutsche Grammophon', 'CLASSICAL', 4200000000000, '1824-05-07');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (31, 'CD', 'Thriller', 'The best-selling album of all time, featuring iconic hits like Billie Jean and Beat It.', 14.99, 0, CURRENT_TIMESTAMP, '5099749534728', 'Epic Records', 'POP', 2524000000000, '1982-11-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (32, 'CD', 'The Dark Side of the Moon', 'A progressive rock concept album exploring themes of conflict, greed, and mental illness.', 15.99, 55, CURRENT_TIMESTAMP, '5099902894522', 'Harvest', 'ROCK', 2580000000000, '1973-03-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (33, 'CD', 'Rumours', 'One of the best-selling albums ever, recorded during band turmoil and relationship breakups.', 13.99, 65, CURRENT_TIMESTAMP, '0081227959357', 'Warner Bros', 'ROCK', 2394000000000, '1977-02-04');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (34, 'CD', 'Back in Black', 'A hard rock classic and tribute to late vocalist Bon Scott.', 12.99, 70, CURRENT_TIMESTAMP, '5099751283621', 'Atlantic', 'ROCK', 2532000000000, '1980-07-25');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (35, 'CD', 'Legend', 'The best-selling reggae album of all time, a greatest hits compilation.', 11.99, 60, CURRENT_TIMESTAMP, '0042284620321', 'Island Records', 'OTHER', 3360000000000, '1984-05-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (36, 'CD', 'What''s Going On', 'A soul concept album addressing themes of poverty, war, and environmentalism.', 12.99, 45, CURRENT_TIMESTAMP, '0602498840252', 'Tamla', 'BLUES', 2130000000000, '1971-05-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (37, 'CD', 'The Velvet Underground & Nico', 'An influential debut album produced by Andy Warhol.', 13.99, 35, CURRENT_TIMESTAMP, '0602498505427', 'Verve', 'ROCK', 2946000000000, '1967-03-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (38, 'CD', 'Blue', 'A confessional singer-songwriter masterpiece about love and loss.', 11.99, 0, CURRENT_TIMESTAMP, '0075596059329', 'Reprise', 'COUNTRY', 2160000000000, '1971-06-22');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (39, 'CD', 'Pet Sounds', 'An innovative album that influenced countless artists including The Beatles.', 12.99, 50, CURRENT_TIMESTAMP, '0602547091314', 'Capitol', 'POP', 2178000000000, '1966-05-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (40, 'CD', 'Led Zeppelin IV', 'Features the iconic Stairway to Heaven and other hard rock classics.', 13.99, 60, CURRENT_TIMESTAMP, '0075678268526', 'Atlantic', 'ROCK', 2556000000000, '1971-11-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (101, 'CD', 'Nevermind', 'Nirvanas breakthrough grunge album featuring Smells Like Teen Spirit.', 13.99, 0, CURRENT_TIMESTAMP, '0720642442524', 'DGC', 'ROCK', 2517000000000, '1991-09-24');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (102, 'CD', 'OK Computer', 'Radioheads masterpiece exploring technology and alienation.', 14.99, 60, CURRENT_TIMESTAMP, '0724385522925', 'Parlophone', 'ROCK', 3181000000000, '1997-06-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (103, 'CD', 'The Joshua Tree', 'U2s landmark album of American-influenced rock.', 13.99, 65, CURRENT_TIMESTAMP, '0042284236324', 'Island', 'ROCK', 3077000000000, '1987-03-09');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (104, 'CD', 'Purple Rain', 'Princes iconic soundtrack album.', 12.99, 70, CURRENT_TIMESTAMP, '0075992511025', 'Warner Bros', 'POP', 2643000000000, '1984-06-25');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (105, 'CD', 'Born to Run', 'Bruce Springsteens breakthrough album.', 12.99, 55, CURRENT_TIMESTAMP, '5099749413023', 'Columbia', 'ROCK', 2396000000000, '1975-08-25');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (106, 'CD', 'Appetite for Destruction', 'Guns N Roses explosive debut album.', 13.99, 60, CURRENT_TIMESTAMP, '0720642414829', 'Geffen', 'ROCK', 3261000000000, '1987-07-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (107, 'CD', 'The Wall', 'Pink Floyds epic rock opera about isolation.', 16.99, 50, CURRENT_TIMESTAMP, '5099924195526', 'Harvest', 'ROCK', 4860000000000, '1979-11-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (108, 'CD', 'London Calling', 'The Clashs genre-defying double album.', 14.99, 55, CURRENT_TIMESTAMP, '5099749534124', 'CBS', 'ROCK', 3913000000000, '1979-12-14');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (109, 'CD', 'Hotel California', 'The Eagles quintessential California rock album.', 12.99, 65, CURRENT_TIMESTAMP, '0075596049825', 'Asylum', 'ROCK', 2616000000000, '1976-12-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (110, 'CD', 'Wish You Were Here', 'Pink Floyds tribute to Syd Barrett.', 14.99, 55, CURRENT_TIMESTAMP, '5099902943824', 'Harvest', 'ROCK', 2688000000000, '1975-09-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (111, 'CD', 'Exile on Main St.', 'The Rolling Stones raw and sprawling double album.', 15.99, 0, CURRENT_TIMESTAMP, '0602527015545', 'Rolling Stones', 'ROCK', 4016000000000, '1972-05-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (112, 'CD', 'The Doors', 'The Doors self-titled debut with Light My Fire.', 12.99, 60, CURRENT_TIMESTAMP, '0075596050128', 'Elektra', 'ROCK', 2613000000000, '1967-01-04');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (113, 'CD', 'Highway 61 Revisited', 'Bob Dylans electric rock masterpiece.', 13.99, 50, CURRENT_TIMESTAMP, '5099751234821', 'Columbia', 'ROCK', 3081000000000, '1965-08-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (114, 'CD', 'Blonde on Blonde', 'Dylans influential double album.', 14.99, 45, CURRENT_TIMESTAMP, '5099751234920', 'Columbia', 'ROCK', 4380000000000, '1966-05-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (115, 'CD', 'Sgt Peppers Lonely Hearts Club Band', 'The Beatles groundbreaking concept album.', 14.99, 70, CURRENT_TIMESTAMP, '5099969945229', 'Parlophone', 'ROCK', 2397000000000, '1967-05-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (116, 'CD', 'Revolver', 'The Beatles experimental masterpiece.', 13.99, 65, CURRENT_TIMESTAMP, '5099969945328', 'Parlophone', 'ROCK', 2093000000000, '1966-08-05');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (117, 'CD', 'The White Album', 'The Beatles sprawling double album.', 16.99, 55, CURRENT_TIMESTAMP, '5099969945427', 'Apple', 'ROCK', 5576000000000, '1968-11-22');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (118, 'CD', 'A Love Supreme', 'John Coltranes spiritual jazz masterpiece.', 12.99, 50, CURRENT_TIMESTAMP, '0602498840826', 'Impulse', 'JAZZ', 1968000000000, '1965-01-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (119, 'CD', 'Time Out', 'The Dave Brubeck Quartets innovative album featuring Take Five.', 11.99, 0, CURRENT_TIMESTAMP, '0886974169826', 'Columbia', 'JAZZ', 2375000000000, '1959-12-14');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (120, 'CD', 'Bitches Brew', 'Miles Davis pioneering jazz fusion album.', 14.99, 45, CURRENT_TIMESTAMP, '5099706563822', 'Columbia', 'JAZZ', 6360000000000, '1970-03-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (121, 'CD', 'Songs in the Key of Life', 'Stevie Wonders ambitious double album.', 15.99, 50, CURRENT_TIMESTAMP, '0602498632826', 'Tamla', 'BLUES', 6300000000000, '1976-09-28');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (122, 'CD', 'Innervisions', 'Stevie Wonders socially conscious masterpiece.', 12.99, 55, CURRENT_TIMESTAMP, '0602498632727', 'Tamla', 'BLUES', 2640000000000, '1973-08-03');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (123, 'CD', 'Off the Wall', 'Michael Jacksons disco-funk breakthrough.', 12.99, 65, CURRENT_TIMESTAMP, '5099749534629', 'Epic', 'POP', 2526000000000, '1979-08-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (124, 'CD', 'Bad', 'Michael Jackson continues his pop dominance.', 13.99, 60, CURRENT_TIMESTAMP, '5099749535121', 'Epic', 'POP', 2858000000000, '1987-08-31');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (125, 'CD', 'Sign o the Times', 'Princes eclectic double album.', 15.99, 50, CURRENT_TIMESTAMP, '0075992555326', 'Paisley Park', 'POP', 4800000000000, '1987-03-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (126, 'CD', '1999', 'Princes breakthrough album.', 13.99, 55, CURRENT_TIMESTAMP, '0075992372428', 'Warner Bros', 'POP', 4200000000000, '1982-10-27');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (127, 'CD', 'Graceland', 'Paul Simons South African-influenced album.', 12.99, 60, CURRENT_TIMESTAMP, '0886970828321', 'Warner Bros', 'POP', 2720000000000, '1986-08-25');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (128, 'CD', 'Bridge over Troubled Water', 'Simon and Garfunkels final studio album.', 12.99, 55, CURRENT_TIMESTAMP, '5099749413528', 'Columbia', 'POP', 2220000000000, '1970-01-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (129, 'CD', 'Born in the USA', 'Bruce Springsteens commercial peak.', 13.99, 65, CURRENT_TIMESTAMP, '5099749413627', 'Columbia', 'ROCK', 2820000000000, '1984-06-04');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (130, 'CD', 'Nebraska', 'Springsteens stark acoustic album.', 11.99, 45, CURRENT_TIMESTAMP, '5099749413726', 'Columbia', 'COUNTRY', 2400000000000, '1982-09-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (131, 'CD', 'Physical Graffiti', 'Led Zeppelins sprawling double album.', 15.99, 55, CURRENT_TIMESTAMP, '0075678268825', 'Swan Song', 'ROCK', 4920000000000, '1975-02-24');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (132, 'CD', 'Houses of the Holy', 'Led Zeppelins experimental fifth album.', 13.99, 0, CURRENT_TIMESTAMP, '0075678268627', 'Atlantic', 'ROCK', 2541000000000, '1973-03-28');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (133, 'CD', 'Who''s Next', 'The Whos hard rock masterpiece.', 12.99, 55, CURRENT_TIMESTAMP, '0602498492222', 'Decca', 'ROCK', 2617000000000, '1971-08-14');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (134, 'CD', 'Tommy', 'The Whos rock opera about a deaf dumb and blind boy.', 14.99, 45, CURRENT_TIMESTAMP, '0602498492321', 'Decca', 'ROCK', 4500000000000, '1969-05-23');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (135, 'CD', 'In the Court of the Crimson King', 'King Crimsons prog rock debut.', 13.99, 40, CURRENT_TIMESTAMP, '0633367050120', 'Island', 'ROCK', 2616000000000, '1969-10-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (136, 'CD', 'The Rise and Fall of Ziggy Stardust', 'David Bowies glam rock concept album.', 12.99, 55, CURRENT_TIMESTAMP, '0724352190126', 'RCA', 'ROCK', 2334000000000, '1972-06-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (137, 'CD', 'Heroes', 'Bowies Berlin-era masterpiece.', 12.99, 0, CURRENT_TIMESTAMP, '0724352190225', 'RCA', 'ROCK', 2400000000000, '1977-10-14');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (138, 'CD', 'Low', 'Bowies experimental first Berlin album.', 12.99, 45, CURRENT_TIMESTAMP, '0724352190324', 'RCA', 'ROCK', 2340000000000, '1977-01-14');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (139, 'CD', 'Parallel Lines', 'Blondies new wave breakthrough.', 11.99, 55, CURRENT_TIMESTAMP, '5099950020225', 'Chrysalis', 'POP', 2400000000000, '1978-09-23');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (140, 'CD', 'Talking Heads 77', 'Talking Heads debut album.', 11.99, 0, CURRENT_TIMESTAMP, '0075992738422', 'Sire', 'ROCK', 2280000000000, '1977-09-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (141, 'CD', 'Remain in Light', 'Talking Heads African-influenced masterpiece.', 12.99, 45, CURRENT_TIMESTAMP, '0075992738521', 'Sire', 'ROCK', 2403000000000, '1980-10-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (142, 'CD', 'Achtung Baby', 'U2s reinvention with electronic influences.', 13.99, 55, CURRENT_TIMESTAMP, '0042284236628', 'Island', 'ROCK', 3340000000000, '1991-11-18');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (143, 'CD', 'Automatic for the People', 'R.E.M.s introspective masterpiece.', 12.99, 60, CURRENT_TIMESTAMP, '0075992457422', 'Warner Bros', 'ROCK', 2930000000000, '1992-10-05');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (144, 'CD', 'Ten', 'Pearl Jams grunge debut album.', 12.99, 0, CURRENT_TIMESTAMP, '5099747501821', 'Epic', 'ROCK', 3175000000000, '1991-08-27');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (145, 'CD', 'The Bends', 'Radioheads guitar-driven second album.', 12.99, 55, CURRENT_TIMESTAMP, '0724385522826', 'Parlophone', 'ROCK', 2920000000000, '1995-03-13');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (146, 'CD', 'Kid A', 'Radioheads experimental electronic turn.', 13.99, 50, CURRENT_TIMESTAMP, '0724385523021', 'Parlophone', 'ROCK', 2954000000000, '2000-10-02');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (147, 'CD', 'In Rainbows', 'Radioheads pay-what-you-want release.', 12.99, 55, CURRENT_TIMESTAMP, '0634904032326', 'XL', 'ROCK', 2539000000000, '2007-10-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (148, 'CD', 'Loveless', 'My Bloody Valentines shoegaze masterpiece.', 13.99, 40, CURRENT_TIMESTAMP, '5099914817427', 'Creation', 'ROCK', 2920000000000, '1991-11-04');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (149, 'CD', 'Doolittle', 'Pixies surreal alternative rock album.', 12.99, 0, CURRENT_TIMESTAMP, '5014436302626', '4AD', 'ROCK', 2340000000000, '1989-04-17');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (150, 'CD', 'Disintegration', 'The Cures atmospheric goth masterpiece.', 13.99, 45, CURRENT_TIMESTAMP, '0042284236925', 'Fiction', 'ROCK', 4320000000000, '1989-05-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (170, 'CD', 'Appetite for Destruction', 'Guns N Roses explosive debut album.', 13.99, 65, CURRENT_TIMESTAMP, '0720642414828', 'Geffen', 'ROCK', 3238000000000, '1987-07-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (171, 'CD', 'Slippery When Wet', 'Bon Jovis breakthrough arena rock album.', 12.99, 55, CURRENT_TIMESTAMP, '0731452824028', 'Mercury', 'ROCK', 2594000000000, '1986-08-18');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (172, 'CD', 'Hysteria', 'Def Leppards polished rock masterpiece.', 12.99, 50, CURRENT_TIMESTAMP, '0731451244629', 'Mercury', 'ROCK', 3762000000000, '1987-08-03');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (173, 'CD', 'Metallica (Black Album)', 'Metallicas commercial breakthrough.', 13.99, 70, CURRENT_TIMESTAMP, '0731451044227', 'Elektra', 'ROCK', 3875000000000, '1991-08-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (174, 'CD', 'Master of Puppets', 'Metallicas thrash metal landmark.', 13.99, 0, CURRENT_TIMESTAMP, '0075992537421', 'Elektra', 'ROCK', 3282000000000, '1986-03-03');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (175, 'CD', 'Back in Black', 'AC/DCs tribute to Bon Scott.', 12.99, 75, CURRENT_TIMESTAMP, '5099751283622', 'Atlantic', 'ROCK', 2520000000000, '1980-07-25');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (176, 'CD', 'Highway to Hell', 'AC/DCs last album with Bon Scott.', 12.99, 60, CURRENT_TIMESTAMP, '5099751283126', 'Atlantic', 'ROCK', 2495000000000, '1979-07-27');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (177, 'CD', 'Blood Sugar Sex Magik', 'Red Hot Chili Peppers funk rock breakthrough.', 13.99, 55, CURRENT_TIMESTAMP, '0075992660822', 'Warner Bros', 'ROCK', 4380000000000, '1991-09-24');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (178, 'CD', 'Californication', 'Red Hot Chili Peppers melodic comeback.', 13.99, 65, CURRENT_TIMESTAMP, '0093624730828', 'Warner Bros', 'ROCK', 3536000000000, '1999-06-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (179, 'CD', 'Superunknown', 'Soundgardens alternative metal peak.', 13.99, 0, CURRENT_TIMESTAMP, '5401053750226', 'A&M', 'ROCK', 4215000000000, '1994-03-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (180, 'CD', 'Dirt', 'Alice in Chains dark grunge masterpiece.', 13.99, 45, CURRENT_TIMESTAMP, '5099751283423', 'Columbia', 'ROCK', 3512000000000, '1992-09-29');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (181, 'CD', 'Siamese Dream', 'Smashing Pumpkins layered alternative rock.', 13.99, 55, CURRENT_TIMESTAMP, '0724383099726', 'Virgin', 'ROCK', 3872000000000, '1993-07-27');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (182, 'CD', 'Mellon Collie and the Infinite Sadness', 'Smashing Pumpkins ambitious double album.', 16.99, 40, CURRENT_TIMESTAMP, '0724383498826', 'Virgin', 'ROCK', 7268000000000, '1995-10-24');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (183, 'CD', 'Grace', 'Jeff Buckleys only studio album.', 14.99, 45, CURRENT_TIMESTAMP, '5099750442227', 'Columbia', 'ROCK', 3090000000000, '1994-08-23');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (184, 'CD', 'Violator', 'Depeche Modes electronic pop masterpiece.', 13.99, 0, CURRENT_TIMESTAMP, '5016025660124', 'Mute', 'POP', 2858000000000, '1990-03-19');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (185, 'CD', 'Songs of Faith and Devotion', 'Depeche Modes darker electronic album.', 12.99, 45, CURRENT_TIMESTAMP, '5016025661128', 'Mute', 'POP', 2935000000000, '1993-03-22');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (186, 'CD', 'The Downward Spiral', 'Nine Inch Nails industrial masterwork.', 13.99, 40, CURRENT_TIMESTAMP, '0731452206527', 'Nothing', 'ROCK', 3928000000000, '1994-03-08');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (187, 'CD', 'Pretty Hate Machine', 'Nine Inch Nails debut industrial album.', 12.99, 50, CURRENT_TIMESTAMP, '0731452207524', 'TVT', 'ROCK', 2928000000000, '1989-10-20');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (188, 'CD', 'Jagged Little Pill', 'Alanis Morissettes breakthrough album.', 12.99, 60, CURRENT_TIMESTAMP, '0093624571025', 'Maverick', 'POP', 3399000000000, '1995-06-13');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (189, 'CD', 'Ray of Light', 'Madonnas electronic reinvention.', 13.99, 0, CURRENT_TIMESTAMP, '0093624697527', 'Maverick', 'POP', 3952000000000, '1998-02-22');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (190, 'CD', 'Vitalogy', 'Pearl Jams experimental third album.', 12.99, 50, CURRENT_TIMESTAMP, '5099750066621', 'Epic', 'ROCK', 3348000000000, '1994-11-22');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (191, 'CD', 'Vs.', 'Pearl Jams harder edged second album.', 12.99, 55, CURRENT_TIMESTAMP, '5099750066225', 'Epic', 'ROCK', 2770000000000, '1993-10-19');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (192, 'CD', 'Weezer (Blue Album)', 'Weezers power pop debut.', 11.99, 65, CURRENT_TIMESTAMP, '0720642437926', 'DGC', 'ROCK', 2477000000000, '1994-05-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (193, 'CD', 'Pinkerton', 'Weezers raw emotional second album.', 11.99, 0, CURRENT_TIMESTAMP, '0720642448426', 'DGC', 'ROCK', 2112000000000, '1996-09-24');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (194, 'CD', 'Parachutes', 'Coldplays debut album.', 12.99, 60, CURRENT_TIMESTAMP, '0724352778928', 'Parlophone', 'ROCK', 2525000000000, '2000-07-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (195, 'CD', 'A Rush of Blood to the Head', 'Coldplays breakthrough second album.', 13.99, 65, CURRENT_TIMESTAMP, '0724353596026', 'Parlophone', 'ROCK', 3156000000000, '2002-08-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (196, 'CD', 'Is This It', 'The Strokes garage rock revival debut.', 12.99, 55, CURRENT_TIMESTAMP, '0078636796628', 'RCA', 'ROCK', 2238000000000, '2001-07-30');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (197, 'CD', 'Hot Fuss', 'The Killers synth-rock debut.', 12.99, 60, CURRENT_TIMESTAMP, '0602498625220', 'Island', 'ROCK', 2874000000000, '2004-06-15');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (198, 'CD', 'Elephant', 'The White Stripes blues rock masterpiece.', 13.99, 0, CURRENT_TIMESTAMP, '5035194003221', 'V2', 'ROCK', 2998000000000, '2003-04-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (199, 'CD', 'White Blood Cells', 'The White Stripes garage rock breakthrough.', 12.99, 0, CURRENT_TIMESTAMP, '5035194000923', 'Sympathy', 'ROCK', 2403000000000, '2001-07-03');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (200, 'CD', 'Whatever People Say I Am Thats What Im Not', 'Arctic Monkeys record-breaking debut.', 12.99, 0, CURRENT_TIMESTAMP, '5034202013228', 'Domino', 'ROCK', 2536000000000, '2006-01-23');

-- CD-Musician relationships
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (11, 11); -- John Lennon
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (11, 21); -- Paul McCartney
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (11, 22); -- George Harrison
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (11, 23);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (12, 12); -- Freddie Mercury
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (12, 24); -- Brian May
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (12, 25); -- Roger Taylor
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (12, 26);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (13, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (14, 14); -- Miles Davis
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (14, 27); -- John Coltrane
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (14, 28); -- Bill Evans
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (14, 29);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (15, 15); -- Ella Fitzgerald
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (15, 30);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (16, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (17, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (18, 18); -- Jimi Hendrix
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (18, 31); -- Noel Redding
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (18, 32);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (19, 19);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (20, 20);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (31, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (32, 44); -- Roger Waters
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (32, 45);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (33, 46); -- Stevie Nicks
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (33, 47);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (34, 48); -- Angus Young
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (34, 49);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (35, 50);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (36, 51);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (37, 52); -- Lou Reed
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (37, 53);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (38, 54);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (39, 55); -- Brian Wilson
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (39, 56);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (40, 57); -- Robert Plant
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (40, 58); -- Jimmy Page
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (40, 59); -- John Paul Jones
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (40, 60);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (101, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (102, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (103, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (104, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (105, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (106, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (107, 44);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (107, 45);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (108, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (109, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (110, 44);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (110, 45);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (111, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (112, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (113, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (114, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (115, 11);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (115, 21);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (115, 22);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (115, 23);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (116, 11);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (116, 21);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (116, 22);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (116, 23);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (117, 11);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (117, 21);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (117, 22);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (117, 23);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (118, 27);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (119, 14);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (120, 14);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (121, 51);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (122, 51);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (123, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (124, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (125, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (126, 43);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (127, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (128, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (129, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (130, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (131, 57);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (131, 58);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (131, 59);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (131, 60);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (132, 57);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (132, 58);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (132, 59);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (132, 60);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (133, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (134, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (135, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (136, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (137, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (138, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (139, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (140, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (141, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (142, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (143, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (144, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (145, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (146, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (147, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (148, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (149, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (150, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (170, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (171, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (172, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (173, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (174, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (175, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (176, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (177, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (178, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (179, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (180, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (181, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (182, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (183, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (184, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (185, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (186, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (187, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (188, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (189, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (190, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (191, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (192, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (193, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (194, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (195, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (196, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (197, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (198, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (199, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id)
VALUES (200, 18);

-- Tracks for CDs
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (1, 'Come Together', 259000000000, 1, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (2, 'Something', 182000000000, 2, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (3, 'Maxwell''s Silver Hammer', 207000000000, 3, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (4, 'Oh! Darling', 206000000000, 4, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (5, 'Octopus''s Garden', 170000000000, 5, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (6, 'Here Comes the Sun', 185000000000, 6, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (7, 'Death on Two Legs', 223000000000, 1, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (8, 'Lazing on a Sunday Afternoon', 67000000000, 2, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (9, 'You''re My Best Friend', 170000000000, 3, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (10, 'Bohemian Rhapsody', 354000000000, 4, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (11, 'God Save the Queen', 75000000000, 5, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (12, 'Five Years', 275000000000, 1, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (13, 'Soul Love', 213000000000, 2, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (14, 'Starman', 255000000000, 3, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (15, 'Ziggy Stardust', 194000000000, 4, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (16, 'Suffragette City', 206000000000, 5, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (17, 'Rock ''n'' Roll Suicide', 180000000000, 6, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (18, 'So What', 562000000000, 1, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (19, 'Freddie Freeloader', 588000000000, 2, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (20, 'Blue in Green', 327000000000, 3, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (21, 'All Blues', 690000000000, 4, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (22, 'Flamenco Sketches', 567000000000, 5, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (23, 'Can''t We Be Friends?', 206000000000, 1, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (24, 'Isn''t This a Lovely Day?', 296000000000, 2, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (25, 'Moonlight in Vermont', 201000000000, 3, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (26, 'They Can''t Take That Away from Me', 221000000000, 4, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (27, 'Cheek to Cheek', 357000000000, 5, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (28, 'Tangled Up in Blue', 352000000000, 1, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (29, 'Simple Twist of Fate', 256000000000, 2, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (30, 'You''re a Big Girl Now', 294000000000, 3, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (31, 'Idiot Wind', 467000000000, 4, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (32, 'Shelter from the Storm', 300000000000, 5, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (33, 'Respect', 148000000000, 1, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (34, 'Drown in My Own Tears', 193000000000, 2, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (35, 'I Never Loved a Man', 172000000000, 3, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (36, 'Dr. Feelgood', 211000000000, 4, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (37, 'A Change Is Gonna Come', 257000000000, 5, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (38, 'Purple Haze', 170000000000, 1, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (39, 'Manic Depression', 210000000000, 2, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (40, 'Hey Joe', 202000000000, 3, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (41, 'The Wind Cries Mary', 200000000000, 4, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (42, 'Fire', 165000000000, 5, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (43, 'Are You Experienced?', 254000000000, 6, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (44, 'I Put a Spell on You', 156000000000, 1, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (45, 'Feeling Good', 177000000000, 2, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (46, 'Ne Me Quitte Pas', 218000000000, 3, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (47, 'Marriage Is for Old Folks', 134000000000, 4, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (48, 'Tomorrow Is My Turn', 185000000000, 5, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (49, 'I. Allegro ma non troppo', 960000000000, 1, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (50, 'II. Molto vivace', 720000000000, 2, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (51, 'III. Adagio molto e cantabile', 900000000000, 3, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (52, 'IV. Presto - Ode to Joy', 1440000000000, 4, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (53, 'Wanna Be Startin'' Somethin''', 363000000000, 1, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (54, 'Baby Be Mine', 260000000000, 2, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (55, 'The Girl Is Mine', 222000000000, 3, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (56, 'Thriller', 358000000000, 4, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (57, 'Beat It', 258000000000, 5, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (58, 'Billie Jean', 294000000000, 6, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (59, 'Speak to Me', 68000000000, 1, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (60, 'Breathe', 169000000000, 2, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (61, 'Time', 413000000000, 3, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (62, 'Money', 382000000000, 4, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (63, 'Brain Damage', 226000000000, 5, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (64, 'Second Hand News', 163000000000, 1, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (65, 'Dreams', 257000000000, 2, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (66, 'Go Your Own Way', 217000000000, 3, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (67, 'The Chain', 270000000000, 4, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (68, 'Don''t Stop', 193000000000, 5, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (69, 'Hells Bells', 312000000000, 1, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (70, 'Shoot to Thrill', 317000000000, 2, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (71, 'Back in Black', 255000000000, 3, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (72, 'You Shook Me All Night Long', 210000000000, 4, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (73, 'Rock and Roll Ain''t Noise Pollution', 255000000000, 5, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (74, 'Is This Love', 231000000000, 1, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (75, 'No Woman, No Cry', 285000000000, 2, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (76, 'Jamming', 211000000000, 3, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (77, 'Redemption Song', 228000000000, 4, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (78, 'One Love', 172000000000, 5, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (79, 'What''s Going On', 233000000000, 1, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (80, 'What''s Happening Brother', 164000000000, 2, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (81, 'Mercy Mercy Me', 201000000000, 3, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (82, 'Inner City Blues', 338000000000, 4, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (83, 'Sunday Morning', 175000000000, 1, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (84, 'I''m Waiting for the Man', 267000000000, 2, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (85, 'Femme Fatale', 157000000000, 3, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (86, 'Venus in Furs', 309000000000, 4, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (87, 'All Tomorrow''s Parties', 359000000000, 5, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (88, 'All I Want', 215000000000, 1, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (89, 'My Old Man', 213000000000, 2, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (90, 'Little Green', 201000000000, 3, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (91, 'A Case of You', 262000000000, 4, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (92, 'River', 245000000000, 5, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (93, 'Wouldn''t It Be Nice', 153000000000, 1, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (94, 'Sloop John B', 183000000000, 2, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (95, 'God Only Knows', 172000000000, 3, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (96, 'I Know There''s an Answer', 195000000000, 4, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (97, 'Caroline, No', 174000000000, 5, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (98, 'Black Dog', 295000000000, 1, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (99, 'Rock and Roll', 220000000000, 2, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (100, 'The Battle of Evermore', 352000000000, 3, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (101, 'Stairway to Heaven', 482000000000, 4, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (102, 'Misty Mountain Hop', 278000000000, 5, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk)
VALUES (103, 'When the Levee Breaks', 427000000000, 6, 40);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_tracks_seq RESTART WITH 104;

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_items_seq RESTART WITH 201;
