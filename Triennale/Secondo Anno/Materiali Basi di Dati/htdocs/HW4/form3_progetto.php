<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	Inserimento
	</title>
	</meta>
	
	<body>
		<h1>Inserimento nuova apparizione VIP</h1>
		
		<body>
		<h2>Dati nuova apparizione VIP</h2>
		
		<form method="GET" action="inserisci3_progetto.php">
			<table>
			
			<tr> <td>VIP:</td> <td> <select name="CodFisc">
					<?php
					
					$con=mysqli_connect('localhost','root','','televisione');
					if(mysqli_connect_errno())
						die('Connessione non riuscita '.mysqli_connect_error());
					mysqli_set_charset($con,"utf8");
					
					$result=mysqli_query($con,'SELECT CodFisc,Nome,Cognome FROM VIP');
					if(!$result)
						die('Impossibile trovare vip. '.mysqli_error($con));
						
					if(mysqli_num_rows($result)>0){
						while($vip=mysqli_fetch_row($result)){
							printf("<option value=%s>%s %s (%s)</option>",$vip[0],$vip[1],$vip[2],$vip[0]);
						}
					}
					
					mysqli_free_result($result);
					?>
			</select> </td> </tr>
			<tr> <td>Canale:</td> <td> <select name="CodC">
			
					<?php
					$result=mysqli_query($con,'SELECT CodC,Nome FROM CANALE_TV');
					if(!$result)
						die('Impossibile trovare canali tv. '.mysqli_error($con));
						
					if(mysqli_num_rows($result)>0){
						while($corso=mysqli_fetch_row($result)){
							printf("<option value=%s>%s (%s)</option>",$corso[0],$corso[1],$corso[0]);
						}
					}
					
					mysqli_free_result($result);
					?>
			<tr> <td>Data:</td> <td> <input type="date" name="Data"/> </td> </tr>
			<tr> <td>Ora inizio:</td> <td> <input type="time" name="OraInizio"/> </td> </tr>
			<tr> <td>Ora fine:</td> <td> <input type="time" name="OraFine"/> </td> </tr>
			</select> </td> </tr>
			<table>
			
			</br>
			<p><input type="submit" value="Invia"/> <input type="reset" value="Cancella"/></p>
			
		</form>

	</body>
</html>