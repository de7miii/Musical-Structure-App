package com.example.musicalstructureapp.Model;

import com.example.musicalstructureapp.R;

import java.util.UUID;

public class Song {
    private UUID mId;
    private String mName;
    private Artist mArtist;
    private int mDuration;
    private int mCoverArtId;
    private int mThumbnailId;
    private int mReleaseYear;

    public Song(String name,Artist artist, int duration, int releaseYear) {
        mId = UUID.randomUUID();
        mName = name;
        mArtist = artist;
        mDuration = duration;
        mCoverArtId = R.drawable.song_cover;
        mThumbnailId = R.drawable.song_thumbnail;
        mReleaseYear = releaseYear;
    }

    public Song() {
        mId = UUID.randomUUID();
        mCoverArtId = R.drawable.song_cover;
        mThumbnailId = R.drawable.song_thumbnail;
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

    public int getCoverArtId() {
        return mCoverArtId;
    }

    public void setCoverArtId(int coverArtId) {
        mCoverArtId = coverArtId;
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
}
