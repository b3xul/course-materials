<?php
/*FUCK MY LIFE*/
COSI' E' GIUSTO
if(($num_rows=mysqli_num_rows($result))>0){
		echo "Il risultato contiene $num_rows righe </br>";
PRIMA ASSEGNO NUM_ROWS E POI EFFETTUO IL CONFRONTO

COSI' NON E' GIUSTO
if($num_rows=mysqli_num_rows($result)>0){
		echo "Il risultato contiene $num_rows righe </br>";
PERCHE' LA PRECEDENZA DEGLI OPERATORI FA SI' CHE MYSQLI_NUM_ROWS>0 SIA ESEGUITA PRIMA (RISULTATO=BOOL)
E NUM ROWS CONTENGA IL RISULTATO DEL CONFRONTO (BOOL)! INVECE DELL'INT' RISULTATO
/*UTILE*/
VAR_DUMP($vettore);
mi da info approfondite su cosa contiene;
/*4:elaborazione*/			$length=mysqli_num_rows($result);
							
							for($i=0;$i<$length;$i++){
								echo ("<option value=".$corso[$i].">".$corso[$i]."</option>");
							}
							foreach($corso as $value){
								echo "<p>";
								echo "$value";
								echo "</p>";
							}*/
							
/*FETCH ROW==FETCH ARRAY NUM->corso contiene 1 sola riga. corso[0]=primo campo della riga, corso[1]=secondo campo della riga*/
				
				while($corso=mysqli_fetch_row($result)){
								printf("<p> %s </p>",$corso[0]);
				}
				while($corso=mysqli_fetch_array($result,MYSQLI_NUM)){
								printf("<p> %s </p>",$corso[0]);	
				}
/*FETCH ASSOC==FETCH ARRAY ASSOC->corso contiene 1 sola riga. corso['nomecampo']=campo scelto della riga*/
				
				while($corso=mysqli_fetch_assoc($result)){
								printf("<p> %s </p>",$corso['CodC']);	
				}
				
				while($corso=mysqli_fetch_array($result,MYSQLI_ASSOC)){
								printf("<p> %s </p>",$corso['CodC']);	
				}
/*FETCH ARRAY BOTH->corso contiene 1 sola riga. corso[0]oppure['nomecampo']=campo scelto della riga/primo campo*/
				while($corso=mysqli_fetch_array($result,MYSQLI_BOTH)){
								printf("<p> %s </p>",$corso['CodC']);	
				}
				
/*mysqli_stmt_bind_result() tells mysqli which variable you want to be populate when you fetch a row, but it doesn't fetch() anything yet. This is necessary to call once, before you call fetch.

mysqli_stmt_store_result() sets an optional behavior so that the client downloads all rows when you fetch() the first row, 
and caches the whole result set in the client (i.e. PHP). Subsequent fetch() calls will simply iterate over this client-cached result set.
But setting this option itself does not cause a fetch() yet either. This function is totally optional.

mysqli_stmt_fetch() returns the next row in the result set, and causes it to be stored in a bound variable. You must call this function in a loop, for each row of the result set. That is, until the fetch returns false


The process of querying an SQL database contains these steps:

sending of the query to the SQL server
the SQL server parsing the query and gathering the requested result set
transferring of the found result set data from the SQL server to PHP
putting the data into PHP variables somehow so the script can work with them
The functions you ask about have these roles in this process:

store: transfer all rows immediately from the MySQL database into PHP's memory; typically has no practical effect and is done automatically at some point anyway
bind: bind variables, so that when you call fetch() those variables contain result data; i.e. tell PHP which variables it should put the result into
fetch: "read" a row from the result set and store it in the variables you previously bound; if the data is not in PHP's memory yet it will be transferred from the SQL server at this point*/				
						1	if(!mysqli_stmt_bind_param($stmt,"s",$CodC))
								die('Parametri errati: '.mysqli_error($con));

					
						2	if(!mysqli_stmt_execute($stmt))
								die('Impossibile eseguire query richiesta: '.mysqli_error($con));

							echo ("<table><tr><th>Giorno</th><th>Ora Inizio</th><th>Durata</th><th>Sala</th><th>Nome istruttore</th><th>Cognome istruttore</th></tr>");

						3	$result=mysqli_stmt_get_result($stmt);
							
						4	while($info=mysqli_fetch_row($result)){
								echo ("<tr><td>$info[0]</td>");
								echo ("<td>$info[1]</td>");
								echo ("<td>$info[2]</td>");
								echo ("<td>$info[3]</td>");
								echo ("<td>$info[4]</td>");
								echo ("<td>$info[5]</td></tr>");
							}
							echo ("</table>");
						
/*OPPURE */
							if(!mysqli_stmt_bind_result($stmt,$giorno,$oraInizio,$durata,$sala,$nome,$cognome)) //Bind to vettore!
								die('Parametri di ritorno errati: '.mysqli_error($con));*/
/*ISSET Controlla se la variabile indicata è uguale a NULL oppure è stata settata*/
	(! isset ( $_REQUEST ["codice_corso"] ))
	die ( "Errore: inserire tutti i dati richiesti" );


/*INSERIRE VARIABILI IN UNA QUERY IMMEDIATA: "SELECT CodC FRfOM CORSI WHERE CodC='$CodC'"*/
/*Scorrere FOREACH: foreach($valori as $key => $val)
						echo "$key -> $val</br>";*/
						

$query="SELECT CodC
					FROM CORSI
					WHERE CodC='$CodC'";
			$result=mysqli_query($con,$query);
			if(mysqli_num_rows($result)>0)
				die("Impossibile inserire nuovo corso: la chiave primaria non può essere duplicata");
			/*Necessario?*/
			if (!is_numeric($Livello))
				die("Errore: Il livello deve essere un valore intero compreso tra 1 e 4");
			/**/
			if($Livello<1||$Livello>4)
				die("Impossibile inserire nuovo corso: il livello non rispetta il range 1-4");
			
			mysqli_free_result($result);
			
			$query2=	"INSERT INTO CORSI(CodC,Nome,Tipo,Livello)
						VALUES ('$CodC','$Nome','$Tipo','$Livello');";
			
			$result=mysqli_query($con,$query2);
			if(!$result)
				die ('Impossibile eseguire la query richiesta! '.mysqli_error($con));
			
			
			
$var=$_REQUEST["Cerca"];
							if($var=="Cacca")
								echo "Eli è pirla";
							

/*VALUE E NAME*/
The value attribute specifies the value of an <input> element.

The value attribute is used differently for different input types:

For "button", "reset", and "submit" - it defines the text on the button
For "text", "password", and "hidden" - it defines the initial (default) value of the input field
For "checkbox", "radio", "image" - it defines the value associated with the input (this is also the value that is sent on submit)
Note: The value attribute cannot be used with <input type="file">.
--------
The name attribute specifies the name of an <input> element.

The name attribute is used to reference elements in a JavaScript, or to reference form data after a form is submitted.

Note: Only form elements with a name attribute will have their values passed when submitting a form.							
?>

