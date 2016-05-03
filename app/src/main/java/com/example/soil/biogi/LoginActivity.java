package com.example.soil.biogi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String TAG = "THIS" ;
    RequestQueue requestQueue ;
    Button login,register ;
    EditText inputemail,inputpassword ;
    private SessionManger session;
    private SaveText db ;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail = (EditText) findViewById(R.id.email);
        inputpassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogin);
        register =(Button)findViewById(R.id.btnLinkToRegisterScreen) ;
        session = new SessionManger(getApplicationContext());
        db = new SaveText(getApplicationContext()) ;


        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }


    public void check(final String email, final String password){

                StringRequest checkreq = new StringRequest(Request.Method.POST, AllUrl.Loginurl, new
                        Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    boolean error = jObj.getBoolean("error");

                                    if (!error) {

                                        session.setLogin(true);
                                        JSONObject user = jObj.getJSONObject("user");
                                        String id = user.getString("id");
                                        String name = user.getString("address");
                                        String created_at = user.getString("date");
                                        String username = user.getString("name") ;
                                        String sex = user.getString("sex") ;
                                        String birthday = user.getString("birthday") ;
                                        String phone = user.getString("phone");
                                        String cellphone = user.getString("cellphone") ;
                                        String email = user.getString("mail") ;
                                        int time = user.getInt("time");
                                        Log.d(TAG, "NAME=" + name) ;
                                        db.addUser(id, name, created_at, password, username, sex, birthday, phone, cellphone, email);

                                        Intent intent = new Intent(LoginActivity.this,
                                                MainActivity.class);
                                        intent.putExtra("loginin", true);
                                        startActivity(intent);
                                        finish();
                                        pDialog.dismiss();

                                    }else {
                                        Toast.makeText(LoginActivity.this, "請輸入正確的帳號或密碼", Toast.LENGTH_LONG).show();
                                        pDialog.dismiss();
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
                        pDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "尚未連線", Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> parmater = new HashMap<>() ;
                        parmater.put("email", email) ;
                        parmater.put("password", password) ;
                        return  parmater ;
                    }


                };
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(checkreq) ;

    }


    public void Login(View view) {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("請稍後...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

       String email = inputemail.getText().toString().trim();
        String password = inputpassword.getText().toString().trim();
        // Check for empty data in the form
        if (!email.isEmpty() && !password.isEmpty()) {
            // login user

            check(email,password) ;
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext(),
                    "要輸入帳號與密碼!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void Registater(View view) {
        Intent intent = new Intent(LoginActivity.this,
                Register.class);
        startActivity(intent);
        finish();
    }


}
