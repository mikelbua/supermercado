-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.1.72-community - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para supermercado
CREATE DATABASE IF NOT EXISTS `supermercado` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `supermercado`;

-- Volcando estructura para tabla supermercado.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `foto` varchar(500) DEFAULT NULL,
  `descuento` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuario` (`id_usuario`),
  CONSTRAINT `FK_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.producto: ~13 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id`, `nombre`, `precio`, `foto`, `descuento`, `id_usuario`) VALUES
	(3, 'tortilla de patatas', 13, 'http://www.palacios.es/palacios/usuariosftp/conexion/imagenes4146a.png', 50, 1),
	(4, 'galletas', 11, 'https://static.ulabox.com/media/59655_xl.jpg', 3, 2),
	(5, 'donetes', 3.99, 'https://static.ulabox.com/media/126077_l1.jpg', 12, 3),
	(7, 'doritos', 5.99, 'https://doritos.es/images/default-source/productos/doritos-tex-mex-600x568_3d_new.png', 56, 4),
	(8, 'cereales', 9.99, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201710/25/00120633900236____1__600x600.jpg', 10, 4),
	(9, 'mermelada', 8.99, 'https://www.latiendahero.es/media/catalog/product/cache/3/thumbnail/600x/17f82f742ffe127f42dca9de82fb58b1/f/d/xfdp_tol_ngx_fresas_2018.jpg.pagespeed.ic.IBljQHoPHQ.jpg', 50, 4),
	(10, 'mermelada albaricoque', 10, 'https://www.latiendahero.es/media/catalog/product/cache/3/thumbnail/600x/17f82f742ffe127f42dca9de82fb58b1/f/d/fdp_ngx_albaricoques_2018.jpg', 50, 3),
	(11, 'sal rosa', 15.2, 'https://images-na.ssl-images-amazon.com/images/I/818VX25gNWL._SX569_.jpg', 4, 3),
	(12, 'sal marina', 10.99, 'http://www.biocop.es/1270/sal_marina_atlantica_gruesa_biocop_1_kg.jpg', 3, 2),
	(13, 'chocolocos', 15.99, 'https://www.recheio.pt/catalogo/media/catalog/product/cache/1/image/900x900/9df78eab33525d08d6e5fb8d27136e95/6/6/664626_6.png', 8, 2),
	(14, '3d', 2.9, 'https://www.capsularium.com/4772-large_default/bugles-3d-matutano-35-gr.jpg', 2, 1),
	(15, 'chococrispies', 6.99, 'https://static.ulabox.com/media/119290_l1.jpg', 3, 1),
	(16, 'salmon ahumado', 13.99, 'https://casasantona.com/wp-content/uploads/2019/07/salmon_ahumado_100.png', 50, 1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla supermercado.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `contrasenia` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.usuario: ~4 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `contrasenia`) VALUES
	(1, 'admin', '123456'),
	(2, 'pepe', '654321'),
	(3, 'pepa', '123654'),
	(4, 'andoni', '2222222');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;