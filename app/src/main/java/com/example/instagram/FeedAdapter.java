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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class FeedAdapter extends BaseAdapter {
    public List<Postare> postari;
    private Context context;

    public FeedAdapter(List<Postare> postari, Context context) {
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

        ImageButton op= itemView.findViewById(R.id.optionsButton);
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "caca", Toast.LENGTH_SHORT).show();
            }
        });
        PopupMenu popupMenu = new PopupMenu(context.getApplicationContext(), itemView);
        popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
        return itemView;

    }

    public void update(List<Postare> postariNoi)
    {

        this.postari.addAll(postariNoi);
        Collections.reverse(postari);
        notifyDataSetChanged();
    }

}

