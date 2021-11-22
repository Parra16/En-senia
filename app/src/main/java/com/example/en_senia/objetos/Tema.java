package com.example.en_senia.objetos;

import android.net.Uri;

public class Tema {
    private String id;
    private String titulo;
    private String descripcion;
    private Uri imagen;

    public Tema(String id, String titulo, String descripcion, Uri imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Tema() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }
}
