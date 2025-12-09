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

-- Additional Authors for multi-author books
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

-- Musicians (Persons + Musicians due to JOINED inheritance)
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

-- Additional Musicians for bands
-- Beatles (for Abbey Road)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (21, 'Paul', 'McCartney', 'English singer, songwriter, and bassist for the Beatles.', '1942-06-18', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (22, 'George', 'Harrison', 'English musician and lead guitarist of the Beatles.', '1943-02-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (23, 'Ringo', 'Starr', 'English musician and drummer for the Beatles.', '1940-07-07', CURRENT_TIMESTAMP);

-- Queen (for A Night at the Opera)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (24, 'Brian', 'May', 'English musician and lead guitarist of Queen.', '1947-07-19', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (25, 'Roger', 'Taylor', 'English musician and drummer for Queen.', '1949-07-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (26, 'John', 'Deacon', 'English musician and bassist for Queen.', '1951-08-19', CURRENT_TIMESTAMP);

-- Kind of Blue session musicians
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (27, 'John', 'Coltrane', 'American jazz saxophonist and composer.', '1926-09-23', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (28, 'Bill', 'Evans', 'American jazz pianist and composer.', '1929-08-16', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (29, 'Cannonball', 'Adderley', 'American jazz alto saxophonist.', '1928-09-15', CURRENT_TIMESTAMP);

-- Louis Armstrong (for Ella and Louis)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (30, 'Louis', 'Armstrong', 'American trumpeter and vocalist, one of the most influential figures in jazz.', '1901-08-04', CURRENT_TIMESTAMP);

-- Jimi Hendrix Experience
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (31, 'Noel', 'Redding', 'English musician, bassist for the Jimi Hendrix Experience.', '1945-12-25', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (32, 'Mitch', 'Mitchell', 'English drummer for the Jimi Hendrix Experience.', '1946-07-09', CURRENT_TIMESTAMP);

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

-- Additional Musicians for new CDs
-- Michael Jackson
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (43, 'Michael', 'Jackson', 'American singer and dancer known as the King of Pop.', '1958-08-29', CURRENT_TIMESTAMP);
-- Pink Floyd
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (44, 'Roger', 'Waters', 'English musician, co-founder and bassist of Pink Floyd.', '1943-09-06', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (45, 'David', 'Gilmour', 'English guitarist and vocalist of Pink Floyd.', '1946-03-06', CURRENT_TIMESTAMP);
-- Fleetwood Mac
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (46, 'Stevie', 'Nicks', 'American singer-songwriter, vocalist of Fleetwood Mac.', '1948-05-26', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (47, 'Lindsey', 'Buckingham', 'American guitarist and vocalist of Fleetwood Mac.', '1949-10-03', CURRENT_TIMESTAMP);
-- AC/DC
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (48, 'Angus', 'Young', 'Australian guitarist and co-founder of AC/DC.', '1955-03-31', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (49, 'Brian', 'Johnson', 'English singer, lead vocalist of AC/DC since 1980.', '1947-10-05', CURRENT_TIMESTAMP);
-- Bob Marley
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (50, 'Bob', 'Marley', 'Jamaican singer-songwriter, pioneer of reggae music.', '1945-02-06', CURRENT_TIMESTAMP);
-- Marvin Gaye
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (51, 'Marvin', 'Gaye', 'American singer-songwriter known as the Prince of Soul.', '1939-04-02', CURRENT_TIMESTAMP);
-- Lou Reed (Velvet Underground)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (52, 'Lou', 'Reed', 'American musician, guitarist and vocalist of The Velvet Underground.', '1942-03-02', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (53, 'Christa', 'Päffgen', 'German singer, songwriter, and model known as Nico, collaborated with Velvet Underground.', '1938-10-16', CURRENT_TIMESTAMP);
-- Joni Mitchell
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (54, 'Joni', 'Mitchell', 'Canadian singer-songwriter known for confessional lyrics.', '1943-11-07', CURRENT_TIMESTAMP);
-- Brian Wilson (Beach Boys)
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (55, 'Brian', 'Wilson', 'American musician, co-founder and leader of The Beach Boys.', '1942-06-20', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (56, 'Mike', 'Love', 'American singer, co-founder and vocalist of The Beach Boys.', '1941-03-15', CURRENT_TIMESTAMP);
-- Led Zeppelin
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (57, 'Robert', 'Plant', 'English singer, lead vocalist of Led Zeppelin.', '1948-08-20', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (58, 'Jimmy', 'Page', 'English guitarist, founder of Led Zeppelin.', '1944-01-09', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (59, 'John Paul', 'Jones', 'English musician, bassist and keyboardist of Led Zeppelin.', '1946-01-03', CURRENT_TIMESTAMP);
INSERT INTO t_persons (id, first_name, last_name, bio, date_of_birth, created_date)
VALUES (60, 'John', 'Bonham', 'English drummer for Led Zeppelin, considered one of the greatest rock drummers.', '1948-05-31', CURRENT_TIMESTAMP);

INSERT INTO t_musicians (id, stage_name, instrument) VALUES (43, 'King of Pop', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (44, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (45, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (46, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (47, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (48, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (49, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (50, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (51, 'Prince of Soul', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (52, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (53, 'Nico', 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (54, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (55, NULL, 'Piano');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (56, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (57, NULL, 'Vocals');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (58, NULL, 'Guitar');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (59, NULL, 'Bass');
INSERT INTO t_musicians (id, stage_name, instrument) VALUES (60, 'Bonzo', 'Drums');

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_persons_seq RESTART WITH 61;

-- Books (Items with dtype='BOOK')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (1, 'BOOK', 'The Shining', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence.', 14.99,
        50, CURRENT_TIMESTAMP, '9780385121675', 447, '1977-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (2, 'BOOK', 'Harry Potter and the Philosopher''s Stone',
        'A young wizard discovers his magical heritage and attends Hogwarts School of Witchcraft and Wizardry.', 12.99, 100, CURRENT_TIMESTAMP, '9780747532699',
        223, '1997-06-26', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (3, 'BOOK', '1984', 'A dystopian novel set in a totalitarian society under constant surveillance.', 11.99, 75, CURRENT_TIMESTAMP, '9780451524935', 328,
        '1949-06-08', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (4, 'BOOK', 'One Hundred Years of Solitude', 'The multi-generational story of the Buendía family in the fictional town of Macondo.', 15.99, 40,
        CURRENT_TIMESTAMP, '9780060883287', 417, '1967-05-30', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (5, 'BOOK', 'Norwegian Wood', 'A nostalgic story of loss and sexuality set in Tokyo during the late 1960s.', 13.99, 60, CURRENT_TIMESTAMP,
        '9780375704024', 296, '1987-09-04', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (6, 'BOOK', 'Les Misérables', 'An epic tale of redemption, justice, and love in 19th century France.', 18.99, 30, CURRENT_TIMESTAMP, '9780140444308',
        1232, '1862-01-01', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (7, 'BOOK', 'Pride and Prejudice', 'A witty commentary on society and romance in Regency-era England.', 9.99, 80, CURRENT_TIMESTAMP, '9780141439518',
        279, '1813-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (8, 'BOOK', 'The Metamorphosis', 'A traveling salesman wakes up one morning to find himself transformed into a giant insect.', 8.99, 90,
        CURRENT_TIMESTAMP, '9780805209990', 55, '1915-01-01', 'GERMAN', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (9, 'BOOK', 'The Alchemist', 'A philosophical story about a shepherd boy who travels in search of treasure and discovers the true meaning of life.',
        14.99, 120, CURRENT_TIMESTAMP, '9780061122415', 208, '1988-01-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (10, 'BOOK', 'The Name of the Rose', 'A medieval murder mystery set in an Italian monastery.', 16.99, 35, CURRENT_TIMESTAMP, '9780151446476', 536,
        '1980-01-01', 'ITALIAN', 4);

-- Additional Books (including multi-author books)
-- Good Omens by Terry Pratchett AND Neil Gaiman
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (21, 'BOOK', 'Good Omens', 'The Nice and Accurate Prophecies of Agnes Nutter, Witch. A comedic novel about the apocalypse.', 14.99, 65,
        CURRENT_TIMESTAMP, '9780060853983', 400, '1990-05-01', 'ENGLISH', 2);

-- The Hitchhiker''s Guide to the Galaxy by Douglas Adams
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (22, 'BOOK', 'The Hitchhiker''s Guide to the Galaxy', 'A comedic science fiction series following the misadventures of Arthur Dent.', 12.99, 90,
        CURRENT_TIMESTAMP, '9780345391803', 224, '1979-10-12', 'ENGLISH', 1);

-- Foundation by Isaac Asimov
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (23, 'BOOK', 'Foundation', 'The first novel in the Foundation series, about the fall and rise of galactic civilization.', 15.99, 55,
        CURRENT_TIMESTAMP, '9780553293357', 244, '1951-06-01', 'ENGLISH', 1);

-- Murder on the Orient Express by Agatha Christie
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (24, 'BOOK', 'Murder on the Orient Express', 'Hercule Poirot investigates a murder aboard the famous train.', 11.99, 70,
        CURRENT_TIMESTAMP, '9780062693662', 256, '1934-01-01', 'ENGLISH', 2);

-- The Old Man and the Sea by Ernest Hemingway
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (25, 'BOOK', 'The Old Man and the Sea', 'The story of an aging Cuban fisherman and his epic battle with a giant marlin.', 10.99, 85,
        CURRENT_TIMESTAMP, '9780684801223', 128, '1952-09-01', 'ENGLISH', 3);

-- The Mote in God''s Eye by Larry Niven AND Jerry Pournelle
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (26, 'BOOK', 'The Mote in God''s Eye', 'First contact with an alien species leads to complex political and military dilemmas.', 16.99, 40,
        CURRENT_TIMESTAMP, '9780671741921', 537, '1974-01-01', 'ENGLISH', 3);

-- Neuromancer by William Gibson
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (27, 'BOOK', 'Neuromancer', 'A washed-up computer hacker is hired for one last job in this cyberpunk classic.', 13.99, 60,
        CURRENT_TIMESTAMP, '9780441569595', 271, '1984-07-01', 'ENGLISH', 1);

-- The Difference Engine by William Gibson AND Bruce Sterling
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (28, 'BOOK', 'The Difference Engine', 'An alternate history where Charles Babbage''s mechanical computer was built in Victorian England.', 14.99, 45,
        CURRENT_TIMESTAMP, '9780440423621', 429, '1990-02-01', 'ENGLISH', 1);

-- American Gods by Neil Gaiman
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (29, 'BOOK', 'American Gods', 'A modern fantasy about old gods struggling to survive in contemporary America.', 15.99, 75,
        CURRENT_TIMESTAMP, '9780063081918', 541, '2001-06-19', 'ENGLISH', 2);

-- Small Gods by Terry Pratchett
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk)
VALUES (30, 'BOOK', 'Small Gods', 'A Discworld novel satirizing religion and belief.', 12.99, 50,
        CURRENT_TIMESTAMP, '9780062237378', 389, '1992-01-01', 'ENGLISH', 2);

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

-- Book-Author relationships for additional books (including multi-author)
-- Good Omens (21) by Terry Pratchett AND Neil Gaiman
INSERT INTO t_book_authors (book_id, author_id) VALUES (21, 33);  -- Terry Pratchett
INSERT INTO t_book_authors (book_id, author_id) VALUES (21, 34);  -- Neil Gaiman

-- Hitchhiker's Guide (22) by Douglas Adams
INSERT INTO t_book_authors (book_id, author_id) VALUES (22, 35);  -- Douglas Adams

-- Foundation (23) by Isaac Asimov
INSERT INTO t_book_authors (book_id, author_id) VALUES (23, 36);  -- Isaac Asimov

-- Murder on the Orient Express (24) by Agatha Christie
INSERT INTO t_book_authors (book_id, author_id) VALUES (24, 37);  -- Agatha Christie

-- The Old Man and the Sea (25) by Ernest Hemingway
INSERT INTO t_book_authors (book_id, author_id) VALUES (25, 38);  -- Ernest Hemingway

-- The Mote in God's Eye (26) by Larry Niven AND Jerry Pournelle
INSERT INTO t_book_authors (book_id, author_id) VALUES (26, 39);  -- Larry Niven
INSERT INTO t_book_authors (book_id, author_id) VALUES (26, 40);  -- Jerry Pournelle

-- Neuromancer (27) by William Gibson
INSERT INTO t_book_authors (book_id, author_id) VALUES (27, 41);  -- William Gibson

-- The Difference Engine (28) by William Gibson AND Bruce Sterling
INSERT INTO t_book_authors (book_id, author_id) VALUES (28, 41);  -- William Gibson
INSERT INTO t_book_authors (book_id, author_id) VALUES (28, 42);  -- Bruce Sterling

-- American Gods (29) by Neil Gaiman
INSERT INTO t_book_authors (book_id, author_id) VALUES (29, 34);  -- Neil Gaiman

-- Small Gods (30) by Terry Pratchett
INSERT INTO t_book_authors (book_id, author_id) VALUES (30, 33);  -- Terry Pratchett

-- CDs (Items with dtype='CD')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (11, 'CD', 'Abbey Road', 'The eleventh studio album by the Beatles, featuring iconic tracks like Come Together and Here Comes the Sun.', 14.99, 60,
        CURRENT_TIMESTAMP, '5099969945120', 'Apple Records', 'ROCK', 2826000000000, '1969-09-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (12, 'CD', 'A Night at the Opera', 'The fourth studio album by Queen, featuring the legendary Bohemian Rhapsody.', 13.99, 45, CURRENT_TIMESTAMP,
        '0602527644165', 'EMI', 'ROCK', 2586000000000, '1975-11-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (13, 'CD', 'The Rise and Fall of Ziggy Stardust', 'David Bowie''s fifth studio album, a concept album about a fictional rock star.', 12.99, 40,
        CURRENT_TIMESTAMP, '0724352190003', 'RCA Records', 'ROCK', 2334000000000, '1972-06-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (14, 'CD', 'Kind of Blue', 'Miles Davis'' masterpiece and the best-selling jazz album of all time.', 11.99, 70, CURRENT_TIMESTAMP, '5099706527220',
        'Columbia', 'JAZZ', 2754000000000, '1959-08-17');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (15, 'CD', 'Ella and Louis', 'A duet album featuring Ella Fitzgerald and Louis Armstrong.', 10.99, 35, CURRENT_TIMESTAMP, '0602498840382', 'Verve',
        'JAZZ', 2220000000000, '1956-10-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (16, 'CD', 'Blood on the Tracks', 'Bob Dylan''s fifteenth studio album, widely regarded as one of his greatest works.', 12.99, 50, CURRENT_TIMESTAMP,
        '5099751234524', 'Columbia', 'COUNTRY', 3126000000000, '1975-01-20');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (17, 'CD', 'I Never Loved a Man the Way I Love You', 'Aretha Franklin''s breakthrough album that established her as the Queen of Soul.', 11.99, 40,
        CURRENT_TIMESTAMP, '0081227965730', 'Atlantic', 'BLUES', 1962000000000, '1967-03-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (18, 'CD', 'Are You Experienced', 'The debut album by the Jimi Hendrix Experience, a landmark in rock history.', 13.99, 55, CURRENT_TIMESTAMP,
        '0886976340728', 'Track Records', 'ROCK', 2406000000000, '1967-05-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (19, 'CD', 'I Put a Spell on You', 'Nina Simone''s seventh studio album, featuring jazz and blues standards.', 10.99, 30, CURRENT_TIMESTAMP,
        '0600753458488', 'Philips', 'JAZZ', 2100000000000, '1965-06-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (20, 'CD', 'Symphony No. 9', 'Beethoven''s final complete symphony, featuring the famous Ode to Joy.', 15.99, 25, CURRENT_TIMESTAMP, '0028947753223',
        'Deutsche Grammophon', 'CLASSICAL', 4200000000000, '1824-05-07');

-- Additional CDs
-- Thriller by Michael Jackson
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (31, 'CD', 'Thriller', 'The best-selling album of all time, featuring iconic hits like Billie Jean and Beat It.', 14.99, 80,
        CURRENT_TIMESTAMP, '5099749534728', 'Epic Records', 'POP', 2524000000000, '1982-11-30');

-- The Dark Side of the Moon by Pink Floyd
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (32, 'CD', 'The Dark Side of the Moon', 'A progressive rock concept album exploring themes of conflict, greed, and mental illness.', 15.99, 55,
        CURRENT_TIMESTAMP, '5099902894522', 'Harvest', 'ROCK', 2580000000000, '1973-03-01');

-- Rumours by Fleetwood Mac
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (33, 'CD', 'Rumours', 'One of the best-selling albums ever, recorded during band turmoil and relationship breakups.', 13.99, 65,
        CURRENT_TIMESTAMP, '0081227959357', 'Warner Bros', 'ROCK', 2394000000000, '1977-02-04');

-- Back in Black by AC/DC
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (34, 'CD', 'Back in Black', 'A hard rock classic and tribute to late vocalist Bon Scott.', 12.99, 70,
        CURRENT_TIMESTAMP, '5099751283621', 'Atlantic', 'ROCK', 2532000000000, '1980-07-25');

-- Legend by Bob Marley
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (35, 'CD', 'Legend', 'The best-selling reggae album of all time, a greatest hits compilation.', 11.99, 60,
        CURRENT_TIMESTAMP, '0042284620321', 'Island Records', 'OTHER', 3360000000000, '1984-05-08');

-- What''s Going On by Marvin Gaye
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (36, 'CD', 'What''s Going On', 'A soul concept album addressing themes of poverty, war, and environmentalism.', 12.99, 45,
        CURRENT_TIMESTAMP, '0602498840252', 'Tamla', 'BLUES', 2130000000000, '1971-05-21');

-- The Velvet Underground & Nico
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (37, 'CD', 'The Velvet Underground & Nico', 'An influential debut album produced by Andy Warhol.', 13.99, 35,
        CURRENT_TIMESTAMP, '0602498505427', 'Verve', 'ROCK', 2946000000000, '1967-03-12');

-- Blue by Joni Mitchell
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (38, 'CD', 'Blue', 'A confessional singer-songwriter masterpiece about love and loss.', 11.99, 40,
        CURRENT_TIMESTAMP, '0075596059329', 'Reprise', 'COUNTRY', 2160000000000, '1971-06-22');

-- Pet Sounds by The Beach Boys
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (39, 'CD', 'Pet Sounds', 'An innovative album that influenced countless artists including The Beatles.', 12.99, 50,
        CURRENT_TIMESTAMP, '0602547091314', 'Capitol', 'POP', 2178000000000, '1966-05-16');

-- Led Zeppelin IV
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date)
VALUES (40, 'CD', 'Led Zeppelin IV', 'Features the iconic Stairway to Heaven and other hard rock classics.', 13.99, 60,
        CURRENT_TIMESTAMP, '0075678268526', 'Atlantic', 'ROCK', 2556000000000, '1971-11-08');

-- CD-Musician relationships
-- Abbey Road (CD 11) - The Beatles
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (11, 11);  -- John Lennon
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (11, 21);  -- Paul McCartney
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (11, 22);  -- George Harrison
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (11, 23);  -- Ringo Starr

-- A Night at the Opera (CD 12) - Queen
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (12, 12);  -- Freddie Mercury
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (12, 24);  -- Brian May
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (12, 25);  -- Roger Taylor
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (12, 26);  -- John Deacon

-- Ziggy Stardust (CD 13) - David Bowie
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (13, 13);  -- David Bowie

-- Kind of Blue (CD 14) - Miles Davis Sextet
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (14, 14);  -- Miles Davis
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (14, 27);  -- John Coltrane
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (14, 28);  -- Bill Evans
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (14, 29);  -- Cannonball Adderley

-- Ella and Louis (CD 15)
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (15, 15);  -- Ella Fitzgerald
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (15, 30);  -- Louis Armstrong

-- Blood on the Tracks (CD 16) - Bob Dylan
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (16, 16);  -- Bob Dylan

-- I Never Loved a Man (CD 17) - Aretha Franklin
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (17, 17);  -- Aretha Franklin

-- Are You Experienced (CD 18) - Jimi Hendrix Experience
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (18, 18);  -- Jimi Hendrix
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (18, 31);  -- Noel Redding
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (18, 32);  -- Mitch Mitchell

-- I Put a Spell on You (CD 19) - Nina Simone
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (19, 19);  -- Nina Simone

-- Symphony No. 9 (CD 20) - Beethoven
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (20, 20);  -- Ludwig van Beethoven

-- CD-Musician relationships for additional CDs
-- Thriller (CD 31) - Michael Jackson
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (31, 43);  -- Michael Jackson

-- The Dark Side of the Moon (CD 32) - Pink Floyd
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (32, 44);  -- Roger Waters
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (32, 45);  -- David Gilmour

-- Rumours (CD 33) - Fleetwood Mac
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (33, 46);  -- Stevie Nicks
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (33, 47);  -- Lindsey Buckingham

-- Back in Black (CD 34) - AC/DC
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (34, 48);  -- Angus Young
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (34, 49);  -- Brian Johnson

-- Legend (CD 35) - Bob Marley
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (35, 50);  -- Bob Marley

-- What's Going On (CD 36) - Marvin Gaye
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (36, 51);  -- Marvin Gaye

-- The Velvet Underground & Nico (CD 37)
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (37, 52);  -- Lou Reed
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (37, 53);  -- Nico

-- Blue (CD 38) - Joni Mitchell
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (38, 54);  -- Joni Mitchell

-- Pet Sounds (CD 39) - The Beach Boys
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (39, 55);  -- Brian Wilson
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (39, 56);  -- Mike Love

-- Led Zeppelin IV (CD 40)
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (40, 57);  -- Robert Plant
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (40, 58);  -- Jimmy Page
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (40, 59);  -- John Paul Jones
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (40, 60);  -- John Bonham

-- Tracks for CDs
-- Abbey Road (CD ID 11)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (1, 'Come Together', 259000000000, 1, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (2, 'Something', 182000000000, 2, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (3, 'Maxwell''s Silver Hammer', 207000000000, 3, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (4, 'Oh! Darling', 206000000000, 4, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (5, 'Octopus''s Garden', 170000000000, 5, 11);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (6, 'Here Comes the Sun', 185000000000, 6, 11);

-- A Night at the Opera (CD ID 12)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (7, 'Death on Two Legs', 223000000000, 1, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (8, 'Lazing on a Sunday Afternoon', 67000000000, 2, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (9, 'You''re My Best Friend', 170000000000, 3, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (10, 'Bohemian Rhapsody', 354000000000, 4, 12);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (11, 'God Save the Queen', 75000000000, 5, 12);

-- The Rise and Fall of Ziggy Stardust (CD ID 13)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (12, 'Five Years', 275000000000, 1, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (13, 'Soul Love', 213000000000, 2, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (14, 'Starman', 255000000000, 3, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (15, 'Ziggy Stardust', 194000000000, 4, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (16, 'Suffragette City', 206000000000, 5, 13);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (17, 'Rock ''n'' Roll Suicide', 180000000000, 6, 13);

-- Kind of Blue (CD ID 14)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (18, 'So What', 562000000000, 1, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (19, 'Freddie Freeloader', 588000000000, 2, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (20, 'Blue in Green', 327000000000, 3, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (21, 'All Blues', 690000000000, 4, 14);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (22, 'Flamenco Sketches', 567000000000, 5, 14);

-- Ella and Louis (CD ID 15)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (23, 'Can''t We Be Friends?', 206000000000, 1, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (24, 'Isn''t This a Lovely Day?', 296000000000, 2, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (25, 'Moonlight in Vermont', 201000000000, 3, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (26, 'They Can''t Take That Away from Me', 221000000000, 4, 15);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (27, 'Cheek to Cheek', 357000000000, 5, 15);

-- Blood on the Tracks (CD ID 16)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (28, 'Tangled Up in Blue', 352000000000, 1, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (29, 'Simple Twist of Fate', 256000000000, 2, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (30, 'You''re a Big Girl Now', 294000000000, 3, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (31, 'Idiot Wind', 467000000000, 4, 16);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (32, 'Shelter from the Storm', 300000000000, 5, 16);

-- I Never Loved a Man the Way I Love You (CD ID 17)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (33, 'Respect', 148000000000, 1, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (34, 'Drown in My Own Tears', 193000000000, 2, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (35, 'I Never Loved a Man', 172000000000, 3, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (36, 'Dr. Feelgood', 211000000000, 4, 17);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (37, 'A Change Is Gonna Come', 257000000000, 5, 17);

-- Are You Experienced (CD ID 18)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (38, 'Purple Haze', 170000000000, 1, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (39, 'Manic Depression', 210000000000, 2, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (40, 'Hey Joe', 202000000000, 3, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (41, 'The Wind Cries Mary', 200000000000, 4, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (42, 'Fire', 165000000000, 5, 18);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (43, 'Are You Experienced?', 254000000000, 6, 18);

-- I Put a Spell on You (CD ID 19)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (44, 'I Put a Spell on You', 156000000000, 1, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (45, 'Feeling Good', 177000000000, 2, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (46, 'Ne Me Quitte Pas', 218000000000, 3, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (47, 'Marriage Is for Old Folks', 134000000000, 4, 19);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (48, 'Tomorrow Is My Turn', 185000000000, 5, 19);

-- Symphony No. 9 (CD ID 20)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (49, 'I. Allegro ma non troppo', 960000000000, 1, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (50, 'II. Molto vivace', 720000000000, 2, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (51, 'III. Adagio molto e cantabile', 900000000000, 3, 20);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (52, 'IV. Presto - Ode to Joy', 1440000000000, 4, 20);

-- Tracks for additional CDs
-- Thriller (CD ID 31)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (53, 'Wanna Be Startin'' Somethin''', 363000000000, 1, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (54, 'Baby Be Mine', 260000000000, 2, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (55, 'The Girl Is Mine', 222000000000, 3, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (56, 'Thriller', 358000000000, 4, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (57, 'Beat It', 258000000000, 5, 31);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (58, 'Billie Jean', 294000000000, 6, 31);

-- The Dark Side of the Moon (CD ID 32)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (59, 'Speak to Me', 68000000000, 1, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (60, 'Breathe', 169000000000, 2, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (61, 'Time', 413000000000, 3, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (62, 'Money', 382000000000, 4, 32);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (63, 'Brain Damage', 226000000000, 5, 32);

-- Rumours (CD ID 33)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (64, 'Second Hand News', 163000000000, 1, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (65, 'Dreams', 257000000000, 2, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (66, 'Go Your Own Way', 217000000000, 3, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (67, 'The Chain', 270000000000, 4, 33);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (68, 'Don''t Stop', 193000000000, 5, 33);

-- Back in Black (CD ID 34)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (69, 'Hells Bells', 312000000000, 1, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (70, 'Shoot to Thrill', 317000000000, 2, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (71, 'Back in Black', 255000000000, 3, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (72, 'You Shook Me All Night Long', 210000000000, 4, 34);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (73, 'Rock and Roll Ain''t Noise Pollution', 255000000000, 5, 34);

-- Legend (CD ID 35)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (74, 'Is This Love', 231000000000, 1, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (75, 'No Woman, No Cry', 285000000000, 2, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (76, 'Jamming', 211000000000, 3, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (77, 'Redemption Song', 228000000000, 4, 35);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (78, 'One Love', 172000000000, 5, 35);

-- What's Going On (CD ID 36)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (79, 'What''s Going On', 233000000000, 1, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (80, 'What''s Happening Brother', 164000000000, 2, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (81, 'Mercy Mercy Me', 201000000000, 3, 36);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (82, 'Inner City Blues', 338000000000, 4, 36);

-- The Velvet Underground & Nico (CD ID 37)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (83, 'Sunday Morning', 175000000000, 1, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (84, 'I''m Waiting for the Man', 267000000000, 2, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (85, 'Femme Fatale', 157000000000, 3, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (86, 'Venus in Furs', 309000000000, 4, 37);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (87, 'All Tomorrow''s Parties', 359000000000, 5, 37);

-- Blue (CD ID 38)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (88, 'All I Want', 215000000000, 1, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (89, 'My Old Man', 213000000000, 2, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (90, 'Little Green', 201000000000, 3, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (91, 'A Case of You', 262000000000, 4, 38);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (92, 'River', 245000000000, 5, 38);

-- Pet Sounds (CD ID 39)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (93, 'Wouldn''t It Be Nice', 153000000000, 1, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (94, 'Sloop John B', 183000000000, 2, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (95, 'God Only Knows', 172000000000, 3, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (96, 'I Know There''s an Answer', 195000000000, 4, 39);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (97, 'Caroline, No', 174000000000, 5, 39);

-- Led Zeppelin IV (CD ID 40)
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (98, 'Black Dog', 295000000000, 1, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (99, 'Rock and Roll', 220000000000, 2, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (100, 'The Battle of Evermore', 352000000000, 3, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (101, 'Stairway to Heaven', 482000000000, 4, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (102, 'Misty Mountain Hop', 278000000000, 5, 40);
INSERT INTO t_tracks (id, title, duration, track_number, cd_fk) VALUES (103, 'When the Levee Breaks', 427000000000, 6, 40);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_tracks_seq RESTART WITH 104;

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_items_seq RESTART WITH 41;

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
