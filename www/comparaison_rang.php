<script type="text/javascript" src="jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
       <div id="chart_div" style="width: 900px; height: 500px;"></div>
   
   
<script type="text/javascript">
   google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'ABDELKADER', 'ANDRIEU', 'LACHEREST', 'DIANI'],
		  
		  
		  

<?php
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

$sql = "
SELECT annee,max(yann) as yann, max(seb) as seb
FROM
(
	SELECT annee, 
		CASE nom
			WHEN 'ABDELKADER' THEN rang
			WHEN 'LACHEREST'  THEN 0  
		END as yann,
		
		CASE nom
			WHEN 'ABDELKADER' THEN 0
			WHEN 'LACHEREST'  THEN rang
		END as seb
	
	FROM ligne l
	WHERE l.nom IN ('ABDELKADER', 'LACHEREST') AND annee > 2009

) tmp
GROUP by annee";

$sql = "SELECT rang, nom, prenom, sexe, naissance, club, ligue, perf, ville, datePerf, idEpreuve FROM ligne WHERE nom like '" . $_GET['nom'] . "%' LIMIT 2000";


$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "['" . $row["annee"]. "," . $row["yann"]. "," . $row["seb"]. "],";
    }
} else {
    echo "0 results";
}
$conn->close();
?>




        ]);

        var options = {
          title: 'Comparatif rangs'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
   </script>