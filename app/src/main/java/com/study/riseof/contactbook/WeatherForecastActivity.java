package com.study.riseof.contactbook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends AppCompatActivity
        implements WeatherForecastParser.PassLocation, WeatherForecastParser.PassWeatherForecasts {

    private enum TOWN{
        NOVOSIBIRSK("99","Novosibirsk");

        String index;
        String name;
        private TOWN(String index, String name){
            this.index = index;
            this.name = name;
        }
    }


    private String townIndex;
    private String town;
    private String latitude;
    private String longitude;
    private  List<WeatherForecast>  weatherForecasts;
    private WeatherForecastParser weatherForecastParser;
    private WeatherForecastRecyclerAdapter adapter;
    @BindView(R.id.recycler_view_weather_forecast)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ButterKnife.bind(this);

        weatherForecastParser = new WeatherForecastParser(this);
        /*
// загрузка локального файла
        XmlPullParser xpp = getResources().getXml(R.xml.local_weather);
        */
        // тестовая строка
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?><MMWEATHER><REPORT type=\"frc3\"><TOWN index=\"99\" sname=\"%D0%9D%D0%BE%D0%B2%D0%BE%D1%81%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA\" latitude=\"55\" longitude=\"83\"><FORECAST day=\"28\" month=\"10\" year=\"2018\" hour=\"21\" tod=\"3\" predict=\"0\" weekday=\"4\">" +
                "<PHENOMENA cloudiness=\"2\" precipitation=\"6\" rpower=\"0\" spower=\"0\"/></FORECAST></TOWN></REPORT></MMWEATHER>";
        weatherForecastParser.parse(xmlData);


    }

    @Override
    public void location(String townIndex, String latitude, String longitude) {
        this.townIndex = townIndex;
        this.latitude = latitude;
        this.longitude = longitude;
        setTown(townIndex);
        Log.d("myLog","location"+" this.townIndex "+this.townIndex+" latitude "+this.latitude+" longitude "+this.longitude);
    }


    @Override
    public void passWeatherForecasts(List<WeatherForecast> forecasts) {
        weatherForecasts = forecasts;
        Log.d("myLog","weatherForecasts.size() = "+weatherForecasts.size());
        for(int i=0;i<weatherForecasts.size();i++){
            WeatherForecast wf = weatherForecasts.get(i);


            Log.d("myLog","town "+ town + " longitude "+longitude + " latitude "+ latitude+"\n"+
                    "дата " +wf.getDate()+" время суток " +wf.getTod()+ " время "+wf.getHour()+"\n"+
                    "часы прогноза " +wf.getPredict()+ " день недели "+ wf.getWeekday()+"\n"+
                    "облачность "+wf.getCloudiness()+" осадки "+wf.getPrecipitation()+"\n"+
                    "давление мин "+wf.getMinPressure()+" давление макс "+wf.getMaxPressure()+"\n"+
                    "температура мин "+wf.getMinTemperature()+" температура макс "+wf.getMaxTemperature()+"\n"+
                    "ветер мин "+wf.getMinWindSpeed()+" ветер макс "+wf.getMaxWindSpeed()+" ветер направление " +wf.getWindDirection()+"\n"+
                    "влажность мин"+wf.getMinRelativeWet()+" влажность макс"+wf.getMaxRelativeWet()+"\n"+
                    "ощущается мин "+wf.getMinHeat()+ " ощущается макс "+wf.getMaxHeat()+"\n"+wf.getRelativeWet()
            );
        }
        adapter = new WeatherForecastRecyclerAdapter(this, weatherForecasts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    public void setTown(String town) {
        for (TOWN value:TOWN.values()){
            if(town.equals(value.index)){
                this.town = value.name;
                return;
            }
        }
        this.town = town;
    }

private class WeatherForecastDataLoader extends AsyncTask<Void, Void, XmlPullParser>{

    @Override
    protected XmlPullParser doInBackground(Void... voids) {
        return null;
    }
}

}
