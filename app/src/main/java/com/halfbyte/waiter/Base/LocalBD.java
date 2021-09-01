package com.halfbyte.waiter.Base;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LocalBD {
    @PrimaryKey
    @ColumnInfo(name = "id_local")
    private int id_local;
    @ColumnInfo(name="codigo")
    private String codigo;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="imagen")
    private String imagen;
    @ColumnInfo(name="mesa")
    private int mesa;

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
}
