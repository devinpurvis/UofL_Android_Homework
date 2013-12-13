package com.purvis.devin.walk;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class WalkListFragment extends ListFragment {
	
	private static final String TAG = "WalkListFragment";
	private List<Walk> mWalks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.walks_title);
        mWalks = WalkSetup.get(getActivity()).getWalks();
        
        WalkAdapter adapter = new WalkAdapter(mWalks);
        setListAdapter(adapter);
    }
       
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) { 
        Walk w = ((WalkAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, w.getTitle() + " was clicked");
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
