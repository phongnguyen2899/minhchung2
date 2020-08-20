package com.example.tournode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DiaDiemAdapter  extends BaseAdapter {
    private LayoutInflater inflater ;
    private ArrayList<DiaDiem> list;
    public DiaDiemAdapter(Context context, ArrayList<DiaDiem> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.layout_dia_diem,null);
        TextView txtTen = view.findViewById(R.id.txtTenDiaDiem);
        TextView txtDiaChi = view.findViewById(R.id.txt_dia_chi);
        ImageView imageView = view.findViewById(R.id.hinhAnh);
        TextView txtMoTa = view.findViewById(R.id.txt_mo_ta);
        Button btn = view.findViewById(R.id.setStt);
        int stt = i;
        if(list.get(i) != null) {
            stt++;
            btn.setText(stt+"/"+list.size());
            txtTen.setText(list.get(i).getTenDiaDiem());
            txtDiaChi.setText(list.get(i).getDiaChi());
            if (list.get(i).getHinhAnh() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(i).getHinhAnh(), 0, list.get(i).getHinhAnh().length);
                imageView.setImageBitmap(bitmap);
            }
            txtMoTa.setText(list.get(i).getMoTa());
        }
        return view;
    }
}
