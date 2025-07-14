CREATE DATABASE IF NOT EXISTS test_hive;
USE test_hive;
CREATE TABLE IF NOT EXISTS test(id String) STORED AS TEXTFILE location 'hdfs://namenode:9000/user/hive/warehouse/test_hive.db/test';
