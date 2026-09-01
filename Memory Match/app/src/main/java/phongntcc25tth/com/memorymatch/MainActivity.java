package phongntcc25tth.com.memorymatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
package com.phongntcc25th.memorymatch;

import android.content.Intent;
import Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemInsets.left, systemInsets.top, systemInsets.right, systemInsets.bottom);
            return insets;
        });

        Button btnDe = findViewById(R.id.btnDe);
        Button btnTrungBinh = findViewById(R.id.btnTrungBinh);
        Button btnKho = findViewById(R.id.btnKho);
        Button btnLichSu = findViewById(R.id.btnLichSu);

        btnDe.setOnClickListener(v -> moManHinhChoi("Dễ", 4, 3));
        btnTrungBinh.setOnClickListener(v -> moManHinhChoi("Trung bình", 4, 4));
        btnKho.setOnClickListener(v -> moManHinhChoi("Khó", 5, 4));
        btnLichSu.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LichSuActivity.class)));
    }

    private void moManHinhChoi(String capDo, int soHang, int soCot) {
        Intent i = new Intent(MainActivity.this, ChoiGameActivity.class);
        i.putExtra("CAP_DO", capDo);
        i.putExtra("SO_HANG", soHang);
        i.putExtra("SO_COT", soCot);
        startActivity(i);
    }
}


