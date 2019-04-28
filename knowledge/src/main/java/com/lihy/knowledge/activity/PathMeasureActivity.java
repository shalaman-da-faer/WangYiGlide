package com.lihy.knowledge.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lihy.knowledge.view.PathMeasureTest;
import com.lihy.knowledge.view.PathMeasureView1;

/**
 * Created by lihy on 2019/4/8.
 */

public class PathMeasureActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureView1(this));
    }
}
