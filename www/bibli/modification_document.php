<?php

include "../constantes.php";

$id=$_POST["id"];
$titre=$_POST["titre"];
$auteur=$_POST["auteur"];
$annee=$_POST["annee"];
$type=$_POST["type"];
$theme=$_POST["theme"];
$bibli=$_POST["bibli"];
$commentaire=$_POST["commentaire"];


// Create connection
// $conn = new mysqli($servername, $username, $password, $dbname);
// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
$link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
mysql_select_db($dbname);



$sql = "UPDATE Document SET "
	. "titre  = '" . $titre  . "',"
	. "auteur = '" . $auteur . "',"
	. "annee = '" . $annee   . "',"
	. "type = '" . $type   . "',"
	. "theme = '" . $theme   . "',"
	. "bibli = '" . $bibli   . "',"
	. "commentaire = '" . $commentaire   . "' WHERE id = " . $id;
    
    echo $sql;
    
    $result = mysql_query($sql);
    if (!$result) {
        die('Requête invalide : ' . mysql_error());
    }
    
    echo '<script type="text/javascript">window.location = "/bibli";</script>';
    
    mysql_close($link);
?>