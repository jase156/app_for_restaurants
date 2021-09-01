package com.halfbyte.waiter.Modelo;

public class Local {
    private int id_local;
    private String codigo;
    private String nombre;
    private String imagen;
    private int mesa;

    public int getId() {
        return id_local;
    }

    public void setId(int id) {
        this.id_local = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
