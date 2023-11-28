package com.solncev.fx.chat.view;

import com.solncev.fx.chat.client.HttpClientImpl;
import org.json.JSONObject;

public class Currency {
    String currency;
    String json;
    String API = "cur_live_xkJPZAvmVixa1y8ccdMhFEPzqjJ7z3HRSeTB9GLK";
    String url = "https://api.currencyapi.com/v3/latest?apikey=cur_live_xkJPZAvmVixa1y8ccdMhFEPzqjJ7z3HRSeTB9GLK";
    public String getCurrency(String name){
        json = new HttpClientImpl().get(url, null);
        JSONObject json = new JSONObject(this.json);
        return String.valueOf(json.getJSONObject("data").getJSONObject(name).get("value"));
    }
}
