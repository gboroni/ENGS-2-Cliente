package com.ufs.sicaa.request;

import android.os.AsyncTask;
import android.util.Log;

import com.ufs.sicaa.util.Constants;
import com.ufs.sicaa.util.Singleton;
import com.ufs.sicaa.ws.IServiceAsyncListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by boroni on 07/11/16.
 */


public class AvaliacaoServiceWS extends AsyncTask<String, Void, String> {

    private IServiceAsyncListener listenerResponse;

    public AvaliacaoServiceWS(IServiceAsyncListener listenerResponse){
        this.listenerResponse = listenerResponse;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        InputStream is = null;
        String responseText = "";
            try {
                Singleton sing = Singleton.getInstance();
                String url_s = Constants.URL_AVALIACAO;
                url_s += params[1]+"/";//criterio
                url_s += params[0]+"/";//nota
                url_s += sing.getAlunoAvaliado().getId()+"/";
                url_s += sing.getUsuario().getMatricula()+"/";
                url_s += sing.getAlunoAvaliado().getId_apresentacao();
                url = new URL(url_s);

                Log.e("URL>>>>>>",url_s);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoInput(true);

//                String encoded = Base64.encodeToString((Constants.AUTH_USER+":"+ Constants.AUTH_PASS).getBytes(),Base64.DEFAULT);
//                conn.setRequestProperty("Authorization", "Basic "+encoded);


                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                is = conn.getInputStream();

                // Convert the InputStream into a string
                 responseText = readIt(is);
                return responseText;
            } catch (Exception e) {
                e.printStackTrace();

            }

            return responseText;

    }

    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(reader);
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        this.listenerResponse.onPostExecuteFinish(result);
    }
}
