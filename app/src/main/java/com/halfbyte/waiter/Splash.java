package com.halfbyte.waiter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.halfbyte.waiter.Modelo.Pedido;
import com.halfbyte.waiter.Rest.Servicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splash extends AppCompatActivity {
    List<Pedido> pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Comprobación logeo
        //BaseDatos bd = Room.databaseBuilder(getApplicationContext(),
        //        BaseDatos.class, "Waiter").build();

        //String nombre =  bd.localDao().getLocal().getNombre();



        final Intent intent = new Intent(this, MainActivity.class);
        pedidos = new ArrayList<Pedido>();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Servicio.API_ROUTE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Servicio service = retrofit.create(Servicio.class);
        Call<List<Pedido>> requestPedidos = service.getPedidos();

        requestPedidos.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(!response.isSuccessful()){
                    Log.i("Pedidos","Error: "+response.code());
                }else{
                    for(Pedido pedido : response.body()){
                        pedido.setMesa("Mesa "+pedido.getMesa());
                        pedidos.add(pedido);
                    }
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.putParcelableArrayListExtra("pedidos", (ArrayList<? extends Parcelable>) pedidos);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("Pedidos","Error:");
            }
        });

        //Intent intent = new Intent(this, MainActivity.class);
        //intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //startActivity(intent);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        //finish();


    }
}
