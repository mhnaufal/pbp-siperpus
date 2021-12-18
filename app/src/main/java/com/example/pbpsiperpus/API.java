package com.example.pbpsiperpus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

//    @GET("/anggota")
//    public void getAnggota(Callback callback);

    @GET("/anggota")
    Call<List<Anggota>> getAnggota();

    @POST("/anggota")
    Call<Anggota> createAnggota(@Body Anggota anggota);
}
