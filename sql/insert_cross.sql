/*
 ="INSERT INTO cros (rang, chrono, nom, club, sexe, dep, cat, naissance, championnat, annee) VALUES (" & A1 & ",'" & B1 & "','" & C1 & "','" & D1 & "','F','" & E1 & "','" & F1 & "',99,'reg', 2020);"
 */
UPDATE cros set chrono = REPLACE(chrono, '_', '''');
UPDATE cros set club = REPLACE(club, '_', '''');

SELECT  SUBSTRING(cat, 1,3) FROM cros WHERE LENGTH(cat) > 3;

UPDATE cros SET naissance = SUBSTRING(cat, 5) WHERE naissance = 99;
UPDATE cros SET cat = SUBSTRING(cat, 1,3) WHERE LENGTH(cat) > 3


ALTER TABLE cros DROP xx ;

