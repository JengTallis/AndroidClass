package com.example.jengtallis.simpleui;

/**
 * Created by alog1024 on 8/15/16.
 */
public class DrinkOrder {
    Drink drink;
    int lNumber = 0;
    int mNumber = 0;
    String ice = "REGULAR";
    String sugar = "REGULAR";
    String note = "";

    public DrinkOrder(Drink drink){
        this.drink = drink;
    }
}
