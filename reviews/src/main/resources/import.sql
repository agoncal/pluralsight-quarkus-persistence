-- Product Reviews for Books
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1001, 1, 'BOOK', 'john_doe', 5, 'Stephen King at his best!', 'The Shining is a masterpiece of horror. Absolutely terrifying and brilliantly written.', '2024-01-15 10:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1002, 1, 'BOOK', 'horror_fan', 4, 'Classic horror novel', 'A must-read for any horror fan. The atmosphere is incredible.', '2024-02-20 14:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1003, 1, 'BOOK', 'bookworm99', 5, 'Kept me up all night', 'Could not put this down. King is the master of suspense.', '2024-03-05 09:15:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1004, 2, 'BOOK', 'fantasy_queen', 5, 'Magical journey', 'Harry Potter changed my life. Pure magic from start to finish.', '2024-03-18 16:20:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1005, 2, 'BOOK', 'wizard_fan', 5, 'Perfect for all ages', 'Read it as a kid, still love it as an adult. Timeless classic.', '2024-04-02 11:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1006, 2, 'BOOK', 'muggle123', 4, 'Great start to the series', 'A wonderful introduction to the wizarding world.', '2024-04-25 08:30:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1007, 3, 'BOOK', 'retro_reader', 5, 'Dystopian perfection', '1984 is more relevant today than ever. A chilling warning.', '2024-05-10 13:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1008, 3, 'BOOK', 'lit_lover', 5, 'Essential reading', 'Everyone should read this book at least once in their life.', '2024-06-01 17:30:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1009, 4, 'BOOK', 'classic_reader', 5, 'Magical realism at its finest', 'One Hundred Years of Solitude is a literary masterpiece.', '2024-06-15 10:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1010, 4, 'BOOK', 'literature_prof', 5, 'Unforgettable', 'The Buendia family saga will stay with you forever.', '2024-07-08 15:15:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1011, 5, 'BOOK', 'japan_lover', 5, 'Beautifully surreal', 'Norwegian Wood captures the essence of youth and loss perfectly.', '2024-07-20 09:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1012, 5, 'BOOK', 'murakami_fan', 4, 'Haunting and beautiful', 'Murakami writes with such elegance. A melancholic masterpiece.', '2024-08-05 14:30:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1013, 6, 'BOOK', 'french_lit', 5, 'Epic and emotional', 'Les Miserables is one of the greatest novels ever written.', '2024-08-18 11:15:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1014, 7, 'BOOK', 'austen_addict', 5, 'Witty and romantic', 'Pride and Prejudice never gets old. Elizabeth Bennet is iconic.', '2024-09-01 16:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1015, 7, 'BOOK', 'romance_reader', 5, 'The ultimate love story', 'Darcy and Elizabeth forever! A perfect romance.', '2024-09-15 10:30:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1016, 8, 'BOOK', 'existential_reader', 5, 'Mind-bending', 'The Metamorphosis is strange, unsettling, and brilliant.', '2024-09-28 08:00:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1017, 9, 'BOOK', 'spiritual_seeker', 5, 'Life-changing', 'The Alchemist inspired me to follow my dreams.', '2024-10-10 12:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1018, 9, 'BOOK', 'traveler99', 4, 'Inspirational journey', 'A beautiful fable about pursuing your personal legend.', '2024-10-25 15:30:00');

INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1019, 10, 'BOOK', 'mystery_lover', 5, 'Intellectual thriller', 'The Name of the Rose is a medieval mystery masterpiece.', '2024-11-05 09:45:00');

-- Product Reviews for CDs (CDs have IDs 11-20 in catalog)
-- CD 11 = Abbey Road (Beatles)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2001, 11, 'CD', 'beatles_forever', 5, 'Legendary album', 'Abbey Road is the perfect swan song. The medley is incredible.', '2024-01-20 10:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2002, 11, 'CD', 'classic_rock_fan', 5, 'Timeless', 'Come Together, Something, Here Comes the Sun - all classics.', '2024-02-15 14:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2003, 11, 'CD', 'vinyl_collector', 5, 'Essential collection piece', 'Every music lover needs Abbey Road in their collection.', '2024-03-10 09:00:00');

-- CD 12 = A Night at the Opera (Queen)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2004, 12, 'CD', 'queen_fan', 5, 'Bohemian Rhapsody!', 'This album changed music forever. Queen at their absolute peak.', '2024-03-25 16:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2005, 12, 'CD', 'rock_enthusiast', 5, 'Operatic rock masterpiece', 'The range of styles on this album is incredible.', '2024-04-08 11:30:00');

-- CD 13 = Ziggy Stardust (Bowie)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2006, 13, 'CD', 'bowie_devotee', 5, 'Starman genius', 'Ziggy Stardust is Bowie at his most creative and daring.', '2024-04-22 08:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2007, 13, 'CD', 'glam_rock_lover', 4, 'Concept album perfection', 'The story of Ziggy is told brilliantly through the music.', '2024-05-05 13:15:00');

-- CD 14 = Kind of Blue (Miles Davis)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2008, 14, 'CD', 'jazz_aficionado', 5, 'The definitive jazz album', 'Kind of Blue is pure musical perfection. Essential listening.', '2024-05-18 10:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2009, 14, 'CD', 'trumpet_player', 5, 'Miles at his finest', 'So What and Blue in Green are transcendent pieces.', '2024-06-02 15:00:00');

-- CD 15 = Ella and Louis
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2010, 15, 'CD', 'jazz_collector', 5, 'Two legends together', 'The chemistry between Ella and Louis is magical.', '2024-06-16 09:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2011, 15, 'CD', 'vocal_jazz_fan', 5, 'Pure vocal magic', 'Their voices complement each other beautifully.', '2024-07-01 12:45:00');

-- CD 16 = Blood on the Tracks (Bob Dylan)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2012, 16, 'CD', 'dylan_fan', 5, 'Dylan at his most personal', 'Blood on the Tracks is heartbreaking and beautiful.', '2024-07-15 08:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2013, 16, 'CD', 'folk_rock_lover', 5, 'Lyrical genius', 'Tangled Up in Blue alone makes this album essential.', '2024-07-28 14:15:00');

-- CD 17 = Aretha Franklin
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2014, 17, 'CD', 'soul_music_fan', 5, 'Queen of Soul indeed', 'Aretha voice is simply unmatched. Powerful and moving.', '2024-08-10 11:00:00');

-- CD 18 = Are You Experienced (Jimi Hendrix)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2015, 18, 'CD', 'guitar_hero', 5, 'Revolutionary guitar work', 'Hendrix changed what was possible on electric guitar.', '2024-08-23 16:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2016, 18, 'CD', 'psychedelic_fan', 5, 'Mind-blowing debut', 'Purple Haze and Hey Joe are timeless classics.', '2024-09-05 09:15:00');

-- CD 19 = I Put a Spell on You (Nina Simone)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2017, 19, 'CD', 'jazz_vocals_lover', 5, 'Nina is mesmerizing', 'Her interpretation of the title track is haunting.', '2024-09-18 13:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2018, 19, 'CD', 'blues_enthusiast', 5, 'Emotional depth', 'Nina Simone pours her soul into every note.', '2024-10-02 10:00:00');

-- CD 20 = Symphony No. 9 (Beethoven)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2019, 20, 'CD', 'classical_purist', 5, 'Ode to Joy!', 'Beethoven ninth symphony is the pinnacle of classical music.', '2024-10-15 15:30:00');

-- Additional Book Reviews (Books 21-30)
-- Book 21 = Good Omens
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1020, 21, 'BOOK', 'comedy_lover', 5, 'Hilarious apocalypse', 'Pratchett and Gaiman together is pure genius. Laughed out loud!', '2024-11-10 10:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1021, 21, 'BOOK', 'angel_demon_fan', 5, 'Perfect duo', 'Aziraphale and Crowley are the best literary duo ever.', '2024-11-12 14:30:00');

-- Book 22 = The Hitchhiker's Guide to the Galaxy
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1022, 22, 'BOOK', 'scifi_nerd', 5, 'Don''t panic!', 'The funniest sci-fi book ever written. 42 is the answer!', '2024-11-15 09:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1023, 22, 'BOOK', 'towel_carrier', 5, 'Essential reading', 'Douglas Adams was a genius. This book changed my life.', '2024-11-18 11:30:00');

-- Book 23 = Foundation
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1024, 23, 'BOOK', 'asimov_fan', 5, 'Epic scope', 'Foundation is the blueprint for all space opera. Brilliant.', '2024-11-20 16:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1025, 23, 'BOOK', 'psychohistorian', 4, 'Visionary science fiction', 'Asimov predicted so much about our future. A must-read.', '2024-11-22 08:45:00');

-- Book 24 = Murder on the Orient Express
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1026, 24, 'BOOK', 'detective_fan', 5, 'Twist ending!', 'The ending blew my mind. Agatha Christie at her finest.', '2024-11-25 13:15:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1027, 24, 'BOOK', 'poirot_admirer', 5, 'Classic mystery', 'Poirot is the greatest detective. This case proves it.', '2024-11-28 10:30:00');

-- Book 25 = The Old Man and the Sea
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1028, 25, 'BOOK', 'hemingway_reader', 5, 'Simple perfection', 'Hemingway proves less is more. Profoundly moving.', '2024-12-01 15:00:00');

-- Book 26 = The Mote in God's Eye
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1029, 26, 'BOOK', 'first_contact_fan', 5, 'Best alien contact story', 'The Moties are the most realistic aliens in sci-fi.', '2024-12-03 09:30:00');

-- Book 27 = Neuromancer
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1030, 27, 'BOOK', 'cyberpunk_lover', 5, 'Genre-defining', 'Gibson invented cyberpunk with this masterpiece.', '2024-12-05 12:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1031, 27, 'BOOK', 'matrix_fan', 5, 'Ahead of its time', 'The Matrix owes everything to this book. Essential.', '2024-12-07 14:00:00');

-- Book 28 = The Difference Engine
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1032, 28, 'BOOK', 'steampunk_fan', 4, 'Victorian computing', 'Fascinating alternate history. Steampunk at its best.', '2024-12-09 10:15:00');

-- Book 29 = American Gods
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1033, 29, 'BOOK', 'mythology_buff', 5, 'Gods in America', 'Gaiman weaves mythology into modern America brilliantly.', '2024-12-11 16:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1034, 29, 'BOOK', 'roadtrip_reader', 5, 'Epic journey', 'Shadow''s journey is unforgettable. A modern classic.', '2024-12-13 08:00:00');

-- Book 30 = Small Gods
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1035, 30, 'BOOK', 'discworld_fan', 5, 'Pratchett''s best', 'A brilliant satire on religion. Om is hilarious!', '2024-12-15 11:45:00');

-- Additional Book Reviews (Books 41-50)
-- Book 41 = It
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1036, 41, 'BOOK', 'pennywise_survivor', 5, 'Terrifying!', 'Pennywise gave me nightmares for weeks. King''s scariest.', '2024-12-17 14:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1037, 41, 'BOOK', 'losers_club_member', 5, 'Epic horror', 'The friendship between the Losers is beautiful. And terrifying.', '2024-12-19 09:30:00');

-- Book 42 = The Stand
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1038, 42, 'BOOK', 'apocalypse_reader', 5, 'Post-apocalyptic masterpiece', 'The ultimate battle between good and evil. Unforgettable.', '2024-12-21 13:15:00');

-- Book 43 = Misery
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1039, 43, 'BOOK', 'thriller_addict', 5, 'Psychological terror', 'Annie Wilkes is the scariest villain King ever created.', '2024-12-23 10:00:00');

-- Book 44 = Harry Potter and the Chamber of Secrets
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1040, 44, 'BOOK', 'hp_collector', 5, 'Darker sequel', 'The mystery of the Chamber kept me guessing. Love Dobby!', '2024-12-25 15:30:00');

-- Book 45 = Harry Potter and the Prisoner of Azkaban
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1041, 45, 'BOOK', 'marauder_fan', 5, 'Best HP book', 'Sirius and Lupin are amazing. The time-turner twist is perfect.', '2024-12-27 08:45:00');

-- Book 46 = Harry Potter and the Goblet of Fire
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1042, 46, 'BOOK', 'triwizard_champion', 5, 'Game changer', 'The series gets darker here. The ending is devastating.', '2024-12-29 12:00:00');

-- Book 47 = Animal Farm
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1043, 47, 'BOOK', 'political_reader', 5, 'Brilliant allegory', 'Four legs good, two legs bad. Orwell''s genius in a fable.', '2025-01-02 14:30:00');

-- Book 48 = Kafka on the Shore
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1044, 48, 'BOOK', 'surrealist_reader', 5, 'Dreamlike', 'Murakami at his most surreal. The cat interviews are unforgettable.', '2025-01-04 09:15:00');

-- Book 49 = 1Q84
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1045, 49, 'BOOK', 'parallel_world_fan', 4, 'Ambitious epic', 'Two moons, two timelines. Murakami''s magnum opus.', '2025-01-06 11:00:00');

-- Book 50 = The Hunchback of Notre-Dame
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1046, 50, 'BOOK', 'hugo_devotee', 5, 'Gothic masterpiece', 'Quasimodo''s story is heartbreaking. Hugo at his finest.', '2025-01-08 16:45:00');

-- Book 51 = Sense and Sensibility
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1047, 51, 'BOOK', 'austen_collector', 5, 'Sisters and society', 'Elinor and Marianne are both so relatable. Beautiful story.', '2025-01-10 10:00:00');

-- Book 52 = Emma
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1048, 52, 'BOOK', 'matchmaker_fan', 4, 'Charming comedy', 'Emma is infuriating and lovable. Classic Austen wit.', '2025-01-12 14:30:00');

-- Book 60 = Colour of Magic
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1049, 60, 'BOOK', 'rincewind_fan', 5, 'Where it all began', 'The first Discworld book. Meet the Luggage!', '2025-01-14 09:00:00');

-- Book 61 = Mort
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1050, 61, 'BOOK', 'death_fan', 5, 'DEATH SPEAKS IN CAPS', 'Death is the best character in fiction. This book proves it.', '2025-01-16 11:30:00');

-- Book 62 = Coraline
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1051, 62, 'BOOK', 'other_mother_scared', 5, 'Creepy and wonderful', 'Gaiman writes the best scary stories for all ages.', '2025-01-18 16:00:00');

-- Book 63 = The Graveyard Book
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1052, 63, 'BOOK', 'nobody_owens_fan', 5, 'Raised by ghosts', 'Bod''s story is touching and spooky. Won the Newbery for good reason.', '2025-01-20 08:45:00');

-- Book 77 = Pet Sematary
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1053, 77, 'BOOK', 'burial_ground_reader', 5, 'Sometimes dead is better', 'The most disturbing King book. I still think about it.', '2025-01-22 13:15:00');

-- Book 78 = Salem''s Lot
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1054, 78, 'BOOK', 'vampire_hunter', 5, 'Small town vampires', 'King made vampires scary again. Barlow is terrifying.', '2025-01-24 10:30:00');

-- Book 79 = Carrie
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1055, 79, 'BOOK', 'prom_queen_reader', 5, 'Where it started', 'King''s first novel. Carrie''s revenge is unforgettable.', '2025-01-26 15:00:00');

-- Book 80 = Harry Potter and the Order of the Phoenix
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1056, 80, 'BOOK', 'dumbledores_army', 5, 'Rebellion begins', 'Umbridge is the worst. The DA chapters are fantastic.', '2025-01-28 09:30:00');

-- Book 85 = Stardust
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1057, 85, 'BOOK', 'fairy_tale_lover', 5, 'Modern fairy tale', 'Gaiman at his most romantic. A beautiful adventure.', '2025-01-30 12:45:00');

-- Book 86 = Neverwhere
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1058, 86, 'BOOK', 'london_below_fan', 5, 'Urban fantasy gem', 'The underground world of London is magical and dangerous.', '2025-02-01 14:00:00');

-- Book 87 = I, Robot
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1059, 87, 'BOOK', 'three_laws_fan', 5, 'Robot ethics', 'Asimov''s Three Laws shaped all AI fiction. Essential.', '2025-02-03 10:15:00');

-- Book 151 = Firestarter
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1060, 151, 'BOOK', 'pyro_reader', 4, 'Government conspiracy', 'Charlie is one of King''s best young protagonists.', '2025-02-05 16:30:00');

-- Book 155 = Foundation (duplicate entry in catalog)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1061, 155, 'BOOK', 'hari_seldon_fan', 5, 'Psychohistory rules', 'The math that predicts the future. Asimov was a visionary.', '2025-02-07 08:00:00');

-- Book 163 = Good Omens (duplicate)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1062, 163, 'BOOK', 'ineffable_fan', 5, 'Apocalypse comedy', 'The buddy comedy to end all buddy comedies. Literally.', '2025-02-09 11:45:00');

-- Book 169 = The Colour of Magic (duplicate)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (1063, 169, 'BOOK', 'twoflower_fan', 5, 'Tourist in Ankh-Morpork', 'Where Discworld began. The Luggage is iconic.', '2025-02-11 14:00:00');

-- Additional CD Reviews (CDs 31-40)
-- CD 31 = Thriller (Michael Jackson)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2020, 31, 'CD', 'mj_forever', 5, 'King of Pop!', 'The best-selling album ever for a reason. Every track is a hit.', '2024-10-20 10:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2021, 31, 'CD', 'eighties_kid', 5, 'Defined an era', 'Billie Jean, Beat It, Thriller - pure perfection.', '2024-10-22 14:30:00');

-- CD 32 = The Dark Side of the Moon (Pink Floyd)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2022, 32, 'CD', 'floyd_head', 5, 'Mind-expanding', 'Listen with headphones in the dark. Life-changing experience.', '2024-10-25 09:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2023, 32, 'CD', 'prog_rock_fan', 5, 'Perfect album', 'Every song flows into the next. A seamless masterpiece.', '2024-10-28 11:30:00');

-- CD 33 = Rumours (Fleetwood Mac)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2024, 33, 'CD', 'mac_fan', 5, 'Born from chaos', 'Amazing that such beauty came from so much drama.', '2024-10-30 16:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2025, 33, 'CD', 'seventies_lover', 5, 'Timeless', 'The Chain, Go Your Own Way, Dreams - all classics.', '2024-11-02 08:45:00');

-- CD 34 = Back in Black (AC/DC)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2026, 34, 'CD', 'hard_rock_fan', 5, 'Pure energy', 'Brian Johnson proved he was the right choice. Incredible.', '2024-11-05 13:15:00');

-- CD 35 = Legend (Bob Marley)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2027, 35, 'CD', 'reggae_lover', 5, 'One love', 'Every song is a message of peace. Bob lives forever.', '2024-11-08 10:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2028, 35, 'CD', 'jamaican_roots', 5, 'Essential reggae', 'No Woman No Cry still makes me emotional every time.', '2024-11-10 15:00:00');

-- CD 36 = What's Going On (Marvin Gaye)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2029, 36, 'CD', 'soul_collector', 5, 'Socially conscious soul', 'Still relevant today. Marvin was ahead of his time.', '2024-11-13 09:30:00');

-- CD 37 = The Velvet Underground & Nico
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2030, 37, 'CD', 'avant_garde_fan', 5, 'Influential debut', 'Everyone who bought this album started a band.', '2024-11-16 12:45:00');

-- CD 38 = Blue (Joni Mitchell)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2031, 38, 'CD', 'singer_songwriter_fan', 5, 'Raw emotion', 'Joni bares her soul. The most honest album ever made.', '2024-11-19 14:00:00');

-- CD 39 = Pet Sounds (Beach Boys)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2032, 39, 'CD', 'brian_wilson_fan', 5, 'Ahead of its time', 'God Only Knows is the most beautiful song ever written.', '2024-11-22 10:15:00');

-- CD 40 = Led Zeppelin IV
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2033, 40, 'CD', 'zeppelin_fan', 5, 'Stairway!', 'Stairway to Heaven alone makes this essential. But every track rocks.', '2024-11-25 16:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2034, 40, 'CD', 'rock_historian', 5, 'Hard rock perfection', 'Black Dog, Rock and Roll, When the Levee Breaks - legendary.', '2024-11-28 08:00:00');

-- Additional CD Reviews (CDs 101-150)
-- CD 101 = Nevermind (Nirvana)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2035, 101, 'CD', 'grunge_kid', 5, 'Changed everything', 'Smells Like Teen Spirit killed hair metal. Thank you Kurt.', '2024-12-01 11:45:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2036, 101, 'CD', 'nineties_nostalgia', 5, 'Voice of a generation', 'This album defined the 90s. Raw and real.', '2024-12-03 14:00:00');

-- CD 102 = OK Computer (Radiohead)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2037, 102, 'CD', 'radiohead_obsessed', 5, 'Prophetic', 'Paranoid Android predicted our digital dystopia.', '2024-12-06 09:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2038, 102, 'CD', 'art_rock_fan', 5, 'Masterpiece', 'Every listen reveals something new. Endlessly rewarding.', '2024-12-08 13:15:00');

-- CD 103 = The Joshua Tree (U2)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2039, 103, 'CD', 'u2_believer', 5, 'American dream', 'Where the Streets Have No Name gives me chills every time.', '2024-12-11 10:00:00');

-- CD 104 = Purple Rain (Prince)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2040, 104, 'CD', 'prince_devotee', 5, 'Genius at work', 'Prince could do everything. This album proves it.', '2024-12-14 15:30:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2041, 104, 'CD', 'purple_one_fan', 5, 'Iconic', 'When Doves Cry, Lets Go Crazy, Purple Rain - perfection.', '2024-12-16 08:45:00');

-- CD 105 = Born to Run (Bruce Springsteen)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2042, 105, 'CD', 'boss_fan', 5, 'American epic', 'Thunder Road is the greatest opening track ever.', '2024-12-19 12:00:00');

-- CD 106 = Appetite for Destruction (Guns N' Roses)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2043, 106, 'CD', 'gnr_fan', 5, 'Raw power', 'Welcome to the Jungle still hits hard after all these years.', '2024-12-22 14:30:00');

-- CD 107 = The Wall (Pink Floyd)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2044, 107, 'CD', 'floyd_collector', 5, 'Epic rock opera', 'Another Brick in the Wall spoke to every kid in school.', '2024-12-25 09:15:00');

-- CD 108 = London Calling (The Clash)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2045, 108, 'CD', 'punk_historian', 5, 'Punk evolved', 'The Clash proved punk could be diverse and smart.', '2024-12-28 11:00:00');

-- CD 109 = Hotel California (Eagles)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2046, 109, 'CD', 'california_dreamer', 5, 'Classic rock perfection', 'That guitar solo at the end is pure magic.', '2024-12-31 16:45:00');

-- CD 110 = Wish You Were Here (Pink Floyd)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2047, 110, 'CD', 'syd_barrett_tribute', 5, 'Emotional tribute', 'Shine On You Crazy Diamond is heartbreakingly beautiful.', '2025-01-03 10:00:00');

-- CD 115 = Sgt. Pepper's (Beatles)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2048, 115, 'CD', 'beatlemania', 5, 'Revolutionary', 'Changed what an album could be. A Day in the Life is perfect.', '2025-01-06 14:30:00');

-- CD 116 = Revolver (Beatles)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2049, 116, 'CD', 'tomorrow_never_knows', 5, 'Experimental Beatles', 'Eleanor Rigby and Tomorrow Never Knows - incredible range.', '2025-01-09 09:00:00');

-- CD 118 = A Love Supreme (John Coltrane)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2050, 118, 'CD', 'coltrane_disciple', 5, 'Spiritual jazz', 'This album is a religious experience. Transcendent.', '2025-01-12 11:30:00');

-- CD 120 = Bitches Brew (Miles Davis)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2051, 120, 'CD', 'fusion_pioneer', 5, 'Mind-blowing fusion', 'Miles invented jazz fusion here. Still sounds futuristic.', '2025-01-15 16:00:00');

-- CD 121 = Songs in the Key of Life (Stevie Wonder)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2052, 121, 'CD', 'stevie_devotee', 5, 'Musical genius', 'Sir Duke, Isn''t She Lovely - every song is perfect.', '2025-01-18 08:45:00');

-- CD 123 = Off the Wall (Michael Jackson)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2053, 123, 'CD', 'disco_lover', 5, 'Pre-Thriller brilliance', 'Don''t Stop ''Til You Get Enough started it all.', '2025-01-21 13:15:00');

-- CD 127 = Graceland (Paul Simon)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2054, 127, 'CD', 'world_music_fan', 5, 'Cultural fusion', 'South African sounds meet American songwriting. Beautiful.', '2025-01-24 10:30:00');

-- CD 131 = Physical Graffiti (Led Zeppelin)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2055, 131, 'CD', 'kashmir_fan', 5, 'Epic double album', 'Kashmir alone justifies this album. But there''s so much more.', '2025-01-27 15:00:00');

-- CD 143 = Automatic for the People (R.E.M.)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2056, 143, 'CD', 'rem_fan', 5, 'Melancholic beauty', 'Everybody Hurts and Man on the Moon - emotional masterpieces.', '2025-01-30 09:30:00');

-- CD 144 = Ten (Pearl Jam)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2057, 144, 'CD', 'eddie_vedder_fan', 5, 'Grunge anthem', 'Alive, Jeremy, Even Flow - this album changed rock.', '2025-02-02 12:45:00');

-- CD 146 = Kid A (Radiohead)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2058, 146, 'CD', 'experimental_fan', 5, 'Brave reinvention', 'Radiohead threw away the rulebook. Everything In Its Right Place.', '2025-02-05 14:00:00');

-- CD 148 = Loveless (My Bloody Valentine)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2059, 148, 'CD', 'shoegaze_lover', 5, 'Wall of sound', 'The most beautiful noise ever recorded. Groundbreaking.', '2025-02-08 10:15:00');

-- CD 149 = Doolittle (Pixies)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2060, 149, 'CD', 'pixies_fan', 5, 'Alternative rock blueprint', 'Debaser, Here Comes Your Man, Monkey Gone to Heaven - perfect.', '2025-02-11 16:30:00');

-- CD 150 = Disintegration (The Cure)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2061, 150, 'CD', 'goth_romantic', 5, 'Dark beauty', 'Pictures of You and Lovesong are achingly beautiful.', '2025-02-14 08:00:00');

-- Additional CD Reviews (CDs 170-200)
-- CD 170 = Appetite for Destruction (duplicate)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2062, 170, 'CD', 'sunset_strip_fan', 5, 'Debut perfection', 'Sweet Child O Mine, Paradise City - instant classics.', '2025-02-17 11:45:00');

-- CD 173 = Metallica (Black Album)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2063, 173, 'CD', 'metal_head', 5, 'Heavy and accessible', 'Enter Sandman brought metal to the masses. Nothing Else Matters is beautiful.', '2025-02-20 14:00:00');
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2064, 173, 'CD', 'thrash_fan', 4, 'Commercial but great', 'Some say they sold out, but these songs are undeniable.', '2025-02-23 09:30:00');

-- CD 174 = Master of Puppets (Metallica)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2065, 174, 'CD', 'cliff_burton_fan', 5, 'Thrash perfection', 'The title track is the greatest metal song ever written.', '2025-02-26 13:15:00');

-- CD 177 = Blood Sugar Sex Magik (RHCP)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2066, 177, 'CD', 'funk_rock_lover', 5, 'Funk rock perfection', 'Under the Bridge, Give It Away - the Chili Peppers at their peak.', '2025-03-01 10:00:00');

-- CD 178 = Californication (RHCP)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2067, 178, 'CD', 'frusciante_fan', 5, 'Comeback album', 'John Frusciante''s return made this magic. Scar Tissue is haunting.', '2025-03-04 15:30:00');

-- CD 179 = Superunknown (Soundgarden)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2068, 179, 'CD', 'seattle_sound', 5, 'Grunge peak', 'Black Hole Sun is hypnotic. Chris Cornell''s voice was unmatched.', '2025-03-07 08:45:00');

-- CD 180 = Dirt (Alice in Chains)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2069, 180, 'CD', 'dark_grunge_fan', 5, 'Beautiful darkness', 'Rooster and Down in a Hole are devastatingly good.', '2025-03-10 12:00:00');

-- CD 181 = Siamese Dream (Smashing Pumpkins)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2070, 181, 'CD', 'pumpkins_fan', 5, 'Layered masterpiece', 'Today and Disarm are beautiful. Cherub Rock rocks.', '2025-03-13 14:30:00');

-- CD 183 = Grace (Jeff Buckley)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2071, 183, 'CD', 'hallelujah_lover', 5, 'Voice of an angel', 'His Hallelujah cover is the definitive version. Gone too soon.', '2025-03-16 09:15:00');

-- CD 184 = Violator (Depeche Mode)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2072, 184, 'CD', 'synth_pop_fan', 5, 'Electronic perfection', 'Personal Jesus and Enjoy the Silence are timeless.', '2025-03-19 11:00:00');

-- CD 186 = The Downward Spiral (Nine Inch Nails)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2073, 186, 'CD', 'industrial_fan', 5, 'Intense and cathartic', 'Closer and Hurt are unforgettable. Raw emotion.', '2025-03-22 16:45:00');

-- CD 188 = Jagged Little Pill (Alanis Morissette)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2074, 188, 'CD', 'nineties_girl', 5, 'Angry and empowering', 'You Oughta Know is still cathartic. Ironic is iconic.', '2025-03-25 10:00:00');

-- CD 192 = Weezer Blue Album
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2075, 192, 'CD', 'geek_rock_fan', 5, 'Power pop perfection', 'Buddy Holly, Say It Ain''t So, Undone - all killers.', '2025-03-28 14:30:00');

-- CD 194 = Parachutes (Coldplay)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2076, 194, 'CD', 'yellow_fan', 5, 'Beautiful debut', 'Yellow and Trouble are gorgeous. Before they got too big.', '2025-03-31 09:00:00');

-- CD 196 = Is This It (The Strokes)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2077, 196, 'CD', 'garage_rock_revival', 5, 'Revived rock', 'Last Nite started the 2000s rock revival. So cool.', '2025-04-03 11:30:00');

-- CD 197 = Hot Fuss (The Killers)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2078, 197, 'CD', 'mr_brightside_fan', 5, 'Arena rock reborn', 'Mr. Brightside never gets old. Somebody Told Me rocks.', '2025-04-06 16:00:00');

-- CD 198 = Elephant (The White Stripes)
INSERT INTO t_reviews (id, item_id, item_type, username, rating, title, comment, createdDate)
VALUES (2079, 198, 'CD', 'seven_nation_army', 5, 'Blues rock stripped down', 'That riff. Everyone knows that riff. Genius.', '2025-04-09 08:45:00');
