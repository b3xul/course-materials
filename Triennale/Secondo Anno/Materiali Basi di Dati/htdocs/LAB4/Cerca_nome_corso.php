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
						/*ini_set('display_errors', 'On');
						error_reporting(E_ALL | E_STRICT);*/
						
/*0:controllo parametri*/	// $CodC=$_GET["CodC"]; Meglio REQUEST per passare più facilmente a un metodo POST in futuro
							if(!isset($_REQUEST["CodC"]))
								die ( "Errore: inserire tutti i dati richiesti" );
							$CodC=$_REQUEST["CodC"];
							
/*1:connessione*/			$con=mysqli_connect('localhost','root','','palestra');
							if(mysqli_connect_errno())
								die('Connessione non riuscita '.mysqli_connect_error());
							mysqli_set_charset($con,"utf8");
							
/*2:query immediata*/		$result=mysqli_query($con,"SELECT CodC FROM CORSI WHERE CodC='$CodC'");

							if(!$result)
								die ('Errore nella formulazione della query! '.mysqli_error($con));
							
							if(mysqli_num_rows($result)<=0)
								die ('Parametro non ammesso! '.mysqli_error($con));
							
							mysqli_free_result($result);
/*2:query preparata*/
/*2a:Dichiarazione statement*/
							$stmt=mysqli_prepare($con,	'SELECT Giorno,OraInizio,Durata,Sala,Nome,Cognome 
														FROM PROGRAMMA P,ISTRUTTORE I
														WHERE	P.CodFisc=I.CodFisc AND
																CodC=?');
							if(!$stmt)
								die('Impossibile trovare informazioni sul corso richiesto: '.mysqli_error($con));
							
/*2b:Impostazione parametri*/
							if(!mysqli_stmt_bind_param($stmt,"s",$CodC))
								die('Parametri errati: '.mysqli_error($con));

/*2c: Esecuzione della query*/
							if(!mysqli_stmt_execute($stmt))
								die('Impossibile eseguire query richiesta: '.mysqli_error($con));
							
							
							/*if(!mysqli_stmt_bind_result($stmt,$giorno,$oraInizio,$durata,$sala,$nome,$cognome))
								die('Parametri di ritorno errati: '.mysqli_error($con));*/
							
							/*Come stabilire righe risultato query preparata? Get_result/Store_result*/
							echo ("<h1>Lezioni in programma per il corso $CodC</h1>");
							echo ("<table>");
							echo ("<tr><th>Giorno</th><th>Ora Inizio</th><th>Durata</th><th>Sala</th><th>Nome istruttore</th><th>Cognome istruttore</th></tr>");
/*3:lettura risultati		while(mysqli_stmt_fetch($stmt)){
								echo ("<tr><td>$giorno</td>");
								echo ("<td>$oraInizio</td>");
								echo ("<td>$durata</td>");
								echo ("<td>$sala</td>");
								echo ("<td>$nome</td>");
								echo ("<td>$cognome</td></tr>");
							}
							echo ("</table>");
							*/
							$result=mysqli_stmt_get_result($stmt);
							while($info=mysqli_fetch_row($result)){
								echo ("<tr><td>$info[0]</td>");
								echo ("<td>$info[1]</td>");
								echo ("<td>$info[2]</td>");
								echo ("<td>$info[3]</td>");
								echo ("<td>$info[4]</td>");
								echo ("<td>$info[5]</td></tr>");
							}
							echo ("</table>");
								
						mysqli_stmt_close($stmt);
						mysqli_close($con);
			?>
			<form method="get" action="Form_Corsiv2.php">
				<p><input type="submit" name="Home" value="Effettua un'altra ricerca"/></p>
			</form>
		</h1>
		
		
	</body>
</html>