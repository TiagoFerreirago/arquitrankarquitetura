CREATE TABLE `historico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_mudanca` date DEFAULT NULL,
  `descricao_mudanca` varchar(255) DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKht2lrf55w8ig0b2cqlsyarlye` (`id_cliente`),
  CONSTRAINT `FKht2lrf55w8ig0b2cqlsyarlye` FOREIGN KEY (`id_cliente`) REFERENCES `projeto` (`id`)
);
