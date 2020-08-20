package com.example.tournode;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    public static final int MO_THEM_DIA_DIEM = 1;
    public static final int THEM_DIA_DIEM_OK = 100;

    public static DataBase dataBase;
    private FragmentOne fragmentOne ;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    public static ArrayList<DiaDiem> list = new ArrayList<>();
    private ViewPager pager;
    private MyAdapter myAdapter;
    private TextView txtThemDiaDiem,txtSuaDiaDiem;
    private Toolbar toolbar;
    private FloatingActionButton fabShow,fabSua,fabThem;
    private TabLayout tabLayout;
    private boolean trangThai = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addEven();
        getDataFromDataBase();
    }


    public void initView()
    {
        dataBase = new DataBase(this);
        pager = findViewById(R.id.view_demo);
        fabShow =(FloatingActionButton) findViewById(R.id.fabShow);
        fabSua = findViewById(R.id.fabSua);
        fabThem = findViewById(R.id.fabThem);
        txtSuaDiaDiem = findViewById(R.id.txtSuaDiaDiem);
        txtThemDiaDiem = findViewById(R.id.txtThemDiaDiem);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(myAdapter); //set adapter cho view page;
        pager.setOffscreenPageLimit(2);
        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setSubtitle("TourNote");
        toolbar.inflateMenu(R.menu.main_actions);
        fragmentOne =(FragmentOne) myAdapter.getItem(0);
        fragmentTwo = (FragmentTwo) myAdapter.getItem(1);
        fragmentThree = (FragmentThree) myAdapter.getItem(2);
    }
    public void addEven(){
        fabShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               trangThai = (!trangThai);
               if(trangThai){
                   hienFab();
               }
               else anFab();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Toast.makeText(MainActivity.this, "Search button selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.about:
                        Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.help:
                        return true;
                }

                return false;
            }
             
        });
        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityThemDiaDiem.class);
                startActivityForResult(intent,MO_THEM_DIA_DIEM);
            }
        });
    }
    public void hienFab(){
        fabSua.setVisibility(View.VISIBLE);
        fabThem.setVisibility(View.VISIBLE);
        txtThemDiaDiem.setVisibility(View.VISIBLE);
        txtSuaDiaDiem.setVisibility(View.VISIBLE);
    }
    public void anFab(){
        fabSua.setVisibility(View.INVISIBLE);
        fabThem.setVisibility(View.INVISIBLE);
        txtThemDiaDiem.setVisibility(View.INVISIBLE);
        txtSuaDiaDiem.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.about).setVisible(true);
        menu.findItem(R.id.search).setVisible(true);
        menu.findItem(R.id.help).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void getDataFromDataBase() {

        try {
            Cursor cursor = MainActivity.dataBase.TruyVanTraVe("Select * from DiaDiem");


                while (cursor.moveToNext()) {
                    {

                        DiaDiem d = new DiaDiem();
                        d.setID(cursor.getInt(0));
                        d.setChuDe(cursor.getString(1));
                        d.setTenDiaDiem(cursor.getString(2));
                        d.setMoTa(cursor.getString(3));
                        d.setDiaChi(cursor.getString(4));
                        d.setHinhAnh((byte[]) cursor.getBlob(5));
                        list.add(d);
                    }
                }
                ;


            } catch(CursorIndexOutOfBoundsException c){

            }
        catch(NullPointerException ex){

            }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("err",requestCode + "");
        if(resultCode == MainActivity.THEM_DIA_DIEM_OK && data != null  )
        {

                Bundle bundle = data.getBundleExtra("DIADIEM");
                DiaDiem diaDiem = (DiaDiem) bundle.getSerializable("DATA");
           //     Toast.makeText(MainActivity.this, diaDiem.getTenDiaDiem() + "", Toast.LENGTH_SHORT).show();
                if (diaDiem.getChuDe().equals("Tham Quan")) {
                    fragmentOne.CapNhapListView(diaDiem);
                } else if (diaDiem.getChuDe().equals("Ăn Uống")) {
                    fragmentTwo.CapNhapListView(diaDiem);
                } else if (diaDiem.getChuDe().equals("Mua Sắm")) {
                    fragmentThree.CapNhapListView(diaDiem);
                }


        }
    }


}