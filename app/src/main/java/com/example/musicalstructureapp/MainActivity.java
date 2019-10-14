package com.example.musicalstructureapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.musicalstructureapp.Adapters.CustomAdapter;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.rv_songs)
    RecyclerView songsRecyclerView;

    private ArrayList<Song> mSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(0);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_songs:
                    break;
                case R.id.navigation_albums:
                    Intent albumsIntent = new Intent(getApplicationContext(), AlbumsActivity.class);
                    startActivity(albumsIntent);
                    break;
                case R.id.navigation_playlists:
                    Intent playlistIntent = new Intent(getApplicationContext(), PlaylistActivity.class);
                    startActivity(playlistIntent);
                    break;
                case R.id.navigation_artists:
                    Intent artistsIntent = new Intent(getApplicationContext(), ArtistsActivity.class);
                    startActivity(artistsIntent);
                    break;
            }
            return false;
        });
        //back button exits the app if user is in the starting activity.
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

        setupData();
        setupRecyclerView();

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Songs");
    }

    private void setupRecyclerView() {
        CustomAdapter customAdapter = new CustomAdapter(this, this);
        customAdapter.updateSongsList(mSongs);
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        songsRecyclerView.setLayoutManager(gridLayoutManager);
        songsRecyclerView.setAdapter(customAdapter);
    }

    private void setupData() {
        Artist artist1 = new Artist("Artist 1", "Artist 1");

        mSongs = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Song song = new Song("Song " + i, artist1, 200000, 2010 + i);
            mSongs.add(song);
        }
    }

    @Override
    public void onItemClick(int position) {
        Song currentSong = mSongs.get(position);
        Intent nowPlayingIntent = new Intent(getApplicationContext(), NowPlayingActivity.class);
        nowPlayingIntent.putExtra("SONG", currentSong);
        startActivity(nowPlayingIntent);
    }
}
