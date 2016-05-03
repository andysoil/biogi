package com.example.soil.biogi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by soil on 2016/4/29.
 */
public class talkroom extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        MainActivity.toggle(true) ;
        return inflater.inflate(R.layout.fragment_talkroom, container, false);
    }


}
