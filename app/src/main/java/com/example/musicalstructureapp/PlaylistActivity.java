package com.example.musicalstructureapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musicalstructureapp.Adapters.CustomAdapter;
import com.example.musicalstructureapp.Model.Album;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Playlist;
import com.example.musicalstructureapp.Model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.rv_playlists)
    RecyclerView playlistsRecyclerView;

    private ArrayList<Playlist> mPlaylists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(2);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
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
        setupRecyclerView();

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Playlists");

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
        customAdapter.updatePlaylistsList(mPlaylists);
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        playlistsRecyclerView.setLayoutManager(gridLayoutManager);
        playlistsRecyclerView.setAdapter(customAdapter);
    }

    private void setupData() {
        Artist artist1 = new Artist("Artist 1", "Artist1");

        mPlaylists = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Playlist playlist1 = new Playlist("Playliat " + i, artist1, 2010 + i);
            playlist1.addSong(new Song("Song " + i, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 1, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 2, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 3, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 4, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 5, artist1, 200000, 2010 + i));
            playlist1.addSong(new Song("Song " + i + 6, artist1, 200000, 2010 + i));
            mPlaylists.add(playlist1);
        }

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();

        Playlist currentPlaylist = mPlaylists.get(position);


        Intent albumIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        albumIntent.putExtra("DATA_TYPE", -2);
        albumIntent.putExtra("DATA", currentPlaylist);
        startActivity(albumIntent);
    }
}
