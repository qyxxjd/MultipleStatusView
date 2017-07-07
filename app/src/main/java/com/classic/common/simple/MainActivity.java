package com.classic.common.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
        findViewById(R.id.main_simple).setOnClickListener(this);
        findViewById(R.id.main_simple2).setOnClickListener(this);
        findViewById(R.id.main_custom).setOnClickListener(this);
        findViewById(R.id.main_list).setOnClickListener(this);
        findViewById(R.id.main_refresh).setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_simple:
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.main_simple2:
                startActivity(new Intent(this, Simple2Activity.class));
                break;
            case R.id.main_custom:
                startActivity(new Intent(this, CustomActivity.class));
                break;
            case R.id.main_list:
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.main_refresh:
                startActivity(new Intent(this, RefreshActivity.class));
                break;
        }
    }
}
