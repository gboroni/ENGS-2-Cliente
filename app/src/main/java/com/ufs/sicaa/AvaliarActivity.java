package com.ufs.sicaa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ufs.sicaa.adapter.AvaliacaoAdapter;
import com.ufs.sicaa.model.Criterio;
import com.ufs.sicaa.request.ApresentacoesServiceWS;
import com.ufs.sicaa.ws.IServiceApresentacoesListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvaliarActivity extends AppCompatActivity implements IServiceApresentacoesListener {

    private ListView listView;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progress = ProgressDialog.show(this, "",
                "Carregando...", false);
        new ApresentacoesServiceWS(this).execute("6");


        listView = (ListView) findViewById(R.id.listView);



    }

    @Override
    public void onPostExecuteFinish(String result) {
        try {
            JSONObject j = new JSONObject(result);


            List<Criterio> criterios = new ArrayList<>();

            JSONArray jsonArray = j.getJSONArray("result");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String descricao_criterio = jsonObject.optString("descricao_criterio", "");
                int id = jsonObject.optInt("id", 0);
                int id_criterio = jsonObject.optInt("id_criterio", 0);
                int id_apresentacao = jsonObject.optInt("id_apresentacao", 0);
                int peso_criterio = jsonObject.optInt("peso_criterio", 0);

               criterios.add( new Criterio(id, id_criterio, id_apresentacao, descricao_criterio, peso_criterio));

            }
            listView.setAdapter(new AvaliacaoAdapter(AvaliarActivity.this, R.layout.row_avaliacao, criterios));
            progress.dismiss();
        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }


    }
}
