package com.example.classlab7c.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.classlab7c.R;
import com.example.classlab7c.model.Artist;
import com.example.classlab7c.model.MenuItem;
import com.example.classlab7c.model.Song;

public class ArtistListAdapter extends ArrayAdapter<Artist>{ 
	private Context mContext;
	private List<Artist> mEntries;
	
	public ArtistListAdapter(Context context,int textViewResourceId, List<Artist> entries) { 
		super(context, textViewResourceId, entries);
		mContext=context;
	    mEntries=entries; 
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		     view = inflater.inflate(R.layout.listview_for_each_artist, parent, false);
		}
		Artist artist = mEntries.get(position);
		
		TextView textViewDescription = (TextView)view.findViewById(R.id.textViewArtistName);
		textViewDescription.setText(artist.getArtistName());
		
		String tmpSongs = "";
		for (Song song:artist.getSongs()) {
			tmpSongs += "Song: " + song.getSongTitle() + "\nAlbum: " + song.getAlbumTitle() + "\n\n";
		}
		
		TextView textViewSongs = (TextView)view.findViewById(R.id.textViewArtistSongs);
		textViewSongs.setText(tmpSongs);
		
		return view;
	}	
}


