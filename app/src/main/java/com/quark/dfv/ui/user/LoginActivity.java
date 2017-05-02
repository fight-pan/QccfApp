package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quark.dfv.R;
import com.quark.dfv.base.BaseActivity;
import com.quark.dfv.mainview.MainActivity;
import com.quark.dfv.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by pan on 2016/9/9 0009.
 * >#
 * >#登录
 */
public class LoginActivity extends BaseActivity {

    @InjectView(R.id.login_bt)
    Button loginBt;
    @InjectView(R.id.forgot_pwd)
    TextView forgotPwd;
    @InjectView(R.id.register_now)
    TextView registerNow;
    @InjectView(R.id.close_eye_ibt)
    ImageButton closeEyeIbt;
    @InjectView(R.id.pwd_et)
    EditText pwdEt;
    @InjectView(R.id.user_et)
    EditText userEt;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.right)
    LinearLayout right;

    String telephone;
    String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
//        new AppParam().setSharedPreferencesy(LoginActivity.this, "token", "");
//        new AppParam().setSharedPreferencesy(LoginActivity.this, "isLogin", "0");
//        userEt.setText(new AppParam().getTelephone(this));
        setTitle("登录");
        sign.setText("注册");
        sign.setTextColor(ContextCompat.getColor(this, R.color.white));
        right.setVisibility(View.VISIBLE);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    boolean showpssword = false;

    @OnClick({R.id.left,R.id.login_bt, R.id.forgot_pwd, R.id.right, R.id.close_eye_ibt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.login_bt:
//                if (check()) {
////                    loginRequest();
//                }
                startActivityByClass(MainActivity.class);
                finish();
                break;
            case R.id.forgot_pwd:
                startActivityByClass(ForgetPwdActivity.class);
                break;
            case R.id.right:
                startActivityByClass(RegisterActivity.class);
                break;
            case R.id.terms_tv:

                break;
            case R.id.close_eye_ibt:
                if (!showpssword) {
                    // 设置为明文显示
                    showpssword = true;
                    pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    closeEyeIbt.setBackground(ContextCompat.getDrawable(this, R.drawable.open_eye));
                } else {
                    // 设置为秘闻显示
                    showpssword = false;
                    pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    closeEyeIbt.setBackground(ContextCompat.getDrawable(this, R.drawable.close_eye));
                }
                // 切换后将EditText光标置于末尾
                CharSequence charSequence = pwdEt.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                break;
        }
    }

    private boolean check() {
        telephone = userEt.getText().toString();
        pwd = pwdEt.getText().toString();
        if (Utils.isEmpty(telephone)) {
            showToast("请输入手机号");
            return false;
        }
        if (Utils.isEmpty(pwd)) {
            showToast("请输入密码");
            return false;
        }
        return true;
    }

}
