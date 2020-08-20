package com.example.tournode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public  static final  String DATABASE_NAME = "DiaDiem_DB";
    private static final String TABLE_NAME = "DiaDiem";
    private static final String ID = "ID";
    private static final String CHUDE = "chuDe";
    private static final String TEN = "ten";
    private static final String MOTA = " moTa";
    private static final String DIACHI = "diachi";
    private static final String HINHANH = "hinhAnh";
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                                                ID +" INTEGER  primary key AUTOINCREMENT, " +
                                                CHUDE + " nvarchar(150), " +
                                                TEN + " nvarchar(150), " +
                                                MOTA + " navarchar(150), " +
                                                DIACHI + " navarchar(150)," +
                                                HINHANH + " BOLB )";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null , 1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.e("er","create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void AddDiaDiem(DiaDiem diaDiem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHUDE,diaDiem.getChuDe());
        values.put(TEN,diaDiem.getTenDiaDiem());
        values.put(MOTA,diaDiem.getMoTa());
        values.put(DIACHI,diaDiem.getDiaChi());
        values.put(HINHANH,diaDiem.getHinhAnh());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public int updateDiaDiem( DiaDiem diaDiem )
    {
        String[] whereArgs =  new String[] { String.valueOf(diaDiem.getID())};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHUDE,diaDiem.getChuDe());
        values.put(TEN,diaDiem.getTenDiaDiem());
        values.put(MOTA,diaDiem.getMoTa());
        values.put(DIACHI,diaDiem.getDiaChi());
        values.put(HINHANH,diaDiem.getHinhAnh());
        int  i = db.update(TABLE_NAME, values, ID + " = ?",whereArgs);
        db.close();
        return i;
    }

    public void deleteDiaDiem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}
