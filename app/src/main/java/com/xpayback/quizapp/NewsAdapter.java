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

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
    private ArrayList<String> author = new ArrayList<>();
    OnBottomReachedListener onBottomReachedListener;
private String TAG="newsadapter";
    NewsAdapter(ArrayList<String> Title,ArrayList<String> ImageUrl,ArrayList<String> description,ArrayList<String> content,ArrayList<String> url,ArrayList<String> publishedAt,ArrayList<String> author){
        this.Title = Title;
        this.ImageUrl=ImageUrl;
        this.description=description;
        this.content=content;
        this.url=url;
        this.publishedAt=publishedAt;
        this.author=author;
    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        Log.d(TAG,"ImageUrl:"+ImageUrl.get(position));
        Log.d(TAG,"ImageUrl:"+ImageUrl.get(position));
        if(!ImageUrl.get(position).equals("null"))
        { return 0;}
        else
        {
            return 1;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsrow, parent, false);
                return new ViewHolder1(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsrow2, parent, false);
                return new ViewHolder2(view);
        }
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

       // holder.tvEvent.setText(eventName_adapter.get(position));
        //Picasso.get().load(eventImage_adapter.get(position)).into(holder.ivEvent);

        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder1 vholder = (ViewHolder1) holder;
                vholder.tvEvent.setText(Title.get(position));
                vholder.descrip.setText(description.get(position));
                Log.d(TAG,"imageiurl"+ImageUrl.get(position));
                if(ImageUrl.get(position)!=null)
                    Picasso.get().load(ImageUrl.get(position)).into(vholder.imageEvent);

                if (position == Title.size() - 1){
                    onBottomReachedListener.onBottomReached(position);

                }
                vholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newsdetail=new Intent(v.getContext(),NewsDetail.class);
                        newsdetail.putExtra("title",Title.get(position));
                        newsdetail.putExtra("image",ImageUrl.get(position));
                        newsdetail.putExtra("url",url.get(position));
                        newsdetail.putExtra("content",content.get(position));
                        newsdetail.putExtra("pubat",publishedAt.get(position));
                        newsdetail.putExtra("author",author.get(position));
                        v.getContext().startActivity(newsdetail);
                    }
                });
                break;

            case 1:
                ViewHolder2 vholder2 = (ViewHolder2)holder;
                vholder2.tvEvent.setText(Title.get(position));
                vholder2.descrip.setText(description.get(position));
                Log.d(TAG,"imageiurl"+ImageUrl.get(position));
                if (position == Title.size() - 1){
                    onBottomReachedListener.onBottomReached(position);
                }
                vholder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newsdetail=new Intent(v.getContext(),NewsDetail.class);
                        newsdetail.putExtra("title",Title.get(position));
                        newsdetail.putExtra("image",ImageUrl.get(position));
                        newsdetail.putExtra("url",url.get(position));
                        newsdetail.putExtra("content",content.get(position));
                        newsdetail.putExtra("pubat",publishedAt.get(position));
                        newsdetail.putExtra("author",author.get(position));
                        v.getContext().startActivity(newsdetail);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return Title.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        final TextView tvEvent;
        final ImageView imageEvent;
        final TextView descrip;

        ViewHolder1(View itemView) {
            super(itemView);
            tvEvent = itemView.findViewById(R.id.info_text);
            imageEvent=itemView.findViewById(R.id.ivEvent);
            descrip=itemView.findViewById(R.id.info_text_Desc);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        final TextView tvEvent;
        final TextView descrip;

        ViewHolder2(View itemView) {
            super(itemView);
            tvEvent = itemView.findViewById(R.id.info_text);
            descrip=itemView.findViewById(R.id.info_text_Desc);
        }
    }
    public interface OnBottomReachedListener {

        void onBottomReached(int position);

    }
}
