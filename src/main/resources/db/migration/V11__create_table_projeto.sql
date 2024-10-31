CREATE TABLE `projeto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `area_empreendimento` varchar(255) NOT NULL,
  `id_cliente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsp32ghne186lqdagm65c9fhtm` (`id_cliente`),
  CONSTRAINT `FK43v7oum2iiich2oxdslttp4uf` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
);
