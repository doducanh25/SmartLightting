package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 22/07/2016.
 */
public class DetailScriptActivity extends Activity {

    private ListView mListScriptDetail;
    private Button mAccept;

    private DetailScriptAdapter mDetailScriptAdapter;

    public static List<Groups> listData;

    private static ArrayList<String> list;

    public static String name_script = "";
    private String path;
    private String nameScript;
    private String[] name = null;
    private static FileOutputStream outputStream;
    private String address;
    private PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_script);

        Intent intent = getIntent();
        name_script = intent.getStringExtra("name_script");
        address = intent.getStringExtra("device_address");

        mListScriptDetail = (ListView) findViewById(R.id.list_detail_group_script);
        mDetailScriptAdapter = new DetailScriptAdapter(this);

        mAccept = (Button) findViewById(R.id.accept_script);
        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailScriptActivity.this, SettingScriptActivity.class);
                intent1.putExtra("device_address",address);
                startActivity(intent1);
            }
        });

        path = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

        File f = new File(path);
        File file[] = f.listFiles();

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh", name_script);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        File[] files = f1.listFiles();

        listData = new ArrayList<>();

        for (int ii = 0; ii < files.length; ii++) {

            Log.d("data",files[ii].getName());
            String data = "";

            if (files[ii].getName().contains(name_script+"-")){

                try {
                    FileInputStream is = new FileInputStream(new File(f1 + "/" + files[ii].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((data = reader.readLine()) != null) {

                                String[] addData = data.split("-");

                                listData.add(new Groups(addData[0], Integer.parseInt(addData[1]), DetailScriptActivity.this));

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
        mDetailScriptAdapter.setScript(listData);
        mListScriptDetail.setAdapter(mDetailScriptAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
