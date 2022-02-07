package com.example.testjavafx;
//класс для хранения прививок кота
public class Med {
    String Date;
    String Type;
    String Title;

    public Med()
    {

    }

    public Med(String Date, String Type, String Title)
    {
        this.Date = Date;
        this.Title =Title;
        this.Type = Type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
