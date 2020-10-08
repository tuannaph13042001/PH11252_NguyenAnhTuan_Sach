package com.example.ph11252_nguyenanhtuan_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;

public class ChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQlite mySQLite = new MySQlite(this);
    }
}
