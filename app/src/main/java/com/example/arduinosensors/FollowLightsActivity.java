package com.example.arduinosensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/08/2016.
 */
public class FollowLightsActivity extends Activity {

    GridView mListLights;

    StatusLightsAdapter mAdapter;

    List<Status> statuses;

    ImageView mBack;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_lights);

        Intent intent = getIntent();
        address = intent.getStringExtra("device_address");

        statuses = new ArrayList<>();

        statuses.add(new Status("01",50));
        statuses.add(new Status("02",50));
        statuses.add(new Status("03",50));
        statuses.add(new Status("04",50));
        statuses.add(new Status("05",50));
        statuses.add(new Status("06",50));
        statuses.add(new Status("07",50));
        statuses.add(new Status("08",50));
        statuses.add(new Status("09",50));
        statuses.add(new Status("10",50));
        statuses.add(new Status("11",50));
        statuses.add(new Status("12",50));
        statuses.add(new Status("13",50));
        statuses.add(new Status("14",50));
        statuses.add(new Status("15",50));
        statuses.add(new Status("16",50));
        statuses.add(new Status("17",50));
        statuses.add(new Status("18",50));
        statuses.add(new Status("19",50));
        statuses.add(new Status("20",50));
        statuses.add(new Status("21",50));
        statuses.add(new Status("22",50));
        statuses.add(new Status("23",50));
        statuses.add(new Status("24",50));
        statuses.add(new Status("25",50));
        statuses.add(new Status("26",50));
        statuses.add(new Status("27",50));
        statuses.add(new Status("28",50));
        statuses.add(new Status("29",50));
        statuses.add(new Status("30",50));
        statuses.add(new Status("31",50));
        statuses.add(new Status("32",50));


        mListLights = (GridView) findViewById(R.id.gv_list_lights);

        mAdapter = new StatusLightsAdapter(this);
        mAdapter.setStatus(statuses);

        mListLights.setAdapter(mAdapter);

        mBack = (ImageView) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FollowLightsActivity.this,SettingActivity.class);
                intent1.putExtra("device_address",address);
                startActivity(intent1);
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

}
