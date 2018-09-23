drop table if exists CUSTOMER;
drop table if exists ORDERS;

CREATE TABLE CUSTOMER (
    id BIGINT not null AUTO_INCREMENT,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    date_of_birth TIMESTAMP NULL,
    years INTEGER NOT NULL,
    primary key(id)
);

CREATE TABLE ORDERS (
    id BIGINT not null AUTO_INCREMENT,
    order_number BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) references CUSTOMER(id),
    primary key(id)
);


