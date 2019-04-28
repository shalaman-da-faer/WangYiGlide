package com.lihy.knowledge.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.lihy.knowledge.R;
import com.lihy.knowledge.fragment.ParallaxFragment;
import com.lihy.knowledge.view.ParallaxContainer;

/**
 * Created by lihy on 2019/4/15.
 */

public class ParallaxActivity extends FragmentActivity {
    private ParallaxContainer container;
    private ImageView iv_man;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        container=findViewById(R.id.parallax_container);
        iv_man=findViewById(R.id.iv_man);
        iv_man.setBackgroundResource(R.drawable.man_run);
        container.setIv_man(iv_man);
    }
}

