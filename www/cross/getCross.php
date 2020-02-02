<?php


$annee = '';
if (isset($_GET['annee']))
{
    $annee = $_GET['annee'];
}
else {
	echo "param [annee] manquant";
	exit;
}

$hint = "";

// lookup all hints from array if $annee is different from "" 
if ($annee == "") {
	echo 'param annee vide';
	exit;
}

include '../constantes.php';

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
// $link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
// mysql_select_db($dbname);


$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 

$sql = "SELECT rang, chrono, nom, club, sexe, dep, cat, naissance, championnat, annee FROM  cros";

// OLD $req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$result = $conn->query($sql);

// OLD $nb = mysql_num_rows($req);

// if ($result->num_rows > 0) {

// OLD if ( $nb == 0 ) {
if ($result->num_rows == -1) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	// OLD while($row = mysql_fetch_assoc($req)){
	while($row = $result->fetch_assoc()) {
		if ($hint === "") {
		    $hint = '{ "cross": [{"r":"' . $row["rang"] . '","n":"' . $row["nom"] . '","c":"' . $row["chrono"] . '"}';
		} else {
		    $hint .= ',{"r":"' . $row["rang"] . '","n":"' . $row["nom"] . '","c":"' . $row["chrono"] . '"}';
		}
		}
	$hint .= ']}';
}

// echo $hint === "" ? "aucun nom trouvé" : $hint;
echo $hint === "" ? '{"cross" : [ {"r" : "0", "nom" : "erreur","chrono" : "0"}]}' : $hint;

?>