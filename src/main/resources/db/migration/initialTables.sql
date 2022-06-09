drop table if exists role;
create table role(
    id int primary key auto_increment,
    name varchar(20) not null
) engine=innodb default charset=utf8;

drop table if exists type;
create table type(
    id int primary key auto_increment,
    type varchar(20) not null
) engine=innodb default charset=utf8;

drop table if exists hotel;
create table hotel(
    id int primary key auto_increment,
    name varchar(20) not null
) engine=innodb default charset=utf8;

drop table if exists floor;
create table floor(
    id int primary key auto_increment,
    number int not null,
    hotel_id int not null,
    foreign key(hotel_id) references hotel(id)
) engine=innodb default charset=utf8;

drop table if exists room;
create table room(
    id int primary key auto_increment,
    number int not null,
    floor_id int not null,
    type_id int not null,
    foreign key(floor_id) references floor(id),
    foreign key(type_id) references type(id)
) engine=innodb default charset=utf8;

drop table if exists user;
create table users (
    id int primary key auto_increment,
    name varchar(256) not null,
    password varchar(256) not null,
    email varchar(256) not null,
    role_id int not null foreign key references role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists booking_entity;
create table booking_entity(
    id int primary key auto_increment,
    user_id int not null,
    room_id int not null,
    foreign key(user_id) references user(id),
    foreign key(room_id) references room(id)
) engine=innodb default charset=utf8;