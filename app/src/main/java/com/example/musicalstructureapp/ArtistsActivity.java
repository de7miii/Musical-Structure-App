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
import com.example.musicalstructureapp.Model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistsActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.rv_artists)
    RecyclerView artistsRecyclerView;

    private ArrayList<Artist> mArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        ButterKnife.bind(this);
        Menu menu = mBottomNav.getMenu();
        MenuItem item = menu.getItem(3);
        item.setChecked(true);

        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
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
        setupRecyclerView();

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
        customAdapter.updateArtistsList(mArtists);
        customAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        artistsRecyclerView.setLayoutManager(gridLayoutManager);
        artistsRecyclerView.setAdapter(customAdapter);
    }

    private void setupData() {

        mArtists = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Artist artist = new Artist("Artist " + i, "Artist " + i + " Description");
            Album album = new Album("Album " + i, artist, 2010 + i);
            album.addSong(new Song("Song " + i, artist, 200000, 2010 + i));
            album.addSong(new Song("Song " + i + 1, artist, 200000, 2010 + i + 1));
            album.addSong(new Song("Song " + i + 2, artist, 200000, 2010 + i + 2));
            album.addSong(new Song("Song " + i + 3, artist, 200000, 2010 + i + 3));
            album.addSong(new Song("Song " + i + 4, artist, 200000, 2010 + i + 4));
            album.addSong(new Song("Song " + i + 5, artist, 200000, 2010 + i + 5));
            album.addSong(new Song("Song " + i + 6, artist, 200000, 2010 + i + 6));
            artist.addAlbum(album);

            Album album2 = new Album("Album " + i + 1, artist, 2010 + i);
            album2.addSong(new Song("Song " + i + 1, artist, 200000, 2010 + i));
            album2.addSong(new Song("Song " + i + 2, artist, 200000, 2010 + i + 1));
            album2.addSong(new Song("Song " + i + 3, artist, 200000, 2010 + i + 2));
            album2.addSong(new Song("Song " + i + 4, artist, 200000, 2010 + i + 3));
            album2.addSong(new Song("Song " + i + 5, artist, 200000, 2010 + i + 4));
            album2.addSong(new Song("Song " + i + 6, artist, 200000, 2010 + i + 5));
            album2.addSong(new Song("Song " + i + 7, artist, 200000, 2010 + i + 6));
            artist.addAlbum(album2);

            Album album3 = new Album("Album " + i + 2, artist, 2010 + i);
            album3.addSong(new Song("Song " + i + 2, artist, 200000, 2010 + i));
            album3.addSong(new Song("Song " + i + 3, artist, 200000, 2010 + i + 1));
            album3.addSong(new Song("Song " + i + 4, artist, 200000, 2010 + i + 2));
            album3.addSong(new Song("Song " + i + 5, artist, 200000, 2010 + i + 3));
            album3.addSong(new Song("Song " + i + 6, artist, 200000, 2010 + i + 4));
            album3.addSong(new Song("Song " + i + 7, artist, 200000, 2010 + i + 5));
            album3.addSong(new Song("Song " + i + 8, artist, 200000, 2010 + i + 6));
            artist.addAlbum(album3);
            mArtists.add(artist);
        }
    }

    @Override
    public void onItemClick(int position) {
        Artist currentArtist = mArtists.get(position);

        Intent albumIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        albumIntent.putExtra("DATA_TYPE", -3);
        albumIntent.putExtra("DATA", currentArtist);
        startActivity(albumIntent);
    }
}
