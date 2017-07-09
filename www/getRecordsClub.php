<?php

$nom_club = '';
if (isset($_GET['nom_club']))
{
	$nom_club = $_GET['nom_club'];
}
else {
	echo "param [nom_club] manquant";
	exit;
}

$sexe = '';
if (isset($_GET['sexe']))
{
	$sexe = $_GET['sexe'];
}
else {
	echo "param [sexe] manquant";
	exit;
}

$hint = "";

if ($nom_club == "") {
	echo 'param nom_club vide';
	exit;
}
if ($sexe == "") {
	echo 'param sexe vide';
	exit;
}



if ($nom_club == 'Ac Paris-joinville'){

	if ($sexe == '0'){
		echo "{'record' : [ "
		. {'epreuve' : '100', 'nom' : 'ZIRIGNON - FENOUIL', "'perf' : '10.40'},"
		. {'epreuve' : '200', 'nom' : 'FENOUIL G.', 'perf' : '20.74'},"
		. {'epreuve' : '400', 'nom' : 'NALLET Jean Claude', 'perf' : '45.1'},"
		. {'epreuve' : '800', 'nom' : 'CORNETTE Frédéric', 'perf' : '1.45.82'},"
		. {'epreuve' : '1500', 'nom' : 'KHALOUKI Hassan', 'perf' : '3.39.50'},"
		. {'epreuve' : '3000', 'nom' : 'KHALOUKI Hassan', 'perf' : '8.07.19'},"
		. {'epreuve' : '5000', 'nom' : 'KERROUT Mounir', 'perf' : '14.33.01'},"
		. {'epreuve' : '10000', 'nom' : 'BEUREY C.', 'perf' : '30.53.4'},"
		. {'epreuve' : 'Marathon', 'nom' : 'GABORIAU C.', 'perf' : '2h28.39'},"
		. {'epreuve' : '400 Haies', 'nom' : 'club nom trouvé', 'perf' : '48.60'},"
		. {'epreuve' : 'Steeple', 'nom' : 'KHALOUKI Hassan', 'perf' : '8.56.40'},"
		. {'epreuve' : 'Longueur', 'nom' : 'PINCEMAIL S.', 'perf' : '7m70'},"
		. {'epreuve' : 'Triple saut', 'nom' : 'FOFANA Colomba', "'perf' : '17m34'}"
		. "]}";
		exit;
	}
	else {
		echo "{'record' : [ "
		. {'epreuve' : '100', 'nom' : 'SINGA O.', "'perf' : '11.46'},"
		. {'epreuve' : '200', 'nom' : 'HURIS Murielle', 'perf' : '20.00'},"
		. {'epreuve' : '400', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '800', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '1500', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '3000', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '5000', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '1000', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '200', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '200', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '200', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. {'epreuve' : '200', 'nom' : 'club nom trouvé', 'perf' : '20.00'},"
		. "]}";
		exit;
	}
}

if ($nom_club == 'Us Creteil'){

	if ($sexe == '0'){
		echo "{'record' : [ "
		. {'epreuve' : '100', 'nom' : 'SECK Charles', "'perf' : '10.19'},"
		. {'epreuve' : '200', 'nom' : 'N DIAYE Abdoulaye', 'perf' : '21.18'},"
		. {'epreuve' : '400', 'nom' : 'ROSILETTE Rodolphe', 'perf' : '46.56'},"
		. {'epreuve' : '800', 'nom' : 'BOSSE Pierre Amboise', 'perf' : '1.45.56'},"
		. {'epreuve' : '1500', 'nom' : 'DOUKKANNA Rabii', 'perf' : '3.37.81'},"
		. {'epreuve' : '3000', 'nom' : 'VINDEX Jean Philippe', 'perf' : '8.00.19'},"
		. {'epreuve' : '5000', 'nom' : 'VINDEX Jean Philippe', 'perf' : '13.35.65'},"
		. {'epreuve' : '10000', 'nom' : 'VINDEX Jean Philippe', 'perf' : '28.40.72'},"
		. {'epreuve' : 'Marathon', 'nom' : 'GUENNANI Mohamed', 'perf' : '2h10.46'},"
		. {'epreuve' : '400 Haies', 'nom' : 'CARISTAN Stéphane', 'perf' : '50.45'},"
		. {'epreuve' : 'Steeple', 'nom' : 'BELABBAS Khaled', 'perf' : '8.41.33'},"
		. {'epreuve' : 'Longueur', 'nom' : 'Valendoff', 'perf' : '7m86'},"
		. {'epreuve' : 'Triple saut', 'nom' : 'FILET Arius', "'perf' : '16m82'}"
		. "]}";
		exit;
	}
	else {
		echo "{'record' : [ "
		. {'epreuve' : '100', 'nom' : 'GIRARD Patricia', "'perf' : '11.25'},"
		. {'epreuve' : '200', 'nom' : 'JACQUES SEBASTIEN Lina', 'perf' : '22.59'},"
		. {'epreuve' : '400', 'nom' : 'BEVIS Louise', 'perf' : '52.14'},"
		. {'epreuve' : '800', 'nom' : 'EL HANNOUNI Assia', 'perf' : '2.05.30'},"
		. {'epreuve' : '1500', 'nom' : 'BOUSSAID Soumaya', 'perf' : '4.21.21'},"
		. {'epreuve' : '3000', 'nom' : 'BOUSSAID Soumaya', 'perf' : '9.41.94'},"
		. {'epreuve' : '5000', 'nom' : 'ROLLAND Marjorie', 'perf' : '17.05.19'},"
		. {'epreuve' : 'Marathon', 'nom' : 'TRUEL Aurélia', 'perf' : '2h47.28'}"
		. "]}";
		exit;
	}
}


// ===================
include 'constantes.php';

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
// $link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
// mysql_select_db($dbname);

// NEW PHP
$conn = new mysqli($servername, $username, $password, $dbname);


if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 



$sql = "SELECT l.idEpreuve AS e, CONCAT(l.prenom, ,' ', l.nom) AS n, l.perf AS p FROM ligne l " .
" WHERE l.club = '" . $nom_club . "'" .
	" AND l.sexe = " . $sexe .
	" AND perf IN (" .
			" SELECT MIN(perf)" .
			" FROM ligne l2" .
			" WHERE l2.club = '" . $nom_club . "'" .
			" AND l2.sexe = " . $sexe .
			" GROUP BY l2.idEpreuve)";

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

if ( $nb == 0 ) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
	// NEW while($row = $result->fetch_assoc()) {
		if ($hint === "") {
			$hint = '{ "record": [{"e":"' . $row["e"] . '","nom":"' . $nom_club . '","perf":"' . $row["p"] . '"}';
		} else {
			$hint .= ',{"e":"' . $row["e"] . '","nom":"' . $nom_club . '","perf":"' . $row["p"] . '"}';
		}
		}
	$hint .= ']}';
}

// echo $hint === "" ? "aucun nom trouvé" : $hint;
echo $hint === "" ? '{"record" : [ {"epreuve" : "100", "nom" : "club non trouvé","perf" : "10.00"}, {"epreuve" : "200", "nom" : "club nom trouvé", "perf" : "20.00"}]}' : $hint;

?>