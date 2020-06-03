
------------

**Technologies:**
- SpringBoot;
- JPA;
- H2;
- Flyway;
- Lombok;

------------
Insert before using

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


------------
**REST JSON**

**USER**
1. POST http://localhost:8080/users //add new user
{
	"name" : "User4",
	"balance" : "1000.0"
}
2. GET http://localhost:8080/users/name/{name} //get by name
3. GET http://localhost:8080/users/{id} //get by id
4. GET http://localhost:8080/users //get all users
5. DELETE http://localhost:8080/users/{id} //delete by id
6. PUT http://localhost:8080/users/{id} //add money into balance

**ITEM**
1. POST http://localhost:8080/items //add new item
{
	"name" : "Carrot",
	"price" : "15.0",
	"category" : "FOOD"
}
2. GET http://localhost:8080/items/{id} //get by id
3. GET http://localhost:8080/items/name/{name} //get by name
4. GET http://localhost:8080/items //get all items
5. GET http://localhost:8080/items/category/{category} //get all items by category
6. DELETE http://localhost:8080/users/{id} //delete by id

**ORDER** //What you add in order and want to buy
1. POST http://localhost:8080/orders/{userId}/{itemId} //add item in order some user bu ID
2. GET http://localhost:8080/orders/{id} //get by id
3. GET http://localhost:8080/orders //get all orders
4. GET http://localhost:8080/orders/name/{name} //get all orders from one User  by User name
5. DELETE http://localhost:8080/orders{id} //delete by id

**PURCHASE** //WHat user bought
1. POST http://localhost:8080/purchases/{userId}/{itemId} //add item in purchase some user bu ID
2. GET http://localhost:8080/purchases/{id} //get by id
3. GET http://localhost:8080/purchases //get all purchases
4. GET http://localhost:8080/purchases/name/{name} //get all purchases from one User by User name
5. DELETE http://localhost:8080/purchases/{id} //delete by id 

**PAY** //Implements a purchase mechanism. Item moves from order in purchase, and money withdrawn from User balsnce
1. GET http://localhost:8080/pay//{userId}/{itemId} // Buy one item
2. GET http://localhost:8080/pay//{userId} //Buy all the items which were in order by this Used id

