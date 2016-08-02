package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/07/2016.
 */
public class SettingScriptActivity extends Activity implements ScriptAdapter.OnClickDelete, ScriptAdapter.OnScriptClick {

    private ListView mListScript;

    ScriptAdapter mScriptAdapter;

    private ImageView mBack;
    private static List<Scripts> scriptsList;
    private String path;
    private PrintWriter pw;

    private File fileData;
    private File newFile;
    private String sData = "";
    private String sNewData = "";
    private static FileOutputStream outputStream;
    private File[] file;

    private File f1;

    private File[] file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_script);

        Intent intent = getIntent();
        final String address = intent.getStringExtra("device_address");

        mListScript = (ListView) findViewById(R.id.list_detail_script);

        mBack = (ImageView) findViewById(R.id.back_detail_script);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SettingScriptActivity.this,SettingActivity.class);
                intent1.putExtra("device_address",address);
                startActivity(intent1);
            }
        });

        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_list_script, null, false);
        mListScript.addFooterView(footerView);

        final RelativeLayout mAddGroupNew = (RelativeLayout) footerView.findViewById(R.id.layout_add_script_new);

        final LinearLayout mItemGroup = (LinearLayout) footerView.findViewById(R.id.ll_item_light);

        final EditText mInputName = (EditText) footerView.findViewById(R.id.input_name_script);
        final ImageView mOK = (ImageView) footerView.findViewById(R.id.iv_next);

        mScriptAdapter = new ScriptAdapter(this);

        mScriptAdapter.setOnClickDelete(this);
        mScriptAdapter.setOnScriptClick(this);

        loadArray(SettingScriptActivity.this, "key");

        SettingGroupActivity.loadArray(SettingScriptActivity.this, "key");


        path = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

        File f = new File(path);
        file = f.listFiles();

        mAddGroupNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddGroupNew.setVisibility(View.GONE);
                mItemGroup.setVisibility(View.VISIBLE);

                mOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mInputName.getText().toString() != null && !mInputName.getText().toString().trim().isEmpty()) {
                            scriptsList.add(new Scripts(mInputName.getText().toString(), scriptsList.size() + 1));
                        }
                        mAddGroupNew.setVisibility(View.VISIBLE);
                        mItemGroup.setVisibility(View.GONE);
                        mScriptAdapter.setScripts(scriptsList);
                        mListScript.setAdapter(mScriptAdapter);
                        saveArray(SettingScriptActivity.this, scriptsList, "key_script");

                        SettingGroupActivity.loadArray(SettingScriptActivity.this, "key");

                        f1 = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh", mInputName.getText().toString());
                        if (!f1.exists()) {
                            f1.mkdirs();
                        }

                        file1 = f1.listFiles();

                        if (file1.length == 0) {
                            for (int e = 0; e < SettingGroupActivity.groupsList.size(); e++) {
                                fileData = new File(f1,
                                        mInputName.getText().toString().trim() + "-" + SettingGroupActivity.groupsList.get(e).getmNameGroupLight() + ".txt");
                                sData = SettingGroupActivity.groupsList.get(e).getmNameGroupLight() + "-" + "50";
                                try {
                                    outputStream = new FileOutputStream(fileData);
                                    pw = new PrintWriter(outputStream);
                                    pw.append(sData);
                                    pw.flush();
                                    pw.close();

                                } catch (FileNotFoundException c) {
                                    c.printStackTrace();
                                }
                            }
                        }

                    }
                });

                mScriptAdapter.setScripts(scriptsList);
                mListScript.setAdapter(mScriptAdapter);
                saveArray(SettingScriptActivity.this, scriptsList, "key_script");

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadArray(this, "key_script");

        mScriptAdapter.setScripts(scriptsList);
        mListScript.setAdapter(mScriptAdapter);
    }


    public static boolean saveArray(Context context, List<Scripts> scriptses, String nameArray) {
        SharedPreferences sp = context.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor mEdit1 = sp.edit();
    /* sKey is an array */
        mEdit1.putInt(nameArray + "_size", scriptses.size());

        for (int i = 0; i < scriptses.size(); i++) {
            mEdit1.remove(nameArray + "_" + i);
            mEdit1.putString(nameArray + "_" + i, scriptses.get(i).getmNameScript());
        }

        return mEdit1.commit();
    }

    //
    public static void loadArray(Context mContext, String nameArray) {
        SharedPreferences mSharedPreference1 = mContext.getSharedPreferences("preferencename", 0);
//        groupsList.clear();
        int size = mSharedPreference1.getInt(nameArray + "_size", 0);

        scriptsList = new ArrayList<>();

        for (int a = 0; a < size; a++) {
            scriptsList.add(new Scripts(mSharedPreference1.getString(nameArray + "_" + a, null), a));
        }

    }

    @Override
    public void onClickDelete(int postion,String nameScript) {
        scriptsList.remove(postion);

        mScriptAdapter.notifyDataSetChanged();

        mScriptAdapter.setScripts(scriptsList);
        mListScript.setAdapter(mScriptAdapter);
        saveArray(SettingScriptActivity.this, scriptsList, "key_script");

        path = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

        File fDeleteScript = new File(path);

        if (fDeleteScript.length() != 0) {

            File fileDelete[] = fDeleteScript.listFiles();

            for (int a = 0; a < fileDelete.length; a++) {

                if (fileDelete[a].getName().equals(nameScript)) {
                    deleteRecursive(fileDelete[a]);
                }
            }
        }

    }

    @Override
    public void onScriptClick(int postion) {
        Intent intent = new Intent(this, DetailScriptActivity.class);
        String value = scriptsList.get(postion).getmNameScript();
        intent.putExtra("name_script", value);
        startActivity(intent);
    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }
}
