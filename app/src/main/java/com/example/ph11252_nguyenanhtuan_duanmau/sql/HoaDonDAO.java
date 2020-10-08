package com.example.ph11252_nguyenanhtuan_duanmau.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph11252_nguyenanhtuan_duanmau.HoaDon.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private MySQlite mySQLite;
    public HoaDonDAO(MySQlite mySQLite){
        this.mySQLite = mySQLite;
    }

    public long  themHDCT(HoaDonChiTiet hoaDonChiTiet){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaHDCT",hoaDonChiTiet.maHDCT);
        contentValues.put("MaHoaDon",hoaDonChiTiet.maHoaDon);
        contentValues.put("MaSach",hoaDonChiTiet.maSach);
        contentValues.put("TacGia",hoaDonChiTiet.tacGia);
        contentValues.put("TenSach",hoaDonChiTiet.tenSach);
        contentValues.put("LoaiSach",hoaDonChiTiet.loaiSach);
        contentValues.put("NhaXuatBan",hoaDonChiTiet.nhaXuatBan);
        contentValues.put("GiaBan",hoaDonChiTiet.giaBan);
        contentValues.put("SoLuong",hoaDonChiTiet.soLuongMua);
        return sqLiteDatabase.insert("HoaDonChiTiet", null,contentValues);
    }
    public List<HoaDonChiTiet> getAllHDCT(){
        List<HoaDonChiTiet>HDCTList = new ArrayList<>();
        String truyVan = "SELECT* FROM hoaDonChiTiet";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(truyVan,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                String maHDCT = cursor.getString(cursor.getColumnIndex("MaHDCT"));
                String maHoaDon = cursor.getString(cursor.getColumnIndex("MaHoaDon"));
                String maSach = cursor.getString(cursor.getColumnIndex("MaSach"));
                String tenSACH = cursor.getString(cursor.getColumnIndex("TenSACH"));
                String loaiSach = cursor.getString(cursor.getColumnIndex("LoaiSach"));
                String tacGia = cursor.getString(cursor.getColumnIndex("TacGia"));
                String nhaXuatBan = cursor.getString(cursor.getColumnIndex("NhaXuatBan"));
                String giaBan = cursor.getString(cursor.getColumnIndex("GiaBan"));
                String soLuongMua = cursor.getString(cursor.getColumnIndex("SoLuongMua"));

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.maSach = maSach;
                hoaDonChiTiet.maHDCT = maHDCT;
                hoaDonChiTiet.tenSach = tenSACH;
                hoaDonChiTiet.maHoaDon = maHoaDon;
                hoaDonChiTiet.loaiSach = loaiSach;
                hoaDonChiTiet.tacGia = tacGia;
                hoaDonChiTiet.nhaXuatBan = nhaXuatBan;
                hoaDonChiTiet.giaBan = Float.parseFloat(giaBan);
                hoaDonChiTiet.soLuongMua = Integer.parseInt(soLuongMua);
                HDCTList.add(hoaDonChiTiet);

                cursor.moveToNext();

            }
            cursor.close();

        }
        return HDCTList;
    }
    public int suaSach(HoaDonChiTiet hoaDonChiTiet){
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaHDCT",hoaDonChiTiet.maHDCT);
        contentValues.put("MaHoaDon",hoaDonChiTiet.maHoaDon);
        contentValues.put("MaSach",hoaDonChiTiet.maSach);
        contentValues.put("TacGia",hoaDonChiTiet.tacGia);
        contentValues.put("TenSach",hoaDonChiTiet.tenSach);
        contentValues.put("LoaiSach",hoaDonChiTiet.loaiSach);
        contentValues.put("NhaXuatBan",hoaDonChiTiet.nhaXuatBan);
        contentValues.put("GiaBan",hoaDonChiTiet.giaBan);
        contentValues.put("SoLuong",hoaDonChiTiet.soLuongMua);

        return sqLiteDatabase.update("hoaDonChiTiet", contentValues,
                "maHDCT =?", new String[]{hoaDonChiTiet.maHDCT});
    }
    public void xoaSach(String id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        sqLiteDatabase.delete("hoaDonChiTiet", "maHDCT=?", new String[]{id});
    }

}
