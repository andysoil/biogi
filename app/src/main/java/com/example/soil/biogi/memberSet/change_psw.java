package com.example.soil.biogi.memberSet;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

import com.example.soil.biogi.AllUrl;
import com.example.soil.biogi.R;
import com.example.soil.biogi.SaveText;

import java.util.HashMap;
import java.util.Map;

public class change_psw extends AppCompatActivity {
    EditText inputpassword,newpsw,nowpsw ;
    Button change ;
    private SaveText db;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_password);



        nowpsw = (EditText)findViewById(R.id.now_password) ;
        newpsw = (EditText)findViewById(R.id.new_password) ;
        inputpassword = (EditText)findViewById(R.id.inputpas) ;
        change = (Button)findViewById(R.id.input );
        db = new SaveText(getApplicationContext()) ;





    }
    private void checkPassword(final String email, final String inpsw) {
        StringRequest checkpas = new StringRequest(Request.Method.POST, AllUrl.ChangePas, new
                Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parmater = new HashMap<>();

                parmater.put("inputpsw", inpsw);
                parmater.put("email", email);

                return parmater;

            }
        };

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(checkpas);
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
        String inpsw = inputpassword.getText().toString().trim();
        String nowpas = nowpsw.getText().toString().trim();
        String newpas = newpsw.getText().toString().trim();
        HashMap<String, String> user = db.getUserDetails();
        String email = user.get("name");
        String password = user.get("password");

        if (inpsw.isEmpty() || nowpas.isEmpty() || newpas.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "要輸入密碼!", Toast.LENGTH_LONG)
                    .show();
        } else if (!newpas.equals(inpsw)) {
            Toast.makeText(getApplicationContext(),
                    "密碼不相符!", Toast.LENGTH_LONG)
                    .show();
        } else if (newpas.length() < 6) {
            Toast.makeText(getApplicationContext(),
                    "密碼至少輸入6個字元!", Toast.LENGTH_LONG)
                    .show();
        } else if (!password.equals(nowpas)) {
            Toast.makeText(getApplicationContext(),
                    "與原密碼不同!", Toast.LENGTH_LONG)
                    .show();
        } else {
            checkPassword(email, inpsw);
            db.updatepas(email, inpsw);
            finish();
            Toast.makeText(getApplicationContext(),
                    "密碼更改成功!", Toast.LENGTH_LONG)
                    .show();
        }
    }
    private void back() {

        //只要一個finish即可換頁
        finish();
    }

}
