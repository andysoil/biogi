package com.example.soil.biogi.measure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soil.biogi.MainActivity;
import com.example.soil.biogi.R;


public class measureItemDetail extends Fragment {
    TextView tvName,tvValue,tvDate ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measure_item_detail, container, false);
        tvName = (TextView)view.findViewById(R.id.itemname) ;
        tvValue = (TextView)view.findViewById(R.id.itemvalue) ;
        tvDate = (TextView)view.findViewById(R.id.itemdate) ;


        MainActivity.toggle(false) ;
        MainActivity.toggleClass() ;

        Bundle bundle = getArguments();
        tvName.setText(bundle.getString("name")) ;
        tvValue.setText(bundle.getString("value")) ;
        tvDate.setText(bundle.getString("date")) ;




        return view;
    }


}
