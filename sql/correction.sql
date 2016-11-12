select * from ligne order by naissance desc;

UPDATE ligne SET naissance = 1993 WHERE nom like 'anselme' and prenom = 'jeanne' and naissance = 2030;

UPDATE ligne SET prenom = REPLACE (prenom, '-', ' ') WHERE prenom LIKE '%-%';



CREATE TABLE IF NOT EXISTS personne (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `sexe` int(1) NOT NULL DEFAULT '0',
  `naissance` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


INSERT INTO personne (nom, prenom, naissance)
select nom, prenom, naissance from ligne  
where naissance > 1950 and naissance < 2000
group by nom, prenom, naissance 
order by nom, prenom, naissance;
-- 20 826 lignes





SELECT DISTINCT l1.nom, l1.prenom, l1.naissance, l2.naissance FROM ligne l1, ligne l2
WHERE l1.nom = l2.nom and l1.prenom = l2.prenom and l1.naissance != l2.naissance;

UPDATE ligne l SET l.naissance = (SELECT p.naissance  FROM personne p WHERE p.nom = l.nom AND p.prenom = l.prenom LIMIT 1)
WHERE l.naissance < 1950;

UPDATE ligne l SET l.naissance = (SELECT p.naissance  FROM personne p WHERE p.nom = l.nom AND p.prenom = l.prenom LIMIT 1)
WHERE l.naissance > 2000;