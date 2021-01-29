package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String urlForecast = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String urlCurrent = "https://api.openweathermap.org/data/2.5/weather";
    private static final String key = "f3dbc42cf2a8636fdc9d4f58d6627a4b";
    private static final String charset = "UTF-8";

    public static void main(String[] arge){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your city: (default: Rexburg)");
        String city = scanner.nextLine();

        if (city.trim().equals("") || city ==null){
            city = "Rexburg";
        }
        try {
            String queryCurrent = String.format("q=%s&apiKey=%s",
                    URLEncoder.encode(city,charset),
                    URLEncoder.encode(key,charset));

            URLConnection connection = new URL(urlCurrent+"?"+queryCurrent).openConnection();
            connection.setRequestProperty("Accept-Charset",charset);
            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));

            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            Gson gson = new Gson();
            WeatherConditions weatherConditions = gson.fromJson(stringBuilder.toString(),WeatherConditions.class);
            //print out the information in WeatherConditions
            System.out.println("Weather Condition");
            System.out.println("id: "+weatherConditions.getId());
            System.out.println("name: "+weatherConditions.getName());
            System.out.println("measures:");
            for(Map.Entry<String, Float> entry: weatherConditions.getMeasurements().entrySet()){
                System.out.println(entry.getKey()+": "+entry.getValue());
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            String queryForecast = String.format("q=%s&appid=%s",
                    URLEncoder.encode(city,charset),
                    URLEncoder.encode(key,charset));

            URLConnection connection = new URL(urlCurrent+"?"+queryForecast).openConnection();
            connection.setRequestProperty("Accept-Charset",charset);
            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));

            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }

            Gson gson = new Gson();
            WeatherForecast forecast = gson.fromJson(stringBuilder.toString(), WeatherForecast.class);

            for (WeatherForecastItem item: forecast.getForecastItems()){
                System.out.println("date: "+item.getDateText());
                for(Weather w: item.getWeather()){
                    System.out.println("id: "+w.getId());
                    System.out.println("weather: "+w.getWeather());
                    System.out.println("description: "+w.getDescription());
                }
                for(Map.Entry<String, Float> entry: item.getMeasurements().entrySet()){
                    System.out.println(entry.getKey()+": "+entry.getValue());
                }
                for(Map.Entry<String, Float> entry: item.getWind().entrySet()){
                    System.out.println(entry.getKey()+": "+entry.getValue());
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        //deserializeCurrent(city);
        //deserializeForecast(city);
    }
    /*
    public static void deserializeCurrent(String city){


        try {
            URLConnection connection = new URL(url+"?"+query).openConnection();
            connection.setRequestProperty("Accept-Charset",charset.name());
            InputStream response = connection.getInputStream();
            try(Scanner resScanner = new Scanner(response)){
                String responseBody = resScanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
                Gson gson = new Gson();
                WeatherConditions weatherConditions = gson.fromJson(responseBody, WeatherConditions.class);
                // Testing deserialization
                System.out.println(weatherConditions.id);
                System.out.println(weatherConditions.name);
                for (Map.Entry<String,Float> entry: weatherConditions.measurements.entrySet()){
                    System.out.println(entry.getKey() + " : "+entry.getValue());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deserializeForecast(String city){

        Charset charset = StandardCharsets.UTF_8;
        String q = city;
        String appid = "f3dbc42cf2a8636fdc9d4f58d6627a4b";
        String query = String.format("q=%s&appid=%s",
                URLEncoder.encode(q,charset),
                URLEncoder.encode(appid,charset));
        try {
            URLConnection connection = new URL(url+"?"+query).openConnection();
            connection.setRequestProperty("Accept-Charset",charset.name());
            InputStream response = connection.getInputStream();
            try(Scanner resScanner = new Scanner(response)){
                String responseBody = resScanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
                Gson gson = new Gson();
                //WeatherForecastItem weatherForecastItem = gson.fromJson(responseBody,WeatherForecastItem.class);
                WeatherForecast weatherForecast = gson.fromJson(responseBody, WeatherForecast.class);
                // Testing deserialization
                System.out.println("WeatherForecastItem");

                System.out.println(weatherForecastItem.dt_txt);
                System.out.println("main");
                for(Map.Entry<String,Float> entry: weatherForecastItem.measurements.entrySet()){
                    System.out.println(entry.getKey()+ " : "+entry.getValue());
                }
                System.out.println("weather");
                for(Map.Entry<String,String> entry: weatherForecastItem.weather.entrySet()){
                    System.out.println(entry.getKey()+" : "+entry.getValue());
                }
                System.out.println("wind");
                for(Map.Entry<String,Float> entry: weatherForecastItem.wind.entrySet()){
                    System.out.println(entry.getKey()+" : "+entry.getValue());
                }

                System.out.println("WeatherForecast");
                for(Map.Entry<String, String> entry: weatherForecast.city.entrySet()){
                    if (entry.getKey().equals("id")){
                        System.out.println("id: "+entry.getValue());
                    }
                    if (entry.getKey().equals("name")){
                        System.out.println("name: "+entry.getValue());
                    }
                }

                for(WeatherForecastItem w : weatherForecast.measurements){
                    System.out.println(w.dt_txt);
                    System.out.println("main");
                    for(Map.Entry<String,Float> entry: w.measurements.entrySet()){
                        System.out.println(entry.getKey()+ " : "+entry.getValue());
                    }
                    System.out.println("weather");
                    for(Map.Entry<String,String> entry: w.weather.entrySet()){
                        System.out.println(entry.getKey()+" : "+entry.getValue());
                    }
                    System.out.println("wind");
                    for(Map.Entry<String,Float> entry: w.wind.entrySet()){
                        System.out.println(entry.getKey()+" : "+entry.getValue());
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
