package com.purvis.devin.walk;

import android.support.v4.app.Fragment;

public class WalkListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new WalkListFragment();
	}

}
