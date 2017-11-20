<?PHP
include_once("connection.php");

if(isset($_POST['txtIdServicio'])){

	 $idServicio = $_POST['txtIdServicio'];

	$query = "SELECT o.username,  concat(u.nombre, ' ', u.apellido) nombre, o.comentario, o.calificacion, o.fecha 
FROM opinion o 
INNER JOIN usuario u on o.username = u.username
WHERE o.idServicio = $idServicio
order by o.fecha desc";

	$result= mysqli_query($conn, $query);

	while($row = mysqli_fetch_assoc($result)){
		$data[] = $row;
	}
	
	if(!empty($data)){
			echo json_encode($data);
	}else{
		echo 'null';
		
	}
	exit;
		
	
}
?>

<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<head><title>GET COMENTARIOS</title></head>
    <body>
        
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            Id del servicio: <input type="text" name="txtIdServicio" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>

