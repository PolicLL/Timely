create table project (id BIGINT NOT NULL DEFAULT nextval('project_seq') PRIMARY KEY, duration varchar(255), end_time timestamp, 
name varchar(255), start_time timestamp);

create sequence if not exists project_seq;
