<?php

$epreuve = '';
if (isset($_GET['epreuve']))
{
	$epreuve = $_GET['epreuve'];
}
else {
	echo "param [epreuve] manquant";
	exit;
}

$sexe = '';
if (isset($_GET['seye']))
{
	$sexe = $_GET['seye'];
}
else {
	echo "param [seye] manquant";
	exit;
}

$hint = "";

if ($epreuve == "") {
	echo 'param epreuve vide';
	exit;
}
if ($sexe == "") {
	echo 'param sexe vide';
	exit;
}

// ===================
include 'constantes.php';

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 

// $sql = "SET @pos=0;";
// $result = $conn->query($sql);
$condition_100 = '';
if ($epreuve == '100') {
	$condition_100 = "LENGTH(r.perf),";
}

$sql = "SELECT r.rang AS rang, r.perf AS perf, p.nom AS pays, r.nom AS athlete, r.annee AS annee FROM record r"
. " INNER JOIN pays_wiki p ON p.id = r.idPays"
. " WHERE r.epreuve = '" . $epreuve . "' AND r.sexe = " . $sexe 
. " ORDER BY " . $condition_100 . "r.perf;";	
	
$result = $conn->query($sql);

if ($result->num_rows == -1) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = $result->fetch_assoc()) {
		if ($hint === "") {
			$hint = '{ "records": [{"rang":"' . $row["rang"] . '","perf":"' . $row["perf"] . '","pays":"' . $row["pays"] . '","athlete":"' . $row["athlete"] . '","annee":"' . $row["annee"] . '"}';
		} else {
			$hint .=            ',{"rang":"' . $row["rang"] . '","perf":"' . $row["perf"] . '","pays":"' . $row["pays"] . '","athlete":"' . $row["athlete"] . '","annee":"' . $row["annee"] . '"}';
		}
	}
	$hint .= ']}';
}

echo $hint === "" ? '{"records" : [ {"e" : "100", "nom" : "pays non trouvé","perf" : "10.00"}, {"e" : "200", "nom" : "pays nom trouvé", "perf" : "20.00"}]}' : $hint;

?>