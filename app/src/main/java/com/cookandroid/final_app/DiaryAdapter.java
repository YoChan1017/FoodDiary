package com.cookandroid.final_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {

    Context context;
    ArrayList<DiaryItem> items;
    DBHelper dbHelper;

    public DiaryAdapter(Context context, ArrayList<DiaryItem> items, DBHelper dbHelper) {
        this.context = context;
        this.items = items;
        this.dbHelper = dbHelper;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_diary, parent, false);
        }

        // 현재 위치의 데이터 가져오기
        DiaryItem item = items.get(position);

        // UI 연결
        TextView tvDate = convertView.findViewById(R.id.item_tv_date);
        TextView tvTitle = convertView.findViewById(R.id.item_tv_title);
        Button btnView = convertView.findViewById(R.id.item_btn_view);
        Button btnDelete = convertView.findViewById(R.id.item_btn_delete);

        // 데이터 세팅
        tvDate.setText(item.getDate());
        tvTitle.setText(item.getTitle());

        // ★ [보기 버튼] 클릭 시 상세 정보 다이얼로그 띄우기 ★
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailDialog(item);
            }
        });

        // ★ [삭제 버튼] 클릭 시 DB 삭제 및 리스트 갱신 ★
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 삭제 확인 팝업
                new AlertDialog.Builder(context)
                        .setTitle("일기 삭제")
                        .setMessage("정말로 이 기록을 삭제하시겠습니까?")
                        .setPositiveButton("네", (dialog, which) -> {
                            // DB에서 삭제
                            dbHelper.deleteDiary(item.getId());
                            // 리스트에서 삭제
                            items.remove(position);
                            // 화면 갱신
                            notifyDataSetChanged();
                            Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            }
        });

        return convertView;
    }

    // 상세 정보 다이얼로그 표시 함수
    private void showDetailDialog(DiaryItem item) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_diary_detail);

        // 다이얼로그 크기 조절 (가로 꽉 차게)
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 다이얼로그 UI 연결
        TextView tvDate = dialog.findViewById(R.id.detail_tv_date);
        TextView tvTitle = dialog.findViewById(R.id.detail_tv_title);
        TextView tvMenu = dialog.findViewById(R.id.detail_tv_menu);
        TextView tvContent = dialog.findViewById(R.id.detail_tv_content);
        ImageView ivImage = dialog.findViewById(R.id.detail_iv_image);
        Button btnClose = dialog.findViewById(R.id.detail_btn_close);

        // 데이터 채워넣기
        tvDate.setText(item.getDate());
        tvTitle.setText(item.getTitle());
        tvMenu.setText(item.getMenu());
        tvContent.setText(item.getContent());

        // 이미지 로드
        if (item.getImageUri() != null && !item.getImageUri().isEmpty()) {
            ivImage.setVisibility(View.VISIBLE);
            ivImage.setImageURI(Uri.parse(item.getImageUri()));
        } else {
            ivImage.setVisibility(View.GONE); // 이미지 없으면 숨김
        }

        // 닫기 버튼
        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}