package com.ufs.sicaa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ufs.sicaa.adapter.AvaliacaoAdapter;
import com.ufs.sicaa.model.Criterio;

import java.util.ArrayList;
import java.util.List;

public class AvaliarActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        List<Criterio> criterios = new ArrayList<>();

        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        criterios.add(new Criterio("nome criterio",0,4.2));
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new AvaliacaoAdapter(AvaliarActivity.this, R.layout.row_avaliacao, criterios));

    }

}
