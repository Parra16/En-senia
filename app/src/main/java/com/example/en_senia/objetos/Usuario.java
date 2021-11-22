package com.example.en_senia.objetos;

public class Usuario {
    private String idUsuario;
    private String correo;
    private String pass;
    private String nombre;
    private String[] errores;

     public Usuario(String idUsuario, String correo, String pass, String nombre, String[] errores) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.pass = pass;
        this.nombre = nombre;
        this.errores = errores;
    }

    public Usuario() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getErrores() {
        return errores;
    }

    public void setErrores(String[] errores) {
        this.errores = errores;
    }


}
