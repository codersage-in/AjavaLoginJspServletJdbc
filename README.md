# AjavaLoginJspServletJdbc
Create following table in the temp database:-------

CREATE TABLE `login` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `enabled` boolean DEFAULT false,
  PRIMARY KEY (`username`)
) 

Open the java.security file from your java installation directory:---

Comment out jdk.tls.disabledAlgorithms property.
