<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
			Pour voir le détail des performance, survolez les courbes du diagramme et reportez-vous au tableau.
			</div>
			
       <div id="chart_div" style="width: 900px; height: 500px;"></div>
   
<script type="text/javascript" src="charts/google/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
   
<script type="text/javascript">
   google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
		  ['Année', '800', '1500', '3000'],



<?php
// $param_nom = '77';

// if (preg_match("#[a\-ZA\-Z]#", $param_nom)) {
    //echo "Caratère interdit";
//exit;
//} else {
//    echo "OK Bonne chaine";
//}

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "athle";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 


	$sql = "INSERT INTO log (str, page, date) VALUES ('". $param_nom ." ". $param_prenom ."', 'fiche_athlete', NOW())";

	if ($conn->query($sql) === TRUE) {
		// echo "Record updated successfully";
	} else {
		echo "Error DB. ";
		exit;
	}
	
	
	$sql = "

SELECT annee as annee, max(e_800) as e_800, max(e_1500) as e_1500, max(e_3000) as e_3000
FROM
(
	SELECT annee, 
		CASE idEpreuve
			WHEN 800 THEN points
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_800,
		
		CASE idEpreuve
			WHEN 800 THEN 0
			WHEN 1500  THEN points
			WHEN 3000  THEN 0
		END as e_1500,
	
		CASE idEpreuve
			WHEN 800 THEN 0
			WHEN 1500 THEN 0
			WHEN 3000  THEN points
		END as e_3000
	
	FROM ligne l
	WHERE l.nom = '". $param_nom ."' AND prenom = '". $param_prenom ."' AND l.idEpreuve IN (800, 1500, 3000) AND annee > 2005

) tmp
GROUP by annee";


$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    
        echo "['" . $row["annee"]. "'," . $row["e_800"]. "," . $row["e_1500"]. "," .  $row["e_3000"]. "]";

		if ($row["e_3000"] > 0 and $row["annee"] == 2016){
			// derniere ligne donc rien
		}
		else {
			echo ",";
		}

    }
} else {
    echo "0 results";
}
	





?>

        ]);

        var options = {
          title: 'Meilleures perfs à la table de cotations par année'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
   </script>
<div class="panel-group" id="accordion">
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne">
          Voir le SQL
        </a>
      </h4>

        </div>
        <div id="collapseOne" class="panel-collapse collapse">
            <div class="panel-body"><p>
Note : on n'aurait pu faire plus simple si MySql avait implémenté la fonction PIVOT/UNPIVOT (<a href="http://stackoverflow.com/questions/3392956/sql-how-to-transpose">inversion row/col</a>)
</p>
            
</div>

<pre>
SELECT annee,max(e_800), max(e_1500), max(e_3000)
FROM
(
	SELECT annee, 
		CASE idEpreuve
			WHEN 800 THEN points
			WHEN 1500  THEN 0
			WHEN 3000  THEN 0  
		END as e_800,
		
		CASE idEpreuve
			WHEN 800 THEN 0
			WHEN 1500  THEN points
			WHEN 3000  THEN 0
		END as e_1500,
	
		CASE idEpreuve
			WHEN 800 THEN 0
			WHEN 1500 THEN 0
			WHEN 3000  THEN points
		END as e_3000
	
	FROM ligne l
	WHERE l.nom = '[NOM]' AND prenom = '[PRENOM]' AND l.idEpreuve IN (800, 1500, 3000) AND annee &gt; 2005

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
									</tr>
								</thead>
								<tbody>

<?php


$sql = "SELECT annee, idEpreuve, perf, points, ville, datePerf FROM ligne WHERE prenom = '". $param_prenom ."' AND nom ='". $param_nom ."' ORDER BY annee, datePerf";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    
        echo "<tr><td>" . $row["annee"]. "</td><td>" . $row["idEpreuve"]. "</td><td>" . $row["perf"]. "</td><td>" . $row["points"]. "</td><td>" . $row["ville"]. "</td><td>" .  $row["datePerf"]. "</td></tr>";

	}
}
$conn->close();
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