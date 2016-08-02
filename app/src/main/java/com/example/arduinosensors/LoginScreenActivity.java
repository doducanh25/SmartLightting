package com.example.arduinosensors;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 18/07/2016.
 */
public class LoginScreenActivity extends Activity {

    private ImageView mBack;
    private EditText mInputPassWord;
    private Button mOK;

    private String address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setupUI(findViewById(R.id.layout_login));

        Intent intent = getIntent();
        address = intent.getStringExtra("address");

        mBack = (ImageView) findViewById(R.id.back_login_screen);
        mBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent inte = new Intent(LoginScreenActivity.this, ScriptMainActivity.class);
               inte.putExtra("device_address", address);
               startActivity(inte);
           }
       });

        mInputPassWord = (EditText) findViewById(R.id.input_pass_login);

        mOK = (Button) findViewById(R.id.accept_pass_login);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = mInputPassWord.getText().toString();

                if (input.contains("1234")) {
                    Intent int_send_setting = new Intent(LoginScreenActivity.this,SettingActivity.class);
                    int_send_setting.putExtra("device_address", address);
                    startActivity(int_send_setting);
                } else {
                    final Dialog dialog = new Dialog(LoginScreenActivity.this);
                    dialog.setContentView(R.layout.dialog_notification_error_login);
                    dialog.setTitle("Thông Báo");

                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
            }
        });

        final TextView tvLinkHome = (TextView) findViewById(R.id.link);
        tvLinkHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLinkHome.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (v != findViewById(R.id.accept_pass_login)) {
                        hideSoftKeyboard(LoginScreenActivity.this);
                    }
                    return false;
                }

            });
        }

    }


}
