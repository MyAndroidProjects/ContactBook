package com.study.riseof.contactBookAndWeather.weather.network;

import android.os.AsyncTask;
import android.util.Log;

import com.study.riseof.contactBookAndWeather.weather.presenter.WeatherForecastContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherForecastDataLoader extends AsyncTask<String, Void, String> implements WeatherForecastContract.WeatherDataLoader {

    private StopExecuteListener stopExecuteListener;

    @Override
    public void startExecute(String weatherSitePath) {
        this.execute(weatherSitePath);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... urlPaths) {
        String dataXml = "";
        int index = 0;
        try {
            if (isCancelled()) {
                return null;
            }
            dataXml = downloadXml(urlPaths[index]);
        } catch (IOException ex) {
            Log.e("myTag", "downloadXML: IO Exception reading data: " + ex.getMessage());
        }
        return dataXml;
    }

    @Override
    protected void onPostExecute(String xmlData) {
        super.onPostExecute(xmlData);
        if (stopExecuteListener != null) {
            stopExecuteListener.stopExecute(xmlData);
        }
    }

    private String downloadXml(String urlPath) throws IOException {
        StringBuilder xmlResult = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (isCancelled()) {
                    return null;
                }
                xmlResult.append(line);
            }
            return xmlResult.toString();
        } catch (MalformedURLException ex) {
            Log.e("myTag", "downloadXML: Invalid URL " + ex.getMessage());
        } catch (IOException ex) {
            Log.e("myTag", "downloadXML: IO Exception reading data: " + ex.getMessage());
        } catch (SecurityException ex) {
            Log.e("myTag", "downloadXML: Security Exception.  Needs permission? " + ex.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    public void setStopExecuteListener(StopExecuteListener stopExecuteListener) {
        this.stopExecuteListener = stopExecuteListener;
    }

    public interface StopExecuteListener {
        void stopExecute(String xmlData);
    }

}

