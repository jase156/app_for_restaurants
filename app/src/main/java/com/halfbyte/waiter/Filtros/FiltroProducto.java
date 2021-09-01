package com.halfbyte.waiter.Filtros;

import android.widget.Filter;

import com.halfbyte.waiter.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class FiltroProducto extends Filter {

    final List<Producto> lista;

    public FiltroProducto(List<Producto> lista) {
        this.lista = lista;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String filtro = charSequence.toString().toLowerCase();
        FilterResults resultados = new FilterResults();

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

    }
}
