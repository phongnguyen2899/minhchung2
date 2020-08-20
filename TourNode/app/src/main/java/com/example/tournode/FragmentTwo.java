package com.example.tournode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import java.util.ArrayList;

public class FragmentTwo extends ListFragment {
    private static final int MO_CHI_TIET_DIA_DIEM_AN_UONG = 300;
    private ArrayList<DiaDiem> listAnUong = new ArrayList<>();
    private DiaDiemAdapter adapter;
    private int indexSelected;
    private DiaDiem diaDiem;


    FragmentTwo(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        setListView();
        return view;
    }

    public void setListView() {
        if (!MainActivity.list.isEmpty()) {
            for (DiaDiem diaDiem : MainActivity.list) {
                if (diaDiem.getChuDe().equals("Ăn Uống"))
                {
                    listAnUong.add(diaDiem);

                }

            }
            adapter = new DiaDiemAdapter(getActivity(), listAnUong);
            setListAdapter(adapter);
        }
    }
    public void CapNhapListView(DiaDiem diaDiem)// thêm địa điểm mới vào list view sau khi đóng acitvityThemDiaDiem
    {
        listAnUong.add(diaDiem);
        if(adapter == null)
        {
            adapter = new DiaDiemAdapter(getActivity(),listAnUong);
            setListAdapter(adapter);
        }
        else adapter.notifyDataSetChanged();;
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
        startActivityForResult(intent,MO_CHI_TIET_DIA_DIEM_AN_UONG);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MO_CHI_TIET_DIA_DIEM_AN_UONG && data == null){
            listAnUong.remove(indexSelected);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == MO_CHI_TIET_DIA_DIEM_AN_UONG && data != null )
        {
            diaDiem = (DiaDiem) data.getSerializableExtra("DATA");
            listAnUong.set(indexSelected,diaDiem);
            adapter.notifyDataSetChanged();
        }
    }

}
