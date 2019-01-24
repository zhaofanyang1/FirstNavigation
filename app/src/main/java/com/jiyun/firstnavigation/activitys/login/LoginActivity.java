package com.jiyun.firstnavigation.activitys.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.activitys.login.Login2Activity;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private ImageView mIdShut;
    private EditText mIdPhoneNumber;
    private EditText mIdVerificationNumber;
    private TextView mGetYanzhengma;
    private ImageView im;
    private CheckBox checkbox;
    private ImageView mIdWichat;
    private ImageView mIdQq;
    private ImageView mIdMicroblog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        mIdShut = findViewById(R.id.id_shut);
        mIdPhoneNumber = findViewById(R.id.id_phone_number);
        mIdVerificationNumber = findViewById(R.id.id_verification_number);
        mGetYanzhengma = findViewById(R.id.get_yanzhengma);
        im = findViewById(R.id.login_btn);
        checkbox = findViewById(R.id.checkbox);
        mIdWichat = findViewById(R.id.id_wichat);
        mIdQq = findViewById(R.id.id_qq);
        mIdMicroblog = findViewById(R.id.id_microblog);

        mIdPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changimage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIdVerificationNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changimage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIdShut.setOnClickListener(this);
        checkbox.setOnCheckedChangeListener(this);
        mGetYanzhengma.setOnClickListener(this);
        im.setOnClickListener(this);
        mIdWichat.setOnClickListener(this);
        mIdQq.setOnClickListener(this);
        mIdMicroblog.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_shut:
                Toast.makeText(this, "shut", Toast.LENGTH_SHORT).show();
                break;
            case R.id.get_yanzhengma:
                mIdVerificationNumber.setText("121389");
                break;
            case R.id.id_wichat:
                Toast.makeText(this, "使用微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_qq:
                Toast.makeText(this, "使用QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_microblog:
                Toast.makeText(this, "使用微博登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_btn:
                String s = mIdPhoneNumber.getText().toString();
                if (s.matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$") && s != null && checkbox.isChecked()) {
                    changimage();
                    startActivity(new Intent(this, Login2Activity.class));
                } else if (s == null) {
                    Toast.makeText(this, "请输入您的手机号！", Toast.LENGTH_SHORT).show();
                } else if (!s.matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$")) {
                    Toast.makeText(this, "您输入的手机号有误，请重新输入！", Toast.LENGTH_SHORT).show();
                } else if (!checkbox.isChecked()) {
                    Toast.makeText(this, "请阅读并同意“用户协议”", Toast.LENGTH_SHORT).show();
                }
              //  Log.e("LoginActivity", ("测试bugly结果" + 2 / 0));
               // CrashReport.testJavaCrash();

                break;
        }
    }


    private void changimage() {
        if (mIdPhoneNumber.getText().toString().matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$") && mIdVerificationNumber.getText().toString().matches("^\\d{6}$") && checkbox.isChecked()) {
            im.setImageResource(R.mipmap.btn_log_red);
            im.setEnabled(true);
        } else {
            im.setImageResource(R.mipmap.btn_login);
            im.setEnabled(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (checkbox.isChecked()) {
            changimage();
        }
    }
}
