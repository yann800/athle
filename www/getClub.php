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


$sql = "SELECT DISTINCT nom FROM club WHERE nom LIKE '%". $q ."%' LIMIT 30;";


$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
		
		if ($hint === "") {
			$hint = '{ "clubs": [{"c":"' . $row["nom"] . '"}';
		} else {
			$hint .= ',{"c":"' . $row["nom"] . '"}';
		}
		
	}
	$hint .= ']}';
}

// Output "no suggestion" if no hint was found or output correct values 
echo $hint === "" ? "aucun nom trouvé" : $hint;
?>