drop table if exists Package cascade;
drop table if exists History cascade;
drop table if exists PostOffice cascade;
drop table if exists Client cascade;
drop table if exists ValuablePack cascade;
drop table if exists Envelope cascade;
drop table if exists Letter cascade;
drop table if exists Mark cascade;
drop table if exists Money cascade;



create table Package (
id bigint	 primary key,
sender bigint	,
recipient bigint	,
addressFrom text,
postOfficeFrom bigint	,
addressTo text,
postOfficeTo bigint	,
price int,
date text,
received boolean default false,

size float,
weight float,
fragile boolean
);

create table ValuablePack (
id bigint	 primary key,
sender bigint	,
recipient bigint	,
addressFrom text,
postOfficeFrom bigint	,
addressTo text,
postOfficeTo bigint	,
price int,
date text,
received boolean default false,

size float,
weight float,
fragile boolean,
declaredValue bigint
);

create table Letter (
id bigint	 primary key,
sender bigint	,
recipient bigint	,
addressFrom text,
postOfficeFrom bigint	,
addressTo text,
postOfficeTo bigint	,
price int,
date text,
received boolean default false,
weight int,
mark bigint	,
markCount int,
envelope bigint	
);

create table Money (
id bigint	 primary key,
sender bigint	,
recipient bigint	,
addressFrom text,
postOfficeFrom bigint	,
addressTo text,
postOfficeTo bigint	,
price int,
date text,
received boolean default false,
sum int,
currency text
);


create table Client (
  id bigint	 primary key,
  name text
  );

create table Envelope(
  id int primary key,
  size text,
  price int
);

create table History(
  id bigint	 primary key,
  idPostObject bigint	,
  date text,
  postOffice bigint
);

create table Mark(
  id bigint	 primary key,
  name text,
  price int
);

create table PostOffice(
  officeId bigint	 primary key,
  country text,
  city text,
  address text
);
