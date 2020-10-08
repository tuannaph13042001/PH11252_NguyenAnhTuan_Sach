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

import java.util.List;

import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDung;
import com.example.ph11252_nguyenanhtuan_duanmau.NguoiDung.NguoiDungAdapter;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.UserDAO;

import java.util.List;

public class QLNguoiDungActivity extends AppCompatActivity {
private ListView listView;
private MySQlite mySQlite;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quan_ly_khach_hang);

    listView = findViewById(R.id.lvList);
    mySQlite = new MySQlite(this);
    final UserDAO userDAO = new UserDAO(mySQlite);

    List<NguoiDung> nguoiDungList = userDAO.getAllUsers();
    NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
    listView.setAdapter(nguoiDungAdapter);
    EditText edtTim = findViewById(R.id.edtTim);

    edtTim.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() == 0) {
                List<NguoiDung> nguoiDungList = userDAO.getAllUsers();
                NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
                listView.setAdapter(nguoiDungAdapter);
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
            View view = LayoutInflater.from(this).inflate(R.layout.add_user_dialog, null);
            builder.setView(view);

            final EditText editTextTextPersonName5 = view.findViewById(R.id.editTextTextPersonName5);
            final EditText editTextTextPassword = view.findViewById(R.id.editTextTextPassword);
            final EditText editTextPhone = view.findViewById(R.id.editTextPhone);
            final EditText editTextTextPersonName6 = view.findViewById(R.id.editTextTextPersonName6);
            Button button3 = view.findViewById(R.id.button3);
            Button button4 = view.findViewById(R.id.button4);
            final AlertDialog alertDialog = builder.show();
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.username = editTextTextPersonName5.getText().toString().trim();
                    nguoiDung.ten = editTextTextPersonName6.getText().toString().trim();
                    nguoiDung.password = editTextTextPassword.getText().toString().trim();
                    nguoiDung.sdt = editTextPhone.getText().toString().trim();

                    checkEmpty(nguoiDung.username, editTextTextPersonName5);
                    checkEmpty(nguoiDung.password, editTextTextPassword);
                    checkEmpty(nguoiDung.sdt, editTextPhone);
                    checkEmpty(nguoiDung.ten, editTextTextPersonName6);

                    UserDAO userDAO = new UserDAO(mySQlite);

                    boolean ketQua = userDAO.addUser(nguoiDung);
                    if (ketQua) {
                        Toast.makeText(QLNguoiDungActivity.this,
                                "THANH CONG!!!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                        List<NguoiDung> nguoiDungList = userDAO.getAllUsers();
                        NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
                        listView.setAdapter(nguoiDungAdapter);
                    } else {
                        Toast.makeText(QLNguoiDungActivity.this,
                                "KHONG THANH CONG!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            button4.setOnClickListener(new View.OnClickListener() {
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

    public void searchUserName(View view) {
        EditText edtTim = findViewById(R.id.edtTim);
        String username = edtTim.getText().toString().trim();
        if (username.isEmpty()) {
            edtTim.setError("NHAP DU LIEU TRC");
            return;
        }
        UserDAO userDAO = new UserDAO(mySQlite);
        List<NguoiDung> nguoiDungList = userDAO.timKiemUsername(username);

        if (nguoiDungList.size() == 0) {
            edtTim.setError("KHONG TIM THAY USER NAO!!!!");
        } else {
            NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
            listView.setAdapter(nguoiDungAdapter);
        }

    }
}
