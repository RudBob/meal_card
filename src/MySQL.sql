CREATE DATABASE meal_card;
USE meal_card;
CREATE TABLE card(
  id VARCHAR(20),
  name VARCHAR(35),
  class_name VARCHAR(30),
  money FLOAT DEFAULT 100,
  password VARCHAR(30) DEFAULT '000000',
  num_of_bank_card FLOAT DEFAULT 1000
);
