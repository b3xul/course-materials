<html>
<?php
$con=mysqli_connect("localhost","root","","palestra");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$Giorno="Martedì";

if ($result=mysqli_query($con,"SELECT CodFisc,Giorno,CodC FROM PROGRAMMA WHERE Giorno='$Giorno'"))
  {
  // Return the number of rows in result set
  $rowcount=mysqli_num_rows($result);
  printf("Result set has %d rows.\n",$rowcount); 
  echo "<table>";
				echo ("	<tr> <th> CodFisc </th> <th> Giorno </th><th> CodC </th></tr>");
								
				while($info=mysqli_fetch_row($result)){
					echo ("<tr><td>$info[0]</td>");
					echo ("<td>$info[1]</td>");
					echo ("<td>$info[2]</td></tr>");
				}
				echo ("</table>");
	// Free result set
  mysqli_free_result($result);
  }

mysqli_close($con);
?>
</html>