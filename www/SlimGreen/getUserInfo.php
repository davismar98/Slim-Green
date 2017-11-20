<?PHP
include_once("connection.php");

if(isset($_POST['username'])){

	 $username = $_POST['username'];

	$query = "SELECT u.username, u.password, u.nombre, u.apellido, u.correo, u.telefono, f.idFormulario, f.formulario, f.url as formularioURL, uf.estado as formularioEstado, uf.fecha formularioFecha, uf.respuesta
from usuario u 
inner join usuario_formulario uf on u.username = uf.username
INNER JOIN formulario f on uf.idFormulario = f.idFormulario
where u.username = '$username'";

	$result= mysqli_query($conn, $query);

	while($row = mysqli_fetch_assoc($result)){
		$data[] = $row;
	}
	echo json_encode($data);
	exit;
}
?>

<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<head><title>GET USER INFO</title></head>
    <body>
        
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            username: <input type="text" name="username" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>

