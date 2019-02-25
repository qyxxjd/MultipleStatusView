package com.classic.common.simple;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public abstract class AbsActivity extends AppCompatActivity implements View.OnClickListener {

    static final int DELAY = 5000;

    MultipleStatusView   mMultipleStatusView;
    FloatingActionMenu   mFloatingActionMenu;
    FloatingActionButton mLoadingFab;
    FloatingActionButton mEmptyFab;
    FloatingActionButton mErrorFab;
    FloatingActionButton mNoNetworkFab;
    FloatingActionButton mContentFab;

    abstract void initView();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) { actionBar.setDisplayHomeAsUpEnabled(true); }

        mFloatingActionMenu = findViewById(R.id.menu);
        mLoadingFab = findViewById(R.id.fab_loading);
        mEmptyFab = findViewById(R.id.fab_empty);
        mErrorFab = findViewById(R.id.fab_error);
        mNoNetworkFab = findViewById(R.id.fab_no_network);
        mContentFab = findViewById(R.id.fab_content);
        mLoadingFab.setOnClickListener(this);
        mEmptyFab.setOnClickListener(this);
        mErrorFab.setOnClickListener(this);
        mNoNetworkFab.setOnClickListener(this);
        mContentFab.setOnClickListener(this);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);
        mMultipleStatusView.setOnViewStatusChangeListener(mViewStatusChangeListener);
    }

    void loading() {
        mMultipleStatusView.showLoading();
        mMultipleStatusView.postDelayed(new Runnable() {
            @Override public void run() {
                mMultipleStatusView.showContent();
            }
        }, DELAY);
    }

    final View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "您点击了重试视图", Toast.LENGTH_SHORT).show();
            loading();
        }
    };

    final MultipleStatusView.OnViewStatusChangeListener mViewStatusChangeListener =
            new MultipleStatusView.OnViewStatusChangeListener() {

        /**
         * 视图状态改变时回调
         *
         * @param oldViewStatus 之前的视图状态
         * @param newViewStatus 新的视图状态
         */
        @Override
        public void onChange(int oldViewStatus, int newViewStatus) {
            Log.d("MultipleStatusView", "oldViewStatus=" + oldViewStatus
                    + ", newViewStatus=" + newViewStatus);
        }
    };

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_loading:
                loading();
                break;
            case R.id.fab_empty:
                mMultipleStatusView.showEmpty();
                break;
            case R.id.fab_error:
                mMultipleStatusView.showError();
                break;
            case R.id.fab_no_network:
                mMultipleStatusView.showNoNetwork();
                break;
            case R.id.fab_content:
                mMultipleStatusView.showContent();
                break;
        }
        mFloatingActionMenu.toggle(false);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
