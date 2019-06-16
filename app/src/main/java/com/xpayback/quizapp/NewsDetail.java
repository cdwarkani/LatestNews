package com.xpayback.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
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
    private String TAG="newsdetaiul";
    private String wrby="Written by ";
    private String time="/ Time: ";
    private String timen="Time: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            if(getSupportActionBar() != null) {
                this.getSupportActionBar().hide();
            }
        }
        catch (NullPointerException e){
            Log.d(TAG,""+e);
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
        if(!pubats.equals("null"))
        {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.US);
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy' | 'HH:mm:ss",Locale.US);
                Date date = inputFormat.parse(pubats);
                pubats = outputFormat.format(date);
            }catch (Exception e)
            {
                Log.d(TAG,""+e); // prints 10-04-20
            }
        }
        String published;
        if(!authors.equals("null") && !pubats.equals("null")) {
            published=wrby + authors + time + pubats;
            pubat.setText(published);
        }
        else if(!authors.equals("null")) {
            published=wrby + authors;
        }
        else {
            published=time + pubats;

        }
        pubat.setText(published);

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
