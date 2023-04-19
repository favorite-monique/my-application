package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.R;

import java.util.List;

public class EarthQuakesAdapter extends RecyclerView.Adapter<EarthQuakesAdapter.ViewHolder> {

    List<EarthQuake> earthQuakeList;
    Context context;

    // Pass in the contact array into the constructor
    public EarthQuakesAdapter(List<EarthQuake> earthQuakeList) {
        this.earthQuakeList = earthQuakeList;
    }

    @NonNull
    @Override
    //Involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_earth_quake, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        // Get the data model based on position
        EarthQuake earthQuake = earthQuakeList.get(position);

        // Set item views based on your views and data model
        TextView username = holder.earthquakeNameTextView;

        String earthQuakeName = earthQuake.getTitle().substring(30, 35);
        String paddedStr = String.format("%-10s", earthQuakeName);
        username.setText(paddedStr);

        TextView dateTv = holder.dateTextView;
        //dateTv.setText(earthQuake.getTitle().substring(earthQuake.getTitle().length()-20,earthQuake.getTitle().length()-8));
        dateTv.setText(earthQuake.getPubDate());

        TextView magnitudeTv = holder.magnitudeValueTextView;
        magnitudeTv.setText(earthQuake.getMagnitude() + "");

        TextView colorTextView = holder.magnitudeColorTextView;
        //setting color based on magnitude

        Double magnitude = earthQuake.getMagnitude();
        if (magnitude < 5.5) {

            colorTextView.setTextColor(Color.GREEN);
            colorTextView.setText("Low");

        } else if (magnitude >= 5.5 && magnitude < 7.0) {

            colorTextView.setTextColor(Color.YELLOW);
            colorTextView.setText("medium");

        } else {

            colorTextView.setTextColor(Color.RED);
            colorTextView.setText("High");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data="data";
//                FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                EarthQuakeDetailsFragment fragment = EarthQuakeDetailsFragment.newInstance(data);
//                ft.replace(R.id.rvEarthquakes, fragment);
//                ft.addToBackStack(null);
//                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.earthQuakeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Contains a member variable
        // for any view that will be set as a row is rendered
        public TextView magnitudeColorTextView;
        public TextView earthquakeNameTextView;
        public TextView dateTextView;
        public TextView magnitudeValueTextView;

        // A constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {

            // Super Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            magnitudeColorTextView = (TextView) itemView.findViewById(R.id.magnitudeColorTextView);
            earthquakeNameTextView = itemView.findViewById(R.id.textViewName);
            dateTextView = itemView.findViewById(R.id.textViewDate);
            magnitudeValueTextView = itemView.findViewById(R.id.magnitudeValueTextView);
        }
    }

    public void setEarthQuakeList(List<EarthQuake> earthQuakeList) {
        this.earthQuakeList = earthQuakeList;
        notifyDataSetChanged();
    }
}
