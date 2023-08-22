CREATE TABLE loan (
    loan_req_no integer Not Null Auto_increment,
     loan_amt double Not Null,
     customer_id integer Not Null,
     Foreign Key(customer_id)  
);
