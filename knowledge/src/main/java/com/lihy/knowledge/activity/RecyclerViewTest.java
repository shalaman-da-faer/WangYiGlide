package com.lihy.knowledge.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lihy.knowledge.R;
import com.lihy.knowledge.adapter.MyRecyclerAdapter;

/**
 * Created by lihy on 2019/4/18.
 */

public class RecyclerViewTest extends FragmentActivity {
    private RecyclerView recyclerView;
    private LinearLayout layout_top;
    private TextView text_top;

    private int topHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_view);
        recyclerView=findViewById(R.id.recycler_view);
        layout_top=findViewById(R.id.layout_top);
        text_top=findViewById(R.id.text_top);
        final LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyRecyclerAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                topHeight=layout_top.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View child=recyclerView.getChildAt(1);
                if(child.getTop()<=topHeight){
                    layout_top.setY(child.getTop()-topHeight);
                }else {
                    layout_top.setY(0);
                    updateTop(manager.findFirstVisibleItemPosition());
                }
            }
        });
    }


    private void updateTop(int position){
        text_top.setText(""+position);
    }
}
