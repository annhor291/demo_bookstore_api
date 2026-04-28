create database bookstore;

use bookstore;

create table book(
   id bigint auto_increment primary key, -- bigint thay cho long
   title varchar(255),
   author varchar(255),
   price double,
   quantity int,
   category varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


create table customer(
   id bigint auto_increment primary key,
   name varchar(100),
   email varchar(255) unique,
   phone varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


create table orders(
   id bigint auto_increment primary key,
   orders_date datetime,
   total_amount double,
   customer_id bigint,
   
   constraint fk_customer
   foreign key(customer_id)
   references customer(id)
   on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


create table order_detail(
   id bigint auto_increment primary key,
   quantity int,
   price double,
   book_id bigint,
   order_id bigint,
   
   constraint fk_book
   foreign key (book_id)
   references book(id)
   on delete cascade,
   
   constraint fk_order
   foreign key(order_id)
   references orders(id)
   on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO book (title, author, price, quantity, category) VALUES
('Java Core', 'Nguyen Van A', 100, 10, 'Programming'),
('Spring Boot', 'Tran Van B', 200, 5, 'Programming'),
('Clean Code', 'Robert C. Martin', 300, 7, 'Software'),
('Design Patterns', 'Erich Gamma', 250, 4, 'Software'),
('Database System', 'Pham Van C', 150, 8, 'Database');


INSERT INTO customer (name, email, phone) VALUES
('An', 'an@gmail.com', '0123'),
('Binh', 'binh@gmail.com', '0456'),
('Cuong', 'cuong@gmail.com', '0789');

INSERT INTO orders (orders_date, total_amount, customer_id) VALUES
(NOW(), 300, 2),
(NOW(), 200, 3);

INSERT INTO orderDetail (quantity, price, book_id, order_id) VALUES
(2, 100, 1, 3),
(1, 100, 2, 3),
(1, 200, 2, 4);

select * from customer;
select * from orders;