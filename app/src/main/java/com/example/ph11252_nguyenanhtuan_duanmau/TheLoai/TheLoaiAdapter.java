package com.example.ph11252_nguyenanhtuan_duanmau.TheLoai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph11252_nguyenanhtuan_duanmau.R;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.TheLoaiDAO;


import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private List<TheLoai> theLoaiList;
    public TheLoaiAdapter(List<TheLoai> theLoaiList){
        this.theLoaiList = theLoaiList;
    }

    @Override
    public int getCount() {
        return theLoaiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.the_loai,viewGroup,false);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(theLoaiList.get(i).maTheLoai + " - " + theLoaiList.get(i).tenTheLoai);
        view.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoaiDAO theLoaiDAO = new TheLoaiDAO(new MySQlite(viewGroup.getContext()));
                String maTheLoai = theLoaiList.get(i).maTheLoai;
                boolean ketQua = theLoaiDAO.xoaTheLoai(maTheLoai);
                if (ketQua) {
                    Toast.makeText(viewGroup.getContext(), "Xoa Thanh Cong!!!",
                            Toast.LENGTH_SHORT).show();

                    theLoaiList.remove(i);
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(viewGroup.getContext(), "Xoa KHONG Thanh Cong!!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }
}
