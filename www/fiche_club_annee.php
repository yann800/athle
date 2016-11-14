<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<!-- <link href="css/bootstrap-theme.css" media="screen" rel="stylesheet" type="text/css" /> -->
<link href="css/dataTables.bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />


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

echo '<title>Meilleures performances de ' . $nom_club . '</title>';
?>

</head>

<body>

	<article class="content">

		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<nav id="menu"></nav>
		</div>


<div class="row"></div>


		<div class="col-sm-1"></div>
		
		<div class="col-sm-11">
		
<?php
echo '<h1>Club ' . $nom_club . '</h1>';
echo '<a href="records_club.php?id=' . $param_id . '">Records du club [35 - 39 ans], [+40 ans] et toutes cat�gories</a>';
?>

</div>


<!-- Requete unique dont le resultat sera split� par annee puis epreuve -->
	
<?php	
	
	$sql = "SELECT nom, prenom, perf, annee, idEpreuve " .
  " FROM ligne " .
  " WHERE club = '" . $nom_club . "'";

	$result = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

// First we make the query and store the rows in an array
$data_array = array();
while ($data = mysql_fetch_assoc($result)) {
	$data_array[] = $data;
}
mysql_close();

for ($annee = 2006; $annee < 2017; $annee++) {
	

?>



<div class="row"></div>

		<div class="col-sm-1"></div>

		<div class="col-sm-11">
<?php 
echo '<h4>' . $annee . '</h4>';
?>
		</div>
		
		<div class="col-sm-1"></div>


<!-- tableau 800 -->
<div class="col-sm-3">


		<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Pr�nom</th>
						<th>Performance</th>
					</tr>
				</thead>
				<tbody>

<?php 
$epreuve = 800;
// while($row = mysql_fetch_assoc($req)){
foreach ($data_array as $row) {
	if ($row["annee"] == $annee and $row["idEpreuve"] == $epreuve){
		//  echo '<tr><td><a href="javascript:doPost(\'' . $row["nom"]. '\', \'' . $row["prenom"]. '\')">' . $row["nom"]. '</a></td><td>' . $row["prenom"]. '</td><td>' . $row["sexe"]. '</td><td>' .  $row["naissance"]. '</td><td>' .  $row["perf"]. '</td><td>'  .  $row["points"]. '</td><td>' .  $row["annee"]. '</td></tr>';
		echo '<tr><td><a href="javascript:doPost(\'' . $row["nom"]. '\', \'' . $row["prenom"]. '\')">' . $row["nom"]. '</a></td><td>' . $row["prenom"]. '</td><td>' .  $row["perf"]. '</td></tr>';
	}
}
?>
</tbody>
</table>
</div>


<!-- tableau 1500 -->
<div class="col-sm-3">
<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Pr�nom</th>
				<th>Performance</th>
			</tr>
		</thead>
		<tbody>

<?php 
$epreuve = 1500;
// while($row = mysql_fetch_assoc($req)){
foreach ($data_array as $row) {
	if ($row["annee"] == $annee and $row["idEpreuve"] == $epreuve){
		echo '<tr><td><a href="javascript:doPost(\'' . $row["nom"]. '\', \'' . $row["prenom"]. '\')">' . $row["nom"]. '</a></td><td>' . $row["prenom"]. '</td><td>' .  $row["perf"]. '</td></tr>';
	}
}

?>

</tbody>
</table>
</div>



<!-- tableau 3000 -->
<div class="col-sm-3">
<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Pr�nom</th>
				<th>Performance</th>
			</tr>
		</thead>
		<tbody>
<?php 
$epreuve = 3000;
// while($row = mysql_fetch_assoc($req)){
foreach ($data_array as $row) {
	if ($row["annee"] == $annee and $row["idEpreuve"] == $epreuve){
		echo '<tr><td><a href="javascript:doPost(\'' . $row["nom"]. '\', \'' . $row["prenom"]. '\')">' . $row["nom"]. '</a></td><td>' . $row["prenom"]. '</td><td>' .  $row["perf"]. '</td></tr>';
	}
}

?>
</tbody>
</table>
</div>


<?php 
} // end for it�ration annee
?>


<div class="col-sm-1"></div> <!-- 12�me et derni�re colonne de la ligne bootstrap -->
		
		
	</article>

	<script src="js/menu.js" type="text/javascript"></script>
	
		<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable(
					{
				        "order": [[ 4, "desc" ]]
				    }
			);
		});

		$('.dropdown-toggle').dropdown();

	</script>
	
		
	<script>
		document.getElementById("clubs").setAttribute("class", "active");
	</script>
	
	<script type="text/javascript"> 
   function doPost(p_nom, p_prenom){
	   
	    $('body').append($('<form/>')
			  .attr({'action': 'http://base.athle.free.fr/fiche_athlete', 'method': 'post', 'id': 'replacer'})
			  .append($('<input/>')
			    .attr({'type': 'hidden', 'name': 'nom', 'value': p_nom})
			  )
			  .append($('<input/>')
			    .attr({'type': 'hidden', 'name': 'prenom', 'value': p_prenom})
			  )
			).find('#replacer').submit();
   }

</script>
</body>
</html>