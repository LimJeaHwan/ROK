package com.example.rokbike_bb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Register";
    EditText reg_id, reg_pwd, reg_email, reg_age;
    Button btn_register;
    String userid = "", userpassword = "", useremail = "", userage = "";
    /*에디트텍스트,버튼 객체 선언 및 각각의 String 변수 초기화*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        reg_id = (EditText) findViewById(R.id.reg_id);
        reg_pwd = (EditText) findViewById(R.id.reg_pwd);
        reg_email = (EditText) findViewById(R.id.reg_eamil);
        reg_age = (EditText) findViewById(R.id.reg_age);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {  /*클릭 이벤트*/
        switch (v.getId()) {
                case R.id.btn_register:
                    /*에디트 텍스트에 있는 값들 get*/
                    userid = reg_id.getText().toString();
                    userpassword = reg_pwd.getText().toString();
                    useremail = reg_email.getText().toString();
                    userage = reg_age.getText().toString();
                    /*각각의 에디트 텍스트의 길이가 1 미만이면*/
                    if (userid.length() <= 1 || userpassword.length() <= 1 || useremail.length() <= 1 || userage.length() <= 1) {
                        /*Log.d(TAG, "입력되지 않은 데이터가 있습니다.");*/
                        Toast.makeText(Register.this, "모든칸은 공백없이 채워야합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        RegisterServer();
                    }
                    break;
            default:
                break;
        }
    }

    private void RegisterServer() {
        final String SIGNIN_URL = "";
        final String urlSuffix = "?userid=" + userid + "&userpassword=" + userpassword + "&useremail=" + useremail + "&userage=" + userage;

        class SignupUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this,"진행중입니다.",null);
            /*진행시 대기문자 출력*/
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                /*인텐트*/
                if (s != null) {
                    try {
                        Toast.makeText(Register.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this,MainActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Register.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            protected String doInBackground(String... strings) {
                /*백그라운드내부 행위*/
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
                    Log.d(TAG, page);
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