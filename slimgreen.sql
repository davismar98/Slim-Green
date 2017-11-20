-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2017 a las 05:51:41
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `slimgreen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atributo`
--

CREATE TABLE `atributo` (
  `codigoAtributo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `rtaAsociada` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `atributo`
--

INSERT INTO `atributo` (`codigoAtributo`, `rtaAsociada`) VALUES
('acido', 'Acido'),
('almuerzo', 'Almuerzo'),
('ambas', 'Ambas'),
('amigos', 'Sus amigos'),
('bebidas', 'Bebidas'),
('calidadcomida', 'Calidad de Comida'),
('calidaddeservicio', 'Calidad del servicio'),
('calidadproducto', 'Calidad de Producto'),
('calidadservicio', 'Calidad de Servicio'),
('calidadservicios', 'Calidad de Servicio'),
('caminando', 'Caminando'),
('carta', 'Platos a la carta'),
('cena', 'Cena'),
('comidarapida', 'Comida Rápida (Subway, Mcdonalds, KFC, etc)'),
('comidasrapidas', 'Comidas rapidas'),
('corrientazo', 'Corrientazo'),
('costoso', 'Más de 50.000'),
('desayuno', 'Desayuno'),
('domicilio', 'Domicilio'),
('dulce', 'Dulce'),
('economico', 'Menos de 20.000 pesos'),
('familia', 'Su Familia'),
('fuertes', 'Platos fuertes'),
('listasllevar', 'Comida lista para llevar'),
('lugar', 'Lugar agradable y seguro'),
('medio', 'Entre 20.000 y 50.000 pesos'),
('menuvariado', 'Menú con una gran variedad de platos'),
('negocios', 'Reuniones de negocios'),
('paginaweb', 'Páginas WEB'),
('pareja', 'Su pareja'),
('postres', 'Postres'),
('precio', 'Precio'),
('radio', 'Radio'),
('rapidez', 'Rapidez en la entrega de platos'),
('recomendacion', 'Recomendacion de un amigo'),
('salado', 'Salado'),
('saludable', 'Saludable'),
('sensillos', 'Platos sencillos'),
('solo', 'Nadie, prefiero comer solo'),
('television', 'Televisión'),
('umami', 'Umami'),
('valoragregado', 'Valor agregado (espectáculos, música)'),
('vegetariana', 'Vegetariana');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formulario`
--

CREATE TABLE `formulario` (
  `idFormulario` int(100) NOT NULL,
  `formulario` varchar(100) CHARACTER SET utf8 NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `formulario`
--

INSERT INTO `formulario` (`idFormulario`, `formulario`, `url`) VALUES
(1, 'Gustos Usuario', 'https://goo.gl/forms/bsa9Bu3hcgf61hn03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opinion`
--

CREATE TABLE `opinion` (
  `idOpinion` int(100) NOT NULL,
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `idServicio` int(100) NOT NULL,
  `calificacion` int(100) NOT NULL,
  `comentario` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `opinion`
--

INSERT INTO `opinion` (`idOpinion`, `username`, `idServicio`, `calificacion`, `comentario`, `fecha`) VALUES
(1, 'davidmapu', 1, 85, 'Me encanta este lugar. Recomendadísimo.', '2017-04-11 13:40:21'),
(2, 'esteban98', 1, 35, 'Mala atención.', '2017-11-02 18:13:13'),
(3, 'davidmapu', 2, 77, 'Me gusta', '2017-11-30 06:10:01'),
(5, 'esteban98', 3, 90, 'Meh', '2017-11-18 21:42:17'),
(8, 'esteban98', 1, 40, 'test', '2017-11-18 22:09:58'),
(9, 'esteban98', 2, 100, 'La mejor comida de la vida.', '2017-11-18 22:54:06'),
(10, 'esteban98', 2, 10, 'Horrible', '2017-11-19 01:36:16'),
(11, 'davidmapu', 5, 40, ':v', '2017-11-19 02:38:38'),
(12, 'test', 6, 60, 'test comment :v', '2017-11-20 00:43:27'),
(13, 'maria123', 1, 60, 'prueba', '2017-11-20 03:01:40'),
(14, 'davidmapu', 2, 20, 'No me gusta', '2017-11-20 04:12:29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `idServicio` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `foto` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `horario` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `latitud` double NOT NULL,
  `logitud` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`idServicio`, `nombre`, `telefono`, `direccion`, `correo`, `foto`, `horario`, `latitud`, `logitud`) VALUES
(1, 'Restaurante Zoe', '645 4859', '#8B, Cl. 108 #4532', 'restaurantezoe@gmail.com', 'https://www.degusta.com.co/img/pix/restaurante.zoe_1_foto-r-0.jpg', 'Lunes a Viernes - 11:00 a 18:00; Sábados - 14:00 a 20:00', 4.689303, -74.039898),
(2, 'Freshii', '6162611', 'Local 3, Cra. 12 #93-08', 'freshii@gmail.com', 'https://res.cloudinary.com/civico/image/upload/c_fit,f_auto,fl_lossy,h_1200,q_auto:low,w_1200/v1449175780/entity/image/file/1f9/000/5660aae32f41f33c560001f9.jpg', 'Lunes a Viernes -  7:30 a 20:00; Sábados y Domingo- 8:00 a 18:00', 4.6752484, -74.04895379999999),
(3, 'Go Green', '2365442', 'Cl. 95 #11-3', 'gogreen@gmail.com', 'https://res.cloudinary.com/civico/image/upload/c_fit,f_auto,fl_lossy,h_1200,q_auto:low,w_1200/v1449002302/entity/image/file/0a9/000/565e05122f41f34ab00000a9.jpg', 'Lunes a Sábado - 10:00 a 18:00', 4.67912, -74.04626200000001),
(4, 'Oh Verde', '6011183', '55, Cl. 96 #12', 'ohverde@gmail.com', 'http://www.guiadegusta.com/webservice/getxRestaurante.pais3.w1200.h628.q80.oh-verde-centro_1_foto-r-0.jpg', 'Lunes a Sábado - 10:00 a 18:00', 4.680375, -74.047575),
(5, 'SUNA', '3177909', 'Cl. 72 Bis #5-09', 'suna@gmail.com', 'http://www.sunacolombia.com/wp-content/uploads/2017/02/restaurante_mercado_1.jpg', 'Lunes a Viernes -  7:00 a 22:00; Sábados y Domingo- 8:00 a 16:00', 4.6541682, -74.05377490000001),
(6, 'El Manjar', '2880832', '2do piso, Cl. 47 #8-07', 'elmanjar@gmail.com', 'https://u.tfstatic.com/restaurant_photos/182/67182/169/612/el-manjar-vista-interior-d0228.jpg', 'Lunes a Sábado - 7:00 a 16:00', 4.633634499999999, -74.0646552),
(7, 'Curcuma', '317 5907551', 'Cra. 37 #57-36', 'curcuma@gmail.com', 'http://viaresto.viapais.com.ar/img/curcuma1.jpg', 'Lunes a Sábado - 7:00 a 16:30', 4.6469736, -74.0818688),
(8, 'Bio Plaza', '257 4047', '90, Cl. 79b #7', 'bioplaza@gmail.com', 'http://pilarmode.com/site/media/BioPlaza.jpeg', 'Lunes a Sábado - 9:00 a 20:00; Domingo 9:00 a 17:00', 4.66128, -74.051692),
(9, 'Restaurante Pecorino', '347 3737', 'Cra. 7 #70-24', 'pecorino@gmail.com', 'https://res.cloudinary.com/civico/image/upload/c_fit,f_auto,fl_lossy,h_1200,q_auto:low,w_1200/v1421855448/entity/image/file/379/003/5241fbb031e93cda50003379.jpg', 'Lunes a Viernes- 9:00 a 17:00', 4.6527917, -74.0561935),
(10, 'Bawana', '610 7403', 'Ak. 11 #9814', 'bawana@gmail.com', 'https://img.grouponcdn.com/needish/4ZHykYd6fDJv7Z2V82mH4Hf6T7eb/4Z-700x420/v1/t900x600.jpg', '7:00 a 22:00; Sábados y Domingo- 8:00 a 16:00', 4.681363591502406, -74.04386579990387);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_atributo`
--

CREATE TABLE `servicio_atributo` (
  `idServicio` int(100) NOT NULL,
  `codigoAtributo` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `servicio_atributo`
--

INSERT INTO `servicio_atributo` (`idServicio`, `codigoAtributo`) VALUES
(1, 'almuerzo'),
(1, 'calidadcomida'),
(1, 'calidaddeservicio'),
(1, 'carta'),
(1, 'desayuno'),
(1, 'dulce'),
(1, 'postres'),
(1, 'precio'),
(1, 'saludable'),
(1, 'sensillos'),
(2, 'almuerzo'),
(2, 'calidadcomida'),
(2, 'carta'),
(2, 'cena'),
(2, 'corrientazo'),
(2, 'domicilio'),
(2, 'listasllevar'),
(2, 'rapidez'),
(2, 'saludable'),
(2, 'sensillos'),
(2, 'solo'),
(3, 'acido'),
(3, 'almuerzo'),
(3, 'calidadcomida'),
(3, 'calidadservicio'),
(3, 'carta'),
(3, 'desayuno'),
(3, 'domicilio'),
(3, 'economico'),
(3, 'rapidez'),
(3, 'sensillos'),
(3, 'solo'),
(3, 'vegetariana'),
(4, 'almuerzo'),
(4, 'medio'),
(4, 'precio'),
(4, 'solo'),
(4, 'vegetariana'),
(5, 'almuerzo'),
(5, 'amigos'),
(5, 'calidaddeservicio'),
(5, 'calidadservicio'),
(5, 'calidadservicios'),
(5, 'carta'),
(5, 'cena'),
(5, 'costoso'),
(5, 'desayuno'),
(5, 'familia'),
(5, 'fuertes'),
(5, 'lugar'),
(5, 'negocios'),
(5, 'pareja'),
(5, 'salado'),
(5, 'saludable'),
(6, 'almuerzo'),
(6, 'amigos'),
(6, 'calidaddeservicio'),
(6, 'calidadservicios'),
(6, 'carta'),
(6, 'desayuno'),
(6, 'familia'),
(6, 'medio'),
(6, 'pareja'),
(6, 'salado'),
(6, 'saludable'),
(7, 'almuerzo'),
(7, 'amigos'),
(7, 'calidadproducto'),
(7, 'calidadservicios'),
(7, 'desayuno'),
(7, 'dulce'),
(7, 'familia'),
(7, 'medio'),
(7, 'pareja'),
(7, 'salado'),
(7, 'saludable'),
(8, 'almuerzo'),
(8, 'amigos'),
(8, 'calidadproducto'),
(8, 'cena'),
(8, 'desayuno'),
(8, 'economico'),
(8, 'familia'),
(8, 'menuvariado'),
(8, 'negocios'),
(8, 'pareja'),
(8, 'precio'),
(8, 'vegetariana'),
(9, 'almuerzo'),
(9, 'ambas'),
(9, 'bebidas'),
(9, 'calidadservicio'),
(9, 'carta'),
(9, 'desayuno'),
(9, 'dulce'),
(9, 'familia'),
(9, 'fuertes'),
(9, 'medio'),
(9, 'menuvariado'),
(9, 'negocios'),
(9, 'pareja'),
(9, 'salado'),
(9, 'saludable'),
(10, 'almuerzo'),
(10, 'ambas'),
(10, 'bebidas'),
(10, 'calidadproducto'),
(10, 'calidadservicio'),
(10, 'cena'),
(10, 'costoso'),
(10, 'desayuno'),
(10, 'fuertes'),
(10, 'lugar'),
(10, 'menuvariado'),
(10, 'saludable'),
(10, 'umami'),
(10, 'valoragregado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8 NOT NULL,
  `apellido` varchar(255) CHARACTER SET utf8 NOT NULL,
  `correo` varchar(255) CHARACTER SET utf8 NOT NULL,
  `telefono` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`username`, `password`, `nombre`, `apellido`, `correo`, `telefono`) VALUES
('davidmapu', '12345', 'David', 'Martínez', 'davidmapu@unisabana.edu.co', '3015959187'),
('davidrohe', '12345', 'David', 'Rojas', 'davidrohe@unisabana.edu.co', '3014245812'),
('esteban98', 'asdfg', 'Esteban', 'Pulido', 'estaba.pulido@gmail.com', '3112234123'),
('maria123', '12345', 'María', 'Malagón', 'maria.malagon@yahoo.es', '3198461523'),
('test', 'test', 'Test', 'User', 'user@test.com', '3001010333');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_formulario`
--

CREATE TABLE `usuario_formulario` (
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `idFormulario` int(100) NOT NULL,
  `estado` int(10) NOT NULL DEFAULT '0',
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `respuesta` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_formulario`
--

INSERT INTO `usuario_formulario` (`username`, `idFormulario`, `estado`, `fecha`, `respuesta`) VALUES
('davidmapu', 1, 1, '2017-11-19 00:53:22', '\'Comida Rápida (Subway, Mcdonalds, KFC, etc)\', \'Entre 20.000 y 50.000 pesos\', \'Cena\', \'Calidad de Servicio\', \'Radio\', \'Salado\', \'Ambas\', \'Rapidez en la entrega de platos\', \'Sus amigos\', \'Compañeros del trabajo\', \'Comidas rápidas\''),
('davidrohe', 1, 0, '2017-11-19 00:53:22', ''),
('esteban98', 1, 1, '2017-11-19 01:35:46', '\'Platos a la carta\', \'Entre 20.000 y 50.000 pesos\', \'Cena\', \'Calidad de Comida\', \'Recomendacion de un amigo\', \'Amargo\', \'Ambas\', \'Calidad del servicio\', \'Su Familia\', \'Sus amigos\', \'Compañeros del trabajo\', \'Bebidas\''),
('maria123', 1, 1, '2017-11-20 03:00:22', '\'Corrientazo\', \'Entre 20.000 y 50.000 pesos\', \'Desayuno\', \'Calidad de Servicio\', \'Calidad de Comida\', \'Páginas WEB\', \'Salado\', \'Calidad de Servicio\', \'Calidad del servicio\', \'Sus amigos\', \'Platos sencillos\''),
('test', 1, 0, '2017-11-19 00:53:22', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `atributo`
--
ALTER TABLE `atributo`
  ADD PRIMARY KEY (`codigoAtributo`);

--
-- Indices de la tabla `formulario`
--
ALTER TABLE `formulario`
  ADD PRIMARY KEY (`idFormulario`);

--
-- Indices de la tabla `opinion`
--
ALTER TABLE `opinion`
  ADD PRIMARY KEY (`idOpinion`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`idServicio`);

--
-- Indices de la tabla `servicio_atributo`
--
ALTER TABLE `servicio_atributo`
  ADD PRIMARY KEY (`idServicio`,`codigoAtributo`),
  ADD KEY `fk_codigoAtributo` (`codigoAtributo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `usuario_formulario`
--
ALTER TABLE `usuario_formulario`
  ADD PRIMARY KEY (`username`,`idFormulario`),
  ADD KEY `fk_username` (`idFormulario`),
  ADD KEY `fk_username2` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `opinion`
--
ALTER TABLE `opinion`
  MODIFY `idOpinion` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `servicio_atributo`
--
ALTER TABLE `servicio_atributo`
  ADD CONSTRAINT `fk_codigoAtributo` FOREIGN KEY (`codigoAtributo`) REFERENCES `atributo` (`codigoAtributo`),
  ADD CONSTRAINT `fk_idServicio` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`);

--
-- Filtros para la tabla `usuario_formulario`
--
ALTER TABLE `usuario_formulario`
  ADD CONSTRAINT `fk_user2` FOREIGN KEY (`username`) REFERENCES `usuario` (`username`),
  ADD CONSTRAINT `fk_username` FOREIGN KEY (`idFormulario`) REFERENCES `formulario` (`idFormulario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
