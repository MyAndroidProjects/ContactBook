package com.study.riseof.contactbook;

public class WeatherForecast {

    private enum Tod{
        // tod - время суток, для которого составлен прогноз: 0 - ночь 1 - утро, 2 - день, 3 - вечер
        NIGHT("0","Night"),
        MORNING("1","Morning"),
        DAY("2","Day"),
        EVENING("3","Evening");

        String index;
        String dayPart;
        private Tod(String index, String dayPart){
            this.index = index;
            this.dayPart = dayPart;
        }
    }

    private enum Weekday{
        //weekday -	день недели, 1 - воскресенье, 2 - понедельник, и т.д.
        SUNDAY("1","Sunday"),
        MONDAY("2","Monday"),
        TUESDAY("3","Tuesday"),
        WEDNESDAY("4","Wednesday"),
        THURSDAY("5","Thursday"),
        FRIDAY("6","Friday"),
        SATURDAY("7","Saturday");

        String index;
        String name;
        private Weekday(String index, String name){
            this.index = index;
            this.name = name;
        }
    }

    private enum Cloudiness{
        //cloudiness -	облачность по градациям: -1 - туман, 0 - ясно, 1 - малооблачно, 2 - облачно, 3 - пасмурно
        FOG("-1","fog"),
        CLEAR("0","clear"),
        LOW_CLOUD("1","low cloud"),
        CLOUDY("2","cloudy"),
        OVERCAST("3","overcast ");

        String index;
        String name;
        private Cloudiness(String index, String name){
            this.index = index;
            this.name = name;
        }
    }

    private enum Precipitation{
        // precipitation -	тип осадков: 3 - смешанные, 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
        MIXED("3","mixed"),
        RAIN("4","rain"),
        CLOUDBURST("5","cloudburst"),
        SNOW("6","snow"),
        WET_SNOW("7","wet snow"),
        THUNDERSTORM("8","thunderstorm"),
        NO_DATA("9","no data"),
        NO_PRECIPITATION("10","no precipitation");

        String index;
        String name;
        private Precipitation(String index, String name){
            this.index = index;
            this.name = name;
        }
    }

    private enum WindDirection{
        // direction - направление ветра , 0 - северный, 1 - северо-восточный, и т.д
        N("0","N"),
        NE("1","NE"),
        E("2","E"),
        SE("3","SE"),
        S("4","S"),
        SW("5","SW"),
        W("6","W"),
        NW("7","NW");

        String index;
        String name;
        private WindDirection(String index, String name){
            this.index = index;
            this.name = name;
        }
    }

    private int DEGREE_SYMBOL_CODE=186;

    private String day;
    private String month;
    private String year;
    private String date;
    private String hour;
    private String tod;
    private String weekday;
    private String predict;
    private String cloudiness;
    private String precipitation;
    //   private String rPower;
    //   private String sPower;
    private String minPressure;
    private String maxPressure;
    private String minTemperature;
    private String maxTemperature;
    private String minWindSpeed;
    private String maxWindSpeed;
    private String windDirection;
    private String minRelativeWet;
    private String maxRelativeWet;
    private String minHeat;
    private String maxHeat;

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDate(String day, String month, String year) {
        this.date = day + "." + month + "." + year;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTod(String tod) {
        for (Tod val:Tod.values()){
            if(tod.equals(val.index)){
                this.tod = val.dayPart;
                return;
            }
        }
        this.tod = tod;
    }

    public void setWeekday(String weekday) {
        for (Weekday val:Weekday.values()){
            if(weekday.equals(val.index)){
                this.weekday = val.name;
                return;
            }
        }
        this.weekday = weekday;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public void setCloudiness(String cloudiness) {
        for (Cloudiness val : Cloudiness.values()){
            if(cloudiness.equals(val.index)){
                this.cloudiness = val.name;
                return;
            }
        }
        this.cloudiness = cloudiness;
    }

    public void setPrecipitation(String precipitation) {
        for (Precipitation val : Precipitation.values()){
            if(precipitation.equals(val.index)){
                this.precipitation = val.name;
                return;
            }
        }
        this.precipitation = precipitation;
    }
    /*
        public void setRPower(String rPower) {
            this.rPower = rPower;
        }

        public void setSPower(String sPower) {
            this.sPower = sPower;
        }
    */
    public void setMinPressure(String minPressure) {
        this.minPressure = minPressure;
    }

    public void setMaxPressure(String maxPressure) {
        this.maxPressure = maxPressure;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinWindSpeed(String minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public void setMaxWindSpeed(String maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public void setWindDirection(String windDirection) {
        for (WindDirection val : WindDirection.values()){
            if(windDirection.equals(val.index)){
                this.windDirection = val.name;
                return;
            }
        }
        this.windDirection = windDirection;
    }

    public void setMinRelativeWet(String minRelativeWet) {
        this.minRelativeWet = minRelativeWet;
    }

    public void setMaxRelativeWet(String maxRelativeWet) {
        this.maxRelativeWet = maxRelativeWet;
    }

    public void setMinHeat(String minHeat) {
        this.minHeat = minHeat;
    }

    public void setMaxHeat(String maxHeat) {
        this.maxHeat = maxHeat;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }
    public String getTime() {
        return hour+":00";
    }

    public String getTod() {
        return tod;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getPredict() {
        return predict;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getPrecipitation() {
        return precipitation;
    }
/*
    public String getRPower() {
        return rPower;
    }

    public String getSPower() {
        return sPower;
    }*/

    public String getMaxPressure() {
        return maxPressure;
    }

    public String getMinPressure() {
        return minPressure;
    }

    public String getPressure() {
        String pressure = minPressure +".." + maxPressure + " mmHg";
        return pressure;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }
    public String getTemperature() {
        String temperature = minTemperature + ".." + maxTemperature + "," + ((char)DEGREE_SYMBOL_CODE)+"C";
        return temperature;
    }

    public String getMinWindSpeed() {
        return minWindSpeed;
    }

    public String getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWind() {
        String wind = minWindSpeed + "-" + maxWindSpeed + " m/s, "+ windDirection;
        return wind;
    }

    public String getMinRelativeWet() {
        return minRelativeWet;
    }

    public String getMaxRelativeWet() {
        return maxRelativeWet;
    }

    public String getRelativeWet() {
        String wet = minRelativeWet + "-" + maxRelativeWet + " %";
        return wet;
    }

    public String getMaxHeat() {
        return maxHeat;
    }

    public String getMinHeat() {
        return minHeat;
    }
    public String getHeat() {
        String heat = maxHeat + ".." + maxHeat + "," + ((char)DEGREE_SYMBOL_CODE)+"C";
        return heat;
    }
}
