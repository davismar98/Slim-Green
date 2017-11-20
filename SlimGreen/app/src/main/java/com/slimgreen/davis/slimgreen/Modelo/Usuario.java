package com.slimgreen.davis.slimgreen.Modelo;

/**
 * Created by davis on 16/11/2017.
 */

public class Usuario {

    public String username;
    public String password;
    public String nombre;
    public String apellido;
    public String correo;
    public String telefono;
    public int idFormulario;
    public String formulario;
    public String formularioURL;
    public int formularioEstado;
    public String formularioFecha;
    public String respuesta;

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", idFormulario=" + idFormulario +
                ", formulario='" + formulario + '\'' +
                ", formularioURL='" + formularioURL + '\'' +
                ", formularioEstado=" + formularioEstado +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

    public String getFormularioFecha() {
        return formularioFecha;
    }

    public void setFormularioFecha(String formularioFecha) {
        this.formularioFecha = formularioFecha;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getFormularioURL() {
        return formularioURL;
    }

    public void setFormularioURL(String formularioURL) {
        this.formularioURL = formularioURL;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public int getFormularioEstado() {
        return formularioEstado;
    }

    public void setFormularioEstado(int formularioEstado) {
        this.formularioEstado = formularioEstado;
    }
}
