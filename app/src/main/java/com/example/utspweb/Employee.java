package com.example.utspweb;

import java.io.Serializable;

public class Employee implements Serializable {
    private int foto;
    private String nama;
    private String nidn;
    private String gender;
    private String keahlian;




    public Employee(int foto, String nama, String nidn, String gender, String keahlian) {
        this.foto = foto;
        this.nama = nama;
        this.nidn = nidn;
        this.gender = gender;
        this.keahlian = keahlian;
    }

    public int getFoto() {
        return foto;
    }
    public String getNama() {
        return nama;
    }

    public String getNIDN() { return nidn;}

    public String getGender() {
        return gender;
    }

    public String getKeahlian() {
        return keahlian;
    }
}



