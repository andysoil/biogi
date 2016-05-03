package com.example.soil.biogi.memberSet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.soil.biogi.AllUrl;
import com.example.soil.biogi.R;
import com.example.soil.biogi.SaveText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class editData extends AppCompatActivity {
    EditText sex,name,birthday,phone,cellphone,mail ;
    private SaveText db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        sex = (EditText)findViewById(R.id.sex) ;
        name  = (EditText)findViewById(R.id.itemname) ;
        birthday  = (EditText)findViewById(R.id.birthday) ;
        phone = (EditText)findViewById(R.id.phone) ;
        cellphone  = (EditText)findViewById(R.id.cellphone) ;
        mail  = (EditText)findViewById(R.id.mail) ;
        db = new SaveText(getApplicationContext()) ;

        onSetText();

        HashMap<String, String> user = db.getUserDetails();
        sex.setText(user.get("sex"));
        name.setText(user.get("username"));
        birthday.setText(user.get("birthday"));
        phone.setText(user.get("phone"));
        cellphone.setText(user.get("cellphone"));
        mail.setText(user.get("mail"));



    }
    public void onSetText(){
        HashMap<String, String> user = db.getUserDetails();
        sex.setText(user.get("sex"));
        name.setText(user.get("username"));
        birthday.setText(user.get("birthday"));
        phone.setText(user.get("phone"));
        cellphone.setText(user.get("cellphone"));
        mail.setText(user.get("mail"));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.changepasmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back) {
            finish();
            return true;
        }else if(id==R.id.enter) {
            enter() ;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enter() {
        HashMap<String, String> user = db.getUserDetails();
        String usename = user.get("name");

        String inname = name.getText().toString().trim();
        String insex = sex.getText().toString().trim();
        String inbirthday = birthday.getText().toString().trim();
        String inphone = phone.getText().toString().trim();
        String incellphone = cellphone.getText().toString().trim();
        String inmail = mail.getText().toString().trim();
        if(inname.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "請輸入名字!", Toast.LENGTH_LONG)
                    .show();
        }else{
            inputdate(inname,insex,inbirthday,inphone,incellphone,inmail,usename);
            Toast.makeText(getApplicationContext(),
                    "更改成功!", Toast.LENGTH_LONG)
                    .show();
            finish();
        }
    }



    private void inputdate( final String inname, final String insex, final String inbirthday, final String inphone, final String incellphone, final String inmail, final String usename) {
        StringRequest inputdate = new StringRequest(Request.Method.POST, AllUrl.chdata, new
                Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");

                            if(!error){
                                db.updateMemberData(inname, insex,inbirthday,inphone,incellphone,inmail,usename);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parmater = new HashMap<>();

                parmater.put("username",usename) ;
                parmater.put("inname", inname);
                parmater.put("insex", insex);
                parmater.put("inbirthday", inbirthday);
                parmater.put("inphone", inphone);
                parmater.put("incellphone", incellphone);
                parmater.put("inmail", inmail);

                return parmater;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(inputdate);
    }


    public void back() {
        finish();
    }
}
