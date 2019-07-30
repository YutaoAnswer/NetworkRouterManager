package com.trigletop.networkroutermanager.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DeviceAuthority;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DeviceSpeed;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DeviceTimeLimit;

public class Device implements Serializable {
    protected String nickname = "";
    protected String hostname = "";
    protected String mac = "";
    protected int online = -1;
    protected String ip = "";
    protected int port = -1;
    protected String dev = "";
    protected String icon = "";
    protected int count;
    protected int restrictenable;
    protected int usageenable;
    protected DeviceAuthority authority = new DeviceAuthority();
    protected DeviceSpeed speed = new DeviceSpeed();
    protected List<DeviceTimeLimit> timelist = new ArrayList();
    protected int warn;
    protected long lease_start;
    protected long lease_time;
    protected int is_ap;

    public Device() {
    }

    public void setNickname(String var1) {
        this.nickname = var1;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setHostname(String var1) {
        this.hostname = var1;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setMac(String var1) {
        this.mac = var1;
    }

    public String getMac() {
        return this.mac;
    }

    public void setOnline(int var1) {
        this.online = var1;
    }

    public int getOnline() {
        return this.online;
    }

    public void setIp(String var1) {
        this.ip = var1;
    }

    public String getIp() {
        return this.ip;
    }

    public void setPort(int var1) {
        this.port = var1;
    }

    public int getPort() {
        return this.port;
    }

    public void setDev(String var1) {
        this.dev = var1;
    }

    public String getDev() {
        return this.dev;
    }

    public void setIcon(String var1) {
        this.icon = var1;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setAuthority(DeviceAuthority var1) {
        this.authority = var1;
    }

    public DeviceAuthority getAuthority() {
        return this.authority;
    }

    public void setSpeed(DeviceSpeed var1) {
        this.speed = var1;
    }

    public DeviceSpeed getSpeed() {
        return this.speed;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int var1) {
        this.count = var1;
    }

    public int getRestrictenable() {
        return this.restrictenable;
    }

    public void setRestrictenable(int var1) {
        this.restrictenable = var1;
    }

    public int getUsageenable() {
        return this.usageenable;
    }

    public void setUsageenable(int var1) {
        this.usageenable = var1;
    }

    public void setTimelist(List<DeviceTimeLimit> var1) {
        this.timelist = var1;
    }

    public List<DeviceTimeLimit> getTimelist() {
        if (this.timelist == null) {
            this.timelist = new ArrayList();
        }

        return this.timelist;
    }

    public int getWarn() {
        return this.warn;
    }

    public void setWarn(int var1) {
        this.warn = var1;
    }

    public long getLease_start() {
        return this.lease_start;
    }

    public void setLease_start(long var1) {
        this.lease_start = var1;
    }

    public long getLease_time() {
        return this.lease_time;
    }

    public void setLease_time(long var1) {
        this.lease_time = var1;
    }

    public int getIs_ap() {
        return this.is_ap;
    }

    public void setIs_ap(int var1) {
        this.is_ap = var1;
    }

}
