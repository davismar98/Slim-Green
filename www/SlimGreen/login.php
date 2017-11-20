<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtUsername']) && isset($_POST['txtPassword']) ) { 
        $username = $_POST['txtUsername'];
        $password = $_POST['txtPassword'];
        
        $query = "SELECT username, password FROM usuario ". 
        " WHERE username = '$username' AND password = '$password'"; 
        
        $result = mysqli_query($conn, $query);
      
        if($result->num_rows > 0){
            echo $username;
            exit; 
        }
        else{ 
            echo "failed"; 
            exit;
        } 
    } 
?>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<head><title>Login|Slim&Green</title></head>
    <body>
        <h1>Iniciar Sesión|<a href=”http://www.slimgreen.com”>Slim & Green</a></h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            Usuario <input type="text" name="txtUsername" value="" /><br/>
            Contraseña <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>