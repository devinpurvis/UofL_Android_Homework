package com.example.classlab7c;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.classlab7c.fragments.Layout1;
import com.example.classlab7c.fragments.Layout2;
import com.example.classlab7c.fragments.Layout3;
import com.example.classlab7c.fragments.Layout4;
import com.example.classlab7c.listeners.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener{

    private static final String TAG = "Main_Activity_Fragment";


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
	public void onRssItemSelected(String link) {
		FragmentManager fragmentManager = getFragmentManager();  
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        Layout1 layout1;
        Layout2 layout2;
        Layout3 layout3;
        Layout4 layout4;
		
        //I do this because a string literal can never be null, the string
        //passed in can be. It's a very simple way of protecting your code
        //from npe -wtr
        if("layout1".equalsIgnoreCase(link)){
        	layout1 = new Layout1();
        	fragmentTransaction.replace(R.id.detailFragment, layout1);  
            fragmentTransaction.commit(); 
        }else if("layout2".equalsIgnoreCase(link)){
        	layout2 = new Layout2();
        	fragmentTransaction.replace(R.id.detailFragment, layout2);
        	fragmentTransaction.commit(); 
        }else if("layout3".equalsIgnoreCase(link)){
        	layout3 = new Layout3();
        	fragmentTransaction.replace(R.id.detailFragment, layout3);
        	fragmentTransaction.commit();
        }else if("layout4".equalsIgnoreCase(link)){
        	layout4 = new Layout4();
        	fragmentTransaction.replace(R.id.detailFragment, layout4);
        	fragmentTransaction.commit();
        }else{
        	Log.e(TAG, "Fragment definition was not found. Please check that fragment exists: " + link);
        }
	}
}
