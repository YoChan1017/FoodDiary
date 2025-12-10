package com.cookandroid.final_app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView tvDate, tvMenu;
    EditText etTitle, etContent; // ★ etTitle 추가
    ImageView ivFoodImage;
    Button btnSave, btnBack;

    Calendar myCalendar = Calendar.getInstance();
    String selectedImageUriString = "";

    ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    ivFoodImage.setImageURI(selectedImageUri);
                    ivFoodImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    selectedImageUriString = selectedImageUri.toString();

                    try {
                        getContentResolver().takePersistableUriPermission(
                                selectedImageUri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        );
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        dbHelper = new DBHelper(this);

        tvDate = findViewById(R.id.tv_diary_date);
        etTitle = findViewById(R.id.et_diary_title); // ★ 제목 EditText 연결
        tvMenu = findViewById(R.id.tv_diary_menu);
        etContent = findViewById(R.id.et_diary_content);
        ivFoodImage = findViewById(R.id.iv_food_image);
        btnSave = findViewById(R.id.btn_save_diary);
        btnBack = findViewById(R.id.btn_back_diary);

        updateLabel();

        String menuName = getIntent().getStringExtra("menuName");
        if (menuName != null) {
            tvMenu.setText(menuName);
        } else {
            tvMenu.setText("메뉴 정보 없음");
        }

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DiaryActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ivFoodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                galleryLauncher.launch(intent);
            }
        });

        // --- 저장 버튼 ---
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateText = tvDate.getText().toString();
                String title = etTitle.getText().toString(); // ★ 제목 가져오기
                String content = etContent.getText().toString();

                if (title.isEmpty()) { // ★ 제목 입력 확인
                    Toast.makeText(DiaryActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (content.isEmpty()) {
                    Toast.makeText(DiaryActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ★ DB에 저장 (제목 title 포함) ★
                dbHelper.insertDiary(dateText, title, tvMenu.getText().toString(), content, selectedImageUriString);

                Toast.makeText(DiaryActivity.this, "일기가 저장되었습니다!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DiaryActivity.this, SelectMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy.MM.dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        tvDate.setText(sdf.format(myCalendar.getTime()));
    }
}