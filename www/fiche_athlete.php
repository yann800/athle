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


$param_nom = '';
if (isset($_POST['nom']))
{
    $param_nom = $_POST['nom'];
}
else {
	echo "param [nom] manquant";
	exit;
}
$param_prenom = '';
if (isset($_POST['prenom']))
{
    $param_prenom = $_POST['prenom'];
}
else {
	echo "param [prenom] manquant";
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
		  ['Année', '400', '800', '1500', '3000'],



<?php
// $param_nom = '77';

// if (preg_match("#[a\-ZA\-Z]#", $param_nom)) {
    //echo "Caratere interdit";
//exit;
//} else {
//    echo "OK Bonne chaine";
//}

	include 'constantes.php';

	// Create connection http://php.net/manual/fr/function.mysql-connect.php
	
	$link  =  mysql_connect($servername, $username, $password)
	or die( "Impossible de se connecter : "  .  mysql_error ());
	
	mysql_select_db($dbname);
	
	
	$sql = "SELECT annee as annee, max(e_400) as e_400, max(e_800) as e_800, max(e_1500) as e_1500, max(e_3000) as e_3000
FROM
(
	SELECT annee, 
		CASE idEpreuve
			WHEN 400 THEN points
			WHEN 800 THEN 0
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_400,
		
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN points
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_800,
		
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN 0
			WHEN 1500  THEN points
			WHEN 3000  THEN 0
		END as e_1500,
	
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN 0
			WHEN 1500 THEN 0
			WHEN 3000  THEN points
		END as e_3000
	
	FROM ligne l
	WHERE l.nom = '". $param_nom ."' AND prenom = '". $param_prenom ."' AND l.idEpreuve IN (400, 800, 1500, 3000) AND annee > 2005

) tmp
GROUP by annee";


// $result = mysql_query($sql);

$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");


$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
	echo "['0',  0, 0, 0]";

} else {
// on fait une boucle qui va faire un tour pour chaque enregistrement
while($row = mysql_fetch_assoc($req)){

        echo "['" . $row["annee"]. "'," . $row["e_400"]. "," . $row["e_800"]. "," . $row["e_1500"]. "," .  $row["e_3000"]. "]";

		if ($row["e_3000"] > 0 and $row["annee"] == 2016){
			// derniere ligne donc rien
		}
		else {
			echo ",";
		}

}
}



?>

        ]);

        var options = {
          title: 'Meilleures performances à la table de cotations par année'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
   </script>
   
<?php  
if ( $nb == 0 ) {   
    echo '<div class="alert alert-danger danger"><b>Pas de performances trouvées pour l\'athlète [' . $param_nom . ' ' . $param_prenom . '], ou ses nom-prénom ne sont pas orthographiés comme dans la base (vérifiez dans le tableau des athlètes du club si vous le connaissez)</b></div>';
	echo '<script>document.getElementById("chart_div").setAttribute("style", "visibility:hidden");</script>';
}

?>
   
<div class="panel-group" id="accordion">
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne">Voir le SQL</a>
      </h4>

        </div>
        <div id="collapseOne" class="panel-collapse collapse">
            <div class="panel-body"><p>
Note : on n'aurait pu faire plus simple si MySql avait implémenté la fonction PIVOT/UNPIVOT (<a href="http://stackoverflow.com/questions/3392956/sql-how-to-transpose">inversion row/col</a>)
</p>
            
		</div>

<pre>
SELECT annee, max(e_800), max(e_1500), max(e_3000)
FROM
(
	SELECT annee, 
		CASE idEpreuve
			WHEN 400 THEN points
			WHEN 800 THEN 0
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_400,
		
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN points
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_800,
		
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN 0
			WHEN 1500  THEN points
			WHEN 3000  THEN 0
		END as e_1500,
	
		CASE idEpreuve
			WHEN 400 THEN 0
			WHEN 800 THEN 0
			WHEN 1500 THEN 0
			WHEN 3000  THEN points
		END as e_3000
	
	FROM ligne l
	WHERE l.nom = '[NOM]' AND prenom = '[PRENOM]' AND l.idEpreuve IN (400, 800, 1500, 3000) AND annee &gt; 2005

) tmp
GROUP by annee
</pre>


        </div>
    </div>
</div>


<table class="table table-striped table-bordered table-hover table-condensed" id="example">
	<thead>
		<tr>
			<th>Année</th>
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


$sql = "SELECT annee, idEpreuve, perf, points, ville, datePerf, club FROM ligne WHERE prenom = '". $param_prenom ."' AND nom ='". $param_nom ."' ORDER BY annee, datePerf";


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
	
		<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable();
		});

		$('.dropdown-toggle').dropdown();

	</script>

	
	<script>
		document.getElementById("athletes").setAttribute("class", "active");
	</script>
</body>
</html>