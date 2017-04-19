package com.ufs.sicaa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ufs.sicaa.util.Singleton;

public class CodigoActivity extends AppCompatActivity {

    private Button enviar;

    private EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Pesquisar apresentação");
        codigo = (EditText) findViewById(R.id.codigo);

        enviar = (Button) findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codigo.getText().toString().trim().equals("")){
                    codigo.setError("Informe o código da apresentação");
                }else {
                    Singleton.getInstance().setCodigoApresentacao(codigo.getText().toString());
                    Intent i = new Intent(CodigoActivity.this, AvaliarActivity.class);
                    startActivity(i);
                }
            }
        });


    }

}
