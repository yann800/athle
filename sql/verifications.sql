-- Vérifications
				

SELECT annee, idEpreuve, max(rang) FROM ligne group by annee, idEpreuve;

SELECT count(*) FROM ligne;

DELETE FROM ligne WHERE naissance > 300; -- >>> 3 rows deleteds

UPDATE ligne set naissance = naissance + 1000 WHERE naissance < 1000; -- >>> 14 rows updated 


SELECT SUBSTRING(club, 1) FROM ligne  where club like ' %';

update ligne set club = SUBSTRING(club, 2) where club like ' %';