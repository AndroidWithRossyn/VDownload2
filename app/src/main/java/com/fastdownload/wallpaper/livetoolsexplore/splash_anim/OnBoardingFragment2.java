package com.fastdownload.wallpaper.livetoolsexplore.splash_anim;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fastdownload.wallpaper.livetoolsexplore.R;
import com.fastdownload.wallpaper.livetoolsexplore.activity.DashboardActivity;

public class OnBoardingFragment2 extends Fragment {
    TextView SkipId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        SkipId=root.findViewById(R.id.SkipId);
        SkipId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
