package com.example.arduinosensors;

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
import java.util.StringTokenizer;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScriptMainActivity extends Activity {

    Button btnLevelOne, btnLevelTwo;
    Button btnLevelThree;
    Button btnLevelFour;
    Handler bluetoothIn;

    private ImageView mTurnDimScrip;
    SeekBar mSeekBar;
    Button mDisplayValueDim;

    private LinearLayout mLayoutScript1vs2;
    private LinearLayout mLayoutScript3vs4;

    private Button mLogin;

    final int handlerState = 0;                         //used to identify handler message
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();

    public static ConnectedThread mConnectedThread;

    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");

    // String for MAC address
    private static String address;

    private boolean isChoose = false;

    private static FileOutputStream outputStreamDim;
    private PrintWriter pwDim;

    private String path;
    private String dataDim = "0";

    private String pathScript;
    private String pathSaveScript;

    private static  final String script1 = "0";
    private static  final String script2 = "1";
    private static  final String script3 = "2";
    private static  final String script4 = "3";

    private File fileData;
    private static FileOutputStream outputStream;
    private PrintWriter pw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pathScript = Environment.getExternalStorageDirectory().toString() + "/DucAnh";

        File folder = new File(pathScript);
        File[] item = folder.listFiles();
        if (item.length ==0) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Chào bạn!" + "\n"+ "Hãy đăng nhập để sử dụng ứng dụng");

            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        File sdcard = Environment.getExternalStorageDirectory();
        File fSave = new File(sdcard.getAbsolutePath() + "/SaveScript/");

        if (!fSave.exists()) {
            fSave.mkdir();
        }

        fileData = new File(fSave, "savescript" + ".txt");
        if(!fileData.exists()) {
            try {
                fileData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        btnLevelOne = (Button) findViewById(R.id.btnLevelOne);
        btnLevelTwo = (Button) findViewById(R.id.btnLevelTwo);
        btnLevelThree = (Button) findViewById(R.id.btnLevelThree);
        btnLevelFour = (Button) findViewById(R.id.btnLevelFour);

        mTurnDimScrip = (ImageView) findViewById(R.id.turn_dim_script);

        mSeekBar = (SeekBar) findViewById(R.id.sb_choose_script_dim);

        mDisplayValueDim = (Button) findViewById(R.id.display_result_choose_script_dim);

        mLayoutScript1vs2 = (LinearLayout) findViewById(R.id.layout_scrip_1_vs_2);
        mLayoutScript3vs4 = (LinearLayout) findViewById(R.id.layout_scrip_3_vs_4);

        mLogin = (Button) findViewById(R.id.login_button);

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

        // Set up onClick listeners for buttons to send 1 or 0 to turn on/off LED
        btnLevelOne.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("-2");    // Send "0" via Bluetooth
                Toast.makeText(getBaseContext(), "Turn On Level One", Toast.LENGTH_SHORT).show();
                btnLevelOne.setBackgroundResource(R.color.background_color_button_script_main_screen);
                btnLevelThree.setBackgroundResource(R.color.background_button_script);
                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                btnLevelFour.setBackgroundResource(R.color.background_button_script);


                try {
                    outputStream = new FileOutputStream(fileData);
                    pw = new PrintWriter(outputStream);
                    pw.append(String.valueOf(0));
                    pw.flush();
                    pw.close();

                } catch (FileNotFoundException c) {
                    c.printStackTrace();
                }

            }
        });

        btnLevelTwo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mConnectedThread.write("-3");    // Send "1" via Bluetooth
                Toast.makeText(getBaseContext(), "Turn On Level Two", Toast.LENGTH_SHORT).show();
                btnLevelTwo.setBackgroundResource(R.color.background_color_button_script_main_screen);
                btnLevelOne.setBackgroundResource(R.color.background_button_script);
                btnLevelThree.setBackgroundResource(R.color.background_button_script);
                btnLevelFour.setBackgroundResource(R.color.background_button_script);

                try {
                    outputStream = new FileOutputStream(fileData);
                    pw = new PrintWriter(outputStream);
                    pw.append(String.valueOf(1));
                    pw.flush();
                    pw.close();

                } catch (FileNotFoundException c) {
                    c.printStackTrace();
                }

            }
        });

        btnLevelThree.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mConnectedThread.write("-4");
                Toast.makeText(getBaseContext(), "Turn On Level Three", Toast.LENGTH_SHORT).show();
                btnLevelThree.setBackgroundResource(R.color.background_color_button_script_main_screen);
                btnLevelOne.setBackgroundResource(R.color.background_button_script);
                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                btnLevelFour.setBackgroundResource(R.color.background_button_script);

                try {
                    outputStream = new FileOutputStream(fileData);
                    pw = new PrintWriter(outputStream);
                    pw.append(String.valueOf(2));
                    pw.flush();
                    pw.close();

                } catch (FileNotFoundException c) {
                    c.printStackTrace();
                }
            }
        });

        btnLevelFour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mConnectedThread.write("-5");
                Toast.makeText(getBaseContext(), "Turn On Level Four", Toast.LENGTH_SHORT).show();

                btnLevelFour.setBackgroundResource(R.color.background_color_button_script_main_screen);
                btnLevelThree.setBackgroundResource(R.color.background_button_script);
                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                btnLevelOne.setBackgroundResource(R.color.background_button_script);

                try {
                    outputStream = new FileOutputStream(fileData);
                    pw = new PrintWriter(outputStream);
                    pw.append(String.valueOf(3));
                    pw.flush();
                    pw.close();

                } catch (FileNotFoundException c) {
                    c.printStackTrace();
                }
            }
        });

        mTurnDimScrip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isChoose) {
                    mTurnDimScrip.setImageResource(R.drawable.switch_on);
                    mSeekBar.setVisibility(View.VISIBLE);
                    mDisplayValueDim.setVisibility(View.VISIBLE);

                    mLayoutScript1vs2.setAlpha(0.2f);
                    mLayoutScript3vs4.setAlpha(0.2f);

                    btnLevelOne.setEnabled(false);
                    btnLevelTwo.setEnabled(false);
                    btnLevelThree.setEnabled(false);
                    btnLevelFour.setEnabled(false);


                    String path = Environment.getExternalStorageDirectory().toString() + "/DimLight";
                    Log.d("Files", "Path: " + path);
                    File fDim = new File(path);
                    File fileDim[] = fDim.listFiles();
                    String valueDim = "0";

                    if (fileDim.length != 0) {
                        for (int i = 0; i < fileDim.length; i++) {
                            try {
                                FileInputStream is = new FileInputStream(new File(fDim + "/" + fileDim[i].getName()));
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                                if (is != null) {
                                    try {
                                        while ((valueDim = reader.readLine()) != null) {
                                            mConnectedThread.write(String.valueOf(Integer.parseInt(valueDim) + 100));
                                            Toast.makeText(getBaseContext(), "DIM"+ "-" + valueDim, Toast.LENGTH_SHORT).show();

                                        }
                                        is.close();
                                    } catch (IOException a) {
                                        // TODO Auto-generated catch block
                                        a.printStackTrace();
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                Log.e("e", "File not found: " + e.toString());
                            }
                        }
                    } else {
                        mConnectedThread.write("100");
                    }

                } else {
                    mTurnDimScrip.setImageResource(R.drawable.switch_off);
                    mSeekBar.setVisibility(View.INVISIBLE);
                    mDisplayValueDim.setVisibility(View.INVISIBLE);

                    mLayoutScript1vs2.setAlpha(1f);
                    mLayoutScript3vs4.setAlpha(1f);

                    btnLevelOne.setEnabled(true);
                    btnLevelTwo.setEnabled(true);
                    btnLevelThree.setEnabled(true);
                    btnLevelFour.setEnabled(true);

                    String pathReadFile = Environment.getExternalStorageDirectory().toString() + "/SaveScript";

                    File fReadFile = new File(pathReadFile);
                    File fileList[] = fReadFile.listFiles();

                    String dataScriptFile = "0";

                    if (fileList.length != 0) {

                        for (int i = 0; i < fileList.length; i++) {
                            try {
                                FileInputStream isFile = new FileInputStream(new File(fReadFile + "/" + fileList[i].getName()));
                                BufferedReader readerFile = new BufferedReader(new InputStreamReader(isFile));
                                if (isFile != null) {
                                    try {
                                        while ((dataScriptFile = readerFile.readLine()) != null) {

//                                            String[] item = dataScript.split("-");
//                                            byte ptext[] = item[1].getBytes();
//                                            String value = new String(ptext, "UTF-8");


                                            switch (dataScriptFile){
                                                case script1:
                                                    mConnectedThread.write("-2");
                                                    Toast.makeText(getBaseContext(), "Send"+ "-" + dataScriptFile, Toast.LENGTH_SHORT).show();
                                                    break;
                                                case script2:
                                                    mConnectedThread.write("-3");
                                                    Toast.makeText(getBaseContext(), "Send"+ "-" + dataScriptFile, Toast.LENGTH_SHORT).show();
                                                    break;
                                                case script3:
                                                    mConnectedThread.write("-4");
                                                    Toast.makeText(getBaseContext(), "Send"+ "-" + dataScriptFile, Toast.LENGTH_SHORT).show();
                                                    break;
                                                default :
                                                    mConnectedThread.write("-5");
                                                    Toast.makeText(getBaseContext(), "Send"+ "-" + dataScriptFile, Toast.LENGTH_SHORT).show();
                                                    break;
                                            }
//
                                            if (dataScriptFile.contains("0")) {

                                                btnLevelOne.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                                btnLevelThree.setBackgroundResource(R.color.background_button_script);
                                                btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                            } else if (dataScriptFile.contains("1")) {

                                                btnLevelTwo.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                                btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                                btnLevelThree.setBackgroundResource(R.color.background_button_script);
                                                btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                            } else if (dataScriptFile.contains("2")) {

                                                btnLevelThree.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                                btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                                btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                            } else if (dataScriptFile.contains("3")) {

                                                btnLevelFour.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                                btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                                btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                                btnLevelThree.setBackgroundResource(R.color.background_button_script);

                                            }

                                        }
                                        isFile.close();
                                    } catch (IOException ee) {
                                        // TODO Auto-generated catch block
                                        ee.printStackTrace();
                                    }
                                }
                            } catch (FileNotFoundException eee) {
                                Log.e("e", "File not found: " + eee.toString());
                            }
                        }
                    }

                }

                isChoose = !isChoose;

            }
        });




        path = Environment.getExternalStorageDirectory().toString() + "/DimLight";
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();

        if (file.length != 0) {

            for (int i = 0; i < file.length; i++) {
                try {
                    FileInputStream is = new FileInputStream(new File(f + "/" + file[i].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((dataDim = reader.readLine()) != null) {

                                mDisplayValueDim.setText(dataDim + "%");
                                mSeekBar.setProgress(Integer.parseInt(dataDim));

                                mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    int progressBar = Integer.parseInt(dataDim);

                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        progressBar = progress;
                                        mDisplayValueDim.setText(progressBar + "%");
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        mDisplayValueDim.setText(progressBar + "%");

                                        File sdcard = Environment.getExternalStorageDirectory();
                                        File dir = new File(sdcard.getAbsolutePath() + "/DimLight/");

                                        if (!dir.exists()) {
                                            dir.mkdir();
                                        }

                                        File file = new File(dir, "dim.txt");
                                        try {
                                            outputStreamDim = new FileOutputStream(file);
                                            pwDim = new PrintWriter(outputStreamDim);
                                            pwDim.append(String.valueOf(progressBar));
                                            pwDim.flush();
                                            pwDim.close();

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }

                                        mConnectedThread.write(String.valueOf(progressBar + 100));
                                        Toast.makeText(getBaseContext(),"DIM" +"-"+ String.valueOf(progressBar + 100), Toast.LENGTH_SHORT).show();

                                    }
                                });

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

        } else {
            mDisplayValueDim.setText("0%");
            mSeekBar.setProgress(0);

            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressBar = 0;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressBar = progress;
                    mDisplayValueDim.setText(progressBar + "%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mDisplayValueDim.setText(progressBar + "%");

                    mConnectedThread.write(String.valueOf(progressBar + 100));

                    File sdcard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdcard.getAbsolutePath() + "/DimLight/");

                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File file = new File(dir, "dim.txt");
                    try {
                        outputStreamDim = new FileOutputStream(file);
                        pwDim = new PrintWriter(outputStreamDim);
                        pwDim.append(String.valueOf(progressBar));
                        pwDim.flush();
                        pwDim.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });

        }


        mLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScriptMainActivity.this, LoginScreenActivity.class);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });


        File fScript = new File(pathScript,"1");
        if (fScript.exists()) {
            btnLevelOne.setVisibility(View.VISIBLE);
        } else {
            btnLevelOne.setVisibility(View.INVISIBLE);
        }

        File fScript2 = new File(pathScript,"2");
        if (fScript2.exists()) {
            btnLevelTwo.setVisibility(View.VISIBLE);
        } else {
            btnLevelTwo.setVisibility(View.INVISIBLE);
        }

        File fScript3 = new File(pathScript,"3");
        if (fScript3.exists()) {
            btnLevelThree.setVisibility(View.VISIBLE);
        } else {
            btnLevelThree.setVisibility(View.INVISIBLE);
        }

        File fScript4 = new File(pathScript,"4");
        if (fScript4.exists()) {
            btnLevelFour.setVisibility(View.VISIBLE);
        } else {
            btnLevelFour.setVisibility(View.INVISIBLE);
        }

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
            btSocket.connect();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();


        String pathReadFile = Environment.getExternalStorageDirectory().toString() + "/SaveScript";

        File fReadFile = new File(pathReadFile);
        File fileList[] = fReadFile.listFiles();

        String dataScript = "0";

        if (fileList.length != 0) {

            for (int i = 0; i < fileList.length; i++) {
                try {
                    FileInputStream is = new FileInputStream(new File(fReadFile + "/" + fileList[i].getName()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    if (is != null) {
                        try {
                            while ((dataScript = reader.readLine()) != null) {

                                switch (dataScript){
                                    case script1:
                                        mConnectedThread.write("-2");

                                        break;
                                    case script2:
                                        mConnectedThread.write("-3");

                                        break;
                                    case script3:
                                        mConnectedThread.write("-4");

                                        break;
                                    default :
                                        mConnectedThread.write("-5");

                                        break;
                                }
//
                                if (dataScript.contains("0")) {

                                    btnLevelOne.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                    btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                    btnLevelThree.setBackgroundResource(R.color.background_button_script);
                                    btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                } else if (dataScript.contains("1")) {

                                    btnLevelTwo.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                    btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                    btnLevelThree.setBackgroundResource(R.color.background_button_script);
                                    btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                } else if (dataScript.contains("2")) {

                                    btnLevelThree.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                    btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                    btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                    btnLevelFour.setBackgroundResource(R.color.background_button_script);

                                } else if (dataScript.contains("3")) {

                                    btnLevelFour.setBackgroundResource(R.color.background_color_button_script_main_screen);
                                    btnLevelOne.setBackgroundResource(R.color.background_button_script);
                                    btnLevelTwo.setBackgroundResource(R.color.background_button_script);
                                    btnLevelThree.setBackgroundResource(R.color.background_button_script);

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
        }



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


    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }


    //create new class for connect thread
    public class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
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
//            byte[] buffer = new byte[256];
//            int bytes;
//
//            // Keep looping to listen for received messages
//            while (true) {
//                try {
//                    bytes = mmInStream.read(buffer);        	//read bytes from input buffer
//                    String readMessage = new String(buffer, 0, bytes);
//                    // Send the obtained bytes to the UI Activity via handler
//                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
//                } catch (IOException e) {
//                    break;
//                }
//            }


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
}
    
