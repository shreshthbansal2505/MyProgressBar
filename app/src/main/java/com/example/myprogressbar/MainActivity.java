package com.example.myprogressbar;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
private Button btn1;
   private ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd  = new ProgressDialog(v.getContext());
                pd.setMessage("Downloading");
                pd.setTitle("Download");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setIndeterminate(false);
                pd.setMax(100);

                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (pd.getProgress()<=pd.getMax()){
                                Thread.sleep(200);
                                handler.sendMessage(handler.obtainMessage());
                                    if(pd.getProgress()==pd.getMax()){
                                        pd.dismiss();
                                    }

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    pd.incrementProgressBy(1);
                }
            };

        });

    }
}
