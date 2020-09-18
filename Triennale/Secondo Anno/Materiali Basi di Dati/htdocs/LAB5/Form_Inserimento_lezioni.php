<html>
	<title>
	Programmazione
	</title>
	
	<body>
		<h1>Inserimento nuova programmazione/lezione</h1>
		
		<body>
		<p>Programmazione della nuova lezione settimanale</p>
		
		<form method="GET" action="inserisciProgrammazione.php">
			<table>
			
			<tr> <td>Istruttore:</td> <td> <select name="codfisc">
					<?php
					ini_set('display_errors', 'On');
					error_reporting(E_ALL | E_STRICT);
					
					$con=mysqli_connect('localhost','root','','palestra');
					if(mysqli_connect_errno())
						die('Connessione non riuscita '.mysqli_connect_error());

					$result=mysqli_query($con,'SELECT Cognome,Nome,CodFisc FROM ISTRUTTORE');
					if(!$result)
						die('Impossibile trovare istruttore. '.mysqli_error($con));
						
					if(mysqli_num_rows($result)>0){
						while($corso=mysqli_fetch_row($result)){
							printf("<option value=%s>%s %s (%s)</option>",$corso[2],$corso[0],$corso[1],$corso[2]);
						}
					}
					
					mysqli_free_result($result);
					?>
			</select> </td> </tr>
			
			<tr> <td>Giorno:</td> <td> <input type="text" name="giorno"/> </td> </tr>
			<tr> <td>Ora inizio:</td> <td> <input type="time" name="orainizio"/> </td> </tr>
			<tr> <td>Durata:</td> <td> <input type="text" name="durata"/> </td> </tr>
			<tr> <td>Codice Corso:</td> <td> <select name="codc">
			
					<?php
					$result=mysqli_query($con,'SELECT Nome,CodC FROM CORSI');
					if(!$result)
						die('Impossibile trovare corsi. '.mysqli_error($con));
						
					if(mysqli_num_rows($result)>0){
						while($corso=mysqli_fetch_row($result)){
							printf("<option value=%s>%s (%s)</option>",$corso[1],$corso[0],$corso[1]);
						}
					}
					
					mysqli_free_result($result);
					?>
			</select> </td> </tr>
			<tr> <td>Sala:</td> <td> <input type="text" name="sala"/> </td> </tr>
			<table>
			
			<p><input type="submit" name="Inserisci" value="inserisci"/></p>
			
		</form>

	</body>
</html>