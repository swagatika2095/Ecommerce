DROP TABLE IF EXISTS Order_Details; 
DROP TABLE IF EXISTS Order_Table;
DROP TABLE IF EXISTS product;  
CREATE TABLE product (  
Product_Id INT  PRIMARY KEY auto_increment,  
product_Name Varchar(20) ,  
price INT , 
Product_type Varchar(20),
Product_Description Varchar(20),
quantity int,
Status Varchar(20)

);  

DROP TABLE IF EXISTS User;  
CREATE TABLE User (  
User_Id INT  PRIMARY KEY auto_increment,  
User_Name VARCHAR(20),  
Age INT ,
Gender varchar(3),
Address VARCHAR(100),
telephone VARCHAR(20),
Password Varchar(10000)
);


CREATE TABLE Order_Table(  
User_Id INT ,  
Product_Id Int,  
Order_Id INT  PRIMARY KEY auto_increment,
Order_Status varchar(10),
Payment_Type VARCHAR(100),
Payment_Ammount int,
CONSTRAINT FK_User  foreign key(User_Id) REFERENCES User (User_Id),
CONSTRAINT FK_Product  foreign key(Product_Id) REFERENCES product (Product_Id)
);

 
CREATE TABLE Order_Details (
Order_Details_Id int primary key auto_increment,
Order_Id INT  ,  
Product_Id int,  
product_price INT ,
CONSTRAINT FK_Order  foreign key(Order_Id) REFERENCES Order_Table (Order_Id),
CONSTRAINT FK_Productorder  foreign key(Product_Id) REFERENCES product (Product_Id)
);