package com.example.soil.biogi.healthCheck;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.soil.biogi.AllUrl;
import com.example.soil.biogi.MainActivity;
import com.example.soil.biogi.R;
import com.example.soil.biogi.SaveText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class healthCheck extends Fragment {
    EditText showdate;
    Button btnadd,btnless ;
    View view ;
    SaveText db;
    RequestQueue requestQueue ;
    String[] daysa ;
    String[] numsa ;
    int maincount=0 ;
    TextView comment ;
    private Calendar myCalendar ;
    ViewPager viewPager ;
    private List<healthModel> movieList = new ArrayList<>();
    healthAdapter adapter ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        view = inflater.inflate(R.layout.fragment_health_check, container, false); //find all view
        showdate =(EditText)view.findViewById(R.id.itemdate);
        comment = (TextView)view.findViewById(R.id.doctor) ;
        btnadd = (Button)view.findViewById(R.id.add);
        btnless = (Button)view.findViewById(R.id.less);
        db = new SaveText(getActivity());
        final HashMap<String, String> user = db.getUserDetails();
        final String id = user.get("_id") ;
        MainActivity.toggle(true) ;
        myCalendar = Calendar.getInstance();
        gethealthdata(id) ;
/*
        viewPager =(ViewPager)view.findViewById(R.id.view_pager);
        adapter = new healthAdapter(getActivity()) ;
        viewPager.setAdapter(adapter);
*/
        showdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(maincount< daysa.length-1 &&maincount>=0) {

                   maincount++;
                   showdate.setText(daysa[maincount]);
                   comment.setText(numsa[maincount]);
               }else{
                   maincount=0;
                   showdate.setText(daysa[maincount]);
                   comment.setText(numsa[maincount]);
               }
            }

        });

        btnless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maincount<= daysa.length-1 &&maincount>0) {
                    maincount--;
                    comment.setText(numsa[maincount]);
                    showdate.setText(daysa[maincount]);
                }else{
                    maincount=daysa.length-1;
                    showdate.setText(daysa[maincount]);
                    comment.setText(numsa[maincount]);
                }
            }

        });

        return view ;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }

    };
    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        showdate.setText(sdf.format(myCalendar.getTime()));
    }
    private void gethealthdata(final String id){
        StringRequest measuredate = new StringRequest(Request.Method.POST, AllUrl.healthcheck,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jobj = new JSONObject(response) ;
                            JSONArray day = jobj.getJSONArray("dates") ;
                            JSONArray num = jobj.getJSONArray("value") ;

                             daysa = new String[day.length()] ;
                             numsa = new String[num.length()] ;

                            for(int i=0;i<day.length();i++){
                                daysa[i] = day.getString(i) ;
                                numsa[i] = num.getString(i);
                                healthAdapter model = new healthAdapter( getActivity(),daysa, numsa);
                                Log.d("n1", daysa[0]);
                            }
                            showdate.setText(daysa[0]);
                            comment.setText(numsa[0]);
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String, String> parmater = new HashMap<>();
                parmater.put("_id",id );
                Log.d("n1", id);
                return parmater;
            }
        };
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(measuredate) ;
    }

}
