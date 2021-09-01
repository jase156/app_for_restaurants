package com.halfbyte.waiter.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halfbyte.waiter.Modelo.Mesero;
import com.halfbyte.waiter.Modelo.Pedido;
import com.halfbyte.waiter.Modelo.Producto;
import com.halfbyte.waiter.R;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder>  {
    List<Pedido> pedidos;
    Context context;

    public PedidoAdapter(){
        super();

        Producto producto = new Producto();
        producto.setId(1);
        producto.setImagen(R.drawable.ic_launcher_background);
        producto.setNombre("Cafe doble");

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setImagen(R.drawable.ic_launcher_background);
        producto2.setNombre("Cafe Expreso");

        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        productos.add(producto2);


        Mesero mesero = new Mesero();
        mesero.setId(1);
        mesero.setNombre("Andres Camacho");

        List<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero);
        meseros.add(mesero);


        Pedido pedido = new Pedido();
        pedidos = new ArrayList<>();

        pedido.setId(1);
        pedido.setNombre_cliente("Javier Sarango");
        pedido.setTotal((float) 7.5);
        pedido.setMesa("Mesa 5");
        pedido.setMesero(meseros);
        pedido.setProducto(productos);

        pedidos.add(pedido);
        pedidos.add(pedido);


    }
    public  PedidoAdapter(List<Pedido> pedidos){
        super();
        this.pedidos=pedidos;
    }

    public  PedidoAdapter(Pedido pedido){
        super();
        pedidos.add(pedido);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pedido,viewGroup,false);
        context = viewGroup.getContext();
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Pedido pedido = pedidos.get(i);
        String mesero = "NN";

        viewHolder.nombre_cliente.setText(pedido.getNombre_cliente());
        viewHolder.mesa.setText(pedido.getMesa());
        try {
            mesero = pedido.getMesero().get(0).getNombre();
        }catch (Exception e){}
        viewHolder.mesero.setText(mesero);
        viewHolder.total.setText(""+pedido.getTotal());

    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre_cliente;
        public TextView mesa;
        public TextView mesero;
        public TextView total;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_cliente = itemView.findViewById(R.id.tv_persona);
            mesa = itemView.findViewById(R.id.tv_mesa);
            mesero = itemView.findViewById(R.id.tv_mesero);
            total = itemView.findViewById(R.id.tv_total);

        }
    }
}
