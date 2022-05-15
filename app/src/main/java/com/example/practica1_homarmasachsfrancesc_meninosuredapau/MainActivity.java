package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView llistaExpositors;
    List<Expositor> expositors;
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llistaExpositors = findViewById(R.id.llistaExpositors);
        expositors = new ArrayList<Expositor>();
        expositors.add(new Expositor("Expositor1", "Random Description", "1", "111222333", "111222333", "12345"));
        expositors.add(new Expositor("Expositor2", "Random Description", "2", "111222333", "111222333", "12345"));

        adaptador = new Adaptador(this, expositors);
        llistaExpositors.setAdapter(adaptador);
        llistaExpositors.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void openForm(MenuItem item) {
        Intent intent = new Intent(this, FormActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Expositor nouExpo = new Expositor(data.getStringExtra("empresa"), data.getStringExtra("tipologia"), data.getStringExtra("nstand"), data.getStringExtra("telefon"), data.getStringExtra("nif"), data.getStringExtra("coordenades"));
                        expositors.add(nouExpo);
                        adaptador.notifyItemInserted(expositors.size()-1);
                        llistaExpositors.scrollToPosition(expositors.size()-1);
                    }
                }
            });
}