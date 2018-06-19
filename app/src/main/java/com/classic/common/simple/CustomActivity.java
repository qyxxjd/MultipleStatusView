package com.classic.common.simple;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomActivity extends AbsActivity {

    private final RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

    private View mEmptyView;
    private View mLoadingView;
    private View mErrorView;
    private View mNoNetworkView;

    @Override void initView() {
        setContentView(R.layout.activity_custom);
        setTitle("CustomActivity");
        mMultipleStatusView = findViewById(R.id.custom_multiple_status_view);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);
        mLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_loading:
                if (null == mLoadingView) {
                    mLoadingView = createCustomView("自定义加载中视图");
                }
                mMultipleStatusView.showLoading(mLoadingView, mLayoutParams);
                break;
            case R.id.fab_empty:
                if (null == mEmptyView) {
                    mEmptyView = createCustomView("自定义空视图");
                }
                mMultipleStatusView.showEmpty(mEmptyView, mLayoutParams);
                break;
            case R.id.fab_error:
                if (null == mErrorView) {
                    mErrorView = createCustomView("自定义错误视图");
                }
                mMultipleStatusView.showError(mErrorView, mLayoutParams);
                break;
            case R.id.fab_no_network:
                if (null == mNoNetworkView) {
                    mNoNetworkView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.no_network, null);
                }
                mMultipleStatusView.showNoNetwork(mNoNetworkView, mLayoutParams);
                break;
            case R.id.fab_content:
                mMultipleStatusView.showContent(R.layout.custom_content_view, mLayoutParams);
                break;
        }
        mFloatingActionMenu.toggle(false);
    }

    private TextView createCustomView(String text) {
        TextView tv = new TextView(getApplicationContext());
        tv.setId(Utils.generateViewId());
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);
        //noinspection deprecation
        tv.setTextColor(getResources().getColor(R.color.color_item_intro));
        return tv;
    }
}
