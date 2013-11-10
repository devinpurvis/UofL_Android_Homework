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
import com.example.classlab7c.model.Song;

public class SongAdapter extends ArrayAdapter<Song>{
	private SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd"); 
	private Context mContext;
	private List<Song> mEntries;
	
	public SongAdapter(Context context,int textViewResourceId, List<Song> entries) { 
		super(context, textViewResourceId, entries);
	    mContext=context;
	    mEntries=entries; 
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		     view = inflater.inflate(R.layout.listview_for_each_song, parent, false);
		}
		Song song = mEntries.get(position);
		
		TextView textViewTitle = (TextView)view.findViewById(R.id.textViewSongTitle);
		textViewTitle.setText(song.getSongTitle());
		
		TextView textViewDescription = (TextView)view.findViewById(R.id.textViewSongDescription);
		textViewDescription.setText("Album: " + song.getAlbumTitle());
		
		TextView textViewPublished= (TextView)view.findViewById(R.id.textViewSongPublish);
		textViewPublished.setText("Published: " + df.format(song.getSongPublishedDate()));
		
		return view;
	}
}
