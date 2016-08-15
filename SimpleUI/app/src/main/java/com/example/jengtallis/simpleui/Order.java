package com.example.jengtallis.simpleui;

import java.util.List;

/**
 * Created by alog1024 on 8/10/16.
 */
public class Order {
    String note;
    String storeInfo;
    List<DrinkOrder> drinkOrderList;

    public int getTotal(){
        int total = 0;
        for(DrinkOrder drinkOrder: drinkOrderList){
            total += drinkOrder.mNumber * drinkOrder.drink.mPrice + drinkOrder.lNumber * drinkOrder.drink.lPrice;
        }
        return total;
    }

}
