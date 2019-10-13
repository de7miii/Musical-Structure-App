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
import com.example.musicalstructureapp.Model.Playlist;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistsActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav) BottomNavigationView mBottomNav;
    @BindView(R.id.rv_artists) RecyclerView artistsRecyclerView;

    private ArrayList<Artist> mArtists;

    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(3);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.navigation_songs:
                    Intent albumsIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(albumsIntent);
                    break;
                case R.id.navigation_albums:
                    Intent artistsIntent = new Intent(getApplicationContext(), AlbumsActivity.class);
                    startActivity(artistsIntent);
                    break;
                case R.id.navigation_playlists:
                    Intent playlistIntent = new Intent(getApplicationContext(), PlaylistActivity.class);
                    startActivity(playlistIntent);
                    break;
                case R.id.navigation_artists:
                    break;
            }
            return false;
        });

        setupData();
        mCustomAdapter = new CustomAdapter(this);
        mCustomAdapter.updateArtistsList(mArtists);
        mCustomAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        artistsRecyclerView.setLayoutManager(gridLayoutManager);
        artistsRecyclerView.setAdapter(mCustomAdapter);
    }
    private void setupData(){
        Artist artist1 = new Artist("Artist1", "Artist1");
        Artist artist2 = new Artist("Artist2", "Artist2");
        Artist artist3 = new Artist("Artist3", "Artist3");
        Artist artist4 = new Artist("Artist4", "Artist4");
        Artist artist5 = new Artist("Artist5", "Artist5");
        Artist artist6 = new Artist("Artist6", "Artist6");
        Artist artist7 = new Artist("Artist7", "Artist7");
        Artist artist8 = new Artist("Artist8", "Artist8");
        Artist artist9 = new Artist("Artist9", "Artist9");


        mArtists = new ArrayList<>();
        mArtists.add(artist1);
        mArtists.add(artist2);
        mArtists.add(artist3);
        mArtists.add(artist4);
        mArtists.add(artist5);
        mArtists.add(artist6);
        mArtists.add(artist7);
        mArtists.add(artist8);
        mArtists.add(artist9);
    }

}
