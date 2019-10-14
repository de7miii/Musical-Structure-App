package com.example.musicalstructureapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musicalstructureapp.Adapters.CustomAdapter;
import com.example.musicalstructureapp.Model.Album;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.rv_albums)
    RecyclerView albumsRecyclerView;

    private ArrayList<Album> mAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(1);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
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
        setupRecyclerView();

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Albums");

        //back button take you to the songs activity directly -starting activity- (the expected behavior when using BottomNavigationView)
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupRecyclerView() {
        CustomAdapter customAdapter = new CustomAdapter(this, this);
        customAdapter.updateAlbumsList(mAlbums);
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        albumsRecyclerView.setLayoutManager(gridLayoutManager);
        albumsRecyclerView.setAdapter(customAdapter);
    }

    private void setupData() {
        Artist artist1 = new Artist("Artist1", "Artist1");

        mAlbums = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Album album = new Album("Album " + i, artist1, 2010 + i);
            album.addSong(new Song("Song " + i, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 1, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 2, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 3, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 4, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 5, artist1, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 6, artist1, 200000, 2010 + i));
            mAlbums.add(album);
        }

    }

    @Override
    public void onItemClick(int position) {
        Album currentAlbum = mAlbums.get(position);

        Intent albumIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        albumIntent.putExtra("DATA_TYPE", -1);
        albumIntent.putExtra("DATA", currentAlbum);
        startActivity(albumIntent);
    }
}
