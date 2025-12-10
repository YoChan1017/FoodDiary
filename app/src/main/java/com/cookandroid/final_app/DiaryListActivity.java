package com.cookandroid.final_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryListActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    DiaryAdapter adapter;
    ArrayList<DiaryItem> diaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listView_diary);
        Button btnBack = findViewById(R.id.btn_back_list);

        // 1. DB에서 데이터 가져오기
        diaryList = dbHelper.getDiaryList();

        // 2. 어댑터 연결
        if (diaryList.isEmpty()) {
            // 데이터 없을 경우 처리 (여기서는 빈 리스트 보여줌)
        }

        adapter = new DiaryAdapter(this, diaryList, dbHelper);
        listView.setAdapter(adapter);

        // 3. 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 화면이 다시 보일 때 데이터 갱신 (삭제 등 반영 확실하게 하기 위해)
        diaryList.clear();
        diaryList.addAll(dbHelper.getDiaryList());
        if(adapter != null) adapter.notifyDataSetChanged();
    }
}