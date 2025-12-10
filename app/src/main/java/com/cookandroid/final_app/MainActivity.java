package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 레이아웃의 "터치" 버튼을 Java 코드와 연결 (ID: btn_touch)
        Button btnTouch = findViewById(R.id.btn_touch);

        // 2. 버튼 클릭 이벤트 설정
        btnTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 화면 전환을 위한 Intent 생성
                // (현재 화면: MainActivity.this -> 이동할 화면: SelectMenuActivity.class)
                Intent intent = new Intent(MainActivity.this, SelectMenuActivity.class);

                // 화면 이동 시작
                startActivity(intent);
            }
        });
    }
}