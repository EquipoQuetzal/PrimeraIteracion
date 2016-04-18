INSERT INTO usuario(idusuario,nombre,correo,contrasena,calificacion, esadmin) VALUES (1, 'usuario1', 'usuario@gmail.com', '12345', 0, FALSE);

INSERT INTO publicacion (idPublicacion,idUsuario,idPrestatario,precio,estado,descripcion,fechaPublicacion,calificacion)
VALUES (1, 1,NULL, 10.0,'Coahuila', 'Memoria Ram Corsair 4gb DDR4 2133MHZ', '2016/2/25',5);

INSERT INTO publicacion (idPublicacion,idUsuario,idPrestatario,precio,estado,descripcion,fechaPublicacion,calificacion)
VALUES (2, 1,NULL, 10.0,'Coahuila', 'Memoria Ram Kingnston 4gb DDR4 2X 1866MHZ', '2016/2/25',3);

INSERT INTO publicacion (idPublicacion,idUsuario,idPrestatario,precio,estado,descripcion,fechaPublicacion,calificacion)
VALUES (3, 1,NULL, 10.0,'Coahuila', 'Intel Core i5-6500 - Procesador (Intel Core i5-6xxx, 3.2 GHz, LGA1151, 64 GB, DDR3L-SDRAM, DDR4-SDRAM, 2133,1333,1600,1866 MHz)', '2016/2/25',5);
