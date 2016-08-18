package com.example.jengtallis.simpleui;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by alog1024 on 8/10/16.
 */

@ParseClassName("Order")

public class Order extends ParseObject{
//    String note;
//    String storeInfo;
//    List<DrinkOrder> drinkOrderList;

    static final String NOTE_COL = "note";
    static final String STOREINFO_COL = "storeInfo";
    static final String DRINKORDERLIST_COL = "note";


    public String getNote() {
        return getString(NOTE_COL);
    }

    public void setNote(String note) {
        put(NOTE_COL, note);
    }

    public List<DrinkOrder> getDrinkOrderList() {
        return getList(DRINKORDERLIST_COL);
    }

    public void setDrinkOrderList(List<DrinkOrder> drinkOrderList) {
        put(DRINKORDERLIST_COL, drinkOrderList);
    }

    public String getStoreInfo() {
        return getString(STOREINFO_COL);
    }

    public void setStoreInfo(String storeInfo) {
        put(STOREINFO_COL, storeInfo);
    }

    public int getTotal(){
        int total = 0;
        for(DrinkOrder drinkOrder: getDrinkOrderList()){
            total += drinkOrder.getmNumber() * drinkOrder.getDrink().getmPrice() + drinkOrder.getlNumber() * drinkOrder.getDrink().getlPrice();
        }
        return total;
    }

    public static ParseQuery<Order> getQuery(){

        return ParseQuery.getQuery(Order.class)
                .include(DRINKORDERLIST_COL)
                .include(DRINKORDERLIST_COL + '.' + DrinkOrder.DRINK_COL);
    }

    public static void getOrderFromLocalThenRemote(final FindCallback<Order> callback){
        getQuery().fromLocalDatastore().findInBackground(callback);
        getQuery().findInBackground(new FindCallback<Order>() {
            @Override
            public void done(final List<Order> list, ParseException e) {
                if (e == null) {
                    pinAllInBackground("Order", list);
                }
                callback.done(list, e);
            }
        });
    }

}
