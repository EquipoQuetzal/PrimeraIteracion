
set client_encoding = 'utf-8'; 

CREATE TABLE usuario(
   idUsuario      SERIAL PRIMARY KEY,
   nombre         TEXT NOT NULL,
   correo         TEXT NOT NULL,
   contrasena     INT NOT NULL,
   calificacion   INT,
   esAdmin        BOOLEAN,
   constraint correoUnico unique(correo)
);
comment on table usuario
is
'Relación usuario guarda los datos de un usuario registrado en el sitio';

CREATE TABLE publicacion(
   idPublicacion     SERIAL PRIMARY KEY,
   idUsuario         INT NOT NULL REFERENCES usuario(idUsuario),   -- Este id es del usuario que publico
   idPrestatario     INT REFERENCES usuario(idUsuario),            -- Este id es del usuario que tiene prestado el objeto
                                                                   -- puede ser nulo para indicar que el objeto no ha sido prestado.
   precio            REAL,
   estado            TEXT NOT NULL,
   descripcion	  	   TEXT NOT NULL,
   fechaPublicacion  DATE NOT NULL,
   calificacion      INT                                           -- La calificación de la publicación.
);
comment on table publicacion
is
'Relación publicacion guarda los datos de una  publicación hecha por un usuario';

CREATE TABLE comentario(
   idComentario   SERIAL PRIMARY KEY,
   idUsuario      INT NOT NULL REFERENCES usuario(idUsuario),
   idPublicacion  INT NOT NULL REFERENCES publicacion(idPublicacion),
   fecha          DATE NOT NULL,
   contenido      TEXT NOT NULL
  );
comment on table comentario
is
'Relación comentario guarda los datos de un comentario que se ha publicado en una publicación por un usuario';

CREATE TABLE galeria(
   idFoto	   	SERIAL PRIMARY KEY,
   idPublicacion  INT    NOT NULL REFERENCES publicacion(idPublicacion),
   foto           TEXT                                              --Una foto será la url de la fotografía.
);
comment on table usuario
is
'Relación galeria guarda las fotografías de cada publicación';