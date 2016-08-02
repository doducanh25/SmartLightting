package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21/07/2016.
 */
public class DetailGroupLightActivity extends Activity implements DetailGroupLightAdapter.OnClickDeleteLight {

    private ListView mListLightDetail;

    private DetailGroupLightAdapter mDetailGroupLightAdapter;

    private static List<Light> lights;

    RelativeLayout mAddLight;

    private String id_group;
    private File f1;

    ImageView mBack;

    private String pathLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_group_light);



        Intent intent = getIntent();
        id_group = intent.getStringExtra("id");

        mBack = (ImageView) findViewById(R.id.back_detail_group);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailGroupLightActivity.this,SettingGroupActivity.class);
                startActivity(intent1);
            }
        });

        mListLightDetail = (ListView) findViewById(R.id.list_detail_light);

        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_list_detail_group_light, null, false);
        mListLightDetail.addFooterView(footerView);

        mAddLight = (RelativeLayout) footerView.findViewById(R.id.layout_add_light_new);
        mAddLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailGroupLightActivity.this,ListGroupLightActivity.class);
                intent.putExtra("id_group",id_group);
                startActivity(intent);
            }
        });

        mDetailGroupLightAdapter = new DetailGroupLightAdapter(this);

        lights = new ArrayList<>();

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "Group", "data"+ id_group + "-");
        if (!f1.exists()) {
            f1.mkdirs();
        }


        File fileGroup[] = f1.listFiles();


        String dataInGroup = "";

        for (int i=0; i < fileGroup.length; i++)
        {
            Log.d("name",fileGroup[i].getName());
            if (fileGroup[i].getName().contains("data"+ id_group + "-")) {
                try {
                    FileInputStream is = new FileInputStream(new File(f1+"/"+fileGroup[i].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((dataInGroup = reader.readLine()) != null) {

                                String[] addData = dataInGroup.split("-");
                                Log.d("name",addData[1]);

                                lights.add(new Light(addData[1],Integer.parseInt(addData[1])));

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


        mDetailGroupLightAdapter.setGroupDetail(lights);
        mListLightDetail.setAdapter(mDetailGroupLightAdapter);
        mDetailGroupLightAdapter.setOnClickDeleteLight(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mDetailGroupLightAdapter = new DetailGroupLightAdapter(this);

        lights = new ArrayList<>();

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "Group", "data"+ id_group + "-");
        if (!f1.exists()) {
            f1.mkdirs();
        }


        File fileGroup[] = f1.listFiles();


        String dataInGroup = "";

        for (int i=0; i < fileGroup.length; i++)
        {
            Log.d("name",fileGroup[i].getName());
            if (fileGroup[i].getName().contains("data"+ id_group + "-")) {
                try {
                    FileInputStream is = new FileInputStream(new File(f1+"/"+fileGroup[i].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((dataInGroup = reader.readLine()) != null) {

                                String[] addData = dataInGroup.split("-");

                                lights.add(new Light(addData[1],Integer.parseInt(addData[1])));

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


        mDetailGroupLightAdapter.setGroupDetail(lights);
        mListLightDetail.setAdapter(mDetailGroupLightAdapter);
        mDetailGroupLightAdapter.setOnClickDeleteLight(this);
    }

    @Override
    public void onClickDeleteLight(int postion,String light) {
        Log.d("vao day","1");

        lights.remove(postion);
        mDetailGroupLightAdapter.notifyDataSetChanged();


        mDetailGroupLightAdapter.setGroupDetail(lights);
        mListLightDetail.setAdapter(mDetailGroupLightAdapter);

        mListLightDetail.invalidateViews();

        pathLight = Environment.getExternalStorageDirectory().toString() + "/Group";

        File fDelete = new File(pathLight);

        if (fDelete.length() != 0) {

            File fileDelete[] = fDelete.listFiles();

            for (int a = 0; a < fileDelete.length; a++) {

                File f1Delete = new File(pathLight, fileDelete[a].getName());

                File[] nameDelete = f1Delete.listFiles();

                for (int aaa = 0; aaa < nameDelete.length;aaa++){
                    if (nameDelete[aaa].getName().contains("-"+ light +".txt")){
                        nameDelete[aaa].delete();
                    }
                }

            }
        }
    }
}
