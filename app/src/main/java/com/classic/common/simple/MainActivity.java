package com.classic.common.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.classic.common.MultipleStatusView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MultipleStatusView mMultipleStatusView;
    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mLoadingFab;
    private FloatingActionButton mEmptyFab;
    private FloatingActionButton mErrorFab;
    private FloatingActionButton mNoNetworkFab;
    private FloatingActionButton mContentFab;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.main_fab_menu);
        mLoadingFab = (FloatingActionButton) findViewById(R.id.main_fab_loading);
        mEmptyFab = (FloatingActionButton) findViewById(R.id.main_fab_empty);
        mErrorFab = (FloatingActionButton) findViewById(R.id.main_fab_error);
        mNoNetworkFab = (FloatingActionButton) findViewById(R.id.main_fab_no_network);
        mContentFab = (FloatingActionButton) findViewById(R.id.main_fab_content);
        mLoadingFab.setOnClickListener(this);
        mEmptyFab.setOnClickListener(this);
        mErrorFab.setOnClickListener(this);
        mNoNetworkFab.setOnClickListener(this);
        mContentFab.setOnClickListener(this);

        mMultipleStatusView.setOnRetryClickListener(onRetryClickListener);
        mMultipleStatusView.showLoading();
    }

    private final View.OnClickListener onRetryClickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"您点击了重试视图",Toast.LENGTH_SHORT).show();
            mMultipleStatusView.showLoading();
        }
    };

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab_loading:
                mMultipleStatusView.showLoading();
                break;
            case R.id.main_fab_empty:
                mMultipleStatusView.showEmpty();
                break;
            case R.id.main_fab_error:
                mMultipleStatusView.showError();
                break;
            case R.id.main_fab_no_network:
                mMultipleStatusView.showNoNetwork();
                break;
            case R.id.main_fab_content:
                mMultipleStatusView.showContent();
                break;
        }
        mFloatingActionMenu.toggle(false);
    }
}
