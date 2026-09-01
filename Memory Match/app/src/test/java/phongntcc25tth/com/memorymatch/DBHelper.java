package phongntcc25tth.com.memorymatch;

public class DBHelper {
    package com.phongntcc25th.memorymatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DBHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "MemoryMatch.db";
        private static final int DB_VERSION = 1;

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String taoBangLS = "CREATE TABLE LICH_SU (" +
                    "MaLS INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "CapDo TEXT," +
                    "ThoiGian INTEGER," +
                    "SoLuot INTEGER," +
                    "Diem INTEGER," +
                    "NgayChoi TEXT)";
            db.execSQL(taoBangLS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS LICH_SU");
            onCreate(db);
        }

        public long luuKetQua(String capDo, int thoiGian, int soLuot, int diem, String ngay) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues gtri = new ContentValues();
            gtri.put("CapDo", capDo);
            gtri.put("ThoiGian", thoiGian);
            gtri.put("SoLuot", soLuot);
            gtri.put("Diem", diem);
            gtri.put("NgayChoi", ngay);
            long id = db.insert("LICH_SU", null, gtri);
            db.close();
            return id;
        }

        public int layDiemCaoNhat() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT MAX(Diem) FROM LICH_SU", null);
            int diemCao = 0;
            if (c.moveToFirst()) diemCao = c.getInt(0);
            c.close();
            db.close();
            return diemCao;
        }

        public Cursor layTatCaLichSu() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("SELECT * FROM LICH_SU ORDER BY MaLS DESC", null);
        }
    }
}
