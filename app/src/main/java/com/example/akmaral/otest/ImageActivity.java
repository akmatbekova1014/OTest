package com.example.akmaral.otest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String photo_url = getIntent().getStringExtra("photo_url");
        ImageView imageView = (ImageView) findViewById(R.id.img);
        Picasso.with(this).load(photo_url).into(imageView);
    }
}
