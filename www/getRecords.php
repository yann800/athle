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


// Create connection http://php.net/manual/fr/function.mysql-connect.php

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);


$sql = "SELECT idEpreuve AS e, min(perf) AS p FROM ligne WHERE nom = '". $q ."' GROUP BY idEpreuve;";


$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
		
		if ($hint === "") {
			$hint = '{ "record": [{"e":"' . $row["p"] . ',"nom":"' . $q . ',"perf":"' . $row["p"] . '"}';
		} else {
			$hint .= ',{"e":"' . $row["p"] . ',"nom":"' . $q . ',"perf":"' . $row["p"] . '"}';
		}
	}
	$hint .= ']}';
}

// echo $hint === "" ? "aucun nom trouvé" : $hint;
echo $hint === "" ? '{"record" : [ {"epreuve" : "100", "nom" : "aucun nom trouvé","perf" : "10.00"}, {"epreuve" : "200", "nom" : "aucun nom trouvé", "perf" : "20.00"}]}' : $hint;

?>