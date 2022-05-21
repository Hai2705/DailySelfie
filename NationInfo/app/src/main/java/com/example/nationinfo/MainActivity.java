package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nationinfo.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String HTTP_REQUEST = "http://api.geonames.org/countryInfoJSON?username=vinhtien123";
    ListView countryList;
    List<Country> countries;
    Handler handler = new Handler();
    ProgressDialog progressDialog;
    ActivityMainBinding activityMainBinding;
    CountryListAdapter countryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        countryList = activityMainBinding.countryList;
        countries = new ArrayList<>();
        countryListAdapter = new CountryListAdapter(this, countries);
        countryList.setAdapter(countryListAdapter);
        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CountryDetails.class);
                Country c = countries.get(position);
                intent.putExtra("flag", c.getFlag());
                intent.putExtra("name", c.getName());
                intent.putExtra("map", c.getMap());
                intent.putExtra("population", c.getPopulation());
                intent.putExtra("area", c.getArea());
                startActivity(intent);
            }
        });
        new FetchData().start();
    }

    class FetchData extends Thread {
        String data = "";

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Đang lazy load");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });
            try {
                URL url = new URL(HTTP_REQUEST);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    data += line;
                }
                if (!data.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = (JSONArray) jsonObject.get("geonames");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject country = jsonArray.getJSONObject(i);
                        Country c = new Country(
                                "https://img.geonames.org/flags/x/" + country.getString("countryCode").toLowerCase() + ".gif",
                                country.getString("countryName"),
                                "https://img.geonames.org/img/country/250/" + country.getString("countryCode") + ".png",
                                country.getString("population"),
                                country.getString("areaInSqKm"));
                        countries.add(c);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    countryListAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}