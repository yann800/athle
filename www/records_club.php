<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/dataTables.bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/athle.css" media="screen" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

<?php


$param_id = '';
if (isset($_GET['id']))
{
	$param_id = $_GET['id'];
}
else {
	echo "param [id] manquant";
	exit;
}



include 'constantes.php';




// Create connection http://php.net/manual/fr/function.mysql-connect.php

$link  =  mysql_connect($servername, $username, $password)
or die( "Impossible de se connecter : "  .  mysql_error ());

mysql_select_db($dbname);

$result = mysql_query("SELECT nom FROM club WHERE id=" . $param_id);

$row = mysql_fetch_row($result);

$nom_club = $row[0];

echo '<title>Record du club ' . $nom_club . '</title>';
?>
</head>

<body>

	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>

<?php
echo '<h1>Records du club ' . $nom_club . '</h1>';
?>

			<div class="alert alert-info">
			Note : l'âge est obtenu par la soustraction (anne performance - annee naissance).
			Comme le mois de naissance n'est pas pris en compte, cette formule n'est pas toujours satisfaisante. En effet pour des perf réalisées p.ex. en juin 2010 :
			<ul>
			<li>pour un athlète né en février 1970, c'est bon, l'âge est bien 2010 - 1970 = 40</li>
			<li>pour un athlète né en octobre 1970, en revanche l'âge au moment de la perf est en fait 39.</li>
			</ul>
			</div>

			<h2>Records 35-39 féminin</h2>

<table class="table table-striped table-bordered" id="moyenne1">
<thead>
<tr>
<th>Epreuve</th><th>Nom</th><th>Prénom</th><th>Perf</th><th>Points</th><th>Ville</th><th>Année</th><th>Age</th></tr>
</thead>
				<tbody>
	
<?php	
	
	$sql = "SELECT l.idEpreuve, l.nom, l.prenom, l.perf, l.points, l.ville, l.annee AS r_annee, (l.annee - l.naissance) AS age FROM ligne l " .
	" WHERE l.club = '" . $nom_club . "'" .
		" AND (l.annee - l.naissance) > 34 " .
		" AND (l.annee - l.naissance) < 40 " .
		" AND l.sexe =1 " .
		" AND perf IN (" .
				" SELECT MIN(perf)" .
				" FROM ligne l2" .
				" WHERE l2.club = '" . $nom_club . "'" .
				" AND (l2.annee - l2.naissance) > 34" .
				" AND (l2.annee - l2.naissance) < 40" .
				" AND l2.sexe =1" .
				" GROUP BY l2.idEpreuve)";
	
	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

while($row = mysql_fetch_assoc($req)){

  echo "<tr><td>" . $row["idEpreuve"]. "</td><td>"
		. $row["nom"]. "</td><td>"
		. $row["prenom"]. "</td><td>"
		. $row["perf"]. "</td><td>"
		. $row["points"]. "</td><td>"
		. $row["ville"]. "</td><td>"
		. $row["r_annee"]. "</td><td>"
		. $row["age"]. "</td></tr>";
}



?>


</tbody>
</table>

  
<div class="panel-group" id="accordion">
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne">Voir le SQL</a>
      </h4>

        </div>
        <div id="collapseOne" class="panel-collapse collapse">
           <!-- 
             <div class="panel-body"><p>
Note : </p></div>
            -->


			<pre><code>SELECT l.idEpreuve, l.nom, l.prenom, l.perf, l.points, l.ville, l.annee, (l.annee - l.naissance) AS age
FROM ligne l
WHERE l.club = '" . $nom_club . "' AND (l.annee - l.naissance) &gt; 34 AND (l.annee - l.naissance) &lt; 40 AND l.sexe = 1
AND perf  IN 
	(SELECT MIN(perf) 
	FROM ligne l2
	WHERE l2.club = '" . $nom_club . "' and (l2.annee - l2.naissance) &gt; 34 and (l2.annee - l2.naissance) &lt; 40 and l2.sexe = 1
	GROUP BY l2.idEpreuve
	)
</code></pre>


        </div>
    </div>
</div>



			<h2>Records +40 féminin</h2>

<table class="table table-striped table-bordered" id="moyenne1">
<thead>
<tr>
<th>Epreuve</th><th>Nom</th><th>Prénom</th><th>Perf</th><th>Points</th><th>Ville</th><th>Année</th><th>Age</th></tr>
</thead>
				<tbody>
	
<?php	
	
	$sql = "SELECT l.idEpreuve, l.nom, l.prenom, l.perf, l.points, l.ville, l.annee AS r_annee, (l.annee - l.naissance) AS age FROM ligne l " .
	" WHERE l.club = '" . $nom_club . "'" .
		" AND (l.annee - l.naissance) > 39 " .
		" AND l.sexe =1 " .
		" AND perf IN (" .
				" SELECT MIN(perf)" .
				" FROM ligne l2" .
				" WHERE l2.club = '" . $nom_club . "'" .
				" AND (l2.annee - l2.naissance) > 39" .
				" AND l2.sexe =1" .
				" GROUP BY l2.idEpreuve)";
	
	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

while($row = mysql_fetch_assoc($req)){

  echo "<tr><td>" . $row["idEpreuve"]. "</td><td>"
		. $row["nom"]. "</td><td>"
		. $row["prenom"]. "</td><td>"
		. $row["perf"]. "</td><td>"
		. $row["points"]. "</td><td>"
		. $row["ville"]. "</td><td>"
		. $row["r_annee"]. "</td><td>"
		. $row["age"]. "</td></tr>";
}



?>


</tbody>
</table>







			<h2>Records 35-39 masculin</h2>


<table class="table table-striped table-bordered" id="moyenne1">
<thead>
<tr>
<th>Epreuve</th><th>Nom</th><th>Prénom</th><th>Perf</th><th>Points</th><th>Ville</th><th>Année</th><th>Age</th></tr>
</thead>
				<tbody>
	
<?php	
	
	$sql = "SELECT l.idEpreuve, l.nom, l.prenom, l.perf, l.points, l.ville, l.annee AS r_annee, (l.annee - l.naissance) AS age FROM ligne l " .
	" WHERE l.club = '" . $nom_club . "'" .
		" AND (l.annee - l.naissance) > 34 " .
		" AND (l.annee - l.naissance) < 40 " .
		" AND l.sexe = 0 " .
		" AND perf IN (" .
				" SELECT MIN(perf)" .
				" FROM ligne l2" .
				" WHERE l2.club = '" . $nom_club . "'" .
				" AND (l2.annee - l2.naissance) > 34" .
				" AND (l2.annee - l2.naissance) < 40" .
				" AND l2.sexe = 0" .
				" GROUP BY l2.idEpreuve)";
	
	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

while($row = mysql_fetch_assoc($req)){

  echo "<tr><td>" . $row["idEpreuve"]. "</td><td>"
		. $row["nom"]. "</td><td>"
		. $row["prenom"]. "</td><td>"
		. $row["perf"]. "</td><td>"
		. $row["points"]. "</td><td>"
		. $row["ville"]. "</td><td>"
		. $row["r_annee"]. "</td><td>"
		. $row["age"]. "</td></tr>";
}



?>


</tbody>
</table>


			<h2>Records +40 masculin</h2>



<table class="table table-striped table-bordered" id="moyenne1">
<thead>
<tr>
<th>Epreuve</th><th>Nom</th><th>Prénom</th><th>Perf</th><th>Points</th><th>Ville</th><th>Année</th><th>Age</th></tr>
</thead>
				<tbody>
	
<?php	
	
	$sql = "SELECT l.idEpreuve, l.nom, l.prenom, l.perf, l.points, l.ville, l.annee AS r_annee, (l.annee - l.naissance) AS age FROM ligne l " .
	" WHERE l.club = '" . $nom_club . "'" .
		" AND (l.annee - l.naissance) > 39 " .
		" AND l.sexe = 0 " .
		" AND perf IN (" .
				" SELECT MIN(perf)" .
				" FROM ligne l2" .
				" WHERE l2.club = '" . $nom_club . "'" .
				" AND (l2.annee - l2.naissance) > 39" .
				" AND l2.sexe = 0" .
				" GROUP BY l2.idEpreuve)";
	
	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

while($row = mysql_fetch_assoc($req)){

  echo "<tr><td>" . $row["idEpreuve"]. "</td><td>"
		. $row["nom"]. "</td><td>"
		. $row["prenom"]. "</td><td>"
		. $row["perf"]. "</td><td>"
		. $row["points"]. "</td><td>"
		. $row["ville"]. "</td><td>"
		. $row["r_annee"]. "</td><td>"
		. $row["age"]. "</td></tr>";
}



?>


</tbody>
</table>





		</div>
	</article>

	<script src="js/menu.js" type="text/javascript"></script>
	<script>document.getElementById("clubs").setAttribute("class", "active");</script>
</body>
</html>