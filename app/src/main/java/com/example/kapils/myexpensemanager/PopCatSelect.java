package com.example.kapils.myexpensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Priyanjul on 19-06-2017.
 */

public class PopCatSelect extends Activity {

    RadioButton btn1, btn2, btn3;
    String choice1, choice2, choice3;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_cat);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int)(height*0.3));

        btn1 = (RadioButton)findViewById(R.id.radioButton);
        btn2 = (RadioButton)findViewById(R.id.radioButton2);
        btn3 = (RadioButton)findViewById(R.id.radioButton3);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        if(btn1.isChecked())
            choice1 = btn1.getText().toString();
        if(btn2.isChecked())
            choice2 = btn2.getText().toString();
        if(btn3.isChecked())
            choice3 = btn3.getText().toString();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                i.putExtra("CHOICE1",choice1);
                i.putExtra("CHOICE2",choice2);
                i.putExtra("CHOICE3",choice3);
                setResult(3,i);
                finish();
            }
        });



    }
}
