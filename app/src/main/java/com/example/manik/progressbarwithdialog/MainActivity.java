package com.example.manik.progressbarwithdialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hp on 11-02-2018.
 */


public  class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    CharSequence[] values = {"ABC", "BCD", "DEF"};
    Boolean[] itemchecked = new Boolean[values.length];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b2 = (Button) findViewById(R.id.b12);
    }


    public void push2(View v) {
        progressBar = new ProgressDialog(v.getContext());
        // progressBar.setIcon(R.drawable.lpu);
        progressBar.setCancelable(true);
        progressBar.setMessage("File downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        fileSize = 0;
        //Thread t =new Thread();interface is runnable
        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {


                    progressBarStatus = doSomeTasks();


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if (progressBarStatus >= 100) {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }

            }

        }).start();

    }


    public int doSomeTasks() {

        while (fileSize <= 1000000) {

            fileSize++;

            if (fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            }
            // ...add your own

        }

        return 100;

    }



}

