package com.example.ph11252_nguyenanhtuan_duanmau.Sach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph11252_nguyenanhtuan_duanmau.R;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.MySQlite;
import com.example.ph11252_nguyenanhtuan_duanmau.sql.SachDAO;


import java.util.List;

public class SachAdapter extends BaseAdapter {
    private List<Sach> sachList;
    public SachAdapter(List<Sach> sachList){
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sach,viewGroup,false);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(sachList.get(i).maSach + " - " + sachList.get(i).tenSACH);
        view.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SachDAO sachDAO = new SachDAO(new MySQlite(viewGroup.getContext()));
                String maSach = sachList.get(i).maSach;
                boolean ketQua = sachDAO.xoaSach(maSach);
                if (ketQua) {
                    Toast.makeText(viewGroup.getContext(), "Xoa Thanh Cong!!!",
                            Toast.LENGTH_SHORT).show();

                    sachList.remove(i);
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
