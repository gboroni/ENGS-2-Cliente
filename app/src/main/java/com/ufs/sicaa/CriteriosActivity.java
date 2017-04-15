package com.ufs.sicaa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ufs.sicaa.adapter.AvaliacaoAdapter;
import com.ufs.sicaa.util.Singleton;

public class CriteriosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new AvaliacaoAdapter(CriteriosActivity.this, R.layout.row_avaliacao, Singleton.getInstance().getCriterios()));



    }

}
