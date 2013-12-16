package com.purvis.devin.walk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Pedometer extends Activity {
    
	private SensorManager mSensorManager;
	private StepDetector mStepDetector = null;
    private StepNotifier mStepNotifier = null;
    public static int mStepsPush;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_pedometer);
        
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mStepNotifier = new StepNotifier(this);
        mStepDetector = new StepDetector(mStepNotifier);
          
    }
   
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mStepDetector, 
                SensorManager.SENSOR_ACCELEROMETER | 
                SensorManager.SENSOR_MAGNETIC_FIELD | 
                SensorManager.SENSOR_ORIENTATION,
                SensorManager.SENSOR_DELAY_FASTEST);
    }
    
    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(mStepDetector);
        super.onStop();
    }    
    
    public static class StepNotifier extends TextView implements StepListener {
    	
    	private Activity mActivity;
    	public int mCounter = 0;
    	
    	@TargetApi(11)
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_walk, parent, false);
			
			return v;	
    	}
    	
		public TextView mStepCount;    	
    	
		public TextView getStepCount() {
			return mStepCount;
		}

		public void setStepCount(TextView stepCount) {
			mStepCount = stepCount;
		}
    	
    	public StepNotifier(Context context) {
    		super(context);
    		mActivity = (Activity)context;
            mStepCount = (TextView) mActivity.findViewById(R.id.step_count);
            
    	}
    	
    	private void display() {
    		mStepCount.setText("" + mCounter);	
    	}
    	
    	public void onStep() {
    		// Add step
    		mCounter ++;
    		display();
    	}
    }


	private interface StepListener {
    	public void onStep();

		View onCreateView(LayoutInflater inflater, ViewGroup parent,
				Bundle savedInstanceState);
    }
    
    public class StepDetector implements SensorListener
    {
        private float mLastValues[] = new float[6];
        private float mScale[] = new float[2];
        private float mYOffset;

        private float mLastDirections[] = new float[6];
        private float mLastExtremes[][] = { new float[6], new float[6] };
        private float mLastDiff[] = new float[6];
        private int mLastMatch = -1;
        
        private StepListener mStepListener = null;
    	
    	public StepDetector(StepListener stepListener) {
    		mStepListener = stepListener;
    		
    		int h = 480; 
            mYOffset = h * 0.5f;
            mScale[0] = - (h * 0.5f * (1.0f / (SensorManager.STANDARD_GRAVITY * 2)));
            mScale[1] = - (h * 0.5f * (1.0f / (SensorManager.MAGNETIC_FIELD_EARTH_MAX)));
    	}
    	
		@TargetApi(11)
		public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_pedometer, parent, false);
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            getActionBar().setDisplayHomeAsUpEnabled(true);
	        }   
			return v;
			
		}
    	
        @Override
    	public void onSensorChanged(int sensor, float[] values) {
            synchronized (this) {
                if (sensor == SensorManager.SENSOR_ORIENTATION) {
                }
                else {
                    int j = (sensor == SensorManager.SENSOR_MAGNETIC_FIELD) ? 1 : 0;
                    if (j == 0) { 
                        float vSum = 0;
                        for (int i=0 ; i<3 ; i++) {
                            final float v = mYOffset + values[i] * mScale[j];
                            vSum += v;
                        }
                        int k = 0;
                        float v = vSum / 3;
                        
                        float direction = (v > mLastValues[k] ? 1 : (v < mLastValues[k] ? -1 : 0));
                        if (direction == - mLastDirections[k]) {
                        	// Direction changed
                        	int extType = (direction > 0 ? 0 : 1); // minumum or maximum?
                        	mLastExtremes[extType][k] = mLastValues[k];
                        	float diff = Math.abs(mLastExtremes[extType][k] - mLastExtremes[1 - extType][k]);
                        	int limit = 30;
                        	if (diff > limit) {
                            	
                            	boolean isAlmostAsLargeAsPrevious = diff > (mLastDiff[k]*2/3);
                            	boolean isPreviousLargeEnough = mLastDiff[k] > (diff/3);
                            	boolean isNotContra = (mLastMatch != 1 - extType);
                            	
                            	if (isAlmostAsLargeAsPrevious && isPreviousLargeEnough && isNotContra) {
                            		mStepListener.onStep();
                            		mLastMatch = extType;
                            	}
                            	else {
                            		mLastMatch = -1;
                            	}
                        	}
                        	mLastDiff[k] = diff;
                        }
                        mLastDirections[k] = direction;
                        mLastValues[k] = v;
                    }
                }
            }
        }
        
        @Override
        public void onAccuracyChanged(int sensor, int accuracy) {
        	// Not used
        }
    }

}
