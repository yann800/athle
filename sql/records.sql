CREATE TABLE IF NOT EXISTS continent (
  id INT(11) NOT NULL,
  nom VARCHAR(65) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS pays (
  id INT(11) NOT NULL,
  nom VARCHAR(65) COLLATE latin1_general_ci NOT NULL,
  idContinent int(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS record (
  id int(11) NOT NULL AUTO_INCREMENT,
  epvreuve VARCHAR(65) DEFAULT '0',
  perf varchar(12) NOT NULL,
  nom varchar(30) NOT NULL,
  sexe int(1) NOT NULL DEFAULT '0',
  naissance int(11) NOT NULL,
  idPays int(11) NOT NULL,
  annee int(11) DEFAULT NULL,
  points int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


ALTER TABLE pays
  ADD CONSTRAINT pays_ibfk_1 FOREIGN KEY (idContinent) REFERENCES continent (id);
  
  
 
insert into continent (id, nom) values (1, 'Afrique');
insert into continent (id, nom) values (2, 'Amérique du Nord');
insert into continent (id, nom) values (3, 'Amérique centrale et Caraïbes');
insert into continent (id, nom) values (4, 'Amérique du Sud');
insert into continent (id, nom) values (5, 'Asie');
insert into continent (id, nom) values (6, 'Europe');
insert into continent (id, nom) values (7, 'Océanie');

INSERT INTO pays (id, nom) VALUES ( 1, 'Afrique du Sud');
INSERT INTO pays (id, nom) VALUES ( 2, 'Algérie');
INSERT INTO pays (id, nom) VALUES ( 3, 'Allemagne');
INSERT INTO pays (id, nom) VALUES ( 4, 'Antigua-et-Barbuda');
INSERT INTO pays (id, nom) VALUES ( 5, 'Arabie saoudite');
INSERT INTO pays (id, nom) VALUES ( 6, 'Argentine');
INSERT INTO pays (id, nom) VALUES ( 7, 'Australie');
INSERT INTO pays (id, nom) VALUES ( 8, 'Autriche');
INSERT INTO pays (id, nom) VALUES ( 9, 'Bahamas');
INSERT INTO pays (id, nom) VALUES ( 10, 'Bahreïn');
INSERT INTO pays (id, nom) VALUES ( 11, 'la Barbade');
INSERT INTO pays (id, nom) VALUES ( 12, 'Belgique');
INSERT INTO pays (id, nom) VALUES ( 13, 'Bermudes');
INSERT INTO pays (id, nom) VALUES ( 14, 'Biélorussie');
INSERT INTO pays (id, nom) VALUES ( 15, 'Botswana');
INSERT INTO pays (id, nom) VALUES ( 16, 'Brésil');
INSERT INTO pays (id, nom) VALUES ( 17, 'Bulgarie');
INSERT INTO pays (id, nom) VALUES ( 18, 'Burundi');
INSERT INTO pays (id, nom) VALUES ( 19, 'Cameroun');
INSERT INTO pays (id, nom) VALUES ( 20, 'Canada');
INSERT INTO pays (id, nom) VALUES ( 21, 'Chili');
INSERT INTO pays (id, nom) VALUES ( 22, 'Chine');
INSERT INTO pays (id, nom) VALUES ( 73, 'Chypre');
INSERT INTO pays (id, nom) VALUES ( 23, 'Colombie');
INSERT INTO pays (id, nom) VALUES ( 24, 'Corée du Sud');
INSERT INTO pays (id, nom) VALUES ( 25, 'Costa Rica');
INSERT INTO pays (id, nom) VALUES ( 26, 'Côte d''Ivoire');
INSERT INTO pays (id, nom) VALUES ( 27, 'Croatie');
INSERT INTO pays (id, nom) VALUES ( 28, 'Cuba');
INSERT INTO pays (id, nom) VALUES ( 29, 'Danemark');
INSERT INTO pays (id, nom) VALUES ( 30, 'Égypte');
INSERT INTO pays (id, nom) VALUES ( 31, 'Équateur');
INSERT INTO pays (id, nom) VALUES ( 32, 'Espagne');
INSERT INTO pays (id, nom) VALUES ( 33, 'Estonie');
INSERT INTO pays (id, nom) VALUES ( 34, 'États fédérés de Micronésie');
INSERT INTO pays (id, nom) VALUES ( 35, 'États-Unis');
INSERT INTO pays (id, nom) VALUES ( 36, 'Éthiopie');
INSERT INTO pays (id, nom) VALUES ( 37, 'Finlande');
INSERT INTO pays (id, nom) VALUES ( 1789, 'France');
INSERT INTO pays (id, nom) VALUES ( 38, 'Gabon');
INSERT INTO pays (id, nom) VALUES ( 39, 'Ghana');
INSERT INTO pays (id, nom) VALUES ( 40, 'Grèce');
INSERT INTO pays (id, nom) VALUES ( 41, 'Grenade');
INSERT INTO pays (id, nom) VALUES ( 42, 'Hongrie');
INSERT INTO pays (id, nom) VALUES ( 43, 'Inde');
INSERT INTO pays (id, nom) VALUES ( 44, 'Iran');
INSERT INTO pays (id, nom) VALUES ( 45, 'Irlande');
INSERT INTO pays (id, nom) VALUES ( 46, 'Israël');
INSERT INTO pays (id, nom) VALUES ( 47, 'Italie');
INSERT INTO pays (id, nom) VALUES ( 48, 'Jamaïque');
INSERT INTO pays (id, nom) VALUES ( 49, 'Japon');
INSERT INTO pays (id, nom) VALUES ( 50, 'Kazakhstan');
INSERT INTO pays (id, nom) VALUES ( 51, 'Kenya');
INSERT INTO pays (id, nom) VALUES ( 52, 'Kirghizistan');
INSERT INTO pays (id, nom) VALUES ( 53, 'Lettonie');
INSERT INTO pays (id, nom) VALUES ( 54, 'Lituanie');
INSERT INTO pays (id, nom) VALUES ( 55, 'Luxembourg');
INSERT INTO pays (id, nom) VALUES ( 56, 'Mali');
INSERT INTO pays (id, nom) VALUES ( 57, 'Maroc');
INSERT INTO pays (id, nom) VALUES ( 58, 'Maurice');
INSERT INTO pays (id, nom) VALUES ( 59, 'Mexique');
INSERT INTO pays (id, nom) VALUES ( 60, 'Namibie');
INSERT INTO pays (id, nom) VALUES ( 61, 'Nigeria');
INSERT INTO pays (id, nom) VALUES ( 62, 'Norvège');
INSERT INTO pays (id, nom) VALUES ( 63, 'Nouvelle-Zélande');
INSERT INTO pays (id, nom) VALUES ( 64, 'Ouganda');
INSERT INTO pays (id, nom) VALUES ( 65, 'Ouzbékistan');
INSERT INTO pays (id, nom) VALUES ( 66, 'Panama');
INSERT INTO pays (id, nom) VALUES ( 67, 'Paraguay');
INSERT INTO pays (id, nom) VALUES ( 68, 'Pays-Bas');
INSERT INTO pays (id, nom) VALUES ( 69, 'Pologne');
INSERT INTO pays (id, nom) VALUES ( 70, 'Porto Rico');
INSERT INTO pays (id, nom) VALUES ( 71, 'Portugal');
INSERT INTO pays (id, nom) VALUES ( 72, 'Qatar');
INSERT INTO pays (id, nom) VALUES ( 74, 'Taïwan');
INSERT INTO pays (id, nom) VALUES ( 75, 'République démocratique du Congo');
INSERT INTO pays (id, nom) VALUES ( 76, 'République dominicaine');
INSERT INTO pays (id, nom) VALUES ( 77, 'République tchèque');
INSERT INTO pays (id, nom) VALUES ( 78, 'Roumanie');
INSERT INTO pays (id, nom) VALUES ( 79, 'Royaume-Uni');
INSERT INTO pays (id, nom) VALUES ( 80, 'Russie');
INSERT INTO pays (id, nom) VALUES ( 81, 'Sénégal');
INSERT INTO pays (id, nom) VALUES ( 82, 'Serbie');
INSERT INTO pays (id, nom) VALUES ( 83, 'Seychelles');
INSERT INTO pays (id, nom) VALUES ( 84, 'Slovaquie');
INSERT INTO pays (id, nom) VALUES ( 85, 'Slovénie');
INSERT INTO pays (id, nom) VALUES ( 86, 'Soudan');
INSERT INTO pays (id, nom) VALUES ( 87, 'Sri Lanka');
INSERT INTO pays (id, nom) VALUES ( 88, 'Suède');
INSERT INTO pays (id, nom) VALUES ( 89, 'Suisse');
INSERT INTO pays (id, nom) VALUES ( 90, 'Thaïlande');
INSERT INTO pays (id, nom) VALUES ( 91, 'Trinité-et-Tobago');
INSERT INTO pays (id, nom) VALUES ( 92, 'Turquie');
INSERT INTO pays (id, nom) VALUES ( 93, 'Ukraine');
INSERT INTO pays (id, nom) VALUES ( 94, 'Uruguay');
INSERT INTO pays (id, nom) VALUES ( 95, 'Venezuela');
INSERT INTO pays (id, nom) VALUES ( 96, 'Zambie');
INSERT INTO pays (id, nom) VALUES ( 97, 'Zimbabwe');