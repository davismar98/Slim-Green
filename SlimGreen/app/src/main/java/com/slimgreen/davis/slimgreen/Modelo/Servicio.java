package com.slimgreen.davis.slimgreen.Modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by davis on 17/11/2017.
 */

public class Servicio implements Serializable{

    //SELECT `idServicio`, `nombre`, `telefono`, `direccion`, `foto`, `horario`, `latitud`, `logitud` FROM `servicio` WHERE 1

    @SerializedName("idServicio") private int idServicio;
    @SerializedName("nombre") private String nombre;
    @SerializedName ("telefono") private String telefono;
    @SerializedName ("direccion") private String direccion;
    @SerializedName("correo") private String correo;
    @SerializedName ("foto") private String foto;
    @SerializedName ("horario") private String horario;
    @SerializedName ("latitud") private float latitud;
    @SerializedName ("logitud") private float longitud;
    @SerializedName("calificacion") private int calificacion;
    @SerializedName("cantidadOpiniones") private int cantidadOpiniones;

    public Servicio(int idServicio, String nombre, String telefono, String direccion, String correo, String foto, String horario, float latitud, float longitud, int calificacion, int cantidadOpiniones) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.foto = foto;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.calificacion = calificacion;
        this.cantidadOpiniones = cantidadOpiniones;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", foto='" + foto + '\'' +
                ", horario='" + horario + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", calificacion=" + calificacion +
                ", cantidadOpiniones=" + cantidadOpiniones +
                '}';
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getCantidadOpiniones() {
        return cantidadOpiniones;
    }

    public void setCantidadOpiniones(int cantidadOpiniones) {
        this.cantidadOpiniones = cantidadOpiniones;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
