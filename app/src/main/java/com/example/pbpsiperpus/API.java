package com.example.pbpsiperpus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

//    @GET("/anggota")
//    public void getAnggota(Callback callback);

    @GET("/anggota")
    Call<List<Anggota>> getAnggota();

    @POST("/anggota")
    Call<Anggota> createAnggota(@Body Anggota anggota);

//    @PATCH("anggota/{id}")
//    Call<Anggota> patchAnggota(@Path("id")int id, @Body Anggota anggota);
//
//    @PUT("anggota/{id}")
//    Call<Anggota> putAnggota(@Path("id")int id, @Body Anggota anggota);

    @DELETE("/anggota/{nim}")
    Call<Void> deleteAnggota(@Path("nim") String nim);

}
