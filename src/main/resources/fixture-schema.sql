drop table if exists fixture CASCADE; 
create table fixture (id bigint generated by default as identity, 
Stadium varchar(255) not null, Conditions varchar(255) not null, TeamSize bigInt not null, 
primary key (id));