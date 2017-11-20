package com.slimgreen.davis.slimgreen.Modelo;

/**
 * Created by davis on 18/11/2017.
 */

public class Opinion {

    private String username, nombre, comentario, fecha;
    private int calificacion;

    public Opinion(String username, String nombre, String comentario, String fecha, int calificacion) {
        this.username = username;
        this.nombre = nombre;
        this.comentario = comentario;
        this.fecha = fecha;
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", calificacion=" + calificacion +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
