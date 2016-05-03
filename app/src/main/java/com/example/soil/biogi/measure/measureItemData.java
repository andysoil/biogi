package com.example.soil.biogi.measure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.soil.biogi.R;

/**
 * Created by soil on 2016/5/1.
 */
public class measureItemData extends Fragment {
    View view ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        view = inflater.inflate(R.layout.fragment_measureitemdata, container, false); //find all view

        return view ;
    }
}