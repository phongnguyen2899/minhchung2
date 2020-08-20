package com.example.tournode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SuaDiaDiemActivity extends AppCompatActivity {

    private EditText edtTen,edtDiaChi,edtMota;
    private ImageView ivDiaDiem;
    private Toolbar toolbar;
    private DiaDiem diaDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_dia_diem);
        InitView();
        getData();
        setData();
    }

    public void InitView()
    {
        edtTen = findViewById(R.id.edtTenDiaDiem);
        edtDiaChi = findViewById((R.id.edtDiaChi));
        edtMota = findViewById(R.id.edt_Mo_Ta);
        ivDiaDiem = findViewById(R.id.image);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Tạo nút trở lại MainActivity
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void getData()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        diaDiem = (DiaDiem) bundle.getSerializable("DIADIEM");
    }
    public void setData(){
        edtTen.setText(diaDiem.getTenDiaDiem());
        edtDiaChi.setText(diaDiem.getDiaChi());
        edtMota.setText(diaDiem.getMoTa());
        Bitmap bitmap = BitmapFactory.decodeByteArray(diaDiem.getHinhAnh(), 0, diaDiem.getHinhAnh().length);
        ivDiaDiem.setImageBitmap(bitmap);
    }

    public void suaDiaDiem()
    {
        diaDiem.setTenDiaDiem(edtTen.getText()+"");
        diaDiem.setDiaChi(edtDiaChi.getText()+"");
        diaDiem.setMoTa(edtMota.getText()+"");
        diaDiem.setHinhAnh(Support.ConverttoArrayByte(ivDiaDiem));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_luu_dia_diem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case  R.id.luuDiaDiem:
                suaDiaDiem();
                int i = MainActivity.dataBase.updateDiaDiem(diaDiem);
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DIADIEM",diaDiem);
                data.putExtra("DATA",bundle);
                setResult(RESULT_OK,data);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
