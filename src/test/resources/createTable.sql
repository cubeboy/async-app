create table member (
    member_id bigint not null auto_increment,
    member_name varchar(255),
    email_address varchar(255),
    password varchar(255),
    primary key (member_id)
);


insert into member (member_name, email_address, password) values
('user01', 'user01@asnyc.com', 'password01'),
('user02', 'user02@asnyc.com', 'password02'),
('user03', 'user03@asnyc.com', 'password03')
;
