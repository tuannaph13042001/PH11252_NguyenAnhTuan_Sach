package com.example.ph11252_nguyenanhtuan_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;




import com.example.ph11252_nguyenanhtuan_duanmau.Sach.Sach;
import com.example.ph11252_nguyenanhtuan_duanmau.Sach.SachAdapter;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.SachDAO;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.UserDAO;


import java.util.List;

public class QuanLySachActivity extends AppCompatActivity {
    private ListView listView;
    private MySQlite mySQlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);

        listView = findViewById(R.id.lvList);
        mySQlite = new MySQlite(this);
        final SachDAO sachDAO = new SachDAO(mySQlite);

        List<Sach> sachList = sachDAO.getAllSach();
        SachAdapter sachAdapter = new SachAdapter(sachList);
        listView.setAdapter(sachAdapter);
        EditText edtTim = findViewById(R.id.edtTim);

        edtTim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    List<Sach> sachList = sachDAO.getAllSach();
                    SachAdapter sachAdapter = new SachAdapter(sachList);
                    listView.setAdapter(sachAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.activity_them_sach, null);
            builder.setView(view);

            final EditText edt_MaSach = view.findViewById(R.id.edt_maSach);
            final EditText edt_TenSach = view.findViewById(R.id.edt_tenSach);
            final EditText edt_GiaSach = view.findViewById(R.id.edt_GiaSach);
            final EditText edt_LoaiSach = view.findViewById(R.id.edt_loaiSach);
            final EditText edt_NXB = view.findViewById(R.id.edt_NXB);
            final EditText edt_TacGia = view.findViewById(R.id.edt_tacGia);
            final EditText edt_SoLuong = view.findViewById(R.id.edt_soLuong);
            Button btn_Them = view.findViewById(R.id.btn_Them);
            Button btn_CANCEL = view.findViewById(R.id.btn_cancel);
            final AlertDialog alertDialog = builder.show();
            btn_Them.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Sach sach = new Sach();
                    sach.maSach = edt_MaSach.getText().toString().trim();
                    sach.tenSACH = edt_TenSach.getText().toString().trim();
                    sach.giaBan = Float.parseFloat(edt_GiaSach.getText().toString().trim());
                    sach.maTheLoai = edt_LoaiSach.getText().toString().trim();
                    sach.nhaXuatBan = edt_NXB.getText().toString().trim();
                    sach.tacGia = edt_TacGia.getText().toString().trim();
                    sach.soLuong = String.valueOf(Integer.parseInt(edt_SoLuong.getText().toString().trim()));

                    checkEmpty(sach.maSach, edt_MaSach);
                    checkEmpty(sach.tenSACH, edt_TenSach);
                    checkEmpty(String.valueOf(sach.giaBan), edt_GiaSach);
                    checkEmpty(sach.maTheLoai, edt_LoaiSach);
                    checkEmpty(sach.nhaXuatBan, edt_NXB);
                    checkEmpty(sach.tacGia, edt_TacGia);
                    checkEmpty(sach.soLuong, edt_SoLuong);

                    SachDAO sachDAO = new SachDAO(mySQlite);

                    boolean ketQua = sachDAO.themSach(sach);
                    if (ketQua) {
                        Toast.makeText(QuanLySachActivity.this,
                                "THANH CONG!!!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                        List<Sach> sachList = sachDAO.getAllSach();
                        SachAdapter sachAdapter = new SachAdapter(sachList);
                        listView.setAdapter(sachAdapter);
                    } else {
                        Toast.makeText(QuanLySachActivity.this,
                                "KHONG THANH CONG!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            btn_CANCEL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkEmpty(String data, EditText edt) {
        if (data.isEmpty()) {
            edt.setError("Nhap du thong tin...");
            return;
        }
    }

    public void searchSACH(View view) {
        EditText edtTim = findViewById(R.id.edtTim);
        String username = edtTim.getText().toString().trim();
        if (username.isEmpty()) {
            edtTim.setError("NHAP DU LIEU TRC");
            return;
        }
        SachDAO sachDAO = new SachDAO(mySQlite);
        List<Sach> sachList = sachDAO.timKiemMaSach(username);

        if (sachList.size() == 0) {
            edtTim.setError("KHONG TIM THAY USER NAO!!!!");
        } else {
            SachAdapter sachAdapter = new SachAdapter(sachList);
            listView.setAdapter(sachAdapter);
        }

    }
}
