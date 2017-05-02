package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.quark.dfv.R;
import com.quark.dfv.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by pan on 2016/9/9 0009.
 * >#
 * >#找回密码
 */
public class ForgetPwdActivity extends BaseActivity {

    String telephone;
    String code;
    String pwd;
    Handler handlercode;
    @InjectView(R.id.user_et)
    EditText userEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd);
        ButterKnife.inject(this);

        handlercode = new Handler();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    boolean showpssword = false;

//    @OnClick({R.id.go_code, R.id.close_eye_ibt,R.id.edit_bt})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.go_code:
//                telephone = userEt.getText().toString();
//                if (Utils.isEmpty(userEt.getText().toString())) {
//                    showToast("请输入手机号");
//                } else {
////                    getcodeRequest();
//                }
//                break;
////            case R.id.close_eye_ibt:
//                if (!showpssword) {
//                    // 设置为明文显示
//                    showpssword = true;
//                    pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    closeEyeIbt.setBackground(ContextCompat.getDrawable(this, R.drawable.open_eye));
//                } else {
//                    // 设置为秘闻显示
//                    showpssword = false;
//                    pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    closeEyeIbt.setBackground(ContextCompat.getDrawable(this, R.drawable.close_eye));
//                }
//                // 切换后将EditText光标置于末尾
//                CharSequence charSequence = pwdEt.getText();
//                if (charSequence instanceof Spannable) {
//                    Spannable spanText = (Spannable) charSequence;
//                    Selection.setSelection(spanText, charSequence.length());
//                }
//                break;
//        }
//    }

//    private boolean check() {
//        telephone = userEt.getText().toString();
//        code = codeEt.getText().toString();
//        pwd = pwdEt.getText().toString();
//        if (Utils.isEmpty(telephone)) {
//            showToast("请输入手机号");
//            return false;
//        }
//        if (Utils.isEmpty(code)) {
//            showToast("请输入验证码");
//            return false;
//        }
//        if (Utils.isEmpty(pwd)) {
//            showToast("请输入密码");
//            return false;
//        }
//        return true;
//    }



}
