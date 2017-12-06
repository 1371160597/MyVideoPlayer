package com.example.jason.myvideoplayer.videoDetail;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.base.BaseActivity;
import com.example.jason.myvideoplayer.mainPage.util.Parameter;
import com.example.jason.myvideoplayer.videoDetail.fragment.VideoDetailFragment;

public class VideoDetailActivity extends BaseActivity {

    private LinearLayout layout;
    private Parameter parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        parameter = (Parameter) getIntent().getSerializableExtra("parameter_data");
        layout = (LinearLayout) findViewById(R.id.video_detail_layout);
        initFragment();
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.video_detail_layout, new VideoDetailFragment(parameter));
        transaction.commit();
    }
}

