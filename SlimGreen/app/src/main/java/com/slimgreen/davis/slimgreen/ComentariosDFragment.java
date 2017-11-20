package com.slimgreen.davis.slimgreen;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.slimgreen.davis.slimgreen.Modelo.Opinion;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DavidM on 14/04/2017.
 */

public class ComentariosDFragment extends DialogFragment implements AsyncResponse {
    private int idServicio;
    private RecyclerView recyclerView;
    private ArrayList<Opinion> opinions;
    private AdapterOpiniones adapterOpiniones;
    private RatingBar ratingServicio;
    private CheckBox cbComentario;
    private EditText etComentario;
    private Button btnEnviar;
    private TextView txtUsuario, txtRating, noComentarios;
    private String user, comment, rating;
    private int nErrores;
    private int mNum;
    private MyDialogCloseListener closeListener;


    private boolean userLogged;
    private String username;

    /**
     * Create a new instance of ComentariosDFragment, providing "num"
     * as an argument.
     */
    static ComentariosDFragment newInstance(int idServicio) {
        ComentariosDFragment f = new ComentariosDFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("idServicio", idServicio);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idServicio = getArguments().getInt("idServicio");

        userLogged = checkUserStatus();
        if(userLogged){
            username = getUserLogged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_comentarios, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerDialogFragment);
        Toast.makeText(ComentariosDFragment.this.getActivity(), idServicio + " ", Toast.LENGTH_SHORT).show();
        // View tv = v.findViewById(R.id.text);
        //((TextView)tv).setText("Dialog #" + mNum);
        // Watch for button clicks.
        HashMap postData = new HashMap();
        postData.put("txtIdServicio", idServicio + "");
        PostResponseAsyncTask tarea = new PostResponseAsyncTask(ComentariosDFragment.this.getActivity(), postData, this);
        tarea.execute("http://10.0.2.2/SlimGreen/getComentarios.php");


        this.getDialog().setTitle("Sobre este restaurante");

        ratingServicio = (RatingBar) v.findViewById(R.id.ratingServicio);
        etComentario = (EditText) v.findViewById(R.id.etComent);
        txtUsuario = (TextView) v.findViewById(R.id.txtUser);
        txtUsuario.setText("@" +username);
        cbComentario = (CheckBox) v.findViewById(R.id.cbComentario);
        btnEnviar = (Button) v.findViewById(R.id.btnEnviar);
        txtRating = (TextView) v.findViewById(R.id.txtRating);
        noComentarios = (TextView) v.findViewById(R.id.noComentarios);

        cbComentario.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etComentario.setEnabled(true);
                    etComentario.setHint("Escribe tu opinión sobre este servicio");
                }else{
                    etComentario.setText("");
                    etComentario.setHint("¿Sin comentario? ¡No importa!");
                    etComentario.setEnabled(false);
                }
            }
        });

        ratingServicio.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtRating.setText((int) (10*rating) + "/100");
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnEnviar.setText("Clicked");
                if (cantidadErrores()==0){
                    HashMap postData = new HashMap();
                    postData.put("txtUsername", username);
                    postData.put("txtIdServicio", idServicio +"");
                    postData.put("txtCalificacion", rating);
                    postData.put("txtComentario", comment);

                    PostResponseAsyncTask tarea = new PostResponseAsyncTask(ComentariosDFragment.this.getActivity(), postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            if(s.equalsIgnoreCase("success")){
                                Toast.makeText(ComentariosDFragment.this.getActivity(), "Done!", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }else{
                                Toast.makeText(ComentariosDFragment.this.getActivity(), s, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    tarea.execute("http://10.0.2.2/SlimGreen/insertOpinion.php");
                }
            }
        });

        return v;

    }

    public void DismissListner(MyDialogCloseListener closeListener) {
        this.closeListener = closeListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(closeListener!=null) {
            closeListener.handleDialogClose(null);
        }

    }

    @Override
    public void processFinish(String s) {
        if(s.equalsIgnoreCase("null")){
            noComentarios.setVisibility(View.VISIBLE);
        }else{
            opinions = new JsonConverter<Opinion>().toArrayList(s, Opinion.class);
            adapterOpiniones = new AdapterOpiniones(ComentariosDFragment.this.getActivity(), opinions);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            LinearLayoutManager layoutManager = new LinearLayoutManager(ComentariosDFragment.this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterOpiniones);
            recyclerView.addItemDecoration(new DividerItemDecoration(ComentariosDFragment.this.getActivity()));
        }


    }

    public int cantidadErrores(){
        nErrores=0;
        String errores = "";
        if (ratingServicio.getRating()==0){
            errores = "* Ingresa una calificación para el restaurante.\n";
            txtRating.setError("Ingresa una calificación");
            nErrores+=1;
        }else{
            rating = (int)ratingServicio.getRating()*10 +"";
            txtRating.setError(null);
        }

        /*user = etUsuario.getText().toString();
        if(TextUtils.isEmpty(user)){
            etUsuario.setError("Ingresa un nombre");
            nErrores+=1;
        }*/

        comment = etComentario.getText().toString();
        if(cbComentario.isChecked()){
            if(TextUtils.isEmpty(comment)){
                etComentario.setError("Ingresa un comentario");
                nErrores+=1;
            }
        }
        return nErrores;
    }

    protected boolean checkUserStatus(){
        boolean isLoggedIn ;
        Context context = ComentariosDFragment.this.getActivity().getApplicationContext();
        SharedPreferences pref = context.getSharedPreferences("Session Data", Context.MODE_PRIVATE);
        isLoggedIn = pref.getBoolean("isLoggedIn", false);
        return isLoggedIn ;
    }

    protected String getUserLogged(){
        String user;
        Context context = ComentariosDFragment.this.getActivity().getApplicationContext();
        SharedPreferences pref = context.getSharedPreferences("Session Data", Context.MODE_PRIVATE);
        user = pref.getString("username", "userDefault");
        return user ;
    }
}
