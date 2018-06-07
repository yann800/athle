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

$seye = '';
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

// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
$link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
mysql_select_db($dbname);


$sql = "SET @pos=0;";
$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

$sql = "SELECT @pos:=@pos+1 AS rang, r.perf AS perf, p.nom AS pays, r.nom AS athlete, r.annee AS annee FROM record r"
. " INNER JOIN pays_wiki p ON p.id = r.idPays"
. " WHERE r.epreuve = " . $epreuve . " AND r.sexe = " . $sexe 
. " ORDER BY r.perf;";	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	// rien à faire
}
else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
	// NEW while($row = $result->fetch_assoc()) {
		if ($hint === "") {
			$hint = '{ "record": [{"rang":"' . $row["rang"] . '","perf":"' . $row["perf"] . '","pays":"' . $row["pays"] . '","athlete":"' . $row["athlete"] . '","annee":"' . $row["annee"] . '"}';
		} else {
			$hint .=            ',{"rang":"' . $row["rang"] . '","perf":"' . $row["perf"] . '","pays":"' . $row["pays"] . '","athlete":"' . $row["athlete"] . '","annee":"' . $row["annee"] . '"}';
		}
	}
	$hint .= ']}';
}

echo $hint === "" ? '{"record" : [ {"e" : "100", "nom" : "pays non trouvé","perf" : "10.00"}, {"e" : "200", "nom" : "pays nom trouvé", "perf" : "20.00"}]}' : $hint;

?>