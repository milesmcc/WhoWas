package com.mdc.whowas;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Main on 3/14/16.
 */
public class JSONUtility {
    public static Object getJsonObject(String url) throws IOException, ParseException{
        URL url2 = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        return  obj;
    }
}
