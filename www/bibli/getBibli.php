<?php

// ===================
include '../constantes.php';

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
$link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
mysql_select_db($dbname);


$sql = "SELECT d.id AS id, d.titre AS titre, d.auteur AS auteur, d.type AS type, d.theme AS theme, d.bibli AS bibli, d.annee AS annee, d.commentaire AS commentaire FROM Document d";	
	
$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

$nb = mysql_num_rows($req);

$hint = "";

if ( $nb == 0 ) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
	// NEW while($row = $result->fetch_assoc()) {
		if ($hint === "") {
		    $hint = '[{"id":' . $row["id"] . ', "titre":"' . $row["titre"] . '","auteur":"' . $row["auteur"] . '","annee":"' . $row["annee"] . '","type":"' . $row["type"] . '","theme":"' . $row["theme"] . '","bibli":"' . $row["bibli"] . '","commentaire":"' . $row["commentaire"] . '"}';
		} else {
		    $hint .= ',{"id":' . $row["id"] . ', "titre":"' . $row["titre"] . '","auteur":"' . $row["auteur"] . '","annee":"' . $row["annee"] . '","type":"' . $row["type"] . '","theme":"' . $row["theme"] . '","bibli":"' . $row["bibli"] . '","commentaire":"' . $row["commentaire"] . '"}';
		}
	}
	$hint .= ']';
}

echo $hint;

?>