create table users
(
    id int primary key not null,
    login varchar(40) not null,
    password varchar(40) not null,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    birthdate date,
    registration_date date not null,
    active boolean
);

create table roles
(
    id int primary key not null,
    name varchar(100) not null
);

create table user_roles
(
    id int primary key not null,
    user_id int,
    role_id int
);

create table products
(
    id int primary key not null,
    name varchar(200) not null,
    price double,
    weight double,
    description varchar(2000),
    storage_id int,
    shop_id int
);

create table storages
(
    id int primary key not null,
    name varchar(200) not null,
    address varchar(250),
    capacity double,
    description varchar(2000)
);

create table shops
(
    id int primary key not null,
    name varchar(200) not null,
    address varchar(250),
    description varchar(2000)
);

create table positions
(
    id int primary key not null,
    name varchar(150) not null
);

create table user_positions
(
    id int primary key not null,
    user_id int,
    position_id int
);

alter table user_roles add foreign key (user_id) references users(id);
alter table user_roles add foreign key (role_id) references roles(id);
alter table user_positions add foreign key (user_id) references users(id);
alter table user_positions add foreign key (position_id) references positions(id);