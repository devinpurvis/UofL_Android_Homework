package com.purvis.devin.walk;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class WalkActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID walkID = (UUID)getIntent().getSerializableExtra(WalkFragment.EXTRA_WALK_ID);
		
		return WalkFragment.newInstance(walkID);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.walk, menu);
//		return true;
//	}

}
