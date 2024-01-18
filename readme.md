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
    transaction_type character varying(50) COLLATE pg_catalog."default",
    transaction_amount double precision,
    transaction_date date,
    transaction_reason character varying(5000) COLLATE pg_catalog."default",
    CONSTRAINT "TRANSACTION_pkey" PRIMARY KEY (record_id)
)

API'S Present:

1. /transaction/management/transaction/create  -for creating the new transaction with 2 different currencies INR and USD depending upon the RequestBody parameters

2. /transaction/management/transactions – To list all transaction and list transaction on specified date or date range in 2 different currencies.

1. /transaction/management/transaction/create--Request Body parameters and example --
{

    "transactionType": "Debit"/"Credit depending upon type of transaction,
   
    "transactionMoney": Double value---Amount of Money needs To be debitted or credited,
   
    "reasonOfTransaction": String -- Reason of Transaction,
   
    "currencyType":INR/USD currency in which you will be doing Transaction
   
 }

2./transaction/management/transactions-- Request Body parameters and example

{

    "requestListType":String should be "1"/"2" to determine will it be listing all transaction or some filters will be applied. Here 1 denotes defualt Listing and 2 Filter Listing This will be compulsory parameter to get listing else you will be getting empty list,
    
    "dateOfTransaction":Date in YYYY-MM-DD Format. This is optional and will be used to get list of all transaction on this particular Day Format must be followed,
    
	"startDateOfTransaction":Date in YYYY-MM-DD Format. This is optional and will be used to get list of all transaction on this from this Day To below end Date Format must be followed,
 
    "endDateOfTransaction":Date in YYYY-MM-DD Format. This is optional and will be used to get list of all transaction on this from above strat date To this end Date Format must be followed,
    
	"currencyType": INR/USD currency in which you will be viewing the transactions.
 
}

Here while Doing the transaction if money is sent in INR will be saved to DB in same value and if it is sent in USD will be converted to USD at that instance and  will be saved to DB while retriving will be converting this INR to USD if user wants to view in USD and will be representing the currency value at that instant of retriving and the value at the time of appeninding



