package com.example.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {
	private Button mPlayButton;
	private Button mStopButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup Parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_hello_moon, Parent, false);
		
		mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
		mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
		
		return v;
	}

}