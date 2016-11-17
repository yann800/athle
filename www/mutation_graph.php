<!DOCTYPE html>
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="js/vis.js"></script>
    <link href="css/vis.css" rel="stylesheet" type="text/css" />



<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<!-- <link href="css/bootstrap-theme.css" media="screen" rel="stylesheet" type="text/css" /> -->
<link href="css/dataTables.bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

    <style type="text/css">
        #mynetwork {
            width: 800px;
            height: 500px;
            border: 1px solid lightgray;
        }
    </style>
</head>
<body>


<?php

$param_club1 = '';
if (isset($_POST['searchClub1']))
{
    $param_club1 = $_POST['searchClub1'];
}
else {
	echo "param [searchClub1] manquant";
	exit;
}
$param_club2 = '';
if (isset($_POST['searchClub2']))
{
    $param_club2 = $_POST['searchClub2'];
}
else {
	echo "param [searchClub2] manquant";
	exit;
}
?>

	<article class="content">

<div class="container">

	<div id="row">

		<div class="col-sm-1"></div>


		<div class="col-sm-11">


			<nav id="menu"></nav>
			
			<br/><br/>
			
			<div id="mynetwork"></div>

			<br/><br/>

		</div>
	</div>
	
	<div id="row">


	<div class="col-sm-6 col-sm-offset-1">

		<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div id="infoSpan" class="panel-heading">Information sur le club ou athlète sélectionné</div>
			<!--   <div class="panel-body"> -->
			<!--     <p>...</p> -->
			<!--   </div> -->
		  
			<table id="tableAthleteSelected" class="table table-striped table-bordered table-hover" >
			<thead>
				<tr><th>Club</th><th>Athlètes<th>Action</th></tr>
			</thead>
			<tbody>
			</tbody>
			</table>
		</div>
	</div>
	<div class="col-sm-5">
	
			<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Actions</div>
	<div id="actionSpan">

<?php

    	    
   include 'constantes.php';

    		
   // Create connection http://php.net/manual/fr/function.mysql-connect.php
   $link  =  mysql_connect($servername, $username, $password)
   or die( "Impossible de se connecter : "  .  mysql_error ());
   
   mysql_select_db($dbname);
   
   // récup des idClub
   // ca montreuil et joinville
   $idClub1 = 0;
   $idClub2 = 0;

   $sql = "SELECT id FROM club WHERE nom = '". $param_club1 ."';";
   
   $req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

   // on fait une boucle qui va faire un tour pour chaque enregistrement
   while($row = mysql_fetch_assoc($req)){
   	$idClub1 = $row["id"];
   }
   
   $sql = "SELECT id FROM club WHERE nom = '". $param_club2 ."';";
   
   $req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");
   
   // on fait une boucle qui va faire un tour pour chaque enregistrement
   while($row = mysql_fetch_assoc($req)){
   	$idClub2 = $row["id"];
   }
echo '<input class="btn btn-default" type="button" onclick="addAthletes(' . $idClub1 . ')" value="Ajouter tous les athlètes de ' . $param_club1 . '" />';
echo '<input class="btn btn-default" type="button" onclick="addAthletes(' . $idClub2 . ')" value="Ajouter tous les athlètes de ' . $param_club2 . '" />';

?>
<!-- 
<input class="btn btn-default" type="button" onclick="removeAthleteNonClub(1)" value="Supprimer les athlètes non-ACP Joinville" />
 -->

	</div>
		</div>
	</div>	
</div>



<div class="row">

<div class="panel-group col-sm-9 col-md-offset-2" id="accordion1" role="tablist"
 aria-multiselectable="true" style="padding-top: 40px">
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="headingOne">
			<h4 class="panel-title"><a data-toggle="collapse" href="#collapse1">Information sur le SQL exécuté</a></h4>
			
       
		</div>
		<div id="collapse1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel-body">
			
<br/>
	<div class="alert alert-info col-sm-12" role="info">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		
		La liste des athlètes des clubs respectifs est obenu avec l'utisation d'UNION.
		<br/><br/>
		Pour la liste des athlètes ayant appartenu aux deux clubs - intêret de permettre de faire apparaître
		les mutations dans le graphes - c'est l'INNER JOIN qui correspond au besoin (cf. 		
<a href="http://sql.sh/cours/jointures">http://sql.sh/cours/jointures</a>).
	</div>

<pre>
<code>-- liste des athlètes de deux clubs
SELECT DISTINCT l.nom
FROM ligne l
WHERE l.club = 'Ac paris-joinville'
UNION
SELECT DISTINCT l.nom
FROM ligne l
WHERE l.club = 'Us creteil';
&gt;&gt;&gt;153 lignes en résultat

-- liste des athlètes ayant appartenu aux deux clubs
SELECT DISTINCT l1.nom
FROM ligne l1
INNER JOIN ligne l2 ON l1.nom = l2.nom AND l1.club = 'Ac paris-joinville' AND l2.club = 'Us creteil';

-- en MySQL pas de INTERSECT http://sql.sh/cours/intersect donc solution alternative
-- (ici pas possible d'utiliser deux JOIN à la fois)
SELECT DISTINCT p.id, p.nom
FROM personne p, licence l1
WHERE l1.idPersonne = p.id
AND l1.idClub =91
AND p.id
IN (
  SELECT DISTINCT p2.id FROM personne p2
  INNER JOIN licence l2 ON l2.idPersonne = p2.id
  WHERE l2.idClub =1583

 </code>
</pre>

<pre>
<code>Avant le codage en full JS, génération automatique par LibreOffice Calc sur export CSV de phpMyAdmin e: écrire les lignes 1 et 2
 avec les deux clubs, puis deux formules appliquées sur toutes les lignes :
= "{id:" &amp; LIGNE() &amp; ",label:'" &amp; C3 &amp; "'},"
= "{from:" &amp; LIGNE() &amp; ",to:'" &amp; A3 &amp; "'},"
</code></pre>



		</div>
</div></div>
	</div> <!-- end panel group -->

</div> <!-- end row -->

</div> <!--  end container -->

</article>

	<script src="js/menu.js" type="text/javascript"></script>
	
		<script type="text/javascript" class="init">
		$('.dropdown-toggle').dropdown();
		

	</script>
	<script>document.getElementById("clubs").setAttribute("class", "active");</script>

<script type="text/javascript">
    var nodeIds, shadowState, nodesArray, nodes, edgesArray, edges, network;

    var listeIdClub = [];
 
<?php
    echo 'listeIdClub.push(' . $idClub1 . ');';
    echo 'listeIdClub.push(' . $idClub2 . ');';
?>      
    

    function startNetwork() {
        // this list is kept to remove a random node.. we do not add node 1 here because it's used for changes
        shadowState = false;


        // create an array with nodes
        nodesArray = [


<?php 
	
	
	
	echo "{id: ". $idClub1 .", label: '" . $param_club1. "', color: '#cc0099'},";
	echo "{id: ". $idClub2 .", label: '" . $param_club2. "', color: '#cc0099'}";
	
	// liste des personnes du club c1 et du club c2
	$sql = "SELECT DISTINCT p.id, p.nom FROM personne p " .
	" JOIN licence l1 ON l1.idPersonne = p.id AND l1.idClub = ". $idClub1 .  
	" JOIN licence l2 ON l2.idPersonne = p.id AND l2.idClub = ". $idClub2 . ";";
	
// $result = mysql_query($sql);

$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");


$nb = mysql_num_rows($req);

// on fait une boucle qui va faire un tour pour chaque enregistrement
while($row = mysql_fetch_assoc($req)){

	echo ",{id: ". $row["id"] .", label: '" . $row["nom"]. "'}";
}
?>                                

];
        nodes = new vis.DataSet(nodesArray);

        // create an array with edges
        edgesArray = [


<?php                                  


$sql = "SELECT idPersonne, idClub FROM licence 
		WHERE idClub IN (". $idClub1 .",". $idClub2 .") GROUP BY idPersonne, idClub;";

$req = mysql_query($sql) or die("['Erreur SQL !','" .$sql. "','" . mysql_error() . "]");

// on fait une boucle qui va faire un tour pour chaque enregistrement
$first = 0;
while($row = mysql_fetch_assoc($req)){

	if ($first === 0){
		$first = 1;
		echo '{from:' . $row["idPersonne"] . ',to:' . $row["idClub"] . '}';
	}
	else {
		echo ',{from:' . $row["idPersonne"] . ',to:' . $row["idClub"] . '}'; 
	}
}
?>

];
        edges = new vis.DataSet(edgesArray);

       
       
       
       // create a network
       var container = document.getElementById('mynetwork');

       // provide the data in the vis format
       var data = {
           nodes: nodes,
           edges: edges    };
       var options = {interaction:{hover:true}};

       // initialize your network!
       network = new vis.Network(container, data, options);
       
       network.on("click", function (params) {
           // params.event = "[original event]";
           var nodeId = parseInt(params.nodes);
           document.getElementById('infoSpan').innerHTML = '<b>Informations de ' + nodes.get(nodeId).label + ' ('+ nodeId + ')' + '</b>';

           // $.inArray(91, [91,1583]) return 0. Si non trouvé, -1
           if($.inArray(nodeId, listeIdClub) > -1){
           	clickClub(nodeId);
           }
           else {
           	clickAthlete(nodeId);
           }
       
       });

       }
       
    //http://visjs.org/examples/network/data/dynamicData.html
    function addClub(idClub1, idClub2, nomClub) {
            
        $( "#actionSpan" ).append('<input class="btn btn-default" type="button" onclick="addAthletes(' + idClub2 + ')" value="Ajouter tous les athlètes de ' + nomClub + '" />');

        // 1. ajout du nouveau club
        nodes.add({id:idClub2, label:nomClub, color: '#cc0099'});
        listeIdClub.push(idClub2);
        
        // 2. ajout des athlètes des deux clubs avec leur edge
       	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {

            	var reponseGetAthletes = this.responseText;
                
	            var jsonData = JSON.parse(reponseGetAthletes);
	            for (var i = 0; i < jsonData.athletes.length; i++) {
	            	var a = jsonData.athletes[i];

	            	if (nodes.get(a.id) == null){
	            		nodes.add({id:a.id, label:a.a});
	            	}
            		addEdge(a.id, idClub2);
	            }
	            
            }
        };
        xmlhttp.open("GET", "getAthletesFromDeuxClub.php?c1=" + idClub1 + "&c2=" + idClub2, true);
        xmlhttp.send();	        
        
        
	

    }

    function addAthletes(idClub) {

    	var idDejaPresent = -1; // vérification todo
    	if (idClub === idDejaPresent) { 
            return '';
        }
        
       	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {

            	var reponseGetAthletes = this.responseText;
                
	            var jsonData = JSON.parse(reponseGetAthletes);
	            for (var i = 0; i < jsonData.athletes.length; i++) {
	            	var a = jsonData.athletes[i];
	            	if (nodes.get(a.id) == null){
	            		nodes.add({id:a.id, label:a.a});
	            		addEdge(a.id, idClub);
	            	}
	            }

            }
        };
        xmlhttp.open("GET", "getAthletesFromClub.php?q=" + idClub, true);
        xmlhttp.send();
        
    	
    }
    
    
    function removeAthleteNonClub(idClub) {
    	
    	alert("fonction pas finie, des suppressions sont oubliées pour l''instant");
    	
        try {
        	
        	var listeIdAthleteToRemove = [];
        	for (var i = 92; i <= 170; i++) {
        		listeIdAthleteToRemove.push(i);
        	}
			// https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Objets_globaux/Array/forEach
        	listeIdAthleteToRemove.forEach(function (idToRemove){
        		nodes.remove({id: idToRemove});
        	});
        	
        }
        catch (err) {
            alert(err);
        }
    }


    function addEdge(idClub, idPersonne) {
        try {
            edges.add({
                from: idPersonne,
                to: idClub
            });
        }
        catch (err) {
            alert(err);
        }
    }
    
    
    function changeOptions() {
        shadowState = !shadowState;
        network.setOptions({nodes:{shadow:shadowState},edges:{shadow:shadowState}});
    }

    function resetAllNodes() {
        nodes.clear();
        edges.clear();
        nodes.add(nodesArray);
        edges.add(edgesArray);
    }

    function resetAllNodesStabilize() {
        resetAllNodes();
        network.stabilize();
    }

    function setTheData() {
        nodes = new vis.DataSet(nodesArray);
        edges = new vis.DataSet(edgesArray);
        network.setData({nodes:nodes, edges:edges})
    }

    function resetAll() {
        if (network !== null) {
            network.destroy();
            network = null;
        }
        startNetwork();
    }

    function clickClub(idClub1) {
    	
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 var reponseGetClubs = this.responseText;
                 
                 $("#tableAthleteSelected tbody").children().remove();
                 
	             var jsonData = JSON.parse(reponseGetClubs);
	             for (var i = 0; i < jsonData.clubs.length; i++) {
	            	var obj = jsonData.clubs[i];
	
	            	$("#tableAthleteSelected tbody").append('<tr><td>' + obj.c + '</td><td>' + obj.a 
	+ '</td><td><button class="btn btn-primary btn-xs" onclick="addClub(' + idClub1 + ',' + obj.c_id + ',\'' + obj.c
	+ '\')"><span class="glyphicon glyphicon-plus"/></td></tr>');
	             }

                 
             }
         };
         xmlhttp.open("GET", "getClubsOfClub.php?q=" + idClub1, true);
         xmlhttp.send();
    }
    
    function clickAthlete(idClub1) {

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 var reponseGetLicence = this.responseText;
                 // document.getElementById("clubsAthleteSelected").innerHTML = reponseGetLicence;

                 
                 $("#tableAthleteSelected tbody").children().remove();
                 
	             var jsonData = JSON.parse(reponseGetLicence);
	             for (var i = 0; i < jsonData.clubs.length; i++) {
	            	var obj = jsonData.clubs[i];
	
	            	$("#tableAthleteSelected tbody").append('<tr><td>' + obj.c
	+ '</td><td>' + obj.a + '</td><td><button class="btn btn-primary btn-xs" onclick="addClub(' + idClub1 + ',' +  obj.c_id + ',\'' + obj.c
	 + '\')"><span class="glyphicon glyphicon-plus"/></td></tr>');
	             }

                 
             }
         };
         xmlhttp.open("GET", "getLicenceByPersonne.php?q=" + idClub1, true);
         xmlhttp.send();
     } 
    startNetwork();
</script>



</body>
</html>