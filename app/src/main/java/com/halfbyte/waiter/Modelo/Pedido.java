package com.halfbyte.waiter.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Parcelable {
    private int id_pedido;
    private String nombre_cliente;
    private String mesa;
    private List<Mesero> mesero;
    private float total;
    private List<Producto> producto;
    private String detalle;

    public Pedido(){}
    public  Pedido(Parcel in){
        mesero = new ArrayList<Mesero>();
        producto = new ArrayList<Producto>();
        readFromParcel(in);
    }

    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

    public int getId() {
        return id_pedido;
    }

    public void setId(int id) {
        this.id_pedido = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public List<Mesero> getMesero() {
        return mesero;
    }

    public void setMesero(List<Mesero> mesero) {
        this.mesero = mesero;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_pedido);
        parcel.writeString(nombre_cliente);
        parcel.writeString(mesa);
        parcel.writeList(mesero);
        parcel.writeFloat(total);
        parcel.writeList(producto);
        parcel.writeString(detalle);
    }
    private void readFromParcel(Parcel in) {
        id_pedido = in.readInt();
        nombre_cliente = in.readString();
        mesa = in.readString();
        in.readList(mesero,null);
        total = in.readFloat();
        in.readList(producto,null);
        detalle = in.readString();
    }
}
