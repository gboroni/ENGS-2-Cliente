package com.ufs.sicaa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.ufs.sicaa.adapter.AlunoAdapter;
import com.ufs.sicaa.adapter.AvaliacaoAdapter;
import com.ufs.sicaa.model.Aluno;
import com.ufs.sicaa.model.Criterio;
import com.ufs.sicaa.request.AvaliacaoServiceWS;
import com.ufs.sicaa.util.Alert;
import com.ufs.sicaa.util.Singleton;
import com.ufs.sicaa.ws.IServiceApresentacoesListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CriteriosActivity extends AppCompatActivity implements IServiceApresentacoesListener {

    private ListView listView;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Avaliar " + Singleton.getInstance().getAlunoAvaliado().getNome_aluno());

        if (Singleton.getInstance().getCriterios().isEmpty()){
            Alert.showAlertCloseActivity("","Os critérios a serem avaliados ainda não foram cadastrados, informe seu professor!",this,this);
        }

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new AvaliacaoAdapter(CriteriosActivity.this, R.layout.row_avaliacao, Singleton.getInstance().getCriterios()));


    }

    public String getValues(){
        Boolean error = false;
        String result = "";
        for(int i=0;i<Singleton.getInstance().getCriterios().size();i++){
            if (i > 0){
                result += ",";
            }

            View view=listView.getChildAt(i);
            EditText editText = (EditText) view.findViewById(R.id.nota);
            String string = editText.getText().toString();
            result += string;

            if (string.trim().equals("")){
                editText.setError("Informe a nota");
                error = true;
            }else{
                Double nota = Double.valueOf(string);
                if (nota == null){
                    editText.setError("Nota inválida");
                    error = true;
                }else if (nota > 10.0 || nota < 0.0){
                    editText.setError("A nota deve ser entre 0 e 10");
                    error = true;
                }
            }


        }
        return (error) ? "" : result;
    }

    public String getCriteriosID(){
        String result = "";
        for(int i=0;i<Singleton.getInstance().getCriterios().size();i++){
            Criterio c = Singleton.getInstance().getCriterios().get(i);
            if (i > 0){
                result += ",";
            }
            result += c.getId();
        }
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           String notas =  getValues();
            if (!notas.equals("")){
                progress = ProgressDialog.show(this, "",
                        "Carregando...", false);
                new AvaliacaoServiceWS(this).execute(notas,getCriteriosID());

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostExecuteFinish(String result) {
        try {

            if (result.equals("")){
                Alert.showInfoAlert("Erro","Falha na comunicação com o servidor, verifique sua conexão.",CriteriosActivity.this);
                progress.dismiss();
                return;
            }

            Log.e(">>>>>>>>>>>>>>>>>>",result);
            JSONObject j = new JSONObject(result);

            progress.dismiss();

            int erro = j.optInt("erro",0);

            String msg = j.optString("mensagem","");

            if (erro == 0){
                Alert.showAlertCloseActivity("Sucesso",msg, CriteriosActivity.this,CriteriosActivity.this);
            }
        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }

    }
}
