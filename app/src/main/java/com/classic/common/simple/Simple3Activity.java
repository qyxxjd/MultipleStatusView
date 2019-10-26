package com.classic.common.simple;

import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;

public class Simple3Activity extends AbsActivity {

    @Override
    void initView() {
        setContentView(R.layout.activity_simple3);
        setTitle("Simple3Activity");

        ViewGroup ll_content = findViewById(R.id.ll_content);
        mMultipleStatusView = MultipleStatusView.attach(ll_content);
        if (null != mMultipleStatusView) {
            mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);
        }
    }
}
