package com.example.jengtallis.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView;
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hello textView!");
    }

    public void click(View view){
        textView.setText("view clicked!");
    }
}
