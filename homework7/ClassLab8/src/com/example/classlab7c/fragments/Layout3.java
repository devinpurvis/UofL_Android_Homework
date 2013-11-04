package com.example.classlab7c.fragments;

import java.util.List;

import com.example.classlab7c.R;
import com.example.classlab7c.adapters.ArtistListAdapter;
import com.example.classlab7c.adapters.EventListAdapter;
import com.example.classlab7c.model.Artist;
import com.example.classlab7c.model.Event;
import com.example.classlab7c.service.MusicListService;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Layout3 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.layout3, container, false);
		
		List<Event>events = MusicListService.getInstance(getActivity()).getAllEvents();
		
		EventListAdapter adapter =
				new EventListAdapter(getActivity(), R.layout.listview_for_each_event, events);
		ListView listViewMusic = (ListView) view.findViewById(R.id.listViewEvents);
		listViewMusic.setAdapter(adapter);
		
		return view;
	}

}
