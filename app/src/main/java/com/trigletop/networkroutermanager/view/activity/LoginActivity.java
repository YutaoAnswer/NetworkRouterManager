package com.trigletop.networkroutermanager.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trigletop.networkroutermanager.R;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.SFException;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SFObjectResponseListener;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;

/**
 * 登录页
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * 通过extra登录
     * loginExtra为找回帐号的唯一信息(可以是手机号、邮箱等信息）。登录成功后返回SFUser对象
     *
     * @param context 上下文
     * @param extra
     */
    private void loginByExtra(Context context, String extra) {
        SFUser.loginByExtra(context, extra, new SFObjectResponseListener<SFUser>() {
            @Override
            public void onSuccess(SFUser sfUser) {

            }

            @Override
            public void onError(SFException e) {

            }
        });
    }


    /**
     * 通过loginKey登录
     * loginKey为用户生成后用于登录的唯一信息，app端进行保存。
     *
     * @param context   上下文
     * @param sfUser    SFUser实例化对象
     */
    private void loginByLoginKey(Context context, SFUser sfUser) {
        SFUser.loginByKey(context, sfUser.getLoginkey(), new SFObjectResponseListener<SFUser>() {
            @Override
            public void onSuccess(SFUser sfUser) {

            }

            @Override
            public void onError(SFException e) {

            }
        });
    }

}
