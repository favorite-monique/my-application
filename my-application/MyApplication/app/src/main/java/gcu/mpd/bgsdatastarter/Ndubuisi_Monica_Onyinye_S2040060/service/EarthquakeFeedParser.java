package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.service;


import android.os.AsyncTask;
import android.util.Log;

import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthQuake;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model.EarthquakeListener;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeFeedParser extends AsyncTask<Void, Void, List<EarthQuake>> {
    private static final String TAG = EarthquakeFeedParser.class.getSimpleName();

    private String mUrl;
    private EarthquakeListener mListener;


    public EarthquakeFeedParser(String url, EarthquakeListener listener) {
        mUrl = url;
        mListener = listener;
    }

    @Override
    protected List<EarthQuake> doInBackground(Void... voids) {
        List<EarthQuake> earthquakes = new ArrayList<>();

        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            EarthQuake earthquake = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            earthquake = new EarthQuake();
                        } else if (tagName.equalsIgnoreCase("title")) {
                            if (earthquake != null) {
                                earthquake.setTitle(parser.nextText());
                            }
                        } else if (tagName.equalsIgnoreCase("description")) {
                            if (earthquake != null) {
                                String description = parser.nextText();
                                String[] parts = description.split(";");

                                for (String part : parts) {
                                    String[] keyValue = part.trim().split(":");
                                    String key = keyValue[0].trim().toLowerCase();
                                    String value = keyValue[1].trim();

                                    switch (key) {
                                        case "origin date/time":
                                            earthquake.setOriginDateTime(value);
                                            break;
                                        case "lat/long":
                                            String[] latLong = value.split(",");
                                            double latitude = Double.parseDouble(latLong[0].trim());
                                            double longitude = Double.parseDouble(latLong[1].trim());
                                            earthquake.setLatitude(latitude);
                                            earthquake.setLongitude(longitude);
                                            break;
                                        case "depth":
                                            int depth = Integer.parseInt(value.split(" ")[0]);
                                            earthquake.setDepth(depth);
                                            break;
                                        case "magnitude":
                                            double magnitude = Double.parseDouble(value);
                                            earthquake.setMagnitude(magnitude);
                                            break;
                                    }
                                }
                            }
                        } else if (tagName.equalsIgnoreCase("link")) {
                            if (earthquake != null) {
                                earthquake.setLink(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            earthquakes.add(earthquake);
                            earthquake = null;
                        }
                        break;
                }

                eventType = parser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Error parsing earthquake feed", e);
            mListener.onError(e.getMessage());
        }

        return earthquakes;
    }

    @Override
    protected void onPostExecute(List<EarthQuake> earthquakes) {
        mListener.onEarthquakesLoaded(earthquakes);
        Log.d("EQ",earthquakes.toString());
    }}
