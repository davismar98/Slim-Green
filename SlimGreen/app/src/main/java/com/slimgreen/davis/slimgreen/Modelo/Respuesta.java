package com.slimgreen.davis.slimgreen.Modelo;

import android.util.Log;

/**
 * Created by davis on 16/11/2017.
 */

public class Respuesta {

    private String fecha, pregunta1, pregunta2, pregunta3,pregunta4,pregunta5,pregunta6,pregunta7,pregunta8,pregunta9,pregunta10;

    public Respuesta(String fecha, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {
        this.fecha = fecha;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
        this.pregunta4 = pregunta4;
        this.pregunta5 = pregunta5;
        this.pregunta6 = pregunta6;
        this.pregunta7 = pregunta7;
        this.pregunta8 = pregunta8;
        this.pregunta9 = pregunta9;
        this.pregunta10 = pregunta10;
    }


    public String getRespuesta() {

        //Como pregunta4 y pregunta9 pueden tener múltiples respuestas, debemos hacer un formateo
        String rtasP4[] = pregunta4.split(",");
        String rtasP9[] = pregunta9.split(",");
        String pregunta4Format = "";
        String pregunta9Format = "";
        for(int i = 0; i<rtasP4.length; i++){
            if (i == (rtasP4.length - 1)) {
                pregunta4Format += rtasP4[i].trim();
            }else{
                pregunta4Format += rtasP4[i].trim() + "', '";
            }
        }
        for(int i = 0; i<rtasP9.length; i++){
            if (i == (rtasP9.length - 1)) {
                pregunta9Format += rtasP9[i].trim();
            }else{
                pregunta9Format += rtasP9[i].trim() + "', '";
            }
        }

        return "'"+ pregunta1 + "', '" + pregunta2 + "', '" + pregunta3 + "', '" + pregunta4Format + "', '" + pregunta5 + "', '" + pregunta6 + "', '" + pregunta7 + "', '" + pregunta8 + "', '" + pregunta9Format + "', '" + pregunta10 + "'";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(String pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public String getPregunta6() {
        return pregunta6;
    }

    public void setPregunta6(String pregunta6) {
        this.pregunta6 = pregunta6;
    }

    public String getPregunta7() {
        return pregunta7;
    }

    public void setPregunta7(String pregunta7) {
        this.pregunta7 = pregunta7;
    }

    public String getPregunta8() {
        return pregunta8;
    }

    public void setPregunta8(String pregunta8) {
        this.pregunta8 = pregunta8;
    }

    public String getPregunta9() {
        return pregunta9;
    }

    public void setPregunta9(String pregunta9) {
        this.pregunta9 = pregunta9;
    }

    public String getPregunta10() {
        return pregunta10;
    }

    public void setPregunta10(String pregunta10) {
        this.pregunta10 = pregunta10;
    }
}
