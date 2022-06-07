insert into shopper.roles (id, name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into shopper.positions (id, name) values
(1, 'Seller'),
(2, 'Manager'),
(3, 'Storekeeper'),
(4, 'Admin'),
(5, 'Newcomer');

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

insert into shopper.users (id, login, password, firstname, lastname, birthdate,
                           registration_date, active, position_id) values
(1, 'deniska', '1111', 'Denis', 'Ivanov', '1993-01-01', '2022-01-01', 1, 4),
(2, 'mashka', '1323', 'Masha', 'Petrova', null, '2022-02-12', 1, 1),
(3, 'petka', '4334', 'Petya', 'Smirnov', '1995-05-09', '2021-08-22', 1, 3),
(4, 'vika555', '6764', 'Victoria', 'Vasileva', null, '2021-08-22', 1, 2);

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