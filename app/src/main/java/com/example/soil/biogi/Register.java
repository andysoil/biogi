package com.example.soil.biogi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText re_email,re_password,re_id ;

    RequestQueue requestQueue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        re_email = (EditText)findViewById(R.id.member_act) ;
        re_password = (EditText)findViewById(R.id.member_psw);
        re_id = (EditText)findViewById(R.id.member_id) ;





    }
    public void back(View view) {
        Intent intent = new Intent(Register.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void register(View view) {
        String email = re_email.getText().toString().trim();
        String password = re_password.getText().toString().trim();
        String id = re_id.getText().toString().trim();
        if (!email.isEmpty() && !password.isEmpty() && !id.isEmpty()) {
            register_check(id, email, password);
        } else {
            Toast.makeText(getApplicationContext(),
                    "要輸入咚咚得私", Toast.LENGTH_LONG)
                    .show();
        }
    }
    private void register_check(final String member_id, final String member_act, final String member_psw){
        StringRequest checkre = new StringRequest(Request.Method.POST, AllUrl.Register, new
                Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            boolean errorSame = jObj.getBoolean("errorSame") ;
                            if (!error) {
                                Intent intent = new Intent(Register.this,
                                        LoginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(),
                                        "註冊完成",Toast.LENGTH_LONG)
                                        .show();
                            } else if(errorSame) {
                                Toast.makeText(Register.this, "ID已被註冊", Toast.LENGTH_LONG).show();
                            }else{
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(Register.this, "輸入的ID或帳號密碼錯誤，請重新輸入", Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parmater = new HashMap<>() ;
                parmater.put("id",member_id);
                parmater.put("act",member_act);
                parmater.put("psw",member_psw) ;
                return  parmater ;
            }

        };
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(checkre) ;
    }
}
