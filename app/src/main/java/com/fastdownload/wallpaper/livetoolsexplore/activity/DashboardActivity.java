package com.fastdownload.wallpaper.livetoolsexplore.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fastdownload.wallpaper.livetoolsexplore.R;

public class DashboardActivity extends AppCompatActivity {
    TextView homepage, wallpaper, saved,menu_opn;
    ImageView menu;
    LinearLayout ll1;
    RelativeLayout more_apps_btn, saved_status_btn, share_btn, rate_us_btn, privacy_policy_btn;
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    String CopyKey = "";
    String CopyValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = DashboardActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        homepage = findViewById(R.id.homepage);
        menu = findViewById(R.id.menu);
        menu_opn = findViewById(R.id.menu_opn);
        ll1 = findViewById(R.id.ll1);
        ll1.setVisibility(View.VISIBLE);
        wallpaper = findViewById(R.id.wallpaper);
        saved = findViewById(R.id.saved);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll1.setVisibility(View.GONE);
                menu_opn.setVisibility(View.VISIBLE);

            }
        });
        menu_opn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll1.setVisibility(View.VISIBLE);
                menu_opn.setVisibility(View.GONE);
            }
        });
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homepage.startAnimation(animation);
                Intent intent = new Intent(DashboardActivity.this, StatusSaverOfAllAppActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaper.startAnimation(animation);
                Intent intent = new Intent(DashboardActivity.this, WallActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved.startAnimation(animation);
                Intent intent = new Intent(DashboardActivity.this, DownloadActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DashboardActivity.this, ThankuActivity.class);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
        startActivity(intent);
    }


}