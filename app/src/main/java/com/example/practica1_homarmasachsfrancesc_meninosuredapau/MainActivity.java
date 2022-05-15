package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adaptador.OnNoteListener {
    static Adaptador adaptador;
    RecyclerView llistaExpositors;
    List<Expositor> expositors;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        llistaExpositors = findViewById(R.id.llistaExpositors);
        expositors = new ArrayList<Expositor>();
        expositors.add(new Expositor("Expositor1", "Random Description", "1", "111222333", "111222333", "12345"));
        expositors.add(new Expositor("Expositor2", "Random Description", "2", "111222333", "111222333", "12345"));

        gridLayoutManager = new GridLayoutManager(this,2);

        if (adaptador==null){
            adaptador = new Adaptador(this, expositors, this);
        }
        llistaExpositors.setAdapter(adaptador);

        llistaExpositors.setLayoutManager(gridLayoutManager);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            gridLayoutManager.setSpanCount(2);
        }else{
            gridLayoutManager.setSpanCount(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void openForm(MenuItem item) {
        Intent intent = new Intent(this, FormActivity.class);
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Expositor nouExpo = new Expositor(data.getStringExtra("empresa"), data.getStringExtra("tipologia"), data.getStringExtra("nstand"), data.getStringExtra("telefon"), data.getStringExtra("nif"), data.getStringExtra("coordenades"));
                        expositors.add(nouExpo);
                        adaptador.notifyItemInserted(expositors.size()-1);
                    }
                }
            });


    @Override
    public void onNoteClick(int position) {
        expositors.get(position);
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("expositor",expositors.get(position).getExpositors());
        intent.putExtra("tipologia",expositors.get(position).getTipologia());
        intent.putExtra("nStand",expositors.get(position).getnStand());
        intent.putExtra("telefon",expositors.get(position).getTelefon());
        intent.putExtra("nif",expositors.get(position).getNif());
        intent.putExtra("coordenades",expositors.get(position).getCoordenades());
        intent.putExtra("position", position);
        editExpositorActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> editExpositorActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Expositor nouExpo = new Expositor(data.getStringExtra("empresa"), data.getStringExtra("tipologia"), data.getStringExtra("nstand"), data.getStringExtra("telefon"), data.getStringExtra("nif"), data.getStringExtra("coordenades"));
                        expositors.set(data.getIntExtra("position",999), nouExpo);
                        adaptador.notifyItemChanged(expositors.size()-1);
                        llistaExpositors.setAdapter(adaptador);
                    }
                }
            });
}