package com.example.codebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button ScanCodigo;
    EditText LectSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanCodigo= findViewById(R.id.ScanCodigo);
        LectSC= findViewById(R.id.LectSC);

        ScanCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrador= new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("codigo de barra o qr");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result!= null){
            if(result.getContents()== null){
                Toast.makeText(this, "Lector finalizado", Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                LectSC.setText(result.getContents());
            }
        }else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}