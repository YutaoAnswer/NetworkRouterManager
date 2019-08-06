package com.trigletop.networkroutermanager.view.fragment.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.DummyChildDataItem;
import com.trigletop.networkroutermanager.adapter.DummyParentDataItem;
import com.trigletop.networkroutermanager.adapter.RecyclerDataAdapter;
import com.trigletop.networkroutermanager.utils.SiUtil;
import com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount.DDNSFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount.DMZHostFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount.RouterFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount.UPnPSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount.VirtualServerFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.BackupFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.ChangePswFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.DiagnosticToolsFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.ResetFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.RestartFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.SystemLogFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.TimeAndLanguageFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.UpdateFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.BindingSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.DHCPServerFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.LANPortSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.MacAddressFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.GuestNetworkFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.HostNetworkFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.WDSFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class AdvancedSettingFragment extends Fragment {

    private static final String TAG = AdvancedSettingFragment.class.getSimpleName();

    @BindView(R.id.rcy_sidebar)
    RecyclerView rcySidebar;
    Unbinder unbinder;

    private SiUtil siUtil;
    private LocalApi localApi;

    private List<Fragment> fragmentList = new ArrayList<>();
    private Fragment currentFragment;
    private FragmentManager supportFragmentManager;
    private WANPortSettingFragment wanPortSettingFragment;
    private LANPortSettingFragment lanPortSettingFragment;
    private MacAddressFragment macAddressFragment;
    private DHCPServerFragment dhcpServerFragment;
    private BindingSettingFragment bindingSettingFragment;
    private HostNetworkFragment hostNetworkFragment;
    private GuestNetworkFragment guestFragment;
    private WDSFragment wdsFragment;
    private BackupFragment backupFragment;
    private ChangePswFragment changePswFragment;
    private DiagnosticToolsFragment diagnosticToolsFragment;
    private ResetFragment resetFragment;
    private RestartFragment restartFragment;
    private SystemLogFragment systemLogFragment;
    private TimeAndLanguageFragment timeAndLanguageFragment;
    private UpdateFragment updateFragment;
    private DDNSFragment ddnsFragment;
    private DMZHostFragment dmzHostFragment;
    private RouterFragment routerFragment;
    private UPnPSettingFragment uPnPSettingFragment;
    private VirtualServerFragment virtualServerFragment;

    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = (String) msg.obj;
            switch (obj) {
                // TODO: 19-7-31 代码优化
                case "WAN口设置":
                    switchFragment(currentFragment, wanPortSettingFragment);
                    currentFragment = wanPortSettingFragment;
                    break;
                case "LAN口设置":
                    switchFragment(currentFragment, lanPortSettingFragment);
                    currentFragment = lanPortSettingFragment;
                    break;
                case "Mac地址":
                    switchFragment(currentFragment, macAddressFragment);
                    currentFragment = macAddressFragment;
                    break;
                case "DHCP服务器":
                    switchFragment(currentFragment, dhcpServerFragment);
                    currentFragment = dhcpServerFragment;
                    break;
                case "IP与MAC绑定设置":
                    switchFragment(currentFragment, bindingSettingFragment);
                    currentFragment = bindingSettingFragment;
                    break;
                case "主人网络":
                    switchFragment(currentFragment, hostNetworkFragment);
                    currentFragment = hostNetworkFragment;
                    break;
                case "访客网络":
                    switchFragment(currentFragment, guestFragment);
                    currentFragment = guestFragment;
                    break;
                case "WDS":
                    switchFragment(currentFragment, wdsFragment);
                    currentFragment = wdsFragment;
                    break;
                case "虚拟服务器":
                    switchFragment(currentFragment, virtualServerFragment);
                    currentFragment = virtualServerFragment;
                    break;
                case "DMZ主机":
                    switchFragment(currentFragment, dmzHostFragment);
                    currentFragment = dmzHostFragment;
                    break;
                case "UPnP设置":
                    switchFragment(currentFragment, uPnPSettingFragment);
                    currentFragment = uPnPSettingFragment;
                    break;
                case "路由功能":
                    switchFragment(currentFragment, routerFragment);
                    currentFragment = routerFragment;
                    break;
                case "DDNS":
                    switchFragment(currentFragment, ddnsFragment);
                    currentFragment = ddnsFragment;
                    break;
                case "时间和语言":
                    switchFragment(currentFragment, timeAndLanguageFragment);
                    currentFragment = timeAndLanguageFragment;
                    break;
                case "软件升级":
                    switchFragment(currentFragment, updateFragment);
                    currentFragment = updateFragment;
                    break;
                case "恢复出厂设置":
                    switchFragment(currentFragment, resetFragment);
                    currentFragment = resetFragment;
                    break;
                case "备份":
                    switchFragment(currentFragment, backupFragment);
                    currentFragment = backupFragment;
                    break;
                case "重启路由器":
                    switchFragment(currentFragment, restartFragment);
                    currentFragment = restartFragment;
                    break;
                case "修改登录密码":
                    switchFragment(currentFragment, changePswFragment);
                    currentFragment = changePswFragment;
                    break;
                case "诊断工具":
                    switchFragment(currentFragment, diagnosticToolsFragment);
                    currentFragment = diagnosticToolsFragment;
                    break;
                case "系统日志":
                    switchFragment(currentFragment, systemLogFragment);
                    currentFragment = systemLogFragment;
                    break;
                default:
                    break;
            }
        }
    };


    public static AdvancedSettingFragment newInstance() {
        AdvancedSettingFragment advancedSettingFragment = new AdvancedSettingFragment();
        Bundle args = new Bundle();
        advancedSettingFragment.setArguments(args);
        return advancedSettingFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advanced_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        siUtil = new SiUtil(getActivity());
        localApi = siUtil.localApiInit();

//        ExtranetSettingFragment extranetSettingFragment = ExtranetSettingFragment.newInstance();//外网设置
//        WIFILeaseFragment wifiLeaseFragment = WIFILeaseFragment.newInstance();//WIFI租赁
//        WIFISettingFragment wifiSettingFragment = WIFISettingFragment.newInstance();//WIFI设置
//        FloodControlNetworkFragment floodControlNetworkFragment = FloodControlNetworkFragment.newInstance();//防蹭网
//        NetworkDetectionFragment networkDetectionFragment = NetworkDetectionFragment.newInstance();//网络检测
//        UpdateFragment updateFragment = UpdateFragment.newInstance();//固件升级
//        fragmentList.add(extranetSettingFragment);
//        fragmentList.add(wifiLeaseFragment);
//        fragmentList.add(wifiSettingFragment);
//        fragmentList.add(floodControlNetworkFragment);
//        fragmentList.add(networkDetectionFragment);
//        fragmentList.add(updateFragment);

        wanPortSettingFragment = WANPortSettingFragment.newInstance();
        lanPortSettingFragment = LANPortSettingFragment.newInstance();
        macAddressFragment = MacAddressFragment.newInstance();
        dhcpServerFragment = DHCPServerFragment.newInstance();
        bindingSettingFragment = BindingSettingFragment.newInstance();

        guestFragment = GuestNetworkFragment.newInstance();
        hostNetworkFragment = HostNetworkFragment.newInstance();
        wdsFragment = WDSFragment.newInstance();

        backupFragment = BackupFragment.newInstance();
        changePswFragment = ChangePswFragment.newInstance();
        diagnosticToolsFragment = DiagnosticToolsFragment.newInstance();
        resetFragment = ResetFragment.newInstance();
        restartFragment = RestartFragment.newInstance();
        systemLogFragment = SystemLogFragment.newInstance();
        timeAndLanguageFragment = TimeAndLanguageFragment.newInstance();
        updateFragment = UpdateFragment.newInstance();

        ddnsFragment = DDNSFragment.newInstance();
        dmzHostFragment = DMZHostFragment.newInstance();
        routerFragment = RouterFragment.newInstance();
        uPnPSettingFragment = UPnPSettingFragment.newInstance();
        virtualServerFragment = VirtualServerFragment.newInstance();

        fragmentList.add(wanPortSettingFragment);
        fragmentList.add(lanPortSettingFragment);
        fragmentList.add(macAddressFragment);
        fragmentList.add(dhcpServerFragment);
        fragmentList.add(bindingSettingFragment);

        fragmentList.add(guestFragment);
        fragmentList.add(hostNetworkFragment);
        fragmentList.add(wdsFragment);

        fragmentList.add(backupFragment);
        fragmentList.add(changePswFragment);
        fragmentList.add(diagnosticToolsFragment);
        fragmentList.add(resetFragment);
        fragmentList.add(resetFragment);
        fragmentList.add(restartFragment);
        fragmentList.add(systemLogFragment);
        fragmentList.add(timeAndLanguageFragment);
        fragmentList.add(updateFragment);

        fragmentList.add(ddnsFragment);
        fragmentList.add(dmzHostFragment);
        fragmentList.add(routerFragment);
        fragmentList.add(uPnPSettingFragment);
        fragmentList.add(virtualServerFragment);

        supportFragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    }

    private void initView() {
        //初始化界面　首页展示设备管理界面
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout_advanced_setting, fragmentList.get(0))
                .commit();
        currentFragment = fragmentList.get(0);

        //RecyclerView
//        TvRecyclerView.openDEBUG();
//        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rcySetting.setLayoutManager(manager);
//
//        int itemSpace = getResources().getDimensionPixelSize(R.dimen.recyclerView_item_space);
//        rcySetting.addItemDecoration(new SpaceItemDecoration(itemSpace));
//        DevicesAdapter mAdapter = new DevicesAdapter(getActivity(), TAG);
//        rcySetting.setAdapter(mAdapter);
//
//        rcySetting.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
//            @Override
//            public void onItemViewClick(View view, int position) {
//                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.addToBackStack(null).add(R.id.frameLayout, fragmentList.get(position)).replace(R.id.frameLayout, fragmentList.get(position)).commit();
//            }
//
//            @Override
//            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
//
//            }
//        });

        //rcy sidebar

        RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(getDummyDataToPass(), uiHandler);
        rcySidebar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcySidebar.setAdapter(recyclerDataAdapter);
        rcySidebar.setHasFixedSize(true);
    }

    private ArrayList<DummyParentDataItem> getDummyDataToPass() {
        ArrayList<DummyParentDataItem> arrDummyData = new ArrayList<>();
        ArrayList<DummyChildDataItem> childDataItems;

        childDataItems = new ArrayList<>();
        childDataItems.add(new DummyChildDataItem("WAN口设置"));
        childDataItems.add(new DummyChildDataItem("LAN口设置"));
        childDataItems.add(new DummyChildDataItem("Mac地址"));
        childDataItems.add(new DummyChildDataItem("DHCP服务器"));
        childDataItems.add(new DummyChildDataItem("IP与MAC绑定设置"));
        arrDummyData.add(new DummyParentDataItem("网络参数", childDataItems));
        /////////
        childDataItems = new ArrayList<>();
        childDataItems.add(new DummyChildDataItem("主人网络"));
        childDataItems.add(new DummyChildDataItem("访客网络"));
        childDataItems.add(new DummyChildDataItem("WDS"));
        arrDummyData.add(new DummyParentDataItem("无线设置", childDataItems));
        /////////
        childDataItems = new ArrayList<>();
        childDataItems.add(new DummyChildDataItem("虚拟服务器"));
        childDataItems.add(new DummyChildDataItem("DMZ主机"));
        childDataItems.add(new DummyChildDataItem("UPnP设置"));
        childDataItems.add(new DummyChildDataItem("路由功能"));
        childDataItems.add(new DummyChildDataItem("DDNS"));
        arrDummyData.add(new DummyParentDataItem("高级用户", childDataItems));
        /////////
        childDataItems = new ArrayList<>();
        childDataItems.add(new DummyChildDataItem("时间和语言"));
        childDataItems.add(new DummyChildDataItem("软件升级"));
        childDataItems.add(new DummyChildDataItem("恢复出厂设置"));
        childDataItems.add(new DummyChildDataItem("备份"));
        childDataItems.add(new DummyChildDataItem("重启路由器"));
        childDataItems.add(new DummyChildDataItem("修改登录密码"));
        childDataItems.add(new DummyChildDataItem("诊断工具"));
        childDataItems.add(new DummyChildDataItem("系统日志"));
        arrDummyData.add(new DummyParentDataItem("设备管理", childDataItems));
        return arrDummyData;
    }

    private void initData() {

    }

    /**
     * 切换Fragment
     *
     * @param fromFragment：需要隐藏的Fragment
     * @param toFragment：需要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (fromFragment != toFragment) {
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_advanced_setting, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

}
