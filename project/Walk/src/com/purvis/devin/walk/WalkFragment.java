package com.purvis.devin.walk;

import java.util.Date;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class WalkFragment extends Fragment {
	public static final String EXTRA_WALK_ID = "walk.WALK_ID";
	
	private static final String DIALOG_DATE = "date";

	private static final int REQUEST_DATE = 0;
	
	private Walk mWalk;
	private EditText mTitleField;
	private Button mDateButton;
	private Button mPedometerButton;
	private EditText mDetailsField;
	private EditText mStepsTaken;
	private Pedometer mPedometer;
	
	public static WalkFragment newInstance(UUID walkId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_WALK_ID, walkId);

        WalkFragment fragment = new WalkFragment();
        fragment.setArguments(args);

        return fragment;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID walkId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_WALK_ID);
		
		mWalk = WalkSetup.get(getActivity()).getWalk(walkId);
		setHasOptionsMenu(true);
		
		
	}
	
	public void updateDate() {
		mDateButton.setText(mWalk.getDate().toString());
	}
	
	//inflates fragment_walk.xml
		@TargetApi(11)
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_walk, parent, false);
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
	        }   
			
			//wire up EditText to respond to user input
			mTitleField = (EditText)v.findViewById(R.id.walk_title);
			mTitleField.setText(mWalk.getTitle());
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
			
			//wire up EditText to respond to user input
			mDetailsField = (EditText)v.findViewById(R.id.walk_details);
			mDetailsField.setText(mWalk.getDetails());
			mDetailsField.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence c, int start, int before, int count) {
					mWalk.setDetails(c.toString());
				}
				
				public void beforeTextChanged(CharSequence c, int start, int count, int after) {
					//left blank
				}
				
				public void afterTextChanged(Editable c) {
					//left blank
				}
			});
			
			//wire up EditText to respond to user input
			mStepsTaken = (EditText)v.findViewById(R.id.steps_taken);
    		mStepsTaken.setText(mWalk.getSteps());
			mStepsTaken.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence c, int start, int before, int count) {
					mWalk.setSteps(c.toString());
				}
				
				public void beforeTextChanged(CharSequence c, int start, int count, int after) {
					//left blank
				}
				
				public void afterTextChanged(Editable c) {
					//left blank
				}
			});
			
			mDateButton = (Button)v.findViewById(R.id.walk_date);
	        updateDate();
	        mDateButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {                	            	
	                FragmentManager fm = getActivity()
	                        .getSupportFragmentManager();
	                DatePickerFragment dialog = DatePickerFragment.newInstance(mWalk.getDate());
	                dialog.setTargetFragment(WalkFragment.this, REQUEST_DATE);
	                dialog.show(fm, DIALOG_DATE);              	
	            }
	        });
	        
	        mPedometerButton = (Button)v.findViewById(R.id.pedometer_button);
	        mPedometerButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), Pedometer.class);
					startActivity(i);
					
					
				}
			});

			
			return v;
		}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mWalk.setDate(date);
            updateDate();
        }
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } 
    }
	
	@Override
	public void onPause() {
		super.onPause();
		WalkSetup.get(getActivity()).saveWalks();
	}
	
	

}
