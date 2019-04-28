package com.lihy.knowledge;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lihy.knowledge.activity.DragBubbleActivity;
import com.lihy.knowledge.activity.GuaGuaActivity;
import com.lihy.knowledge.activity.ParallaxActivity;
import com.lihy.knowledge.activity.PathMeasureActivity;
import com.lihy.knowledge.activity.RecyclerViewTest;
import com.lihy.knowledge.activity.SplashActivity;
import com.lihy.knowledge.activity.TestActivity;

/**
 * Created by lihy on 2019/4/7.
 */

public class StartActivity extends ListActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"粒子爆炸", "刮刮卡","水波纹","QQ未读消息气泡",
            "使用PathMeasure实现旋转的箭头","小红书引导页","自定义RecyclerView","测试。。。"}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, MainActivity.class);
                break;
            case 1:
                intent.setClass(this, GuaGuaActivity.class);
                break;
            case 2:
                intent.setClass(this, SplashActivity.class);
                break;
            case 3:
                intent.setClass(this, DragBubbleActivity.class);
                break;
            case 4:
                intent.setClass(this, PathMeasureActivity.class);
                break;
            case 5:
                intent.setClass(this,ParallaxActivity.class);
                break;
            case 6:
                intent.setClass(this,RecyclerViewTest.class);
                break;
            case 7:
                intent.setClass(this,TestActivity.class);
                break;
            default:
                intent.setClass(this, MainActivity.class);
        }
        startActivity(intent);

    }
}
