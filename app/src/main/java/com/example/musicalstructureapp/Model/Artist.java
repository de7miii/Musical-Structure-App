package com.example.musicalstructureapp.Model;

import com.example.musicalstructureapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Artist implements Serializable {
    private UUID mId;
    private String mName;
    private String mDescription;
    private ArrayList<Album> mAlbums;
    private ArrayList<Playlist> mPlaylists;
    private int mThumbnailId;

    public Artist(String name, String description) {
        mId = UUID.randomUUID();
        mName = name;
        mDescription = description;
        mAlbums = new ArrayList<>();
        mPlaylists = new ArrayList<>();
        mThumbnailId = R.drawable.artist_thumbnail;
    }

    public Artist() {
        mId = UUID.randomUUID();
        mAlbums = new ArrayList<>();
        mPlaylists = new ArrayList<>();
        mThumbnailId = R.drawable.artist_thumbnail;
    }

    public String getId() {
        return mId.toString();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public ArrayList<Album> getAlbums() {
        return mAlbums;
    }

    public ArrayList<Playlist> getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        mPlaylists = playlists;
    }

    public int getThumbnailId() {
        return mThumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        mThumbnailId = thumbnailId;
    }

    public void addAlbum(Album album) {
        mAlbums.add(album);

    }

    private Album getAlbum(String albumName) {
        for (Album album :
                mAlbums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        Album newAlbum = new Album();
        newAlbum.setName(albumName);
        return newAlbum;
    }

    public void addSong(Song song, String albumName) {
        getAlbum(albumName).addSong(song);
    }

    public Song getSong(String songName) {
        for (Album album :
                mAlbums) {
            for (Song song :
                    album.getSongs()) {
                if (song.getName().equals(songName)) {
                    return song;
                }
            }
        }
        Song newSong = new Song();
        newSong.setName(songName);
        return newSong;
    }
}
