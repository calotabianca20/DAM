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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

public class FeedAdapter extends BaseAdapter {
    private int[] images;
    private String[] users;
    private String[] photoDescriptions;
    private Context context;
    public FeedAdapter(Context context, int[] images, String[] users, String[] photoDescriptions) {
        this.context=context;
        this.images = images;
        this.users=users;
        this.photoDescriptions=photoDescriptions;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
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
        TextView user = (TextView) itemView.findViewById(R.id.userName_post);
        TextView desc = (TextView) itemView.findViewById(R.id.phDescp);
        image.setImageResource(images[position]);
        user.setText(users[position]);
        String str  = users[position]+" "+photoDescriptions[position];
        Spannable spannable =  new SpannableString(str);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),0,users[position].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        desc.setText(spannable);

        PopupMenu popupMenu = new PopupMenu(context.getApplicationContext(), itemView);
        popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
        return itemView;

    }



}
