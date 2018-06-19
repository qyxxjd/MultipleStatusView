package com.classic.common.simple;

public class Simple2Activity extends AbsActivity {

    @Override void initView() {
        setContentView(R.layout.activity_simple2);
        setTitle("Simple2Activity");
        mMultipleStatusView = findViewById(R.id.simple2_multiple_status_view);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);

        loading();
    }
}
