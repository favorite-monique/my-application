package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.R;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthQuakesAdapter;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.util.EarthQuakeDb;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Lookup the recyclerview in activity layout
        RecyclerView rvEarthQuakes = (RecyclerView) v.findViewById(R.id.rvEarthquakes);

        // Create adapter passing in the sample user data
        EarthQuakesAdapter recyclerAdapter = new EarthQuakesAdapter(EarthQuakeDb.instance().getEarthQuakesLoaded());
        EarthQuakeDb.adapter=recyclerAdapter;

        recyclerAdapter.setEarthQuakeList(EarthQuakeDb.instance().getEarthQuakesLoaded());

        // Attach the adapter to the recyclerview to populate items
        rvEarthQuakes.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

        // Set layout manager to position the items
        rvEarthQuakes.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}