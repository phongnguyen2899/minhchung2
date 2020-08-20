package com.example.tournode;

import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;



public class FragmentOne extends ListFragment  {

    private static final int MO_CHI_TIET_DIA_DIEM_THAM_QUAN = 200;
    private ArrayList<DiaDiem> listThamQuan = new ArrayList<>();
    private DiaDiemAdapter adapter;
    private int indexSelected;
    private DiaDiem diaDiem;
    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        setListView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitView();
    }

    public void setListView() {
        if(!MainActivity.list.isEmpty()) {
            for (DiaDiem diaDiem : MainActivity.list) {
                if (diaDiem.getChuDe().equals("Tham Quan"))
                {
                    listThamQuan.add(diaDiem);

                }

            }

            adapter = new DiaDiemAdapter(getActivity(), listThamQuan);
            setListAdapter(adapter);
        }
    }

    public void InitView()
    {
        getListView().setClickable(true);

    }

    public void CapNhapListView(DiaDiem diaDiem)// thêm địa điểm mới vào list view sau khi đóng acitvityThemDiaDiem
    {
        listThamQuan.add(diaDiem);
        if(adapter == null)
        {
            adapter = new DiaDiemAdapter(getActivity(),listThamQuan);
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
        startActivityForResult(intent,MO_CHI_TIET_DIA_DIEM_THAM_QUAN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MO_CHI_TIET_DIA_DIEM_THAM_QUAN && data == null){
            listThamQuan.remove(indexSelected);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == MO_CHI_TIET_DIA_DIEM_THAM_QUAN && data != null )
        {
            diaDiem = (DiaDiem) data.getSerializableExtra("DATA");
            listThamQuan.set(indexSelected,diaDiem);
            adapter.notifyDataSetChanged();
        }
    }
}