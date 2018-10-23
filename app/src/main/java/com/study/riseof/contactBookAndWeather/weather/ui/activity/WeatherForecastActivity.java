package com.study.riseof.contactBookAndWeather.weather.ui.activity;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.weather.objects.WeatherForecast;
import com.study.riseof.contactBookAndWeather.weather.ui.adapter.WeatherForecastRecyclerAdapter;
import com.study.riseof.contactBookAndWeather.weather.xmlParse.WeatherForecastParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends AppCompatActivity{

    // ??? два одинаковых enum TownInfo (здесь и в WeatherForecastParser)
    // делать его публичным в парсере или как быть ???
    private enum TownInfo{
        TOWN_INDEX(0),
        NAME(1),
        LATITUDE(2),
        LONGITUDE(3);

        private int index;
        private TownInfo(int index){
            this.index = index;
        }
    }

    private final String WEATHER_SITE_PATH = "https://xml.meteoservice.ru/export/gismeteo/point/99.xml";
    private final String NORTH_LATITUDE = "N";
    private final String EAST_LONGITUDE = "E";
    private final int DEGREE_SYMBOL_CODE = 186;
    private final String LOADING = "loading...";
    private final String EMPTY_STRING = "";
    private String[] town;
    private List<WeatherForecast>  weatherForecasts;
    private WeatherForecastRecyclerAdapter adapter;
    private WeatherForecastDataLoader weatherForecastDataLoader;

    @BindView(R.id.weather_forecast_toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_weather_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.town_name)
    TextView townNameText;
    @BindView(R.id.town_coordinates)
    TextView townCoordinatesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        weatherForecastDataLoader = new WeatherForecastDataLoader();
        weatherForecastDataLoader.execute(WEATHER_SITE_PATH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherForecastDataLoader.cancel(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.menu_item_update_forecast :
                weatherForecastDataLoader = new WeatherForecastDataLoader();
                weatherForecastDataLoader.execute(WEATHER_SITE_PATH);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setWeatherForecastRecyclerAdapter() {
        adapter = new WeatherForecastRecyclerAdapter(this, weatherForecasts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private String getTownCoordinates() {
        String coord = town[TownInfo.LATITUDE.index] + (char)DEGREE_SYMBOL_CODE + NORTH_LATITUDE+ " "+
                town[TownInfo.LONGITUDE.index] + (char)DEGREE_SYMBOL_CODE + EAST_LONGITUDE;
        return coord;
    }

    private void resetTownInfoText(){
        townNameText.setText(LOADING);
        townCoordinatesText.setText(EMPTY_STRING);
    }
    private void setTownInfoText(){
        townNameText.setText(town[TownInfo.NAME.index]);
        townCoordinatesText.setText(getTownCoordinates());
    }

    private class WeatherForecastDataLoader extends AsyncTask<String, Void, String>{

        private WeatherForecastParser weatherForecastParser;

        @Override
        protected void onPreExecute(){
            resetTownInfoText();
        }

        @Override
        protected String doInBackground(String... urlPaths) {
            String dataXml="";
            int index = 0;
            try{
                if (isCancelled()) {
                    return null;
                }
                dataXml = downloadXml(urlPaths[index]);
            }
            catch (IOException ex){
                Log.e("myTag", "downloadXML: IO Exception reading data: " + ex.getMessage());
            }
            return dataXml;
        }

        @Override
        protected void onPostExecute(String xmlData) {
            super.onPostExecute(xmlData);
            weatherForecastParser = new WeatherForecastParser();
            if(xmlData !=null &&  weatherForecastParser.parse(xmlData))
            {
                town = weatherForecastParser.getTown();
                weatherForecasts = weatherForecastParser.getWeatherForecasts();
                setWeatherForecastRecyclerAdapter();
                setTownInfoText();
            }
        }

        private String downloadXml (String urlPath)throws IOException {
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try {
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (isCancelled()) {
                        return null;
                    }
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            } catch(MalformedURLException ex) {
                Log.e("myTag", "downloadXML: Invalid URL " + ex.getMessage());
            } catch(IOException ex) {
                Log.e("myTag", "downloadXML: IO Exception reading data: " + ex.getMessage());
            } catch(SecurityException ex) {
                Log.e("myTag", "downloadXML: Security Exception.  Needs permisson? " + ex.getMessage());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return null;
        }
    }
}
