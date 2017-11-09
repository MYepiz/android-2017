package com.example.marty.coollogin;


import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.VideoView;

public class Login extends AppCompatActivity {

    VideoView VV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        VV = (VideoView)findViewById(R.id.vv);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_video);
        VV.setVideoURI(uri);
        VV.start();

        VV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mp.setLooping(true);
            }
        });
    }
}
