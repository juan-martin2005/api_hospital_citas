insert into roles(nombre) values ('ROLE_PACIENTE');
insert into roles(nombre) values ('ROLE_DOCTOR');
insert into roles(nombre) values ('ROLE_ADMIN');

insert into usuarios(username,password,estado) values ('admin','$2a$12$/f6y.2oER7hDlBJvDYuiYeWn4TMBcVYG3UJWtTkZoifmeFhbMBbNG','ACTIVO');
insert into usuario_roles(usuario_id,role_id) values (1,3);

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Cardiología', 'Diagnóstico y tratamiento de enfermedades del corazón y sistema circulatorio.', 180.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Dermatología', 'Trata enfermedades de la piel, cabello y uñas.', 120.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Pediatría', 'Atención médica de bebés, niños y adolescentes.', 100.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Neurología', 'Especialidad en trastornos del sistema nervioso.', 200.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Gastroenterología', 'Trata enfermedades del sistema digestivo.', 150.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Oncología', 'Diagnóstico y tratamiento del cáncer.', 220.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Ortopedia y Traumatología', 'Trata enfermedades y lesiones del sistema musculoesquelético.', 160.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Oftalmología', 'Estudia y trata enfermedades de los ojos y la visión.', 130.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Urología', 'Trata enfermedades del sistema urinario y reproductor masculino.', 140.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Endocrinología', 'Se encarga de trastornos hormonales y metabólicos.', 150.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Neumología', 'Trata enfermedades de los pulmones y vías respiratorias.', 140.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Nefrología', 'Diagnóstico y tratamiento de las enfermedades renales.', 170.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Reumatología', 'Trata enfermedades autoinmunes y musculoesqueléticas.', 160.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Psiquiatría', 'Especialidad en trastornos mentales y emocionales.', 180.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Infectología', 'Trata enfermedades causadas por agentes infecciosos.', 130.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Alergología e Inmunología', 'Diagnóstico y tratamiento de alergias y enfermedades del sistema inmune.', 140.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Anestesiología', 'Administración de anestesia y manejo del dolor.', 190.00);
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Cirugía General', 'Realiza procedimientos quirúrgicos para tratar diversas afecciones.', 210.00);
