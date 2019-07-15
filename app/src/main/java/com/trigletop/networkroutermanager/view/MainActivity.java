package com.trigletop.networkroutermanager.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.trigletop.networkroutermanager.R;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.SFException;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.BindRet;
import sirouter.sdk.siflower.com.remotelibrary.Listener.RemoteConnectionListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SFObjectResponseListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SiWiFiListListener;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;
import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SFUser mUser;
    private Routers routers;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
        login();
        bindRouter();
        getWifiObserve();
    }

    /**
     * 获取监听Wifi
     */
    private void getWifiObserve() {
        if (mUser == null) {
            return;
        }

        if (routers == null) {
            return;
        }

        progressDialog.show();
        SiWiFiManager.getInstance().getWifiObserve(routers, mUser, new SingleObserver<List<WiFiInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<WiFiInfo> wiFiInfos) {
                progressDialog.dismiss();
                Log.d(TAG, "wifiinfos: " + new Gson().toJson(wiFiInfos));
                Toast.makeText(MainActivity.this, new Gson().toJson(wiFiInfos), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "wifiinfos: " + e.getMessage());
            }
        });
    }

    /**
     * 初始化
     */
    private void init() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        mUser = SFUser.getCacheUser(this);

    }

    /**
     * 初始化View
     */
    private void initView() {

    }

    /**
     * 登录
     */
    private void login() {
        if (mUser != null && !mUser.getLoginkey().equals("")) {
            SFUser.loginByKey(this, mUser.getLoginkey(), new SFObjectResponseListener<SFUser>() {

                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                    MainActivity.this.mUser = sfUser;
                    if (sfUser.getBinder() != null) {
                        if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                        }
                    }

                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                        @Override
                        public void onConnectSuccess() {

                            Log.e(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.e(TAG, "on connection close");
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.e(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "登录失败，请检查AppSecret和AppKey");
                }
            });
        } else {
            SFUser.loginByExtra(this, "999556", new SFObjectResponseListener<SFUser>() {

                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                    MainActivity.this.mUser = sfUser;
                    if (sfUser.getBinder() != null) {
                        if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                        }

                    }
                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                        @Override
                        public void onConnectSuccess() {
                            Log.e(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.e(TAG, "on connection close");
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.e(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "登录失败，请检查AppSecret和AppKey");
                }
            });
        }
    }

    /**
     * 绑定路由器
     */
    private void bindRouter() {
        if (mUser == null) {
            return;
        }

        SiWiFiManager.getInstance().bindSiRouter(MainActivity.this, LocalApi.DEFAULT_APP_API_VERSION, mUser, new SingleObserver<BindRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BindRet bindRet) {
                Log.e(TAG, "bind success ");
                Toast.makeText(MainActivity.this, new Gson().toJson(bindRet), Toast.LENGTH_SHORT).show();
                SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
                    @Override
                    public void onSuccess(List<Routers> objlist) {
                        for (Routers routers : objlist) {
                            if (routers.getObjectId().equals(bindRet.getRouterobjectid())) {
                                MainActivity.this.routers = routers;
                            }
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {

                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "bind error " + e.getMessage());
            }
        });
    }


}
