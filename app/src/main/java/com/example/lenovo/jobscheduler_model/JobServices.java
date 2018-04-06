package com.example.lenovo.jobscheduler_model;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;

import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 22-03-2018.
 */

public class JobServices extends JobService {

    List<DataProvider> dataProviders = new ArrayList<>();

    private jobEcecuter jb_exec;
    @SuppressLint("StaticFieldLeak")

    public JobServices(){

    }
    @Override
    public boolean onStartJob(final JobParameters params) {

       Toast.makeText(getApplicationContext(),"on start...",Toast.LENGTH_LONG).show();

       jb_exec=new jobEcecuter(){
           @Override
           protected void onPostExecute(String s) {
               super.onPostExecute(s);

               try {
                   JSONObject root_ibject = new JSONObject(s);
                   JSONArray users = root_ibject.getJSONArray("users");
                   for (int i=0;i<users.length();i++){

                       JSONObject object = users.getJSONObject(i);

                       String id = object.getString("id");
                       String name = object.getString("name");
                       String email = object.getString("email");
                       String gender = object.getString("gender");

                       dataProviders.add(new DataProvider(id,name,email,gender));

                       Log.e("id",id);
                       Log.e("name",name);
                       Log.e("email",email);
                       Log.e("gender",gender);

                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
               jobFinished(params,false);

           }
       };

       jb_exec.execute();



        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        jb_exec.cancel(true);
        Toast.makeText(getApplicationContext(),"on stop...",Toast.LENGTH_LONG).show();
        return true;

    }




}

