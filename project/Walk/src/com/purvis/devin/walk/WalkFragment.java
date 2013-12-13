package com.purvis.devin.walk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class WalkFragment extends Fragment {
	private Walk mWalk;
	private EditText mTitleField;
	private Button mDateButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWalk = new Walk();
	}
	//inflates fragment_walk.xml
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_walk, parent, false);
		
		//wire up EditText to respond to user input
		mTitleField = (EditText)v.findViewById(R.id.walk_title);
		mTitleField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mWalk.setTitle(c.toString());
			}
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				//left blank
			}
			
			public void afterTextChanged(Editable c) {
				//left blank
			}
		});
		
		mDateButton = (Button)v.findViewById(R.id.walk_date);
        mDateButton.setText(mWalk.getDate().toString());
        mDateButton.setEnabled(false);
		
		return v;
	}

}
