package com.example.jengtallis.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by alog1024 on 8/11/16.
 */

@ParseClassName("Drink")

public class Drink extends ParseObject implements Parcelable {
    static final String NAME_COL = "name";
    static final String MPRICE_COL = "mPrice";
    static final String LPRICE_COL = "lPrice";
    static final String IMAGE_COL = "image";
//    int imageId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(getObjectId() == null){
            dest.writeInt(0);
            dest.writeString(this.getName());
            dest.writeInt(this.getlPrice());
            dest.writeInt(this.getmPrice());
//            dest.writeInt(this.imageId);
        }else{
            dest.writeInt(1);
            dest.writeString(getObjectId());
        }

    }

    public Drink() {
        super();
    }

    protected Drink(Parcel in) {
        this.setName(in.readString());
        this.setlPrice(in.readInt());
        this.setmPrice(in.readInt());
//        this.imageId = in.readInt();
    }

    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel source) {
            int isFromremote = source.readInt();
            if(isFromremote == 0){
                return new Drink(source);
            }else{
                String objectId = source.readString();
                return getDrinkFromCache(objectId);
            }
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public String getName() {
        return getString(NAME_COL);
    }

    public void setName(String name) {
        this.put(NAME_COL, name);
    }

    public int getmPrice() {
        return getInt(MPRICE_COL);
    }

    public void setmPrice(int mPrice) {
        this.put(MPRICE_COL, mPrice);
    }

    public int getlPrice() {
        return getInt(LPRICE_COL);
    }

    public void setlPrice(int lPrice) {
        this.put(LPRICE_COL, lPrice);
    }

    public ParseFile getImage(){return getParseFile(IMAGE_COL);}



    public static ParseQuery<Drink> getQuery(){
        return ParseQuery.getQuery(Drink.class);
    }

    public static Drink getDrinkFromCache(String objectId){
        try {
//            Drink drink = getQuery().setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK).get(objectId);
            Drink drink = getQuery().fromLocalDatastore().get(objectId);
            return drink;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Drink.createWithoutData(Drink.class, objectId);
    }

    public static void getDrinksFromLocalThenRemote(final FindCallback<Drink> callback){
        getQuery().fromLocalDatastore().findInBackground(callback);
        getQuery().findInBackground(new FindCallback<Drink>() {
            @Override
            public void done(final List<Drink> list, ParseException e) {
                if (e == null) {
                    unpinAllInBackground("Drink", new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            pinAllInBackground("Drink", list);
                        }
                    });
                }
                callback.done(list, e);
            }
        });
    }

}
