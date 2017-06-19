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