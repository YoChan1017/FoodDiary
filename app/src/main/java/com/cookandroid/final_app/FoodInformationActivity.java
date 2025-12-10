package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ProgressDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FoodInformationActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    ArrayAdapter<String> adapter;
    ProgressDialog progressDialog;

    EditText etSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_information);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listView_food);

        // 검색 UI 연결
        etSearch = findViewById(R.id.et_search_food);
        btnSearch = findViewById(R.id.btn_search_food);

        Button btnBack = findViewById(R.id.btn_back_food);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // ★ 검색 버튼 클릭 이벤트 ★
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = etSearch.getText().toString().trim();

                ArrayList<String> searchResult;

                if (keyword.isEmpty()) {
                    // 검색어가 없으면 전체 목록 표시
                    searchResult = dbHelper.getAllFoodList();
                    Toast.makeText(FoodInformationActivity.this, "전체 목록을 표시합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 검색어가 있으면 검색 결과 표시
                    searchResult = dbHelper.searchFoodList(keyword);
                    if (searchResult.isEmpty()) {
                        searchResult.add("검색 결과가 없습니다.");
                    }
                }

                // 리스트뷰 갱신
                adapter = new ArrayAdapter<>(
                        FoodInformationActivity.this,
                        android.R.layout.simple_list_item_1,
                        searchResult);
                listView.setAdapter(adapter);
            }
        });

        // 데이터 존재 여부 확인 후 로딩
        if (!dbHelper.isDataExists()) {
            loadCsvAndInsertToDB();
        } else {
            loadListFromDB();
        }
    }

    private void loadCsvAndInsertToDB() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("데이터베이스 구축 중입니다...\n(약 3~5초 소요)");
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

                        // ★ [수정된 부분] 단순 쉼표(,)가 아니라 정규식을 사용하여 분리합니다. ★
                        // 정규식 설명: "따옴표 안에 포함되지 않은 쉼표"만 찾아서 자릅니다.
                        // 예: "장류, 양념류",100g -> ["장류, 양념류", "100g"] 로 정상 분리됨
                        String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        if (parts.length >= 4) {
                            String name = parts[0];
                            String category = parts[1];
                            String amount = parts[2];
                            String calories = parts[3];

                            // ★ [추가] 데이터에 붙어있을 수 있는 따옴표(") 제거 ★
                            // 예: "장류, 양념류" -> 장류, 양념류
                            name = deleteQuote(name);
                            category = deleteQuote(category);
                            amount = deleteQuote(amount);
                            calories = deleteQuote(calories);

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
                            Toast.makeText(FoodInformationActivity.this, "데이터 구축 완료 (" + dataList.size() + "개)", Toast.LENGTH_SHORT).show();
                            loadListFromDB();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null) progressDialog.dismiss();
                            Toast.makeText(FoodInformationActivity.this, "오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    // ★ 따옴표 제거 헬퍼 함수 추가 ★
    private String deleteQuote(String str) {
        if (str != null && str.length() >= 2 && str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    private void loadListFromDB() {
        ArrayList<String> foodList = dbHelper.getAllFoodList();
        if (foodList.isEmpty()) {
            foodList.add("데이터가 없습니다.");
        }
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, foodList);
        listView.setAdapter(adapter);
    }
}