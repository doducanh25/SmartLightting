package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19/07/2016.
 */
public class SettingGroupActivity extends Activity implements GroupLightAdapter.OnClickDelete, GroupLightAdapter.OnGroupClick {

    private ListView mListGroup;

    GroupLightAdapter mGroupAdapter;

    private static List<Groups> groups;
    public static List<Groups> groupsList;

    private static List<Integer> listPostion = new ArrayList<>();

    private String path;
    private static FileOutputStream outputStream;
    private PrintWriter pw;

    private ImageView mBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_group);

        mListGroup = (ListView) findViewById(R.id.list_group_light);

        mBack = (ImageView) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingGroupActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_list_group, null, false);
        mListGroup.addFooterView(footerView);


        final RelativeLayout mAddGroupNew = (RelativeLayout) footerView.findViewById(R.id.layout_add_group_new);

        final LinearLayout mItemGroup = (LinearLayout) footerView.findViewById(R.id.ll_item_light);

        final EditText mInputName = (EditText) footerView.findViewById(R.id.input_name_group);
        final ImageView mOK = (ImageView) footerView.findViewById(R.id.iv_next);

        groups = new ArrayList<>();

        mGroupAdapter = new GroupLightAdapter(this);

        mGroupAdapter.setOnClickDelete(this);
        mGroupAdapter.setOnGroupClick(this);

        loadArray(SettingGroupActivity.this, "key");

        mAddGroupNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddGroupNew.setVisibility(View.GONE);
                mItemGroup.setVisibility(View.VISIBLE);

                mOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (mInputName.getText().toString() != null && !mInputName.getText().toString().trim().isEmpty()) {
                            groupsList.add(new Groups(mInputName.getText().toString(), groupsList.size() + 1,SettingGroupActivity.this));

                        }
                        mAddGroupNew.setVisibility(View.VISIBLE);
                        mItemGroup.setVisibility(View.GONE);
                        mGroupAdapter.setLight(groupsList);
                        mListGroup.setAdapter(mGroupAdapter);
                        saveArray(SettingGroupActivity.this, groupsList, "key");

                        path = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

                        File f = new File(path);

                        if (f.length() != 0) {

                            File file[] = f.listFiles();

                            for (int a = 0; a<file.length;a++) {

                                File f1 = new File(path,file[a].getName());

                                String nameScrip = f1.getName();

                                String[] nameSplitScript = nameScrip.split("-");

                                File fileData = new File(f1,
                                        nameSplitScript[0] + "-" + mInputName.getText().toString() + ".txt");
                                String sData = mInputName.getText().toString() + "-" + "50";

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

                mGroupAdapter.setLight(groupsList);
                mListGroup.setAdapter(mGroupAdapter);
                saveArray(SettingGroupActivity.this, groupsList, "key");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadArray(this, "key");

        mGroupAdapter.setLight(groupsList);
        mListGroup.setAdapter(mGroupAdapter);
    }

    public static boolean saveArray(Context context, List<Groups> groupses, String nameArray) {
        SharedPreferences sp = context.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor mEdit1 = sp.edit();
    /* sKey is an array */
        mEdit1.putInt(nameArray + "_size", groupses.size());

        for (int i = 0; i < groupses.size(); i++) {
            mEdit1.remove(nameArray + "_" + i);
            Log.d("abcd", groupses.get(i).getmNameGroupLight());
            mEdit1.putString(nameArray + "_" + i, groupses.get(i).getmNameGroupLight());
        }

        return mEdit1.commit();
    }

    //
    public static void loadArray(Context mContext, String nameArray) {
        SharedPreferences mSharedPreference1 = mContext.getSharedPreferences("preferencename", 0);
//        groupsList.clear();
        int size = mSharedPreference1.getInt(nameArray + "_size", 0);

        Log.d("aaa", String.valueOf(size));

        groupsList = new ArrayList<>();

        for (int a = 0; a < size; a++) {
            groupsList.add(new Groups(mSharedPreference1.getString(nameArray + "_" + a, null), a,mContext));
        }

    }

    @Override
    public void onClickDelete(int postion,String nameLight) {

        groupsList.remove(postion);
        mGroupAdapter.notifyDataSetChanged();

        listPostion.add(postion);


        mGroupAdapter.setLight(groupsList);
        mListGroup.setAdapter(mGroupAdapter);

        mListGroup.invalidateViews();

        saveArray(SettingGroupActivity.this, groupsList, "key");

        path = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

        File fDelete = new File(path);

        if (fDelete.length() != 0) {

            File fileDelete[] = fDelete.listFiles();

            for (int a = 0; a < fileDelete.length; a++) {

                File f1Delete = new File(path, fileDelete[a].getName());

                File[] nameDelete = f1Delete.listFiles();

                for (int aaa = 0; aaa < nameDelete.length;aaa++){
                    if (nameDelete[aaa].getName().contains("-"+ nameLight +".txt")){
                        nameDelete[aaa].delete();
                    }
                }

            }
        }

        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + "Group");
        File fileGroup[] = f1.listFiles();

        if (fileGroup.length > 0) {
            for (int i=0; i < fileGroup.length; i++) {
                if (fileGroup[i].getName().contains("data"+ nameLight + "-")) {
                        deleteRecursive(fileGroup[i]);
                }
            }
        }

    }

    @Override
    public void onGroupClick(int idGroup) {

        Intent intent = new Intent(SettingGroupActivity.this, DetailGroupLightActivity.class);

        String value = groupsList.get(idGroup).getmNameGroupLight();

        intent.putExtra("id", value);

        startActivity(intent);
    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }
}
