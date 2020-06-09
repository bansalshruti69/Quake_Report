package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends ArrayAdapter<Earthquake> {
    public CustomAdapter(Activity context, ArrayList<Earthquake> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_category, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.mag);

        GradientDrawable magnitudeCircle = (GradientDrawable) nameTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentAndroidFlavor.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        DecimalFormat formatter = new DecimalFormat("0.0");
        String mag = formatter.format(currentAndroidFlavor.getMagnitude());
        nameTextView.setText(mag);

        // Find the TextView in the list_item.xml layout with the ID version_number
        String city = currentAndroidFlavor.getCity();
        ArrayList<String> c = new ArrayList<>();
        int i = city.indexOf("of");
        if(i==-1){
            c.add("Near the");
            c.add(city);
        }
        else{
            c.add(city.substring(0,i+2));
            c.add(city.substring(i+3,city.length()));
        }
        TextView offsetcity = (TextView) listItemView.findViewById(R.id.offset_city);
        offsetcity.setText(c.get(0));
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.city);
        numberTextView.setText(c.get(1));

        Date dateObject = new Date(currentAndroidFlavor.getDate());
        String date = formatDate(dateObject);
        TextView numberTextView1 = (TextView) listItemView.findViewById(R.id.date);
        numberTextView1.setText(date);
        String time = formatTime(dateObject);
        TextView numberTextView2 = (TextView) listItemView.findViewById(R.id.time);
        numberTextView2.setText(time);
        return listItemView;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
