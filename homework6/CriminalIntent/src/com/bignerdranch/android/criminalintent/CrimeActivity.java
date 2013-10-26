package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class CrimeActivity extends SingleFragmentActvity {

	
	protected Fragment createFragment() {
		return new CrimeFragment();
		
	}

}

