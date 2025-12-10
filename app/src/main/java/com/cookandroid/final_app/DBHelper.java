package com.cookandroid.final_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FoodDB";
    private static final int DB_VERSION = 4; // 테이블 구조가 변경되었으므로 버전 확인 필요
    private static final String TABLE_FOOD = "food_table";
    private static final String TABLE_DIARY = "diary_table";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. 음식 정보 테이블 생성
        db.execSQL("CREATE TABLE " + TABLE_FOOD + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "category TEXT, " +
                "amount TEXT, " +
                "calories TEXT);");

        // 2. 일기 테이블 생성 (날짜, 제목, 메뉴, 내용, 이미지경로)
        db.execSQL("CREATE TABLE " + TABLE_DIARY + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "title TEXT, " +
                "menu TEXT, " +
                "content TEXT, " +
                "image_uri TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 버전 변경 시 기존 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        onCreate(db);
    }

    // ==========================================
    // [음식 데이터 관련 메소드]
    // ==========================================

    // 대량의 음식 데이터를 고속으로 저장 (트랜잭션 사용)
    public void insertAllFood(ArrayList<String[]> dataList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // 트랜잭션 시작
        try {
            String sql = "INSERT INTO " + TABLE_FOOD + " (name, category, amount, calories) VALUES (?, ?, ?, ?)";
            SQLiteStatement stmt = db.compileStatement(sql);

            for (String[] row : dataList) {
                stmt.bindString(1, row[0]); // name
                stmt.bindString(2, row[1]); // category
                stmt.bindString(3, row[2]); // amount
                stmt.bindString(4, row[3]); // calories
                stmt.execute();
                stmt.clearBindings();
            }
            db.setTransactionSuccessful(); // 성공 처리
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // 트랜잭션 종료 (반영)
            db.close();
        }
    }

    // 음식 데이터가 이미 존재하는지 확인 (중복 구축 방지)
    public boolean isDataExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + TABLE_FOOD, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    // 모든 음식 목록 가져오기 (음식 정보 열람용)
    public ArrayList<String> getAllFoodList() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FOOD, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            String calories = cursor.getString(4);
            list.add(name + " - " + category + " - " + calories + "kcal");
        }
        cursor.close();
        db.close();
        return list;
    }

    // 특정 카테고리의 메뉴 이름만 가져오기 (룰렛 게임용)
    public ArrayList<String> getMenuByCategory(String categoryName) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_FOOD + " WHERE category = ?", new String[]{categoryName});

        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return list;
    }

    // ==========================================
    // [일기 데이터 관련 메소드]
    // ==========================================

    // 일기 저장하기 (제목, 이미지 경로 포함)
    public void insertDiary(String date, String title, String menu, String content, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_DIARY + " (date, title, menu, content, image_uri) VALUES (?, ?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);

        stmt.bindString(1, date);
        stmt.bindString(2, title);
        stmt.bindString(3, menu);
        stmt.bindString(4, content);

        // 이미지가 없을 경우 빈 문자열 처리
        if (imageUri == null) imageUri = "";
        stmt.bindString(5, imageUri);

        stmt.execute();
        db.close();
    }

    // 저장된 일기 목록 전체 가져오기 (최신순 정렬)
    public ArrayList<DiaryItem> getDiaryList() {
        ArrayList<DiaryItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // _id 내림차순(최신순)으로 조회
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DIARY + " ORDER BY _id DESC", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String title = cursor.getString(2);
            String menu = cursor.getString(3);
            String content = cursor.getString(4);
            String imageUri = cursor.getString(5);

            items.add(new DiaryItem(id, date, title, menu, content, imageUri));
        }
        cursor.close();
        db.close();
        return items;
    }

    // 특정 일기 삭제하기
    public void deleteDiary(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DIARY + " WHERE _id = " + id);
        db.close();
    }

    // ★ [추가] 검색 기능 메소드 ★
    public ArrayList<String> searchFoodList(String keyword) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // name 컬럼에 keyword가 포함된 데이터 검색 (LIKE %keyword%)
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE name LIKE '%" + keyword + "%'";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            String calories = cursor.getString(4);
            list.add(name + " - " + category + " - " + calories + "kcal");
        }
        cursor.close();
        db.close();
        return list;
    }
}