Drop table customer if exists;
CREATE TABLE customer (
    id      bigint             NOT NULL,
    birth_date  DATE           ,
    first_name  VARCHAR(14)     NOT NULL,
    last_name   VARCHAR(16)     ,
    gender      VARCHAR(14)   ,
    PRIMARY KEY (id) 
);