package com.example.ph11252_nguyenanhtuan_duanmau.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDung;
import com.example.ph11252_nguyenanhtuan_duanmau.TheLoai.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private MySQlite mySQLite;
    public TheLoaiDAO(MySQlite mySQLite){
        this.mySQLite = mySQLite;
    }

    public boolean  themTheLoai(TheLoai theLoai){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTheLoai",theLoai.maTheLoai);
        contentValues.put("tenTheLoai",theLoai.tenTheLoai);
        contentValues.put("moTa",theLoai.moTa);
        contentValues.put("viTri",theLoai.viTri);
        long kq = sqLiteDatabase.insert("The_Loai", null, contentValues);

        if (kq > 0) return true;
        else return false;
    }
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai>theLoaiList = new ArrayList<>();
        String truyVan = "SELECT* FROM sach";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(truyVan,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){

                String maTheLoai = cursor.getString(cursor.getColumnIndex("maTheLoai"));
                String tenTheLoai = cursor.getString(cursor.getColumnIndex("tenTheLoai"));
                String moTa = cursor.getString(cursor.getColumnIndex("moTa"));
                String viTri = cursor.getString(cursor.getColumnIndex("viTri"));

                TheLoai theLoai = new TheLoai();
                theLoai.maTheLoai = maTheLoai;
                theLoai.tenTheLoai = tenTheLoai;
                theLoai.moTa = moTa;
                theLoai.viTri = viTri;

                theLoaiList.add(theLoai);

                cursor.moveToNext();

            }
            cursor.close();

        }
        return theLoaiList;
    }
    public int suaTheLoai(TheLoai theLoai){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTheLoai",theLoai.maTheLoai);
        contentValues.put("tenTheLoai",theLoai.tenTheLoai);
        contentValues.put("moTa",theLoai.moTa);
        contentValues.put("viTri",theLoai.viTri);
        return sqLiteDatabase.update("The_Loai",contentValues,
                "maTheLoai =?", new String[]{theLoai.maTheLoai});
    }
    public boolean xoaTheLoai(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("The_Loai", "maTheLoai=?",
                new String[]{id});

        if (kq > 0) return true;
        else return false;
    }

    public List<TheLoai> timKiemMaTheLoai(String TimMa) {
        List<TheLoai> theLoaiList = new ArrayList<>();
        String sql = "SELECT * FROM The_Loai WHERE maTheLoai LIKE '%" + TimMa + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maTheLoai = cursor.getString(cursor.getColumnIndex("maTheLoai"));
                String tenTheLoai = cursor.getString(cursor.getColumnIndex("tenTheLoai"));
                String moTa = cursor.getString(cursor.getColumnIndex("moTa"));
                String viTri = cursor.getString(cursor.getColumnIndex("viTri"));

                TheLoai theLoai = new TheLoai();
                theLoai.maTheLoai = maTheLoai;
                theLoai.tenTheLoai = tenTheLoai;
                theLoai.moTa = moTa;
                theLoai.viTri = viTri;

                theLoaiList.add(theLoai);
                cursor.moveToNext();
            }
        }

        return theLoaiList;
    }

}
