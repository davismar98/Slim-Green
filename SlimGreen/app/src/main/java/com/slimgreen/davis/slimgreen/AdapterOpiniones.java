package com.slimgreen.davis.slimgreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slimgreen.davis.slimgreen.Modelo.Opinion;

import java.util.ArrayList;

/**
 * Created by DavidM on 26/03/2017.
 */

public class AdapterOpiniones extends RecyclerView.Adapter<AdapterOpiniones.myViewHolder>{

    private final LayoutInflater inflater;
    Context contextAct;
    ArrayList<Opinion> opiniones;

    public AdapterOpiniones(Context context, ArrayList<Opinion> listData){
        inflater = LayoutInflater.from(context);
        this.opiniones = listData;
        this.contextAct=context;
    }
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.comentarios_list_row,parent,false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        Opinion opinion = opiniones.get(position);
        holder._usuario.setText(opinion.getNombre() + " (@"+opinion.getUsername()+")");
        holder._comentario.setText("("+ opinion.getCalificacion()+") "+ opinion.getComentario());
        holder._fecha.setText(opinion.getFecha());
        holder._estrellas.setIsIndicator(true);
        holder._estrellas.setStepSize(0.5f);
        holder._estrellas.setRating((opinion.getCalificacion()/10));
    }

    @Override
    public int getItemCount() {
        return opiniones.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView _usuario;
        TextView _comentario;
        TextView _fecha;
        RatingBar _estrellas;

        public myViewHolder(View itemView) {
            super(itemView);

            _usuario = (TextView)  itemView.findViewById(R.id.txt_usuario);
            _comentario = (TextView) itemView.findViewById(R.id.txt_comentario);
            _fecha = (TextView) itemView.findViewById(R.id.txt_fecha);
            _estrellas = (RatingBar) itemView.findViewById(R.id.rb_calificacion);
            itemView.setTag(itemView);
        }

    }
}
