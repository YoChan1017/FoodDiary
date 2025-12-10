package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog; // 로딩창

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SelectMenuActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_menu);

        dbHelper = new DBHelper(this);

        // ★ [핵심] 화면에 들어오자마자 데이터가 있는지 확인하고, 없으면 로딩 시작 ★
        if (!dbHelper.isDataExists()) {
            loadCsvAndInsertToDB();
        }

        // 1. 뒤로가기 버튼
        Button btnBack = findViewById(R.id.btn_back_title);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 2. 음식 정보 열람 버튼
        Button btnFoodInfo = findViewById(R.id.btn_food_info);
        btnFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectMenuActivity.this, FoodInformationActivity.class);
                startActivity(intent);
            }
        });

        // 3. 카테고리 선택 영역 클릭 이벤트
        TextView tvCategorySelect = findViewById(R.id.tv_category_select);
        tvCategorySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectMenuActivity.this, FoodCategoryActivity.class);
                startActivity(intent);
            }
        });

        // ★ 4. [추가] 기록 버튼 연결 (btn_record) ★
        Button btnRecord = findViewById(R.id.btn_record);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일기 목록 화면으로 이동
                Intent intent = new Intent(SelectMenuActivity.this, DiaryListActivity.class);
                startActivity(intent);
            }
        });
    }

    // --- 데이터 로딩 함수 (FoodInformationActivity에 있던 것을 가져옴) ---
    private void loadCsvAndInsertToDB() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("초기 데이터를 구축 중입니다...\n(잠시만 기다려주세요)");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = getAssets();
                ArrayList<String[]> dataList = new ArrayList<>();

                try {
                    InputStream inputStream = assetManager.open("food_data.csv");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    String line;
                    boolean isHeader = true;

                    while ((line = reader.readLine()) != null) {
                        if (isHeader) { isHeader = false; continue; }

                        // 정규식 분리
                        String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        if (parts.length >= 4) {
                            String name = deleteQuote(parts[0]);
                            String category = deleteQuote(parts[1]);
                            String amount = deleteQuote(parts[2]);
                            String calories = deleteQuote(parts[3]);

                            dataList.add(new String[]{name, category, amount, calories});
                        }
                    }
                    reader.close();

                    dbHelper.insertAllFood(dataList);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(SelectMenuActivity.this, "데이터 준비 완료!", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null) progressDialog.dismiss();
                            Toast.makeText(SelectMenuActivity.this, "오류 발생: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    // 따옴표 제거 헬퍼 함수
    private String deleteQuote(String str) {
        if (str != null && str.length() >= 2 && str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }
}