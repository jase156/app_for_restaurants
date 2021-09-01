package com.halfbyte.waiter.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfbyte.waiter.Modelo.Producto;
import com.halfbyte.waiter.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private List<Producto> productos;
    private final List<Producto> copiaProductos;
    private FiltroProducto mFilter = new FiltroProducto();

    public ProductoAdapter(Context context){
        this.context = context;


        productos = new ArrayList<Producto>();

        Producto pro1 = new Producto();
        pro1.setId(1);
        pro1.setNombre("Cafe expreso");
        pro1.setPrecio(7.5);

        Producto pro2 = new Producto();
        pro2.setId(2);
        pro2.setNombre("Cafe late");
        pro2.setPrecio(7.5);

        Producto pro3 = new Producto();
        pro3.setId(3);
        pro3.setNombre("Cafe late doble");
        pro3.setPrecio(7.5);

        productos.add(pro1);
        productos.add(pro2);
        productos.add(pro3);

        copiaProductos = productos;

    }

    public ProductoAdapter(Context context, List<Producto> productos){
        this.productos = productos;
        this.copiaProductos = productos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Producto producto = productos.get(i);

        if(view==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.agregar_producto,null);
            int screenHeight = ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getHeight();
            int screenWidth = ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getWidth();
            view.setLayoutParams(new ViewGroup.LayoutParams(screenWidth/2-30,screenHeight/4));
        }

        final ImageView imageProducto = view.findViewById(R.id.img_producto);
        final TextView nombreProducto = view.findViewById(R.id.tv_nombre_producto);
        final TextView cantidadProducto = view.findViewById(R.id.tv_cantidad);
        final TextView removeProducto = view.findViewById(R.id.remove_producto);
        final TextView addProducto = view.findViewById(R.id.add_producto);



        imageProducto.setImageResource(R.mipmap.ic_cafe);
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(""+producto.getCantidad());
        removeProducto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidad = producto.getCantidad();
                cantidad--;
                producto.setCantidad(cantidad);
                cantidadProducto.setText(""+cantidad);
                View parentRow = (View)v.getParent().getParent();
                GridView gridView = (GridView)parentRow.getParent();
                final int position = gridView.getPositionForView(parentRow);
            }
        });

        addProducto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidad = producto.getCantidad();
                cantidad++;
                producto.setCantidad(cantidad);
                cantidadProducto.setText(""+cantidad);
            }
        });

        return view;
    }


    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class FiltroProducto extends Filter {



        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filtro = charSequence.toString().toLowerCase();
            FilterResults resultados = new FilterResults();

            final List<Producto> lista = copiaProductos;

            int count = lista.size();
            final List<Producto> resultado = new ArrayList<>();

            for(int i=0;i<count;i++){
                if(lista.get(i).getNombre().toLowerCase().contains(filtro))
                    resultado.add(lista.get(i));
            }

            resultados.values = resultado;
            resultados.count = resultado.size();

            return resultados;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productos = (List<Producto>) filterResults.values;
            notifyDataSetChanged();

        }
    }

}
