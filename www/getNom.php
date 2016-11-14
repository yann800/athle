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

if (strlen($q) < 3) {
	echo 'param q de longueur inférieure à 3';
	exit;
}


include 'constantes.php';




// Create connection http://php.net/manual/fr/function.mysql-connect.php

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);


$sql = "SELECT DISTINCT nom FROM ligne WHERE nom LIKE '%". $q ."%' LIMIT 200;";


$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
		
		if ($hint === "") {
			$hint = $row["nom"];
		} else {
			$hint .= ', ' . $row["nom"];
		}
	}
}

// Output "no suggestion" if no hint was found or output correct values 
echo $hint === "" ? "aucun nom trouvé" : $hint;
?>