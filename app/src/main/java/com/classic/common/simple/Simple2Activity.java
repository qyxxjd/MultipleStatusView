package com.classic.common.simple;

import com.classic.common.MultipleStatusView;

public class Simple2Activity extends AbsActivity {

    @Override void initView() {
        setContentView(R.layout.activity_simple2);
        setTitle("Simple2Activity");
        mMultipleStatusView = (MultipleStatusView)findViewById(R.id.simple2_multiple_status_view);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);

        loading();
    }
}
