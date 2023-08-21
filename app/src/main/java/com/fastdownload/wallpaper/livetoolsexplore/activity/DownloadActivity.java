package com.fastdownload.wallpaper.livetoolsexplore.activity;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import static com.fastdownload.wallpaper.livetoolsexplore.utils.Utils.createFileFolder;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fastdownload.wallpaper.livetoolsexplore.Fragment.AllinOneGalleryFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.FBDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.InstaDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.LikeeDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.RoposoDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.SharechatDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.SnackVideoDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.TikTokDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.TwitterDownloadedFragment;
import com.fastdownload.wallpaper.livetoolsexplore.Fragment.WhatsAppDowndlededFragment;
import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.databinding.ActivityDownloadBinding;
import com.fastdownload.wallpaper.livetoolsexplore.utils.AppLangSessionManager;
import com.fastdownload.wallpaper.livetoolsexplore.utils.Utils;
import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DownloadActivity extends AppCompatActivity {
    DownloadActivity activity;
    ActivityDownloadBinding binding;
    ImageView menu;
    LinearLayout ll1;
    AppLangSessionManager appLangSessionManager;
    TextView homepage, wallpaper, saved, menu_opn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_download);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = DownloadActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        activity = this;
        homepage = findViewById(R.id.homepage);
        wallpaper = findViewById(R.id.wallpaper);
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
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homepage.startAnimation(animation);
                Intent intent = new Intent(DownloadActivity.this, StatusSaverOfAllAppActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaper.startAnimation(animation);
                Intent intent = new Intent(DownloadActivity.this, WallActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved.startAnimation(animation);
                Intent intent = new Intent(DownloadActivity.this, DownloadActivity.class);
                overridePendingTransition(0, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        appLangSessionManager = new AppLangSessionManager(activity);
        setLocale(appLangSessionManager.getLanguage());

        initViews();
    }

    public void initViews() {
        setupViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);
//        binding.imBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        for (int i = 0; i < binding.tabs.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(activity).inflate(R.layout.custom_tab, null);
            binding.tabs.getTabAt(i).setCustomView(tv);
        }

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        createFileFolder();
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new AllinOneGalleryFragment(Utils.ROOTDIRECTORYJOSHSHOW), "Josh");
        adapter.addFragment(new AllinOneGalleryFragment(Utils.ROOTDIRECTORYCHINGARISHOW), "Chingari");
        adapter.addFragment(new AllinOneGalleryFragment(Utils.ROOTDIRECTORYMITRONSHOW), "Mitron");
        adapter.addFragment(new SnackVideoDownloadedFragment(), "Snack Video");
        adapter.addFragment(new SharechatDownloadedFragment(), "Sharechat");
        adapter.addFragment(new RoposoDownloadedFragment(), "Roposo");
        adapter.addFragment(new InstaDownloadedFragment(), "Instagram");
        adapter.addFragment(new WhatsAppDowndlededFragment(), "Whatsapp");
        adapter.addFragment(new TikTokDownloadedFragment(), "TikTok");
        adapter.addFragment(new FBDownloadedFragment(), "Facebook");
        adapter.addFragment(new TwitterDownloadedFragment(), "Twitter");
        adapter.addFragment(new LikeeDownloadedFragment(), "Likee");
        adapter.addFragment(new AllinOneGalleryFragment(Utils.ROOTDIRECTORYMXSHOW), "MXTakaTak");
        adapter.addFragment(new AllinOneGalleryFragment(Utils.ROOTDIRECTORYMOJSHOW), "Moj");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DownloadActivity.this, ThankuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);

    }
}