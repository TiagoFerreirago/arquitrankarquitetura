CREATE TABLE `tipo_pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  `id_arquiteto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK685n1s10dcv98sbl2ouvm3wb2` (`id_arquiteto`),
  CONSTRAINT `FKphxuf0dkaetaq00ubdqg0bt3t` FOREIGN KEY (`id_arquiteto`) REFERENCES `arquiteto` (`id`)
);