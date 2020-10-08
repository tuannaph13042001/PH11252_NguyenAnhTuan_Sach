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

import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDung;
import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDungAdapter;
import com.example.ph11252_nguyenanhtuan_duanmau.TheLoai.TheLoai;
import com.example.ph11252_nguyenanhtuan_duanmau.TheLoai.TheLoaiAdapter;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.TheLoaiDAO;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.UserDAO;

import java.util.List;

public class QuanLyTheLoaiActivity extends AppCompatActivity {
    private ListView listView;
    private MySQlite mySQlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_the_loai);

        listView = findViewById(R.id.lvList);
        mySQlite = new MySQlite(this);
        final TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQlite);

        List<TheLoai> theLoaiList = theLoaiDAO.getAllTheLoai();
        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList);
        listView.setAdapter(theLoaiAdapter);
        EditText edtTim = findViewById(R.id.edtTim);

        edtTim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    List<TheLoai> theLoaiList = theLoaiDAO.getAllTheLoai();
                    TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList);
                    listView.setAdapter(theLoaiAdapter);
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
            View view = LayoutInflater.from(this).inflate(R.layout.activity_them_the_loai, null);
            builder.setView(view);

            final EditText edt_maTheLoai = view.findViewById(R.id.edt_maTheLoai);
            final EditText edt_tenTheLoai = view.findViewById(R.id.edt_tenTheLoai);
            final EditText edt_moTa = view.findViewById(R.id.edt_moTa);
            final EditText edt_viTri = view.findViewById(R.id.edt_viTri);
            Button button = view.findViewById(R.id.button);
            Button button2 = view.findViewById(R.id.button2);
            final AlertDialog alertDialog = builder.show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TheLoai theLoai = new TheLoai();
                    theLoai.maTheLoai = edt_maTheLoai.getText().toString().trim();
                    theLoai.tenTheLoai = edt_tenTheLoai.getText().toString().trim();
                    theLoai.moTa = edt_moTa.getText().toString().trim();
                    theLoai.viTri = edt_viTri.getText().toString().trim();

                    checkEmpty(theLoai.maTheLoai, edt_maTheLoai);
                    checkEmpty(theLoai.tenTheLoai, edt_tenTheLoai);
                    checkEmpty(theLoai.moTa, edt_moTa);
                    checkEmpty(theLoai.viTri, edt_viTri);

                    TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQlite);

                    boolean ketQua = theLoaiDAO.themTheLoai(theLoai);
                    if (ketQua) {
                        Toast.makeText(QuanLyTheLoaiActivity.this,
                                "THANH CONG!!!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                        List<TheLoai> theLoaiList = theLoaiDAO.getAllTheLoai();
                        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList);
                        listView.setAdapter(theLoaiAdapter);
                    } else {
                        Toast.makeText(QuanLyTheLoaiActivity.this,
                                "KHONG THANH CONG!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            button2.setOnClickListener(new View.OnClickListener() {
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
    public void searchTheLoai(View view) {
        EditText edtTim = findViewById(R.id.edtTim);
        String maTheLoai = edtTim.getText().toString().trim();
        if (maTheLoai.isEmpty()) {
            edtTim.setError("NHAP DU LIEU TRC");
            return;
        }
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(mySQlite);
        List<TheLoai> theLoaiList = theLoaiDAO.timKiemMaTheLoai(maTheLoai);

        if (theLoaiList.size() == 0) {
            edtTim.setError("KHONG TIM THAY USER NAO!!!!");
        } else {
            TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theLoaiList);
            listView.setAdapter(theLoaiAdapter);
        }
    }
}
