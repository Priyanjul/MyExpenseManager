package com.example.kapils.myexpensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by Priyanjul on 19-06-2017.
 */

public class PopDateSelect extends Activity {

    DatePicker from, to;
    int day1,month1, year1;
    int day2, month2, year2;
    String date1, date2;
    FloatingActionButton done;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_sd);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int)(height*0.8));

        from = (DatePicker)findViewById(R.id.dp_from);
        to = (DatePicker)findViewById(R.id.dp_to);
        done = (FloatingActionButton)findViewById(R.id.fab);

        from.init(from.getYear(), from.getMonth(), from.getDayOfMonth(), new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day1 = dayOfMonth;
                month1 = monthOfYear;
                year1 = year;
                date1 = Integer.toString(day1)+"-"+Integer.toString(month1)+"-"+Integer.toString(year1);
            }
        });

        to.init(to.getYear(), to.getMonth(), to.getDayOfMonth(), new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day2 = dayOfMonth;
                month2 = monthOfYear;
                year2 = year;
                date2 = Integer.toString(day2)+"-"+Integer.toString(month2)+"-"+Integer.toString(year2);
            }
        });

        //If date not changed

        day1 = from.getDayOfMonth();
        month1 = from.getMonth();
        year1 = from.getYear();
        date1 = Integer.toString(day1)+"-"+Integer.toString(month1)+"-"+Integer.toString(year1);

        day2 = to.getDayOfMonth();
        month2 = to.getMonth();
        year2 = to.getYear();
        date2 = Integer.toString(day2)+"-"+Integer.toString(month2)+"-"+Integer.toString(year2);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("DATE1",date1);
                i.putExtra("DATE2",date2);
                setResult(2,i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("DATE1",date1);
        i.putExtra("DATE2",date2);
        setResult(2,i);
        finish();
    }


}
