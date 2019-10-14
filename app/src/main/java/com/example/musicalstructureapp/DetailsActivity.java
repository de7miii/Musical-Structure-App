package com.example.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicalstructureapp.Adapters.CustomAdapter;
import com.example.musicalstructureapp.Model.Album;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Playlist;
import com.example.musicalstructureapp.Model.Song;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int dataType = getIntent().getIntExtra("DATA_TYPE", 0);

        switch (dataType) {
            case -1:
                handleAlbum();
                break;
            case -2:
                handlePlaylist();
                break;
            case -3:
                handleArtist();
                break;
        }
    }

    public void handleAlbum() {
        setContentView(R.layout.activity_details_album);
        Album album = (Album) getIntent().getSerializableExtra("DATA");
        assert album != null;
        ImageView albumCover = findViewById(R.id.img_album_cover);
        TextView albumName = findViewById(R.id.text_album_name);
        TextView artistName = findViewById(R.id.text_artist_name);
        albumCover.setImageResource(album.getThumbnailId());
        albumName.setText(album.getName());
        artistName.setText(album.getArtist().getName());

        RecyclerView recyclerView = findViewById(R.id.rv_album_songs);

        CustomAdapter.OnItemClickListener onItemClickListener = position -> {
            Song currentSong = album.getSongs().get(position);
            Intent nowPlayingIntent = new Intent(getApplicationContext(), NowPlayingActivity.class);
            nowPlayingIntent.putExtra("SONG", currentSong);
            startActivity(nowPlayingIntent);
        };

        CustomAdapter customAdapter = new CustomAdapter(this, onItemClickListener);

        assert album.getSongs() != null;
        customAdapter.updateSongsList(album.getSongs());
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);
    }

    public void handlePlaylist() {
        setContentView(R.layout.activity_details_playlist);
        Playlist playlist = (Playlist) getIntent().getSerializableExtra("DATA");
        assert playlist != null;

        ImageView albumCover = findViewById(R.id.img_playlist_cover);
        TextView albumName = findViewById(R.id.text_playlist_name);
        TextView artistName = findViewById(R.id.text_artist_name);
        albumCover.setImageResource(playlist.getThumbnailId());
        albumName.setText(playlist.getName());
        artistName.setText(playlist.getArtist().getName());

        RecyclerView recyclerView = findViewById(R.id.rv_playlist_songs);

        CustomAdapter.OnItemClickListener onItemClickListener = position -> {
            Song currentSong = playlist.getSongs().get(position);
            Intent nowPlayingIntent = new Intent(getApplicationContext(), NowPlayingActivity.class);
            nowPlayingIntent.putExtra("SONG", currentSong);
            startActivity(nowPlayingIntent);
        };

        CustomAdapter customAdapter = new CustomAdapter(this, onItemClickListener);

        assert playlist.getSongs() != null;
        customAdapter.updateSongsList(playlist.getSongs());
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);
    }

    public void handleArtist() {
        setContentView(R.layout.activity_details_artist);
        Artist artist = (Artist) getIntent().getSerializableExtra("DATA");
        assert artist != null;
        ImageView albumCover = findViewById(R.id.img_artist_cover);
        TextView albumName = findViewById(R.id.text_artist_name);
        TextView artistName = findViewById(R.id.text_artist_description);
        albumCover.setImageResource(artist.getThumbnailId());
        albumName.setText(artist.getName());
        artistName.setText(artist.getDescription());

        RecyclerView recyclerView = findViewById(R.id.rv_artist_albums);

        CustomAdapter.OnItemClickListener onItemClickListener = position -> {
            Album currentAlbum = artist.getAlbums().get(position);
            Intent albumIntent = new Intent(getApplicationContext(), DetailsActivity.class);
            albumIntent.putExtra("DATA_TYPE", -1);
            albumIntent.putExtra("DATA", currentAlbum);
            startActivity(albumIntent);
        };

        CustomAdapter customAdapter = new CustomAdapter(this, onItemClickListener);

        assert artist.getAlbums() != null;
        customAdapter.updateAlbumsList(artist.getAlbums());
        customAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);
    }
}
