package com.example.tournode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityThemDiaDiem extends AppCompatActivity {
    private Toolbar toolbar;
    public static final int REQUEST_CODE_CAMERA = 123;
    public static final int REQUEST_CODE_PHOTO = 111;
    private ImageButton btnCamera,btnAddPhoto;
    private ImageView image;
    private RadioButton rdAnUong,rdThamQuan,rDuLich;
    private EditText edtTenDiaDiem,edtMoTa,edtDiaChi;
    private DiaDiem diaDiem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        init();
        addEven();
    }

    private void init()
    {
        btnAddPhoto = findViewById(R.id.icon_add_photo);
        btnCamera = findViewById(R.id.icon_camera);
        toolbar = findViewById(R.id.toolBarAdd);
        image = findViewById(R.id.image);
        rdAnUong = findViewById(R.id.rdAnUong);
        rdThamQuan = findViewById(R.id.rdThamQuan);
        rDuLich = findViewById(R.id.rdDuLich);
        edtTenDiaDiem = findViewById(R.id.edtTenDiaDiem);
        edtMoTa = findViewById(R.id.edt_Mo_Ta);
        edtDiaChi = findViewById(R.id.edtDiaChi);

     //   DiaDiem dd = new DiaDiem(1,"h","hh","hhh", "hhhhh",Support.ConverttoArrayByte(image));
      //  MainActivity.dataBase.AddDiaDiem(dd);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Tạo nút trở lại MainActivity
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    private void addEven(){
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//mở camera
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Intent.ACTION_PICK));
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data"); //lấy ảnh từ camera
            image.setImageBitmap(bitmap);


        }
        else if(requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

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
            case R.id.luuDiaDiem:
                getDataFormView();
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA",diaDiem);
                intent.putExtra("DIADIEM",bundle);
                setResult(MainActivity.THEM_DIA_DIEM_OK,intent);
                finish();
                return  true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getDataFormView()//lấy dữ liệu từ màn hình
    {
        diaDiem = new DiaDiem();
        diaDiem.setTenDiaDiem(edtTenDiaDiem.getText().toString());
        diaDiem.setMoTa(edtMoTa.getText().toString());
        diaDiem.setDiaChi(edtDiaChi.getText().toString());
        diaDiem.setHinhAnh(Support.ConverttoArrayByte(image));
        if(rDuLich.isChecked()){
            diaDiem.setChuDe(rDuLich.getText().toString());
        }
        else if(rdThamQuan.isChecked()){
            diaDiem.setChuDe(rdThamQuan.getText().toString());
        }
        else if(rdAnUong.isChecked()){
            diaDiem.setChuDe(rdAnUong.getText().toString());
        }

        MainActivity.dataBase.AddDiaDiem(diaDiem);
    }

}
