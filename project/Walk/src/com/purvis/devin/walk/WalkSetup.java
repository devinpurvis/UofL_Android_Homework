package com.purvis.devin.walk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class WalkSetup {
	private static final String TAG = "WalkSetup";
    private static final String FILENAME = "walks.json";

	//Storing list of walks in singleton - only one instance of self can be created
	
	private List<Walk> mWalks;
    private WalkJSON mSerializer;
	
	private static WalkSetup sWalkSetup;
	private Context mAppContext;
	
	private WalkSetup(Context appContext) {
		mAppContext = appContext;
		mSerializer = new WalkJSON(mAppContext, FILENAME);
		
		try {
			mWalks = mSerializer.loadWalks();
        } catch (Exception e) {
        	mWalks = new ArrayList<Walk>();
            Log.e(TAG, "Error loading walks: ", e);
        }
	}
	
	public static WalkSetup get(Context c) {
        if (sWalkSetup == null) {
        	sWalkSetup = new WalkSetup(c.getApplicationContext());
        }
        return sWalkSetup;
	}
	
	public boolean saveWalks() {
        try {
            mSerializer.saveWalks(mWalks);
            Log.d(TAG, "walks saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving walks: " + e);
            return false;
        }
    }
	
	public void addWalk(Walk w) {
		mWalks.add(w);
	}
	
	public void deleteWalk(Walk w) {
		mWalks.remove(w);
	}
	
	public List<Walk> getWalks() {
		return mWalks;
	}
	
	public Walk getWalk(UUID id) {
		for (Walk w: mWalks) {
			if(w.getId().equals(id))
				return w;
		}
		return null;
	}

}
