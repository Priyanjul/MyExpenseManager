package com.example.kapils.myexpensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Reccuring_Fragment extends Fragment {

    Button date, category;
    public ListView lv;
    public MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reccuring, container, false);

        date = (Button)view.findViewById(R.id.btn_date);
        category = (Button)view.findViewById(R.id.btn_cat);
        lv = (ListView)view.findViewById(R.id.lv_dsorted);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        int dim = (width/2)-30;

        date.setWidth(dim);
        date.setHeight(dim);
        date.setTextSize(30);
        date.animate();
        category.setWidth(dim);
        category.setHeight(dim);
        category.setTextSize(30);
        category.animate();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Reccuring_Fragment.this.getActivity(),PopDateSelect.class),2);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Reccuring_Fragment.this.getActivity(),PopCatSelect.class),3);
            }
        });




        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            Toast.makeText(getActivity(),"From: "+data.getStringExtra("DATE1")+" To: "+data.getStringExtra("DATE2"),Toast.LENGTH_SHORT).show();
        }
        if(requestCode == 3){
            String ch1, ch2, ch3;
            //Toast.makeText(getActivity(),data.getStringExtra("CHOICE"),Toast.LENGTH_SHORT).show();
          /*  ch1= data.getStringExtra("CHOICE1");
            ch2= data.getStringExtra("CHOICE2");
            ch3= data.getStringExtra("CHOICE3");

            dbHandler = new MyDBHandler(getContext(),null,null,1);

            final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                    R.layout.listview_item_layout,
                    dbHandler.getExpenseByCat(ch1, ch2, ch3),
                    new String[]{dbHandler.COLUMN_TITLE, ""+dbHandler.COLUMN_AMOUNT,dbHandler.COLUMN_DATE, dbHandler.COLUMN_CATEGORY},
                    new int[]{R.id.itemtitle,R.id.itemamount,R.id.itemdate, R.id.itemcategory},
                    0);

            lv.setAdapter(adapter);*/

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Reccuring");
    }

}
