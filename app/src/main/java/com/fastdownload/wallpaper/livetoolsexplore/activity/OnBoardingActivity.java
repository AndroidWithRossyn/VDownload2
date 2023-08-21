package com.fastdownload.wallpaper.livetoolsexplore.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.splash_anim.OnBoardingFragment;
import com.fastdownload.wallpaper.livetoolsexplore.splash_anim.OnBoardingFragment2;
import com.fastdownload.wallpaper.livetoolsexplore.splash_anim.OnBoardingFragment3;

public class OnBoardingActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdaptor pageradaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = OnBoardingActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        viewPager = findViewById(R.id.pager);
        pageradaptor = new ScreenSlidePagerAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(pageradaptor);



    }
    private class ScreenSlidePagerAdaptor extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdaptor(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    OnBoardingFragment tab1 = new OnBoardingFragment();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}