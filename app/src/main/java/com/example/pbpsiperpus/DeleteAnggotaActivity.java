package com.example.pbpsiperpus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteAnggotaActivity extends AppCompatActivity {

    private EditText nim;
    private Button deleteDataBtn;
    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_anggota);

        this.nim = findViewById(R.id.nim);
        this.deleteDataBtn = findViewById(R.id.idBtnDelete);
        this.loadingPB = findViewById(R.id.idLoadingPB2);
        this.responseTV = findViewById(R.id.idTVResponse2);

        deleteDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nim.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteAnggotaActivity.this, "Silakan isi nim dengan lengkap!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                deleteAnggota(nim.getText().toString());
            }
        });
    }

    private void deleteAnggota(String nim) {
        loadingPB.setVisibility(View.VISIBLE);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pbp-siperpus.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<Void> deleteAnggota = api.deleteAnggota(nim);

        deleteAnggota.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
//                Log.d("coba", response.body().toString());
                loadingPB.setVisibility(View.GONE);
                String responseData = "Response Code: " + response.code();
                responseTV.setText(responseData);
                Toast.makeText(DeleteAnggotaActivity.this, "Anggota berhasil dihapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d("coba", t.toString());
                responseTV.setText(String.format("Error found is : %s", t.getMessage()));
            }
        });
    }
}
