package com.example.soil.biogi.measure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soil.biogi.R ;

import java.util.List;

/**
 * Created by soil on 2016/4/16.
 */
public class measureClassAdapter extends RecyclerView.Adapter<measureClassAdapter.MyviewHolder> {
    private List<measureClassModel> measureList;


    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.measureclass_list_row, parent, false);

        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        measureClassModel movie = measureList.get(position) ;

        holder.name.setText(movie.getName());
//        holder.year.setText(movie.getYear()) ;

    }

    @Override
    public int getItemCount() {
        return measureList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
    public TextView name;
        public MyviewHolder(View view){
            super(view);

            name = (TextView)view.findViewById(R.id.itemname) ;

        }

    }
    public measureClassAdapter(List<measureClassModel> measureList){
        this.measureList = measureList;
    }
}
