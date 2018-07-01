Drop table customer if exists;
CREATE TABLE customer (
    CUSTOMER_NO      bigint             NOT NULL,
    BIRTH_DATE  DATE           ,
    FIRST_NAME    VARCHAR(14)     NOT NULL,
    LAST_NAME   VARCHAR(16)     ,
    GENDER      VARCHAR(14)   ,
    PRIMARY KEY (CUSTOMER_NO) 
);