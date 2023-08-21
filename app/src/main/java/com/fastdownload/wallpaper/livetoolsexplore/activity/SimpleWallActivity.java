package com.fastdownload.wallpaper.livetoolsexplore.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.HomeFragment;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.MainPagerAdapter;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.MoreItemActivity;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.NetworkStateChangeReciver;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.NonSwipeableViewPager;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.RandomGenerateActivity;
import com.fastdownload.wallpaper.livetoolsexplore.newwall.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.pesonal.adsdk.AppManage;

public class SimpleWallActivity extends AppCompatActivity implements HomeFragment.ViewAllCatClicked {

    private static final int REQUEST_PERMISSION_KEY = 1;
    Toolbar toolbar;
    NavigationView navView;
    BottomNavigationView bottomNavigationView;
    NonSwipeableViewPager mainPager;
    RelativeLayout splashLayout;
    SearchView searchView;
    MenuItem menuItem;
    long backPressedTime;
    TextView downloads, gallery, offline_generator;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_wall);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        AppManage.getInstance(SimpleWallActivity.this).loadInterstitialAd(this);
        android.graphics.drawable.Drawable background = SimpleWallActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        toolbar = findViewById(R.id.main_toolbar);
        mainPager = findViewById(R.id.main_pager);
        downloads = findViewById(R.id.downloads);
        gallery = findViewById(R.id.gallery);
        offline_generator = findViewById(R.id.offline_generator);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        downloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(SimpleWallActivity.this).showInterstitialAd(SimpleWallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent1 = new Intent(SimpleWallActivity.this, MoreItemActivity.class);
                        intent1.putExtra("title", "DOWNLOADS");
                        startActivity(intent1);
                        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
                    }
                }, AppManage.ADMOB, AppManage.app_mainClickCntSwAd);

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(SimpleWallActivity.this).showInterstitialAd(SimpleWallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent2 = new Intent(SimpleWallActivity.this, MoreItemActivity.class);
                        intent2.putExtra("title", "GALLERY");
                        startActivity(intent2);
                        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
                    }
                }, AppManage.ADMOB, AppManage.app_mainClickCntSwAd);

            }
        });
        offline_generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(SimpleWallActivity.this).showInterstitialAd(SimpleWallActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent1 = new Intent(SimpleWallActivity.this, RandomGenerateActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
                    }
                }, AppManage.ADMOB, AppManage.app_mainClickCntSwAd);

            }
        });

        setSupportActionBar(toolbar);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReciver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(NetworkStateChangeReciver.IS_NETWORK_AVAILABLE, false);
                if (!isNetworkAvailable) {
                    bottomNavigationView.setVisibility(View.GONE);
                    mainPager.setVisibility(View.GONE);
                    toolbar.setTitle("OFFLINE");
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    mainPager.setVisibility(View.VISIBLE);
                    int id = bottomNavigationView.getSelectedItemId();
                    bottomNavigationView.setSelectedItemId(id);
                }
            }
        }, intentFilter);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 3, 4);
        mainPager.setAdapter(pagerAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            if (getApplicationContext().checkCallingOrSelfPermission(PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSION_KEY);
            }
        }

        if (!internetIsConnected()) {
            bottomNavigationView.setVisibility(View.GONE);
            mainPager.setVisibility(View.GONE);
            toolbar.setTitle("OFFLINE");
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        mainPager.setCurrentItem(0, false);
                        toolbar.setTitle("HOME");
                        break;
                    case R.id.popular:
                        mainPager.setCurrentItem(1, false);
                        toolbar.setTitle("POPULAR");
                        break;
                    case R.id.categories:
                        mainPager.setCurrentItem(2, false);
                        toolbar.setTitle("CATEGORIES");
                        break;
                    case R.id.recent:
                        mainPager.setCurrentItem(3, false);
                        toolbar.setTitle("RECENT");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                hideKeyboard(SimpleWallActivity.this);
                Intent intent = new Intent(SimpleWallActivity.this, SearchActivity.class);
                intent.putExtra("query", s);
                intent.putExtra("extra", "&q=" + s);
                startActivity(intent);
                searchView.setIconified(true);
                menuItem.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean internetIsConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public void onBackPressed() {
        AppManage.getInstance(SimpleWallActivity.this).showInterstitialAd(SimpleWallActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                SimpleWallActivity.super.onBackPressed();
            }
        }, AppManage.ADMOB, AppManage.app_mainClickCntSwAd);
    }


    @Override
    public void onViewAllCatClicked() {
        bottomNavigationView.setSelectedItemId(R.id.categories);
    }
}