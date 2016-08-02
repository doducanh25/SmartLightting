package com.example.arduinosensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by user on 21/07/2016.
 */
public class ListGroupLightActivity extends Activity {

    ImageView mBack;
    Button mAcceptChooseLightIntoGroup;

    RelativeLayout mLight1;
    RelativeLayout mLight2;
    RelativeLayout mLight3;
    RelativeLayout mLight4;
    RelativeLayout mLight5;
    RelativeLayout mLight6;


    ImageView mIvLight1;
    ImageView mIvLight2;
    ImageView mIvLight3;
    ImageView mIvLight4;
    ImageView mIvLight5;
    ImageView mIvLight6;

    TextView mTvLight1;
    TextView mTvLight2;
    TextView mTvLight3;
    TextView mTvLight4;
    TextView mTvLight5;
    TextView mTvLight6;


    boolean light1 = false;
    boolean light2 = false;
    boolean light3 = false;
    boolean light4 = false;
    boolean light5 = false;
    boolean light6 = false;

    private String id_group;
    private int id_light;

    String path;
    private static FileOutputStream outputStreamItem;
    private PrintWriter pwItem;

    private static final String idLight1 = "1";
    private static final String idLight2 = "2";
    private static final String idLight3 = "3";
    private static final String idLight4 = "4";
    private static final String idLight5 = "5";
    private static final String idLight6 = "6";

    private File f1;
    private String pathLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_light);

        Intent intent = getIntent();
        id_group = intent.getStringExtra("id_group");


        id_light = Integer.parseInt(id_group);

        mBack = (ImageView) findViewById(R.id.back);
        mAcceptChooseLightIntoGroup = (Button) findViewById(R.id.accept_choose_light);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLight1 = (RelativeLayout) findViewById(R.id.light_1);
        mLight2 = (RelativeLayout) findViewById(R.id.light_2);
        mLight3 = (RelativeLayout) findViewById(R.id.light_3);
        mLight4 = (RelativeLayout) findViewById(R.id.light_4);
        mLight5 = (RelativeLayout) findViewById(R.id.light_5);
        mLight6 = (RelativeLayout) findViewById(R.id.light_6);

        mIvLight1 = (ImageView) findViewById(R.id.iv_light_1);
        mIvLight2 = (ImageView) findViewById(R.id.iv_light_2);
        mIvLight3 = (ImageView) findViewById(R.id.iv_light_3);
        mIvLight4 = (ImageView) findViewById(R.id.iv_light_4);
        mIvLight5 = (ImageView) findViewById(R.id.iv_light_5);
        mIvLight6 = (ImageView) findViewById(R.id.iv_light_6);

        mTvLight1 = (TextView) findViewById(R.id.tv_light_1);
        mTvLight2 = (TextView) findViewById(R.id.tv_light_2);
        mTvLight3 = (TextView) findViewById(R.id.tv_light_3);
        mTvLight4 = (TextView) findViewById(R.id.tv_light_4);
        mTvLight5 = (TextView) findViewById(R.id.tv_light_5);
        mTvLight6 = (TextView) findViewById(R.id.tv_light_6);

        f1 = new File(Environment.getExternalStorageDirectory() + "/" + "Group", "data"+id_group+"-");
        if (!f1.exists()) {
            f1.mkdirs();
        }

        File[] files = f1.listFiles();

        mLight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light1) {
                    mIvLight1.setImageResource(R.drawable.ic_on);
                    mTvLight1.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight1.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "1" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "1" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }


                } else {
                    mIvLight1.setImageResource(R.drawable.ic_off);
                    mTvLight1.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight1.setText("");
                    deleteLight("1");
                }

                light1 = !light1;
            }
        });

        mLight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light2) {
                    mIvLight2.setImageResource(R.drawable.ic_on);
                    mTvLight2.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight2.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "2" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "2" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight2.setImageResource(R.drawable.ic_off);
                    mTvLight2.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight2.setText("");
                    deleteLight("2");
                }

                light2 = !light2;
            }
        });

        mLight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light3) {
                    mIvLight3.setImageResource(R.drawable.ic_on);
                    mTvLight3.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight3.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "3" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "3" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight3.setImageResource(R.drawable.ic_off);
                    mTvLight3.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight3.setText("");
                    deleteLight("3");
                }

                light3 = !light3;
            }
        });

        mLight4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light4) {
                    mIvLight4.setImageResource(R.drawable.ic_on);
                    mTvLight4.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight4.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "4" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "4" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight4.setImageResource(R.drawable.ic_off);
                    mTvLight4.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight4.setText("");
                    deleteLight("4");
                }

                light4 = !light4;
            }
        });

        mLight5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light5) {
                    mIvLight5.setImageResource(R.drawable.ic_on);
                    mTvLight5.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight5.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "5" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "5" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight5.setImageResource(R.drawable.ic_off);
                    mTvLight5.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight5.setText("");
                    deleteLight("5");
                }

                light5 = !light5;
            }
        });

        mLight6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light6) {
                    mIvLight6.setImageResource(R.drawable.ic_on);
                    mTvLight6.setTextColor(getResources().getColor(R.color.text_yellow));
                    mTvLight6.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "6" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "6" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight6.setImageResource(R.drawable.ic_off);
                    mTvLight6.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight6.setText("");
                    deleteLight("6");
                }

                light6 = !light6;
            }
        });

        mAcceptChooseLightIntoGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(ListGroupLightActivity.this, DetailGroupLightActivity.class);
                intent1.putExtra("id", id_group);
                startActivity(intent1);
            }
        });


        String pathItemGroup = Environment.getExternalStorageDirectory().toString() + "/Group";

        File fItem = new File(pathItemGroup);
        File fileItem[] = fItem.listFiles();

        for (int i = 0; i < fileItem.length; i++) {
            File f1Group = new File(fItem, fileItem[i].getName());
            File[] fileContentGroup = f1Group.listFiles();

            String dataItem = "";

            for (int a = 0; a < fileContentGroup.length; a++) {

                try {
                    FileInputStream is = new FileInputStream(new File(f1Group + "/" + fileContentGroup[a].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((dataItem = reader.readLine()) != null) {

                                String[] input = dataItem.split("-");
                                displayNameGroupLight(input[1], input[0]);

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

    }

    private void displayNameGroupLight(String idLight, String idGroup) {

        switch (idLight) {
            case idLight1:
                mTvLight1.setText(idGroup);
                mIvLight1.setImageResource(R.drawable.ic_on);
                mTvLight1.setTextColor(getResources().getColor(R.color.text_yellow));

                light1 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight1.setEnabled(true);

                } else {
                    mLight1.setEnabled(false);
                }

                break;
            case idLight2:
                mTvLight2.setText(idGroup);
                mIvLight2.setImageResource(R.drawable.ic_on);
                mTvLight2.setTextColor(getResources().getColor(R.color.text_yellow));

                light2 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight2.setEnabled(true);
                } else {
                    mLight2.setEnabled(false);
                }

                break;
            case idLight3:
                mTvLight3.setText(idGroup);
                mIvLight3.setImageResource(R.drawable.ic_on);
                mTvLight3.setTextColor(getResources().getColor(R.color.text_yellow));

                light3 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight3.setEnabled(true);
                } else {
                    mLight3.setEnabled(false);
                }

                break;
            case idLight4:
                mTvLight4.setText(idGroup);
                mIvLight4.setImageResource(R.drawable.ic_on);
                mTvLight4.setTextColor(getResources().getColor(R.color.text_yellow));

                light4 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight4.setEnabled(true);
                } else {
                    mLight4.setEnabled(false);
                }

                break;
            case idLight5:
                mTvLight5.setText(idGroup);
                mIvLight5.setImageResource(R.drawable.ic_on);
                mTvLight5.setTextColor(getResources().getColor(R.color.text_yellow));

                light5 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight5.setEnabled(true);
                } else {
                    mLight5.setEnabled(false);
                }

                break;
            default:
                mTvLight6.setText(idGroup);
                mIvLight6.setImageResource(R.drawable.ic_on);
                mTvLight6.setTextColor(getResources().getColor(R.color.text_yellow));

                light6 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight6.setEnabled(true);
                } else {
                    mLight6.setEnabled(false);
                }

                break;

        }
    }

    private void deleteLight(String idLight) {
        pathLight = Environment.getExternalStorageDirectory().toString() + "/Group";

        File fDelete = new File(pathLight);

        if (fDelete.length() != 0) {

            File fileDelete[] = fDelete.listFiles();

            for (int a = 0; a < fileDelete.length; a++) {

                File f1Delete = new File(pathLight, fileDelete[a].getName());

                File[] nameDelete = f1Delete.listFiles();

                for (int aaa = 0; aaa < nameDelete.length;aaa++){
                    if (nameDelete[aaa].getName().contains("-"+ idLight +".txt")){
                        nameDelete[aaa].delete();
                    }
                }

            }
        }
    }

}
