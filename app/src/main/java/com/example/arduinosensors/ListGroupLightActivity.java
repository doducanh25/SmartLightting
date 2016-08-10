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

    Button mAcceptChooseLightIntoGroup;

    RelativeLayout mLight1;
    RelativeLayout mLight2;
    RelativeLayout mLight3;
    RelativeLayout mLight4;
    RelativeLayout mLight5;
    RelativeLayout mLight6;
    RelativeLayout mLight7;
    RelativeLayout mLight8;
    RelativeLayout mLight9;
    RelativeLayout mLight10;
    RelativeLayout mLight11;
    RelativeLayout mLight12;
    RelativeLayout mLight13;
    RelativeLayout mLight14;
    RelativeLayout mLight15;
    RelativeLayout mLight16;
    RelativeLayout mLight17;
    RelativeLayout mLight18;
    RelativeLayout mLight19;
    RelativeLayout mLight20;
    RelativeLayout mLight21;
    RelativeLayout mLight22;
    RelativeLayout mLight23;
    RelativeLayout mLight24;
    RelativeLayout mLight25;
    RelativeLayout mLight26;
    RelativeLayout mLight27;
    RelativeLayout mLight28;
    RelativeLayout mLight29;
    RelativeLayout mLight30;
    RelativeLayout mLight31;
    RelativeLayout mLight32;


    ImageView mIvLight1;
    ImageView mIvLight2;
    ImageView mIvLight3;
    ImageView mIvLight4;
    ImageView mIvLight5;
    ImageView mIvLight6;
    ImageView mIvLight7;
    ImageView mIvLight8;
    ImageView mIvLight9;
    ImageView mIvLight10;
    ImageView mIvLight11;
    ImageView mIvLight12;
    ImageView mIvLight13;
    ImageView mIvLight14;
    ImageView mIvLight15;
    ImageView mIvLight16;
    ImageView mIvLight17;
    ImageView mIvLight18;
    ImageView mIvLight19;
    ImageView mIvLight20;
    ImageView mIvLight21;
    ImageView mIvLight22;
    ImageView mIvLight23;
    ImageView mIvLight24;
    ImageView mIvLight25;
    ImageView mIvLight26;
    ImageView mIvLight27;
    ImageView mIvLight28;
    ImageView mIvLight29;
    ImageView mIvLight30;
    ImageView mIvLight31;
    ImageView mIvLight32;

    TextView mTvLight1;
    TextView mTvLight2;
    TextView mTvLight3;
    TextView mTvLight4;
    TextView mTvLight5;
    TextView mTvLight6;
    TextView mTvLight7;
    TextView mTvLight8;
    TextView mTvLight9;
    TextView mTvLight10;
    TextView mTvLight11;
    TextView mTvLight12;
    TextView mTvLight13;
    TextView mTvLight14;
    TextView mTvLight15;
    TextView mTvLight16;
    TextView mTvLight17;
    TextView mTvLight18;
    TextView mTvLight19;
    TextView mTvLight20;
    TextView mTvLight21;
    TextView mTvLight22;
    TextView mTvLight23;
    TextView mTvLight24;
    TextView mTvLight25;
    TextView mTvLight26;
    TextView mTvLight27;
    TextView mTvLight28;
    TextView mTvLight29;
    TextView mTvLight30;
    TextView mTvLight31;
    TextView mTvLight32;


    boolean light1 = false;
    boolean light2 = false;
    boolean light3 = false;
    boolean light4 = false;
    boolean light5 = false;
    boolean light6 = false;
    boolean light7 = false;
    boolean light8 = false;
    boolean light9 = false;
    boolean light10 = false;
    boolean light11 = false;
    boolean light12 = false;
    boolean light13 = false;
    boolean light14 = false;
    boolean light15 = false;
    boolean light16 = false;
    boolean light17 = false;
    boolean light18 = false;
    boolean light19 = false;
    boolean light20 = false;
    boolean light21 = false;
    boolean light22 = false;
    boolean light23 = false;
    boolean light24 = false;
    boolean light25 = false;
    boolean light26 = false;
    boolean light27 = false;
    boolean light28 = false;
    boolean light29 = false;
    boolean light30 = false;
    boolean light31 = false;
    boolean light32 = false;

    private String id_group;
    private String address;
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
    private static final String idLight7 = "7";
    private static final String idLight8 = "8";
    private static final String idLight9 = "9";
    private static final String idLight10 = "10";
    private static final String idLight11 = "11";
    private static final String idLight12 = "12";
    private static final String idLight13 = "13";
    private static final String idLight14 = "14";
    private static final String idLight15 = "15";
    private static final String idLight16 = "16";
    private static final String idLight17 = "17";
    private static final String idLight18 = "18";
    private static final String idLight19 = "19";
    private static final String idLight20 = "20";
    private static final String idLight21 = "21";
    private static final String idLight22 = "22";
    private static final String idLight23 = "23";
    private static final String idLight24 = "24";
    private static final String idLight25 = "25";
    private static final String idLight26 = "26";
    private static final String idLight27 = "27";
    private static final String idLight28 = "28";
    private static final String idLight29 = "29";
    private static final String idLight30 = "30";
    private static final String idLight31 = "31";
    private static final String idLight32 = "32";


    private File f1;
    private String pathLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_light);

        Intent intent = getIntent();
        id_group = intent.getStringExtra("id_group");
        address = intent.getStringExtra("device_address");


        id_light = Integer.parseInt(id_group);

        mAcceptChooseLightIntoGroup = (Button) findViewById(R.id.accept_choose_light);

        mLight1 = (RelativeLayout) findViewById(R.id.light_1);
        mLight2 = (RelativeLayout) findViewById(R.id.light_2);
        mLight3 = (RelativeLayout) findViewById(R.id.light_3);
        mLight4 = (RelativeLayout) findViewById(R.id.light_4);
        mLight5 = (RelativeLayout) findViewById(R.id.light_5);
        mLight6 = (RelativeLayout) findViewById(R.id.light_6);
        mLight7 = (RelativeLayout) findViewById(R.id.light_7);
        mLight8 = (RelativeLayout) findViewById(R.id.light_8);
        mLight9 = (RelativeLayout) findViewById(R.id.light_9);
        mLight10 = (RelativeLayout) findViewById(R.id.light_10);
        mLight11 = (RelativeLayout) findViewById(R.id.light_11);
        mLight12 = (RelativeLayout) findViewById(R.id.light_12);
        mLight13 = (RelativeLayout) findViewById(R.id.light_13);
        mLight14 = (RelativeLayout) findViewById(R.id.light_14);
        mLight15 = (RelativeLayout) findViewById(R.id.light_15);
        mLight16 = (RelativeLayout) findViewById(R.id.light_16);
        mLight17 = (RelativeLayout) findViewById(R.id.light_17);
        mLight18 = (RelativeLayout) findViewById(R.id.light_18);
        mLight19 = (RelativeLayout) findViewById(R.id.light_19);
        mLight20 = (RelativeLayout) findViewById(R.id.light_20);
        mLight21 = (RelativeLayout) findViewById(R.id.light_21);
        mLight22 = (RelativeLayout) findViewById(R.id.light_22);
        mLight23 = (RelativeLayout) findViewById(R.id.light_23);
        mLight24 = (RelativeLayout) findViewById(R.id.light_24);
        mLight25 = (RelativeLayout) findViewById(R.id.light_25);
        mLight26 = (RelativeLayout) findViewById(R.id.light_26);
        mLight27 = (RelativeLayout) findViewById(R.id.light_27);
        mLight28 = (RelativeLayout) findViewById(R.id.light_28);
        mLight29 = (RelativeLayout) findViewById(R.id.light_29);
        mLight30 = (RelativeLayout) findViewById(R.id.light_30);
        mLight31 = (RelativeLayout) findViewById(R.id.light_31);
        mLight32 = (RelativeLayout) findViewById(R.id.light_32);


        mIvLight1 = (ImageView) findViewById(R.id.iv_light_1);
        mIvLight2 = (ImageView) findViewById(R.id.iv_light_2);
        mIvLight3 = (ImageView) findViewById(R.id.iv_light_3);
        mIvLight4 = (ImageView) findViewById(R.id.iv_light_4);
        mIvLight5 = (ImageView) findViewById(R.id.iv_light_5);
        mIvLight6 = (ImageView) findViewById(R.id.iv_light_6);
        mIvLight7 = (ImageView) findViewById(R.id.iv_light_7);
        mIvLight8 = (ImageView) findViewById(R.id.iv_light_8);
        mIvLight9 = (ImageView) findViewById(R.id.iv_light_9);
        mIvLight10 = (ImageView) findViewById(R.id.iv_light_10);
        mIvLight11 = (ImageView) findViewById(R.id.iv_light_11);
        mIvLight12 = (ImageView) findViewById(R.id.iv_light_12);
        mIvLight13 = (ImageView) findViewById(R.id.iv_light_13);
        mIvLight14 = (ImageView) findViewById(R.id.iv_light_14);
        mIvLight15 = (ImageView) findViewById(R.id.iv_light_15);
        mIvLight16 = (ImageView) findViewById(R.id.iv_light_16);
        mIvLight17 = (ImageView) findViewById(R.id.iv_light_17);
        mIvLight18 = (ImageView) findViewById(R.id.iv_light_18);
        mIvLight19 = (ImageView) findViewById(R.id.iv_light_19);
        mIvLight20 = (ImageView) findViewById(R.id.iv_light_20);
        mIvLight21 = (ImageView) findViewById(R.id.iv_light_21);
        mIvLight22 = (ImageView) findViewById(R.id.iv_light_22);
        mIvLight23 = (ImageView) findViewById(R.id.iv_light_23);
        mIvLight24 = (ImageView) findViewById(R.id.iv_light_24);
        mIvLight25 = (ImageView) findViewById(R.id.iv_light_25);
        mIvLight26 = (ImageView) findViewById(R.id.iv_light_26);
        mIvLight27 = (ImageView) findViewById(R.id.iv_light_27);
        mIvLight28 = (ImageView) findViewById(R.id.iv_light_28);
        mIvLight29 = (ImageView) findViewById(R.id.iv_light_29);
        mIvLight30 = (ImageView) findViewById(R.id.iv_light_30);
        mIvLight31 = (ImageView) findViewById(R.id.iv_light_31);
        mIvLight32 = (ImageView) findViewById(R.id.iv_light_32);


        mTvLight1 = (TextView) findViewById(R.id.tv_light_1);
        mTvLight2 = (TextView) findViewById(R.id.tv_light_2);
        mTvLight3 = (TextView) findViewById(R.id.tv_light_3);
        mTvLight4 = (TextView) findViewById(R.id.tv_light_4);
        mTvLight5 = (TextView) findViewById(R.id.tv_light_5);
        mTvLight6 = (TextView) findViewById(R.id.tv_light_6);
        mTvLight7 = (TextView) findViewById(R.id.tv_light_7);
        mTvLight8 = (TextView) findViewById(R.id.tv_light_8);
        mTvLight9 = (TextView) findViewById(R.id.tv_light_9);
        mTvLight10 = (TextView) findViewById(R.id.tv_light_10);
        mTvLight11 = (TextView) findViewById(R.id.tv_light_11);
        mTvLight12 = (TextView) findViewById(R.id.tv_light_12);
        mTvLight13 = (TextView) findViewById(R.id.tv_light_13);
        mTvLight14 = (TextView) findViewById(R.id.tv_light_14);
        mTvLight15 = (TextView) findViewById(R.id.tv_light_15);
        mTvLight16 = (TextView) findViewById(R.id.tv_light_16);
        mTvLight17 = (TextView) findViewById(R.id.tv_light_17);
        mTvLight18 = (TextView) findViewById(R.id.tv_light_18);
        mTvLight19 = (TextView) findViewById(R.id.tv_light_19);
        mTvLight20 = (TextView) findViewById(R.id.tv_light_20);
        mTvLight21 = (TextView) findViewById(R.id.tv_light_21);
        mTvLight22 = (TextView) findViewById(R.id.tv_light_22);
        mTvLight23 = (TextView) findViewById(R.id.tv_light_23);
        mTvLight24 = (TextView) findViewById(R.id.tv_light_24);
        mTvLight25 = (TextView) findViewById(R.id.tv_light_25);
        mTvLight26 = (TextView) findViewById(R.id.tv_light_26);
        mTvLight27 = (TextView) findViewById(R.id.tv_light_27);
        mTvLight28 = (TextView) findViewById(R.id.tv_light_28);
        mTvLight29 = (TextView) findViewById(R.id.tv_light_29);
        mTvLight30 = (TextView) findViewById(R.id.tv_light_30);
        mTvLight31 = (TextView) findViewById(R.id.tv_light_31);
        mTvLight32 = (TextView) findViewById(R.id.tv_light_32);

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
                    mTvLight1.setTextColor(getResources().getColor(R.color.text_color));
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
                    mTvLight2.setTextColor(getResources().getColor(R.color.text_color));
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
                    mTvLight3.setTextColor(getResources().getColor(R.color.text_color));
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
                    mTvLight4.setTextColor(getResources().getColor(R.color.text_color));
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
                    mTvLight5.setTextColor(getResources().getColor(R.color.text_color));
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
                    mTvLight6.setTextColor(getResources().getColor(R.color.text_color));
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

        mLight7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light7) {
                    mIvLight7.setImageResource(R.drawable.ic_on);
                    mTvLight7.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight7.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "7" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "7" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight7.setImageResource(R.drawable.ic_off);
                    mTvLight7.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight7.setText("");
                    deleteLight("7");
                }

                light7 = !light7;
            }
        });


        mLight8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light8) {
                    mIvLight8.setImageResource(R.drawable.ic_on);
                    mTvLight8.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight8.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "8" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "8" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight8.setImageResource(R.drawable.ic_off);
                    mTvLight8.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight8.setText("");
                    deleteLight("8");
                }

                light8 = !light8;
            }
        });


        mLight9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light9) {
                    mIvLight9.setImageResource(R.drawable.ic_on);
                    mTvLight9.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight9.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "9" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "9" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight9.setImageResource(R.drawable.ic_off);
                    mTvLight9.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight9.setText("");
                    deleteLight("9");
                }

                light9 = !light9;
            }
        });

        mLight10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light10) {
                    mIvLight10.setImageResource(R.drawable.ic_on);
                    mTvLight10.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight10.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "10" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "10" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight10.setImageResource(R.drawable.ic_off);
                    mTvLight10.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight10.setText("");
                    deleteLight("10");
                }

                light10 = !light10;
            }
        });

        mLight11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light11) {
                    mIvLight11.setImageResource(R.drawable.ic_on);
                    mTvLight11.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight11.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "11" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "11" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight11.setImageResource(R.drawable.ic_off);
                    mTvLight11.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight11.setText("");
                    deleteLight("11");
                }

                light11 = !light11;
            }
        });

        mLight12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light12) {
                    mIvLight12.setImageResource(R.drawable.ic_on);
                    mTvLight12.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight12.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "12" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "12" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight12.setImageResource(R.drawable.ic_off);
                    mTvLight12.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight12.setText("");
                    deleteLight("12");
                }

                light12 = !light12;
            }
        });

        mLight13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light13) {
                    mIvLight13.setImageResource(R.drawable.ic_on);
                    mTvLight13.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight13.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "13" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "13" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight13.setImageResource(R.drawable.ic_off);
                    mTvLight13.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight13.setText("");
                    deleteLight("13");
                }

                light13 = !light13;
            }
        });

        mLight14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light14) {
                    mIvLight14.setImageResource(R.drawable.ic_on);
                    mTvLight14.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight14.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "14" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "14" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight14.setImageResource(R.drawable.ic_off);
                    mTvLight14.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight14.setText("");
                    deleteLight("14");
                }

                light14 = !light14;
            }
        });

        mLight15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light15) {
                    mIvLight15.setImageResource(R.drawable.ic_on);
                    mTvLight15.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight15.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "15" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "15" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight15.setImageResource(R.drawable.ic_off);
                    mTvLight15.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight15.setText("");
                    deleteLight("15");
                }

                light15 = !light15;
            }
        });

        mLight16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light16) {
                    mIvLight16.setImageResource(R.drawable.ic_on);
                    mTvLight16.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight16.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "16" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "16" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight16.setImageResource(R.drawable.ic_off);
                    mTvLight16.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight16.setText("");
                    deleteLight("16");
                }

                light16 = !light16;
            }
        });

        mLight17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light17) {
                    mIvLight17.setImageResource(R.drawable.ic_on);
                    mTvLight17.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight17.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "17" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "17" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight17.setImageResource(R.drawable.ic_off);
                    mTvLight17.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight17.setText("");
                    deleteLight("17");
                }

                light17 = !light17;
            }
        });


        mLight18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light18) {
                    mIvLight18.setImageResource(R.drawable.ic_on);
                    mTvLight18.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight18.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "18" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "18" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight18.setImageResource(R.drawable.ic_off);
                    mTvLight18.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight18.setText("");
                    deleteLight("18");
                }

                light18 = !light18;
            }
        });


        mLight19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light19) {
                    mIvLight19.setImageResource(R.drawable.ic_on);
                    mTvLight19.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight19.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "19" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "19" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight19.setImageResource(R.drawable.ic_off);
                    mTvLight19.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight19.setText("");
                    deleteLight("19");
                }

                light19 = !light19;
            }
        });

        mLight20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light20) {
                    mIvLight20.setImageResource(R.drawable.ic_on);
                    mTvLight20.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight20.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "20" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "20" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight20.setImageResource(R.drawable.ic_off);
                    mTvLight20.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight20.setText("");
                    deleteLight("20");
                }

                light20 = !light20;
            }
        });

        mLight21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light21) {
                    mIvLight21.setImageResource(R.drawable.ic_on);
                    mTvLight21.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight21.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "21" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "21" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight21.setImageResource(R.drawable.ic_off);
                    mTvLight21.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight21.setText("");
                    deleteLight("21");
                }

                light21 = !light21;
            }
        });

        mLight22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light22) {
                    mIvLight22.setImageResource(R.drawable.ic_on);
                    mTvLight22.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight22.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "22" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "22" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight22.setImageResource(R.drawable.ic_off);
                    mTvLight22.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight22.setText("");
                    deleteLight("22");
                }

                light22 = !light22;
            }
        });

        mLight23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light23) {
                    mIvLight23.setImageResource(R.drawable.ic_on);
                    mTvLight23.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight23.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "23" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "23" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight23.setImageResource(R.drawable.ic_off);
                    mTvLight23.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight23.setText("");
                    deleteLight("23");
                }

                light23 = !light23;
            }
        });

        mLight24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light24) {
                    mIvLight24.setImageResource(R.drawable.ic_on);
                    mTvLight24.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight24.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "24" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "24" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight24.setImageResource(R.drawable.ic_off);
                    mTvLight24.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight24.setText("");
                    deleteLight("24");
                }

                light24 = !light24;
            }
        });

        mLight25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light25) {
                    mIvLight25.setImageResource(R.drawable.ic_on);
                    mTvLight25.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight25.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "25" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "25" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight25.setImageResource(R.drawable.ic_off);
                    mTvLight25.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight25.setText("");
                    deleteLight("25");
                }

                light25 = !light25;
            }
        });

        mLight26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light26) {
                    mIvLight26.setImageResource(R.drawable.ic_on);
                    mTvLight26.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight26.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "26" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "26" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight26.setImageResource(R.drawable.ic_off);
                    mTvLight26.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight26.setText("");
                    deleteLight("26");
                }

                light26 = !light26;
            }
        });

        mLight27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light27) {
                    mIvLight27.setImageResource(R.drawable.ic_on);
                    mTvLight27.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight27.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "27" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "27" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight27.setImageResource(R.drawable.ic_off);
                    mTvLight27.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight27.setText("");
                    deleteLight("27");
                }

                light27 = !light27;
            }
        });

        mLight28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light28) {
                    mIvLight28.setImageResource(R.drawable.ic_on);
                    mTvLight28.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight28.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "28" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "28" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight28.setImageResource(R.drawable.ic_off);
                    mTvLight28.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight28.setText("");
                    deleteLight("28");
                }

                light28 = !light28;
            }
        });

        mLight29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light29) {
                    mIvLight29.setImageResource(R.drawable.ic_on);
                    mTvLight29.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight29.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "29" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "29" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight29.setImageResource(R.drawable.ic_off);
                    mTvLight29.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight29.setText("");
                    deleteLight("29");
                }

                light29 = !light29;
            }
        });

        mLight30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light30) {
                    mIvLight30.setImageResource(R.drawable.ic_on);
                    mTvLight30.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight30.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "30" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "30" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight30.setImageResource(R.drawable.ic_off);
                    mTvLight30.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight30.setText("");
                    deleteLight("30");
                }

                light30 = !light30;
            }
        });

        mLight31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light31) {
                    mIvLight31.setImageResource(R.drawable.ic_on);
                    mTvLight31.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight31.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "31" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "31" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight31.setImageResource(R.drawable.ic_off);
                    mTvLight31.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight31.setText("");
                    deleteLight("31");
                }

                light31 = !light31;
            }
        });

        mLight32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!light32) {
                    mIvLight32.setImageResource(R.drawable.ic_on);
                    mTvLight32.setTextColor(getResources().getColor(R.color.text_color));
                    mTvLight32.setText(String.valueOf(id_light));

                    File fileData = new File(f1,
                            "data"+id_group + "-" + "32" + ".txt");
                    try {
                        outputStreamItem = new FileOutputStream(fileData);

                        pwItem = new PrintWriter(outputStreamItem);

                        pwItem.append(id_group + "-" + "32" + "\n");
                        pwItem.flush();
                        pwItem.close();

                    } catch (FileNotFoundException c) {
                        c.printStackTrace();
                    }

                } else {
                    mIvLight32.setImageResource(R.drawable.ic_off);
                    mTvLight32.setTextColor(getResources().getColor(R.color.text_black));
                    mTvLight32.setText("");
                    deleteLight("32");
                }

                light32 = !light32;
            }
        });


        mAcceptChooseLightIntoGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(ListGroupLightActivity.this, DetailGroupLightActivity.class);
                intent1.putExtra("id", id_group);
                intent1.putExtra("device_address",address);
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
                mTvLight1.setTextColor(getResources().getColor(R.color.text_color));

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
                mTvLight2.setTextColor(getResources().getColor(R.color.text_color));

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
                mTvLight3.setTextColor(getResources().getColor(R.color.text_color));

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
                mTvLight4.setTextColor(getResources().getColor(R.color.text_color));

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
                mTvLight5.setTextColor(getResources().getColor(R.color.text_color));

                light5 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight5.setEnabled(true);
                } else {
                    mLight5.setEnabled(false);
                }

                break;
            case idLight6:
                mTvLight6.setText(idGroup);
                mIvLight6.setImageResource(R.drawable.ic_on);
                mTvLight6.setTextColor(getResources().getColor(R.color.text_color));

                light6 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight6.setEnabled(true);

                } else {
                    mLight6.setEnabled(false);
                }

                break;

            case idLight7:
                mTvLight7.setText(idGroup);
                mIvLight7.setImageResource(R.drawable.ic_on);
                mTvLight7.setTextColor(getResources().getColor(R.color.text_color));

                light7 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight7.setEnabled(true);

                } else {
                    mLight7.setEnabled(false);
                }

                break;

            case idLight8:
                mTvLight8.setText(idGroup);
                mIvLight8.setImageResource(R.drawable.ic_on);
                mTvLight8.setTextColor(getResources().getColor(R.color.text_color));

                light8 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight8.setEnabled(true);

                } else {
                    mLight8.setEnabled(false);
                }

                break;

            case idLight9:
                mTvLight9.setText(idGroup);
                mIvLight9.setImageResource(R.drawable.ic_on);
                mTvLight9.setTextColor(getResources().getColor(R.color.text_color));

                light9 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight9.setEnabled(true);

                } else {
                    mLight9.setEnabled(false);
                }

                break;

            case idLight10:
                mTvLight10.setText(idGroup);
                mIvLight10.setImageResource(R.drawable.ic_on);
                mTvLight10.setTextColor(getResources().getColor(R.color.text_color));

                light10 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight10.setEnabled(true);

                } else {
                    mLight10.setEnabled(false);
                }

                break;

            case idLight11:
                mTvLight11.setText(idGroup);
                mIvLight11.setImageResource(R.drawable.ic_on);
                mTvLight11.setTextColor(getResources().getColor(R.color.text_color));

                light11 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight11.setEnabled(true);

                } else {
                    mLight11.setEnabled(false);
                }

                break;

            case idLight12:
                mTvLight12.setText(idGroup);
                mIvLight12.setImageResource(R.drawable.ic_on);
                mTvLight12.setTextColor(getResources().getColor(R.color.text_color));

                light12 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight12.setEnabled(true);

                } else {
                    mLight12.setEnabled(false);
                }

                break;

            case idLight13:
                mTvLight13.setText(idGroup);
                mIvLight13.setImageResource(R.drawable.ic_on);
                mTvLight13.setTextColor(getResources().getColor(R.color.text_color));

                light13 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight13.setEnabled(true);

                } else {
                    mLight13.setEnabled(false);
                }

                break;

            case idLight14:
                mTvLight14.setText(idGroup);
                mIvLight14.setImageResource(R.drawable.ic_on);
                mTvLight14.setTextColor(getResources().getColor(R.color.text_color));

                light14 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight14.setEnabled(true);

                } else {
                    mLight14.setEnabled(false);
                }

                break;

            case idLight15:
                mTvLight15.setText(idGroup);
                mIvLight15.setImageResource(R.drawable.ic_on);
                mTvLight15.setTextColor(getResources().getColor(R.color.text_color));

                light15 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight15.setEnabled(true);

                } else {
                    mLight15.setEnabled(false);
                }

                break;

            case idLight16:
                mTvLight16.setText(idGroup);
                mIvLight16.setImageResource(R.drawable.ic_on);
                mTvLight16.setTextColor(getResources().getColor(R.color.text_color));

                light16 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight16.setEnabled(true);

                } else {
                    mLight16.setEnabled(false);
                }

                break;

            case idLight17:
                mTvLight17.setText(idGroup);
                mIvLight17.setImageResource(R.drawable.ic_on);
                mTvLight17.setTextColor(getResources().getColor(R.color.text_color));

                light17 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight17.setEnabled(true);

                } else {
                    mLight17.setEnabled(false);
                }

                break;

            case idLight18:
                mTvLight18.setText(idGroup);
                mIvLight18.setImageResource(R.drawable.ic_on);
                mTvLight18.setTextColor(getResources().getColor(R.color.text_color));

                light18 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight18.setEnabled(true);

                } else {
                    mLight18.setEnabled(false);
                }

                break;

            case idLight19:
                mTvLight19.setText(idGroup);
                mIvLight19.setImageResource(R.drawable.ic_on);
                mTvLight19.setTextColor(getResources().getColor(R.color.text_color));

                light19 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight19.setEnabled(true);

                } else {
                    mLight19.setEnabled(false);
                }

                break;

            case idLight20:
                mTvLight20.setText(idGroup);
                mIvLight20.setImageResource(R.drawable.ic_on);
                mTvLight20.setTextColor(getResources().getColor(R.color.text_color));

                light20 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight20.setEnabled(true);

                } else {
                    mLight20.setEnabled(false);
                }

                break;

            case idLight21:
                mTvLight21.setText(idGroup);
                mIvLight21.setImageResource(R.drawable.ic_on);
                mTvLight21.setTextColor(getResources().getColor(R.color.text_color));

                light21 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight21.setEnabled(true);

                } else {
                    mLight21.setEnabled(false);
                }

                break;

            case idLight22:
                mTvLight22.setText(idGroup);
                mIvLight22.setImageResource(R.drawable.ic_on);
                mTvLight22.setTextColor(getResources().getColor(R.color.text_color));

                light22 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight22.setEnabled(true);

                } else {
                    mLight22.setEnabled(false);
                }

                break;

            case idLight23:
                mTvLight23.setText(idGroup);
                mIvLight23.setImageResource(R.drawable.ic_on);
                mTvLight23.setTextColor(getResources().getColor(R.color.text_color));

                light23 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight23.setEnabled(true);

                } else {
                    mLight23.setEnabled(false);
                }

                break;

            case idLight24:
                mTvLight24.setText(idGroup);
                mIvLight24.setImageResource(R.drawable.ic_on);
                mTvLight24.setTextColor(getResources().getColor(R.color.text_color));

                light24 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight24.setEnabled(true);

                } else {
                    mLight24.setEnabled(false);
                }

                break;

            case idLight25:
                mTvLight25.setText(idGroup);
                mIvLight25.setImageResource(R.drawable.ic_on);
                mTvLight25.setTextColor(getResources().getColor(R.color.text_color));

                light25 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight25.setEnabled(true);

                } else {
                    mLight25.setEnabled(false);
                }

                break;

            case idLight26:
                mTvLight26.setText(idGroup);
                mIvLight26.setImageResource(R.drawable.ic_on);
                mTvLight26.setTextColor(getResources().getColor(R.color.text_color));

                light26 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight26.setEnabled(true);

                } else {
                    mLight26.setEnabled(false);
                }

                break;

            case idLight27:
                mTvLight27.setText(idGroup);
                mIvLight27.setImageResource(R.drawable.ic_on);
                mTvLight27.setTextColor(getResources().getColor(R.color.text_color));

                light27 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight27.setEnabled(true);

                } else {
                    mLight27.setEnabled(false);
                }

                break;

            case idLight28:
                mTvLight28.setText(idGroup);
                mIvLight28.setImageResource(R.drawable.ic_on);
                mTvLight28.setTextColor(getResources().getColor(R.color.text_color));

                light28 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight28.setEnabled(true);

                } else {
                    mLight28.setEnabled(false);
                }

                break;

            case idLight29:
                mTvLight29.setText(idGroup);
                mIvLight29.setImageResource(R.drawable.ic_on);
                mTvLight29.setTextColor(getResources().getColor(R.color.text_color));

                light29 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight29.setEnabled(true);

                } else {
                    mLight29.setEnabled(false);
                }

                break;

            case idLight30:
                mTvLight30.setText(idGroup);
                mIvLight30.setImageResource(R.drawable.ic_on);
                mTvLight30.setTextColor(getResources().getColor(R.color.text_color));

                light30 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight30.setEnabled(true);

                } else {
                    mLight30.setEnabled(false);
                }

                break;

            case idLight31:
                mTvLight31.setText(idGroup);
                mIvLight31.setImageResource(R.drawable.ic_on);
                mTvLight31.setTextColor(getResources().getColor(R.color.text_color));

                light31 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight31.setEnabled(true);

                } else {
                    mLight31.setEnabled(false);
                }

                break;


            default:
                mTvLight32.setText(idGroup);
                mIvLight32.setImageResource(R.drawable.ic_on);
                mTvLight32.setTextColor(getResources().getColor(R.color.text_color));

                light32 = true;

                if (Integer.parseInt(idGroup) == Integer.parseInt(id_group)) {
                    mLight32.setEnabled(true);
                } else {
                    mLight32.setEnabled(false);
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
