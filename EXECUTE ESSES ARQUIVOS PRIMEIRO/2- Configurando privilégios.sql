CREATE USER IF NOT EXISTS 'Gerente'@'%' IDENTIFIED BY '54321';

GRANT USAGE ON *.* TO `Gerente`@`%`;
GRANT SELECT, INSERT, UPDATE, DELETE ON `locadora_riotavares`.* TO `Gerente`@`%`;

FLUSH PRIVILEGES;

CREATE USER IF NOT EXISTS 'Atendente'@'%' IDENTIFIED BY '12345';

GRANT USAGE ON *.* TO `Atendente`@`%`;
GRANT SELECT, INSERT, UPDATE, DELETE ON `locadora_riotavares`.* TO `Atendente`@`%`;

FLUSH PRIVILEGES;

CREATE USER IF NOT EXISTS 'Estoquista'@'%' IDENTIFIED BY '98765';

GRANT USAGE ON *.* TO `Estoquista`@`%`;
GRANT SELECT, INSERT, UPDATE, DELETE ON `locadora_riotavares`.* TO `Estoquista`@`%`;

FLUSH PRIVILEGES;

CREATE USER IF NOT EXISTS 'Supervisor'@'%' IDENTIFIED BY '56789';

GRANT USAGE ON *.* TO `Supervisor`@`%`;
GRANT SELECT ON `locadora_riotavares`.* TO `Supervisor`@`%`;

FLUSH PRIVILEGES;


CREATE USER IF NOT EXISTS 'Administrador'@'%' IDENTIFIED BY '12345';

GRANT USAGE ON *.* TO 'Administrador'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES ON `locadora_riotavares`.* TO 'Administrador'@'%';

-- Permissões para backup e restore
GRANT CREATE, DROP, ALTER, INDEX, LOCK TABLES, SHOW VIEW, TRIGGER, EVENT ON `locadora_riotavares`.* TO 'Administrador'@'%';

FLUSH PRIVILEGES;