package com.example.jengtallis.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioButton blackTeaRadioBtn;
    RadioButton greenTeaRadioBtn;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea";

    List<Order> data = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        editText = (EditText) findViewById(R.id.editText);

        blackTeaRadioBtn = (RadioButton) findViewById(R.id.blackTeaRadioBtn);
        greenTeaRadioBtn = (RadioButton) findViewById(R.id.greenTeaRadioBtn);

        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.blackTeaRadioBtn){
                    drink = "Black Tea";
                }else if(checkedId == R.id.greenTeaRadioBtn){
                    drink = "Green Tea";
                }
            }
        });

        setupListView();
        setupSpinner();

    }

    private void setupListView(){
//        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9"};
//        List<Map<String, String>>mapList = new ArrayList<>();
//        for(Order order: data){
//            Map<String, String> item = new HashMap<>();
//
//            item.put("note", order.note);
//            item.put("storeInfo", order.storeInfo);
//            item.put("drink", order.drink);
//
//            mapList.add(item);
//        }
//
//        String[] from = {"note", "storeInfo", "drink"};
//        int[] to = {R.id.noteTextView, R.id.storeInfoTextView, R.id.drinkTextView};
//
//        SimpleAdapter adapter = new SimpleAdapter(this, mapList, R.layout.listview_order_item, from, to);

          OrderAdapter adapter = new OrderAdapter(this, data);

          listView.setAdapter(adapter);
    }

    private void setupSpinner(){
        String[] storeInfo = getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, storeInfo);
        spinner.setAdapter(adapter);
    }

    public void click(View view){
        String name = editText.getText().toString();
        String str = name + "'s order: " + drink;
        textView.setText(str);
        editText.setText("");

        Order order = new Order();

        order.note = name;
        order.drink = drink;
        order.storeInfo = (String)spinner.getSelectedItem();

        data.add(order);
        setupListView();
    }

}
