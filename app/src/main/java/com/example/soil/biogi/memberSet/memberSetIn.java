package com.example.soil.biogi.memberSet;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.soil.biogi.LoginActivity;
import com.example.soil.biogi.MainActivity;
import com.example.soil.biogi.R;
import com.example.soil.biogi.SaveText;
import com.example.soil.biogi.SessionManger;


public class memberSetIn extends Fragment {

    private SaveText db;
    private SessionManger session;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        view = inflater.inflate(R.layout.fragment_member_set, container, false); //find all view
        Button changeData = (Button) view.findViewById(R.id.changedata);
        Button changePas = (Button) view.findViewById(R.id.changebtn);
        Button logout = (Button)view.findViewById(R.id.logout) ;
        session = new SessionManger(getActivity());
        db = new SaveText(getActivity()) ;

        MainActivity.toggle(true) ;


        changeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        editData.class);
                startActivity(intent);
            }
        });
        changePas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),
                        change_psw.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                session.setLogin(false);
                db.deleteUsers();
                Intent intent = new Intent(getActivity(),
                        LoginActivity.class);

                startActivity(intent);
            }
        });
        return view ;
    }

}