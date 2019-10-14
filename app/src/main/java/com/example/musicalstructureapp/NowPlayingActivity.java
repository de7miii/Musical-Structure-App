package com.example.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.musicalstructureapp.Model.Song;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingActivity extends AppCompatActivity {

    @BindView(R.id.img_cover)
    ImageView coverImage;
    @BindView(R.id.text_song_name)
    TextView songNameText;
    @BindView(R.id.text_artist_name)
    TextView artistNameText;
    @BindView(R.id.btn_play_pause)
    ImageButton playPauseBtn;
    @BindView(R.id.btn_fast_forward)
    ImageButton fastForwardBtn;
    @BindView(R.id.btn_fast_rewind)
    ImageButton fastRewinddBtn;
    @BindView(R.id.song_progress)
    ProgressBar songProgress;

    private boolean isPlaying = false;

    private int songDuration;
    private int remSongDuration;
    private CountDownTimer songProgressCountDowntimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ButterKnife.bind(this);

        // change the status bar to transparent color to
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // hide the action bar from the layout
        assert getSupportActionBar() != null;
        getSupportActionBar().hide();


        Song song = (Song) getIntent().getSerializableExtra("SONG");

        assert song != null;
        String songName = song.getName();
        String artistName = song.getArtist().getName();
        int songCoverId = song.getCoverArtId();
        songDuration = song.getDuration();

        remSongDuration = songDuration;

        coverImage.setImageResource(songCoverId);
        songNameText.setText(songName);
        artistNameText.setText(artistName);

        playPauseBtn.setOnClickListener((v -> {
            if (!isPlaying) {
                play();
            } else {
                pause();
            }
        }));

        fastForwardBtn.setOnClickListener((v) -> fastForward());
        fastRewinddBtn.setOnClickListener((v) -> fastRewind());
    }

    void play() {
        playPauseBtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_pause, null));
        isPlaying = true;
        songProgressCountDowntimer = new CountDownTimer(remSongDuration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                remSongDuration = (int) millisUntilFinished;
                int progress = (int) ((songDuration - millisUntilFinished) * 100) / songDuration;
                songProgress.incrementProgressBy(progress);
                songProgress.setProgress(progress);
            }

            @Override
            public void onFinish() {
                remSongDuration = songDuration;
                songProgress.setProgress(0);
                pause();
            }
        }.start();
    }

    void pause() {
        playPauseBtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_play, null));
        isPlaying = false;
        songProgressCountDowntimer.cancel();
    }

    void fastForward() {
        pause();
        remSongDuration -= 5000;
        int progress = ((songDuration - remSongDuration) * 100) / songDuration;
        songProgress.setProgress(progress);
        play();
    }

    void fastRewind() {
        pause();
        remSongDuration += 5000;
        int progress = ((songDuration - remSongDuration) * 100) / songDuration;
        songProgress.setProgress(progress);
        play();
    }
}
