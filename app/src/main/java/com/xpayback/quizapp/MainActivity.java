package com.xpayback.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private NewsAdapter eventAdapter;
    private String TAG="MainActivity";
    private ArrayList<String> Title = new ArrayList<>();
    private ArrayList<String> ImageUrl = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> content = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();
    private ArrayList<String> publishedAt = new ArrayList<>();
    private String maxLimit="20";
    private int pageNumber=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        eventAdapter = new NewsAdapter(Title,ImageUrl,description,content,url,publishedAt);
        recyclerView.setAdapter(eventAdapter);
        setNewsFeedContent("20",pageNumber);
        eventAdapter.setOnBottomReachedListener(new NewsAdapter.OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                //your code goes here
                pageNumber=pageNumber+1;
                Log.d(TAG,"num: "+pageNumber);
                if(pageNumber<Integer.parseInt(maxLimit)/20) {
                    Toast.makeText(MainActivity.this, "Loading more..", Toast.LENGTH_SHORT).show();
                    setNewsFeedContent("20",pageNumber);
                }else
                {
                    Toast.makeText(MainActivity.this, "Thats all folks", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setNewsFeedContent(String pageSize,int page) {
        AndroidNetworking.get("https://newsapi.org/v2/everything?q=business&sortBy=publishedAt&apiKey=76985723947c4d4f841bfe548a418640")
                .addQueryParameter("pageSize", pageSize)
                .addQueryParameter("page", String.valueOf(page))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG,"data ::"+response);

                            if(response.get("status").equals("ok"))
                            {
                                maxLimit= String.valueOf(response.get("totalResults"));
                                JSONArray articles= (JSONArray) response.get("articles");

                                for(int i=0;i<articles.length();i++)
                                {
                                    JSONObject initObject= (JSONObject) articles.get(i);
                                    Title.add((String) initObject.get("title"));
                                    ImageUrl.add(String.valueOf(initObject.get("urlToImage")));
                                    description.add(String.valueOf(initObject.get("description")));
                                    content.add(String.valueOf(initObject.get("content")));
                                    publishedAt.add(String.valueOf(initObject.get("publishedAt")));
                                    url.add(String.valueOf(initObject.get("url")));
                                }
                                eventAdapter.notifyDataSetChanged();

                            }else if(response.get("status").equals("error") && response.get("code").equals("maximumResultsReached"))
                            {
                                Toast.makeText(MainActivity.this, "NEWS API free account limit is restricted to 100 results", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG,"error");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Free account limit from NEWSAPI. 100 results are max limit for free account result search", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
