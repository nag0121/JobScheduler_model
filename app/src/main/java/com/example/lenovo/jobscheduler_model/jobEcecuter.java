package com.example.lenovo.jobscheduler_model;

import android.net.Uri;
import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.net.wifi.WifiConfiguration.Status.strings;

/**
 * Created by LENOVO on 23-03-2018.
 */

public class jobEcecuter extends AsyncTask<Void,Void,String> {

    private static final String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1%22";

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder builder_root = new StringBuilder();
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection=(HttpURLConnection) url1.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(1000);

//            urlConnection.addRequestProperty("Uid","25");
//            Uri.Builder builder=new Uri.Builder();
//
//            builder.appendQueryParameter("Uid","25");
//
//            String query = builder.build().getQuery();

//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
//            BufferedWriter writer=new BufferedWriter(outputStreamWriter);
//            writer.write(query);
//            writer.flush();
//            writer.close();
//            outputStreamWriter.close();

            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line="";
            StringBuilder builder_temp=new StringBuilder();
            while((line=br.readLine())!=null)
            {
                builder_temp.append(line);
            }
            builder_root.append(builder_temp.toString());

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return builder_root.toString();
    }
}
