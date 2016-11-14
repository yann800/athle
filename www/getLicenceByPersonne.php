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


$servername = "localhost";
$username = "root";
$password = "xxxx_bad_mdp";
$dbname = "base_athle";

// Create connection http://php.net/manual/fr/function.mysql-connect.php

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);


$sql = "SELECT DISTINCT c.id, c.nom FROM licence l INNER JOIN club c ON c.id = l.idClub WHERE l.idPersonne = ". $q .";";


$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
		
		if ($hint === "") {
			$hint = '{ "clubs": [{"id":' . $row["id"] . ',"c":"' . $row["nom"] . '"}';
		} else {
			$hint .= ',{"id":' . $row["id"] . ', "c":"' . $row["nom"] . '"}';
		}
		
	}
	$hint .= ']}';
}

// Output "no suggestion" if no hint was found or output correct values 
echo $hint === "" ? "aucun club trouvé" : $hint;
?>