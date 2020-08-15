drop schema if exists examples;

create schema examples;

use examples;

create table attribute (
	id int primary key auto_increment,
    subject_id int,
    att_code varchar(64),
    string_val varchar(256)
);

create index attribute_idx_01 on  attribute (
	subject_id,
    att_code,
    string_val
);

create index attribute_idx_02 on attribute (
	att_code,
    string_val,
    subject_id
);

create index attribute_idx_03 on attribute (
	string_val,
    att_code,
    subject_id
);


drop index attribute_idx_01 on attribute;
drop index attribute_idx_02 on attribute;
drop index attribute_idx_03 on attribute;


--
-- queries 
-- 

select count(*) from attribute;

select * from attribute;

select att_code, count(*) from attribute group by 1;

select
    city.string_val city,
    gen.string_val gender,
    pet.string_val pet,
    count(distinct att.subject_id)
from
	attribute att
    join attribute city on att.subject_id = city.subject_id and city.att_code = 'CITY'
    join attribute gen on att.subject_id = gen.subject_id and gen.att_code = 'GENDER'
    join attribute pet on att.subject_id = pet.subject_id and pet.att_code = 'PET'
where 1=1
group by 1,2,3
order by city,gender,pet
;

    
    


