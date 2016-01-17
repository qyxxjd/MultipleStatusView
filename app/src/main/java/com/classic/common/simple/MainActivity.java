package com.classic.common.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.classic.common.MultipleStatusView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MultipleStatusView multipleStatusView;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton loadingFab;
    private FloatingActionButton emptyFab;
    private FloatingActionButton errorFab;
    private FloatingActionButton noNetworkFab;
    private FloatingActionButton contentFab;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.main_fab_menu);
        loadingFab = (FloatingActionButton) findViewById(R.id.main_fab_loading);
        emptyFab = (FloatingActionButton) findViewById(R.id.main_fab_empty);
        errorFab = (FloatingActionButton) findViewById(R.id.main_fab_error);
        noNetworkFab = (FloatingActionButton) findViewById(R.id.main_fab_no_network);
        contentFab = (FloatingActionButton) findViewById(R.id.main_fab_content);
        loadingFab.setOnClickListener(this);
        emptyFab.setOnClickListener(this);
        errorFab.setOnClickListener(this);
        noNetworkFab.setOnClickListener(this);
        contentFab.setOnClickListener(this);

        multipleStatusView.setOnRetryClickListener(onRetryClickListener);
        multipleStatusView.showLoading();
    }

    private final View.OnClickListener onRetryClickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"您点击了重试视图",Toast.LENGTH_SHORT).show();
            multipleStatusView.showLoading();
        }
    };

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab_loading:
                multipleStatusView.showLoading();
                break;
            case R.id.main_fab_empty:
                multipleStatusView.showEmpty();
                break;
            case R.id.main_fab_error:
                multipleStatusView.showError();
                break;
            case R.id.main_fab_no_network:
                multipleStatusView.showNoNetwork();
                break;
            case R.id.main_fab_content:
                multipleStatusView.showContent();
                break;
        }
        floatingActionMenu.toggle(false);
    }
}
