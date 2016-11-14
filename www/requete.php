<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link href="css/bootstrap.min.css" media="screen, projection" rel="stylesheet" type="text/css" />
<!-- <link href="css/bootstrap-theme.css" media="screen, projection" rel="stylesheet" type="text/css" /> -->
<link href="css/dataTables.bootstrap.min.css" media="screen, projection" rel="stylesheet" type="text/css" />
<link href="css/site-examples.css" media="screen, projection" rel="stylesheet" type="text/css" />


<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</head>



<body>

	<div class="content">

		<div class="col-sm-2"></div>

		<div class="col-sm-8">

			<ul class="nav nav-pills">

				<li role="presentation" class="active"><a href="#">Accueil</a></li>

				<li role="presentation" ><a href="#">Requêtes</a></li>


				<li role="presentation" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
					aria-expanded="false"> Bilans <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">

						<li><a href="#">800</a></li>
						<li><a href="#">1500</a></li>
						<li><a href="#">10 km</a></li>
					</ul></li>
			</ul>




			<br />
			<br />
			<table id="example" class="table table-striped table-bordered">

			<thead>
				<tr>
					<th>rang</th>
					<th>nom</th>
					<th>prenom</th>
					<th>sexe</th>
					<th>naissance</th>					
					<th>club</th>
					<th>ligue</th>
					<th>epreuve</th>
					<th>perf</th>
					<th>ville</th>
					<th>datePerf</th>
					<th>idEpreuve</th>
					
				</tr>
			</thead>
			<tbody>
<?php
include 'constantes.php';

$password = "";
$dbname = "athle";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT rang, nom, prenom, sexe, naissance, club, ligue, perf, ville, datePerf, idEpreuve FROM ligne WHERE club like 'AC Pa%';


$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        // echo "<tr><td>" . $row["rang"]. "</td><td>" . $row["nom"]. "</td><td>" . $row["prenom"]. "</td><td>" . $row["perf"]. "</td><td>" . $row["idEpreuve"]. "</td><td>" . $row["datePerf"]. "</td><td>" . $row["naissance"]. "</td></tr>";
        // echo "<tr><td>" . $row["nom"]. "</td><td>" . $row["perf"]. "</td><td>" . $row["idEpreuve"]. "</td></tr>";        
    }
} else {
    echo "0 results";
}
$conn->close();
?>

			</tbody>
		</table>
		</div>

	</div>

	<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable();
		});

		$('.dropdown-toggle').dropdown();

	</script>

</body>
</html>


	


