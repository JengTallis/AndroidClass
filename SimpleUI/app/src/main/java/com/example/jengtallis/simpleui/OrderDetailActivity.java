package com.example.jengtallis.simpleui;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.logging.LogRecord;
import android.os.Handler;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
        TextView storeInfoTextView = (TextView)findViewById(R.id.storeInfoTextView);
        TextView drinkOrderResultsTextView = (TextView)findViewById(R.id.drinkOrderResultsTextView);
        final TextView latLngTextView = (TextView)findViewById(R.id.latLngTextView);

        final Intent intent = getIntent();
        Order order = intent.getParcelableExtra("order");

        noteTextView.setText(order.getStoreInfo());

        String resultText = "";
        for(DrinkOrder drinkOrder: order.getDrinkOrderList()){
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String drinkName = drinkOrder.getDrink().getName();
            resultText += drinkName + " M: " + mNumber + "  L: " + lNumber + "\n";
        }
        drinkOrderResultsTextView.setText(resultText);

        (new GeoCodingTask()).execute("");

        // Thread may lead to memory leak

//        final Handler handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
////                latLngTextView.setText("123,456");
//                return false;
//            }
//        });
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    handler.sendMessage(new Message());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();

    }
}
