CREATE TABLE users
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    varchar(100) NOT NULL UNIQUE,
    balance bigint(100)  NOT NULL
);

CREATE TABLE items
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     varchar(100) NOT NULL UNIQUE,
    price    bigint(100)  NOT NULL,
    category varchar(100) NOT NULL
);

CREATE TABLE discounts
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id     bigint      NOT NULL,
    discount bigint(100) NOT NULL
);

CREATE TABLE orders
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_Id     bigint      NOT NULL,
    item_id     bigint      NOT NULL
);

CREATE TABLE purchase
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_Id     bigint      NOT NULL,
    item_id     bigint      NOT NULL
);

INSERT INTO users(name, balance)
VALUES ('Soroka Aleks', 10.0),
       ('Ivanov Ivan', 15000.0),
       ('Sidorov Petro', 5000.0),
       ('Petrov Aleksandr', 300.0);

INSERT INTO items(name, price, category)
VALUES ('Beer', 5.0, 'DRINK'),
       ('Cheese', 10.0, 'FOOD'),
       ('Bread', 4.0, 'FOOD'),
       ('Water', 3.0, 'DRINK'),
       ('Soap', 8.0, 'HOUSEHOLD'),
       ('Washing powder', 12.0, 'HOUSEHOLD'),
       ('Pen', 1.0, 'OTHER'),
       ('Pencil', 1.5, 'OTHER');

INSERT INTO discounts(item_id, discount)
VALUES (SELECT ID FROM items WHERE name = 'Cheese', 10),
       (SELECT ID FROM items WHERE name = 'Washing powder', 29),
       (SELECT ID FROM items WHERE name = 'Soap', 15),
       (SELECT ID FROM items WHERE name = 'Beer', 44),
       (SELECT ID FROM items WHERE name = 'Pen', 13),
       (SELECT ID FROM items WHERE name = 'Bread', 11);