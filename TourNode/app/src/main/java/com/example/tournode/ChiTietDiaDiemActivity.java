package com.example.tournode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChiTietDiaDiemActivity extends AppCompatActivity {

    private static int REQUEST_CODE_SUA_DIA_DIEM = 999;
    private DiaDiem diaDiem;
    private Toolbar toolbar;
    private TextView txtDiaChi,txtGhiChu;
    private ImageView ivHinhAnh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_dia_diem);
        getDataForMain();
        initView();
        setData();



    }

    public void getDataForMain() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        diaDiem = (DiaDiem) bundle.getSerializable("DIADIEM");
        Toast.makeText(this, diaDiem.getID() + " ", Toast.LENGTH_SHORT).show();
    }

    public void initView() {
        txtDiaChi = findViewById(R.id.txt_dia_chi);
        txtGhiChu = findViewById(R.id.txt_mo_ta);
        ivHinhAnh = findViewById(R.id.iv_hinhAnh);
        toolbar = findViewById(R.id.tb_dia_diem);
        toolbar.setTitle(diaDiem.getTenDiaDiem());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Tạo nút trở lại MainActivity
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void setData()
    {
        toolbar.setTitle(diaDiem.getTenDiaDiem());
        txtDiaChi.setText(diaDiem.getDiaChi());
        txtGhiChu.setText(diaDiem.getMoTa());
        Bitmap bitmap = BitmapFactory.decodeByteArray(diaDiem.getHinhAnh(), 0, diaDiem.getHinhAnh().length);
        ivHinhAnh.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chi_tiet_dia_diem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                 Intent data = new Intent();
                 data.putExtra("DATA", diaDiem);
                // Activity đã hoàn thành OK, trả về dữ liệu.
                setResult(RESULT_OK,data);
                finish();
                return true;
            case R.id.item_xoa:
                MainActivity.dataBase.deleteDiaDiem(diaDiem.getID());
              //  Intent data = new Intent();
               // data.putExtra("DATA", diaDiem);
                // Activity đã hoàn thành OK, trả về dữ liệu.
                this.setResult(RESULT_OK);
                super.finish();
                return true;
            case R.id.item_sua:
                Intent intent  = new Intent(this,SuaDiaDiemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DIADIEM",diaDiem);
                intent.putExtra("DATA",bundle);
                startActivityForResult(intent,REQUEST_CODE_SUA_DIA_DIEM);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SUA_DIA_DIEM && data != null)
        {
            Bundle bundle = data.getBundleExtra("DATA");
            diaDiem = (DiaDiem) bundle.getSerializable("DIADIEM");
            setData();
        }
    }
}
