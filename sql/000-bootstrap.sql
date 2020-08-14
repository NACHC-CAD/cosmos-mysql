-- drop schema if exists cosmos;

-- drop user if exists greshje;

create schema cosmos;

create user greshje identified by 'changeme';

grant all privileges on cosmos.* to 'greshje'@'%'; 

grant all privileges on *.* to 'greshje'@'%'; 

flush privileges;

use cosmos;

