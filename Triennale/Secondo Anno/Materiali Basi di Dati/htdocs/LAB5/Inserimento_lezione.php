<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<title>
	Inserimento corso
	</title>
	
	<body>
		<?php
			ini_set('display_errors', 'On');
			error_reporting(E_ALL | E_STRICT);
			mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
			
			$parametri= array("CodFisc","Giorno","OraInizio","Durata","CodC","Sala");
			$valori= array();
			foreach($parametri as $par){
				if(!isset($_REQUEST[$par]))
					die("Parametri mancanti");
				if($_REQUEST[$par]=="")
					die("Parametro $par non valorizzato");
				
				
				$valori[$par]=($_REQUEST[$par]);
			}
			
			/*foreach($valori as $key => $val)	Controllo array associativo
				echo "$key -> $val</br>";
			*/
			if($valori["Durata"]>60)			/*Controllo vincoli*/
				die("Impossibile creare una lezione di durata maggiore di 60 minuti!");
			
			if($valori["Giorno"]!="Lunedì"&&$valori["Giorno"]!="Martedì"&&$valori["Giorno"]!="Mercoledì"&&$valori["Giorno"]!="Giovedì"&&$valori["Giorno"]!="Venerdì")
				die("Impossibile creare una lezione nel giorno specificato!");
			
			$con=mysqli_connect("localhost","root","","palestra");
			if(mysqli_connect_errno())
				die('Connessione non riuscita '.mysqli_connect_error());
			mysqli_set_charset($con,"utf8");
			
			mysqli_query($con,"SET autocommit=0;");
			mysqli_query($con,"START TRANSACTION;");
			
			$query="SELECT *
					FROM PROGRAMMA
					WHERE 	CodC='$valori[CodC]' AND
							Giorno='$valori[Giorno]'";
			$result=mysqli_query($con,$query);
			
			if(!$result)
				die("Query non riuscita").mysqli_error($con);
			
			if($num_rows=mysqli_num_rows($result)>0){
				echo "$num_rows";
				if($num_rows>1)
					echo "Sono già presenti più lezioni dello stesso corso nel giorno selezionato: </br>";
				else
					echo "E' già presente un'altra lezione dello stesso corso nel giorno selezionato: </br>";
				
				while($visualizza=mysqli_fetch_assoc($result)){
					foreach($visualizza as $key => $val)
						echo "$val ";
					echo '</br>';
				}
				mysqli_query($con,"ROLLBACK");
				die ("Impossibile inserire nuova lezione");
			}
			
			$insert="INSERT INTO PROGRAMMA(CodFisc,Giorno,OraInizio,Durata,CodC,Sala)
					VALUES('$valori[CodFisc]','$valori[Giorno]','$valori[OraInizio]','$valori[Durata]','$valori[CodC]','$valori[Sala]')";
					
			$fine=mysqli_query($con,$insert);
			
			if($fine){
				mysqli_query($con,"COMMIT");
				echo "La lezione è stata inserita nel programma dei corsi. </br>";
			}
			else{
				mysqli_query($con,"ROLLBACK");
				echo "Impossibile inserire la lezione nel programma dei corsi." .mysqli_error($con);
			}
			
			mysqli_close($con);
		?>
		<form method="POST" action="Form_Inserimento_lezioni.php">
		<p> <input type="submit" name="Inserisci" value="Effettua un altro inserimento"/> </p>
		</form>
	</body>
</html>