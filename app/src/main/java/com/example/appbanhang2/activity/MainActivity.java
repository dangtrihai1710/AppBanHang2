package com.example.appbanhang2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang2.R;
import com.example.appbanhang2.adapter.LoaiSpAdapter;
import com.example.appbanhang2.model.LoaiSp;
import com.example.appbanhang2.network.NetworkUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recylerViewManHinhChinh;
    ListView listViewManHinhChinh;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaisp;

    private NetworkUtils networkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionBar();
        ActionViewFlipper();

        networkUtils = new NetworkUtils(this);
        networkUtils.startNetworkListener(new NetworkUtils.NetworkListener() {
            @Override
            public void onNetworkConnected() {
                // Xử lý khi có kết nối mạng
                Toast.makeText(getApplicationContext(), "Có kết nối Internet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNetworkDisconnected() {
                // Xử lý khi mất kết nối mạng
                Toast.makeText(getApplicationContext(), "Không có kết nối Internet, vui lòng kết nối lại", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ActionViewFlipper() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> mangquangcao = new ArrayList<>();
                mangquangcao.add("https://lsx.vn/wp-content/uploads/2022/08/Vi-pham-ban-quyen-game.jpg");
                mangquangcao.add("https://img.upanh.tv/2023/09/21/Noi-dung-doan-van-ban-ca-bane2dfe78311c6ee17.jpg");
                mangquangcao.add("https://ggpay-dev.s3.ap-southeast-1.amazonaws.com/public/images/2022-6-13/14/c7239d6322cdfead6899b150965ce23e_origin");
                for (int i = 0; i < mangquangcao.size(); i++) {
                    ImageView imageView = new ImageView(getApplicationContext());
                    Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewFlipper.addView(imageView);
                }
                viewFlipper.setFlipInterval(3000);
                viewFlipper.setAutoStart(true);
                // Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                // Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                // viewFlipper.setInAnimation(slide_in);
                // viewFlipper.setOutAnimation(slide_out);
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recylerViewManHinhChinh = findViewById(R.id.recyclerview);
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        // khoi tao list
        mangloaisp = new ArrayList<>();
        // khoi tao adapter
        loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), mangloaisp);
        listViewManHinhChinh.setAdapter(loaiSpAdapter);
    }
}
