insert into roles(nombre) values ('ROLE_PACIENTE');
insert into roles(nombre) values ('ROLE_DOCTOR');
insert into roles(nombre) values ('ROLE_ADMIN');

insert into usuarios(username,password,estado) values ('admin','$2a$10$bR4qy1aE8DQN5pMI8sCBuu9mTkGqQrAPttlN6iNygDXb1R/jAiF2G','ACTIVO');
insert into usuario_roles(usuario_id,role_id) values (1,3);
