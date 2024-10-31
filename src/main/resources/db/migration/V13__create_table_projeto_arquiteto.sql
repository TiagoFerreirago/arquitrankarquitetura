CREATE TABLE `projeto_arquiteto` (
  `id_projeto` bigint NOT NULL,
  `id_arquiteto` bigint NOT NULL,
  KEY `FK6ga6lkqej9eu132rsauw391eg` (`id_arquiteto`),
  KEY `FKhlu7yd4g8rfe75kd4uy9qjwwm` (`id_projeto`),
  CONSTRAINT `FK6ga6lkqej9eu132rsauw391eg` FOREIGN KEY (`id_arquiteto`) REFERENCES `arquiteto` (`id`),
  CONSTRAINT `FKhlu7yd4g8rfe75kd4uy9qjwwm` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
);
