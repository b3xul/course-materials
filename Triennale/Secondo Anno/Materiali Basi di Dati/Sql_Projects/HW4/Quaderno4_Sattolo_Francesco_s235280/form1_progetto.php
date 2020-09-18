<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	Ricerca
	</title>
	</head>
	<body>
		<h1>Ricerca VIP</h1>
		
		<body>
		<table>
		<form method="GET" action="Cerca1_progetto.php">
			<tr> <td> Prime tre lettere del cognome:</td> <td> <input type="text" size="3" maxLength="3" name="Iniziali"> </td> </tr>
			<tr> <td> Emittente: <select name="Emittente">
								<?php
								$con=mysqli_connect('localhost','root','','televisione');
								if(mysqli_connect_errno())
									die('Connessione non riuscita '.mysqli_connect_error());
								mysqli_set_charset($con,"utf8");
								
								$result=mysqli_query($con,'SELECT DISTINCT Emittente FROM CANALE_TV ORDER BY Emittente');
									if(!$result)
										die('Impossibile trovare emittenti. '.mysqli_error($con));

								if(mysqli_num_rows($result)>0){	
									while($emittente=mysqli_fetch_row($result)){
										printf("<option value=%s>%s</option>",$emittente[0],$emittente[0]);
									}
								}
								mysqli_free_result($result);
								mysqli_close($con);
								?>
								</select>
								</td> </tr>
		</table>
		<p><input type="reset" value="Reimposta"/> <input type="submit" value="Invia Richiesta"/> </p>
			
		</form>
	</body>
</html>