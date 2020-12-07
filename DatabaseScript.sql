create table Admin
(
    Admin_Username varchar(15)                 not null,
    Admin_Password varchar(40) default 'admin' not null,
    constraint Admin_Username
        unique (Admin_Username)
);

create table Customer
(
    Customer_ID        int         not null
        primary key,
    Customer_Firstname varchar(15) not null,
    Customer_Lastname  varchar(15) not null,
    Customer_Address   varchar(45) not null
);

create table Account
(
    Account_Number  int auto_increment
        primary key,
    Customer_ID     int                            not null,
    Account_Balance double      default 0          not null,
    Account_PIN     int         default 1234       not null,
    Account_Type    varchar(15)                    not null,
    Account_Status  varchar(10) default 'unlocked' not null,
    constraint Account_ibfk_1
        foreign key (Customer_ID) references Customer (Customer_ID)
);

create index Customer_ID
    on Account (Customer_ID);


