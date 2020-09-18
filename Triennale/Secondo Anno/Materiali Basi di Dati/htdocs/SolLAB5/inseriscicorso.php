<html>
<head>
<title>Inserimento corso</title>
</head>

<body>



<?php

/* CONTROLLO E LETTURA PARAMETRI*/

if( !isset($_REQUEST["codc"]) or
    !isset($_REQUEST["nome"]) or
    !isset($_REQUEST["tipo"]) or
    !isset($_REQUEST["livello"]))
  die("Errore: inserire tutti i dati richiesti");

$codc = $_REQUEST["codc"];
$nome = $_REQUEST["nome"];
$tipo = $_REQUEST["tipo"];
$livello = $_REQUEST["livello"];

if ($codc=="")
	echo "Codice corso non valorizzato<br>";

if ($nome=="")
	echo "Nome corso non valorizzato<br>";

if ($tipo=="")
	echo "Tipo corso non valorizzato<br>";

if ($livello=="")
	echo "Livello corso non valorizzato<br>";


if ($codc=="" || $nome=="" || $tipo=="" || $livello=="")
	die();

if (!is_numeric($livello))
	die("Errore: Il livello deve essere un valore intero compreso tra 1 e 4");

if ($livello<1 || $livello>4)
	die("Errore: Il livello deve essere compreso tra 1 e 4");	



/* CONNESSIONE AL DB*/

$con = mysqli_connect('localhost','root','','palestra');

if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


/* ESECUZIONE INSERIMENTO*/

if(mysqli_query($con,"INSERT INTO CORSI(CodC,Nome,Tipo,Livello) VALUES('$codc', '$nome', '$tipo', '$livello');"))
{
  echo "Il corso $codc è stata inserito nel database.";
}
else   {
  echo "Non è stato possibile inserire i dati perchè si è verificato un errore: ". mysqli_error($con);
}

mysqli_close($con);
?>


</br>
	<form method="get" action="form1.php">
		<input type="submit" value="Effettua un altro inserimento">
	</form>



</body>
</html>
