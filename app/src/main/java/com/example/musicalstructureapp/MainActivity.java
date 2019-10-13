package com.example.musicalstructureapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav) BottomNavigationView mBottomNav;
    @BindView(R.id.rv_songs) RecyclerView songsRecyclerView;

    private ArrayList<Song> mSongs;

    private CustomAdapter mCustomAdapter;

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
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

        mCustomAdapter = new CustomAdapter(this);
        setupData();
        mCustomAdapter.updateSongsList(mSongs);
        mCustomAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        songsRecyclerView.setLayoutManager(gridLayoutManager);
        songsRecyclerView.setAdapter(mCustomAdapter);
    }

    private void setupData(){
        Artist artist1 = new Artist("Artist1", "Artist1");
        Song song = new Song("Song1", artist1, 200000, 2019);
        Song song2 = new Song("Song2", artist1, 200000, 2018);
        Song song3 = new Song("Song3", artist1, 200000, 2017);
        Song song4 = new Song("Song4", artist1, 200000, 2016);
        Song song5 = new Song("Song5", artist1, 200000, 2015);
        Song song6 = new Song("Song6", artist1, 200000, 2014);
        Song song7 = new Song("Song7", artist1, 200000, 2013);

        mSongs = new ArrayList<>();
        mSongs.add(song);
        mSongs.add(song2);
        mSongs.add(song3);
        mSongs.add(song4);
        mSongs.add(song5);
        mSongs.add(song6);
        mSongs.add(song7);

    }
}
