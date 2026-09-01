package phongntcc25tth.com.memorymatch;

public class LichSuActivity {
package com.phongntcc25th.memorymatch;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

    public class LichSuActivity extends AppCompatActivity {
        DBHelper db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lich_su);

            db = new DBHelper(this);
            ListView lv = findViewById(R.id.lvLichSu);
            TextView tvDiemCao = findViewById(R.id.tvDiemCao);
            Button btnQuayLai = findViewById(R.id.btnQuayLaiChinh);

            int diemCao = db.layDiemCaoNhat();
            tvDiemCao.setText(diemCao + " điểm");

            Cursor c = db.layTatCaLichSu();
            String[] tuCot = {"CapDo", "ThoiGian", "SoLuot", "Diem", "NgayChoi"};
            int[] denView = {R.id.tvCapDo, R.id.tvThoiGian, R.id.tvLuot, R.id.tvDiem, R.id.tvNgay};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this, R.layout.dong_lich_su, c, tuCot, denView, 0);
            lv.setAdapter(adapter);

            btnQuayLai.setOnClickListener(v -> finish());
        }
    }

}
