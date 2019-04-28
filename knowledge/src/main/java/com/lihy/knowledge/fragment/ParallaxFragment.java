package com.lihy.knowledge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihy.knowledge.yewu.ParallaxLayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihy on 2019/4/15.
 */

public class ParallaxFragment extends Fragment {

    //此Fragment上所有的需要实现视差动画的视图
    private List<View> parallaxViews = new ArrayList<View>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater original, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int layoutId=getArguments().getInt("layoutId");
//        ViewGroup root= (ViewGroup) inflater.inflate(layoutId,null);
//        if(parallaxViews.size()==0){
//            for(int i=0;i<root.getChildCount();i++){
//                parallaxViews.add(root.getChildAt(i));
//            }
//        }
//        return root;
        ParallaxLayoutInflater inflater = new ParallaxLayoutInflater(original, getActivity(),this);
        return inflater.inflate(layoutId, null);
    }

    public List<View> getParallaxViews() {
        return parallaxViews;
    }
}
