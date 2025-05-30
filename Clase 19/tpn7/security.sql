-- creamos el usuario que sera ADMIN
INSERT INTO security.users (username, password, enabled, account_not_expired, account_not_locked, credential_not_expired)
VALUES ('centro8', '$2a$10$Q.tpOB8DJQ9BcS3aKP1SbeuJzY4MqUkomjwhPaspNXbb8lU1nmm82', 1, 1, 1, 1);


-- Si el rol ADMIN no existe:
INSERT INTO roles (role) VALUES ('ADMIN');

-- Asocia el usuario al rol (ajusta los nombres de las columnas/tablas según tu modelo)
INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'centro8'),
    (SELECT id FROM roles WHERE role = 'ADMIN')
);




-- Asociaciones de permisos READ y CREATE al rol ADMIN
SELECT id FROM roles WHERE role = 'ADMIN';
SELECT id FROM permissions WHERE permission_name = 'READ';
SELECT id FROM permissions WHERE permission_name = 'CREATE';

-- Insertamos las asociaciones en la tabla de enlace
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 1); -- ADMIN - READ
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 2); -- ADMIN - CREATE

-- Verifica la asociación
SELECT * FROM roles_permissions WHERE role_id = 1;