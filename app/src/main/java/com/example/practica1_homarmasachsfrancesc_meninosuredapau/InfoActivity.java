package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    TextView expositor, tipologia, nStand, telefon, nif, coordenades;

    String sExpositor, sTipologia, sNStand, sTelefon, sNif, sCoordenades;
    boolean editedForm = false;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        expositor = findViewById(R.id.nom_empresa);
        tipologia = findViewById(R.id.tipologia);
        nStand = findViewById(R.id.num_parada);
        telefon = findViewById(R.id.telefon);
        nif = findViewById(R.id.nif);
        coordenades = findViewById(R.id.coordenades);

        getData();
        setData();
    }

    public boolean onSupportNavigateUp() {
        if(editedForm){
            Intent intent = new Intent();
            intent.putExtra("empresa", expositor.getText().toString());
            intent.putExtra("tipologia", tipologia.getText().toString());
            intent.putExtra("nif", nif.getText().toString());
            intent.putExtra("telefon", telefon.getText().toString());
            intent.putExtra("nstand", nStand.getText().toString());
            intent.putExtra("coordenades", coordenades.getText().toString());
            intent.putExtra("position", position);
            setResult(RESULT_OK, intent);
        }
        finish();
        return true;
    }

    private void getData(){
        if(getIntent().hasExtra("expositor") && getIntent().hasExtra("tipologia")&& getIntent().hasExtra("nStand")&& getIntent().hasExtra("telefon")&& getIntent().hasExtra("nif")&& getIntent().hasExtra("coordenades")){

            sExpositor = getIntent().getStringExtra("expositor");
            sTipologia = getIntent().getStringExtra("tipologia");
            sNStand = getIntent().getStringExtra("nStand");
            sTelefon = getIntent().getStringExtra("telefon");
            sNif = getIntent().getStringExtra("nif");
            sCoordenades = getIntent().getStringExtra("coordenades");
            position = getIntent().getIntExtra("position", 9999);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        expositor.setText(sExpositor);
        tipologia.setText(sTipologia);
        nStand.setText(sNStand);
        telefon.setText(sTelefon);
        nif.setText(sNif);
        coordenades.setText(sCoordenades);
    }

    public void clickButtonMaps(View view) {

        try {
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+sCoordenades);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }catch(ActivityNotFoundException e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickButtonTrucada(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+sTelefon));
            startActivity(intent);
        }catch(ActivityNotFoundException e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbarinfo, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void editForm(MenuItem item) {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("empresa", expositor.getText().toString());
        intent.putExtra("tipologia", tipologia.getText().toString());
        intent.putExtra("nif", nif.getText().toString());
        intent.putExtra("telefon", telefon.getText().toString());
        intent.putExtra("nstand", nStand.getText().toString());
        intent.putExtra("coordenades", coordenades.getText().toString());
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
                        sExpositor = data.getStringExtra("empresa");
                        sTipologia = data.getStringExtra("tipologia");
                        sNif = data.getStringExtra("nif");
                        sTelefon = data.getStringExtra("telefon");
                        sNStand = data.getStringExtra("nstand");
                        sCoordenades = data.getStringExtra("coordenades");
                        editedForm=true;
                        setData();
                    }
                }
            });
}