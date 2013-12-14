package com.purvis.devin.walk;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class WalkListFragment extends ListFragment {
	
	private static final String TAG = "WalkListFragment";
	private List<Walk> mWalks;
	private boolean mSubtitleVisible;
	private Button createWalkButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        
        getActivity().setTitle(R.string.walks_title);
        mWalks = WalkSetup.get(getActivity()).getWalks();
        
        WalkAdapter adapter = new WalkAdapter(mWalks);
        setListAdapter(adapter);
        
        setRetainInstance(true);
        mSubtitleVisible = true;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	((WalkAdapter)getListAdapter()).notifyDataSetChanged();
    }
    
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//        View v = super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_walk_list, parent, false);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {   
            if (mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        
        createWalkButton = (Button)v.findViewById(R.id.fragment_walk_create);
            createWalkButton.setOnClickListener(new OnClickListener() {
              
              @Override
              public void onClick(View v) {
                showCreateWalk();
              }
            });
        
        return v;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_walk_list, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_walk:
//                Walk walk = new Walk();
//                WalkSetup.get(getActivity()).addWalk(walk);
//                Intent i = new Intent(getActivity(), WalkActivity.class);
//                i.putExtra(WalkFragment.EXTRA_WALK_ID, walk.getId());
//                startActivityForResult(i, 0);
            	showCreateWalk();
                return true;
            default:
            	return super.onOptionsItemSelected(item);
        }
    }
    
    private void showCreateWalk() {
    	     Walk walk = new Walk();
    	     WalkSetup.get(getActivity()).addWalk(walk);
    	     Intent i = new Intent(getActivity(), WalkActivity.class);
    	     i.putExtra(WalkFragment.EXTRA_WALK_ID, walk.getId());
    	     startActivityForResult(i, 0);
    	   }
       
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) { 
        //Get the walk from the adapter
    	Walk w = ((WalkAdapter)getListAdapter()).getItem(position);
    	
    	//Start WalkActivity
    	Intent i = new Intent(getActivity(), WalkActivity.class);
    	i.putExtra(WalkFragment.EXTRA_WALK_ID, w.getId());
    	startActivity(i);

    }
    
    private class WalkAdapter extends ArrayAdapter<Walk> {
    	public WalkAdapter(List<Walk> walks) {
    		super(getActivity(), 0, walks);
    	}
    	
    	@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.list_item_walk, null);
            }

            // configure the view for this Crime
            Walk w = getItem(position);

            TextView titleTextView =
                (TextView)convertView.findViewById(R.id.walk_list_item_titleTextView);
            titleTextView.setText(w.getTitle());
            TextView dateTextView =
                (TextView)convertView.findViewById(R.id.walk_list_item_dateTextView);
            dateTextView.setText(w.getDate().toString());

            return convertView;
        }

    }
    
    

}
