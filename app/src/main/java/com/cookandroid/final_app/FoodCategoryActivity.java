package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Intent 추가
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FoodCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_category);

        Button btnBack = findViewById(R.id.btn_back_category);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView listView = findViewById(R.id.listView_category);

        // CSV 파일의 카테고리 명과 정확히 일치해야 DB 조회가 됩니다.
        String[] categories = {
                "빵 및 과자류", "면 및 만두류", "밥류", "찌개 및 전골류", "음료 및 차류",
                "곡류, 서류 제품", "수·조·어·육류", "젓갈류", "장아찌·절임류", "장류, 양념류",
                "유제품류 및 빙과류", "김치류", "생채·무침류", "나물·숙채류", "튀김류",
                "볶음류", "조림류", "전·적 및 부침류", "구이류", "찜류", "국 및 탕류",
                "죽 및 스프류", "과일류", "두류, 견과 및 종실류", "채소, 해조류"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(adapter);

        // ★ 리스트 클릭 시 룰렛 화면으로 이동 ★
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];

                // 룰렛 화면(RouletteActivity)으로 이동하면서 카테고리 정보 전달
                Intent intent = new Intent(FoodCategoryActivity.this, RouletteActivity.class);
                intent.putExtra("category", selectedCategory); // "category"라는 이름표로 데이터 전달
                startActivity(intent);
            }
        });
    }
}