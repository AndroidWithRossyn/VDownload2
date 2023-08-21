package com.fastdownload.wallpaper.livetoolsexplore.livewallpaper;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.livewallpaper.adapters.TabAdapter;
import com.fastdownload.wallpaper.livetoolsexplore.livewallpaper.viewmodel.PlaylistViewModel;
import com.fastdownload.wallpaper.livetoolsexplore.livewallpaper.viewmodel.WallpaperViewModel;
import com.google.android.material.tabs.TabLayout;
import com.pesonal.adsdk.AppManage;

public class ChangeWallpaperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_wallpaper);
        AppManage.getInstance(ChangeWallpaperActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);

        // Initializing ViewModels
        WallpaperViewModel wallpaperViewModel =
                new ViewModelProvider(this).get(WallpaperViewModel.class);
        PlaylistViewModel playlistViewModel =
                new ViewModelProvider(this).get(PlaylistViewModel.class);

        // Initializing ViewPager
        ViewPager viewPager = findViewById(R.id.viewPagerId);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        // Adding required fragments
        TabLayout tabLayout = findViewById(R.id.tabLayoutId);
        tabAdapter.addFragment(new SingleFragment(),"Single");
        tabAdapter.addFragment(new SlideshowFragment(),"Slideshow");
        // Setting up final preview
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        CardView backButton = findViewById(R.id.backButtonId);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
