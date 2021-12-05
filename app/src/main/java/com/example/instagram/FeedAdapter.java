package com.example.instagram;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class FeedAdapter extends BaseAdapter {
    private List<Post> postari;
    private Context context;

    public FeedAdapter(List<Post> postari, Context context) {
        this.postari = postari;
        this.context = context;
    }

    @Override
    public int getCount() {
        return postari.size();
    }

    @Override
    public Object getItem(int position) {
        return postari.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item,parent,false);
        ImageView image = (ImageView) itemView.findViewById(R.id.imageView);
        TextView tv_user = (TextView) itemView.findViewById(R.id.userName_post);
        TextView tv_desc = (TextView) itemView.findViewById(R.id.phDescp);

        String url = postari.get(position).getUrl();
        Picasso.get().load(url).into(image);

        String user =postari.get(position).getUser();
        String desc = postari.get(position).getDescription();
        tv_user.setText(user);
        String str  = user+" "+desc;
        Spannable spannable =  new SpannableString(str);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),0,user.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_desc.setText(spannable);
        return itemView;
    }

    public void update(List<Post> postariNoi)
    {
        this.postari.addAll(postariNoi);
        notifyDataSetChanged();
    }

}

