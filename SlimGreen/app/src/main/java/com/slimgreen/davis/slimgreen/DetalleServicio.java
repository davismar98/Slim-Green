package com.slimgreen.davis.slimgreen;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.slimgreen.davis.slimgreen.Modelo.Servicio;

import java.util.HashMap;

public class DetalleServicio extends AppCompatActivity {

    private Servicio servicio;
    private ImageView imgServicio;
    private TextView txtSNombre, txtSDireccion, txtSTelefono, txtSCorreo, txtSHorario, txt_puntaje, txt_cant_opiniones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_servicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                showDialog();
            }
        });

        imgServicio = (ImageView) findViewById(R.id.imgServicio);
        txtSNombre = (TextView) findViewById(R.id.txtServicioNombre);
        txtSDireccion = (TextView) findViewById(R.id.txtDireccion);
        txtSTelefono = (TextView) findViewById(R.id.txtTelefono);
        txtSCorreo = (TextView) findViewById(R.id.txtCorreo);
        txtSHorario = (TextView) findViewById(R.id.txtHorario);

        txt_puntaje = (TextView) findViewById(R.id.txt_puntaje);
        txt_cant_opiniones = (TextView) findViewById(R.id.txt_cant_opiniones);

        Intent i = getIntent();
        servicio = (Servicio) i.getSerializableExtra("servicio");
        //Toast.makeText(DetalleServicio.this, servicio.toString(), Toast.LENGTH_SHORT).show();

        if (servicio.getFoto() != null) {
            Glide.with(DetalleServicio.this).load(servicio.getFoto()).into(imgServicio);
        }

        txtSNombre.setText(servicio.getNombre());
        txtSDireccion.setText(servicio.getDireccion());
        txtSTelefono.setText(servicio.getTelefono());
        txtSCorreo.setText(servicio.getCorreo());
        txtSHorario.setText(servicio.getHorario());
        txt_puntaje.setText(servicio.getCalificacion()+"");
        txt_cant_opiniones.setText(servicio.getCantidadOpiniones()+"");

    }

    void showDialog() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.


        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        android.app.Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        // Create and show the dialog.
        DialogFragment newFragment = ComentariosDFragment.newInstance(servicio.getIdServicio());
        newFragment.show(ft, "dialog");

        MyDialogCloseListener closeListener = new MyDialogCloseListener() {
            @Override
            public void handleDialogClose(DialogInterface dialog) {
                HashMap data = new HashMap();
                data.put("txtIdServicio", servicio.getIdServicio() + "");
                PostResponseAsyncTask tarea2 = new PostResponseAsyncTask(DetalleServicio.this, data, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        //Toast.makeText(DetalleCarro.this, s, Toast.LENGTH_SHORT).show();
                        String[] info = s.split(",");
                        if(info[0].equalsIgnoreCase("")){
                            txt_puntaje.setText("0");
                        }else{
                            txt_puntaje.setText(info[0]);
                        }
                        txt_cant_opiniones.setText(info[1]);
                    }
                });
                tarea2.execute("http://10.0.2.2/SlimGreen/getPuntaje.php");
            }
        };

        ((ComentariosDFragment)newFragment).DismissListner(closeListener);

/*
        android.app.FragmentManager fm = getFragmentManager();
        fm.executePendingTransactions();
        newFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(DetalleCarro.this, "Se cerró :,v", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}
