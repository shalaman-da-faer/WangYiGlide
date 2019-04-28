package com.lihy.knowledge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lihy.knowledge.R;

/**
 * Created by lihy on 2019/4/18.
 */

public class MyRecyclerAdapter  extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder>{

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,null);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(""+position);
        holder.iv.setImageResource(kindImageResource(position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    private int kindImageResource(int position){
        int pos=position%4;
        switch (pos){
            case 0:
                return R.drawable.intro1_item_0;
            case 1:
                return R.drawable.intro1_item_1;
            case 2:
                return R.drawable.intro1_item_2;
            case 3:
                return R.drawable.intro1_item_3;
            default:
                    return R.drawable.intro1_item_0;

        }
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView iv;

        public MyHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_position);
            iv=itemView.findViewById(R.id.iv_content);
        }
    }
}
