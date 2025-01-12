alter table medicos add telefono varchar(20) not null;
--en otra tabla se pone esto
alter table medicos add activo tinyint;
update medicos set activo = 1