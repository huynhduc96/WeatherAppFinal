package com.example.huynh.weatherappfinal.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huynh.weatherappfinal.Json.JsonModel;
import com.example.huynh.weatherappfinal.MainActivity;
import com.example.huynh.weatherappfinal.R;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huynh on 11-Aug-16.
 */
public class fragment_nghean extends Fragment {
    private TextView main;
    private TextView description;
    private TextView temp;
    private TextView speed;
    private SharedPreferences dataNghean ;

    private SharedPreferences sharedPreferences_nghean;
    private SharedPreferences.Editor editor_nghean;

    private static final String API_KEY = "2114608e4a00b5b5dfae2ac2fe526419";
    private static final String API = "http://api.openweathermap.org/data/2.5/weather";

    public static final String KEY_VALUE_MAIN = "main";
    public static final String KEY_VALUE_DES = "description";
    public static final String KEY_VALUE_TEMP = "temp";
    public static final String KEY_VALUE_SPEED = "speed";
    public static final String NAME_NGHEAN = "HuynhDuc_nghean";


    public fragment_nghean(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class DownloadJSon extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;
            String result = "";

            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(inputString);

                    }
                    result = stringBuilder.toString();
                    httpURLConnection.disconnect();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("DMM", "chostung");
//            Toast.makeText(MainActivity.this, "dis cụ - nó vào rồi!", Toast.LENGTH_LONG).show();


            Log.d("IMM", s + "");

            if (isConneted()) {
                JsonModel jsonModel = (new Gson()).fromJson(s, JsonModel.class);


                String main = String.valueOf(jsonModel.getJsonWeathers().get(0).getMain());
                String description = String.valueOf(jsonModel.getJsonWeathers().get(0).getDescription());
                String temp = String.valueOf(jsonModel.getJsonMain().getTemp());
                String speed = String.valueOf(jsonModel.getJsonWind().getSpeed());

                editor_nghean.putString(KEY_VALUE_MAIN, main);
                editor_nghean.putString(KEY_VALUE_DES, description);
                editor_nghean.putString(KEY_VALUE_TEMP, temp);
                editor_nghean.putString(KEY_VALUE_SPEED, speed);

                editor_nghean.commit();


            }

        }
    }

    private boolean isConneted() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nghean_fragment,container,false);
        main = (TextView) view.findViewById(R.id.main);
        description = (TextView) view.findViewById(R.id.description);
        temp = (TextView) view.findViewById(R.id.temp);
        speed = (TextView) view.findViewById(R.id.speed);

        if(isConneted()){
            //download Json
            DownloadJSon downloadJSon = new DownloadJSon();
            downloadJSon.execute(API + "?q=Nghean&&APPID=" + API_KEY);
            //save in SharePreferences
            sharedPreferences_nghean = this.getActivity().getSharedPreferences(NAME_NGHEAN,  Context.MODE_PRIVATE);
            editor_nghean = sharedPreferences_nghean.edit();
        }






        dataNghean = this.getActivity().getSharedPreferences(NAME_NGHEAN, Context.MODE_PRIVATE);

        String main_s = dataNghean.getString(MainActivity.KEY_VALUE_MAIN,"");
        String description_s = dataNghean.getString(MainActivity.KEY_VALUE_DES,"");
        String temp_s = dataNghean.getString(MainActivity.KEY_VALUE_TEMP,"");
        String speed_s =dataNghean.getString(MainActivity.KEY_VALUE_SPEED,"");




        main.setText(main_s);
        description.setText(description_s);
        temp.setText(temp_s + " C");
        speed.setText(speed_s +" m/s");

        return view;
    }
}
