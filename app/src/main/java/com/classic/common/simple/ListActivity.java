package com.classic.common.simple;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.classic.common.MultipleStatusView;

public class ListActivity extends AbsActivity {

    private RecyclerView mRecyclerView;
    private NewsAdapter  mNewsAdapter;

    @Override void initView() {
        setContentView(R.layout.activity_list);
        setTitle("ListActivity");
        mMultipleStatusView = (MultipleStatusView)findViewById(R.id.list_multiple_status_view);
        mRecyclerView = (RecyclerView)mMultipleStatusView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mNewsAdapter);

        loading();
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
