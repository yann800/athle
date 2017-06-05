<?php


$q = '';
if (isset($_GET['q']))
{
	$q = $_GET['q'];
}
else {
	echo "param [q] manquant";
	exit;
}

$hint = "";

// lookup all hints from array if $q is different from "" 
if ($q == "") {
	echo 'param q vide';
	exit;
}



echo '{"record" : [ {"epreuve" : "100", "nom" : "Bolt",        "perf" :  "9.58"}, {"epreuve" : "200", "nom" : "Bolt", "perf" : "19.19"}]}';
?>