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
    housing_status,
	health_insurance_type,
    education_level,
    count(*)
from
	patient
group by 1,2,3
order by 1,2,3

