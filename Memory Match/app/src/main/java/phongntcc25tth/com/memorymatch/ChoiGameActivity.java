package phongntcc25tth.com.memorymatch;

public class ChoiGameActivity {
 package com.phongntcc25th.memorymatch;

    import android.os.Bundle;
    import android.os.CountDownTimer;
    import android.os.Handler;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.GridView;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Button;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Date;
    import java.text.SimpleDateFormat;

    public class ChoiGameActivity extends AppCompatActivity {
        private String capDo;
        private int soHang, soCot, tongThe;
        private GridView gridThe;
        private TextView tvThoiGian, tvLuot;
        private int thoiGian = 0, soLuot = 0;
        private CountDownTimer demGio;
        private Handler handler = new Handler();

        private Integer[] hinhAnh = {
                R.drawable.img1, R.drawable.img2, R.drawable.img3,
                R.drawable.img4, R.drawable.img5, R.drawable.img6
        };

        private ArrayList

                dangSachHinh = new ArrayList();
        private ImageView theDau = null;
        private int viTriDau = -1;
        private boolean dangKhoa = false;
        private int soCapDaTim = 0;
        private int tongCapDoi = 0;
        private DBHelper db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choi_game);

            db = new DBHelper(this);
            capDo = getIntent().getStringExtra("CAP_DO");
            soHang = getIntent().getIntExtra("SO_HANG", 4);
            soCot = getIntent().getIntExtra("SO_COT", 3);
            tongThe = soHang * soCot;
            tongCapDoi = tongThe / 2;

            gridThe = findViewById(R.id.gridThe);
            tvThoiGian = findViewById(R.id.tvThoiGian);
            tvLuot = findViewById(R.id.tvLuot);
            Button btnQuayLai = findViewById(R.id.btnQuayLai);

            gridThe.setNumColumns(soCot);
            taoDanhSachHinh();
            batDauDemGio();

            gridThe.setAdapter(new TheAdapter());

            btnQuayLai.setOnClickListener(v -> {
                demGio.cancel();
                finish();
            });
        }

        private void taoDanhSachHinh() {
            danhSachHinh.clear();
            int soCap = tongThe / 2;
            for (int i = 0; i ; i++) {
                danhSachHinh.add(hinhAnh[i % hinhAnh.length]);
                danhSachHinh.add(hinhAnh[i % hinhAnh.length]);
            }
            Collections.shuffle(danhSachHinh);
        }

        private void batDauDemGio() {
            demGio = new CountDownTimer(99999999, 1000) {
                public void onTick(long millis) {
                    thoiGian++;
                    tvThoiGian.setText(thoiGian + " giây");
                }
                public void onFinish() {}
            }.start();
        }

        private class TheAdapter extends BaseAdapter {
            @Override
            public int getCount() {
                return tongThe;
            }

            @Override
            public Object getItem(int position) {
                return danhSachHinh.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int viTri, View v, ViewGroup parent) {
                ImageView img;
                if (v == null) {
                    img = new ImageView(ChoiGameActivity.this);
                    img.setLayoutParams(new GridView.LayoutParams(100, 100));
                    img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    img.setPadding(4, 4, 4, 4);
                    img.setImageResource(R.drawable.bg_the_the);
                    img.setTag("dong");
                } else {
                    img = (ImageView) v;
                }
                img.setOnClickListener(click -> xuLyChonThe(img, viTri));
                return img;
            }
        }

        private void xuLyChonThe(ImageView img, int viTri) {
            if (dangKhoa || "mo".equals(img.getTag())) {
                return;
            }

            img.setImageResource(danhSachHinh.get(viTri));
            img.setTag("mo");

            if (theDau == null) {
                theDau = img;
                viTriDau = viTri;
                return;
            }

            soLuot++;
            tvLuot.setText(String.valueOf(soLuot));
            dangKhoa = true;

            if (danhSachHinh.get(viTriDau).equals(danhSachHinh.get(viTri))) {
                theDau = null;
                viTriDau = -1;
                dangKhoa = false;
                soCapDaTim++;
                if (soCapDaTim == tongCapDoi) {
                    ketThucGame();
                }
            } else {
                handler.postDelayed(() -> {
                    theDau.setImageResource(R.drawable.bg_the_the);
                    theDau.setTag("dong");
                    img.setImageResource(R.drawable.bg_the_the);
                    img.setTag("dong");
                    theDau = null;
                    viTriDau = -1;
                    dangKhoa = false;
                }, 1000);
            }
        }

        private void ketThucGame() {
            demGio.cancel();
            int diem = (tongCapDoi * 1000) / (thoiGian + soLuot * 2);
            diem = Math.max(diem, 0);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String ngay = df.format(new Date());
            db.luuKetQua(capDo, thoiGian, soLuot, diem, ngay);

            new AlertDialog.Builder(this)
                    .setTitle("🎉 HOÀN THÀNH!")
                    .setMessage("Cấp độ: " + capDo +
                            "\n⏱ Thời gian: " + thoiGian + " giây" +
                            "\n🔄 Số lượt: " + soLuot +
                            "\n⭐ Điểm số: " + diem)
                    .setPositiveButton("Chơi lại", (dialog, which) -> {
                        taoDanhSachHinh();
                        thoiGian = 0;
                        soLuot = 0;
                        soCapDaTim = 0;
                        tvThoiGian.setText("0 giây");
                        tvLuot.setText("0");
                        batDauDemGio();
                        gridThe.setAdapter(new TheAdapter());
                    })
                    .setNegativeButton("Thoát", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();
        }
    }

}
