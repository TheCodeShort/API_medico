alter table medicos add telefono varchar(20) not null;
--en otra tabla se pone esto
alter table medicos add activo tinyint;--se utiliza para modificar la estructura de la tabla médicos en la base de datos. Específicamente, esta consulta agrega una nueva columna llamada activo a la tabla.
update medicos set activo = 1--se utiliza para actualizar todos los registros en la tabla