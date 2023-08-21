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
import com.fastdownload.wallpaper.livetoolsexplore.activity.PrivacyPolicyActivity;

public class OnBoardingFragment3 extends Fragment {
    TextView SkipId;
    TextView button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3, container, false);
        SkipId=root.findViewById(R.id.SkipId);
        SkipId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        button = root.findViewById(R.id.get_started_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrivacyPolicyActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}
