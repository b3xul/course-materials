<html>
	<title>
	Inserimento corso
	</title>
	
	<body>
		<?php
			$parametri= array("CodC","Nome","Tipo","Livello");
			
			foreach($parametri as $par){
				if(!isset($_REQUEST[$par]))
					die("Parametri mancanti");
				if($_REQUEST[$par]=="")
					die("Parametro $par non valorizzato");
			}
			
			$CodC=$_REQUEST["CodC"];
			$Nome=$_REQUEST["Nome"];
			$Tipo=$_REQUEST["Tipo"];
			$Livello=$_REQUEST["Livello"];
			
			/*Necessario? Sempre meglio abbondare!*/
			if (!is_numeric($Livello))
				die("Errore: Il livello deve essere un valore intero compreso tra 1 e 4");
			/**/
			
			if($Livello<1||$Livello>4)
				die("Impossibile inserire nuovo corso: il livello non rispetta il range 1-4");
			
			$con=mysqli_connect("localhost","root","","palestra");
			if(mysqli_connect_errno())
				die('Connessione non riuscita '.mysqli_connect_error());
			
			/*Query di controllo integrità CodC (evitare ripetizioni) o fatto qui o lasciato fare al DB*/
			$query="SELECT CodC,Giorno
					FROM PROGRAMMA
					WHERE CodC='$CodC'";
			$result=mysqli_query($con,$query);
			while($corso=mysqli_fetch_assoc($result)){
					printf("<p> %s %s</p>",$corso['CodC'],$corso['Giorno']);	
			}	
			if(mysqli_num_rows($result)>0)
				die("Impossibile inserire nuovo corso: la chiave primaria non può essere duplicata");
			
			mysqli_free_result($result);
			
			$query2=	"INSERT INTO CORSI(CodC,Nome,Tipo,Livello)
						VALUES ('$CodC','$Nome','$Tipo','$Livello');";
			
			$result=mysqli_query($con,$query2);
			if(!$result)
				die ('Impossibile eseguire la query richiesta! '.mysqli_error($con));
			
			echo "Il corso $CodC è stato inserito nel database. </br>";
			mysqli_close($con);
		?>
		<form method="POST" action="Form_Inserimento_corsi.php">
		<input type="submit" name="Inserisci" value="Effettua un altro inserimento"/>
		</form>
	</body>
</html>