package com.purvis.devin.walk;

import java.util.Date;
import java.util.UUID;

public class Walk {
	
	private UUID mId;
	private String mTitle;
	private Date mDate;
	
	
	public Walk() {
		//Generate unique identifier
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
	public UUID getId() {
		return mId;
	}
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}
	
	
}
