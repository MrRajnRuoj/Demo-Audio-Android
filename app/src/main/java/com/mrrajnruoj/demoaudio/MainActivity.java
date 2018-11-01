package com.mrrajnruoj.demoaudio;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnPause, btnStop, btnVUp, btnVDown;
    TextView txtSongName;
    boolean isPause;
    MediaPlayer mySong;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnVUp = (Button)findViewById(R.id.btnVUp);
        btnVDown = (Button)findViewById(R.id.btnVDown);
        txtSongName = (TextView)findViewById(R.id.txtSongName);
        isPause = false;
        audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySong == null) {
                    mySong = MediaPlayer.create(MainActivity.this, R.raw.bo_cong_anh);
                    mySong.start();
                    txtSongName.setText("Playing " + MainActivity.this.getResources().getResourceName(R.raw.bo_cong_anh));
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPause == false) {
                    mySong.pause();
                    btnPause.setText("Resume");
                    isPause = true;
                } else {
                    mySong.start();
                    isPause = false;
                    btnPause.setText("Pause");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySong != null) {
                    mySong.release();
                    mySong = null;
                }
            }
        });

        btnVUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });

        btnVDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });
    }
}
