<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/dataTables.bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/athle.css" media="screen" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</head>

<?php


$param_nom = '';
if (isset($_POST['nom']))
{
    $param_nom = $_POST['nom'];
}

?>
<body>





	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>

<?php
echo '<h1>Stade ou ville : ' . $param_nom . '</h1>';
?>
			<br />


<table class="table table-striped table-bordered" id="moyenne1">
<thead>
<tr>
<th>Epreuve</th><th>Sexe</th><th>Record</th></tr>
</thead>
<tbody>



<?php

// if (preg_match("#[a\-ZA\-Z]#", $param_nom)) {
    //echo "Caratere interdit";
//exit;
//} else {
//    echo "OK Bonne chaine";
//}

	$servername = "localhost";
	$username = "root";
	$password = "xxxx_bad_mdp";
	$dbname = "base_athle";

	// Create connection http://php.net/manual/fr/function.mysql-connect.php
	
	$link  =  mysql_connect($servername, $username, $password)
	or die( "Impossible de se connecter : "  .  mysql_error ());
	
	mysql_select_db($dbname);

		
	
	
	$sql = "SELECT idEpreuve, sexe, MIN(perf) AS min_perf"
. " FROM ligne"
. " WHERE ville =  '". $param_nom ."'"
. " GROUP BY idEpreuve, sexe"
. " ORDER BY sexe ASC";


// $result = mysql_query($sql);

$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

// on fait une boucle qui va faire un tour pour chaque enregistrement
while($row = mysql_fetch_assoc($req)){

        echo '<tr><td><a href="bilan_ville?epreuve=' . $row["idEpreuve"]. '&sexe=' . $row["sexe"] . '&ville=' .  $param_nom . '">' . $row["idEpreuve"]. '</a></td><td>' . $row["sexe"]. '</td><td>' . $row["min_perf"]. '</td></tr>';

}



?>


</tbody>
</table>


			<pre>SELECT idEpreuve, sexe, MIN(perf) 
FROM ligne
WHERE ville =  [nom_ville]
GROUP BY idEpreuve, sexe
ORDER BY sexe ASC;
</pre>



		</div>
	</article>

	<script src="js/menu.js" type="text/javascript"></script>
	

</body>
</html>