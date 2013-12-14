package com.purvis.devin.walk;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Walk {
	
	private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
//    private static final String JSON_STEPS = "steps";
	
	private UUID mId;
	private String mTitle;
	private Date mDate;
//	private int mSteps;
	
	
	public Walk() {
		//Generate unique identifier
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
	public Walk(JSONObject json) throws JSONException {
		  mId = UUID.fromString(json.getString(JSON_ID));
	      mTitle = json.getString(JSON_TITLE);
//	      mSteps = json.getInt(JSON_STEPS);
	      mDate = new Date(json.getLong(JSON_DATE));
	}
	
	public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_DATE, mDate.getTime());
//      json.put(JSON_STEPS, mSteps);
        return json;
	}

	
	@Override
	public String toString() {
		return mTitle;
	}
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
	public UUID getId() {
		return mId;
	}
	
	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}
	
	
}
