package com.example.jengtallis.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioButton blackTeaRadioBtn;
    RadioButton greenTeaRadioBtn;
    RadioGroup radioGroup;
    ListView listView;

    String drink = "Black Tea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        editText = (EditText) findViewById(R.id.editText);

        blackTeaRadioBtn = (RadioButton) findViewById(R.id.blackTeaRadioBtn);
        greenTeaRadioBtn = (RadioButton) findViewById(R.id.greenTeaRadioBtn);

        listView = (ListView) findViewById(R.id.listView);

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

    }

    private void setupListView(){
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    public void click(View view){
        String name = editText.getText().toString();
        textView.setText(name + "'s order: " + drink);
        editText.setText("");
    }
}
