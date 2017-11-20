<?PHP
include_once("connection.php");

	$query = "SELECT DISTINCT s.idServicio, s.nombre, s.telefono, s.direccion, s.correo, s.foto, s.horario, s.latitud, s.logitud, round(avg(o.calificacion)) as calificacion, count(o.calificacion) as cantidadOpiniones
from servicio s
inner join servicio_atributo sa on s.idServicio = sa.idServicio
inner join atributo a on sa.codigoAtributo = a.codigoAtributo
LEFT OUTER JOIN opinion o on s.idServicio = o.idServicio
GROUP by s.idServicio";

	$result= mysqli_query($conn, $query);

	while($row = mysqli_fetch_assoc($result)){
		$data[] = $row;
	}
	echo json_encode($data);
	exit;
?>
