CREATE DATABASE IF NOT EXISTS test_hive;

USE test_hive;

DROP TABLE users;

CREATE TABLE IF NOT EXISTS users(
id INT,
name string,
old INT,
PRIMARY KEY(id) disable novalidate
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE location 'hdfs://namenode:9000/user/hive/warehouse/test_hive.db/users';

DROP TABLE products;

CREATE TABLE IF NOT EXISTS products(
id int,
id_user int,
`name` string,
price int,
PRIMARY KEY(id) disable novalidate,
CONSTRAINT fk_products_id_user FOREIGN KEY(id_user) REFERENCES users(id) disable novalidate
) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE location 'hdfs://namenode:9000/user/hive/warehouse/test_hive.db/products';

INSERT INTO users(id, name, old) VALUES(1, 'DrDeeman', 28);
INSERT INTO products(id, id_user, name, price) VALUES(1,1,'comics',500);


