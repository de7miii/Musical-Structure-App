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
import com.example.musicalstructureapp.Model.Album;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav) BottomNavigationView mBottomNav;
    @BindView(R.id.rv_albums) RecyclerView albumsRecyclerView;

    private ArrayList<Album> mAlbums;

    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(1);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.navigation_songs:
                    Intent albumsIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(albumsIntent);
                    break;
                case R.id.navigation_albums:
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

        setupData();
        mCustomAdapter = new CustomAdapter(this);
        mCustomAdapter.updateAlbumsList(mAlbums);
        mCustomAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        albumsRecyclerView.setLayoutManager(gridLayoutManager);
        albumsRecyclerView.setAdapter(mCustomAdapter);
    }

    private void setupData(){
        Artist artist1 = new Artist("Artist1", "Artist1");
        Album album = new Album("Album1", artist1, 2019);
        Album album2 = new Album("Album2", artist1, 2018);
        Album album3 = new Album("Album3", artist1, 2017);
        Album album4 = new Album("Album4", artist1, 2016);
        Album album5 = new Album("Album5", artist1, 2015);
        Album album6 = new Album("Album6", artist1, 2014);
        Album album7 = new Album("Album7", artist1, 2013);
        Album album8 = new Album("Album8", artist1, 2012);


        mAlbums = new ArrayList<>();
        mAlbums.add(album);
        mAlbums.add(album2);
        mAlbums.add(album3);
        mAlbums.add(album4);
        mAlbums.add(album5);
        mAlbums.add(album6);
        mAlbums.add(album7);
        mAlbums.add(album8);

    }
}
