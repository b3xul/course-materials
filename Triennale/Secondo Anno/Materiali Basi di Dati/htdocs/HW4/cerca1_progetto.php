<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
	table, th, td {
		border: 1px solid black;
	}
	</style>
	<title>
		Risultati
	</title>
	</head>	
	
	<body> 
		<h1> Apparizioni VIP </h1>
			<?php

			if(!isset($_REQUEST["Iniziali"])||!isset($_REQUEST["Emittente"]))
				die ( "Errore: inserire tutti i dati richiesti" );
			
			$Iniziali=$_REQUEST["Iniziali"];
			$Emittente=$_REQUEST["Emittente"];

			if($Iniziali==''||$Emittente=='')
				die ( "Errore: è presente un campo non valorizzato" );
			
			$con=mysqli_connect('localhost','root','','televisione');
			if(mysqli_connect_errno())
				die('Connessione non riuscita '.mysqli_connect_error());
			mysqli_set_charset($con,"utf8");
			
			$query="SELECT CodC,Data,OraInizio,Cognome,Nome
					FROM APPARIZIONE A, VIP V
					WHERE 	V.CodFisc=A.CodFisc AND
							Cognome LIKE ('$Iniziali%') AND
							CodC IN (SELECT CodC
									FROM CANALE_TV
									WHERE Emittente='$Emittente')
					ORDER BY CodC,Data,OraInizio";
			$result=mysqli_query($con,$query);
			if(!$result)
				die ('Errore nella formulazione della query! '.mysqli_error($con));
			
			if(mysqli_num_rows($result)<=0)
				die ('Nessuna apparizione trovata. '.mysqli_error($con));

			echo ("<table>");
			echo ("<tr> <th> Codice canale </th> <th> Data </th> <th> OraInizio </th> <th> Cognome </th> <th> Nome </th> </tr>");

			while($info=mysqli_fetch_row($result)){
				echo ("<tr><td>$info[0]</td>");
				echo ("<td>$info[1]</td>");
				echo ("<td>$info[2]</td>");
				echo ("<td>$info[3]</td>");
				echo ("<td>$info[4]</td>");
			}
			echo ("</table>");
			mysqli_free_result($result);
			mysqli_close($con);
			?>
		</h1>
		
		
	</body>
</html>