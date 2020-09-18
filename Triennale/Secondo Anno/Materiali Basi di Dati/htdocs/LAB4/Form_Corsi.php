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
						/*N.B. Il GET/POST avrà come campo associato all'input type il nome dell'input type (in questo caso Select)->può essere anche un nome diverso*/
						/*(tipo "Codice_Corso") da quello che andrò a cercare nel DB ma in questo caso migliora leggibilità
						/*ini_set('display_errors', 'On');
						error_reporting(E_ALL | E_STRICT);*/
/*1:connessione*/			$con=mysqli_connect('localhost','root','','palestra');
							if(mysqli_connect_errno())
								die('Connessione non riuscita '.mysqli_connect_error());
							
/*2:query immediata*/		$result=mysqli_query($con,'SELECT CodC FROM CORSI ORDER BY CodC');
							if(!$result)
								die('Impossibile trovare corsi. '.mysqli_error($con));
							
							if(mysqli_num_rows($result)>0){	
/*3:lettura risultati*/			while($corso=mysqli_fetch_row($result)){
									printf("<option value=%s>%s</option>",$corso[0],$corso[0]);
									/*ALTERNATIVE: echo "<option value=$row[0]>$row[0]</option>";*/
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