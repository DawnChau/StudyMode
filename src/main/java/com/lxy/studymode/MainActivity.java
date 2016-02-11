package com.lxy.studymode;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {

    private Button startButton;
    private Button endButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start);
        endButton = (Button) findViewById(R.id.end);


        //开始监视按钮的响应事件
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent();
                service.setClass(MainActivity.this, MonitorService.class);
                startService(service);
            }
        });

        //结束监视按钮的响应事件
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent();
                service.setClass(MainActivity.this, MonitorService.class);
                stopService(service);
            }
        });
    }
}
