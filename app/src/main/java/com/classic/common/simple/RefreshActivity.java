package com.classic.common.simple;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.classic.common.MultipleStatusView;

public class RefreshActivity extends AbsActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView       mRecyclerView;
    private NewsAdapter        mNewsAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override void initView() {
        setContentView(R.layout.activity_refresh);
        setTitle("RefreshActivity");
        mMultipleStatusView = (MultipleStatusView)findViewById(R.id.refresh_multiple_status_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setBackgroundResource(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                                                    android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView = (RecyclerView)mMultipleStatusView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mNewsAdapter);

        loading();
    }

    @Override public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, DELAY);
    }

    private class NewsAdapter extends CommonRecyclerAdapter<String> {

        NewsAdapter(@NonNull Context context) {
            super(context, R.layout.item_news);
        }

        @Override public int getItemCount() {
            return 20;
        }

        @Override public void onUpdate(BaseAdapterHelper helper, String item, int position) {
            helper.setText(R.id.item_single_picture_title, "Android七月安全更新上线：Nexus和Pixel系列获得升级")
                  .setText(R.id.item_single_picture_author, "作者：续写经典")
                  .setText(R.id.item_single_picture_date, "2017-07-07 10:36")
                  .setImageResource(R.id.item_single_picture_cover, R.drawable.phone);
        }
    }
}
