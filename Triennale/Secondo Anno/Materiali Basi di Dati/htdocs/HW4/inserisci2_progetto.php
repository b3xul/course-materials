<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	Risultato inserimento
	</title>
	</head>
	
	<body>
		<?php
			
			$parametri= array("CodFisc","Nome","Cognome","Professione");
			$valori= array();
			
			foreach($parametri as $par){
				if(!isset($_REQUEST[$par]))
					die("Parametro $par mancante.");
				$valori[$par]=($_REQUEST[$par]);
				
				if($par=="Professione")
					continue;	//Salta all'indice successivo senza finire le istruzioni di quello attuale
				if($valori[$par]=='')
					die("Parametro $par non valorizzato.");
			}
			
			$con=mysqli_connect("localhost","root","","televisione");
			if(mysqli_connect_errno())
				die('Connessione non riuscita '.mysqli_connect_error());
			mysqli_set_charset($con,"utf8");
			
			if($valori["Professione"]=='')
				$insert="INSERT INTO VIP(CodFisc,Nome,Cognome,Professione)
						VALUES('$valori[CodFisc]','$valori[Nome]','$valori[Cognome]',NULL)";
			else
				$insert="INSERT INTO VIP(CodFisc,Nome,Cognome,Professione)
						VALUES('$valori[CodFisc]','$valori[Nome]','$valori[Cognome]','$valori[Professione]')";
			
			$result=mysqli_query($con,$insert);
			
			if($result)
				echo "Dato inserito nel DB. </br>";
			else
				/*Lascio che sia il DBMS a fare il controllo per evitare la duplicazione della chiave primaria*/
				echo "Impossibile inserire la lezione nel programma dei corsi. " .mysqli_error($con);
			
			mysqli_close($con);
		?>
		<form method="GET" action="form2_progetto.php">
		<p> <input type="submit" value="Effettua un altro inserimento"/> </p>
		</form>
	</body>
</html>