package com.example.musicalstructureapp.Model;

import com.example.musicalstructureapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Playlist implements Serializable {
    private UUID mId;
    private String mName;
    private ArrayList<Song> mSongs;
    private Artist mArtist;
    private int mDuration = 0;
    private int mCreationYear;
    private int mThumbnailId;

    public Playlist(String name, Artist artist, int creationYear) {
        mId = UUID.randomUUID();
        mName = name;
        mSongs = new ArrayList<>();
        mArtist = artist;
        mCreationYear = creationYear;
        mThumbnailId = R.drawable.playlist_thumbnail;
    }

    public Playlist() {
        mId = UUID.randomUUID();
        mSongs = new ArrayList<>();
        mThumbnailId = R.drawable.playlist_thumbnail;
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

    public ArrayList<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(ArrayList<Song> songs) {
        mSongs = songs;
    }

    public Artist getArtist() {
        return mArtist;
    }

    public void setArtist(Artist artist) {
        mArtist = artist;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public int getCreationYear() {
        return mCreationYear;
    }

    public void setCreationYear(int creationYear) {
        mCreationYear = creationYear;
    }

    public int getThumbnailId() {
        return mThumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        mThumbnailId = thumbnailId;
    }

    public void addSong(Song song) {
        if (mSongs != null) {
            mSongs.add(song);
            mDuration += song.getDuration();
        }
    }

}
