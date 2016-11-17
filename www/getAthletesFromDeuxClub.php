<?php

$c1 = '';
if (isset($_GET['c1']))
{
	$c1 = $_GET['c1'];
}
else {
	echo "param [c1] manquant";
	exit;
}

$hint = "";

// lookup all hints from array if $q is different from "" 
if ($c1 == "") {
	echo 'param c1 vide';
	exit;
}

$c2 = '';
if (isset($_GET['c2']))
{
	$c2 = $_GET['c2'];
}
else {
	echo "param [c2] manquant";
	exit;
}

$hint = "";

// lookup all hints from array if $q is different from ""
if ($c2 == "") {
	echo 'param c2 vide';
	exit;
}

include 'constantes.php';

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);


$sql = "SELECT DISTINCT p.id, p.nom FROM personne p, licence l1 
WHERE l1.idPersonne = p.id AND l1.idClub = ". $c1 . "
AND p.id IN (SELECT DISTINCT p2.id FROM personne p2
INNER JOIN licence l2 ON l2.idPersonne = p2.id WHERE l2.idClub = ". $c2 .");";


$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
		
		if ($hint === "") {
			$hint = '{ "athletes": [{"id":' . $row["id"] . ',"a":"' . $row["nom"] . '"}';
		} else {
			$hint .= ',{"id":' . $row["id"] . ', "a":"' . $row["nom"] . '"}';
		}
		
	}
	$hint .= ']}';
}

// Output "no suggestion" if no hint was found or output correct values 
echo $hint === "" ? "aucun club trouvé" : $hint;
?>