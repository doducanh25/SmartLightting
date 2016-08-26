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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by user on 22/07/2016.
 */
public class SettingScriptActivity extends Activity implements ScriptAdapter.OnClickDelete, ScriptAdapter.OnScriptClick {

    private ListView mListScript;

    ScriptAdapter mScriptAdapter;

    private ImageView mBack;
    private ImageView mSendScript;
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

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private static final UUID BTMODULEUUID = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");
    public static ConnectedThreadScript mSendDataScript;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_script);

        Intent intent = getIntent();
        address = intent.getStringExtra("device_address");

        mListScript = (ListView) findViewById(R.id.list_detail_script);

        mBack = (ImageView) findViewById(R.id.back_detail_script);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(SettingScriptActivity.this);
                builder1.setMessage("Bạn có muốn lưu cấu hình kịch bản không ?");
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
                                Intent intent1 = new Intent(SettingScriptActivity.this, SettingActivity.class);
                                intent1.putExtra("device_address", address);
                                startActivity(intent1);
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });

        mSendScript = (ImageView) findViewById(R.id.send_api_script);
        mSendScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;

                while (counter < 2) {

                    File fSendScript = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh");
                    File fileScript[] = fSendScript.listFiles();

                    if (!fSendScript.exists()) {
                        fSendScript.mkdirs();
                    } else {

                        if (fileScript.length != 0) {
                            for (int i = 0; i < fileScript.length; i++) {

                                String nameScript = fileScript[i].getName();

                                mSendDataScript.write(String.valueOf(Integer.parseInt(nameScript) - 1));

                                File fItem = new File(Environment.getExternalStorageDirectory() + "/" + "DucAnh", fileScript[i].getName());
                                File fContent[] = fItem.listFiles();

                                if (fContent.length != 0) {

                                    for (int ii = 0; ii < fContent.length; ii++) {

                                        try {
                                            FileInputStream is = new FileInputStream(new File(fItem + "/" + fContent[ii].getName()));
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                                            if (is != null) {
                                                try {
                                                    String[] item = null;
                                                    String data = "";
                                                    while ((data = reader.readLine()) != null) {

                                                        item = data.split("-");
                                                        String valueDimGroup = item[1];

                                                        mSendDataScript.write(String.valueOf(Integer.parseInt(item[0]) - 1));

                                                        if (Integer.parseInt(valueDimGroup) == 100) {

                                                            mSendDataScript.write("99");

                                                        } else {

                                                            mSendDataScript.write(valueDimGroup);

                                                        }

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
                                    mSendDataScript.write(String.valueOf("-6"));

                                } else {

                                }

                            }

                            final ProgressDialog dialog = new ProgressDialog(SettingScriptActivity.this);

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

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingScriptActivity.this);
                            alertDialogBuilder.setMessage("Bạn chưa cài đặt kịch bản");

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

                    counter++;

                }


                Intent intent1 = new Intent(SettingScriptActivity.this, SettingActivity.class);
                intent1.putExtra("device_address", address);
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


                mInputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
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

                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        return false;
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

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
            btSocket.connect();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }

        mSendDataScript = new ConnectedThreadScript(btSocket);
        mSendDataScript.start();

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
    public void onClickDelete(int postion, String nameScript) {
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
        intent.putExtra("device_address", address);
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
    public class ConnectedThreadScript extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThreadScript(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
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
                mmOutStream.write(Integer.parseInt(input));

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
