package com.purvis.devin.walk;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.purvis.devin.walk.R;


public class WalkListFragment extends ListFragment {
	
	private List<Walk> mWalks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.walks_title);
        mWalks = WalkSetup.get(getActivity()).getWalks();
    }
       

}
