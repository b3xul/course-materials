<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Programmazione</title>
</head>

<body>



<?php

/* CONTROLLO E LETTURA PARAMETRI*/

if( !isset($_REQUEST["codfisc"]) or
    !isset($_REQUEST["giorno"]) or
    !isset($_REQUEST["orainizio"]) or
    !isset($_REQUEST["durata"]) or
    !isset($_REQUEST["codc"]) or
    !isset($_REQUEST["sala"])) 
  die("Errore: inserire tutti i dati richiesti");

$codfisc = $_REQUEST["codfisc"];
$giorno = $_REQUEST["giorno"];
//echo "$giorno è il giorno scelto\n";
if($giorno!="Lunedì"and $giorno!="Martedì"and $giorno!="Mercoledì"and $giorno!="Giovedì"and $giorno!="Venerdì")
   die("Errore: Il giorno specificato non è valido");
$orainizio = $_REQUEST["orainizio"];
$durata = $_REQUEST["durata"];

if ($durata>60)
  die("Errore: Le lezioni non possono durare pi񠤩 60 minuti");

$codc = $_REQUEST["codc"];

$sala = $_REQUEST["sala"];


/* CONNESSIONE AL DB*/

$con = mysqli_connect('localhost','root','','palestra');

if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
mysqli_set_charset($con,"utf8");

/* ESECUZIONE QUERY PER LA VERIFICA DELLA CONDIZIONE DA VERIFICARE E POI INSERIMENTO LEZIONE.
  Fanno parte di una singola transazione  */
// Imposto a FALSE l'autocommit e inizio la transazione
mysqli_query($con,"SET autocommit=0;");
mysqli_query($con,"START TRANSACTION;");

/* ESECUZIONE QUERY */$query="SELECT *
					FROM PROGRAMMA
					WHERE 	CodC='$codc' AND
							Giorno='$giorno'";
			$result=mysqli_query($con,$query);
			
			if(!$result)
				die("Query non riuscita").mysqli_error($con);
			
			if($num_rows=mysqli_num_rows($result)>0){
				echo "Ci sono $num_rows altre lezioni dello stesso corso nel giorno selezionato</br>";
								
				echo "<p>";
							
				echo "<h3>Le lezioni in programma per il corso $codc nel giorno $giorno sono le seguenti. </h3>";
				echo "</br>";
								
				echo "<table>";
				echo ("	<tr> <th> CodFisc </th><th> Giorno </th>  <th>  Ora inizio  </th> <th>  Durata  </th>  <th> Sala </th> <th>  Codice Corso </th></tr>");
								
				while($info=mysqli_fetch_row($result)){
					echo ("<tr><td>$info[0]</td>");
					echo ("<td>$info[1]</td>");
					echo ("<td>$info[2]</td>");
					echo ("<td>$info[3]</td>");
					echo ("<td>$info[4]</td>");
					echo ("<td>$info[5]</td></tr>");
				}
				echo ("</table>");
				
				mysqli_query($con,"ROLLBACK");
				die ("Impossibile inserire nuova lezione");
			}
/*$sql  = "SELECT count(*)
FROM programma 
where CodC='$codc' and Giorno='$giorno'";

$result = mysqli_query($con,$sql);

if( !$result )
  die('Query error: ' . mysqli_error($con));


// Test sul numero di lezioni del corso nel giorno indicato
$row = mysqli_fetch_row($result);
if ($row[0]>1) {
    echo "Non è consentito inserire due lezioni per lo stesso corso nello stesso giorno";
    mysqli_query($con,"ROLLBACK;");
}*/
else
{  // Inserimento nuova lezione
  if(mysqli_query($con,"INSERT INTO Programma (CodFisc,Giorno,OraInizio,Durata,CodC,Sala) 
                      VALUES ('$codfisc','$giorno','$orainizio','$durata','$codc','$sala');"))
  {
    mysqli_query($con,"COMMIT;");
    echo "La lezione è stata inserita nel programma dei corsi.";
  }
  else   
  {
    echo "Non è stato possibile inserire i dati perchè si è verificato un errore: ". mysqli_error($con);
    mysqli_query($con,"ROLLBACK;");
  }
}

mysqli_close($con);

?>

</br>
	<form method="get" action="form2.php">
		<input type="submit" value="Effettua un altro inserimento">
	</form>


</body>
</html>
