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

insert into project values (1, 'Testing: Contraception', 'A project for testing purposes.  Data from this project should not be considered as "Production" data.');

drop table if exists data_set;

create table data_set (
	id int primary key auto_increment,
    guid varchar(64) unique,
    project_id int references project,
    name varchar(64),
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
    guid varchar(64) unique,
    data_set_id int references data_set,
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
    guid varchar(64) unique,
    project_id int references project,
	code varchar(256),
    name varchar(256),
    description varchar(1028)
);

drop table if exists data_type;

create table data_type (
	code varchar(32) primary key,
    name varchar(64) unique,
    description varchar(256)
);

insert into data_type values ('DATE_TIME', 'Date and time', null);
insert into data_type values ('STRING', 'Text value', null);
insert into data_type values ('NUM', 'Number', null);
insert into data_type values ('REF_INT', 'Reference to numeric key', 'Reference to a record for the given entity and key where the key is a number.');
insert into data_type values ('REF_TXT', 'Reference to string key', 'Reference to a record for the given entity and key where the key is a string value.');

drop table if exists patient_att;

create table patient_att (
	id int primary key auto_increment,
    data_set_id int references data_set,
    patient_id int references patient,
    att_type_id int references patient_att_type,
    data_type varchar(32) references data_type,
    num_val double,
    code_val varchar(64),
    string_val varchar(1028),
    date_val datetime,
    ref_entity varchar(256),
    ref_key int,
    ref_code varchar(256)
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

-- truncate patient table
truncate table patient;

--
-- simple selects
-- 

select * from patient;

select * from project;

select * from data_set;

select * from patient;

select * from patient_att_type;

select * from patient_att;

select count(*) from patient_att;

--
-- counts for data sets
--

select
	data_set_id,
    patient_id,
    count(*)
from
	patient_att
group by 1, 2
order by 1, 2
;

select
	data_set_id,
    count(*)
from
	patient
group by 1
order by 1
;

--
-- pivot table for contraception data
-- 

select * from patient_att_type order by id;

select count(*) from patient_att_type order by id;

select
	pat.*,
    att.string_val
from
	patient pat
    join patient_att att where att.patient_id = pat.id and att.att_type_id = 3
;



