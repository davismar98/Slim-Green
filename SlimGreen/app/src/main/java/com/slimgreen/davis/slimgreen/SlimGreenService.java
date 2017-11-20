package com.slimgreen.davis.slimgreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.slimgreen.davis.slimgreen.Modelo.Respuesta;
import com.slimgreen.davis.slimgreen.Modelo.Servicio;
import com.slimgreen.davis.slimgreen.Modelo.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class SlimGreenService extends AppCompatActivity implements AsyncResponse{

    private boolean formularioCompletado = false;
    private String username, urlFormulario, query, respuesta;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
    private ArrayList<Servicio> servicios = new ArrayList<Servicio>();

    private TextView txtNombre, txtUsername, txtFormulario, txtEstado, txtFecha;
    private Button btnResponder, btnSugerencias, btnBuscar;

    private boolean userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtFormulario = (TextView) findViewById(R.id.txtFormulario);
        txtEstado = (TextView) findViewById(R.id.txtEstado);
        txtFecha = (TextView) findViewById(R.id.txtFecha);

        btnResponder = (Button) findViewById(R.id.btnResponder);
        btnSugerencias = (Button) findViewById(R.id.btnSugerencias);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SlimGreenService.this, GestorFomulario.class);
                i.putExtra("url", urlFormulario);
                startActivityForResult(i, 1);
            }
        });

        btnSugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(formularioCompletado){

                    //Se crea la tarea para traer las sugerencias en JSON
                    HashMap data = new HashMap();
                    //Se envían las respuestas del usuario al PHP
                    data.put("respuestas", respuesta);

                    PostResponseAsyncTask task = new PostResponseAsyncTask(SlimGreenService.this, data, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            servicios = new JsonConverter<Servicio>().toArrayList(s, Servicio.class);
                            //Se carga el gestor de mapas con la lista de servicios traidos del servidor
                            Intent i = new Intent(SlimGreenService.this, GestorMapas.class);
                            i.putExtra("servicios", servicios);
                            startActivity(i);
                        }
                    });
                    task.execute("http://10.0.2.2/SlimGreen/getSugerencias.php");
                }else{
                    Toast.makeText(SlimGreenService.this, R.string.fomularioIncompleto, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(SlimGreenService.this, respuesta, Toast.LENGTH_SHORT).show();

            }
        });


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se traen todos los servicios disponiles del servidor
                PostResponseAsyncTask task = new PostResponseAsyncTask(SlimGreenService.this, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        servicios = new JsonConverter<Servicio>().toArrayList(s, Servicio.class);
                        //Toast.makeText(SlimGreenService.this, "Restaurantes encontrados: " + servicios.size(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SlimGreenService.this, GestorMapas.class);
                        i.putExtra("servicios", servicios);
                        startActivity(i);
                    }
                });
                task.execute("http://10.0.2.2/SlimGreen/getServicios.php");
            }
        });

        //se verifica si hay una sesión y se trae el username
        userLogged = checkUserStatus();
        if(userLogged)
            username = getUserLogged();
            //Toast.makeText(SlimGreenService.this, "User logged: " +getUserLogged(), Toast.LENGTH_SHORT).show();

        /*Bundle extras = getIntent().getExtras();
        username = extras.getString("username");*/
        query = "";

        HashMap data = new HashMap();
        data.put("username", username);

        PostResponseAsyncTask task = new PostResponseAsyncTask(SlimGreenService.this, data, this);
        task.execute("http://10.0.2.2/SlimGreen/getUserInfo.php");

    }

    @Override
    public void processFinish(String s) {
        //se convierte el JSON en objetos tipo Usuario
        usuarios = new JsonConverter<Usuario>().toArrayList(s, Usuario.class);
        Usuario u = usuarios.get(0);

        txtNombre.setText(u.getNombre() + " " + u.getApellido());
        txtUsername.setText(u.getUsername());

        txtFormulario.setText("Formulario " + u.getFormulario());

        if(u.getFormularioEstado() == 0){
            formularioCompletado = false;
            txtEstado.setText("Incompleto");
            txtFecha.setText("Creado: " + u.getFormularioFecha());
            btnResponder.setText(R.string.btn_responder);
        }else if(u.getFormularioEstado() == 1){
            formularioCompletado = true;
            txtEstado.setText("Completado");
            txtFecha.setText("Respondido: " +u.getFormularioFecha());
            btnResponder.setText(R.string.btn_responder2);
        }

        urlFormulario = u.getFormularioURL();
        respuesta = u.getRespuesta();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, SlimGreenService.class);
            startActivity(refresh);
            this.finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            System.exit(0);
        }else if(id == R.id.action_logout){
            SharedPreferences pref = getSharedPreferences(
                    "Session Data", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("LAST_VERSION_CODE", BuildConfig.VERSION_CODE);
            edit.putBoolean("isLoggedIn", false);
            edit.commit();
            startActivity(new Intent(SlimGreenService.this, Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    protected boolean checkUserStatus(){
        boolean isLoggedIn ;
        Context context = getApplicationContext();
        SharedPreferences pref = context.getSharedPreferences("Session Data", MODE_PRIVATE);
        isLoggedIn = pref.getBoolean("isLoggedIn", false);
        return isLoggedIn ;
    }

    protected String getUserLogged(){
        String user;
        Context context = getApplicationContext();
        SharedPreferences pref = context.getSharedPreferences("Session Data", MODE_PRIVATE);
        user = pref.getString("username", "userDefault");
        return user ;
    }


}
