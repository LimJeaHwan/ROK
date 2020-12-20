package com.example.rokbike_bb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginSystem extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "LoginSystem";
    EditText user_id, user_pw;
    Button btn_login;
    String userid = "", userpassword = "", User_Json_id = "", User_Json_pw = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn_login=(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        user_id = (EditText) findViewById(R.id.login_id);
        user_pw = (EditText) findViewById(R.id.login_pw);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userid = user_id.getText().toString();
                userpassword = user_pw.getText().toString();
                if (userid.length() <= 1 || userpassword.length() <= 1) {
                    Log.d(TAG, "데이터를 입력하세요");
                } else {
                    ConnectServer();
                }
                break;
            default:
                break;
        }
    }

    private void ConnectServer() {

        final String SIGNIN_URL = "";
        final String urlSuffix = "?userid=" + userid + "&userpassword=" + userpassword;

        class SignupUser extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    final int i = 0;

                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject test = jsonArray.getJSONObject(i);
                    User_Json_id = test.getString("userid");
                    User_Json_pw = test.getString("userpw");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

                if (User_Json_id.equals(userid) && User_Json_pw.equals(userpassword)) {
                    try {
                        Toast.makeText(LoginSystem.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginSystem.this, CourseSelectActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginSystem.this, "아이디 혹은 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    HttpClient client = new DefaultHttpClient();  // 보낼 객체 만들기
                    HttpPost post = new HttpPost(SIGNIN_URL + urlSuffix);  // 주소 뒤에 데이터를 넣기
                    HttpResponse response = (HttpResponse) client.execute(post); // 데이터 보내기
                    BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

                    String line = null;
                    String page = "";

                    while ((line = bufreader.readLine()) != null) {
                        page += line;
                    }
                    Log.d(TAG, "페이지값" + page);

                    return page;
                } catch (Exception e) {

                    return null;
                }
            }
        }
        SignupUser su = new SignupUser();
        su.execute(urlSuffix);
    }
}