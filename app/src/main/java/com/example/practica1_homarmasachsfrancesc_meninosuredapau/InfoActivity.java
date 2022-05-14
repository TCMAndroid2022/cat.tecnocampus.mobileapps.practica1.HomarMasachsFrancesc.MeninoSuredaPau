package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    TextView expositor;

    String empresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        expositor = findViewById(R.id.nom_empresa);

        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("expositor")){

            empresa = getIntent().getStringExtra("expositor");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        expositor.setText(empresa);
    }
}