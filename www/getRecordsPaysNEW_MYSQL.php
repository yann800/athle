<?php

$id_pays = '';
if (isset($_GET['id_pays']))
{
	$id_pays = $_GET['id_pays'];
}
else {
	echo "param [id_pays] manquant";
	exit;
}

$sexe = '';
if (isset($_GET['sexe']))
{
	$sexe = $_GET['sexe'];
}

$hint = "";

if ($id_pays == "") {
	echo 'param id_pays vide';
	exit;
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
// Check connection

if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 

$clauseSexe = '';
if ($sexe != "") {
	$clauseSexe = " AND r.sexe = " . $sexe;
}

$sql = "SELECT r.epreuve AS e, r.nom AS n, r.perf AS p, r.annee AS a, r.sexe AS s, r.rang AS rang FROM record r " .
" WHERE r.idPays = " . $id_pays . 
	$clauseSexe .
	";";

$result = $conn->query($sql);

if ($result->num_rows == -1) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = $result->fetch_assoc()) {
	// NEW while($row = $result->fetch_assoc()) {
		if ($hint === "") {

		$hint = '{ "record": [{"e":"' . $row["e"] . '","nom":"' . $row["n"] . '","perf":"' . $row["p"] . '","annee":"' . $row["a"] . '","sexe":"' . $row["s"] . '","rang":"' . $row["rang"]. '"}';
		} else {
			$hint .= ',{"e":"' . $row["e"] . '","nom":"' . $row["n"] . '","perf":"' . $row["p"] . '","annee":"' . $row["a"] . '","sexe":"' . $row["s"] . '","rang":"' . $row["rang"].'"}';
		}
	}
	$hint .= ']}';
}

echo $hint === "" ? '{"record" : [ {"e" : "100", "nom" : "pays non trouvé","perf" : "10.00"}, {"e" : "200", "nom" : "pays nom trouvé", "perf" : "20.00"}]}' : $hint;

?>