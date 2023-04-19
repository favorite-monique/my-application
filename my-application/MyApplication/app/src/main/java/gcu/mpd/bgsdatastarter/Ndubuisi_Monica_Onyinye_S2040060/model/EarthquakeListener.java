package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model;

import java.util.List;

public interface EarthquakeListener {
        void onEarthquakesLoaded(List<EarthQuake> earthquakes);
        void onError(String error);
        List<EarthQuake> getEarthQuakesLoaded();
    }