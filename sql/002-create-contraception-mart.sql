drop schema if exists contraception;

create schema contraception;

use contraception;

select * from cosmos.patient;

--
-- pivot table for contraception data
-- 

create view patient as  
select
	pat.*,
    age.string_val age,
    sex.string_val sex,
    so.string_val sexual_orientation,
    gi.string_val gender_identity, 
    rce.string_val race,
    eth.string_val ethnicity,
    lan.string_val language,
    usp.string_val udss_spec_pop,
    fpl.string_val fpl,
    hit.string_val health_insurance_type,
    edu.string_val education_level,
    hs.string_val housing_status,
    atc.string_val access_to_care,
	trn.string_val transportation
from
	cosmos.patient pat
    left outer join cosmos.patient_att age on age.att_type_id =  2 and age.patient_id = pat.id
    left outer join cosmos.patient_att sex on sex.att_type_id =  3 and sex.patient_id = pat.id
    left outer join cosmos.patient_att so  on so.att_type_id  =  4 and so.patient_id = pat.id
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
;
