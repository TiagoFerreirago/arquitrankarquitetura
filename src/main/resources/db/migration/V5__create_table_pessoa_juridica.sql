CREATE TABLE `pessoa_juridica` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(20) NOT NULL,
  `inscricao_estadual` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);
