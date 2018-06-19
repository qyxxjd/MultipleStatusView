package com.classic.common.simple;

public class SimpleActivity extends AbsActivity {

    @Override void initView() {
        setContentView(R.layout.activity_simple);
        setTitle("SimpleActivity");
        mMultipleStatusView = findViewById(R.id.simple_multiple_status_view);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);

        loading();
    }
}
