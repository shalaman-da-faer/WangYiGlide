package com.lihy.knowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lihy.knowledge.view.SplitView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));
    }
}
