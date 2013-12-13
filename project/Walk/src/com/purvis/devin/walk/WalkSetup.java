package com.purvis.devin.walk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;

public class WalkSetup {
	
	//Storing list of walks in singleton - only one instance of self can be created
	
	private List<Walk> mWalks;
	
	private static WalkSetup sWalkSetup;
	private Context mAppContext;
	
	private WalkSetup(Context appContext) {
		mAppContext = appContext;
		mWalks = new ArrayList<Walk>();
	    for (int i = 0; i < 100; i++) {
	       Walk c = new Walk();
	       c.setTitle("Crime #" + i);
	       mWalks.add(c);
	    }
	}
	
	public static WalkSetup get(Context c) {
        if (sWalkSetup == null) {
        	sWalkSetup = new WalkSetup(c.getApplicationContext());
        }
        return sWalkSetup;
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
