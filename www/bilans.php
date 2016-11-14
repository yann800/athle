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


$param_annees = '';
if (isset($_POST['annees']))
{
    $param_annees = $_POST['annees'];
    
    // if (preg_match("/^[1-9][0-9\.]{0,15}$/", $param_annees)) {
    	// echo "Caratere interdit";
    	// exit;
    // }
}
else {
	echo "param [annees] manquant";
	exit;
}
$param_sexe = '';
if (isset($_POST['sexe']))
{
    $param_sexe = $_POST['sexe'];
}
else {
	echo "param [sexe] manquant";
	exit;
}


$param_epreuve = '';
if (isset($_POST['epreuve']))
{
	$param_epreuve = $_POST['epreuve'];
}
else {
	echo "param [epreuve] manquant";
	exit;
}


$param_age = '';
$str_categorie = '';

if (isset($_POST['categorie']))
{
	$param_categorie = $_POST['categorie'];
	if ($param_categorie == '19_moins'){
		$param_age = " AND (annee - naissance) < 20 ";
		$str_categorie = '19 ans et moins';
	}
	elseif ($param_categorie == '35_39'){
		$param_age = " AND (annee - naissance) > 34 AND (annee - naissance) < 40 ";
		$str_categorie = '35-39 ans';
	}
	elseif ($param_categorie == '35_plus'){
		$param_age = " AND (annee - naissance) > 34 ";
		$str_categorie = '35 ans et plus';
	}
	elseif ($param_categorie == '40_plus'){
		$param_age = " AND (annee - naissance) > 39 ";
		$str_categorie = '40 ans et plus';
	}
	elseif ($param_categorie == 'tout_age'){
		$param_age = "";
	}
}
else {
	echo "param [categorie] manquant";
	exit;
}

?>
	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>
			


<?php

	$str_sexe = '';
	if ($param_sexe === '1'){
		$str_sexe = 'f�minin';
	}
	else {
		$str_sexe = 'masculin';
	}



	echo '<h4>Bilan ' . $param_epreuve . ' ' . $str_sexe . ' ' . $str_categorie . ' sur [' . $param_annees . ' ]</h3>';
// $param_nom = '77';

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



?>
   
 

<script type="text/javascript">

function init_table(){
	
//	{ id : 0, nom: "CAPLAIN", prenom: "Laurent", age: 36, perf:"1.51.97", annee: 2006, ville: "Elancourt"},
var data = { data : [

<?php


$sql = "SELECT nom, prenom, (annee - naissance) AS age, perf, annee, datePerf, ville,  club FROM ligne " .

" WHERE sexe = ". $param_sexe ." AND idEpreuve = ". $param_epreuve ." AND annee IN (" . $param_annees . ")" . $param_age . " ORDER BY points DESC LIMIT 1000";


$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

    // output data of each row
	while($row = mysql_fetch_assoc($req)){
        echo '{ id : 0, nom: "' . $row["nom"]. '", prenom: "' . $row["prenom"]. '", age: ' . $row["age"]. ', perf: "' . $row["perf"]. '", annee: ' . $row["annee"] . ', club:"' .  $row["club"]. '"},';
	}

mysql_close();
?>
	
]}

var table = $('#bilan').DataTable({
	data : data.data,
  columns : [
    { data: 'id', title: 'Rang' },
    { data: 'nom', title: 'Nom' },
    { data: 'prenom', title: 'Performance' },    
    { data: 'age', title: 'Age' },    
    { data: 'perf', title: 'Performance' },    
    { data: 'annee', title: 'Ann�e' },    
    { data: 'club', title: 'Club' },    
  ]  
})  

var nom, noms = []
table.rows().every(function(rowIdx, tableLoop, rowLoop) {
	nom = this.data().nom;
  if (~noms.indexOf(nom)) {
    this.nodes().to$().attr('class', 'odd exclu') // todo  seulement ajouter la classe exclu au lieu de mettre odd
  } else {
	  noms.push(nom) 
  }
})

}


function all_perfs(){
	
if ($("#btnAllPerfs").text() == "Afficher toutes les performances d'un athl�te"){
	
	$("#btnAllPerfs").html("Ne montrer que la meilleure performance d'un athl�te");		
	
	$(".exclu").css({'display' : ''});
}
else{
	$("#btnAllPerfs").html("Afficher toutes les performances d'un athl�te");

	$(".exclu").css({'display' : 'none'});

}

}

// positionne le td rang de chaque ligne ('-' ou 'num�ro')
function init_rangs(){


var rows = $("#bilan").dataTable().fnGetNodes();
var nom = ''; // nom courant lors de l'it�ration
var noms = [];
var current_rang = 1;

   for(var i = 0; i < rows.length; i++)  {
   	
   	// $(rows[i]).find("td:eq(0)").val('zz');
   	
    // Get text of 2nd column
    nom = $(rows[i]).find("td:eq(1)").text();

    if (~noms.indexOf(nom)) {
    
    	// le nom est d�j� apparu, donc rang = '-'
    	$(rows[i]).find("td:eq(0)").text('-');
    	
    } else {
       	// le nom est nouveau, donc rang calcul�
       	$(rows[i]).find("td:eq(0)").text(current_rang);
    	current_rang = current_rang + 1;
       	
    	noms.push(nom);
    }      	
       
   } // end for
   
   console.log(noms);
}

</script>


<button id="btnAllPerfs" onclick="javascript:all_perfs()" class="btn btn-secondary">Ne montrer que la meilleure performance d'un athl�te</button>

<br/><br/>



<table class="table table-striped table-bordered table-hover table-condensed" id="bilan"></table>
			
							
		</div>
	</article>

	<script src="js/menu.js" type="text/javascript"></script>
	
	
	
	<script type="text/javascript" class="init">
		$('.dropdown-toggle').dropdown();
		
		init_table();
		init_rangs();
	</script>
	
	
	<!-- see http://api.jquery.com/val/ -->
	
	<script type="text/javascript">
	document.getElementById("statistiques").setAttribute("class", "dropdown active");

	</script>

	
</body>
</html>