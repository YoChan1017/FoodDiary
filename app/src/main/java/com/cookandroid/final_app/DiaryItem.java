package com.cookandroid.final_app;

public class DiaryItem {
    int _id;
    String date;
    String title;
    String menu;
    String content;
    String imageUri;

    public DiaryItem(int _id, String date, String title, String menu, String content, String imageUri) {
        this._id = _id;
        this.date = date;
        this.title = title;
        this.menu = menu;
        this.content = content;
        this.imageUri = imageUri;
    }

    // Getter 메소드들
    public int getId() { return _id; }
    public String getDate() { return date; }
    public String getTitle() { return title; }
    public String getMenu() { return menu; }
    public String getContent() { return content; }
    public String getImageUri() { return imageUri; }
}