package com.xpayback.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {

    private TextView title;
    private ImageView imageurl;
    private TextView content;
    private TextView pubat;
    private String titles;
    private String imageurls;
    private String contents;
    private String pubats;
    private String urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=findViewById(R.id.title);
        content=findViewById(R.id.desc);
        imageurl=findViewById(R.id.imageView);
        pubat=findViewById(R.id.pubat);
        Intent intent = getIntent();
        titles = intent.getStringExtra("title");
        contents = intent.getStringExtra("content");
        urls = intent.getStringExtra("url");
        pubats = intent.getStringExtra("pubat");
        imageurls = intent.getStringExtra("image");
        title.setText(titles);
        content.setText(contents);
        pubat.setText(pubats);
        Picasso.get().load(imageurls).into(imageurl);
    }
    private void readfull()
    {

    }
}
