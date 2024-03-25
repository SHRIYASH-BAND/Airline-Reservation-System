drop database if exists airplane;

create schema airplane;

use airplane;

create table customer(
    customer_id int PRIMARY KEY auto_increment,
	name varchar(50),
	email char(50) NOT NULL UNIQUE,
	password varchar(50),
	age int,
	address varchar(50),
	gender char(2),
	mobile int
);
INSERT INTO customer values (default,"sarthak","s@gmail","s12",20,"pune","M",834845);

create table flight(
    flight_id int PRIMARY KEY auto_increment,
    flight_number char(50) NOT NULL UNIQUE,
    seats int NOT NULL,
    fare double NOT NULL,
	name varchar(50) NOT NULL,
	company varchar(50) NOT NULL
);

INSERT INTO flight values (default,"75124VBZ98",500,2000,"Boeing 747", "Boeing");
INSERT INTO flight values (default,"XSW564VBZ98",10,7000,"SpiceJet 205P", "SpiceJet");
INSERT INTO flight values (default,"475NYTCVE74",300,3000,"Lufthansa 7417", "Lufthansa");

create table airport_address(
    address_id int PRIMARY KEY auto_increment,
	state varchar(50),
	city varchar(50) NOT NULL UNIQUE,
	pincode int NOT NULL,
	locality varchar(50) NOT NULL UNIQUE
);

INSERT INTO airport_address values (default,"Maharashtra","Pune",411005,"Viman Nagar");
INSERT INTO airport_address values (default,"Maharashtra","Mumbai",411348,"Navi Mumbai");
INSERT INTO airport_address values (default,"Karnataka","Bangalore",819348,"Kempegowda");

create table airport(
    airport_id int PRIMARY KEY auto_increment,
    name varchar(50)  NOT NULL UNIQUE,
	email char(50) NOT NULL UNIQUE,
	password varchar(50) NOT NULL,
    address_id int,
    FOREIGN KEY (address_id) REFERENCES airport_address(address_id)
);

INSERT INTO airport values (default,"Pune Airport","pune@gmail","p123",1);
INSERT INTO airport values (default,"Chhatrapati Shivaji Maharaj International Airport","air@gmail","a123",2);
INSERT INTO airport values (default,"Kempegowda International Airport","kemp@gmail" ,"k123",3);

create table flight_schedule(
    flight_schedule_id int PRIMARY KEY auto_increment,
    departure_date_time datetime NOT NULL,
    arrival_date_time datetime NOT NULL,
	source_airport int,
    destination_airport int,
    flight_id int,
    available_seats int,
    status char(2) NOT NULL,

    FOREIGN KEY (source_airport) REFERENCES airport(airport_id),
    FOREIGN KEY (destination_airport) REFERENCES airport(airport_id),
    FOREIGN KEY (flight_id) REFERENCES flight(flight_id)

);

INSERT INTO flight_schedule values (default,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),1,3,2,10,"S");

create table reservations(
    reservation_id int PRIMARY KEY auto_increment,
    customer_id int ,
    flight_schedule_id int ,
	seat_nos int NOT NULL ,
	fare double NOT NULL,
	payment_status char(2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (flight_schedule_id) REFERENCES flight_schedule(flight_schedule_id)

);




