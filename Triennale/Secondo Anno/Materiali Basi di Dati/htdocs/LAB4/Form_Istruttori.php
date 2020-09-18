<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	
	<title>
	Istruttori
	</title>
	<body>
		<h1>Ricerca lezioni corso</h1>
		
		<body>
		
		<form method="get" action="Cerca_lezioni_istruttore.php">
			<p>Cognome:
				<select name="Cognome">
						<?php
						/*ini_set('display_errors', 'On');
						error_reporting(E_ALL | E_STRICT);*/
/*1:connessione*/			$con=@mysqli_connect('localhost','root','','palestra');
							if(mysqli_connect_errno())
								die('Connessione non riuscita '.mysqli_connect_error());
							mysqli_set_charset($con,"utf8");
							
/*2:query immediata*/		$result=mysqli_query($con,'SELECT DISTINCT Cognome FROM ISTRUTTORE ORDER BY Cognome');	//Cognome non è chiave primaria!
							if(!$result)
								die('Impossibile trovare istruttori. '.mysqli_error($con));
							
							if(mysqli_num_rows($result)>0){	
/*3:lettura risultati*/			while($istruttore=mysqli_fetch_row($result)){
									printf("<option value=%s>%s</option>",$istruttore[0],$istruttore[0]);
								}
							}
						mysqli_free_result($result);
						
						?>
				</select>
			</p>
			<p>Giorno:
				<select name="Giorno">
						<?php
						/*Lecito sfruttare la connessione aperta in precedenza? MOLTO lecito!*/
/*2:query immediata*/		$result=mysqli_query($con,'SELECT DISTINCT Giorno FROM PROGRAMMA ORDER BY Giorno');	//Cognome non è chiave primaria!
							if(!$result)
								die('Impossibile trovare corsi. '.mysqli_error($con));
							
							if(mysqli_num_rows($result)>0){	
/*3:lettura risultati*/			while($giorno=mysqli_fetch_row($result)){
									printf("<option value=%s>%s</option>",$giorno[0],$giorno[0]);
								}
							}
						mysqli_free_result($result);
						mysqli_close($con);
						?>
				</select>
			</p>
			<input type="submit" name="Cerca" value="Cerca"/>
			
		</form>

	</body>
</html>