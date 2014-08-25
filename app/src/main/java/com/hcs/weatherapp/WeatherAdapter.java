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

    private String[] weatherDataList;

    public WeatherAdapter(Context context, int resource, String[] objects) {
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

        String[] weatherData = weatherDataList[position].split("-");
        viewHolder.dateView.setText(weatherData[0]);
        viewHolder.descriptionView.setText(weatherData[1]);
        String[] tempData = weatherData[2].split("/");
        viewHolder.lowTempView.setText(tempData[0]);
        viewHolder.highTempView.setText(tempData[1]);
        return view;
    }

    static class ViewHolder {

        TextView dateView;
        TextView descriptionView;
        TextView highTempView;
        TextView lowTempView;
    }


}


