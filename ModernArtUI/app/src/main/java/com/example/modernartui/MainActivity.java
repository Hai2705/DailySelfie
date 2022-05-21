package com.example.modernartui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Toolbar tb;
    RelativeLayout container;
    SeekBar seek;
    View view_one, view_two, view_three, view_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);
        seek = findViewById(R.id.seekbar);
        view_one = findViewById(R.id.blue_view);
        view_two = findViewById(R.id.pink_view);
        view_three = findViewById(R.id.brown_view);
        view_five = findViewById(R.id.green_view);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                view_one.setBackgroundColor(Color.rgb(19 + progress, 123 + progress, 172));
                view_two.setBackgroundColor(Color.rgb(179, 13 + progress, 143 + progress));
                view_three.setBackgroundColor(Color.rgb(170 - progress, 25, progress + 25));
                view_five.setBackgroundColor(Color.rgb(progress + 1, 135 - progress, 134));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_more_vert:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Inspired by the work of artist such as Leonardo Da Vinci and Van Gogh.");
                builder.setMessage("Click below to learn more");
                builder.setCancelable(true);
                builder.setPositiveButton("Visit MOMA",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moma.org/"));
                                startActivity(browserIntent);
                            }
                        });
                builder.setNegativeButton("Not Now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
        return true;
    }
}