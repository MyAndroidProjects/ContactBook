package com.study.riseof.contactBookAndWeather.weather.xmlParse;

import android.util.Log;

import com.study.riseof.contactBookAndWeather.weather.model.WeatherForecast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastParser {
    private enum TownInfo{
        TOWN_INDEX(0),
        NAME(1),
        LATITUDE(2),
        LONGITUDE(3);
        // на случай выбора города. Положительные: северная широта и восточная долгота, отрицательные: южная широта, западная долгота
        private int index;
        private TownInfo(int index){
            this.index = index;
        }
    }

    private enum TownList{
        NOVOSIBIRSK("99","Novosibirsk");

        String index;
        String name;
        private TownList(String index, String name){
            this.index = index;
            this.name = name;
        }
    }

    private enum TagName {
        MMWEATHER("MMWEATHER"),
        REPORT("REPORT"),
        TOWN("TOWN"),
        FORECAST("FORECAST"),
        PHENOMENA("PHENOMENA"),
        PRESSURE("PRESSURE"),
        TEMPERATURE("TEMPERATURE"),
        WIND("WIND"),
        RELWET("RELWET"),
        HEAT("HEAT");

        final private String name;
        static public TagName getTagNameByValue(String searchingTagName) {
            for (TagName tagName : TagName.values()) {
                if (tagName.name.equals(searchingTagName)) {
                    return tagName;
                }
            }
            throw new RuntimeException("unknown type");
        }
        TagName(String name) {
            this.name = name;
        }
    }

    private enum AttributeName {
        INDEX("index"),
        SNAME("sname"),
        LATITUDE("latitude"),
        LONGITUDE("longitude"),
        DAY("day"),
        MONTH("month"),
        YEAR("year"),
        HOUR("hour"),
        TOD("tod"),
        WEEKDAY("weekday"),
        PREDICT("predict"),
        CLOUDINESS("cloudiness"),
        PRECIPITATION("precipitation"),
        RPOWER("rpower"),
        SPOWER("spower"),
        MIN("min"),
        MAX("max"),
        DIRECTION("direction");

        final private String name;
        AttributeName(String name) {
            this.name = name;
        }
    }

    final String EMPTY_STRING = "";

    private String[] town;
    private List<WeatherForecast> weatherForecasts = new ArrayList<WeatherForecast>();

    public boolean parse(String xmlData){
        WeatherForecast weatherForecast = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        TagName startTagName = TagName.getTagNameByValue(xpp.getName());
                        switch (startTagName){
                            case MMWEATHER:
                                break;
                            case REPORT:
                                break;
                            case TOWN:
                                startTagTown(xpp);
                                break;
                            case FORECAST:
                                weatherForecast = startTagForecast(xpp, weatherForecast);
                                break;
                            case PHENOMENA:
                                weatherForecast = startTagPhenomena(xpp, weatherForecast);
                                break;
                            case PRESSURE:
                                weatherForecast = startTagPressure(xpp, weatherForecast);
                                break;
                            case TEMPERATURE:
                                weatherForecast = startTagTemperature(xpp, weatherForecast);
                                break;
                            case WIND:
                                weatherForecast = startTagWind(xpp, weatherForecast);
                                break;
                            case RELWET:
                                weatherForecast = startTagRelwet(xpp, weatherForecast);
                                break;
                            case HEAT:
                                weatherForecast = startTagHeat(xpp, weatherForecast);
                                break;
                            default:
                                break;
                        }
                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:
                        TagName endTagName = TagName.getTagNameByValue(xpp.getName());
                        switch (endTagName){
                            case FORECAST:
                                endTagForecast(weatherForecast);
                                break;
                            case TOWN:
                                endTagTown();
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }
            Log.d("myLog", "END_DOCUMENT");
        } catch (XmlPullParserException ex){
            Log.e("myTag", "XmlPullParserException: " + ex.getMessage());
        } catch (IOException ex){
            Log.e("myTag", "IOException: " + ex.getMessage());
        }
        return true;
    }

    private void startTagTown(XmlPullParser xpp){
        int attributeCount = xpp.getAttributeCount();
        String townIndex = EMPTY_STRING;
        String latitude = EMPTY_STRING;
        String longitude = EMPTY_STRING;
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.INDEX.name)) {
                townIndex = xpp.getAttributeValue(i);
            } else if (xpp.getAttributeName(i).equals(AttributeName.LATITUDE.name)) {
                latitude = xpp.getAttributeValue(i);
            } else if (xpp.getAttributeName(i).equals(AttributeName.LONGITUDE.name)) {
                longitude = xpp.getAttributeValue(i);
            }
        }
        setTown(townIndex, latitude, longitude);
    }

    public void setTown(String townIndex, String latitude, String longitude) {
        town = new String[TownInfo.values().length];
        town[TownInfo.TOWN_INDEX.index] = townIndex;
        town[TownInfo.LATITUDE.index] = latitude;
        town[TownInfo.LONGITUDE.index] = longitude;
        for (TownList value: TownList.values()) {
            if (townIndex.equals(value.index)) {
                town[TownInfo.NAME.index] = value.name;
            }
        }
    }

    public String[] getTown() {
        return town;
    }

    private WeatherForecast startTagForecast(XmlPullParser xpp, WeatherForecast weatherForecast){
        weatherForecast = new WeatherForecast();
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.DAY.name)) {
                weatherForecast.setDay(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MONTH.name)) {
                weatherForecast.setMonth(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.YEAR.name)) {
                weatherForecast.setYear(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.HOUR.name)) {
                weatherForecast.setHour(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.TOD.name)) {
                weatherForecast.setTod(xpp.getAttributeValue(i));
            }else if (xpp.getAttributeName(i).equals(AttributeName.PREDICT.name)) {
                weatherForecast.setPredict(xpp.getAttributeValue(i));
            }else if (xpp.getAttributeName(i).equals(AttributeName.WEEKDAY.name)) {
                weatherForecast.setWeekday(xpp.getAttributeValue(i));
            }
        }
        weatherForecast.setDate(weatherForecast.getDay(), weatherForecast.getMonth(), weatherForecast.getYear());
        return weatherForecast;
    }

    private WeatherForecast startTagPhenomena(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.CLOUDINESS.name)) {
                weatherForecast.setCloudiness(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.PRECIPITATION.name)) {
                weatherForecast.setPrecipitation(xpp.getAttributeValue(i));
            } /* else if (xpp.getAttributeName(i).equals(AttributeName.RPOWER.name)) {
                weatherForecast.setRPower(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.SPOWER.name)) {
                weatherForecast.setSPower(xpp.getAttributeValue(i));
            } */
        }
        return weatherForecast;
    }

    private WeatherForecast startTagPressure(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.MIN.name)) {
                weatherForecast.setMinPressure(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MAX.name)) {
                weatherForecast.setMaxPressure(xpp.getAttributeValue(i));
            }
        }
        return weatherForecast;
    }

    private WeatherForecast startTagTemperature(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.MIN.name)) {
                weatherForecast.setMinTemperature(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MAX.name)) {
                weatherForecast.setMaxTemperature(xpp.getAttributeValue(i));
            }
        }
        return weatherForecast;
    }

    private WeatherForecast startTagWind(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.MIN.name)) {
                weatherForecast.setMinWindSpeed(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MAX.name)) {
                weatherForecast.setMaxWindSpeed(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.DIRECTION.name)) {
                weatherForecast.setWindDirection(xpp.getAttributeValue(i));
            }
        }
        return weatherForecast;
    }

    private WeatherForecast startTagRelwet(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.MIN.name)) {
                weatherForecast.setMinRelativeWet(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MAX.name)) {
                weatherForecast.setMaxRelativeWet(xpp.getAttributeValue(i));
            }
        }
        return weatherForecast;
    }

    private WeatherForecast startTagHeat(XmlPullParser xpp, WeatherForecast weatherForecast){
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xpp.getAttributeName(i).equals(AttributeName.MIN.name)) {
                weatherForecast.setMinHeat(xpp.getAttributeValue(i));
            } else if (xpp.getAttributeName(i).equals(AttributeName.MAX.name)) {
                weatherForecast.setMaxHeat(xpp.getAttributeValue(i));
            }
        }
        return weatherForecast;
    }

    private void endTagForecast(WeatherForecast weatherForecast){
        weatherForecasts.add(weatherForecast);
    }

    private void endTagTown(){
        Log.d("myLog","endTagTown");
    }

    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }
}
