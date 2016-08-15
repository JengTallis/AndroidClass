package com.example.jengtallis.simpleui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    RadioButton blackTeaRadioBtn;
    RadioButton greenTeaRadioBtn;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea";

    ArrayList<DrinkOrder> drinkOrderList = new ArrayList<>();
    List<Order> data = new ArrayList<Order>();

    SharedPreferences sharedPreferences;
//    in xml file, not suitable for large data as it includes many tags
//    user info, app setting, ui status
    SharedPreferences.Editor editor;

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

        sharedPreferences = getSharedPreferences("UIState", MODE_PRIVATE);
//        create a xml file called UIState
        editor = sharedPreferences.edit();
//        grant editor the right to edit

        editText.setText(sharedPreferences.getString("editText", ""));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.putString("editText", editText.getText().toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, order.note, Toast.LENGTH_LONG).show();
            }
        });

        setupListView();
        setupSpinner();

        Log.d("DEBUG", "MainActivity OnCreate");

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

        spinner.setSelection(sharedPreferences.getInt("spinner", 0));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("spinner", spinner.getSelectedItemPosition());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void click(View view){
        String name = editText.getText().toString();
        String str = name + "'s order: " + drink;
        textView.setText(str);
        editText.setText("");

        Order order = new Order();

        order.note = name;
        order.drinkOrderList = drinkOrderList;
        order.storeInfo = (String)spinner.getSelectedItem();

        data.add(order);

        drinkOrderList = new ArrayList<>();
        setupListView();
    }

    public void goToMenu(View view){
        Intent intent = new Intent();
        intent.putExtra("result", drinkOrderList);
        intent.setClass(this, DrinkMenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY){
            if(resultCode == RESULT_OK){
                drinkOrderList = data.getParcelableArrayListExtra("result");
//                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Order canceled", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DEBUG", "MainActivity OnStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DEBUG", "MainActivity OnResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DEBUG", "MainActivity OnPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("DEBUG", "MainActivity OnRestart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DEBUG", "MainActivity OnStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DEBUG", "MainActivity OnDestroy");

    }
}
