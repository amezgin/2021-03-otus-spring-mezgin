insert into users(login, password_hash, is_active) values ('admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', true);
insert into users(login, password_hash, is_active) values ('user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', true);
insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');
insert into author (full_name) values ('Гаррисон, Г.');
insert into author (full_name) values ('Перумов, Н.');
insert into author (full_name) values ('Толстой, Л.Н.');
insert into genre (genre_name) values ('Фантастика');
insert into genre (genre_name) values ('Фентези');
insert into genre (genre_name) values ('Роман');
insert into book (title, author_id) values ('Стальная крыса идет на войну', 1);
insert into book (title, author_id) values ('Стальная крыса спасает мир', 1);
insert into book (title, author_id) values ('Не время для драконов', 2);
insert into book_genre  (book_id, genre_id) values (1, 1);
insert into book_genre  (book_id, genre_id) values (2, 1);
insert into book_genre  (book_id, genre_id) values (3, 2);
insert into comment (user_name, text, book_id) values ('ADMIN', 'Классная книга, рекомендую!', 1);
insert into comment (user_name, text, book_id) values ('ADMIN', 'Прочитал в один заход!', 1);
insert into comment (user_name, text, book_id) values ('User', 'Редкая тягомутина! Сжечь!', 3);