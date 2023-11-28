package com.solncev.fx.chat.view;

import com.solncev.fx.chat.client.HttpClientImpl;
import javafx.scene.shape.StrokeLineCap;
import org.json.JSONObject;
import java.io.IOException;

public class Weather {
    Float kelvinCelsius = 273.15F;
    String weather;
    String API = "630038a3144129b2eca8f66b4ec7ec82";
    protected String getWeather(String city)  {

        weather = new HttpClientImpl().get("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API, null);
        JSONObject json = new JSONObject(weather);
        String temperature = String.valueOf(json.getJSONObject("main").getFloat("temp")-kelvinCelsius);
        String humidity = String.valueOf(json.getJSONObject("main").getInt("humidity"));
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        StringBuilder sb = new StringBuilder();
        sb.append("temperature: " + temperature + "\n");
        sb.append("humidity: " + humidity + "\n");
        sb.append("description: " + description + "\n");
        return sb.toString();
    }
}
