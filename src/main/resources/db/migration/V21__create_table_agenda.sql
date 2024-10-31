CREATE TABLE `agenda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_fim` date DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `id_projeto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcxgm3f2fpejedet6uak5nm3te` (`id_projeto`),
  CONSTRAINT `FKcxgm3f2fpejedet6uak5nm3te` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
);
