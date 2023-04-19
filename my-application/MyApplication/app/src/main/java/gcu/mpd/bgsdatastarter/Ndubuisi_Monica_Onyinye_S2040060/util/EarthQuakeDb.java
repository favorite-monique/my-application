package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.util;


import java.util.ArrayList;
import java.util.List;

import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthQuake;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthQuakesAdapter;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthquakeListener;

public class EarthQuakeDb implements EarthquakeListener {

    private static List<EarthQuake>earthQuakeList=new ArrayList<>();
    private  static EarthQuakeDb earthQuakeDb=null;
    public static EarthQuakesAdapter adapter;


    //private to ensure can only be created in this class to ensure data integrity
    private EarthQuakeDb(){

    }

    public static EarthQuakeDb instance(){
        if(earthQuakeDb==null){
            earthQuakeDb=new EarthQuakeDb();
        }
        return  earthQuakeDb;
    }
    @Override
    public void onEarthquakesLoaded(List<EarthQuake> earthquakes) {
        earthQuakeList=earthquakes;
        adapter.setEarthQuakeList(earthquakes);
    }

    @Override
    public void onError(String error) {

        earthQuakeList=new ArrayList<EarthQuake>();
    }

    public List<EarthQuake> getEarthQuakesLoaded() {
        return earthQuakeList;
    }
}
