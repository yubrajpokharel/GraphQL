DROP TABLE IF exists cities;
CREATE TABLE cities
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cityname   VARCHAR(255),
    zipcode    INT,
    population INT,
    country    VARCHAR(20)
);