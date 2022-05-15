package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

public class Expositor {
    String expositors, tipologia, nStand, telefon, nif, coordenades;

    public Expositor(String expositors, String tipologia, String nStand, String telefon, String nif, String coordenades) {
        this.expositors = expositors;
        this.tipologia = tipologia;
        this.nStand = nStand;
        this.telefon = telefon;
        this.nif = nif;
        this.coordenades = coordenades;
    }

    public String getExpositors() {
        return expositors;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getnStand() {
        return nStand;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getNif() {
        return nif;
    }

    public String getCoordenades() {
        return coordenades;
    }
}
