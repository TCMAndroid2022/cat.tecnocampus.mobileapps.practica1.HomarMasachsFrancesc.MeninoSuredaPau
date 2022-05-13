package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView llistaExpositors;
    String[] expositors, tipologia, nStand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llistaExpositors = findViewById(R.id.llistaExpositors);
        expositors = getResources().getStringArray(R.array.expositors);
        tipologia = getResources().getStringArray(R.array.tipologia);
        nStand = getResources().getStringArray(R.array.nStand);

        Adaptador adaptador = new Adaptador(this, expositors, tipologia, nStand);
        llistaExpositors.setAdapter(adaptador);
        llistaExpositors.setLayoutManager(new LinearLayoutManager(this));
    }
}