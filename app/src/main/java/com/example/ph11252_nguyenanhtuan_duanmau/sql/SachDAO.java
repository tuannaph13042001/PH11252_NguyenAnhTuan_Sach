package com.example.ph11252_nguyenanhtuan_duanmau.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph11252_nguyenanhtuan_duanmau.Sach.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private MySQlite mySQLite;
    public SachDAO(MySQlite mySQLite){
        this.mySQLite = mySQLite;
    }

    public boolean  themSach(Sach sach){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSach",sach.maSach);
        contentValues.put("MaTheLoai",sach.maTheLoai);
        contentValues.put("TenSACH",sach.tenSACH);
        contentValues.put("TacGia",sach.tacGia);
        contentValues.put("NhaXuatBan",sach.nhaXuatBan);
        contentValues.put("GiaBan",sach.giaBan);
        contentValues.put("SoLuong",sach.soLuong);
        long kq = sqLiteDatabase.insert("Sach", null,contentValues);
        if (kq>0) return true;
        else return false;
    }
    public List<Sach> getAllSach(){
        List<Sach>sachList = new ArrayList<>();
        String truyVan = "SELECT* FROM Sach";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(truyVan,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                String maSach = cursor.getString(cursor.getColumnIndex("MaSach"));
                String maTheLoai = cursor.getString(cursor.getColumnIndex("MaTheLoai"));
                String tenSACH = cursor.getString(cursor.getColumnIndex("TenSach"));
                String tacGia = cursor.getString(cursor.getColumnIndex("TacGia"));
                String nhaXuatBan = cursor.getString(cursor.getColumnIndex("NhaXuatBan"));
                float giaBan = Float.parseFloat(cursor.getString(cursor.getColumnIndex("GiaBan")));
                String soLuong = cursor.getString(cursor.getColumnIndex("SoLuong"));

                Sach sach = new Sach();
                sach.maSach = maSach;
                sach.maTheLoai = maTheLoai;
                sach.tenSACH = tenSACH;
                sach.tacGia = tacGia;
                sach.nhaXuatBan = nhaXuatBan;
                sach.giaBan = giaBan;
                sach.soLuong = Integer.parseInt(soLuong);
                sachList.add(sach);

                cursor.moveToNext();

            }
            cursor.close();

        }
        return sachList;
    }
    public boolean suaSach(Sach sach){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSach",sach.maSach);
        contentValues.put("MaTheLoai",sach.maTheLoai);
        contentValues.put("TenSach",sach.tenSACH);
        contentValues.put("TacGia",sach.tacGia);
        contentValues.put("NhaXuatBan",sach.nhaXuatBan);
        contentValues.put("GiaBan",sach.giaBan);
        contentValues.put("SoLuong",sach.soLuong);

        long kq = sqLiteDatabase.update("sach", contentValues,
                "MaSach =?", new String[]{sach.maSach});
        if (kq>0) return true;
        else  return false;
    }
    public boolean xoaSach(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        long kq = sqLiteDatabase.delete("Sach", "MaSach=?", new String[]{id});
        if (kq > 0) return true;
        else return false;
    }
    public List<Sach> timKiemMaSach(String TimMa) {
        List<Sach> sachList = new ArrayList<>();
        String sql = "SELECT * FROM Sach WHERE MaSach LIKE '%" + TimMa + "%'";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maSach = cursor.getString(cursor.getColumnIndex("MaSach"));
                String maTheLoai = cursor.getString(cursor.getColumnIndex("MaTheLoai"));
                String tenSACH = cursor.getString(cursor.getColumnIndex("TenSach"));
                String tacGia = cursor.getString(cursor.getColumnIndex("TacGia"));
                String nhaXuatBan = cursor.getString(cursor.getColumnIndex("NhaXuatBan"));
                float giaBan = Float.parseFloat(cursor.getString(cursor.getColumnIndex("GiaBan")));
                String soLuong = cursor.getString(cursor.getColumnIndex("SoLuong"));

                Sach sach = new Sach();
                sach.maSach = maSach;
                sach.maTheLoai = maTheLoai;
                sach.tenSACH = tenSACH;
                sach.tacGia = tacGia;
                sach.nhaXuatBan = nhaXuatBan;
                sach.giaBan = giaBan;
                sach.soLuong = Integer.parseInt(soLuong);
                sachList.add(sach);
                cursor.moveToNext();
            }
        }

        return sachList;
    }
}
