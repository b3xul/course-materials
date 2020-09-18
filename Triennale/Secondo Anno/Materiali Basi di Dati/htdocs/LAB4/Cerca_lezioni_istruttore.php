<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
	table, th, td {
		border: 1px solid black;
	}
	</style>
	</head>
	<title>
		Lezioni corso
	</title>
	
	<body> 
			<?php
						
/*0:controllo parametri*/
						/*ini_set('display_errors', 'On');
						error_reporting(E_ALL | E_STRICT);*/
							if(!isset($_REQUEST["Cognome"])||!isset($_REQUEST["Giorno"]))
								die ( "Errore: inserire tutti i dati richiesti" );
							
							$Cognome=$_REQUEST["Cognome"];
							$Giorno=$_REQUEST["Giorno"];
							
/*1:connessione*/			$con=mysqli_connect('localhost','root','','palestra');
							if(mysqli_connect_errno())
								die('Connessione non riuscita '.mysqli_connect_error());
							mysqli_set_charset($con,"utf8");
							
/*2:query immediata*/		$result1=mysqli_query($con,"SELECT Cognome FROM ISTRUTTORE WHERE Cognome='$Cognome'");
							$result2=mysqli_query($con,"SELECT Giorno FROM PROGRAMMA WHERE Giorno='$Giorno'");
							
							if(!$result1||!$result2)
								die ('Errore nella formulazione della query! '.mysqli_error($con));
							
							if(mysqli_num_rows($result1)<=0||mysqli_num_rows($result2)<=0)
								die ('Parametro non ammesso! '.mysqli_error($con));
							
						mysqli_free_result($result1);
						mysqli_free_result($result2);
						

							$query="SELECT Giorno,OraInizio,Durata,Sala,C.Nome,Tipo,Livello,P.CodFisc,I.Nome,Cognome
									FROM PROGRAMMA P,CORSI C,ISTRUTTORE I
									WHERE 	P.CodC=C.CodC AND
											P.CodFisc=I.CodFisc AND
											Cognome='$Cognome' AND
											Giorno='$Giorno'
									ORDER BY P.CodFisc,C.Nome";
/*2:query immediata*/		$result=mysqli_query($con,$query);

							if(!$result)
								die ('Errore nella formulazione della query! '.mysqli_error($con));
							
							if($num_rows=mysqli_num_rows($result)>0){
							
								echo "<h2>Lezioni in programma</h2>";
								echo "<p>";
								echo "<h3>Le $num_rows lezioni in programma per l'istruttore $Cognome nel giorno $Giorno sono le seguenti. </h3>";
								echo "</br>";
								
								echo "<table>";
								echo ("<tr> <th> Giorno </th>  <th>  Ora inizio  </th> <th>  Durata  </th>  <th> Sala </th> <th>  Nome corso </th> <th> Tipo corso </th>
										<th>  Livello  </th>  <th>  CodFisc Istruttore  </th><th>  Nome Istruttore  </th> <th>  Cognome Istruttore  </th></tr>");
								
								
	/*3:lettura risultati*/		while($info=mysqli_fetch_row($result)){
									echo ("<tr><td>$info[0]</td>");
									echo ("<td>$info[1]</td>");
									echo ("<td>$info[2]</td>");
									echo ("<td>$info[3]</td>");
									echo ("<td>$info[4]</td>");
									echo ("<td>$info[5]</td>");
									echo ("<td>$info[6]</td>");
									echo ("<td>$info[7]</td>");
									echo ("<td>$info[8]</td>");
									echo ("<td>$info[9]</td></tr>");
								}
								echo ("</table>");
							}
							else
								echo ("Nessuna lezione in programma per l’istruttore $Cognome il giorno della settimana $Giorno");
						
						mysqli_close($con);
			?>
			<form method="get" action="Form_Istruttori.php">
				<p><input type="submit" name="Home" value="Effettua un'altra ricerca"/></p>
			</form>
		</h1>
		
		
	</body>
</html>