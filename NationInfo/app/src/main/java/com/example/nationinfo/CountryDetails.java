package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class CountryDetails extends AppCompatActivity {
    ImageView flagDetail, mapDetail;
    TextView nameDetail, populationDetail, areaDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        Intent i = getIntent();
        flagDetail = findViewById(R.id.flagDetail);
        mapDetail = findViewById(R.id.mapDetail);
        nameDetail = findViewById(R.id.nameDetail);
        populationDetail = findViewById(R.id.populationDetail);
        areaDetail = findViewById(R.id.areaDetail);
        Glide.with(this).load(i.getStringExtra("flag")).into(flagDetail);
        Glide.with(this).load(i.getStringExtra("map")).into(mapDetail);
        DecimalFormat formatInt = new DecimalFormat("###,###,###");
        DecimalFormat formatFloat = new DecimalFormat("###,###,###.0");
        nameDetail.setText(i.getStringExtra("name"));
        populationDetail.setText(formatInt.format(Integer.parseInt(i.getStringExtra("population"))) + " người");
        areaDetail.setText(formatFloat.format(Float.parseFloat(i.getStringExtra("area"))) + " km2");
    }
}