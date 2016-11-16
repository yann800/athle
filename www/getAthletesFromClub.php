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

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);


$sql = "SELECT DISTINCT p.id, p.nom FROM personne p INNER JOIN licence l ON l.idPersonne = p.id WHERE l.idClub = ". $q .";";


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