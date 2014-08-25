package com.hcs.weatherapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by shivanshmittal on 23/08/14.
 */


public class FetchWeatherDataTask extends AsyncTask<Void, Void, Void> {

    private JSONObject weatherJsonObject;
    private MainActivity activity;

    public FetchWeatherDataTask(MainActivity activity) {
        this.activity = activity;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;

        String json = null;
        Uri uri = Uri.parse("http://api.openweathermap.org/data/2.5/forecast/daily?").buildUpon().appendQueryParameter("q", "London")
                .appendQueryParameter("mode", "json")
                .appendQueryParameter("units", "metric")
                .appendQueryParameter("cnt", "7")
                .build();
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(uri.toString());
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            if (json != null)
                weatherJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        WeatherData[] weatherDataArray = null;
        if (weatherJsonObject != null) {
            try {
                JSONArray forecastData = weatherJsonObject.getJSONArray("list");
                weatherDataArray = new WeatherData[forecastData.length()];
                for (int i = 0; i < forecastData.length(); i++) {
                    WeatherData weatherData = new WeatherData();

                    JSONObject forecastDay = forecastData.getJSONObject(i);
                    weatherData.setDate(Utility.getFormattedDate(forecastDay.getLong("dt")));

                    JSONObject weather = forecastDay.getJSONArray("weather").getJSONObject(0);
                    weatherData.setDescription (weather.getString("description"));
                    JSONObject temperature = forecastDay.getJSONObject("temp");
                    weatherData.setMintemp(temperature.getString("min"));
                    weatherData.setMaxtemp(temperature.getString("max"));
                    weatherDataArray[i] = weatherData;
                    activity.weatherListView.setAdapter(new WeatherAdapter(activity, R.layout.row, weatherDataArray));
                }
            } catch (JSONException ex) {

            }
        }

    }


}