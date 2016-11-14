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

<body>

<?php


$param_nom1 = '';
if (isset($_POST['nom1']))
{
	$param_nom1 = $_POST['nom1'];
}
else {
	echo "param [nom1] manquant";
	exit;
}
$param_prenom1 = '';
if (isset($_POST['prenom1']))
{
	$param_prenom1 = $_POST['prenom1'];
}
else {
	echo "param [prenom1] manquant";
	exit;
}


$param_nom2 = '';
if (isset($_POST['nom2']))
{
	$param_nom2 = $_POST['nom2'];
}
else {
	echo "param [nom2] manquant";
	exit;
}
$param_prenom2 = '';
if (isset($_POST['prenom2']))
{
	$param_prenom = $_POST['prenom2'];
}
else {
	echo "param [prenom2] manquant";
	exit;
}

?>
	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>
			
			<br/>
			
			<div class="alert alert-info info">
			Pour voir le d&eacute;tail des performance, survolez les courbes du diagramme et reportez-vous au tableau.
			</div>
			
       <div id="chart_div" style="width: 900px; height: 500px;"></div>

<script type="text/javascript" src="charts/google/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
 
<script type="text/javascript">
   google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
                                                          
<?php
		  

     include 'constantes.php';
     
     
     

      echo "['Ann�e', '" . strtoupper($param_nom1) . "','" . strtoupper($param_nom2) . "'],";
     
     
     $link  =  mysql_connect($servername, $username, $password)
     or die( "Impossible de se connecter : "  .  mysql_error ());
     
     mysql_select_db($dbname);

$sql = "SELECT annee, max(" . $param_nom1 . ") as nom1, max(" . $param_nom2 . ") as nom2
FROM
(
	SELECT annee, 
		CASE nom
			WHEN '" . $param_nom1 . "' THEN points
			WHEN '" . $param_nom2 . "'  THEN 0  
		END as " . $param_nom1 . ",
		
		CASE nom
			WHEN '" . $param_nom1 . "' THEN 0
			WHEN '" . $param_nom2 . "'  THEN points
		END as " . $param_nom2 . "
	
	FROM ligne l
	WHERE l.nom IN ('" . $param_nom1 . "', '" . $param_nom2 . "') AND annee > 2005

) tmp
GROUP by annee";



$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");


$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	echo "['0',  0, 0, 0]";

} else {
	// on fait une boucle qui va faire un tour pour chaque enregistrement
	while($row = mysql_fetch_assoc($req)){
        echo "['" . $row["annee"]. "'," . $row["nom1"]. "," . $row["nom2"]. "],";

	}
}

?>


        ]);

        var options = {
          title: 'Comparatif � la table de cotation'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
   </script>
   

<table class="table table-striped table-bordered table-hover table-condensed" id="example">
	<thead>
		<tr>
			<th>Ann�e</th>
			<th>Epreuve</th>
			<th>Perf</th>
			<th>Points</th>
			<th>Ville</th>
			<th>Date</th>
			<th>Club</th>
		</tr>
	</thead>
	<tbody>

<?php 

$sql = "SELECT annee, idEpreuve, perf, points, ville, datePerf, club FROM ligne "
	. "WHERE nom IN ('" . $param_nom1 . "','" . $param_nom2 ."') ORDER BY annee, points";

	$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());
	
	// output data of each row
	while($row = mysql_fetch_assoc($req)){
	
	
		echo "<tr><td>" . $row["annee"]. "</td><td>" . $row["idEpreuve"]. "</td><td>" . $row["perf"]. "</td><td>" . $row["points"]. "</td><td>" . $row["ville"]. "</td><td>" .  $row["datePerf"]. "</td><td>" .  $row["club"]. "</td></tr>";
	
	}
	
	mysql_close();
	?>
	</tbody>
</table>
   
   		</div>
   		
   		</article>



	<script src="js/menu.js" type="text/javascript"></script>
	<script>
		document.getElementById("athletes").setAttribute("class", "active");
	</script>
</body>
</html>