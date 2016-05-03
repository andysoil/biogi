package com.example.soil.biogi.measure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soil.biogi.R;

import java.util.List;

/**
 * Created by soil on 2016/4/27.
 */
public class measureItemAdapter extends RecyclerView.Adapter<measureItemAdapter.MyviewHolder> {
    private List<measureItemModel> measureList;

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.measureitem_list_row, parent, false);

        return new MyviewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        measureItemModel movie = measureList.get(position) ;

        holder.name.setText(movie.getName());
        holder.value.setText(movie.getValue());
        holder.date.setText(movie.getYear());
//        holder.year.setText(movie.getYear()) ;
    }


    @Override
    public int getItemCount() {
        return measureList.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView name,value,date;
        public MyviewHolder(View view){
            super(view);
            value = (TextView)view.findViewById(R.id.itemvalue) ;
            name = (TextView)view.findViewById(R.id.itemname) ;
            date = (TextView)view.findViewById(R.id.itemdate);
        }

    }
    public measureItemAdapter(List<measureItemModel> measureList){
        this.measureList = measureList;
    }
}
