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
import com.example.musicalstructureapp.Model.Playlist;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav) BottomNavigationView mBottomNav;
    @BindView(R.id.rv_playlists) RecyclerView playlistsRecyclerView;

    private ArrayList<Playlist> mPlaylists;

    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(2);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.navigation_songs:
                    Intent albumsIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(albumsIntent);
                    break;
                case R.id.navigation_albums:
                    Intent playlistIntent = new Intent(getApplicationContext(), AlbumsActivity.class);
                    startActivity(playlistIntent);
                    break;
                case R.id.navigation_playlists:
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
        mCustomAdapter.updatePlaylistsList(mPlaylists);
        mCustomAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        playlistsRecyclerView.setLayoutManager(gridLayoutManager);
        playlistsRecyclerView.setAdapter(mCustomAdapter);
    }

    private void setupData(){
        Artist artist1 = new Artist("Artist1", "Artist1");
        Playlist playlist = new Playlist("Playlist1", artist1, 2019);
        Playlist playlist2 = new Playlist("Playlist2", artist1, 2019);
        Playlist playlist3 = new Playlist("Playlist3", artist1, 2019);
        Playlist playlist4 = new Playlist("Playlist4", artist1, 2019);
        Playlist playlist5 = new Playlist("Playlist5", artist1, 2019);
        Playlist playlist6 = new Playlist("Playlist6", artist1, 2019);
        Playlist playlist7 = new Playlist("Playlist7", artist1, 2019);
        Playlist playlist8 = new Playlist("Playlist8", artist1, 2019);


        mPlaylists = new ArrayList<>();
        mPlaylists.add(playlist);
        mPlaylists.add(playlist2);
        mPlaylists.add(playlist3);
        mPlaylists.add(playlist4);
        mPlaylists.add(playlist5);
        mPlaylists.add(playlist6);
        mPlaylists.add(playlist7);
        mPlaylists.add(playlist8);

    }
}
