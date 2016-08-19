package com.example.jengtallis.simpleui;

import android.content.Intent;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.logging.LogRecord;
import android.os.Handler;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OrderDetailActivity extends AppCompatActivity implements GeoCodingTask.GeoCodingCallback {

    TextView latlngTextView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
        TextView storeInfoTextView = (TextView)findViewById(R.id.storeInfoTextView);
        TextView drinkOrderResultsTextView = (TextView)findViewById(R.id.drinkOrderResultsTextView);
        latlngTextView = (TextView)findViewById(R.id.latLngTextView);

        final Intent intent = getIntent();
        Order order = intent.getParcelableExtra("order");

        noteTextView.setText(order.getStoreInfo());
        String addressLong = "台北市士林區";
//        addressLong = order.getNote();
//        String[] parts = addressLong.split(" ");
//        String address = parts[1];

        String resultText = "";
        for(DrinkOrder drinkOrder: order.getDrinkOrderList()){
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String drinkName = drinkOrder.getDrink().getName();
            resultText += drinkName + " M: " + mNumber + "  L: " + lNumber + "\n";
        }
        drinkOrderResultsTextView.setText(resultText);

        MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment);
        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                (new GeoCodingTask(OrderDetailActivity.this)).execute("台北市士林區");

            }
        });


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

    @Override
    public void done(double[] latlng) {
        if(latlng != null){
            String latlngString = String.valueOf(latlng[0] + ", " + latlng[1]);
            latlngTextView.setText(latlngString);

            LatLng latLng = new LatLng(latlng[1], latlng[0]);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("NTU");

            map.moveCamera(cameraUpdate);
            map.addMarker(markerOptions);
        }
    }
}
