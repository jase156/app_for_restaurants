package com.halfbyte.waiter.Rest;


import com.halfbyte.waiter.Modelo.Local;
import com.halfbyte.waiter.Modelo.Mesero;
import com.halfbyte.waiter.Modelo.Pedido;
import com.halfbyte.waiter.Modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Servicio {
    String API_ROUTE = "https://waiterhb.azurewebsites.net/";

    @POST("usuarios")
    @FormUrlEncoded
    Call<Usuario> logear(@Field("usuario") String usuario,
                         @Field("password") String password);

    @GET("local/{id}")
    Call<Local> getLocal(@Path("id") int id);

    @GET("pedido/listar_activos/2")
    Call<List<Pedido>> getPedidos();

    @GET("empleado/{id}")
    Call<Mesero> getMesero(@Path("id") int id);

    @GET("empleado")
    Call<List<Mesero>> getMeseros();
}
