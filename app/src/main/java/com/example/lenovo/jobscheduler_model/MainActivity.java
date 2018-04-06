package com.example.lenovo.jobscheduler_model;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private JobScheduler mjobSceduler;
    private JobInfo jobInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView btn_start=(ImageView)findViewById(R.id.btn_start_main);
        final ImageView btn_stop=(ImageView)findViewById(R.id.btn_stop_main);



        JobServices jobServices = new JobServices();
        ComponentName componentName = new ComponentName(this,JobServices.class);

        JobInfo.Builder builder = new JobInfo.Builder(11,componentName);

        builder.setPeriodic(10000);
        builder.setPersisted(true);

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        jobInfo=builder.build();

        mjobSceduler=(JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mjobSceduler.schedule(jobInfo);



                Toast.makeText(getApplicationContext(),"job scheduled ...",Toast.LENGTH_SHORT).show();


                btn_start.setVisibility(View.GONE);
                btn_stop.setVisibility(View.VISIBLE);

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mjobSceduler.cancelAll();
                Toast.makeText(getApplicationContext(),"job cancelled ...",Toast.LENGTH_SHORT).show();
                btn_start.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.GONE);

            }
        });


    }
}
