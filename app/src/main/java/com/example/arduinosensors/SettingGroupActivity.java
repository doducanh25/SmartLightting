package com.example.arduinosensors;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

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
import java.util.List;
import java.util.UUID;

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
    private ImageView mSendApiGroup;

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private static final UUID BTMODULEUUID = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");
    public static ConnectedThreadGroup mSend;
    private String address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_group);

        Intent intent = getIntent();
        address = intent.getStringExtra("device_address");
        Log.d("send",address);

        mListGroup = (ListView) findViewById(R.id.list_group_light);

        mBack = (ImageView) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(SettingGroupActivity.this);
                builder1.setMessage("Bạn có muốn lưu cấu hình nhóm không ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(SettingGroupActivity.this,SettingActivity.class);
                                intent.putExtra("device_address",address);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });

        mSendApiGroup = (ImageView) findViewById(R.id.send_api_group);
        mSendApiGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File fSendGroup = new File(Environment.getExternalStorageDirectory() + "/" + "Group");
                File fileGroup[] = fSendGroup.listFiles();

                if (!fSendGroup.exists()) {
                    fSendGroup.mkdirs();
                } else {
                    String data = "";

                    if (fileGroup.length != 0) {
                        for (int i = 0; i< fileGroup.length;i++){


                            String nameGroup = fileGroup[i].getName().replace("data","").replace("-","");

                            mSend.write(String.valueOf(Integer.parseInt(nameGroup)+200));

                            File fItem = new File(Environment.getExternalStorageDirectory() + "/" + "Group",fileGroup[i].getName());
                            File fContent[] = fItem.listFiles();

                            if (fContent.length!=0) {

                                for (int ii = 0; ii < fContent.length;ii++) {
                                    try {
                                        FileInputStream is = new FileInputStream(new File(fItem+"/"+fContent[ii].getName()));
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                                        if (is != null) {
                                            try {
                                                String[] item = null;
                                                while ((data = reader.readLine()) != null) {

                                                    item = data.split("-");
                                                    String nameLight = item[1];
                                                    Log.d("groupItem",nameLight);
                                                    mSend.write(String.valueOf(Integer.parseInt(nameLight) + 200));


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
                                mSend.write(String.valueOf("-1"));

                            }

                        }

                        final ProgressDialog dialog = new ProgressDialog(SettingGroupActivity.this);

                        // make the progress bar cancelable
                        dialog.setCancelable(true);

                        // set a message text
                        dialog.setMessage("Sending...");

                        // show it
                        dialog.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 2000);

                    } else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingGroupActivity.this);
                        alertDialogBuilder.setMessage("Bạn chưa cài đặt nhóm");

                        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                }

                Intent intent = new Intent(SettingGroupActivity.this,SettingActivity.class);
                intent.putExtra("device_address",address);
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


                mInputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(actionId== EditorInfo.IME_ACTION_DONE){
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

                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        return false;
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


        btAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
            btSocket.connect();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }

        mSend = new ConnectedThreadGroup(btSocket);
        mSend.start();


    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
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

        intent.putExtra("device_address",address);

        startActivity(intent);
    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }


    //create new class for connect thread
    public class ConnectedThreadGroup extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThreadGroup(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {

                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {


            Log.d("ddddd", "Done");
        }

        //write method
        public void write(String input) {
            try {
                mmOutStream.write(Integer.parseInt(input));                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
    }

}
