package org.example.demo3.Entidades;

public class Encuesta {
    private int idEncuesta;
    private String nombrePersona;
    private String pregunta;
    private String generoMusicalFav;
    private boolean teGustaTomar;
    private boolean teGustaBailar;

    public Encuesta(int idEncuesta, String nombrePersona, String pregunta, String generoMusicalFav,
                    boolean teGustaTomar, boolean teGustaBailar) {
        this.idEncuesta = idEncuesta;
        this.nombrePersona = nombrePersona;
        this.pregunta = pregunta;
        this.generoMusicalFav = generoMusicalFav;
        this.teGustaTomar = teGustaTomar;
        this.teGustaBailar = teGustaBailar;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getGeneroMusicalFav() {
        return generoMusicalFav;
    }

    public void setGeneroMusicalFav(String generoMusicalFav) {
        this.generoMusicalFav = generoMusicalFav;
    }

    public boolean isTeGustaTomar() {
        return teGustaTomar;
    }

    public void setTeGustaTomar(boolean teGustaTomar) {
        this.teGustaTomar = teGustaTomar;
    }

    public boolean isTeGustaBailar() {
        return teGustaBailar;
    }

    public void setTeGustaBailar(boolean teGustaBailar) {
        this.teGustaBailar = teGustaBailar;
    }

}