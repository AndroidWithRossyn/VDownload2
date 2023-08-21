package com.fastdownload.wallpaper.livetoolsexplore.livewallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fastdownload.wallpaper.livetoolsexplore.R;

public class PlanetWallpaperSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_wallpaper_settings);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new WallpaperPreferenceFragment()).commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        // update the wallpaper activity
        Intent intent = new Intent(SettingsUpdateReceiver.INTENT_FILTER_ACTION);
        sendBroadcast(intent);
    }
}