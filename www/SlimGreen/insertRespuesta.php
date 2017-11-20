<?PHP
	include_once("connection.php");
	if(isset($_POST['txtUsername'])  && isset($_POST['txtRespuesta']))
	{
	$username = $_POST['txtUsername'];
	$respuesta = $_POST['txtRespuesta'];
	date_default_timezone_set('America/Bogota');
    $date = date('Y-m-d H:i:s');
	
	$stmt = mysqli_stmt_init($conn);
    mysqli_stmt_prepare($stmt, "INSERT INTO `users` (`username`, `email`, `password`, `type`) VALUES (?,?,?)");
	mysqli_stmt_prepare($stmt, "UPDATE `usuario_formulario` SET `estado`=1,`fecha`=?,`respuesta`=? WHERE username = ?"); 
    mysqli_stmt_bind_param($stmt, 'sss', $date, $respuesta, $username);
    mysqli_stmt_execute($stmt);
	
	$result = mysqli_stmt_affected_rows ($stmt);
	
	if($result>0){
		echo 'success';
		exit;
	}
	
	}
?>

<html>
<head><title>Agregar Opinión</title></head>
    <body>
        <h1>Agregar Respuesta</h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
			username <input type="text" name="txtUsername" value="" /><br/>
            respuesta <input type="text" name="txtRespuesta" value="" /><br/>

            <input type="submit" name="btnSubmit" value="Agregar Comentario"/>
        </form>
    </body>
</html>
