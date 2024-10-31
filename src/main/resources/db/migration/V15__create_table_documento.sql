CREATE TABLE `documento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_envio` datetime(6) NOT NULL,
  `nome_documento` varchar(255) NOT NULL,
  `tipo_documento` varchar(255) NOT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_projeto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64d034hh2kyysafqx1uh6sj4d` (`id_cliente`),
  KEY `FKdvgbn8jkikr46v0kg36iov52` (`id_projeto`),
  CONSTRAINT `FK64d034hh2kyysafqx1uh6sj4d` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKdvgbn8jkikr46v0kg36iov52` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
);