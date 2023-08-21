package com.fastdownload.wallpaper.livetoolsexplore.SocialMediaPages;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

import static com.fastdownload.wallpaper.livetoolsexplore.utils.Utils.ROOTDIRECTORYCHINGARI;
import static com.fastdownload.wallpaper.livetoolsexplore.utils.Utils.createFileFolder;
import static com.fastdownload.wallpaper.livetoolsexplore.utils.Utils.startDownload;
import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.activity.StatusSaverOfAllAppActivity;
import com.fastdownload.wallpaper.livetoolsexplore.api.CommonClassForAPI;
import com.fastdownload.wallpaper.livetoolsexplore.databinding.ActivityChingariBinding;
import com.fastdownload.wallpaper.livetoolsexplore.utils.AppLangSessionManager;
import com.fastdownload.wallpaper.livetoolsexplore.utils.SharePrefs;
import com.fastdownload.wallpaper.livetoolsexplore.utils.Utils;
import com.pesonal.adsdk.AppManage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class ChingariActivity extends AppCompatActivity {
    private ActivityChingariBinding binding;
    ChingariActivity activity;
    CommonClassForAPI commonClassForAPI;
    private String VideoUrl;
    private ClipboardManager clipBoard;
    AppLangSessionManager appLangSessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chingari);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = ChingariActivity.this.getResources().getDrawable(R.drawable.gradient_theme);
        getWindow().setBackgroundDrawable(background);
        activity = this;
        AppManage.getInstance(ChingariActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(ChingariActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        commonClassForAPI = CommonClassForAPI.getInstance(activity);
        createFileFolder();
        initViews();


        appLangSessionManager = new AppLangSessionManager(activity);
        setLocale(appLangSessionManager.getLanguage());

        binding.imAppIcon.setImageDrawable(getResources().getDrawable(R.drawable.chingari_last));
        binding.tvAppName.setText(getResources().getString(R.string.chingari_app_name));
        binding.appName.setText(getResources().getString(R.string.chingari_app_name));
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
        assert activity != null;
        clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        PasteText();
    }

    private void initViews() {
        LinearLayout information = findViewById(R.id.information);
        clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);

        binding.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(ChingariActivity.this).showInterstitialAd(ChingariActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(ChingariActivity.this, StatusSaverOfAllAppActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        binding.imInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                information.setVisibility(View.VISIBLE);
                binding.layoutHowTo.LLHowToLayout.setVisibility(View.VISIBLE);


            }
        });

        Glide.with(activity)
                .load(R.drawable.sc1)
                .into(binding.layoutHowTo.imHowto1);

        Glide.with(activity)
                .load(R.drawable.sc2)
                .into(binding.layoutHowTo.imHowto2);

        Glide.with(activity)
                .load(R.drawable.sc1)
                .into(binding.layoutHowTo.imHowto3);

        Glide.with(activity)
                .load(R.drawable.chi2)
                .into(binding.layoutHowTo.imHowto4);

        binding.layoutHowTo.tvHowToHeadOne.setVisibility(View.GONE);
        binding.layoutHowTo.LLHowToOne.setVisibility(View.GONE);
        binding.layoutHowTo.tvHowToHeadTwo.setText(getResources().getString(R.string.how_to_download));

        binding.layoutHowTo.tvHowTo1.setText(getResources().getString(R.string.open_chingari));
        binding.layoutHowTo.tvHowTo3.setText(getResources().getString(R.string.cop_link_from_chingari));
        if (!SharePrefs.getInstance(activity).getBoolean(SharePrefs.ISSHOWHOWTOCHINGARI)) {
            SharePrefs.getInstance(activity).putBoolean(SharePrefs.ISSHOWHOWTOCHINGARI, true);
            binding.layoutHowTo.LLHowToLayout.setVisibility(View.VISIBLE);
        } else {
            binding.layoutHowTo.LLHowToLayout.setVisibility(View.GONE);
        }


        binding.loginBtn1.setOnClickListener(v -> {
            String LL = binding.etText.getText().toString();
            if (LL.equals("")) {
                Utils.setToast(activity, getResources().getString(R.string.enter_url));
            } else if (!Patterns.WEB_URL.matcher(LL.trim()).matches()) {
                Utils.setToast(activity, getResources().getString(R.string.enter_valid_url));
            } else {
                Utils.showProgressDialog(activity);
                getChingariData();
            }
        });

        binding.tvPaste.setOnClickListener(v -> {
            PasteText();
        });

        binding.imAppIcon.setOnClickListener(v -> {
            Utils.OpenApp(activity, "io.chingari.app");
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(ChingariActivity.this).showInterstitialAd(ChingariActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent = new Intent(ChingariActivity.this, StatusSaverOfAllAppActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    private void getChingariData() {
        try {
            createFileFolder();
            URL url = new URL(binding.etText.getText().toString());
            String host = url.getHost();
            if (host.contains("chingari")) {
                Utils.showProgressDialog(activity);
                new CallGetChingariData().execute(binding.etText.getText().toString());
            } else {
                Utils.setToast(activity, getResources().getString(R.string.enter_url));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PasteText() {
        try {
            binding.etText.setText("");
            String CopyIntent = getIntent().getStringExtra("CopyIntent");
            if (CopyIntent.equals("")) {

                if (!(clipBoard.hasPrimaryClip())) {

                } else if (!(clipBoard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
                    if (clipBoard.getPrimaryClip().getItemAt(0).getText().toString().contains("chingari")) {
                        binding.etText.setText(clipBoard.getPrimaryClip().getItemAt(0).getText().toString());
                    }

                } else {
                    ClipData.Item item = clipBoard.getPrimaryClip().getItemAt(0);
                    if (item.getText().toString().contains("chingari")) {
                        binding.etText.setText(item.getText().toString());
                    }

                }
            } else {
                if (CopyIntent.contains("chingari")) {
                    binding.etText.setText(CopyIntent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CallGetChingariData extends AsyncTask<String, Void, Document> {
        Document chingariDoc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Document doInBackground(String... urls) {
            try {
                chingariDoc = Jsoup.connect(urls[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return chingariDoc;
        }

        protected void onPostExecute(Document result) {
            Utils.hideProgressDialog(activity);
            try {
                VideoUrl = result.select("meta[property=\"og:video:secure_url\"]").last().attr("content");
                if (!VideoUrl.equals("")) {
                    startDownload(VideoUrl, ROOTDIRECTORYCHINGARI, activity, "chingari_" + System.currentTimeMillis() + ".mp4");
                    VideoUrl = "";
                    binding.etText.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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


}