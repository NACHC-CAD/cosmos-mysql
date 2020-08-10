use cosmos;

-- 
-- patient stuff
-- 

drop table if exists project;

create table project (
	id int primary key auto_increment,
    name varchar(64) unique,
    description varchar(1028)
);

drop table if exists orgainization;

create table orgainization ( 
	id int primary key auto_increment,
    name varchar(64) unique,
    description varchar(1028)
);

drop table if exists team;

create table team (
	id int primary key auto_increment,
	name varchar(64) unique,
    description varchar(1028)
);

drop table if exists data_import;

create table data_import (
	id int primary key auto_increment,
    name varchar(64) unique
);

drop table if exists patient_id_type;

create table patient_id_type (
	code varchar(64) primary key,
    name varchar(64) unique,
    description varchar(1028)
);

insert into patient_id_type values('did', 'Deidentified Key', 'A synthetic key for deidentified data than can be mapped back to patient.');  

drop table if exists address;

create table address (
	id int auto_increment primary key,
    street1 varchar(256),
    street2 varchar(256),
    city varchar(256),
    county varchar(256),
    state varchar(2),
    zip varchar(64),
    lat varchar(64),
    lon varchar(64)
);

drop table if exists patient_id_type;

create table patient_id_type (
	code varchar(64) primary key,
    name varchar(64) unique,
    description varchar(256) 
);

drop table if exists patient;

create table patient (
	id int auto_increment primary key,
    guid varchar(32) unique,
    patient_id int,
    patient_id_type varchar(64) references patient_id_type
);

drop table if exists patient_id;

create table patient_id (
	id int primary key auto_increment,
    data_import_id int references data_import,
	patient_id int references patient,
    patient_id_type varchar(64) references patient_id_type
);

drop table if exists patient_att_type;

create table patient_att_type (
	id int primary key auto_increment,
    data_import_id int references data_import,
	code varchar(256),
    name varchar(256),
    description varchar(1028)
);

drop table if exists patient_att;

create table patient_att (
	id int primary key auto_increment,
    att_type_id int references patient_att_type,
    int_val int,
    code_val varchar(64),
    text_val varchar(1028),
    date_val datetime,
    patient_att_type varchar(256) references patient_att_type
);

drop table if exists patient_att_det_type;

create table patient_att_det_type (
	id int primary key auto_increment,
    name varchar(64),
    description varchar(1028)
);

drop table if exists patient_att_detail;

create table patient_att_detail (
	id int primary key auto_increment,
    patient_att_id int references patient_att
);

