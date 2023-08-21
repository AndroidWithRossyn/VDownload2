package com.fastdownload.wallpaper.livetoolsexplore.livewallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fastdownload.wallpaper.livetoolsexplore.R;

public class LiveSliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_slider);
    }
    public void launchIntent2(View v) {
        Intent intent = new Intent(LiveSliderActivity.this, SettingsActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, FractalClockWallpaperService.class));
//        startActivity(intent);
    }

    public void launchSettingsIntent2(View view) {
        Intent intent = new Intent(LiveSliderActivity.this, ChangeWallpaperActivity.class);
        startActivity(intent);
    }
}