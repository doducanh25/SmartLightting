package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19/07/2016.
 */
public class Groups {
    private String mNameGroupLight;
    private int id;
    public static List<String> valueSeekBar = new ArrayList<>();

    private Context mContext;
    String filename = "myfile";
    private static FileOutputStream outputStream;
    private String data ="";
    private PrintWriter pw;

    private boolean isSelected = false;
    public Groups(String name,int id,Context context) {
        super();
        this.mNameGroupLight = name;
        this.id = id;
        this.mContext = context;

    }

    public String getmNameGroupLight() {
        return mNameGroupLight;
    }

    public void setmNameGroupLight(String mNameGroupLight) {
        this.mNameGroupLight = mNameGroupLight;
//        Log.d("group_name",mNameGroupLight);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        Log.d("group item",mNameGroupLight + String.valueOf(id));

        data = mNameGroupLight+"-"+String.valueOf(id);

        // get the path to sdcard
        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/DucAnh/");
        dir.mkdir();

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh", DetailScriptActivity.name_script);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        File file = new File(f1, DetailScriptActivity.name_script +"-"+mNameGroupLight+".txt");
        try {
            outputStream = new FileOutputStream(file);
            pw = new PrintWriter(outputStream);
            pw.append(data);
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }


    public static List<String> getValueSeekBar() {
        return valueSeekBar;
    }

    public static void setValueSeekBar(List<String> valueSeekBar) {
        Groups.valueSeekBar = valueSeekBar;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public void toggleSelected () {
        this.isSelected = !isSelected;
    }

}
