package com.ufs.sicaa.request;

import android.net.Uri;
import android.os.AsyncTask;

import com.ufs.sicaa.util.Constants;
import com.ufs.sicaa.ws.IServiceAsyncListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by boroni on 07/11/16.
 */


public class LoginServiceWS extends AsyncTask<String, Void, String> {

    private IServiceAsyncListener listenerResponse;

    public LoginServiceWS(IServiceAsyncListener listenerResponse){
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
                String url_s = Constants.URL_LOGIN  ;
                url_s += params[0];
                url = new URL(url_s);


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
               // conn.setRequestProperty("Accept", "application/json");
                conn.setDoInput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("user", params[0])
                        .appendQueryParameter("pass", params[1]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();


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
