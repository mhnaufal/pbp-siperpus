package com.example.pbpsiperpus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        Button buttonBuatAnggota = findViewById(R.id.buttonBuatAnggota);
        Button buttonRefresh = findViewById(R.id.buttonRefresh);

        buttonBuatAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAnggotaActivity.class);
                startActivity(intent);
            }
        });

        buttonRefresh.setOnClickListener(callGetAnggota());

        callGetAnggota();
    }

    private View.OnClickListener callGetAnggota() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pbp-siperpus.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Anggota>> getAnggota = api.getAnggota();
        getAnggota.enqueue(new Callback<List<Anggota>>() {
            @Override
            public void onResponse(@NonNull Call<List<Anggota>> call, @NonNull Response<List<Anggota>> response) {
                if (!response.isSuccessful()) {
                    textResult.setText(String.format("Code: %d", response.code()));
                    return;
                }

                List<Anggota> anggotas = response.body();

                for (Anggota anggota : anggotas) {
                    String content = "";
                    content = content + "NIM: " + anggota.getId() + "\n";
                    content = content + "Nama: " + anggota.getNama() + "\n";
                    content = content + "Password: " + anggota.getPassword() + "\n";
                    content = content + "Alamat: " + anggota.getAlamat() + "\n";
                    content = content + "Kota: " + anggota.getKota() + "\n";
                    content = content + "Email: " + anggota.getEmail() + "\n";
                    content = content + "Telepon: " + anggota.getTelp() + "\n";

                    textResult.append(content);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Anggota>> call, @NonNull Throwable t) {
                Log.d("coba", t.toString());
                textResult.setText(t.getMessage());
            }
        });
        return null;
    }
}