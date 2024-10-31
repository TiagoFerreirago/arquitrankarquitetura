CREATE TABLE `espaco_agenda` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `id_agenda` BIGINT NOT NULL,
  `id_projeto` BIGINT NOT NULL,
  FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`),
  FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
);