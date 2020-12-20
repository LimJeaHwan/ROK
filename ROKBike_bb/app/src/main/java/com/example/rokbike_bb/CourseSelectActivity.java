package com.example.rokbike_bb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CourseSelectActivity extends Activity implements View.OnClickListener {


    ImageButton btn_course1,btn_course2,btn_course3,btn_course4;
    Button btn_ROKMap;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        btn_course1 = (ImageButton) findViewById(R.id.btn_course1);
        btn_course2 = (ImageButton) findViewById(R.id.btn_course2);
        btn_course3 = (ImageButton) findViewById(R.id.btn_course3);
        btn_course4 = (ImageButton) findViewById(R.id.btn_course4);
        btn_ROKMap = (Button) findViewById(R.id.btn_ROKMap);

        btn_course1.setOnClickListener(this);
        btn_course2.setOnClickListener(this);
        btn_course3.setOnClickListener(this);
        btn_course4.setOnClickListener(this);

        btn_ROKMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseSelectActivity.this,SuccessActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_course1:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("kakaomap://route?sp=37.48748296256857,126.82060707034023&ep=37.59942754048708,126.8001711404238&by=BICYCLE"));
                startActivity(intent1);
                break;

            case R.id.btn_course2:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                intent2.setData(Uri.parse("kakaomap://route?sp=37.48748296256857,126.82060707034023&ep=37.60673464206643,126.79948134259918&by=BICYCLE"));
                startActivity(intent2);
                break;

            case R.id.btn_course3:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_VIEW);
                intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                intent3.setData(Uri.parse("kakaomap://route?sp=37.48748296256857,126.82060707034023&ep=35.10863431727437,128.9484714285112&by=BICYCLE"));
                startActivity(intent3);
                break;

            case R.id.btn_course4:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_VIEW);
                intent4.addCategory(Intent.CATEGORY_BROWSABLE);
                intent4.setData(Uri.parse("kakaomap://route?sp=37.48748296256857,126.82060707034023&ep=35.5207671,127.14733&by=BICYCLE"));
                startActivity(intent4);
                break;
            default:
                break;
        }

    }
}