
/* Starter project for Mobile Platform Development in Semester B Session 2018/2019
You should use this project as the starting point for your assignment.
This project simply reads the data from the required URL and displays the
raw data in a TextField
*/
//
// Name: Ndubuisi_Monica_Onyinye
// Student ID: S2040060

//
package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.fragment.HomeFragment;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.service.EarthquakeFeedParser;
import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.util.EarthQuakeDb;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private TextView rawDataDisplay;
    private String result;
    private String url1 = "";
    private String urlSource = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the raw links to the graphical components
        rawDataDisplay = (TextView) findViewById(R.id.rawDataDisplay);

        // More Code goes here

        // Set up the raw links to the graphical components
        bottomNav = findViewById(R.id.bottomNav);
        addListenerToBottomNav();

        setupHomeFragment();
        EarthquakeFeedParser parser=new EarthquakeFeedParser(urlSource, EarthQuakeDb.instance());
        parser.execute();
    }

    public void onClick(View aview) {
        startProgress();
    }

    public void startProgress() {
        // Run network access on a separate thread;
        //new Thread(new Task(urlSource)).start();
    }

    // Need separate thread to access the internet resource over network
    // Other neater solutions should be adopted in later iterations.
    private class Task implements Runnable {
        private String url;

        public Task(String aurl) {
            url = aurl;
        }

        @Override
        public void run() {
            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";
            Log.e("MyTag", "in run");
            try {
                Log.e("MyTag", "in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                //
                // Throw away the first 2 header lines before parsing
                //
                while ((inputLine = in.readLine()) != null) {
                    result = result + inputLine;
                    Log.e("MyTag", inputLine);
                }
                in.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }

            //
            // Now that you have the xml data you can parse it
            //
            // Now update the TextView to display raw XML data
            // Probably not the best way to update TextView
            // but we are just getting started !

            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    rawDataDisplay.setText(result);
                }
            });
        }
    }

    private void setupHomeFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HomeFragment hf = new HomeFragment();
        ft.replace(R.id.home_fragment_holder, hf);
        ft.commit();
    }

    private void addListenerToBottomNav() {
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()) {
                    case R.id.item_home:
                        ft.replace(R.id.home_fragment_holder, new HomeFragment());
                        ft.commit();
                        break;
                }
                return false;
            }
        });
    }
}