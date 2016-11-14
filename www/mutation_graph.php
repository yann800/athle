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

	<article class="content">

		<div class="col-sm-2"></div>


		<div class="col-sm-8">


			<nav id="menu"></nav>
			
			<br/><br/>
			
<div id="mynetwork"></div>

<br/>
<br/>

<pre id="eventSpan"></pre>
<pre id="clubsAthleteSelected"></pre>

	<table id="tableAthleteSelected" class="table table-striped table-bordered table-hover" >
	<thead>
		<tr><th>Club</th><th>Action</th></tr>
	</thead>
	<tbody>
	</tbody>
	</table>
<br/>





</div>
</article>

	<script src="js/menu.js" type="text/javascript"></script>
	
		<script type="text/javascript" class="init">
		$('.dropdown-toggle').dropdown();
		

	</script>
	<script>document.getElementById("clubs").setAttribute("class", "active");</script>

<script type="text/javascript">
    var nodeIds, shadowState, nodesArray, nodes, edgesArray, edges, network;

    function startNetwork() {
        // this list is kept to remove a random node.. we do not add node 1 here because it's used for changes
        shadowState = false;


        // create an array with nodes
        nodesArray = [

                                 
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
           document.getElementById('eventSpan').innerHTML = '<b>Noeud cliqué</b> id : ' + nodeId + ', nom : ' + nodes.get(nodeId).label;
           clickAthlete(nodeId);
       
       });

       }
       

       function clickAthlete(str) {

          var xmlhttp = new XMLHttpRequest();
          xmlhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
                   var reponseGetLicence = this.responseText;
                   document.getElementById("clubsAthleteSelected").innerHTML = reponseGetLicence;

	               	var jsonData = JSON.parse(reponseGetLicence);
	            	for (var i = 0; i < jsonData.clubs.length; i++) {
	            	    var obj = jsonData.clubs[i];
	
	            	    $("#tableAthleteSelected tbody").append('<tr><td>' + obj.c + '</td><td><button onclick="javascript:alert(' + obj.id + ')">Ajouter le noeud dans le diagramme</td></tr>');
	
	            		// $("<option></option>").attr("value", obj.c);
	            	}

                   
               }
           };
           xmlhttp.open("GET", "getLicenceByPersonne.php?q=" + str, true);
           xmlhttp.send();
       }    
       
       
       
       //http://visjs.org/examples/network/data/dynamicData.html
       function addNode(idClub, nomClub) {
           nodes.add({id:idClub, label:nomClub, color: '#cc0099'});
           
           addEdge(idClub, 47);
           addEdge(idClub, 50);
           addEdge(idClub, 52);
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

       startNetwork();
 </script>
   


</body>
</html>