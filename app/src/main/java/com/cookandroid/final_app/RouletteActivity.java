package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Intent 추가
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class RouletteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<String> menuList;

    TextView tvResult, tvCategoryInfo;
    Button btnAction, btnBack, btnRecord;

    boolean isRolling = false;
    Handler handler = new Handler();
    Random random = new Random();

    Runnable rouletteRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRolling && !menuList.isEmpty()) {
                int randomIndex = random.nextInt(menuList.size());
                tvResult.setText(menuList.get(randomIndex));
                handler.postDelayed(this, 50);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ★ 중요: XML 레이아웃에 버튼을 추가해야 하므로, 아래 XML 코드도 꼭 적용해야 합니다. ★
        setContentView(R.layout.activity_roulette);

        dbHelper = new DBHelper(this);

        tvResult = findViewById(R.id.tv_result_menu);
        tvCategoryInfo = findViewById(R.id.tv_category_info);
        btnAction = findViewById(R.id.btn_roulette_action);
        btnBack = findViewById(R.id.btn_back_roulette);
        btnRecord = findViewById(R.id.btn_go_record); // 기록하기 버튼 연결

        // 처음엔 기록하기 버튼 숨김
        btnRecord.setVisibility(View.GONE);

        String category = getIntent().getStringExtra("category");
        if (category == null) category = "";

        tvCategoryInfo.setText("[" + category + "] 중에서 선택 중...");
        menuList = dbHelper.getMenuByCategory(category);

        if (menuList.isEmpty()) {
            tvResult.setText("데이터 없음");
            btnAction.setEnabled(false);
            Toast.makeText(this, "해당 카테고리에 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRolling) {
                    // 시작
                    isRolling = true;
                    btnAction.setText("멈춤");
                    btnRecord.setVisibility(View.GONE); // 다시 돌릴 땐 기록 버튼 숨김
                    handler.post(rouletteRunnable);
                } else {
                    // 멈춤
                    isRolling = false;
                    btnAction.setText("다시 뽑기");
                    handler.removeCallbacks(rouletteRunnable);

                    // ★ 멈추면 기록하기 버튼 표시 ★
                    btnRecord.setVisibility(View.VISIBLE);

                    Toast.makeText(RouletteActivity.this,
                            "[" + tvResult.getText() + "] 당첨! 기록해보세요.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ★ 기록하기 버튼 클릭 시 DiaryActivity로 이동 ★
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouletteActivity.this, DiaryActivity.class);
                // 현재 뽑힌 메뉴 이름을 전달
                intent.putExtra("menuName", tvResult.getText().toString());
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRolling = false;
                handler.removeCallbacks(rouletteRunnable);
                finish();
            }
        });
    }
}