package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.R;


public class EarthQuakeDetailsFragment extends Fragment {
    public EarthQuakeDetailsFragment() {
        // Required empty public constructor
    }

    public static EarthQuakeDetailsFragment newInstance(String data){
        return new EarthQuakeDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_earthquake_details, container, false);

        TextView earthQuakeNameTv = v.findViewById(R.id.home_earthquake_name);
        TextView earthQuakeDateTv = v.findViewById(R.id.home_earthquake_date);
        TextView earthQuakeLongitudeTv = v.findViewById(R.id.home_earthquake_longitude);
        TextView earthQuakeLatitudeTv = v.findViewById(R.id.home_earthquake_latitude);
        TextView earthQuakeMagnitudeTv = v.findViewById(R.id.home_earthquake_magnitude);
        TextView earthQuakeDepthTv = v.findViewById(R.id.home_earthquake_depth);


        return v;
    }
}