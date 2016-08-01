<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/bootstrap.min.css" media="screen, projection"
	rel="stylesheet" type="text/css" />
<!-- <link href="css/bootstrap-theme.css" media="screen, projection" rel="stylesheet" type="text/css" /> -->
<link href="css/dataTables.bootstrap.min.css" media="screen, projection"
	rel="stylesheet" type="text/css" />


<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</head>


<body>
		  

<?php

echo 'Bonjour ' . $_GET["nom"] . '!';

$param_nom = $_GET['nom'];
$param_prenom = $_GET['prenom'];



if (preg_match("[A-Z]", $param_nom)) {
    echo "Caratère interdit";
	
} else {
    echo "OK Bonne chaine";
}

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
		echo "Record updated successfully";
	} else {
		echo "Error updating record: " . $conn->error;
	}

$conn->close();



?>

</body>
</html>