package com.example.soil.biogi.healthCheck;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.soil.biogi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soil on 2016/5/3.
 */
public class healthAdapter extends PagerAdapter {
    private String[] value ;
    private String[] date ;
    private Context ctx ;
    private LayoutInflater layoutInflater ;
    private List<healthModel> movieList = new ArrayList<>();

    public healthAdapter(Context inctx,String[] indate,String[] innum){

            this.ctx = inctx;
            this.value = innum;
            this.date = indate;

    }
    @Override
    public int getCount() {

        return value.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false) ;
        TextView txt = (TextView)item_view.findViewById(R.id.value) ;
        txt.setText(value[position]);
        container.addView(item_view) ;
        return  item_view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((RelativeLayout)object) ;
    }
}
