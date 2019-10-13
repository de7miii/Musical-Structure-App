package com.example.musicalstructureapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicalstructureapp.Model.Album;
import com.example.musicalstructureapp.Model.Artist;
import com.example.musicalstructureapp.Model.Playlist;
import com.example.musicalstructureapp.Model.Song;
import com.example.musicalstructureapp.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    // TODO: 10/13/2019 setup ItemClickListner to navigate to NowPlaying Activity

    Context mContext;
    private ArrayList<Song> mSongs;
    private ArrayList<Album> mAlbums;
    private ArrayList<Artist> mArtists;
    private ArrayList<Playlist> mPlaylists;

    public CustomAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        if (mSongs != null){
            holder.bind(mSongs.get(position));
        }else if (mAlbums != null){
            holder.bind(mAlbums.get(position));
        }else if (mArtists != null){
            holder.bind(mArtists.get(position));
        }else if (mPlaylists != null){
            holder.bind(mPlaylists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mSongs!= null? mSongs.size(): mAlbums!=null? mAlbums.size(): mArtists!=null? mArtists.size(): mPlaylists!=null? mPlaylists.size() : 0;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        private ImageView thumbnail;
        private TextView songName;
        private TextView artistName;
        private TextView releaseYear;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.img_thumbnail);
            songName = itemView.findViewById(R.id.text_song_name);
            artistName = itemView.findViewById(R.id.text_artist_name);
            releaseYear = itemView.findViewById(R.id.text_release_year);
        }

        public void bind(Song song){
            thumbnail.setImageResource(song.getThumbnailId());
            songName.setText(song.getName());
            artistName.setText(song.getArtist().getName());
            releaseYear.setText(String.format("%d", song.getReleaseYear()));
        }
        public void bind(Album album){
            thumbnail.setImageResource(album.getThumbnailId());
            songName.setText(album.getName());
            artistName.setText(album.getArtist().getName());
            releaseYear.setText(String.format("%d", album.getReleaseYear()));
        }
        public void bind(Playlist playlist){
            thumbnail.setImageResource(playlist.getThumbnailId());
            songName.setText(playlist.getName());
            artistName.setText(playlist.getArtist().getName());
            releaseYear.setText(String.format("%d", playlist.getCreationYear()));
        }
        public void bind(Artist artist){
            thumbnail.setImageResource(artist.getThumbnailId());
            songName.setText(artist.getName());
            artistName.setVisibility(View.GONE);
            releaseYear.setVisibility(View.GONE);
        }
    }

    public  void updateSongsList(ArrayList<Song> songs){
        mSongs = songs;
    }

    public void updateAlbumsList(ArrayList<Album> albums){
        mAlbums = albums;
    }

    public void updateArtistsList(ArrayList<Artist> artists){
        mArtists = artists;
    }

    public void updatePlaylistsList(ArrayList<Playlist> playlists){
        mPlaylists = playlists;
    }
}
