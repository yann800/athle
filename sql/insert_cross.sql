/*
 ="INSERT INTO cros (rang, chrono, nom, club, sexe, dep, cat, naissance, championnat, annee) VALUES (" & A1 & ",'" & B1 & "','" & C1 & "','" & D1 & "','F','" & E1 & "','" & F1 & "',99,'reg', 2020);"
 */
UPDATE cros set chrono = REPLACE(chrono, '_', '''');
UPDATE cros set club = REPLACE(club, '_', '''');