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


$sql = "SELECT DISTINCT l.idClub, c.nom, l.idPersonne, a.nom FROM  personne a, licence l
		
		JOIN club c ON c.id = l.idClub AND c.id != ". $q ."
		
		WHERE l.idPersonne IN (
			SELECT DISTINCT l2.idPersonne FROM licence l2 WHERE l2.idClub = ". $q .")
		AND l.idPersonne = a.id			
		ORDER BY l.idClub;";



$result = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

// First we make the query and store the rows in an array
$data_array = array();
while ($data = mysql_fetch_assoc($result)) {
	$data_array[] = $data;
}
mysql_close();


$current = -1;

foreach ($data_array as $row) {

	if ($row["id"] != $current){
		
		$data_array_avec_nom_ath.push($row["nom"]);
		
		
	}
	else {
			
	}
}


$hint = '{ "clubs": [';


foreach ($data_array_avec_nom_ath as $row) {
		
		if ($hint === "") {
			$hint .= '{"c":"' . $row["nom"] . '", "a":"' . $row[a] . '"}';
		} else {
			$hint .= ',{"c":"' . $row["nom"] . '", "a":"' . $row[a] . '"}';
		}
		
	}
	$hint .= ']}';


// Output "no suggestion" if no hint was found or output correct values 
echo $hint === "" ? "aucun nom trouvé" : $hint;
?>é__