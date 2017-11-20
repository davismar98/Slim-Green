<?PHP
	include_once("connection.php");
	if(isset($_POST['txtUsername'])  && isset($_POST['txtIdServicio']) && isset($_POST['txtCalificacion']) && isset($_POST['txtComentario']))
	{
	$username = $_POST['txtUsername'];
	$idServicio = $_POST['txtIdServicio'];
	$calificacion = $_POST['txtCalificacion'];
	$comentario = $_POST['txtComentario'];
	date_default_timezone_set('America/Bogota');
    $date = date('Y-m-d H:i:s');
	$query = "INSERT INTO `opinion`(`username`, `idServicio`, `calificacion`, `comentario`, `fecha`) VALUES ('$username',$idServicio,$calificacion, '$comentario', '$date')"; 
    $result = mysqli_query($conn, $query);
		if($result > 0){
			echo 'success';
			exit;
		}
	}
?>

<html>
<head><title>Agregar Opinión</title></head>
    <body>
        <h1><a href=”http://www.criminalbrowser.com”>App sin nombre</a></h1>
        <h1>Agregar Opinión</h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
			username <input type="text" name="txtUsername" value="" /><br/>
            idServicio <input type="text" name="txtIdServicio" value="" /><br/>
            Calificación <input type="text" name="txtCalificacion" value="" /><br/>
            Comentario <input type="text" name="txtComentario" value="" /><br/>

            <input type="submit" name="btnSubmit" value="Agregar Comentario"/>
        </form>
    </body>
</html>
