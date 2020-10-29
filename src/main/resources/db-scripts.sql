create table if not exists  users (
  id int not null,
  name varchar(100) not null,
  email varchar(100),
  primary key(id)
)

insert into users (id , name ) values (1,"nss");
insert into users (id , name ) values (2,"Warions");