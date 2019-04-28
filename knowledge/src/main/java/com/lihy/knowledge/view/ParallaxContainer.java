package com.lihy.knowledge.view;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lihy.knowledge.R;
import com.lihy.knowledge.adapter.ParallaxAdapter;
import com.lihy.knowledge.entity.ParallaxViewTag;
import com.lihy.knowledge.fragment.ParallaxFragment;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihy on 2019/4/15.
 */

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "ParallaxContainer";
    private int[] layouts={R.layout.view_intro_1,R.layout.view_intro_2,R.layout.view_intro_3,
            R.layout.view_intro_4,R.layout.view_intro_5,R.layout.view_intro_6,R.layout.view_intro_7,R.layout.view_login};
//    private float[] x_sudu={1.2f,1.2f,1.3f,1.3f,1.1f,1.1f,1.2f,1.2f,1.3f,1.3f,1.1f,1.1f};
//    private float[] y_sudu={1.3f,1.3f,1.1f,1.1f,1.2f,1.2f,1.3f,1.3f,1.1f,1.1f,1.1f,1.1f};
    private ArrayList<Fragment> fragments;

    private ViewPager viewPager;

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

    private ImageView iv_man;
    public ParallaxContainer(@NonNull Context context) {
        this(context,null);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        fragments=new ArrayList<>();
        for(int i=0;i<layouts.length;i++){
            ParallaxFragment fragment=new ParallaxFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("layoutId",layouts[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        FragmentActivity activity= (FragmentActivity) context;
        viewPager=new ViewPager(context);
        viewPager.setId(R.id.parallax_pager);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        viewPager.setAdapter(new ParallaxAdapter(activity.getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(this);
        addView(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //        动画
        int containerWidth = getWidth();
        ParallaxFragment outFragment = null;

        try {
            outFragment = (ParallaxFragment) fragments.get(position - 1);
        } catch (Exception e) {}
        //获取到退出的页面
        ParallaxFragment inFragment = null;
        try {
            inFragment = (ParallaxFragment) fragments.get(position);
        } catch (Exception e) {}

        if (outFragment != null) {
            //获取Fragment上所有的视图，实现动画效果
            List<View> outViews = outFragment.getParallaxViews();
//            动画
            if (outViews != null) {
                for (int i=0;i<outViews.size();i++) {
//
                    ParallaxViewTag tag = (ParallaxViewTag) outViews.get(i).getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    Log.d(TAG, "onPageScrolled: "+positionOffsetPixels);
                    ViewHelper.setTranslationX(outViews.get(i), (containerWidth - positionOffsetPixels) * tag.xIn);
                    ViewHelper.setTranslationY(outViews.get(i), (containerWidth - positionOffsetPixels) * tag.yIn);
                }

            }

        }
        if(inFragment != null){
            List<View> inViews = inFragment.getParallaxViews();
            if (inViews != null) {
                for (int i=0;i<inViews.size(); i++) {
                    ParallaxViewTag tag = (ParallaxViewTag) inViews.get(i).getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
//                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    ViewHelper.setTranslationY(inViews.get(i), 0 - positionOffsetPixels * tag.yOut);
                    ViewHelper.setTranslationX(inViews.get(i), 0 - positionOffsetPixels * tag.xOut);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == fragments.size() - 1) {
            iv_man.setVisibility(INVISIBLE);
        }else{
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable animation = (AnimationDrawable) iv_man.getBackground();
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animation.start();
                break;

            case ViewPager.SCROLL_STATE_IDLE:
                animation.stop();
                break;

            default:
                break;
        }
    }
}
