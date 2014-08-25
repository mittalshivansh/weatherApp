package com.hcs.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by shivanshmittal on 23/08/14.
 */
public class WeatherAdapter extends ArrayAdapter {

    public WeatherAdapter(Context context, int resource, WeatherData[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.dateView = (TextView) view.findViewById(R.id.dateview);
            viewHolder.descriptionView = (TextView) view.findViewById(R.id.descriptionview);
            viewHolder.highTempView = (TextView) view.findViewById(R.id.max);
            viewHolder.lowTempView = (TextView) view.findViewById(R.id.min);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        WeatherData weatherData = (WeatherData) getItem(position);
        viewHolder.dateView.setText(weatherData.getDate());
        viewHolder.descriptionView.setText(weatherData.getDescription());
        viewHolder.lowTempView.setText(weatherData.getMintemp());
        viewHolder.highTempView.setText(weatherData.getMaxtemp());
        return view;
    }

    static class ViewHolder {

        TextView dateView;
        TextView descriptionView;
        TextView highTempView;
        TextView lowTempView;
    }

}


