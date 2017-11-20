package com.slimgreen.davis.slimgreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.slimgreen.davis.slimgreen.Modelo.Queries;
import com.slimgreen.davis.slimgreen.Modelo.Respuesta;
import com.slimgreen.davis.slimgreen.Modelo.Servicio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorFomulario extends AppCompatActivity {


    private WebView webViewFormulario;
    private String formularioURL, query, userLogged;
    private ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_fomulario);
        query = "";
        userLogged = getUserLogged();
        webViewFormulario = (WebView) findViewById(R.id.webViewformulario);

        Bundle extras = getIntent().getExtras();
        formularioURL = extras.getString("url");
        //Toast.makeText(GestorFomulario.this, formularioURL, Toast.LENGTH_SHORT).show();
        webViewFormulario.getSettings().setJavaScriptEnabled(true);
        webViewFormulario.getSettings().setLoadWithOverviewMode(true);
        webViewFormulario.getSettings().setUseWideViewPort(true);

        webViewFormulario.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Toast.makeText(GestorFomulario.this, "OK!", Toast.LENGTH_SHORT).show();
                    return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Log.d("MyApp", "onPageFinished ");
                String path = url;
// Split path into segments
                String segments[] = path.split("/");
// Grab the last segment
                String keyWord = segments[segments.length - 1];
                if(keyWord.equalsIgnoreCase("formResponse")){
                    getSugerencias();
                }
                //Toast.makeText(GestorFomulario.this, keyWord, Toast.LENGTH_SHORT).show();
            }
        });



        webViewFormulario.loadUrl(formularioURL);
    }



    private void getSugerencias(){
        query = Queries.selectUltimaRespuesta;

        if (!query.isEmpty()) {
            //this.infoBox.setText("Downloading...");
            respuestas.clear();
            new DownloadWebpageTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    processJson(object);
                }
            }).execute(query);
        }
    }


    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");
                String fecha = columns.getJSONObject(0).getString("f");
                String pregunta1 = columns.getJSONObject(1).getString("v");
                String pregunta2 = columns.getJSONObject(2).getString("v");
                String pregunta3 = columns.getJSONObject(3).getString("v");
                String pregunta4 = columns.getJSONObject(4).getString("v");
                String pregunta5 = columns.getJSONObject(5).getString("v");
                String pregunta6 = columns.getJSONObject(6).getString("v");
                String pregunta7 = columns.getJSONObject(7).getString("v");
                String pregunta8 = columns.getJSONObject(8).getString("v");
                String pregunta9 = columns.getJSONObject(9).getString("v");
                String pregunta10 = columns.getJSONObject(10).getString("v");


                Respuesta resp = new Respuesta(fecha, pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);
                respuestas.add(resp);
            }
            if(!respuestas.isEmpty()){
                String respuesta = respuestas.get(respuestas.size()-1).getRespuesta();
                HashMap data = new HashMap();
                data.put("txtUsername", userLogged);
                data.put("txtRespuesta", respuesta);

                PostResponseAsyncTask task = new PostResponseAsyncTask(GestorFomulario.this, data, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        if(s.contains("success")){
                            //Toast.makeText(GestorFomulario.this, "Se han agregado las respuestas a la BD exitósamente", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK, null);
                            finish();
                        }

                        /*
                        servicios = new JsonConverter<Servicio>().toArrayList(s, Servicio.class);
                        //Toast.makeText(SlimGreenService.this, "Restaurantes encontrados: " + servicios.size(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(SlimGreenService.this, GestorMapas.class);
                        i.putExtra("servicios", servicios);
                        startActivity(i);
                        */
                    }
                });
                task.execute("http://10.0.2.2/SlimGreen/insertRespuesta.php");

                Log.d("Respuesta", respuestas.get(0).getRespuesta());
                //Toast.makeText(SlimGreenService.this, respuestas.get(0).getRespuesta(), Toast.LENGTH_SHORT).show();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(GestorFomulario.this, SlimGreenService.class);
            startActivity(refresh);
            this.finish();
        }
    }*/


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
