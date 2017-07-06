package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;
import util.Utils;

/**
 * Created by harri on 01/07/2017.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data){

        Weather weather = new Weather();

        try {

            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat",coordObj));
            place.setLon(Utils.getFloat("lon",coordObj));

            // get sys obj:
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastupdate(Utils.getInt("dt", jsonObject));
            place.setCity(Utils.getString("name", jsonObject));

            weather.place = place;

            // get weather obj:
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentConditions.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentConditions.setDescription(Utils.getString("description", jsonWeather));
            weather.currentConditions.setCondition(Utils.getString("main", jsonWeather));
            weather.currentConditions.setIcon(Utils.getString("icon", jsonWeather));

            //Let's get the main object
//            JSONObject mainObj = Utils.getObject("main", jsonObject);
//            weather.currentConditions.setHumidity(Utils.getInt("humidity", mainObj));
//            weather.currentConditions.setPressure(Utils.getInt("pressure", mainObj));
//            weather.currentConditions.setMinTemperature(Utils.getFloat("temp_min", mainObj));
//            weather.currentConditions.setMinTemperature(Utils.getFloat("temp_max", mainObj));
//            weather.currentConditions.setTemperature(Utils.getDouble("temp", mainObj));


            //Let's setup wind
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));


            //Setup clouds
            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPercipitation(Utils.getInt("all", cloudObj));

            return weather;



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
