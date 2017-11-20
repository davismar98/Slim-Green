<?PHP
	include_once("connection.php");
	if(isset($_POST['txtIdServicio']))
	{
	$idServicio = $_POST['txtIdServicio'];
	
	$query = "SELECT round(avg(o.calificacion)) as calificacion, count(o.calificacion) as cantidadOpiniones
			from opinion o
			inner join servicio s on o.idServicio = s.idServicio
			where o.idServicio = '$idServicio'";
	$result = mysqli_query($conn, $query);
	$row= mysqli_fetch_array($result);
	$calificacion = $row['calificacion'];
	$cantidad = $row['cantidadOpiniones'];
		echo "$calificacion,$cantidad";

		exit;
	}
?>

<html>
<head><title>Get calificación</title></head>
    <body>
        <h1>Get calificación</h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            idServicio <input type="text" name="txtIdServicio" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Get"/>
        </form>
    </body>
</html>