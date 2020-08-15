--
-- example queries
--


--
-- simple selects
-- 

use cosmos;

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

select * from patient_att_type order by id;

select count(*) from patient_att_type order by id;



use contraception;

select * from patient;

select count(*) from patient;

select
    education_level,
    -- housing_status,
	-- health_insurance_type,
    count(*)
from
	patient
where 1 = 1
    -- and housing_status != 'Ignore'
group by 1 -- ,2,3
order by 1 -- ,2,3
;

select education_level from patient;

use cosmos;

select max(id) from cosmos.patient;

select * from contraception.patient order by id desc;

drop table foobar;

create table foobar (
	id int primary key,
    foo int,
    bar varchar(256)
);

select
	/*
    age.string_val age,
    sex.string_val sex,
    so.string_val sexual_orientation,
	count(*)
    */
    age.*
from
	cosmos.patient pat
    left outer join cosmos.patient_att age on age.att_type_id =  2 and age.patient_id = pat.id
    left outer join cosmos.patient_att sex on sex.att_type_id =  3 and sex.patient_id = pat.id
    left outer join cosmos.patient_att so  on so.att_type_id  =  4 and so.patient_id = pat.id
    /*
    left outer join cosmos.patient_att gi  on gi.att_type_id  =  5 and gi.patient_id = pat.id
    left outer join cosmos.patient_att rce on rce.att_type_id =  6 and rce.patient_id = pat.id
    left outer join cosmos.patient_att eth on eth.att_type_id =  7 and eth.patient_id = pat.id
    left outer join cosmos.patient_att lan on lan.att_type_id =  8 and lan.patient_id = pat.id
    left outer join cosmos.patient_att usp on usp.att_type_id =  9 and usp.patient_id = pat.id
    left outer join cosmos.patient_att fpl on fpl.att_type_id = 10 and fpl.patient_id = pat.id
    left outer join cosmos.patient_att hit on hit.att_type_id = 11 and hit.patient_id = pat.id
    left outer join cosmos.patient_att edu on edu.att_type_id = 12 and edu.patient_id = pat.id
    left outer join cosmos.patient_att hs  on hs.att_type_id  = 13 and hs.patient_id = pat.id
    left outer join cosmos.patient_att atc on atc.att_type_id = 12 and atc.patient_id = pat.id
    left outer join cosmos.patient_att trn on trn.att_type_id = 12 and trn.patient_id = pat.id
    */
-- group by 1,2,3
;

