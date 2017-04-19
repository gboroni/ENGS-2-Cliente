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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.ufs.sicaa.model.Usuario;
import com.ufs.sicaa.request.AlunoServiceWS;
import com.ufs.sicaa.request.ApresentacoesServiceWS;
import com.ufs.sicaa.request.LoginServiceWS;
import com.ufs.sicaa.util.Alert;
import com.ufs.sicaa.util.Constants;
import com.ufs.sicaa.util.Security;
import com.ufs.sicaa.util.Singleton;
import com.ufs.sicaa.ws.IServiceAlunosListener;
import com.ufs.sicaa.ws.IServiceApresentacoesListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements IServiceApresentacoesListener, IServiceAlunosListener {

    private Button login;

    private EditText usuario;
    private EditText senha;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = (Button) findViewById(R.id.entrar);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(MainActivity.this, "",
                        "Carregando...", false);
                new LoginServiceWS(MainActivity.this).execute(Security.encrypt(usuario.getText().toString()),Security.encrypt(senha.getText().toString()));
            }
        });

    }

    @Override
    public void onPostExecuteFinish(String result) {
        try {

            Log.e(">>>>>>>>>>>>>>>>>>",result);
            JSONObject j = new JSONObject(result);

            String erro = j.optString("tag","");

            String msg = j.optString("error","");

            if (erro.toLowerCase().equals("erro".toLowerCase())){
                Alert.showInfoAlert("Erro",msg, MainActivity.this);
                progress.dismiss();
            }else {
                String nome = j.optString("nome","");
                String matricula = j.optString("matricula","");
                Usuario u = new Usuario(0,nome,matricula);
                Singleton.getInstance().setUsuario(u);
                new AlunoServiceWS(MainActivity.this).execute(matricula,nome);
            }
        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }

    }

    @Override
    public void onPostExecuteAlternativeFinish(String result) {
        try {

            if (result.equals("")){
                Alert.showInfoAlert("Erro","Falha na comunicação com o servidor, tente mais tarde.",MainActivity.this);
                progress.dismiss();
                return;
            }

            Log.e(">>>>>>>>>>>>>>>>>>",result);
            JSONObject j = new JSONObject(result);

            progress.dismiss();

            int erro = j.optInt("erro",-1);

           if (erro == 0){
               Intent i = new Intent(MainActivity.this, CodigoActivity.class);
               startActivity(i);
               this.finish();
           }else{
               Alert.showInfoAlert("Erro","Falha ao conectar com o servidor, tente novamente mais tarde.",MainActivity.this);
           }
        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }

    }
}
