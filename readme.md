This app is to manage the Transaction of a person on daily basis Majorly Designing Backend APIS

This has used below features:
Java 8
Spring Boot 2.7.8
Postgres as Database
Tomcat (inbuilt in spring boot)
Maven


Please run the below 2 scripts by creating the database in your local Postgres Server
CREATE SEQUENCE public.trans_seq_record_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.mtransaction
(
    record_id bigint NOT NULL,
    transaction_type character varying(5) COLLATE pg_catalog."default",
    transaction_amount double precision,
    transaction_date date,
    transaction_reason character varying(5000) COLLATE pg_catalog."default",
    CONSTRAINT "TRANSACTION_pkey" PRIMARY KEY (record_id)
)

API'S Used:


