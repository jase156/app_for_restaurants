package com.halfbyte.waiter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.halfbyte.waiter.Modelo.Local;
import com.halfbyte.waiter.Modelo.Usuario;
import com.halfbyte.waiter.Rest.Servicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private EditText usuario;
    private EditText password;
    private Button btnIngresar;
    private TextView tv_alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        btnIngresar = findViewById(R.id.bt_ingresar);
        tv_alerta = findViewById(R.id.tv_alerta);

        //Consumo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Servicio.API_ROUTE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Servicio service = retrofit.create(Servicio.class);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usuario.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    Call<Usuario> login = service.logear(usuario.getText().toString(),password.getText().toString());
                    login.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response){
                            Usuario usuario = response.body();
                            Call<Local> local = service.getLocal(usuario.getId_local());
                            local.enqueue(new Callback<Local>() {
                                @Override
                                public void onResponse(Call<Local> call, Response<Local> response) {
                                    Local local = response.body();
                                    String imagen = local.getImagen().split(",")[1];
                                    byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                }
                                @Override
                                public void onFailure(Call<Local> call, Throwable t) {
                                    tv_alerta.setText(R.string.error_logeo);
                                }
                            });
                        }
                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            tv_alerta.setText(R.string.error_logeo);
                        }
                    });
                }
            }
        });

    }

}
