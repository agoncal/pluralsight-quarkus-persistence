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

-- Books (Items with dtype='BOOK')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (1, 'BOOK', 'The Shining', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence.', 14.99, 50, CURRENT_TIMESTAMP, '9780385121675', 447, '1977-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (2, 'BOOK', 'Harry Potter and the Philosopher''s Stone', 'A young wizard discovers his magical heritage and attends Hogwarts School of Witchcraft and Wizardry.', 12.99, 100, CURRENT_TIMESTAMP, '9780747532699', 223, '1997-06-26', 'ENGLISH', 10);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (3, 'BOOK', '1984', 'A dystopian novel set in a totalitarian society under constant surveillance.', 11.99, 75, CURRENT_TIMESTAMP, '9780451524935', 328, '1949-06-08', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (4, 'BOOK', 'One Hundred Years of Solitude', 'The multi-generational story of the Buendía family in the fictional town of Macondo.', 15.99, 40, CURRENT_TIMESTAMP, '9780060883287', 417, '1967-05-30', 'SPANISH', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (5, 'BOOK', 'Norwegian Wood', 'A nostalgic story of loss and sexuality set in Tokyo during the late 1960s.', 13.99, 60, CURRENT_TIMESTAMP, '9780375704024', 296, '1987-09-04', 'JAPANESE', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (6, 'BOOK', 'Les Misérables', 'An epic tale of redemption, justice, and love in 19th century France.', 18.99, 30, CURRENT_TIMESTAMP, '9780140444308', 1232, '1862-01-01', 'FRENCH', 4);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (7, 'BOOK', 'Pride and Prejudice', 'A witty commentary on society and romance in Regency-era England.', 9.99, 80, CURRENT_TIMESTAMP, '9780141439518', 279, '1813-01-28', 'ENGLISH', 1);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (8, 'BOOK', 'The Metamorphosis', 'A traveling salesman wakes up one morning to find himself transformed into a giant insect.', 8.99, 90, CURRENT_TIMESTAMP, '9780805209990', 55, '1915-01-01', 'GERMAN', 5);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (9, 'BOOK', 'The Alchemist', 'A philosophical story about a shepherd boy who travels in search of treasure and discovers the true meaning of life.', 14.99, 120, CURRENT_TIMESTAMP, '9780061122415', 208, '1988-01-01', 'PORTUGUESE', 2);
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, isbn, nb_of_pages, publication_date, language, publisher_fk) VALUES (10, 'BOOK', 'The Name of the Rose', 'A medieval murder mystery set in an Italian monastery.', 16.99, 35, CURRENT_TIMESTAMP, '9780151446476', 536, '1980-01-01', 'ITALIAN', 4);

-- Book-Author relationships
INSERT INTO t_book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO t_book_authors (book_id, author_id) VALUES (2, 2);
INSERT INTO t_book_authors (book_id, author_id) VALUES (3, 3);
INSERT INTO t_book_authors (book_id, author_id) VALUES (4, 4);
INSERT INTO t_book_authors (book_id, author_id) VALUES (5, 5);
INSERT INTO t_book_authors (book_id, author_id) VALUES (6, 6);
INSERT INTO t_book_authors (book_id, author_id) VALUES (7, 7);
INSERT INTO t_book_authors (book_id, author_id) VALUES (8, 8);
INSERT INTO t_book_authors (book_id, author_id) VALUES (9, 9);
INSERT INTO t_book_authors (book_id, author_id) VALUES (10, 10);

-- CDs (Items with dtype='CD')
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (11, 'CD', 'Abbey Road', 'The eleventh studio album by the Beatles, featuring iconic tracks like Come Together and Here Comes the Sun.', 14.99, 60, CURRENT_TIMESTAMP, '5099969945120', 'Apple Records', 'ROCK', 2826000000000, '1969-09-26');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (12, 'CD', 'A Night at the Opera', 'The fourth studio album by Queen, featuring the legendary Bohemian Rhapsody.', 13.99, 45, CURRENT_TIMESTAMP, '0602527644165', 'EMI', 'ROCK', 2586000000000, '1975-11-21');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (13, 'CD', 'The Rise and Fall of Ziggy Stardust', 'David Bowie''s fifth studio album, a concept album about a fictional rock star.', 12.99, 40, CURRENT_TIMESTAMP, '0724352190003', 'RCA Records', 'ROCK', 2334000000000, '1972-06-16');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (14, 'CD', 'Kind of Blue', 'Miles Davis'' masterpiece and the best-selling jazz album of all time.', 11.99, 70, CURRENT_TIMESTAMP, '5099706527220', 'Columbia', 'JAZZ', 2754000000000, '1959-08-17');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (15, 'CD', 'Ella and Louis', 'A duet album featuring Ella Fitzgerald and Louis Armstrong.', 10.99, 35, CURRENT_TIMESTAMP, '0602498840382', 'Verve', 'JAZZ', 2220000000000, '1956-10-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (16, 'CD', 'Blood on the Tracks', 'Bob Dylan''s fifteenth studio album, widely regarded as one of his greatest works.', 12.99, 50, CURRENT_TIMESTAMP, '5099751234524', 'Columbia', 'COUNTRY', 3126000000000, '1975-01-20');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (17, 'CD', 'I Never Loved a Man the Way I Love You', 'Aretha Franklin''s breakthrough album that established her as the Queen of Soul.', 11.99, 40, CURRENT_TIMESTAMP, '0081227965730', 'Atlantic', 'BLUES', 1962000000000, '1967-03-10');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (18, 'CD', 'Are You Experienced', 'The debut album by the Jimi Hendrix Experience, a landmark in rock history.', 13.99, 55, CURRENT_TIMESTAMP, '0886976340728', 'Track Records', 'ROCK', 2406000000000, '1967-05-12');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (19, 'CD', 'I Put a Spell on You', 'Nina Simone''s seventh studio album, featuring jazz and blues standards.', 10.99, 30, CURRENT_TIMESTAMP, '0600753458488', 'Philips', 'JAZZ', 2100000000000, '1965-06-01');
INSERT INTO t_items (id, dtype, title, description, price, stock, created_date, ean, music_company, genre, total_duration, release_date) VALUES (20, 'CD', 'Symphony No. 9', 'Beethoven''s final complete symphony, featuring the famous Ode to Joy.', 15.99, 25, CURRENT_TIMESTAMP, '0028947753223', 'Deutsche Grammophon', 'CLASSICAL', 4200000000000, '1824-05-07');

-- CD-Musician relationships
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (11, 11);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (12, 12);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (13, 13);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (14, 14);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (15, 15);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (16, 16);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (17, 17);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (18, 18);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (19, 19);
INSERT INTO t_cd_musicians (cd_id, musician_id) VALUES (20, 20);

-- Reset sequence for PostgreSQL
ALTER SEQUENCE t_items_seq RESTART WITH 21;

-- Users (using IDENTITY strategy, so we let the database auto-generate IDs)
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('admin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'admin@vintagestore.com', 'ADMIN', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('manager', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'manager@vintagestore.com', 'MANAGER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('johndoe', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'john.doe@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('janesmith', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'jane.smith@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('bobwilson', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'bob.wilson@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('alicejones', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'alice.jones@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('charlieB', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'charlie.brown@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('dianamiller', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'diana.miller@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('evandavis', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'evan.davis@email.com', 'CUSTOMER', false, CURRENT_TIMESTAMP);
INSERT INTO t_users (username, password, email, role, enabled, created_date) VALUES ('fionagarcia', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'fiona.garcia@email.com', 'CUSTOMER', true, CURRENT_TIMESTAMP);

-- Customers (linked to users, using IDENTITY strategy)
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Admin', 'User', '+1-555-000-0001', '1 Admin Plaza', 'New York', 'NY', '10001', 'USA', '1 Admin Plaza', 'New York', 'NY', '10001', 'USA', 1, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Manager', 'User', '+1-555-000-0002', '2 Manager Ave', 'Los Angeles', 'CA', '90001', 'USA', '2 Manager Ave', 'Los Angeles', 'CA', '90001', 'USA', 2, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('John', 'Doe', '+1-555-123-4567', '123 Main Street', 'Chicago', 'IL', '60601', 'USA', '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', 3, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Jane', 'Smith', '+1-555-234-5678', '789 Elm Road', 'Houston', 'TX', '77001', 'USA', '789 Elm Road', 'Houston', 'TX', '77001', 'USA', 4, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Bob', 'Wilson', '+1-555-345-6789', '321 Pine Lane', 'Phoenix', 'AZ', '85001', 'USA', '654 Maple Drive', 'Phoenix', 'AZ', '85002', 'USA', 5, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Alice', 'Jones', '+1-555-456-7890', '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA', '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA', 6, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Charlie', 'Brown', '+1-555-567-8901', '890 Birch Boulevard', 'San Antonio', 'TX', '78201', 'USA', '890 Birch Boulevard', 'San Antonio', 'TX', '78201', 'USA', 7, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Diana', 'Miller', '+1-555-678-9012', '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', 8, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Evan', 'Davis', '+1-555-789-0123', '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', 9, CURRENT_TIMESTAMP);
INSERT INTO t_customers (first_name, last_name, phone, billing_street, billing_city, billing_state, billing_zip, billing_country, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, user_fk, created_date) VALUES ('Fiona', 'Garcia', '+1-555-890-1234', '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', 10, CURRENT_TIMESTAMP);

-- Purchase Orders (linked to customers, using IDENTITY strategy)
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-01-15 10:30:00', 'DELIVERED', 29.98, 3, '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-01-20 14:45:00', 'DELIVERED', 42.97, 4, '789 Elm Road', 'Houston', 'TX', '77001', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-02-05 09:15:00', 'SHIPPED', 27.98, 5, '654 Maple Drive', 'Phoenix', 'AZ', '85002', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-02-10 16:20:00', 'CONFIRMED', 55.96, 3, '456 Oak Avenue', 'Chicago', 'IL', '60602', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-02-15 11:00:00', 'PENDING', 14.99, 6, '567 Cedar Court', 'Philadelphia', 'PA', '19101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-02-20 13:30:00', 'DELIVERED', 38.97, 7, '890 Birch Boulevard', 'San Antonio', 'TX', '78201', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-03-01 08:45:00', 'SHIPPED', 25.98, 8, '234 Walnut Way', 'San Diego', 'CA', '92101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-03-05 15:10:00', 'CANCELLED', 16.99, 9, '678 Spruce Street', 'Dallas', 'TX', '75201', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-03-10 10:00:00', 'CONFIRMED', 47.97, 10, '901 Ash Avenue', 'San Jose', 'CA', '95101', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_purchase_orders (order_date, status, total_amount, customer_fk, shipping_street, shipping_city, shipping_state, shipping_zip, shipping_country, created_date) VALUES ('2024-03-15 12:30:00', 'PENDING', 31.98, 4, '789 Elm Road', 'Houston', 'TX', '77001', 'USA', CURRENT_TIMESTAMP);

-- Suppliers (using IDENTITY strategy)
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Acme Publishing', 'John Smith', 'john.smith@acmepub.com', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Global Music Distribution', 'Maria Garcia', 'maria@globalmusicdist.com', 'Spain', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Nordic Books AB', 'Erik Johansson', 'erik@nordicbooks.se', 'Sweden', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Tokyo Media Corp', 'Yuki Tanaka', 'y.tanaka@tokyomedia.jp', 'Japan', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('British Publishing Ltd', 'James Wilson', 'jwilson@britishpub.co.uk', 'UK', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Vinyl Records Inc', 'Sarah Johnson', 'sarah@vinylrecords.com', 'USA', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Deutsche Medien GmbH', 'Hans Mueller', 'h.mueller@deutschemedien.de', 'Germany', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Paris Entertainment SA', 'Sophie Dubois', 'sophie@parisentertainment.fr', 'France', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Sydney Distributors Pty', 'Michael Brown', 'mbrown@sydneydist.com.au', 'Australia', CURRENT_TIMESTAMP);
INSERT INTO t_suppliers (company_name, contact_name, contact_email, country, created_date) VALUES ('Maple Leaf Media', 'Jennifer Taylor', 'jtaylor@mapleleafmedia.ca', 'Canada', CURRENT_TIMESTAMP);
