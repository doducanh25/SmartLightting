package com.example.arduinosensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

        mlayoutSettingGroup = (RelativeLayout) findViewById(R.id.layout_setting_group);
        mlayoutSettingGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,SettingGroupActivity.class);
                startActivity(intent);
            }
        });

        mLayoutSettingScript = (RelativeLayout) findViewById(R.id.layout_setting_script);
        mLayoutSettingScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,SettingScriptActivity.class);
                startActivity(intent);

            }
        });

        mLayoutSettingControl = (RelativeLayout) findViewById(R.id.layout_setting_control);
        mLayoutSettingControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLayoutFollow = (RelativeLayout) findViewById(R.id.layout_setting_follow);
        mLayoutFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
