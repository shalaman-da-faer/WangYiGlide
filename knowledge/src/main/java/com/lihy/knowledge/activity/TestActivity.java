package com.lihy.knowledge.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.lihy.knowledge.R;

/**
 * Created by lihy on 2019/4/22.
 */

public class TestActivity extends FragmentActivity {
    private static final String TAG = "TestActivity";
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.text_hello);
        Log.e(TAG, "before: "+tv.getTranslationX() );
        Log.e(TAG, "before: "+tv.getScrollX() );
        tv.setTranslationX(50);
        Log.e(TAG, "after: "+tv.getTranslationX() );
        Log.e(TAG, "after: "+tv.getScrollX() );
    }
}
