package com.hcs.weatherapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by shivanshmittal on 23/08/14.
 */
public class WeatherAdapter extends ArrayAdapter {

    private String [] weatherDataList ;
    public WeatherAdapter(Context context, int resource, String[] objects) {
        super(context, resource,objects);
        weatherDataList = objects;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  super.getView(position, convertView, parent);
          TextView dateView;
          TextView descriptionView;
          TextView highTempView;
          TextView lowTempView;

          dateView = (TextView) view.findViewById(R.id.dateview);
          descriptionView = (TextView) view.findViewById(R.id.descriptionview);
          highTempView = (TextView) view.findViewById(R.id.max);
          lowTempView = (TextView) view.findViewById(R.id.min);
          String[] weatherData = weatherDataList[position].split("-");
          dateView.setText(weatherData[0]);
          descriptionView.setText(weatherData[1]);
          String[] tempData = weatherData[2].split("/");
          lowTempView.setText(tempData[0]);
          highTempView.setText(tempData[1]);


        return view;

    }

    @Override
    public int getCount() {
        return weatherDataList.length;
    }
}


