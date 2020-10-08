package com.example.ph11252_nguyenanhtuan_duanmau.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private MySQlite mySQlite;
    public UserDAO(MySQlite mySQlite){
        this.mySQlite = mySQlite;
    }
    //them
    public boolean addUser(NguoiDung nguoiDung) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySQlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        // truy vấn 3
        long kq = sqLiteDatabase.insert("USER", null, contentValues);

        if (kq > 0) return true;
        else return false;

    }

    // update
    public boolean updateUser(NguoiDung nguoiDung) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySQlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        // truy vấn 3
        long kq = sqLiteDatabase.update("USER", contentValues, "username=?",
                new String[]{nguoiDung.username});

        if (kq > 0) return true;
        else return false;

    }

    // del
    public boolean delUser(String username) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySQlite.getWritableDatabase();

        // truy vấn 3
        long kq = sqLiteDatabase.delete("USER", "username=?",
                new String[]{username});

        if (kq > 0) return true;
        else return false;

    }

    // get all

    public List<NguoiDung> getAllUsers() {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM USER";
        Cursor cursor = mySQlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String std = cursor.getString(cursor.getColumnIndex("std"));

                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = username;
                nguoiDung.ten = name;
                nguoiDung.password = password;
                nguoiDung.sdt = std;


                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }

        return nguoiDungList;
    }


    public List<NguoiDung> timKiemUsername(String TimUsername) {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM USER WHERE username LIKE '%" + TimUsername + "%'";
        Cursor cursor = mySQlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String std = cursor.getString(cursor.getColumnIndex("std"));

                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = username;
                nguoiDung.ten = name;
                nguoiDung.password = password;
                nguoiDung.sdt = std;

                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }

        return nguoiDungList;
    }


}
