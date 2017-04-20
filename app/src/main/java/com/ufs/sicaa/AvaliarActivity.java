package com.ufs.sicaa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ufs.sicaa.adapter.AlunoAdapter;
import com.ufs.sicaa.adapter.AvaliacaoAdapter;
import com.ufs.sicaa.model.Aluno;
import com.ufs.sicaa.model.Criterio;
import com.ufs.sicaa.request.ApresentacoesServiceWS;
import com.ufs.sicaa.util.Alert;
import com.ufs.sicaa.util.Singleton;
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

        setTitle("Apresentadores");
        progress = ProgressDialog.show(this, "",
                "Carregando...", false);
        new ApresentacoesServiceWS(this).execute(Singleton.getInstance().getCodigoApresentacao());


        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Singleton.getInstance().setAlunoAvaliado(Singleton.getInstance().getAlunos().get(position));
                Intent i = new Intent (AvaliarActivity.this, CriteriosActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onPostExecuteFinish(String result) {
        try {


            if (result.equals("")){
                Alert.showAlertCloseActivity("Erro","Falha na comunicação com o servidor, verifique sua conexão.",AvaliarActivity.this,AvaliarActivity.this);
                progress.dismiss();
                return;
            }

            Log.e(">>>>>>>>>>>>>>>>>>",result);
            JSONObject j = new JSONObject(result);

            Integer erro = j.optInt("erro",0);

            if (erro != 0){
                String msg = j.optString("mensagem","Falha ao recuperar apresentação");
                Alert.showAlertCloseActivity("Erro",msg,this,this);
            }

            List<Criterio> criterios = new ArrayList<>();

            List<Aluno> alunos = new ArrayList<>();

            JSONArray jsonArray = j.getJSONArray("criterios");

            JSONArray jsonArrayAlunos = j.getJSONArray("alunos");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String descricao_criterio = jsonObject.optString("descricao_criterio", "");
                int id = jsonObject.optInt("id", 0);
                int id_criterio = jsonObject.optInt("id_criterio", 0);
                int id_apresentacao = jsonObject.optInt("id_apresentacao", 0);
                int peso_criterio = jsonObject.optInt("peso_criterio", 0);

               criterios.add( new Criterio(id, id_criterio, id_apresentacao, descricao_criterio, peso_criterio));

            }

            for (int i = 0; i < jsonArrayAlunos.length(); i++) {
                JSONObject jsonObject = jsonArrayAlunos.getJSONObject(i);

                int id = jsonObject.optInt("id", 0);
                int id_aluno = jsonObject.optInt("id_aluno", 0);
                int id_turma = jsonObject.optInt("id_turma", 0);
                int id_apresentacao = jsonObject.optInt("id_apresentacao", 0);
                String matricula_aluno = jsonObject.optString("matricula_aluno", "");
                String nome_aluno = jsonObject.optString("nome_aluno", "");
                String codigo_turma = jsonObject.optString("codigo_turma", "");

                alunos.add( new Aluno( id , id_aluno, id_turma, id_apresentacao, matricula_aluno, nome_aluno, codigo_turma));

            }

            if (alunos.isEmpty()){
                Alert.showAlertCloseActivity("","Os apresentadores ainda não foram cadastrados nesta apresentação, informe seu professor!",this,this);
            }

            Singleton.getInstance().setCriterios(criterios);
            Singleton.getInstance().setAlunos(alunos);

            listView.setAdapter(new AlunoAdapter(AvaliarActivity.this, R.layout.row_aluno, alunos));
//            listView.setAdapter(new AvaliacaoAdapter(AvaliarActivity.this, R.layout.row_avaliacao, criterios));
            progress.dismiss();
        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }


    }
}
