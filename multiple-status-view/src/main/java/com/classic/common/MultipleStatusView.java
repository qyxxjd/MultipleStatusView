package com.classic.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 类描述：  一个方便在多种状态切换的view
 * 创建人:   续写经典
 * 创建时间: 2016/1/15 10:20.
 */
public class MultipleStatusView extends RelativeLayout {
    public static final int STATUS_CONTENT      = 0x00;
    public static final int STATUS_LOADING      = 0x01;
    public static final int STATUS_EMPTY        = 0x02;
    public static final int STATUS_ERROR        = 0x03;
    public static final int STATUS_NO_NETWORK   = 0x04;

    private static final int NULL_RESOURCE_ID   = -1;

    private View emptyRetryView;
    private View errorRetryView;
    private View noNetworkRetryView;
    private int  emptyViewResId;
    private int  errorViewResId;
    private int  loadingViewResId;
    private int  noNetworkViewResId;
    private int  contentViewResId;
    private int  viewStatus;

    private final LayoutInflater inflater;

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a =
            context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        inflater = LayoutInflater.from(context);
        try {
            emptyViewResId =
                a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view);
            errorViewResId =
                a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view);
            loadingViewResId =
                a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view);
            noNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetworkView,
                R.layout.no_network_view);
            contentViewResId = a.getResourceId(R.styleable.MultipleStatusView_contentView,
                NULL_RESOURCE_ID);

            inflater.inflate(emptyViewResId, this, true);
            inflater.inflate(errorViewResId, this, true);
            inflater.inflate(loadingViewResId, this, true);
            inflater.inflate(noNetworkViewResId, this, true);
            if(contentViewResId != NULL_RESOURCE_ID){
                inflater.inflate(contentViewResId, this, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        initRetryView();
        showContent();
    }

    private void initRetryView(){
        emptyRetryView = findViewById(R.id.empty_retry_view);
        errorRetryView = findViewById(R.id.error_retry_view);
        noNetworkRetryView = findViewById(R.id.no_network_retry_view);
    }

    /** 获取当前状态 */
    public int getViewStatus(){
        return viewStatus;
    }

    /**
     * 设置重试点击事件
     * @param onRetryClickListener
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        if(null == onRetryClickListener){
            return;
        }
        if(null != emptyRetryView){
            emptyRetryView.setOnClickListener(onRetryClickListener);
        }
        if(null != errorRetryView){
            errorRetryView.setOnClickListener(onRetryClickListener);
        }
        if(null != noNetworkRetryView){
            noNetworkRetryView.setOnClickListener(onRetryClickListener);
        }
    }

    /** 显示空视图 */
    public final View showEmpty() {
        viewStatus = STATUS_EMPTY;
        return showViewById(R.id.empty_view);
    }
    /** 显示错误视图 */
    public final View showError() {
        viewStatus = STATUS_ERROR;
        return showViewById(R.id.error_view);
    }
    /** 显示加载中视图 */
    public final View showLoading() {
        viewStatus = STATUS_LOADING;
        return showViewById(R.id.loading_view);
    }
    /** 显示无网络视图 */
    public final View showNoNetwork() {
        viewStatus = STATUS_NO_NETWORK;
        return showViewById(R.id.no_network_view);
    }
    /** 显示内容视图 */
    public final View showContent() {
        viewStatus = STATUS_CONTENT;
        return showViewById(R.id.content_view);
    }

    private final View showViewById(int viewId) {
        View view = null;
        for (int i = 0; i < this.getChildCount(); i++) {
            final View child = this.getChildAt(i);
            child.setVisibility(child.getId() == viewId ? View.VISIBLE : View.GONE);
            if(child.getId() == viewId){
                view = child;
            }
        }
        return view;
    }

}
