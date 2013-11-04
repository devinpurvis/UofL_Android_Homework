package com.example.classlab7c.model;

import java.util.Date;

public class Song {
	private int songId;
	private String songTitle;
	private String albumTitle;
	private Date songPublishedDate;
	private Date lastUpdatedDate;
	
	public Song(int id, String title, String albumTitle, Date published){
		this.songId = id;
		this.songTitle = title;
		this.albumTitle = albumTitle;
		this.songPublishedDate = published;
		this.lastUpdatedDate = new Date();
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public Date getSongPublishedDate() {
		return songPublishedDate;
	}

	public void setSongPublishedDate(Date songPublishedDate) {
		this.songPublishedDate = songPublishedDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((albumTitle == null) ? 0 : albumTitle.hashCode());
		result = prime * result
				+ ((lastUpdatedDate == null) ? 0 : lastUpdatedDate.hashCode());
		result = prime * result + songId;
		result = prime
				* result
				+ ((songPublishedDate == null) ? 0 : songPublishedDate
						.hashCode());
		result = prime * result
				+ ((songTitle == null) ? 0 : songTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (albumTitle == null) {
			if (other.albumTitle != null)
				return false;
		} else if (!albumTitle.equals(other.albumTitle))
			return false;
		if (lastUpdatedDate == null) {
			if (other.lastUpdatedDate != null)
				return false;
		} else if (!lastUpdatedDate.equals(other.lastUpdatedDate))
			return false;
		if (songId != other.songId)
			return false;
		if (songPublishedDate == null) {
			if (other.songPublishedDate != null)
				return false;
		} else if (!songPublishedDate.equals(other.songPublishedDate))
			return false;
		if (songTitle == null) {
			if (other.songTitle != null)
				return false;
		} else if (!songTitle.equals(other.songTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Song [songId=" + songId + ", songTitle=" + songTitle
				+ ", albumTitle=" + albumTitle + ", songPublishedDate="
				+ songPublishedDate + ", lastUpdatedDate=" + lastUpdatedDate
				+ "]";
	}
	
	
}
