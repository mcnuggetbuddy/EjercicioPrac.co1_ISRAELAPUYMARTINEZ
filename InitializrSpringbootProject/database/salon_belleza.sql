-- =====================================
-- 1) CREAR USUARIO
-- =====================================
DROP USER IF EXISTS 'usuario_caso_practico_01'@'localhost';
CREATE USER 'usuario_caso_practico_01'@'localhost'
IDENTIFIED BY '123456';
-- =====================================
-- 2) CREAR BASE DE DATOS
-- =====================================
DROP DATABASE IF EXISTS salon_belleza;
CREATE DATABASE salon_belleza;
GRANT ALL PRIVILEGES ON salon_belleza.*
TO 'usuario_caso_practico_01'@'localhost';
FLUSH PRIVILEGES;
USE salon_belleza;
-- =====================================
-- 3) TABLA CATEGORIA
-- =====================================
CREATE TABLE categoria (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL
);
-- =====================================
-- 4) TABLA SERVICIO
-- =====================================
CREATE TABLE servicio (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
precio DECIMAL(8,2) NOT NULL,
categoria_id INT NOT NULL,
FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
-- =====================================
-- 5) TABLA RESERVA
-- =====================================
CREATE TABLE reserva (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre_cliente VARCHAR(100) NOT NULL,
fecha DATE NOT NULL,
servicio_id INT NOT NULL,
FOREIGN KEY (servicio_id) REFERENCES servicio(id));
-- =====================================
-- 6) DATOS DE PRUEBA
-- =====================================
-- Categorías
INSERT INTO categoria (nombre) VALUES
('Cabello'),
('Uñas'),
('Barbería'),
('Spa');
-- Servicios
INSERT INTO servicio (nombre, precio, categoria_id) VALUES
('Corte de Cabello Mujer', 8500.00, 1),
('Balayage', 45000.00, 1),
('Manicure Tradicional', 6000.00, 2),
('Pedicure Spa', 12000.00, 2),
('Corte Caballero', 7000.00, 3),
('Masaje Relajante', 20000.00, 4);
-- Reservas
INSERT INTO reserva (nombre_cliente, fecha, servicio_id) VALUES
('Ana Rodríguez', '2026-03-10', 1),
('María López', '2026-03-12', 3),
('Carlos Sánchez', '2026-03-15', 5);