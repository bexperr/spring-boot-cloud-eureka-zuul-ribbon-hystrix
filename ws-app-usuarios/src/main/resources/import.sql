INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('uli','$2a$10$4Tq12kFewzFKS9p/kt2Rgu95xzsv38NtPbb8GoGBSJdxG8I0Z.1.a',true, 'Ulises', 'Perez','hola@bexperr.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$Plauj572ljAbwrRVDUwvkO/4KW/IwNob5ciQrTOsvpRsTGiG.pCfu',true, 'Jareny', 'Nogueron','hi@bexperr.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);