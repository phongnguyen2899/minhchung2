package com.example.tournode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class FragmentThree extends ListFragment {
    private ArrayList<DiaDiem> listMuaSam = new ArrayList<>();
    private DiaDiemAdapter adapter;
    private static final int MO_CHI_TIET_DIA_DIEM_MUA_SAM = 400;
    private int indexSelected;
    private DiaDiem diaDiem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        setListView();
        return view;
    }

    public void setListView() {
        if(!MainActivity.list.isEmpty()) {
            for (DiaDiem diaDiem : MainActivity.list) {
                if (diaDiem.getChuDe().equals("Mua Sắm"))
                {
                    listMuaSam.add(diaDiem);

                }

            }

            adapter = new DiaDiemAdapter(getActivity(), listMuaSam);
            setListAdapter(adapter);
        }
    }


    public void CapNhapListView(DiaDiem diaDiem)// thêm địa điểm mới vào list view sau khi đóng acitvityThemDiaDiem
    {
        listMuaSam.add(diaDiem);
        if(adapter == null)
        {
            adapter = new DiaDiemAdapter(getActivity(),listMuaSam);
            setListAdapter(adapter);
        }
        else adapter.notifyDataSetChanged();
    }
    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        diaDiem = (DiaDiem) adapter.getItem(position);
        indexSelected = position;
        Bundle bundle = new Bundle();
        bundle.putSerializable("DIADIEM",diaDiem);
        Intent intent = new Intent(getContext(),ChiTietDiaDiemActivity.class);
        intent.putExtra("DATA",bundle);
        startActivityForResult(intent,MO_CHI_TIET_DIA_DIEM_MUA_SAM);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MO_CHI_TIET_DIA_DIEM_MUA_SAM && data == null){
            listMuaSam.remove(indexSelected);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == MO_CHI_TIET_DIA_DIEM_MUA_SAM && data != null )
        {
            diaDiem = (DiaDiem) data.getSerializableExtra("DATA");
            listMuaSam.set(indexSelected,diaDiem);
            adapter.notifyDataSetChanged();
        }
    }
}
