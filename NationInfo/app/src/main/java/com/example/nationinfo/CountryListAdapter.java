package com.example.nationinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryListAdapter extends ArrayAdapter<Country> {
    List<Country> countries;
    Context context;
    Map<String, Bitmap> mBitmaps = new HashMap<>();

    public CountryListAdapter(Context context, List<Country> countries) {
        super(context, 0, countries);
        this.countries = countries;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.countryitem, parent, false);
        Country c = countries.get(position);
        ImageView flagItem = v.findViewById(R.id.flagItem);
        TextView nameItem = v.findViewById(R.id.nameItem);
        nameItem.setText(c.getName());
        Glide.with(context).load(c.getFlag()).into(flagItem);
        return v;
    }

}