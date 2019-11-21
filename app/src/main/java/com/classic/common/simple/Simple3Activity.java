package com.classic.common.simple;

import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;

public class Simple3Activity extends AbsActivity {

    @Override
    void initView() {
        setContentView(R.layout.activity_simple3);
        setTitle("Simple3Activity");
        ViewGroup ll_content = findViewById(R.id.ll_content);
        mMultipleStatusView = MultipleStatusView.attach(ll_content);
    }

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
}
