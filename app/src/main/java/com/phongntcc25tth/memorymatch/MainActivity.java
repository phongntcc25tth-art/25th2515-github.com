package com.phongntcc25tth.memorymatch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

package com.phongntcc25th.memorymatch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the activity, sets up the user interface, handles window insets for edge-to-edge
     * display, and configures event listeners for difficulty selection and history navigation.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in {@link #onSaveInstanceState}. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Xử lý khoảng cách thanh trạng thái
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemInsets.left, systemInsets.top, systemInsets.right, systemInsets.bottom);
            return insets;
        });

        // Khai báo các nút bấm
        Button btnDe = findViewById(R.id.btnDe);
        Button btnTrungBinh = findViewById(R.id.btnTrungBinh);
        Button btnKho = findViewById(R.id.btnKho);
        Button btnLichSu = findViewById(R.id.btnLichSu);

        // Xử lý sự kiện nhấn nút
        btnDe.setOnClickListener(v -> moManHinhChoi("Dễ", 4, 3));
        btnTrungBinh.setOnClickListener(v -> moManHinhChoi("Trung bình", 4, 4));
        btnKho.setOnClickListener(v -> moManHinhChoi("Khó", 5, 4));
        btnLichSu.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LichSuActivity.class)));
    }

    // Mở màn hình chơi game & truyền dữ liệu cấp độ
    private void moManHinhChoi(String capDo, int soHang, int soCot) {
        Intent i = new Intent(MainActivity.this, ChoiGameActivity.class);
        i.putExtra("CAP_DO", capDo);
        i.putExtra("SO_HANG", soHang);
        i.putExtra("SO_COT", soCot);
        startActivity(i);
    }
}