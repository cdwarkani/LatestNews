package com.xpayback.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.Timer;

public class NewsDetail extends AppCompatActivity {

    private TextView title;
    private ImageView imageurl;
    private TextView content;
    private TextView pubat;
    private String titles;
    private String imageurls;
    private String contents;
    private String pubats;
    private String authors;
    private String urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (Exception e){

        }
        setContentView(R.layout.activity_news_detail);
        title=findViewById(R.id.title);
        content=findViewById(R.id.desc);
        imageurl=findViewById(R.id.imageView);
        imageurl.setVisibility(View.VISIBLE);
        pubat=findViewById(R.id.pubat);
        Intent intent = getIntent();
        titles = intent.getStringExtra("title");
        contents = intent.getStringExtra("content");
        urls = intent.getStringExtra("url");
        authors = intent.getStringExtra("author");
        pubats = intent.getStringExtra("pubat");
        imageurls = intent.getStringExtra("image");
        title.setText(titles);
        content.setText(contents);
        if(!authors.equals("null") && !pubats.equals(null))
        pubat.setText("Written by "+ authors+" / Time: "+pubats);
        else if(!authors.equals("null"))
            pubat.setText("Written by "+ authors);
        else
            pubat.setText("Time: "+pubats);
        if(!imageurls.equals("null"))
            Picasso.get().load(imageurls).fit().centerInside().into(imageurl);
        else
            imageurl.setVisibility(View.GONE);

    }

    public void read(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls));
        startActivity(browserIntent);
    }
}
