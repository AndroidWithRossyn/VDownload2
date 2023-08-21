package com.fastdownload.wallpaper.livetoolsexplore.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.Hit;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.HomeFragment;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.RecyclerAdapter;
import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.List;

public class WallActivity extends AppCompatActivity {
    TextView homepage, wallpaper, saved,menu_opn,txt1,txt2;
    ImageView menu;
    LinearLayout ll1;
    CardView crd1, crd2;
    HomeFragment.ViewAllCatClicked activity;
    private RecyclerView categoriesRecycler;
    private List<Hit> categoryList;
    private RecyclerAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = WallActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        AppManage.getInstance(WallActivity.this).loadInterstitialAd(this);
        homepage = findViewById(R.id.homepage);
        wallpaper = findViewById(R.id.wallpaper);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        saved = findViewById(R.id.saved);
        ll1 = findViewById(R.id.ll1);
        menu = findViewById(R.id.menu);
        menu_opn = findViewById(R.id.menu_opn);
        ll1.setVisibility(View.VISIBLE);
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

        categoryList = new ArrayList<>();
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/17/18/46/domestic-cat-4484279__340.jpg","ANIMALS"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/18/17/55/architecture-4487358__340.jpg","ARCHITECTURE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/08/26/12/32/abstract-4431599__340.jpg","TEXTURES"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/13/17/48/hat-4474522__340.jpg","FASHION"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2018/02/16/10/52/beverage-3157395__340.jpg","BUSINESS"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/07/05/06/51/library-4317851__340.jpg","EDUCATION"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/08/19/13/autumn-4461685__340.jpg","EMOTIONS"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/07/26/coffee-4488464__340.jpg","FOOD+DRINK"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2016/11/10/15/24/runner-1814460__340.jpg","HEALTH"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/09/21/26/tractor-4464681__340.jpg","INDUSTRY/CRAFT"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/17/21/32/piano-4484621__340.jpg","MUSIC"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/16/35/landscape-4489716__340.jpg","NATURE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/15/18/35/novice-4479081__340.jpg","PEOPLE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/12/52/eiffel-tower-4489225__340.jpg","PLACES"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/08/27/05/04/cross-4433376__340.jpg","RELIGION"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/15/13/06/aerospace-4478233__340.jpg","SCIENCE"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/19/07/26/extreme-4488462__340.jpg","SPORTS"));
//        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/09/08/23/internet-4463031__340.jpg","TECHNOLOGY"));
        categoryList.add(new Hit("https://cdn.pixabay.com/photo/2019/09/20/22/30/bmw-4492705__340.jpg","TRANSPORTATION"));
        categoryAdapter = new RecyclerAdapter(WallActivity.this, R.layout.categories_layout,R.layout.fragment_home, categoryList);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(WallActivity.this,LinearLayoutManager.HORIZONTAL,false));
        categoriesRecycler.setAdapter(categoryAdapter);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homepage.startAnimation(animation);
                Intent intent = new Intent(WallActivity.this, StatusSaverOfAllAppActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaper.startAnimation(animation);
                Intent intent = new Intent(WallActivity.this, WallActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved.startAnimation(animation);
                Intent intent = new Intent(WallActivity.this, DownloadActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        crd1 = findViewById(R.id.crd1);
        crd2 = findViewById(R.id.crd2);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        crd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(WallActivity.this).showInterstitialAd(WallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(WallActivity.this, SimpleWallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(WallActivity.this).showInterstitialAd(WallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(WallActivity.this, SimpleWallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(WallActivity.this).showInterstitialAd(WallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(WallActivity.this, LiveWallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(WallActivity.this).showInterstitialAd(WallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(WallActivity.this, LiveWallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WallActivity.this, ThankuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);

    }
}