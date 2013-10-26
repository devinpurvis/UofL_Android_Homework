package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActvity {

	@Override
	protected Fragment createFragment() {
		return new CrimeListFragment();
	}

}
