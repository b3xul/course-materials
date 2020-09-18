<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
	table, th, td {
		border: 1px solid black;
	}
	</style>
	<title>
	Programmazione
	</title>
	</head>
	
	<body>
		<?php
			/*ini_set('display_errors', 'On');
			error_reporting(E_ALL | E_STRICT);
			mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);*/
			
			$parametri= array("CodFisc","CodC","Data","OraInizio","OraFine");
			$input= array();
			foreach($parametri as $par){
				if(!isset($_REQUEST[$par]))
					die("Parametro $par mancante.");				
				$input[$par]=($_REQUEST[$par]);
				
				if($input[$par]=="")
					die("Parametro $par non valorizzato.");
			}
			
			/*foreach($input as $key => $val)
				echo "$key -> $val</br>";
			*/
			if($input["OraFine"]<=$input["OraInizio"])
				die ("Parametri OraFine e OraInizio non consistenti (OraFine precede o eguaglia OraInizio).</br>");
			
				
			$con=mysqli_connect("localhost","root","","televisione");
			if(mysqli_connect_errno())
				die('Connessione non riuscita '.mysqli_connect_error());
			mysqli_set_charset($con,"utf8");
			
			mysqli_query($con,"SET autocommit=0;");
			mysqli_query($con,"START TRANSACTION;");
			
			$query="SELECT *
					FROM APPARIZIONE
					WHERE 	CodFisc='$input[CodFisc]' AND
							Data='$input[Data]'";
			$result=mysqli_query($con,$query);
			
			if(!$result)
				die("Query non riuscita").mysqli_error($con);
			
			$sovrapposizioni=0;
			
			if(($num_rows=mysqli_num_rows($result))>0){
				//Aggiungo i secondi alla stringa hh:mm ricevuta in input per poterla confrontare con le stringhe TIME presenti sul DB
				$input["OraInizio"].=":00";
				$input["OraFine"].=":00";
				while($visualizza=mysqli_fetch_array($result,MYSQLI_ASSOC)){
					/*se l'apparizione inizia tra l'inizio e la fine di un'altra apparizione
						oppure se l'apparizione finisce tra l'inizio e la fine di un'altra apparizione
						oppuere se inizia prima dell'inizion e finisce dopo la fine*/
					if	(($input["OraInizio"]>$visualizza["OraInizio"] && $input["OraInizio"]<$visualizza["OraFine"])||	
						($input["OraFine"]>$visualizza["OraInizio"] && $input["OraFine"]<$visualizza["OraFine"])||
						($input["OraInizio"]<$visualizza["OraInizio"] && $input["OraFine"]>=$visualizza["OraFine"])){
							$sovrapposizioni++;
							/*if($sovrapposizioni==1){
								echo ("<table>");
								echo ("<tr> <th> CodFisc </th> <th> Data </th> <th> OraInizio </th> <th> OraFine </th> <th> CodC </th> </tr>");
							}
							
							echo "<tr>";
							foreach($visualizza as $key => $val){
								echo ("<td>$val</td>");
							}
							echo "</tr>";*/
					}
				}
				
			}
			if($sovrapposizioni>0){
				/*echo ("</table>");
					echo '</br>';
				if($sovrapposizioni==1)
					echo "E' presente una sovrapposizione!</br>";
				else
					echo "Sono presenti $sovrapposizioni sovrapposizioni!</br>";*/
				mysqli_query($con,"ROLLBACK");
				echo ("Non è consentito inserire due apparizioni dello stesso vip sovrapposte tra di loro. </br>");
			}
			else{
				$insert="INSERT INTO APPARIZIONE(CodFisc,Data,OraInizio,OraFine,CodC)
						VALUES('$input[CodFisc]','$input[Data]','$input[OraInizio]','$input[OraFine]','$input[CodC]')";
					
				$fine=mysqli_query($con,$insert);
				
				if($fine){
					mysqli_query($con,"COMMIT");
					echo "L'apparizione è stata inserita con successo. </br>";
				}
				else{
					mysqli_query($con,"ROLLBACK");
					echo "Impossibile inserire nuova apparizione. E' già presente un'apparizione con la stessa chiave primaria. </br>" .mysqli_error($con);
				}
			}
			
			mysqli_close($con);
		
		echo "<form method='POST' action='form3_progetto.php'>";
		echo "<input type='submit' value='Effettua un altro inserimento'/>";
		echo "</form>";
		?>
	</body>
</html>