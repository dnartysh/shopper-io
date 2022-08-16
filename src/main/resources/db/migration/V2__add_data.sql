insert into shopper.roles (id, name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into shopper.positions (id, name, description) values
(1, 'seller', 'Продавец'),
(2, 'manager', 'Менеджер'),
(3, 'storekeeper', 'Кладовщик'),
(4, 'admin', 'Админ'),
(5, 'newcomer', 'Новичок');

insert into shopper.shops (id, name, address, description) values
(1, 'Velikiy shop', 'Street Pushkina, 1', 'Prihodite syuda'),
(2, 'Vygodniy shop', 'Bulvar Vasileva, 15', 'Rabotaem bez vyhodnyh');

insert into shopper.storages (id, name, address, capacity, description) values
(1, 'Storage 1', 'Street Kolomoiskaya, 1', 1000, 'Storage 1 description'),
(2, 'Storage 2', 'Street Gruzdneva, 15', 1500, 'Storage 2 description');

insert into shopper.products (id, name, weight, price, description) values
(1, 'Product 1', 5, 200, 'Product 1 description'),
(2, 'Product 2', 20, 400, 'Product 2 description'),
(3, 'Product 3', 10, 487, 'Product 3 description'),
(4, 'Product 4', 3, 50, 'Product 4 description'),
(5, 'Product 5', 17, 158, 'Product 5 description');

insert into shopper.storage_products (id, storage_id, product_id, count) values
(1, 1, 1, 3),
(2, 1, 5, 5),
(3, 2, 2, 3);

insert into shopper.shop_products (id, shop_id, product_id, count) values
(1, 1, 3, 2),
(2, 2, 4, 3);

insert into shopper.users (id, username, password, firstname, lastname, image_path, birthdate,
                           registration_date, first_login, active, position_id) values
(1, 'admin', '$2a$10$sz8RiT4cOhGZy17iDCrSpejNyNcC2OH6Rk/jNj24OQD0tmkOM8mE.', 'Denis', 'Ivanov', null, '1993-01-01', '2022-01-01', 1, 1, 4),
(2, 'seller', '$2a$10$sz8RiT4cOhGZy17iDCrSpejNyNcC2OH6Rk/jNj24OQD0tmkOM8mE.', 'Masha', 'Petrova', null, null, '2022-02-12', 1, 1, 1),
(3, 'storekeeper', '$2a$10$sz8RiT4cOhGZy17iDCrSpejNyNcC2OH6Rk/jNj24OQD0tmkOM8mE.', 'Petya', 'Smirnov', null, '1995-05-09', '2021-08-22', 1, 1, 3),
(4, 'manager', '$2a$10$sz8RiT4cOhGZy17iDCrSpejNyNcC2OH6Rk/jNj24OQD0tmkOM8mE.', 'Victoria', 'Vasileva', null, null, '2021-08-22', 1, 1, 2),
(5, 'newcomer', '$2a$10$sz8RiT4cOhGZy17iDCrSpejNyNcC2OH6Rk/jNj24OQD0tmkOM8mE.', 'Petr', 'Yashin', null, null, '2021-03-21', 1, 1, 5);

insert into shopper.user_roles (id, user_id, role_id) values
(1, 1, 1),
(2, 2, 2),
(3, 3, 2),
(4, 4, 2);

insert into shopper.user_positions (id, user_id, position_id) values
(1, 1, 4),
(2, 2, 1),
(3, 3, 3),
(4, 4, 2);

insert into shopper.orders (id, product_id, count) values
(1, 1, 3),
(2, 2, 6),
(3, 3, 5),
(4, 4, 3),
(5, 5, 2);