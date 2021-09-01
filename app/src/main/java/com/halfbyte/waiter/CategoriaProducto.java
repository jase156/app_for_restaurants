package com.halfbyte.waiter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.halfbyte.waiter.Adaptador.ProductoAdapter;
import com.halfbyte.waiter.Modelo.Categoria;
import com.halfbyte.waiter.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProducto extends AppCompatActivity {

    private TextView busqueda;
    ProductoAdapter productoAdapter;
    private List<Producto> seleccionados;
    Producto seleccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);



        //Categoria
        Bundle bundle = getIntent().getExtras();
        Categoria categoria = (Categoria) bundle.get("categoria");

        busqueda = findViewById(R.id.txvBuscar);
        Toolbar toolbar = findViewById(R.id.toolbarProducto);
        toolbar.setTitle(categoria.getNombre());
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("agregados", (ArrayList<? extends Parcelable>) seleccionados);
                setResult(RESULT_OK,intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        //Producto seleccion
        seleccionados = new ArrayList<>();


        //Adaptador Grid


        final GridView gridView = findViewById(R.id.gridCategoriaProducto);
        productoAdapter = new ProductoAdapter(this);
        gridView.setAdapter(productoAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                seleccion = (Producto) gridView.getAdapter().getItem(i);
                if(!seleccionados.contains(seleccion)){
                    seleccionados.add(seleccion);
                    view.setBackgroundResource(R.color.colorAccent);
                }else{
                    view.setBackgroundResource(R.color.icons);
                    seleccionados.remove(seleccion);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_producto, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint( getText(R.string.app_name));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(CategoriaProducto.this, "Buscando", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                productoAdapter.getFilter().filter(query);
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                busqueda.setText(newText);
                productoAdapter.getFilter().filter(newText);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
