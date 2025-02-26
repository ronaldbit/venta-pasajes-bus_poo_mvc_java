-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-02-2025 a las 16:12:53
-- Versión del servidor: 10.11.10-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `poo_mvc_venta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bus`
--

CREATE TABLE `bus` (
  `BUSNRO` decimal(2,0) NOT NULL,
  `PLACA` char(10) DEFAULT NULL,
  `CAPACIDAD` decimal(2,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bus`
--

INSERT INTO `bus` (`BUSNRO`, `PLACA`, `CAPACIDAD`) VALUES
(1, 'WH2145', 40),
(2, 'MN1975', 60),
(3, 'PQ5478', 50),
(4, 'RP7812', 40),
(5, 'TP3547', 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chofer`
--

CREATE TABLE `chofer` (
  `IDCOD` char(4) NOT NULL,
  `CHONOM` varchar(30) DEFAULT NULL,
  `CHOFIN` date DEFAULT NULL,
  `CHOCAT` varchar(1) DEFAULT NULL,
  `CHOSBA` decimal(8,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `chofer`
--

INSERT INTO `chofer` (`IDCOD`, `CHONOM`, `CHOFIN`, `CHOCAT`, `CHOSBA`) VALUES
('C001', 'PATRICIO HERRERA', '1990-10-08', 'P', 350.0),
('C002', 'JORGE QUISPE', '2001-04-07', 'S', 200.0),
('C003', 'EDWARD TEMPLE', '2005-04-11', 'P', 450.0),
('C004', 'ELMER MORALES', '1998-04-10', 'S', 550.0),
('C005', 'MARCOS CUEVA', '1995-04-12', 'P', 650.0),
('C006', 'Luis Prieto', '1998-04-12', 'P', 350.0),
('C007', 'Eloy Lazo', '2004-04-12', 'P', 350.0),
('C008', 'Jaime Benavidez', '2005-04-12', 'S', 350.0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `BOLNRO` char(6) NOT NULL,
  `VIANRO` char(6) DEFAULT NULL,
  `NOMPASA` varchar(30) DEFAULT NULL,
  `NROASIE` decimal(2,0) DEFAULT NULL,
  `TIPO` char(1) DEFAULT NULL,
  `PAGO` decimal(8,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasajeros`
--

INSERT INTO `pasajeros` (`BOLNRO`, `VIANRO`, `NOMPASA`, `NROASIE`, `TIPO`, `PAGO`) VALUES
('000001', '100001', 'CLAUDIA VASQUEZ', 1, 'E', 40.0),
('000002', '100002', 'CARLOS PAREDES', 2, 'A', 50.0),
('000003', '100001', 'JUAN SANCHEZ', 3, 'A', 70.0),
('000004', '100001', 'ADELA MEZA', 4, 'N', 70.0),
('000005', '100002', 'GLORIA DELGADO', 12, 'N', 60.0),
('000006', '100001', 'VIRNA MEJIA', 6, 'E', 70.0),
('000007', '100001', 'JOSE LINARES', 7, 'A', 70.0),
('000008', '100002', 'JENIFER CRUZADO', 8, 'A', 50.0),
('000009', '100002', 'RAMON CERCADO', 9, 'A', 50.0),
('000010', '100001', 'TERESA EGUSQUIZA', 10, 'A', 20.0),
('000011', '100001', 'CAROLINA RETAMOZO', 11, 'N', 30.0),
('000012', '100002', 'SHANDY PAREDES', 12, 'A', 40.0),
('000013', '100001', 'NURITH GUILLEN', 13, 'A', 70.0),
('000014', '100002', 'DANIEL VERGARA', 14, 'N', 50.0),
('000015', '100001', 'JOHANA LOPEZ', 10, 'A', 70.0),
('000016', '100003', 'huachua Ernestina', 11, 'E', 70.0),
('000017', '100004', 'Cardenas Juana', 5, 'A', 70.0),
('000018', '100004', 'Leon Malpartida', 2, 'A', 50.0),
('000019', '100004', 'Gonzales carrillo', 4, 'N', 70.0),
('000020', '100005', 'Mio Gamboa', 7, 'A', 70.0),
('000021', '100005', 'Maldonado Huaman', 11, 'A', 70.0),
('000022', '100005', 'Gaspar Acosta', 14, 'E', 60.0),
('000023', '100006', 'Echegaray Felix', 10, 'E', 60.0),
('000024', '100006', 'Cano Siu', 8, 'N', 70.0),
('000025', '100006', 'Melgarejo percy', 9, 'N', 70.0),
('000026', '100006', 'Grijalva Alan ', 13, 'A', 70.0),
('000027', '100007', 'Marin LOPEZ', 5, 'A', 70.0),
('000028', '100007', 'JOHANA LOPEZ', 14, 'A', 70.0),
('000029', '100008', 'huachua Ernestina', 11, 'N', 70.0),
('000030', '100008', 'Cardenas Juana', 5, 'E', 70.0),
('000031', '100009', 'Leon Malpartida', 2, 'N', 70.0),
('000032', '100009', 'Gonzales carrillo', 4, 'E', 70.0),
('000033', '100010', 'Mio Gamboa', 7, 'E', 70.0),
('000034', '100011', 'Maldonado Huaman', 11, 'A', 70.0),
('000035', '100012', 'Gaspar Acosta', 14, 'N', 70.0),
('000036', '100013', 'Echegaray Felix', 10, 'A', 70.0),
('000037', '100014', 'Cano Siu', 8, 'E', 70.0),
('000038', '100015', 'Melgarejo percy', 9, 'N', 70.0),
('000039', '100016', 'Grijalva Alan ', 13, 'A', 70.0),
('000040', '100017', 'Marin LOPEZ', 5, 'N', 70.0),
('000041', '100018', 'JOHANA LOPEZ', 14, 'E', 70.0),
('000042', '100019', 'Cama Roxama', 2, 'N', 70.0),
('000043', '100019', 'Lopez Donayre, juan', 4, 'E', 70.0),
('000044', '100019', 'Lopez Vera, Ana', 7, 'E', 70.0),
('000045', '100020', 'Maldonado Huaman', 11, 'A', 70.0),
('000046', '100020', 'Diaz Gaspar Alicia', 14, 'N', 70.0),
('000047', '100020', 'Pezo Zuta Eliana', 10, 'A', 70.0),
('000048', '100021', 'Carrasco Cano Maribel', 8, 'E', 70.0),
('000049', '100021', 'Quispe Rojas, percy', 9, 'N', 70.0),
('000050', '100021', 'Grijalva Alan ', 13, 'A', 70.0),
('000051', '100022', 'Marin LOPEZ', 5, 'N', 70.0),
('000052', '100022', 'JOHANA LOPEZ', 14, 'E', 70.0),
('000053', '100001', 'donald', 2, 'N', 49.0),
('000054', '100001', 'Ronald', 8, 'A', 70.0),
('000055', '100001', 'Otro dato', 37, 'E', 35.0),
('000056', '100017', 'Pasajero', 15, 'A', 70.0),
('000057', '100001', 'pasajero', 40, 'E', 35.0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `RUTCOD` varchar(4) NOT NULL,
  `RUTNOM` varchar(20) DEFAULT NULL,
  `RUTCOS` decimal(8,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`RUTCOD`, `RUTNOM`, `RUTCOS`) VALUES
('LMAR', 'AREQUIPA', 35.0),
('LMAY', 'AYACUCHO', 170.0),
('LMCH', 'CHICLAYO', 80.0),
('LMCZ', 'CUZCO', 50.0),
('LMHA', 'HUANCAVELICA', 200.0),
('LMHC', 'HUANUCO', 120.0),
('LMHZ', 'HUARAZ', 70.0),
('LMIC', 'ICA', 50.0),
('LMTA', 'TACNA', 300.0),
('LMTR', 'TRUJILLO', 15.0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `CODE` varchar(10) NOT NULL,
  `USUARIO` varchar(30) DEFAULT NULL,
  `NOMBRE` varchar(29) DEFAULT NULL,
  `CONTRASEÑA` varchar(20) DEFAULT NULL,
  `CORREO` varchar(30) DEFAULT NULL,
  `TELEFONO` varchar(11) DEFAULT NULL,
  `ROL` varchar(10) NOT NULL,
  `PAIS` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`CODE`, `USUARIO`, `NOMBRE`, `CONTRASEÑA`, `CORREO`, `TELEFONO`, `ROL`, `PAIS`) VALUES
('user1', 'user', 'Carlos Diaz', '1010', 'TuGfa@gmail.com', '3578420', 'user', 'peru'),
('user2', 'abcde', 'ron', '123', 'jvera@gmail.com', '5578420', 'admin', 'peru'),
('user3', 'snt', 'Santiago', '1010', 'santiago@ejemplo.com', '141615101', 'admin', 'Peruano');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `VIANRO` char(6) NOT NULL,
  `BUSNRO` decimal(2,0) DEFAULT NULL,
  `RUTCOD` varchar(4) DEFAULT NULL,
  `IDCOD` char(4) DEFAULT NULL,
  `VIAHRS` time(6) DEFAULT NULL,
  `VIAFCH` date DEFAULT NULL,
  `COSVIA` decimal(8,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `viaje`
--

INSERT INTO `viaje` (`VIANRO`, `BUSNRO`, `RUTCOD`, `IDCOD`, `VIAHRS`, `VIAFCH`, `COSVIA`) VALUES
('100001', 1, 'LMTA', 'C001', '10:30:00.000000', '2023-04-12', 70.0),
('100002', 2, 'LMTA', 'C002', '09:30:00.000000', '2023-04-12', 60.0),
('100003', 3, 'LMCZ', 'C003', '11:30:00.000000', '2023-04-13', 80.0),
('100004', 2, 'LMCZ', 'C002', '08:00:00.000000', '2023-04-13', 90.0),
('100005', 1, 'LMIC', 'C007', '13:30:00.000000', '2023-04-13', 60.0),
('100006', 4, 'LMIC', 'C003', '15:00:00.000000', '2023-04-14', 50.0),
('100007', 5, 'LMHZ', 'C002', '21:30:00.000000', '2023-04-14', 50.0),
('100008', 1, 'LMHZ', 'C001', '12:30:00.000000', '2023-04-14', 60.0),
('100009', 3, 'LMCH', 'C004', '18:30:00.000000', '2023-04-15', 70.0),
('100010', 4, 'LMTR', 'C003', '19:00:00.000000', '2023-04-15', 70.0),
('100011', 2, 'LMCZ', 'C005', '19:40:00.000000', '2023-04-15', 80.0),
('100012', 3, 'LMIC', 'C003', '17:00:00.000000', '2023-04-16', 70.0),
('100013', 3, 'LMHA', 'C002', '18:40:00.000000', '2023-04-16', 90.0),
('100014', 4, 'LMAY', 'C003', '19:00:00.000000', '2023-04-17', 30.0),
('100015', 1, 'LMTA', 'C002', '19:00:00.000000', '2023-04-17', 40.0),
('100016', 1, 'LMCZ', 'C001', '17:00:00.000000', '2023-04-18', 50.0),
('100017', 4, 'LMAR', 'C002', '19:00:00.000000', '2023-04-18', 70.0),
('100018', 2, 'LMTR', 'C005', '15:00:00.000000', '2023-04-19', 60.0),
('100019', 3, 'LMTR', 'C004', '19:00:00.000000', '2023-04-19', 60.0),
('100020', 4, 'LMAY', 'C005', '19:00:00.000000', '2023-04-18', 70.0),
('100021', 3, 'LMTR', 'C007', '19:00:00.000000', '2023-04-19', 60.0),
('100022', 4, 'LMAY', 'C007', '19:00:00.000000', '2023-04-19', 70.0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`BUSNRO`);

--
-- Indices de la tabla `chofer`
--
ALTER TABLE `chofer`
  ADD PRIMARY KEY (`IDCOD`);

--
-- Indices de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD PRIMARY KEY (`BOLNRO`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`RUTCOD`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`CODE`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`VIANRO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
