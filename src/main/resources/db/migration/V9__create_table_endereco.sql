CREATE TABLE `endereco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rua` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `id_arquiteto` bigint DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK75hqgvrmv8wf5ikqg1ifgmb49` (`id_arquiteto`),
  KEY `FKe4y3uftwnlua4wuwisldhnjl4` (`id_cliente`),
  CONSTRAINT `FKe4y3uftwnlua4wuwisldhnjl4` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK75hqgvrmv8wf5ikqg1ifgmb49` FOREIGN KEY (`id_arquiteto`) REFERENCES `arquiteto` (`id`) 
);
