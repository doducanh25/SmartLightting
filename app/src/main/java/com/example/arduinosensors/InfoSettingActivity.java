package com.example.arduinosensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 26/08/2016.
 */
public class InfoSettingActivity extends Activity {

    TextView mDisplayScript;
    TextView mDisplayGroup;
    ImageView mShutDown;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_setting);

        Intent intent = getIntent();
        address = intent.getStringExtra("address");

        mDisplayScript = (TextView) findViewById(R.id.display_info_script);
        mDisplayGroup = (TextView) findViewById(R.id.display_info_group);
        mShutDown = (ImageView) findViewById(R.id.shutdown_control);

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh");
        if (!f1.exists()) {
            f1.mkdirs();
        }

        File[] files = f1.listFiles();

        if (files.length != 0) {

            StringBuilder text = new StringBuilder();

            for (int ii = 0; ii < files.length; ii++) {

                Log.d("info", files[ii].getName());
                String data = "";
                File f2 = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh", files[ii].getName());

                File[] dataFile = f2.listFiles();

                for (int i = 0; i < dataFile.length; i++) {

                    try {
                        FileInputStream is = new FileInputStream(new File(f2 + "/" + dataFile[i].getName()));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        if (is != null) {
                            try {
                                while ((data = reader.readLine()) != null) {

                                    String[] addData = data.split("-");
                                    Log.d("info", addData[1]);

                                    text.append("Kịch bản" + " " + files[ii].getName() + "  " + "nhóm" + " " + addData[0] + "  " + "sáng" + " " + addData[1] + " " + "phần trăm");
                                    text.append('\n');
                                    text.append('\n');

                                }
                                is.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    } catch (FileNotFoundException e) {
                        Log.e("e", "File not found: " + e.toString());
                    }


                }

            }

            mDisplayScript.setText(text);

        }


        File fGroup = new File(Environment.getExternalStorageDirectory() + "/" + "Group");
        if (!fGroup.exists()) {
            fGroup.mkdirs();
        }

        File[] filesGroup = fGroup.listFiles();

        if (filesGroup.length != 0) {

            StringBuilder textGroup = new StringBuilder();

            for (int ii = 0; ii < filesGroup.length; ii++) {

                Log.d("info", filesGroup[ii].getName());

                String nameGroup = filesGroup[ii].getName();
                String sGroup = nameGroup.replace("data","");
                String sGroup1 = sGroup.replace("-","");

                String data = "";
                File f2Group = new File(Environment.getExternalStorageDirectory() + "/" + "Group", filesGroup[ii].getName());

                File[] dataFileGroup = f2Group.listFiles();

                for (int i = 0; i < dataFileGroup.length; i++) {

                    try {
                        FileInputStream is = new FileInputStream(new File(f2Group + "/" + dataFileGroup[i].getName()));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        if (is != null) {
                            try {
                                while ((data = reader.readLine()) != null) {

                                    String[] addData = data.split("-");
                                    Log.d("info", addData[1]);

                                    textGroup.append("Nhóm" + " " + sGroup1 + "  " + "đèn" + " " + addData[1] );
                                    textGroup.append('\n');
                                    textGroup.append('\n');

                                }
                                is.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    } catch (FileNotFoundException e) {
                        Log.e("e", "File not found: " + e.toString());
                    }

                }

            }

            mDisplayGroup.setText(textGroup);

        }

        mShutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(InfoSettingActivity.this, ScriptMainActivity.class);
                inte.putExtra("device_address", address);
                startActivity(inte);
            }
        });

    }
}
