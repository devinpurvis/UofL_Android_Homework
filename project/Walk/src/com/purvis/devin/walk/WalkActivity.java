package com.purvis.devin.walk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class WalkActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new WalkFragment();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.walk, menu);
//		return true;
//	}

}
