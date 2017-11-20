<?PHP
include_once("connection.php");

if(isset($_POST['respuestas'])){

	 $respuestas = $_POST['respuestas'];

	$query = "SELECT DISTINCT s.idServicio, s.nombre, s.telefono, s.direccion, s.correo, s.foto, s.horario, s.latitud, s.logitud, round(avg(o.calificacion)) as calificacion, count(o.calificacion) as cantidadOpiniones
from servicio s
inner join servicio_atributo sa on s.idServicio = sa.idServicio
inner join atributo a on sa.codigoAtributo = a.codigoAtributo
LEFT OUTER JOIN opinion o on s.idServicio = o.idServicio
WHERE a.rtaAsociada in ($respuestas)
GROUP by s.idServicio
HAVING COUNT(s.idServicio)>3";

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
<head><title>GET RESPUESTAS</title></head>
    <body>
        
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            respuestas: <input type="text" name="respuestas" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>

