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

    private WeatherData[] weatherDataList;

    public WeatherAdapter(Context context, int resource, WeatherData[] objects) {
        super(context, resource, objects);
        weatherDataList = objects;

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

        viewHolder.dateView.setText(weatherDataList[position].date);
        viewHolder.descriptionView.setText(weatherDataList[position].description);
        viewHolder.lowTempView.setText(weatherDataList[position].mintemp);
        viewHolder.highTempView.setText(weatherDataList[position].maxtemp);
        return view;
    }

    static class ViewHolder {

        TextView dateView;
        TextView descriptionView;
        TextView highTempView;
        TextView lowTempView;
    }


}


