package com.halfbyte.waiter.Modelo;

public class Usuario {
    private int id_usuaria;
    private String usuario;
    private String password;
    private int id_local;

    public int getId_usuaria() {
        return id_usuaria;
    }

    public void setId_usuaria(int id_usuaria) {
        this.id_usuaria = id_usuaria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }
}
