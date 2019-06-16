package com.xpayback.quizapp;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    /*
    Developed by Dwarsoft | www.dwarsoft.com
    Contact: Varun Dwarkani - 9710187580 | 6380111730
     */
    private ArrayList<String> Title = new ArrayList<>();
    private ArrayList<String> ImageUrl = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> content = new ArrayList<>();
    private ArrayList<String> url = new ArrayList<>();
    private ArrayList<String> publishedAt = new ArrayList<>();
    OnBottomReachedListener onBottomReachedListener;
private String TAG="newsadapter";
    NewsAdapter(ArrayList<String> Title,ArrayList<String> ImageUrl,ArrayList<String> description,ArrayList<String> content,ArrayList<String> url,ArrayList<String> publishedAt){
        this.Title = Title;
        this.ImageUrl=ImageUrl;
        this.description=description;
        this.content=content;
        this.url=url;
        this.publishedAt=publishedAt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsrow, parent, false);
        return new ViewHolder(view);
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

       // holder.tvEvent.setText(eventName_adapter.get(position));
        //Picasso.get().load(eventImage_adapter.get(position)).into(holder.ivEvent);
        holder.tvEvent.setText(Title.get(position));
        holder.descrip.setText(description.get(position));
        Log.d(TAG,"imageiurl"+ImageUrl.get(position));
    if(ImageUrl.get(position)!=null)
    Picasso.get().load(ImageUrl.get(position)).into(holder.imageEvent);

        if (position == Title.size() - 1){
            onBottomReachedListener.onBottomReached(position);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsdetail=new Intent(v.getContext(),NewsDetail.class);
                newsdetail.putExtra("title",Title.get(position));
                newsdetail.putExtra("image",ImageUrl.get(position));
                newsdetail.putExtra("url",url.get(position));
                newsdetail.putExtra("content",content.get(position));
                newsdetail.putExtra("pubat",publishedAt.get(position));
                v.getContext().startActivity(newsdetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Title.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvEvent;
        final ImageView imageEvent;
        final TextView descrip;

        ViewHolder(View itemView) {
            super(itemView);
            tvEvent = itemView.findViewById(R.id.info_text);
            imageEvent=itemView.findViewById(R.id.ivEvent);
            descrip=itemView.findViewById(R.id.info_text_Desc);
        }
    }
    public interface OnBottomReachedListener {

        void onBottomReached(int position);

    }
}
