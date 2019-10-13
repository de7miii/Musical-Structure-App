package com.example.musicalstructureapp.Model;

import com.example.musicalstructureapp.R;

import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private UUID mId;
    private String mName;
    private ArrayList<Song> mSongs;
    private Artist mArtist;
    private int mTotalDuration;
    private int mReleaseYear;
    private int mThumbnailId;

    public Album(String name, Artist artist, int releaseYear) {
        mId = UUID.randomUUID();
        mName = name;
        mSongs = new ArrayList<>();
        mArtist = artist;
        mReleaseYear = releaseYear;
        mThumbnailId = R.drawable.album_thumbnail;
    }

    public Album() {
        mId = UUID.randomUUID();
        mSongs = new ArrayList<>();
        mThumbnailId = R.drawable.album_thumbnail;
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

    public int getTotalDuration() {
        return mTotalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        mTotalDuration = totalDuration;
    }

    public int getReleaseYear() {
        return mReleaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.mReleaseYear = releaseYear;
    }

    public int getThumbnailId() {
        return mThumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        mThumbnailId = thumbnailId;
    }

    public void addSong(Song song){
        if (mSongs != null) {
            mSongs.add(song);
            mTotalDuration += song.getDuration();
        }
    }
}
