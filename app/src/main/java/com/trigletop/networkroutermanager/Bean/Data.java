package com.trigletop.networkroutermanager.Bean;

public class Data {

    /*矽昌sdk管理*/
    //后台 app key
    public static final String appKey = "a2d44e04616af5c399a4dc19a2361874";//mine
//    public static final String appKey = "c20ad4d76fe97759aa27a0c99bff6710";

    //后台 app secret
    public static final String appSecret = "7a0b06cc2877bae5a6c0ff3f92f60c3c";//mine
//    public static final String appSecret = "864850023f299568b353d21e55c6c892";

    public static final String[] title_data = {"常用设置", "高级设置", "用户管理"};

    public static final String[] network_param_data = {"WAN口设置", "LAN口设置", "Mac地址", "DHCP服务器", "IP与MAC绑定设置"};
    public static final String[] wireless_setting_data = {"主人网络", "访客网络", "WDS"};
    public static final String[] advanced_account_data = {"虚拟服务器", "DMZ主机", "UPnP设置", "路由功能", "DDNS"};
    public static final String[] device_management_data = {"时间和语言", "软件升级", "恢复出厂设置", "备份", "重启路由器", "修改登录密码", "诊断工具", "系统日志"};

    // TODO: 19-7-31 转化为不变的字符串
    public static final int handler_message_setWanType_PPOE = 3;
    public static final int handler_message_setWanType_static = 4;
    public static final int handler_message_setWanType_auto = 5;
}
