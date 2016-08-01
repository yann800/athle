
SELECT * FROM (



SELECT l_800.perf FROM ligne l_800
where l_800.nom = 'abdelkader' and l_800.idEpreuve = 800 and l_800.annee = 2016


,

SELECT l_1500.perf FROM ligne l_1500 
where l_1500.nom = 'abdelkader' and l_1500.idEpreuve = 1500 and l_1500.annee = 2016



) tmp