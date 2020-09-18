<html>
	<title>
	Corsi
	</title>
	<body>
		<h1>Ricerca lezioni corso</h1>
		
		<body>
		
		<form method="get" action="Cerca_nome_corso.php">
			<p>Codice corso:
				<select name="CodC">
						<?php
						/*ini_set('display_errors', 'On');
						error_reporting(E_ALL | E_STRICT);*/
/*1:connessione*/			$con=mysqli_connect('localhost','root','','palestra');
							if(mysqli_connect_errno())
								die('Connessione non riuscita '.mysqli_connect_error());
							
/*2:query immediata*/		$result=mysqli_query($con,'SELECT CodC,Nome FROM CORSI ORDER BY CodC');
							if(!$result)
								die('Impossibile trovare corsi. '.mysqli_error($con));
							
							if(mysqli_num_rows($result)>0){	
	/*3:lettura risultati*/		while($corso=mysqli_fetch_row($result)){
									printf("<option value=%s>%s - %s</option>",$corso[0],$corso[0],$corso[1]);
								}
							}
						mysqli_free_result($result);
						mysqli_close($con);
						?>
				</select>
			<input type="submit" name="Cerca" value="Cerca"/>
			
		</form>

	</body>
</html>