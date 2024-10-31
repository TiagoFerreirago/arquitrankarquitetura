CREATE TABLE `pagamentos` (
  `id` bigint NOT NULL,
  `data_pagamento` datetime(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  `valor` double NOT NULL,
  `id_projeto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm8bexymo5uir9dvwxvlxn7lvt` (`id_projeto`),
  CONSTRAINT `FKm8bexymo5uir9dvwxvlxn7lvt` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
);
