package com.example.jengtallis.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alog1024 on 8/11/16.
 */
public class DrinkAdapter extends BaseAdapter {

    List<Drink> drinks;
    LayoutInflater layoutInflater;

    public DrinkAdapter(Context context, List<Drink> drinkList){
        this.drinks = drinkList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_drink_item, null);

            holder = new Holder();

            holder.imageView =(ImageView)convertView.findViewById(R.id.imageView);;
            holder.drinkNameTextView = (TextView)convertView.findViewById(R.id.drinkNameTextView);
            holder.lPriceTextView = (TextView)convertView.findViewById(R.id.lPriceTextView);
            holder.mPriceTextView = (TextView)convertView.findViewById(R.id.mPriceTextView);

            convertView.setTag(holder);

        }else{
            holder = (Holder)convertView.getTag();
        }

        Drink drink = drinks.get(position);
        holder.drinkNameTextView.setText(drink.getName());
        holder.lPriceTextView.setText(String.valueOf(drink.getlPrice()));
        holder.mPriceTextView.setText(String.valueOf(drink.getmPrice()));
        String imageUrl = drink.getImage().getUrl();
        Picasso.with(layoutInflater.getContext()).load(imageUrl).into(holder.imageView);
//        holder.imageView.setImageResource(drink.imageId);

        return convertView;
    }

    class Holder{
        ImageView imageView;
        TextView drinkNameTextView;
        TextView lPriceTextView;
        TextView mPriceTextView;

    }
}
