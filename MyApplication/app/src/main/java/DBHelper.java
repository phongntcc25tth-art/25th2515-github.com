public class DBHelper {
    package com.example.qldangkychp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DBHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "QLDangKyHP.db";
        private static final int DB_VER = 1;

        public static final String TABLE_TK = "TaiKhoan";
        public static final String COL_MATK = "MaTK";
        public static final String COL_TENTK = "TenTaiKhoan";
        public static final String COL_MK = "MatKhau";
        public static final String COL_HOTEN = "HoTen";
        public static final String COL_MASV = "MaSV";
        public static final String COL_VAITRO = "VaiTro";

        public static final String TABLE_HP = "HocPhan";
        public static final String COL_MAHP = "MaHP";
        public static final String COL_TENHP = "TenHP";
        public static final String COL_TINCHI = "SoTinChi";
        public static final String COL_NGANH = "Nganh";
        public static final String COL_HK = "HocKy";
        public static final String COL_SISO_MAX = "SiSoMax";
        public static final String COL_SISO_HIEN = "SiSoHienTai";

        public static final String TABLE_DK = "PhieuDangKy";
        public static final String COL_MAPHIEU = "MaPhieu";
        public static final String COL_MASV_FK = "MaSV";
        public static final String COL_MAHP_FK = "MaHP";
        public static final String COL_NGAYDK = "NgayDangKy";
        public static final String COL_TRANGTHAI = "TrangThai";

        public DBHelper(Context ctx) {
            super(ctx, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlTK = "CREATE TABLE " + TABLE_TK + " (" +
                    COL_MATK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TENTK + " TEXT UNIQUE, " +
                    COL_MK + " TEXT, " +
                    COL_HOTEN + " TEXT, " +
                    COL_MASV + " TEXT UNIQUE, " +
                    COL_VAITRO + " TEXT)";
            db.execSQL(sqlTK);

            String sqlHP = "CREATE TABLE " + TABLE_HP + " (" +
                    COL_MAHP + " TEXT PRIMARY KEY, " +
                    COL_TENHP + " TEXT, " +
                    COL_TINCHI + " INTEGER, " +
                    COL_NGANH + " TEXT, " +
                    COL_HK + " INTEGER, " +
                    COL_SISO_MAX + " INTEGER DEFAULT 60, " +
                    COL_SISO_HIEN + " INTEGER DEFAULT 0)";
            db.execSQL(sqlHP);

            String sqlDK = "CREATE TABLE " + TABLE_DK + " (" +
                    COL_MAPHIEU + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_MASV_FK + " TEXT, " +
                    COL_MAHP_FK + " TEXT, " +
                    COL_NGAYDK + " TEXT, " +
                    COL_TRANGTHAI + " TEXT DEFAULT 'ChoDuyet')";
            db.execSQL(sqlDK);

            ContentValues qtv = new ContentValues();
            qtv.put(COL_TENTK, "admin");
            qtv.put(COL_MK, "123456");
            qtv.put(COL_HOTEN, "Quản trị viên");
            qtv.put(COL_MASV, "QTV001");
            qtv.put(COL_VAITRO, "QTV");
            db.insert(TABLE_TK, null, qtv);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TK);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DK);
            onCreate(db);
        }

        public long dangKySV(String tenTK, String mk, String hoTen, String maSV) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues v = new ContentValues();
            v.put(COL_TENTK, tenTK);
            v.put(COL_MK, mk);
            v.put(COL_HOTEN, hoTen);
            v.put(COL_MASV, maSV);
            v.put(COL_VAITRO, "SinhVien");
            return db.insert(TABLE_TK, null, v);
        }

        public Cursor dangNhap(String tenTK, String mk) {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT * FROM " + TABLE_TK + " WHERE " +
                    COL_TENTK + "=? AND " + COL_MK + "=?", new String[]{tenTK, mk});
        }

        public long themHP(String maHP, String tenHP, int tc, String nganh, int hk) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues v = new ContentValues();
            v.put(COL_MAHP, maHP);
            v.put(COL_TENHP, tenHP);
            v.put(COL_TINCHI, tc);
            v.put(COL_NGANH, nganh);
            v.put(COL_HK, hk);
            return db.insert(TABLE_HP, null, v);
        }

        public int suaHP(String maHP, String tenHP, int tc, String nganh, int hk) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues v = new ContentValues();
            v.put(COL_TENHP, tenHP);
            v.put(COL_TINCHI, tc);
            v.put(COL_NGANH, nganh);
            v.put(COL_HK, hk);
            return db.update(TABLE_HP, v, COL_MAHP + "=?", new String[]{maHP});
        }

        public int xoaHP(String maHP) {
            SQLiteDatabase db = getWritableDatabase();
            return db.delete(TABLE_HP, COL_MAHP + "=?", new String[]{maHP});
        }

        public Cursor layDSHP() {
            return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_HP, null);
        }

        public Cursor timHP(String tuKhoa) {
            return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_HP +
                            " WHERE " + COL_MAHP + " LIKE ? OR " + COL_TENHP + " LIKE ?",
                    new String[]{"%" + tuKhoa + "%", "%" + tuKhoa + "%"});
        }

        public long dangKyHP(String maSV, String maHP, String ngayDK) {
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DK + " WHERE " +
                    COL_MASV_FK + "=? AND " + COL_MAHP_FK + "=?", new String[]{maSV, maHP});
            if (c.getCount() > 0) { c.close(); return -1; }
            c.close();

            Cursor c2 = db.rawQuery("SELECT " + COL_SISO_HIEN + "," + COL_SISO_MAX +
                    " FROM " + TABLE_HP + " WHERE " + COL_MAHP + "=?", new String[]{maHP});
            if (!c2.moveToFirst()) { c2.close(); return -2; }
            int hien = c2.getInt(0), max = c2.getInt(1);
            c2.close();
            if (hien >= max) return -3;

            ContentValues v = new ContentValues();
            v.put(COL_MASV_FK, maSV);
            v.put(COL_MAHP_FK, maHP);
            v.put(COL_NGAYDK, ngayDK);
            long kq = db.insert(TABLE_DK, null, v);
            if (kq > 0) {
                db.execSQL("UPDATE " + TABLE_HP + " SET " + COL_SISO_HIEN + "=" + COL_SISO_HIEN +
                        "+1 WHERE " + COL_MAHP + "=?", new String[]{maHP});
            }
            return kq;
        }

        public Cursor layDSDangKySV(String maSV) {
            return getReadableDatabase().rawQuery(
                    " SELECT dk.*, hp.TenHP, hp.SoTinChi FROM " + TABLE_DK + " dk " +
                            " JOIN " + TABLE_HP + " hp ON dk.MaHP = hp.MaHP " +
                            " WHERE dk.MaSV = ? ORDER BY dk.NgayDangKy DESC ", new String[]{maSV});
        }

        public Cursor layDSChoDuyet() {
            return getReadableDatabase().rawQuery(
                    " SELECT dk.*, tk.HoTen, hp.TenHP FROM " + TABLE_DK + " dk " +
                            " JOIN " + TABLE_TK + " tk ON dk.MaSV = tk.MaSV " +
                            " JOIN " + TABLE_HP + " hp ON dk.MaHP = hp.MaHP " +
                            " WHERE dk.TrangThai = 'ChoDuyet' ", null);
        }

        public int duyetPhieu(int maPhieu, String trangThaiMoi) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues v = new ContentValues();
            v.put(COL_TRANGTHAI, trangThaiMoi);
            return db.update(TABLE_DK, v, COL_MAPHIEU + "=?", new String[]{String.valueOf(maPhieu)});
        }
    }
}
