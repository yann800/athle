<?php


$q = '';
if (isset($_GET['q']))
{
	$q = $_GET['q'];
}
else {
	echo "param [q] manquant";
	exit;
}

$hint = "";

// lookup all hints from array if $q is different from "" 
if ($q == "") {
	echo 'param q vide';
	exit;
}


include 'constantes.php';

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
// $link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
// mysql_select_db($dbname);


$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); } 

$sql = "SELECT e, MIN(perfClean) AS p FROM    (    SELECT idEpreuve AS e, IF ((LENGTH(perf) = 7), CONCAT('0', perf), perf)  AS perfClean  FROM ligne WHERE nom = '". $q ."' ) chronosAvecZero GROUP BY e;";


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
			$hint = '{ "record": [{"e":"' . $row["e"] . '","nom":"' . $q . '","perf":"' . $row["p"] . '"}';
		} else {
			$hint .= ',{"e":"' . $row["e"] . '","nom":"' . $q . '","perf":"' . $row["p"] . '"}';
		}
		}
	$hint .= ']}';
}

// echo $hint === "" ? "aucun nom trouvé" : $hint;
echo $hint === "" ? '{"record" : [ {"epreuve" : "100", "nom" : "athlète non trouvé","perf" : "10.00"}, {"epreuve" : "200", "nom" : "athlète nom trouvé", "perf" : "20.00"}]}' : $hint;

?>