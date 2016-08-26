package com.example.arduinosensors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Set;

/**
 * Created by user on 19/07/2016.
 */
public class SettingActivity extends Activity {

    private RelativeLayout mlayoutSettingGroup;
    private RelativeLayout mLayoutSettingScript;
    private RelativeLayout mLayoutSettingControl;
    private RelativeLayout mLayoutFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();

        final String address = intent.getStringExtra("device_address");

        mlayoutSettingGroup = (RelativeLayout) findViewById(R.id.layout_setting_group);
        mlayoutSettingGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);

                // make the progress bar cancelable
                dialog.setCancelable(true);

                // set a message text
                dialog.setMessage("Đang load dữ liệu...");

                // show it
                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

                Intent intent = new Intent(SettingActivity.this,SettingGroupActivity.class);
                intent.putExtra("device_address",address);
                startActivity(intent);



            }
        });

        mLayoutSettingScript = (RelativeLayout) findViewById(R.id.layout_setting_script);
        mLayoutSettingScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);

                // make the progress bar cancelable
                dialog.setCancelable(true);

                // set a message text
                dialog.setMessage("Đang load dữ liệu...");

                // show it
                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

                Intent intent = new Intent(SettingActivity.this,SettingScriptActivity.class);
                intent.putExtra("device_address",address);
                startActivity(intent);

            }
        });

        mLayoutSettingControl = (RelativeLayout) findViewById(R.id.layout_setting_control);
        mLayoutSettingControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);

                // make the progress bar cancelable
                dialog.setCancelable(true);

                // set a message text
                dialog.setMessage("Đang load dữ liệu...");

                // show it
                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

                Intent intent = new Intent(SettingActivity.this,ScriptMainActivity.class);
                intent.putExtra("device_address",address);
                startActivity(intent);

            }
        });

        mLayoutFollow = (RelativeLayout) findViewById(R.id.layout_setting_follow);
        mLayoutFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingActivity.this,FollowLightsActivity.class);
                intent.putExtra("device_address",address);
                startActivity(intent);

            }
        });

    }
}
