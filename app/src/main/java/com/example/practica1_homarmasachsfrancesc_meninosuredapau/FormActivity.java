package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    EditText empresa, tipologia,  telefon, nif, nStand, coordenades;
    boolean formCorrect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empresa = findViewById(R.id.nomEmpresaForm);
        tipologia = findViewById(R.id.tipologiaForm);
        nif = findViewById(R.id.nifForm);
        telefon = findViewById(R.id.telefonForm);
        nStand = findViewById(R.id.nStandForm);
        coordenades = findViewById(R.id.coordenadesForm);
        getData();
    }

    private void getData(){
        if(getIntent().hasExtra("empresa") && getIntent().hasExtra("tipologia")&& getIntent().hasExtra("nstand")&& getIntent().hasExtra("telefon")&& getIntent().hasExtra("nif")&& getIntent().hasExtra("coordenades")){

            empresa.setText(getIntent().getStringExtra("empresa"), TextView.BufferType.EDITABLE);
            tipologia.setText(getIntent().getStringExtra("tipologia"), TextView.BufferType.EDITABLE);
            nif.setText(getIntent().getStringExtra("nif"), TextView.BufferType.EDITABLE);
            telefon.setText(getIntent().getStringExtra("telefon"), TextView.BufferType.EDITABLE);
            nStand.setText(getIntent().getStringExtra("nstand"), TextView.BufferType.EDITABLE);
            coordenades.setText(getIntent().getStringExtra("coordenades"), TextView.BufferType.EDITABLE);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void clickSaveButton(View view) {
        formCorrect = true;
        if (empresa.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }else if (tipologia.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }else if (nif.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }else if (telefon.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }else if (nStand.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }else if (coordenades.getText().toString().equals("")){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            formCorrect = false;
        }
        if (formCorrect){
            Intent intent = new Intent();
            intent.putExtra("empresa", empresa.getText().toString());
            intent.putExtra("tipologia", tipologia.getText().toString());
            intent.putExtra("nif", nif.getText().toString());
            intent.putExtra("telefon", telefon.getText().toString());
            intent.putExtra("nstand", nStand.getText().toString());
            intent.putExtra("coordenades", coordenades.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}