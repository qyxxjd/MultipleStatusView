package com.classic.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private View emptyView;
    private View errorView;
    private View loadingView;
    private View noNetworkView;
    private View contentView;
    private View emptyRetryView;
    private View errorRetryView;
    private View noNetworkRetryView;
    private int  emptyViewResId;
    private int  errorViewResId;
    private int  loadingViewResId;
    private int  noNetworkViewResId;
    private int  contentViewResId;
    private int  viewStatus;

    private LayoutInflater inflater;
    private OnClickListener onRetryClickListener;
    private final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

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
        a.recycle();
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        inflater = LayoutInflater.from(getContext());
        showContent();
    }

    /** 获取当前状态 */
    public int getViewStatus(){
        return viewStatus;
    }

    /**
     * 设置重试点击事件
     * @param onRetryClickListener 重试点击事件
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    /** 显示空视图 */
    public final void showEmpty() {
        viewStatus = STATUS_EMPTY;
        if(null == emptyView){
            emptyView = inflater.inflate(emptyViewResId, null);
            emptyRetryView = emptyView.findViewById(R.id.empty_retry_view);
            if(null != onRetryClickListener && null != emptyRetryView){
                emptyRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(emptyView, 0, layoutParams);
        }
        showViewByStatus(viewStatus);
    }
    /** 显示错误视图 */
    public final void showError() {
        viewStatus = STATUS_ERROR;
        if(null == errorView){
            errorView = inflater.inflate(errorViewResId, null);
            errorRetryView = errorView.findViewById(R.id.error_retry_view);
            if(null != onRetryClickListener && null != errorRetryView){
                errorRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(errorView,0,layoutParams);
        }
        showViewByStatus(viewStatus);
    }
    /** 显示加载中视图 */
    public final void showLoading() {
        viewStatus = STATUS_LOADING;
        if(null == loadingView){
            loadingView = inflater.inflate(loadingViewResId, null);
            addView(loadingView,0,layoutParams);
        }
        showViewByStatus(viewStatus);
    }
    /** 显示无网络视图 */
    public final void showNoNetwork() {
        viewStatus = STATUS_NO_NETWORK;
        if(null == noNetworkView){
            noNetworkView = inflater.inflate(noNetworkViewResId, null);
            noNetworkRetryView = noNetworkView.findViewById(R.id.no_network_retry_view);
            if(null != onRetryClickListener && null != noNetworkRetryView){
                noNetworkRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(noNetworkView,0,layoutParams);
        }
        showViewByStatus(viewStatus);
    }

    /** 显示内容视图 */
    public final void showContent() {
        viewStatus = STATUS_CONTENT;
        if(null == contentView){
            if(contentViewResId != NULL_RESOURCE_ID){
                contentView = inflater.inflate(contentViewResId, null);
                addView(contentView,0,layoutParams);
            }else{
                contentView = findViewById(R.id.content_view);
            }
        }
        showViewByStatus(viewStatus);
    }

    private void showViewByStatus(int viewStatus) {
        if(null != loadingView){
            loadingView.setVisibility(viewStatus==STATUS_LOADING ? View.VISIBLE : View.GONE);
        }
        if(null != emptyView){
            emptyView.setVisibility(viewStatus==STATUS_EMPTY ? View.VISIBLE : View.GONE);
        }
        if(null != errorView){
            errorView.setVisibility(viewStatus==STATUS_ERROR ? View.VISIBLE : View.GONE);
        }
        if(null != noNetworkView){
            noNetworkView.setVisibility(viewStatus==STATUS_NO_NETWORK ? View.VISIBLE : View.GONE);
        }
        if(null != contentView){
            contentView.setVisibility(viewStatus==STATUS_CONTENT ? View.VISIBLE : View.GONE);
        }
    }

}
