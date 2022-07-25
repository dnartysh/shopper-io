create table users
(
    id int primary key not null auto_increment,
    username varchar(50) not null,
    password varchar(500) not null,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    image_path varchar(255),
    birthdate date,
    registration_date date not null,
    last_visit date,
    active boolean,
    position_id int not null
);

create table roles
(
    id int primary key not null auto_increment,
    name varchar(100) not null
);

create table user_roles
(
    id int primary key not null auto_increment,
    user_id int not null,
    role_id int not null
);

create table products
(
    id int primary key not null auto_increment,
    name varchar(200) not null,
    price double,
    weight double,
    description varchar(2000),
    storage_id int,
    shop_id int
);

create table storages
(
    id int primary key not null auto_increment,
    name varchar(200) not null,
    address varchar(250),
    capacity double,
    description varchar(2000)
);

create table storage_products
(
    id int primary key not null auto_increment,
    storage_id int not null,
    product_id int not null,
    count int
);

create table shop_products
(
    id int primary key not null auto_increment,
    shop_id int not null,
    product_id int not null,
    count int
);

create table shops
(
    id int primary key not null auto_increment,
    name varchar(200) not null,
    address varchar(250),
    description varchar(2000)
);

create table positions
(
    id int primary key not null auto_increment,
    name varchar(150) not null,
    description varchar(500)
);

create table user_positions
(
    id int primary key not null auto_increment,
    user_id int,
    position_id int
);

alter table user_roles add foreign key (user_id) references users(id);
alter table user_roles add foreign key (role_id) references roles(id);
alter table user_positions add foreign key (user_id) references users(id);
alter table user_positions add foreign key (position_id) references positions(id);