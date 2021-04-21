<!doctype html>
<html lang="fr">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" />

<body>


<div class="card">
			<div class="card-header">Document</div>
			<div class="card-body">
			
				<div class="row">
					<div class="col-md-8" style="margin-bottom: 20px">
						<label for="titre" class="col-form-label">Titre</label>


<?php

include "../constantes.php";

$id=$_GET["id"];

// Create connection
// $conn = new mysqli($servername, $username, $password, $dbname);
// --------------
// OLD PHP FOR FREE
// Create connection http://php.net/manual/fr/function.mysql-connect.php
$link  =  mysql_connect($servername, $username, $password) or die( "Impossible de se connecter : "  .  mysql_error ());
mysql_select_db($dbname);



$sql = "SELECT d.id AS id, d.titre AS titre, d.auteur AS auteur, d.type AS type, d.theme AS theme, d.bibli AS bibli, d.annee AS annee, d.commentaire AS commentaire FROM Document d WHERE id = " . $id;

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

$nb = mysql_num_rows($req);

if ( $nb == 0 ) {
    // rien Ã  faire
    echo "pas de ligne id : " . $id;
}
else {
    // on fait une boucle qui va faire un tour pour chaque enregistrement
    while($row = mysql_fetch_assoc($req)){
        
        
        echo $row["titre"];

?>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="row">
					<div class="col-md-8" style="margin-bottom: 20px">
						<label for="titre" class="col-form-label">Auteur</label>
<?php 
    echo $row["auteur"];
?>


						<input type="text" class="form-control" name="auteur" id="auteur" required="required"/>
					</div>
					<div class="col-md-2">
						<label for="annee" class="col-form-label">Année</label>
						<input type="number" class="form-control" name="annee" id="annee" min="1500" max="2030" required="required" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-4" style="margin-bottom: 20px">

						<div class="form-group">
							<label for="type" class="col-form-label">Type</label>
							<select class="form-control" id="type" name="type">
								<option value="roman">roman</option>
								<option value="article">article</option>
								<option value="essai">essai</option>
								<option value="correspondance">correspondance</option>
								<option value="bd">bande dessinée</option>
							</select>
						</div>

					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="theme" class="col-form-label">Thème</label>
							<select class="form-control" id="theme" name="theme">
								<option value="politique">politique</option>
								<option value="histoire">histoire</option>
								<option value="Ecologie">Ecologie</option>
								<option value="Economie">Economie</option>
								<option value="littérature">littérature</option>
								<option value="sport">sport</option>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label for="bibli" class="col-form-label">Bibliothèque</label>
							<select class="form-control" id="bibli" name="bibli">
								<option value="Merwane">Merwane</option>
								<option value="Danielle">Danielle</option>
								<option value="Yann">Yann</option>
								<option value="Juliette">Juliette</option>
								<option value="autre">Autre</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">

					<div class="col-md-10">
						<label for="commentaire" class="col-form-label">Commentaire</label>
						<textarea class="form-control" name="commentaire" id="commentaire" rows="6">
<?php 
    echo $row["commentaire"]
?>
						</textarea>
					</div>
				</div>

				

			</div>
			<div class="row">
				<div class="col-md-12" style="margin-bottom: 20px; text-align: center;">
					<button type="submit" class="btn btn-info" style="margin-right: 20px">Modifier</button>
					<a class="btn btn-secondary" href="/bibli">Retour</a>
				</div>
			</div>
		</div>    
<?php 
    
    }
}

echo $hint;
    
mysql_close($link);
?>