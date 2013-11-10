package com.example.classlab7c.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.classlab7c.R;
import com.example.classlab7c.adapters.SongAdapter;
import com.example.classlab7c.model.Song;
import com.example.classlab7c.service.MusicListService;

public class Layout1 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.layout1, container, false);
		
		List<Song>songs = MusicListService.getInstance(getActivity()).getAllSongs();
		
		SongAdapter adapter =
				new SongAdapter(getActivity(), R.layout.listview_for_each_song, songs);
		ListView listViewMusic = (ListView) view.findViewById(R.id.listViewSongs);
		listViewMusic.setAdapter(adapter);
		
		return view;
	}

}
