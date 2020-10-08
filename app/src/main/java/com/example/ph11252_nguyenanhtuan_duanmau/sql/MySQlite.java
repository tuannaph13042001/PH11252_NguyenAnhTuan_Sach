package com.example.ph11252_nguyenanhtuan_duanmau.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQlite extends SQLiteOpenHelper {
    public MySQlite(@Nullable Context context) {
        super(context, "data.sql", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_table = "CREATE TABLE USER (username char(15) primary key" +
                ",name varchar(50),password varchar" +
                ",numberPhone char)";

        String table_book = "create table Sach(MaSach NVARCHAR(10)primary key, MaTheLoai NVARCHAR(10) NOT NULL, " +
                "TenSach NVARCHAR(50) NOT NULL, NhaXuatBan NVARCHAR(50) NOT NULL, TacGia NVARCHAR(50) NOT NULL," +
                "GiaBan FLOAT NOT NULL, SoLuong INTEGER NOT NULL)";
        String table_type = "create table The_Loai(maTheLoai NVARCHAR(10) primary key, tenTheLoai NVARCHAR(50) NOT NULL," +
                "moTa NVARCHAR(255) NOT NULL, viTri NVARCHAR(50))";
        String table_bill_detail = "create table bill(maHoaDonChiTiet NVARCHAR(10) primary key, tenKhachHang NVARCHAR(50) NOT NULL," +
                "tenSach NVARCHAR(50) NOT NULL, loaiSach NVARCHAR(50) NOT NULL," +
                "tacGia NVARCHAR(50) NOT NULL," +
                "soLuong INTEGER NOT NULL," +
                "donGia FLOAT NOT NULL," +
                "nhaXuatBan NVARCHAR(50) NOT NULL)";
        String table_bill = "create table bill(maHoaDon NVARCHAR(10) primary key, maHoaDonChiTiet NVARCHAR(10) NOT NULL," +
                "ngayMua date NOT NULL)" ;
        db.execSQL(user_table);
        db.execSQL(table_book);
        db.execSQL(table_type);
        db.execSQL(table_bill_detail);
        db.execSQL(table_bill);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
