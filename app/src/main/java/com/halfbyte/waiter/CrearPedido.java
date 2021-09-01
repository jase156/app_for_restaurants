package com.halfbyte.waiter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.halfbyte.waiter.Adaptador.ProductoAdapter;
import com.halfbyte.waiter.Modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CrearPedido extends AppCompatActivity {

    int request_code = 1;

    List<FloatingActionButton> categorias;
    private Spinner mesa;

    //Categorias
    ArrayList<? extends Categoria> categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);

        //Adaptador Grid
        GridView gridView = findViewById(R.id.gridProductoPedido);
        final ProductoAdapter productoAdapter = new ProductoAdapter(this);
        gridView.setAdapter(productoAdapter);

        gridView.setOnItemClickListener(new GridView. OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.print(1);
            }
        });

        //Categorias

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        FloatingActionButton action = new FloatingActionButton(getBaseContext());



        categorias = new ArrayList<FloatingActionButton>();
        categoria = getIntent().getParcelableArrayListExtra("categorias");

        for (int i=0; i<categoria.size(); i++ ){
            action.setIcon(R.drawable.ic_add_black_24dp);
            action.setTitle(categoria.get(i).getNombre());
            final int j =i;
            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CategoriaProducto.class);
                    intent.putExtra("categoria",categoria.get(j));
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivityForResult(intent,request_code);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });
            menuMultipleActions.addButton(action);
            action = new FloatingActionButton(getBaseContext());

        }

        //Combobox
        List<String> mesas = new ArrayList<String>();
        Bundle bundle = getIntent().getExtras();
        int nMesas = bundle.getInt("mesas");
        for(int i=1; i<=nMesas;i++){
            mesas.add("Mesa "+i);
        }
        ArrayAdapter<String> mesaAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mesas);
        mesa = findViewById(R.id.cb_mesas);
        mesa.setAdapter(mesaAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(1==1){

        }
    }

}
