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

echo '<title>Athl�tes et records de ' . $nom_club . '</title>';
?>

</head>

<body>

	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>
<?php
echo '<h1>Club ' . $nom_club . '</h1>';
echo '<a href="records_club.php?id=' . $param_id . '">Records du club [35 - 39 ans], [+40 ans] et toutes cat�gories</a>';
echo ' &#160;&#160; / &#160;&#160;<a href="fiche_club_annee.php?id=' . $param_id . '">Meilleures performances par ann�e</a>';
?>


<br/><br/>

		<table class="table table-striped table-bordered table-hover table-condensed" id="example">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Pr�nom</th>
						<th>Sexe</th>
						<th>Naissance</th>
						<th>Max points</th>
					</tr>
				</thead>
				<tbody>
	
<?php	
	
	$sql = "SELECT r_nom, r_prenom, r_sexe, r_naissance, r_points " .
"	FROM (" .
"	SELECT l_1.nom AS r_nom, l_1.prenom AS r_prenom, l_1.sexe AS r_sexe, l_1.naissance AS r_naissance, l_1.points AS r_points  " .
" FROM ligne l_1 " .
" WHERE l_1.id " .
" IN ( " .

" SELECT l.id " .
" FROM ligne l " .
" WHERE l.club = '" . $nom_club . "' " .
")  " .
" ORDER BY l_1.points DESC) tmp  GROUP BY r_nom ORDER BY r_points DESC;";
	

$req = mysql_query($sql) or die('Erreur SQL !<br>'.$sql.'<br>'.mysql_error());

while($row = mysql_fetch_assoc($req)){

        echo '<tr><td><a href="javascript:doPost(\'' . $row["r_nom"]. '\', \'' . $row["r_prenom"]. '\')">' . $row["r_nom"]. '</a></td><td>' . $row["r_prenom"]. '</td><td>' . $row["r_sexe"]. '</td><td>' .  $row["r_naissance"]. '</td><td>' .  $row["r_points"]. '</td></tr>';
        

}


mysql_close();

?>


</tbody>
</table>



  
<div class="panel-group" id="accordion">
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" href="#collapseOne">
          Voir le SQL
        </a>
      </h4>

        </div>
        <div id="collapseOne" class="panel-collapse collapse">
            

<pre><code>SELECT r_nom, r_prenom, r_sexe, r_naissance, r_points
FROM (

SELECT l_1.nom AS r_nom, l_1.prenom AS r_prenom,
	l_1.sexe AS r_sexe,	l_1.naissance AS r_naissance,
	l_1.points AS r_points
FROM ligne l_1
WHERE l_1.id
IN (

SELECT l.id
FROM ligne l
WHERE l.club = 'Ac paris-joinville'
)
ORDER BY l_1.points DESC
)tmp
GROUP BY r_nom
ORDER BY r_points DESC;</code>
</pre>
</div>


        </div>
    </div>


<br/>

   
<div class="panel-group" id="accordion2">
    <div class="panel panel-default" id="panel2">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseTwo" 
           href="#collapseTwo">
          Voir le JS JQuery [tri datable par une colonne � l'initialisation] et [lien provoquant un POST avec params]</a>
      </h4>

        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
		
		    <div class="panel-body">
		    <p><a href="https://datatables.net/examples/basic_init/table_sorting.html">https://datatables.net/examples/basic_init/table_sorting.html</a></p>
         	<p><a href="http://stackoverflow.com/questions/2367594/open-url-while-passing-post-data-with-jquery">http://stackoverflow.com/questions/2367594/open-url-while-passing-post-data-with-jquery</a></p>
         	</div>
         
         <pre><code>
  $(document).ready(function() {
     $('#example').DataTable(
		{
	        "order": [[ 4, "desc" ]]
	    }
	  );
  });
		
		
	
   &lt;-- ci-dessous un fonction pour faire un submit avec la m�thode POST et avec les param�tres du lien cliqu� --
	
   function doPost(p_nom, p_prenom){
	   
    $('body').append($('&lt;form/&gt;')
		  .attr({'action': 'http://base.athle.free.fr/fiche_athlete',
		         'method': 'post',
		         'id': 'replacer'})
		  .append($(' input/')
		    .attr({'type': 'hidden', 'name': 'nom', 'value': p_nom})
		  )
		  .append($('input/')
		    .attr({'type': 'hidden', 'name': 'prenom', 'value': p_prenom})
		  )
		).find('#replacer').submit();
   }
		
		</code></pre>
		
		
		
		</div>
	</div>
</div>

</div>
		
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