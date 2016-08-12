package com.example.huynh.weatherappfinal;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.huynh.weatherappfinal.Json.JsonModel;
import com.example.huynh.weatherappfinal.fragment.fragment_hanoi;
import com.example.huynh.weatherappfinal.fragment.fragment_nghean;
import com.example.huynh.weatherappfinal.fragment.fragment_tphcm;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private SharedPreferences sharedPreferences_hanoi;
    private SharedPreferences.Editor editor_hanoi;

    private static final String API_KEY = "2114608e4a00b5b5dfae2ac2fe526419";
    private static final String API = "http://api.openweathermap.org/data/2.5/weather";

    public static final String KEY_VALUE_MAIN = "main";
    public static final String KEY_VALUE_DES = "description";
    public static final String KEY_VALUE_TEMP = "temp";
    public static final String KEY_VALUE_SPEED = "speed";
    public static final String NAME_HANOI = "HuynhDuc_hanoi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
//        sliderLayout.stopAutoCycle();

        addSlideImage();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        if(isConneted()){
            Toast.makeText(MainActivity.this,"internet access -downloading data",Toast.LENGTH_SHORT).show();
//            //download Json
//            DownloadJSon downloadJSon = new DownloadJSon();
//            downloadJSon.execute(API + "?q=Hanoi&&APPID=" + API_KEY);
//            //save in SharePreferences
//            sharedPreferences_hanoi = getSharedPreferences(NAME_HANOI, MODE_PRIVATE);
//            editor_hanoi = sharedPreferences_hanoi.edit();

        }else {

            Toast.makeText(MainActivity.this,"internet not available - connecting to download new data",Toast.LENGTH_LONG).show();
            Log.d("inter","dmm");
        }




    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new fragment_hanoi(), "Hà Nội");
        viewPagerAdapter.addFragment(new fragment_nghean(), "Nghệ An");
        viewPagerAdapter.addFragment(new fragment_tphcm(), "TP Hồ Chí Minh");
        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void addSlideImage() {
        TextSliderView haNoi = new TextSliderView(this);
        TextSliderView ngheAn = new TextSliderView(this);
        TextSliderView tpHcm = new TextSliderView(this);

        haNoi.description("Hà Nội")
                .image(R.drawable.hanoi);
        ngheAn.description("Nghệ An")
                .image(R.drawable.nghean);
        tpHcm.description("Thành Phố Hồ Chí Minh")
                .image(R.drawable.tphcm);

        sliderLayout.addSlider(haNoi);
        sliderLayout.addSlider(ngheAn);
        sliderLayout.addSlider(tpHcm);
    }

//    public class DownloadJSon extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            InputStream inputStream;
//            HttpURLConnection httpURLConnection;
//            String result = "";
//
//            try {
//                URL url = new URL(strings[0]);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String inputString;
//                    while ((inputString = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(inputString);
//
//                    }
//                    result = stringBuilder.toString();
//                    httpURLConnection.disconnect();
//                }
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//            return result;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Log.d("DMM", "chostung");
////            Toast.makeText(MainActivity.this, "dis cụ - nó vào rồi!", Toast.LENGTH_LONG).show();
//
//
//            Log.d("IMM", s + "");
//
//            if (isConneted()) {
//                JsonModel jsonModel = (new Gson()).fromJson(s, JsonModel.class);
//
//
//                String main = String.valueOf(jsonModel.getJsonWeathers().get(0).getMain());
//                String description = String.valueOf(jsonModel.getJsonWeathers().get(0).getDescription());
//                String temp = String.valueOf(jsonModel.getJsonMain().getTemp());
//                String speed = String.valueOf(jsonModel.getJsonWind().getSpeed());
//
//                editor_hanoi.putString(KEY_VALUE_MAIN, main);
//                editor_hanoi.putString(KEY_VALUE_DES, description);
//                editor_hanoi.putString(KEY_VALUE_TEMP, temp);
//                editor_hanoi.putString(KEY_VALUE_SPEED, speed);
//
//                editor_hanoi.commit();
//
//
//            }
//
//        }
//    }

    private boolean isConneted() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


}
