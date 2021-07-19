# AjavaLoginJspServletJdbc

CREATE TABLE `login` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `enabled` boolean DEFAULT false,
  PRIMARY KEY (`username`)
) 
