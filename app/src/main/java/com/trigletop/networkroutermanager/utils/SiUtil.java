package com.trigletop.networkroutermanager.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.DevicesAdapter;

import java.util.ArrayList;
import java.util.List;

import app.com.tvrecyclerview.TvRecyclerView;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.IFace;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSScanInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.SFException;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.ACOtaCheckParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.BindParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.CheckNetParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.CommandParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.DeleteDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetBindInfoParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetClientMacParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetFreqIntergrationParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetLeaseNetParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetQosParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetRouterApiVersionParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetStokLocalParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWDSInfoParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWDSScanParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWdsStaIsConnectParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiAdvanceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetCustomWiFiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetLeaseNetParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetPasswordParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetSpeedParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.UnbindParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WDSConnectWiFiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WDSDidableParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WDSEnableParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WDSGetWanIpParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.ACOtaCheckRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.ACOtaUpgradeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.APGroupsRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.BindRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.CheckNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.CommandRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DataUsage;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DeleteDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetBindInfoRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetClientMacRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetFreqIntergrationRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetQosRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetRouterApiVersionRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetStokLocalRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetStokRemoteRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWDSInfoRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWDSScanInfoRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWdsStaIsConnectRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiAdvanceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWifiFilterRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.ScanWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetPasswordRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetSpeedRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiAdvanceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWifiFilterRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.UnbindRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.UpLoadRouterLogRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.WDSConnectWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.WDSDisableRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.WDSEnableRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.WDSGetRelIpRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.WDSGetWanIpRet;
import sirouter.sdk.siflower.com.remotelibrary.Listener.RemoteConnectionListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SFObjectResponseListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SiWiFiListListener;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;
import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;

/**
 * 矽路由管理类
 */
public class SiUtil {

    private static final String TAG = SiUtil.class.getSimpleName();

    private ProgressDialog progressDialog;

    private SFUser mUser;
    private Routers mRouters;

    private List<Routers> routersList;
    private List<WiFiInfo> wifiInfoList;

    private Context mContext;
    private LocalApi localApi;

    public SiUtil(Context context) {
        mContext = context;
        init();
    }

    public SFUser getmUser() {
        return mUser;
    }

    public void setmUser(SFUser mUser) {
        this.mUser = mUser;
    }

    public Routers getmRouters() {
        return mRouters;
    }

    public void setmRouters(Routers mRouters) {
        this.mRouters = mRouters;
    }

    public List<Routers> getRoutersList() {
        return routersList;
    }

    public void setRoutersList(List<Routers> routersList) {
        this.routersList = routersList;
    }

    public List<WiFiInfo> getWifiInfoList() {
        return wifiInfoList;
    }

    public void setWifiInfoList(List<WiFiInfo> wifiInfoList) {
        this.wifiInfoList = wifiInfoList;
    }

    private void init() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        mUser = SFUser.getCacheUser(mContext);
    }

    /**
     * 登录
     */
    public SFUser login() {
        if (mUser != null && !mUser.getLoginkey().equals("")) {
            SFUser.loginByKey(mContext, mUser.getLoginkey(), new SFObjectResponseListener<SFUser>() {

                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.d(TAG, "login success" + new Gson().toJson(sfUser));
                    mUser = sfUser;
//                    if (sfUser.getBinder() != null) {
//                        if (sfUser.getBinder().size() != 0) {
//                            Log.d(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
//                            binder = sfUser.getBinder();
//                            mRouters = binder.get(0);
////                            mRouters = sfUser.getBinder().get(0);
//                            Log.d(TAG, "onSuccess: 1");
//                        }
//                    }

                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {

                        @Override
                        public void onConnectSuccess() {
                            Log.d(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            //todo code是什么,有什么作用
                            Log.d(TAG, "on connection close");
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.d(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "登录失败，请检查AppSecret和AppKey");
                }
            });
            return mUser;
        } else {
            SFUser.loginByExtra(mContext, "13437600253", new SFObjectResponseListener<SFUser>() {

                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.d(TAG, "login success" + new Gson().toJson(sfUser));
                    mUser = sfUser;

//                    if (sfUser.getBinder() != null) {
//                        if (sfUser.getBinder().size() != 0) {
//                            Log.d(TAG, "not zero" + new Gson().toJson(sfUser.getBinder()));
//                            mRouters = sfUser.getBinder().get(0);
//                            binder = sfUser.getBinder();
//                            mRouters = binder.get(0);
//                            Log.d(TAG, "onSuccess: 2");
//                        }
//                    }

                    /*通过internet获取路由器信息，与服务器建立WebSocket连接*/
                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {

                        @Override
                        public void onConnectSuccess() {
                            Log.d(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.d(TAG, "on connection close");
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.d(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.d(TAG, "登录失败，请检查AppSecret和AppKey");
                }
            });
            return mUser;
        }
    }

    /**
     * 绑定路由器
     */
    public void bindRouter() {
        if (mUser == null) {
            return;
        }

        SiWiFiManager.getInstance().bindSiRouter(mContext, LocalApi.DEFAULT_APP_API_VERSION, mUser, new SingleObserver<BindRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BindRet bindRet) {
                Log.e(TAG, "bind success");
                SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
                    @Override
                    public void onSuccess(List<Routers> objlist) {
                        routersList = objlist;
                        for (Routers routers : objlist) {
                            if (routers.getObjectId().equals(bindRet.getRouterobjectid())) {
                                mRouters = routers;
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
                Log.d(TAG, "bind error " + e.getMessage());
            }
        });
    }

    /**
     * 获取WiFi信息
     */
    public void getWifiObserve() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }

        progressDialog.show();
        Log.d(TAG, "getWifiObserve: ");
        SiWiFiManager.getInstance().getWifiObserve(mRouters, mUser, new SingleObserver<List<WiFiInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<WiFiInfo> wiFiInfo) {
                progressDialog.dismiss();
                //todo 这里获取到绑定的路由器的wifi相关信息
                Log.d(TAG, "wifiInfo: " + new Gson().toJson(wiFiInfo));
                Toast.makeText(mContext, new Gson().toJson(wiFiInfo), Toast.LENGTH_SHORT).show();
                wifiInfoList = wiFiInfo;
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "wifiInfo: " + e.getMessage());
            }
        });
    }

    /**
     * 获取WiFI高级设置
     */
    public void getWifiAdvanceObserve() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }

        SiWiFiManager.getInstance().getWifiAdvanceObserve(mRouters, mUser, new SingleObserver<List<WiFiAdvanceInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<WiFiAdvanceInfo> wiFiAdvanceInfos) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 获取连接的设备信息
     */
    public void getSiRouterDeviceDetail() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getSiRouterDeviceDetail(mRouters, mUser, SiWiFiManager.a.a, new SingleObserver<List<Device>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Device> devices) {
                progressDialog.dismiss();
                Log.d(TAG, "devices: " + new Gson().toJson(devices));
                Toast.makeText(mContext, new Gson().toJson(devices), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "devices: " + e.getMessage());

            }
        });
    }

    /**
     * 获取访客网络信息
     */
    public void getCustomWiFiFace() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getCustomWiFiIFace(mRouters, mUser, new SingleObserver<GetCustomWiFiRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetCustomWiFiRet getCustomWiFiRet) {
                progressDialog.dismiss();
                Log.d(TAG, "getCustomWiFiRet" + new Gson().toJson(getCustomWiFiRet));
                Toast.makeText(mContext, new Gson().toJson(getCustomWiFiRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "getCustomWiFiRet" + e.getMessage());
            }
        });
    }

    /**
     * 获取WDS信息
     */
    public void getWDSInfo() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getWDSInfo(mRouters, mUser, new SingleObserver<List<WDSInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<WDSInfo> wdsInfos) {
                progressDialog.dismiss();
                Log.d(TAG, "wdsinfos: " + new Gson().toJson(wdsInfos));
                Toast.makeText(mContext, new Gson().toJson(wdsInfos), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "wdsinfos: " + e.getMessage());
            }
        });

    }

    /**
     * 获取行为管控信息
     */
    public void getDeviceRestrict() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        GetDeviceRestrictParam param = new GetDeviceRestrictParam("V10");
        param.setMac("A0_86_C6_9D_28_7D");
        SiWiFiManager.getInstance().getDeviceRestrict(mRouters, mUser, param, new SingleObserver<GetDeviceRestrictRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetDeviceRestrictRet getDeviceRestrictRet) {
                progressDialog.dismiss();
                Log.d(TAG, "getDeviceRestrictRet" + new Gson().toJson(getDeviceRestrictRet));
                Toast.makeText(mContext, new Gson().toJson(getDeviceRestrictRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "getDeviceRestrictRet" + e.getMessage());
            }
        });
    }

    /**
     * 获取流量管控信息
     */
    public void getDeviceDataUsage() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        GetDeviceDataUsageParam param = new GetDeviceDataUsageParam("V10");
        param.setMac("A0_86_C6_9D_28_7D");
        SiWiFiManager.getInstance().getDeviceDataUsage(mRouters, mUser, param, new SingleObserver<GetDeviceDataUsageRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetDeviceDataUsageRet getDeviceDataUsageRet) {
                progressDialog.dismiss();
                Log.d(TAG, "getDeviceDataUsageRet" + new Gson().toJson(getDeviceDataUsageRet));
                Toast.makeText(mContext, new Gson().toJson(getDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "getDeviceDataUsageRet" + e.getMessage());
            }
        });
    }

    /**
     * 获取租赁网络
     */
    public void getLeaseNet() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getLeaseNet(mRouters, mUser, new SingleObserver<GetLeaseNetRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetLeaseNetRet getLeaseNetRet) {
                progressDialog.dismiss();
                Log.d(TAG, "getLeaseNet" + new Gson().toJson(getLeaseNetRet));
                Toast.makeText(mContext, new Gson().toJson(getLeaseNetRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "getLeaseNet" + e.getMessage());
            }
        });
    }

    /**
     * 获取WDS扫描信息
     */
    public void getWDSScan() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        String band = "2.4G";
        SiWiFiManager.getInstance().getWDSScan(mRouters, mUser, band, new SingleObserver<List<WDSScanInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<WDSScanInfo> wdsScanInfos) {
                progressDialog.dismiss();
                Log.d(TAG, "wdsScanInfos: " + new Gson().toJson(wdsScanInfos));
                Toast.makeText(mContext, new Gson().toJson(wdsScanInfos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "wdsScanInfos: " + e.getMessage());
            }
        });
    }

    /**
     * 获取WDS 状态下路由器ip地址
     */
    public void getWDSRelIp() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        String band = "2.4G";
        SiWiFiManager.getInstance().getWDSRelIp(mRouters, mUser, band, new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                progressDialog.dismiss();
                Log.d(TAG, "s: " + new Gson().toJson(s));
                Toast.makeText(mContext, new Gson().toJson(s), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "s: " + e.getMessage());
            }
        });
    }

    /**
     * 获取双频合一信息
     */
    public void getFreqIntergration() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        GetFreqIntergrationParam param = new GetFreqIntergrationParam("V17");

        SiWiFiManager.getInstance().getFreqIntergration(mRouters, mUser, param, new SingleObserver<GetFreqIntergrationRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetFreqIntergrationRet getFreqIntergrationRet) {
                progressDialog.dismiss();
                Log.d(TAG, " getFreqIntergrationRet" + new Gson().toJson(getFreqIntergrationRet));
                Toast.makeText(mContext, new Gson().toJson(getFreqIntergrationRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, " getFreqIntergrationRet" + e.getMessage());
            }
        });

    }

    /**
     * 获取用户绑定的路由器
     */
    public void getRouters() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
            @Override
            public void onSuccess(List<Routers> list) {
                progressDialog.dismiss();
                Log.d(TAG, "  getRouters" + new Gson().toJson(list));
                routersList = list;
                Toast.makeText(mContext, new Gson().toJson(list), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int i, String s) {
                progressDialog.dismiss();
                Log.d(TAG, " getFreqIntergrationRet" + s);
            }
        });
    }

    /**
     * 获取WAN口信息
     */
    public void getWanTypeObserve() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().getWanTypeObserve(mRouters, mUser, new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWanTypeRet getWanTypeRet) {
                progressDialog.dismiss();
                Log.d(TAG, "getWanTypeRet)" + new Gson().toJson(getWanTypeRet));
                Toast.makeText(mContext, new Gson().toJson(getWanTypeRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Log.d(TAG, "getWanTypeRet)" + e.getMessage());
            }
        });

    }

    /**
     * 获取手机的网关地址(非wds状态下路由器的ip地址)
     */
    public void getGatewayIp() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        Log.d(TAG, "getGateWayIp" + (SiWiFiManager.getInstance().getGatewayIp(mContext)));
        Toast.makeText(mContext, new Gson().toJson((SiWiFiManager.getInstance().getGatewayIp(mContext))), Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置双频合一
     */
    public void setFreqIntergration() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
//        SetFreqIntergrationParam param = new SetFreqIntergrationParam("V17");
//        if (setFreqIntergration_switch.isChecked()) {
//            param.setEnable(1);
//        } else {
//            param.setEnable(0);
//        }
//        Toast.makeText(mContext, "开始请求", Toast.LENGTH_SHORT).show();
//        SiWiFiManager.getInstance().setFreqIntergration(mRouters, mUser, param, new SingleObserver<SetFreqIntergrationRet>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onSuccess(SetFreqIntergrationRet setFreqIntergrationRet) {
//                progressDialog.dismiss();
//                Toast.makeText(mContext, new Gson().toJson(setFreqIntergrationRet), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                progressDialog.dismiss();
//                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 设置流量管控
     */
    public void setDeviceDataUsage() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetDeviceDataUsageParam param = new SetDeviceDataUsageParam("V14");
        param.setMac("A0_86_C6_9D_28_7D");
        List<DataUsage> list = new ArrayList<>();
        DataUsage dataUsage = new DataUsage();
        dataUsage.setCount(100);
        dataUsage.setType(1);
        dataUsage.setWeek("1,7");
        list.add(dataUsage);
        param.setSetlist(list);
        param.setChange(100);
        param.setUsageenable(1);
        SiWiFiManager.getInstance().setDeviceDataUsage(mRouters, mUser, param).subscribe(new SingleObserver<SetDeviceDataUsageRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetDeviceDataUsageRet setDeviceDataUsageRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置网络租赁
     */
    public void setLeaseNet() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetLeaseNetParam param = new SetLeaseNetParam("V14");
        param.setEnable(true);
        param.setSsid("liuxiaopeng-5G");
        param.setLimitdownload(100);
        SiWiFiManager.getInstance().setLeaseNet(mRouters, mUser, true, "liuxiaopeng-5G", 100, new SingleObserver<SetLeaseNetRet>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetLeaseNetRet setLeaseNetRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setLeaseNetRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * WAN口连接设置
     */
    public void setWanType() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetWanTypeParam param = new SetWanTypeParam("V14");
        param.setType(0);
        SiWiFiManager.getInstance().setWanType(mRouters, mUser, param, new SingleObserver<SetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetWanTypeRet setWanTypeRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setWanTypeRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置管理员密码
     */
    public void setAdminPassword() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetPasswordParam param = new SetPasswordParam("V14");
        //todo 设置管理员密码
//        param.setOldpwd(setOldpwd.getText().toString());
//        param.setNewpwd(setNewpwd.getText().toString());
//        SiWiFiManager.getInstance().setAdminPassword(mRouters, mUser, setOldpwd.getText().toString(), setNewpwd.getText().toString(), new SingleObserver<SetPasswordRet>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(SetPasswordRet setPasswordRet) {
//                progressDialog.dismiss();
//                Toast.makeText(mContext, new Gson().toJson(setPasswordRet), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                progressDialog.dismiss();
//                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 设置行为管控信息
     */
    public void setDeviceRestrict() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetDeviceRestrictParam param = new SetDeviceRestrictParam("V14");
        param.setMac("A0_86_C6_9D_28_7D");
        param.setSocial(0);
        param.setVideo(0);
        param.setGame(1);
        param.setRestrictenable(0);
        SiWiFiManager.getInstance().setDeviceRestrict(mRouters, mUser, param).subscribe(new SingleObserver<SetDeviceRestrictRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetDeviceRestrictRet setDeviceRestrictRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setDeviceRestrictRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置访客网络
     */
    public void setCustomWiFi() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SetCustomWiFiParam param = new SetCustomWiFiParam("V14");
        List<IFace> list = new ArrayList<IFace>();
        IFace iFace = new IFace();
        iFace.setBand("2.4G");
        iFace.setRemainingtime(0);
        iFace.setSsid("liuxiaopeng-2.4G");
        iFace.setEnable(true);
        iFace.setLimittimetype(0);
        iFace.setOpen(false);
        iFace.setLimitupload(10);
        iFace.setPeriodicaltime("1-6");
        iFace.setKey("123456789");
        iFace.setLimitdownload(10);
        iFace.setLocalaccess(false);
        iFace.setLimittime(false);
        list.add(iFace);
        param.setIfaces(list);
        SiWiFiManager.getInstance().setCustomWiFi(mRouters, mUser, param, new SingleObserver<SetCustomWiFiRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetCustomWiFiRet setCustomWiFiRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setCustomWiFiRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置wifi信息
     */
    public void setWiFi() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        List<WifiParam> list = new ArrayList<WifiParam>();
        WifiParam wifiParam = new WifiParam();
        wifiParam.enable = 1;
        wifiParam.encryption = "psk2+ccmp";
        wifiParam.signalmode = 0;
        wifiParam.channel = 1;
        wifiParam.password = "12345678";
        wifiParam.oldssid = "siwifi-8340-5G";
        wifiParam.newssid = "LIU-2.4G";
        list.add(wifiParam);
        SiWiFiManager.getInstance().setWiFi(mRouters, mUser, list, new SingleObserver<SetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setWiFiDetailRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Wifi高级设置
     */
    public void setWiFiAdvance() {
        if (mUser == null) {
            return;
        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();

        List<SetWiFiAdvanceInfo> list = new ArrayList<SetWiFiAdvanceInfo>();

        SetWiFiAdvanceInfo param24 = new SetWiFiAdvanceInfo();
        param24.country = "CN";
        param24.rts = 0;
        param24.distance = 0;
        param24.enable = 1;
        param24.encryption = "psk2+ccmp";
        param24.fragment = 0;
        param24.signalmode = 0;

        SetWiFiAdvanceInfo param5 = new SetWiFiAdvanceInfo();
        param5.country = "CN";
        param5.rts = 0;
        param5.distance = 0;
        param5.enable = 1;
        param5.encryption = "psk2+ccmp";
        param5.fragment = 0;
        param5.signalmode = 0;

        list.add(param24);
        list.add(param5);

        SiWiFiManager.getInstance().setWiFiAdvance(mRouters, mUser, list, new SingleObserver<SetWiFiAdvanceRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetWiFiAdvanceRet setWiFiAdvanceRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(setWiFiAdvanceRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 接触路由器绑定
     */
    public void unbindSiRouter() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }
        progressDialog.show();
        SiWiFiManager.getInstance().unbindSiRouter(mRouters, mUser, new SingleObserver<UnbindRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UnbindRet unbindRet) {
                progressDialog.dismiss();
                Toast.makeText(mContext, new Gson().toJson(unbindRet), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 切换用户
     *
     * @param sfUser1 当前用户１
     * @param sfUser2 　要切换成的用户２
     */
    public void changeRemoteUser(SFUser sfUser1, SFUser sfUser2) {
        SiWiFiManager.getInstance().changeRemoteUser(sfUser1, sfUser2);
    }

    /**
     * 断开连接
     */
    public void closeRemoteConnection() {
        SiWiFiManager.getInstance().closeRemoteConnection();
    }

    /**
     * 重启路由器
     */
    public void reboot() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        SiWiFiManager.getInstance().reboot(mRouters, mUser, new SingleObserver<CommandRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CommandRet commandRet) {
                //todo 重启路由器
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 路由器恢复出厂设置
     */
    public void reset() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        SiWiFiManager.getInstance().reset(mRouters, mUser, new SingleObserver<CommandRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CommandRet commandRet) {
                //todo 路由器恢复出厂设置
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 设置单个设备
     */
    public void setSiRouterDeviceDetail() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo 设置单个设备  SetDeviceParam对象　内容了解
        Single<SetDeviceRet> setDeviceRetSingle = SiWiFiManager.getInstance().setSiRouterDeviceDetail(mRouters, mUser, new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, mUser.getBinder().get(0).getMac()));

    }

    /**
     * 上传路由器日志
     */
    public void upLoadRouterLog() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo 上传路由器日志 mparam - 上传参数对象 UpLoadRouterLogRet - 上传路由器日志信息
//        SiWiFiManager.getInstance().upLoadRouterLog(mRouters, mUser, );
    }

    /**
     * 连接WDS
     */
    public void connectWDS() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo 连接WDS    WDSConnectWiFiParam - 连接WDS的参数对象 WDSConnectWiFiRet -
//        SiWiFiManager.getInstance().connectWDS(mRouters, mUser, );
    }

    /**
     * 关闭WDS
     */
    public void disableWDS() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo
//        SiWiFiManager.getInstance().disableWDS(mRouters, mUser, );
    }

    /**
     * 开启WDS
     */
    public void enableWDSNew() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo routers - Routers对象      sfUser - SFUser对象       band - 2.4G 或 5G        iFace - 修改WiFi名称的对象     subscriber -
//        SiWiFiManager.getInstance().enableWDSNew(mRouters, mUser, );
    }

    /**
     * AC控制器接口：获取AP分组列表
     */
    public void getAPGroups() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        SiWiFiManager.getInstance().getAPGroups(mRouters, mUser, new SingleObserver<APGroupsRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(APGroupsRet apGroupsRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * AC控制器接口：获取AP列表
     */
    public void getAPList() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //todo routers - Routers对象      sfUser - SFUser对象       subscriber -
//        SiWiFiManager.getInstance().getAPList(mRouters, mUser, );
    }

    /**
     * AC控制器接口：设置AP分组信息
     */
    public void setAPGroup() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //// TODO: 19-7-22   routers - Routers对象      sfUser - SFUser对象       mparam - 设置的参数      subscriber
//        SiWiFiManager.getInstance().setAPGroup(mRouters, mUser, );
    }

    /**
     * AC控制器接口：设置单个AP信息
     */
    public void setAP() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //// TODO: 19-7-22   routers - Routers对象      sfUser - SFUser对象       mparam - 设置的参数      subscriber -
//        SiWiFiManager.getInstance().setAP(mRouters, mUser, );
    }

    /**
     * AC控制器接口：删除分组
     */
    public void removeAPGroup() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //// TODO: 19-7-22  routers - Routers对象     sfUser - SFUser对象        mparam - 删除的参数        subscriber -
//        SiWiFiManager.getInstance().removeAPGroup(mRouters, mUser, );
    }

    /**
     * AC控制器接口：删除AP
     */
    public void deleteAP() {
        if (mUser == null) {
            return;

        }

        if (mRouters == null) {
            return;
        }

        //// TODO: 19-7-22 routers - Routers对象      sfUser - SFUser对象       mparam - 删除的参数      subscriber -
//        SiWiFiManager.getInstance().deleteAP(mRouters, mUser, );
    }


    /**
     * 初始化
     */
    public LocalApi localApiInit() {
        //路由器版本信息
        localApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);
        //设置路由器的ip地址或域名
        localApi.setmLocalIp("192.168.4.1");
        //路由器管理员密码
        localApi.setAdminPassword("admin");

        return localApi;
    }

    // TODO: 19-7-31 封装抽象　local
    public void executeApiWithSingleResponse(String param) {
        Single<?> getRetSingle;
        switch (param) {
            case "BindInfo":
                getRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetBindInfoRet.class);

                break;
            default:
                break;
        }

    }

    /**
     *
     */
    public void setUserObject() {

    }

    /**
     *
     */
    public void findMyWifiResult(LocalApi localApi, Activity activity) {
        ScanWiFiRet myWifiResult = localApi.findMyWifiResult(activity);
    }

    /**
     *
     */
    public void getmLocalIp(LocalApi localApi) {
        String s = localApi.getmLocalIp();
    }

    /**
     * 判断wifi是否为矽路由
     */
    public void isSiwifi(String mac, LocalApi localApi) {
        boolean siwifi = localApi.isSiwifi(mac);

    }

    /**
     * 获取Wifi列列表信息
     *
     * @param localApi LocalApi实例
     */
    public void getWiFiDetail(LocalApi localApi) {
        Single<GetWiFiDetailRet> getWifi = localApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWifi.subscribe(new SingleObserver<GetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                Log.d(TAG, "onSuccess: " + getWiFiDetailRet.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * 获取设备信息
     *
     * @param tvRecyclerView 　接收返回值的View
     * @param localApi       LocaleApi实例
     * @param activity       当前Activity
     */
    public void getDeviceRet(TvRecyclerView tvRecyclerView, LocalApi localApi, Activity activity, SiUtil siUtil, Handler handler) {
        Single<GetDeviceRet> getDeviceRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRet.class);
        getDeviceRetSingle.subscribe(new SingleObserver<GetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onSuccess(GetDeviceRet getDeviceRet) {
                Log.d(TAG, "onSuccess: " + getDeviceRet.toString());
                DevicesAdapter devicesAdapter = new DevicesAdapter(mContext, activity.toString(), localApi);
                switch (activity.toString()) {
                    case "ConnectedFragment":
                        devicesAdapter.setDeviceList(getDeviceRet.getList());
                        tvRecyclerView.setAdapter(devicesAdapter);
                        tvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
                            @Override
                            public void onItemViewClick(View view, int position) {
                                // TODO: 19-8-2 功能一：弹出框限制上传下载的数据  功能二：禁用按钮实现设备禁用
                                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(activity);
                                dialogBuilder
                                        .withTitle(activity.getString(R.string.upload_download_limit))
                                        .withDuration(700)
                                        .show();
                            }

                            @Override
                            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
                                // TODO: 19-8-2 添加内容

                            }
                        });
                        break;
                    case "ForbiddenFragment":
                        devicesAdapter.setDeviceList(getDeviceRet.getList());
                        tvRecyclerView.setAdapter(devicesAdapter);
                        tvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
                            @Override
                            public void onItemViewClick(View view, int position) {
                                // TODO: 19-8-2 功能一：解除禁用按钮实现设备禁用
                                List<Device> deviceList = devicesAdapter.getDeviceList();
                                siUtil.setDevice(localApi, deviceList.get(position).getAuthority().getLan() + "", handler);
                            }

                            @Override
                            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
                                // TODO: 19-8-2 添加内容

                            }
                        });
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * 获取设备信息
     *
     * @param tvRecyclerView 　接收返回值的View
     * @param localApi       LocaleApi实例
     * @param activity       当前Activity
     */
    public void getDeviceRet(TvRecyclerView tvRecyclerView, LocalApi localApi, Activity activity, SiUtil siUtil) {
        Single<GetDeviceRet> getDeviceRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRet.class);
        getDeviceRetSingle.subscribe(new SingleObserver<GetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(GetDeviceRet getDeviceRet) {
                Log.d(TAG, "onSuccess: " + getDeviceRet.toString());
                Log.d(TAG, "onSuccess: " + activity.toString());
                DevicesAdapter devicesAdapter = new DevicesAdapter(mContext, activity.toString(), localApi);
                devicesAdapter.setDeviceList(getDeviceRet.getList());
                tvRecyclerView.setAdapter(devicesAdapter);
                tvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
                    @Override
                    public void onItemViewClick(View view, int position) {
                        // TODO: 19-8-2 功能一：弹出框限制上传下载的数据  功能二：禁用按钮实现设备禁用
                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(activity);
                        dialogBuilder
                                .withTitle(activity.getString(R.string.upload_download_limit))
                                .withDuration(700)
                                .show();
                    }

                    @Override
                    public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
                        // TODO: 19-8-2 添加内容

                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * 获取设备限制详情
     *
     * @param tvRecyclerView 接收返回值的View
     * @param localApi       LocaleApi实例
     * @param activity       当前活动类
     */
    public void getDeviceRestrictRet(TvRecyclerView tvRecyclerView, LocalApi localApi, Activity activity) {
        Single<GetDeviceRestrictRet> getDeviceRestrictRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRestrictRet.class);
        getDeviceRestrictRetSingle.subscribe(new SingleObserver<GetDeviceRestrictRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(GetDeviceRestrictRet getDeviceRestrictRet) {
                Log.d(TAG, "onSuccess: " + getDeviceRestrictRet.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * 获取WIFI详情
     *
     * @param localApi LocaleApi实例
     * @param activity 　当前活动类
     */
    public void getWiFiDetailRet(LocalApi localApi, Activity activity) {
        Single<GetWiFiDetailRet> getWiFiDetailRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                Log.d(TAG, "onSuccess: " + getWiFiDetailRet.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * 获取路由器API版本号
     */
    public void getRouterApiVersionRet() {
        Single<GetRouterApiVersionRet> getRouterApiVersionRetSingle = localApi.executeApiWithSingleResponse(new GetRouterApiVersionParam(LocalApi.DEFAULT_APP_API_VERSION), GetRouterApiVersionRet.class);
        getRouterApiVersionRetSingle.subscribe(new SingleObserver<GetRouterApiVersionRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(GetRouterApiVersionRet getRouterApiVersionRet) {
                Log.d(TAG, "onSuccess: " + getRouterApiVersionRet.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    /**
     * @param localApi
     */
    public void getBindInfoRetSingle(LocalApi localApi) {
        Single<GetBindInfoRet> getBindInfoRetSingle = localApi.executeApiWithSingleResponse(new GetBindInfoParam(LocalApi.DEFAULT_APP_API_VERSION), GetBindInfoRet.class);
        getBindInfoRetSingle.subscribe(new SingleObserver<GetBindInfoRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetBindInfoRet getBindInfoRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi
     */
    public void getClientMacRet(LocalApi localApi) {
        Single<GetClientMacRet> getClientMacRetSingle = localApi.executeApiWithSingleResponse(new GetClientMacParam(LocalApi.DEFAULT_APP_API_VERSION), GetClientMacRet.class);
        getClientMacRetSingle.subscribe(new SingleObserver<GetClientMacRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetClientMacRet getClientMacRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getDeviceDataUsageRet(LocalApi localApi) {
        Single<GetDeviceDataUsageRet> getDeviceDataUsageRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceDataUsageParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceDataUsageRet.class);
        getDeviceDataUsageRetSingle.subscribe(new SingleObserver<GetDeviceDataUsageRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetDeviceDataUsageRet getDeviceDataUsageRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getFreqIntergrationRet(LocalApi localApi) {
        Single<GetFreqIntergrationRet> getFreqIntergrationRetSingle = localApi.executeApiWithSingleResponse(new GetFreqIntergrationParam(LocalApi.DEFAULT_APP_API_VERSION), GetFreqIntergrationRet.class);
        getFreqIntergrationRetSingle.subscribe(new SingleObserver<GetFreqIntergrationRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetFreqIntergrationRet getFreqIntergrationRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getLeaseNetRet(LocalApi localApi) {
        Single<GetLeaseNetRet> getLeaseNetRetSingle = localApi.executeApiWithSingleResponse(new GetLeaseNetParam(LocalApi.DEFAULT_APP_API_VERSION), GetLeaseNetRet.class);
        getLeaseNetRetSingle.subscribe(new SingleObserver<GetLeaseNetRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetLeaseNetRet getFreqIntergrationRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getQosRet(LocalApi localApi) {
        Single<GetQosRet> getQosRetSingle = localApi.executeApiWithSingleResponse(new GetQosParam(LocalApi.DEFAULT_APP_API_VERSION), GetQosRet.class);
        getQosRetSingle.subscribe(new SingleObserver<GetQosRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetQosRet getQosRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getRouterApiVersionRet(LocalApi localApi) {
        Single<GetRouterApiVersionRet> getQosRetSingle = localApi.executeApiWithSingleResponse(new GetRouterApiVersionParam(LocalApi.DEFAULT_APP_API_VERSION), GetRouterApiVersionRet.class);
        getQosRetSingle.subscribe(new SingleObserver<GetRouterApiVersionRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetRouterApiVersionRet getRouterApiVersionRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getStokLocalRet(LocalApi localApi) {
        Single<GetStokLocalRet> getQosRetSingle = localApi.executeApiWithSingleResponse(new GetStokLocalParam(LocalApi.DEFAULT_APP_API_VERSION), GetStokLocalRet.class);
        getQosRetSingle.subscribe(new SingleObserver<GetStokLocalRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetStokLocalRet getStokLocalRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi localApi实例
     */
    public void getStokRemoteRet(LocalApi localApi) {
        Single<GetStokRemoteRet> getStokRemoteRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetStokRemoteRet.class);
        getStokRemoteRetSingle.subscribe(new SingleObserver<GetStokRemoteRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetStokRemoteRet getStokLocalRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi localApi实例
     */
    public void getWanTypeRet(LocalApi localApi) {
        Single<GetWanTypeRet> getQosRetSingle = localApi.executeApiWithSingleResponse(new GetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION), GetWanTypeRet.class);
        getQosRetSingle.subscribe(new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWanTypeRet getStokLocalRet) {
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void getWDSInfoRet(LocalApi localApi) {
        Single<GetWDSInfoRet> getWDSInfoRetSingle = localApi.executeApiWithSingleResponse(new GetWDSInfoParam(LocalApi.DEFAULT_APP_API_VERSION), GetWDSInfoRet.class);
        getWDSInfoRetSingle.subscribe(new SingleObserver<GetWDSInfoRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWDSInfoRet getWDSInfoRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void getWDSScanInfoRet(LocalApi localApi) {
        Single<GetWDSScanInfoRet> wdsScanInfoRetSingle = localApi.executeApiWithSingleResponse(new GetWDSScanParam(LocalApi.DEFAULT_APP_API_VERSION), GetWDSScanInfoRet.class);
        wdsScanInfoRetSingle.subscribe(new SingleObserver<GetWDSScanInfoRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWDSScanInfoRet getWDSScanInfoRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void getWdsStaIsConnectRet(LocalApi localApi) {
        Single<GetWdsStaIsConnectRet> getWdsStaIsConnectRetSingle = localApi.executeApiWithSingleResponse(new GetWdsStaIsConnectParam(LocalApi.DEFAULT_APP_API_VERSION), GetWdsStaIsConnectRet.class);
        getWdsStaIsConnectRetSingle.subscribe(new SingleObserver<GetWdsStaIsConnectRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWdsStaIsConnectRet getWdsStaIsConnectRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void getWiFiAdvanceRet(LocalApi localApi) {
        Single<GetWiFiAdvanceRet> getWiFiAdvanceRetSingle = localApi.executeApiWithSingleResponse(new GetWiFiAdvanceParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiAdvanceRet.class);
        getWiFiAdvanceRetSingle.subscribe(new SingleObserver<GetWiFiAdvanceRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWiFiAdvanceRet getWiFiAdvanceRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void gtWiFiDetailRet(LocalApi localApi) {
        Single<GetWiFiDetailRet> getWiFiDetailRetSingle = localApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　localApi实例
     */
    public void getWifiFilterRet(LocalApi localApi) {
        Single<GetWifiFilterRet> getWifiFilterRetSingle = localApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWifiFilterRet.class);
        getWifiFilterRetSingle.subscribe(new SingleObserver<GetWifiFilterRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWifiFilterRet getWifiFilterRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 解绑
     *
     * @param localApi 　localApi实例
     */
    public void unbind(LocalApi localApi) {
        Single<UnbindRet> unbindRetSingle = localApi.executeApiWithSingleResponse(new UnbindParam(LocalApi.DEFAULT_APP_API_VERSION), UnbindRet.class);
        unbindRetSingle.subscribe(new SingleObserver<UnbindRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UnbindRet unbindRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * AC控制器Ota信息查询参数
     *
     * @param localApi LocaleApi实例
     */
    public void aCOtaCheck(LocalApi localApi) {
        Single<ACOtaCheckRet> acOtaCheckRetSingle = localApi.executeApiWithSingleResponse(new ACOtaCheckParam(LocalApi.DEFAULT_APP_API_VERSION), ACOtaCheckRet.class);
        acOtaCheckRetSingle.subscribe(new SingleObserver<ACOtaCheckRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ACOtaCheckRet acOtaCheckRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * AC控制器Ota升级参数
     *
     * @param localApi LocaleApi实例
     */
    public void aCOtaUpgrade(LocalApi localApi) {
        Single<ACOtaUpgradeRet> acOtaUpgradeRetSingle = localApi.executeApiWithSingleResponse(new ACOtaCheckParam(LocalApi.DEFAULT_APP_API_VERSION), ACOtaUpgradeRet.class);
        acOtaUpgradeRetSingle.subscribe(new SingleObserver<ACOtaUpgradeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ACOtaUpgradeRet acOtaUpgradeRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 绑定
     *
     * @param localApi 　LocaleApi实例
     */
    public void bind(LocalApi localApi) {
        Single<BindRet> bindRetSingle = localApi.executeApiWithSingleResponse(new BindParam(LocalApi.DEFAULT_APP_API_VERSION), BindRet.class);
        bindRetSingle.subscribe(new SingleObserver<BindRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(BindRet bindRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 检查路由器WAN口状态
     *
     * @param localApi LocaleApi实例
     */
    public void checkNet(LocalApi localApi) {
        Single<CheckNetRet> bindRetSingle = localApi.executeApiWithSingleResponse(new CheckNetParam(LocalApi.DEFAULT_APP_API_VERSION), CheckNetRet.class);
        bindRetSingle.subscribe(new SingleObserver<CheckNetRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CheckNetRet checkNetRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 重启及恢复出厂设置参数
     *
     * @param localApi LocaleApi实例
     */
    public void command(LocalApi localApi) {
        Single<CheckNetRet> bindRetSingle = localApi.executeApiWithSingleResponse(new CheckNetParam(LocalApi.DEFAULT_APP_API_VERSION), CheckNetRet.class);
        bindRetSingle.subscribe(new SingleObserver<CheckNetRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CheckNetRet checkNetRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void deleteDevice(LocalApi localApi) {
        Single<DeleteDeviceRet> deleteDeviceRetSingle = localApi.executeApiWithSingleResponse(new DeleteDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), DeleteDeviceRet.class);
        deleteDeviceRetSingle.subscribe(new SingleObserver<DeleteDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(DeleteDeviceRet deleteDeviceRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void getDeviceDataUsage(LocalApi localApi) {
        Single<GetDeviceDataUsageRet> deleteDeviceRetSingle = localApi.executeApiWithSingleResponse(new GetDeviceDataUsageParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceDataUsageRet.class);
        deleteDeviceRetSingle.subscribe(new SingleObserver<GetDeviceDataUsageRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetDeviceDataUsageRet getDeviceDataUsageRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void wDSConnectWiFi(LocalApi localApi) {
        Single<WDSConnectWiFiRet> wdsConnectWiFiRetSingle = localApi.executeApiWithSingleResponse(new WDSConnectWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), WDSConnectWiFiRet.class);
        wdsConnectWiFiRetSingle.subscribe(new SingleObserver<WDSConnectWiFiRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSConnectWiFiRet wdsConnectWiFiRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void wDSConnectWiFiRet(LocalApi localApi) {
        Single<WDSConnectWiFiRet> wdsConnectWiFiRetSingle = localApi.executeApiWithSingleResponse(new WDSConnectWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), WDSConnectWiFiRet.class);
        wdsConnectWiFiRetSingle.subscribe(new SingleObserver<WDSConnectWiFiRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSConnectWiFiRet wdsConnectWiFiRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void wDSDisableRet(LocalApi localApi) {
        Single<WDSDisableRet> wdsConnectWiFiRetSingle = localApi.executeApiWithSingleResponse(new WDSDidableParam(LocalApi.DEFAULT_APP_API_VERSION), WDSDisableRet.class);
        wdsConnectWiFiRetSingle.subscribe(new SingleObserver<WDSDisableRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSDisableRet wdsDisableRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void wDSEnableRet(LocalApi localApi) {
        Single<WDSEnableRet> wdsConnectWiFiRetSingle = localApi.executeApiWithSingleResponse(new WDSEnableParam(LocalApi.DEFAULT_APP_API_VERSION), WDSEnableRet.class);
        wdsConnectWiFiRetSingle.subscribe(new SingleObserver<WDSEnableRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSEnableRet wdsEnableRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void wDSGetRelIpRet(LocalApi localApi) {
        Single<WDSGetRelIpRet> wdsGetRelIpRetSingle = localApi.executeApiWithSingleResponse(new WDSEnableParam(LocalApi.DEFAULT_APP_API_VERSION), WDSGetRelIpRet.class);
        wdsGetRelIpRetSingle.subscribe(new SingleObserver<WDSGetRelIpRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSGetRelIpRet wdsEnableRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi 　LocaleApi实例
     */
    public void wDSGetWanIpRet(LocalApi localApi) {
        Single<WDSGetWanIpRet> wdsGetRelIpRetSingle = localApi.executeApiWithSingleResponse(new WDSGetWanIpParam(LocalApi.DEFAULT_APP_API_VERSION), WDSGetWanIpRet.class);
        wdsGetRelIpRetSingle.subscribe(new SingleObserver<WDSGetWanIpRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WDSGetWanIpRet wdsGetWanIpRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * @param localApi LocaleApi实例
     */
    public void setSpeed(LocalApi localApi) {
        Single<SetSpeedRet> wdsGetRelIpRetSingle = localApi.executeApiWithSingleResponse(new SetSpeedParam(LocalApi.DEFAULT_APP_API_VERSION), SetSpeedRet.class);
        wdsGetRelIpRetSingle.subscribe(new SingleObserver<SetSpeedRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetSpeedRet setSpeedRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 设置上网方式
     *
     * @param localApi 　LocaleApi实例
     * @param account  　帐号
     * @param password 　密码
     */
    public void setWanType(LocalApi localApi, String account, String password, Handler handler) {
        SetWanTypeParam setWanTypeParam = new SetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION);
        setWanTypeParam.setPppnanme(account);
        setWanTypeParam.setPpppwd(password);
        Single<SetWanTypeRet> setWanTypeRetSingle = localApi.executeApiWithSingleResponse(setWanTypeParam, SetWanTypeRet.class);
        setWanTypeRetSingle.subscribe(new SingleObserver<SetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onSuccess(SetWanTypeRet setWanTypeRet) {
                Log.d(TAG, "onSuccess: ");
                Message message = new Message();
                message.what = Data.handler_message_setWanType_PPOE;
                message.obj = "setDevice";
                handler.sendMessage(message);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });
    }

    public void setWiFiDetail(LocalApi localApi) {
        Single<SetWiFiDetailRet> setWanTypeRetSingle = localApi.executeApiWithSingleResponse(new SetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), SetWiFiDetailRet.class);
        setWanTypeRetSingle.subscribe(new SingleObserver<SetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void SetWifiFilter(LocalApi localApi) {
        Single<SetWifiFilterRet> setWifiFilterRetSingle = localApi.executeApiWithSingleResponse(new SetCustomWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), SetWifiFilterRet.class);
        setWifiFilterRetSingle.subscribe(new SingleObserver<SetWifiFilterRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetWifiFilterRet setWifiFilterRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void upLoadRouterLogRet(LocalApi localApi) {
        Single<UpLoadRouterLogRet> upLoadRouterLogRetSingle = localApi.executeApiWithSingleResponse(new SetCustomWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), UpLoadRouterLogRet.class);
        upLoadRouterLogRetSingle.subscribe(new SingleObserver<UpLoadRouterLogRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UpLoadRouterLogRet loadRouterLogRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void wanSpeed(LocalApi localApi) {
        Single<UpLoadRouterLogRet> upLoadRouterLogRetSingle = localApi.executeApiWithSingleResponse(new SetCustomWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), UpLoadRouterLogRet.class);
        upLoadRouterLogRetSingle.subscribe(new SingleObserver<UpLoadRouterLogRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UpLoadRouterLogRet loadRouterLogRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * WiFi设置
     *
     * @param localApi LocaleApi实例
     */
    public void wifi(LocalApi localApi) {
        // TODO: 19-8-3 需要做一些更改
        Single<UpLoadRouterLogRet> upLoadRouterLogRetSingle = localApi.executeApiWithSingleResponse(new SetCustomWiFiParam(LocalApi.DEFAULT_APP_API_VERSION), UpLoadRouterLogRet.class);
        upLoadRouterLogRetSingle.subscribe(new SingleObserver<UpLoadRouterLogRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UpLoadRouterLogRet loadRouterLogRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 设置管理员密码
     *
     * @param localApi LocaleApi实例
     */
    public void setPassword(LocalApi localApi) {
        Single<SetPasswordRet> setPasswordRetSingle = localApi.executeApiWithSingleResponse(new SetPasswordParam(LocalApi.DEFAULT_APP_API_VERSION), SetPasswordRet.class);
        setPasswordRetSingle.subscribe(new SingleObserver<SetPasswordRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(SetPasswordRet setPasswordRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 设置设备
     *
     * @param localApi LocaleApi实例
     */
    public void setDevice(LocalApi localApi, String param, Handler handler) {
        SetDeviceParam setDeviceParam = new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, param);
        setDeviceParam.setLan(1);//禁用设备使用无线网络
        Single<SetDeviceRet> setDeviceRetSingle = localApi.executeApiWithSingleResponse(setDeviceParam, SetDeviceRet.class);
        setDeviceRetSingle.subscribe(new SingleObserver<SetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override
            public void onSuccess(SetDeviceRet setDeviceRet) {
                Log.d(TAG, "onSuccess: ");
                Message message = new Message();
                message.what = 2;
                message.obj = "setDevice";
                handler.sendMessage(message);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }
        });
    }

}