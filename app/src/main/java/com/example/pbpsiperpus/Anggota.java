package com.example.pbpsiperpus;

import com.google.gson.annotations.SerializedName;

public class Anggota {
    private String id;
    private String nama;
    private String password;
    private String alamat;
    private String kota;
    private String email;

    @SerializedName("no_telp")
    private String telp;

    public Anggota(String id, String nama, String password, String alamat, String kota, String email, String telp) {
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.alamat = alamat;
        this.kota = kota;
        this.email = email;
        this.telp = telp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKota() {
        return kota;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
