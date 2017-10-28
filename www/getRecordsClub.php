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

	if ($sexe == "0"){
		echo '{"record" : [ '
		. '{"e" : "100", "nom" : "ZIRIGNON - FENOUIL", "perf" : "10.40"},'
		. '{"e" : "200", "nom" : "FENOUIL G.", "perf" : "20.74"},'
		. '{"e" : "400", "nom" : "NALLET Jean Claude", "perf" : "45.1"},'
		. '{"e" : "800", "nom" : "CORNETTE Frédéric", "perf" : "1.45.82"},'
		. '{"e" : "1500", "nom" : "KHALOUKI Hassan", "perf" : "3.39.50"},'
		. '{"e" : "3000", "nom" : "KHALOUKI Hassan", "perf" : "8.07.19"},'
		. '{"e" : "5000", "nom" : "KERROUT Mounir", "perf" : "14.33.01"},'
		. '{"e" : "10000", "nom" : "BEUREY C.", "perf" : "30.53.4"},'
		. '{"e" : "Marathon", "nom" : "GABORIAU C.", "perf" : "2h28.39"},'
		. '{"e" : "400 Haies", "nom" : "club nom trouvé", "perf" : "48.60"},'
		. '{"e" : "Steeple", "nom" : "KHALOUKI Hassan", "perf" : "8.56.40"},'
		. '{"e" : "Longueur", "nom" : "PINCEMAIL S.", "perf" : "7m70"},'
		. '{"e" : "Triple saut", "nom" : "FOFANA Colomba", "perf" : "17m34"}'
		. ']}';
		exit;
	}
	else {
		echo '{"record" : [ '
		. '{"e" : "100", "nom" : "SINGA O.", "perf" : "11.46"},'
		. '{"e" : "200", "nom" : "HURIS Murielle", "perf" : "20.00"},'
		. '{"e" : "400", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "800", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "1500", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "3000", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "5000", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "10000", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "400 Haies", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "Steeple", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "Longueur", "nom" : "club nom trouvé", "perf" : "20.00"},'
		. '{"e" : "Triple saut", "nom" : "club nom trouvé", "perf" : "20.00"}'
		. ']}';
		exit;
	}
}

if ($nom_club == "Us Creteil"){

	if ($sexe == "0"){
		echo '{"record" : [ '
		. '{"e" : "100", "nom" : "SECK Charles", "perf" : "10.19"},'
		. '{"e" : "200", "nom" : "N DIAYE Abdoulaye", "perf" : "21.18"},'
		. '{"e" : "400", "nom" : "ROSILETTE Rodolphe", "perf" : "46.56"},'
		. '{"e" : "800", "nom" : "BOSSE Pierre Amboise", "perf" : "1.45.56"},'
		. '{"e" : "1500", "nom" : "DOUKKANNA Rabii", "perf" : "3.37.81"},'
		. '{"e" : "3000", "nom" : "VINDEX Jean Philippe", "perf" : "8.00.19"},'
		. '{"e" : "5000", "nom" : "VINDEX Jean Philippe", "perf" : "13.35.65"},'
		. '{"e" : "10000", "nom" : "VINDEX Jean Philippe", "perf" : "28.40.72"},'
		. '{"e" : "Marathon", "nom" : "GUENNANI Mohamed", "perf" : "2h10.46"},'
		. '{"e" : "400 Haies", "nom" : "CARISTAN Stéphane", "perf" : "50.45"},'
		. '{"e" : "Steeple", "nom" : "BELABBAS Khaled", "perf" : "8.41.33"},'
		. '{"e" : "Longueur", "nom" : "Valendoff", "perf" : "7m86"},'
		. '{"e" : "Triple saut", "nom" : "FILET Arius", "perf" : "16m82"}'
		. ']}';
		exit;
	}
	else {
		echo '{"record" : [ '
		. '{"e" : "100", "nom" : "GIRARD Patricia", "perf" : "11.25"},'
		. '{"e" : "200", "nom" : "JACQUES SEBASTIEN Lina", "perf" : "22.59"},'
		. '{"e" : "400", "nom" : "BEVIS Louise", "perf" : "52.14"},'
		. '{"e" : "800", "nom" : "EL HANNOUNI Assia", "perf" : "2.05.30"},'
		. '{"e" : "1500", "nom" : "BOUSSAID Soumaya", "perf" : "4.21.21"},'
		. '{"e" : "3000", "nom" : "BOUSSAID Soumaya", "perf" : "9.41.94"},'
		. '{"e" : "5000", "nom" : "ROLLAND Marjorie", "perf" : "17.05.19"},'
		. '{"e" : "Marathon", "nom" : "TRUEL Aurélia", "perf" : "2h47.28"}'
		. ']}';
		exit;
	}
}


// ===================
include 'constantes.php';

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
$link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
mysql_select_db($dbname);

// NEW PHP
// $conn = new mysqli($servername, $username, $password, $dbname);


// if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 



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
echo $hint === "" ? '{"record" : [ {"e" : "100", "nom" : "club non trouvé","perf" : "10.00"}, {"e" : "200", "nom" : "club nom trouvé", "perf" : "20.00"}]}' : $hint;

?>