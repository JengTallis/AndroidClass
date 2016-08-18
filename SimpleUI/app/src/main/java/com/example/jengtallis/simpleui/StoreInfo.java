package com.example.jengtallis.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by alog1024 on 8/18/16.
 */

@ParseClassName("StoreInfo")

public class StoreInfo extends ParseObject implements Parcelable {

    static final String NAME_COL = "name";
    static final String ADDRESS_COL = "address";

    public StoreInfo(){
        super();
    }

    public String getName() {
        return getString(NAME_COL);
    }

    public void setName(String name) {
        put(NAME_COL, name);
    }

    public String getAddress() {
        return getString(ADDRESS_COL);
    }

    public void setAddress(String address) {
        put(ADDRESS_COL, address);
    }

    public static ParseQuery<StoreInfo> getQuery() {
        return ParseQuery.getQuery(StoreInfo.class);
    }


    protected StoreInfo(Parcel in) {
        setName(in.readString());
        setAddress(in.readString());
    }

    public static final Creator<StoreInfo> CREATOR = new Creator<StoreInfo>() {
        @Override
        public StoreInfo createFromParcel(Parcel in) {
            return new StoreInfo(in);
        }

        @Override
        public StoreInfo[] newArray(int size) {
            return new StoreInfo[size];
        }
    };

    public static void getStoreInfoFromLocalThenRemote(final FindCallback<StoreInfo> callback) {
        getQuery().fromLocalDatastore().findInBackground(callback);
        getQuery().findInBackground(new FindCallback<StoreInfo>() {
            @Override
            public void done(final List<StoreInfo> list, ParseException e) {
                if (e == null) {
                    unpinAllInBackground("StoreInfo", new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            pinAllInBackground("StoreInfo", list);
                        }
                    });
                }
                callback.done(list, e);
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (getObjectId() == null) {
            dest.writeInt(0);
            dest.writeString(getName());
            dest.writeString(getAddress());
        } else {
            dest.writeInt(1);
            dest.writeString(getObjectId());
        }
    }

    public static StoreInfo getStoreInfoFromCache(String objectId){
        try {
            StoreInfo storeInfo = getQuery().fromLocalDatastore().get(objectId);
            return storeInfo;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StoreInfo.createWithoutData(StoreInfo.class, objectId);
    }

}
