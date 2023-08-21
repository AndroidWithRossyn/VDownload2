package com.fastdownload.wallpaper.livetoolsexplore.livewallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fastdownload.wallpaper.livetoolsexplore.R;

public class PlanetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
    }
    public void launchIntent(View v) {
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, PlanetPaperWallpaperService.class));
        startActivity(intent);
    }

    public void launchSettingsIntent(View view) {
        Intent intent = new Intent(this, PlanetWallpaperSettingsActivity.class);
        startActivity(intent);
    }
}