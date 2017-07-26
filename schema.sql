DROP SCHEMA IF EXISTS rentaldb;

DROP TABLE IF EXISTS rentaldb.carspec;
DROP TABLE IF EXISTS rentaldb.car;
DROP TABLE IF EXISTS rentaldb.rental;
DROP TABLE IF EXISTS rentaldb.customer;

CREATE SCHEMA rentaldb;

/*Begin table creation*/

CREATE TABLE IF NOT EXISTS rentaldb.car
(car_id varchar(200) not null,
  constraint pk_car_pkey PRIMARY KEY (car_id)
);

CREATE TABLE IF NOT EXISTS rentaldb.carspec
(car_id varchar(200) not null,
 make varchar(200) not null,
 model varchar(200) not null,
 year varchar(200) not null,
 size varchar(200),
  constraint fk_car_id_fkey FOREIGN KEY(car_id) REFERENCES rentaldb.car(car_id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS rentaldb.customer
(customer_name varchar(200) not null,
 customer_phone varchar(200) not null,
 customer_address varchar(200) not null,
  constraint pk_customer_pkey PRIMARY KEY (customer_name)
);


CREATE TABLE IF NOT EXISTS rentaldb.rental
(rent_date varchar(200) not null,
 return_date varchar(200) not null DEFAULT '0',
 rental_status varchar(200),
 customer_name varchar(200),
 car_id varchar(200) not null,
  constraint fk_rental_customer_name_fkey FOREIGN KEY(customer_name)
  REFERENCES rentaldb.customer(customer_name) ON DELETE RESTRICT,
  constraint fk_rental_car_fkey FOREIGN KEY(car_id) REFERENCES rentaldb.car(car_id) ON DELETE RESTRICT
);


/*End table creation*/

/*Begin data population*/

/* Car data population*/
insert into rentaldb.car(car_id)
values('5321'),
  ('4874'),
  ('1234'),
  ('4444');

/*CarSpec and Car data*/
insert into rentaldb.carspec(car_id, make, model, year, size)
values((select car_id from rentaldb.car where car_id = '5321'),'Nissan','Altima','2012','SMALL'),
  ((select car_id from rentaldb.car where car_id = '4874'),'Nissan','Altima','2012','MIDSIZE'),
  ((select car_id from rentaldb.car where car_id = '1234'),'VolksWagen','Passat','2002','LARGE'),
  ((select car_id from rentaldb.car where car_id = '4444'),'Mercedes','G-Class','2000', NULL);

/*Customer data population*/
insert into rentaldb.customer(customer_name, customer_phone, customer_address)
values('Samir James','816-878-1111','6012 NE Antioch Rd.'),
  ('Kim Sam','816-847-8888','7123 Main Street'),
  ('Mehmet Scholl','816-444-2387','12 Rockhill Rd.'),
  ('John Smith', '816-234-5678', '8679 Grand Blvd.');

/*Rental data population*/
insert into rentaldb.rental(rent_date, return_date, rental_status, customer_name, car_id)
values('1/12/12', '1/15/12', 'RETURNED',
       (select customer_name from rentaldb.customer where customer_name = 'John Smith'),
       (select car_id from rentaldb.car where car_id = '5321')),
  ('1/12/12', '1/16/12', 'RETURNED',
   (select customer_name from rentaldb.customer where customer_name = 'John Smith'),
   (select car_id from rentaldb.car where car_id ='4874')),
  ('1/1/10', '4/4/10', 'RETURNED',
   (select customer_name from rentaldb.customer where customer_name = 'John Smith'),
   (select car_id from rentaldb.car where car_id ='4444')),
  ('5/6/12', '5/8/12', 'RETURNED',
   (select customer_name from rentaldb.customer where customer_name = 'John Smith'),
   (select car_id from rentaldb.car where car_id ='1234')),
  ('3/22/17', '4/16/17', 'RETURNED',
   (select customer_name from rentaldb.customer where customer_name = 'Samir James'),
   (select car_id from rentaldb.car where car_id ='4444')),
  ('5/18/17', '8/16/17', 'RENTED',
   (select customer_name from rentaldb.customer where customer_name = 'Mehmet Scholl'),
   (select car_id from rentaldb.car where car_id ='4874'));

