package com.library.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * 获取Android设备的 Wifi Mac 地址
 */
public class MacAddressUtil {

    /**
     * 获取固定格式的mac地址
     * 44-45-53-54-00-00
     */
    public static String getFormatMacAddr() {
        String mac = getMacAddr();
        if (!TextUtils.isEmpty(mac) && mac.contains(":")) {
            mac = mac.replace(":", "-");
        }
        return mac;
    }

    /**
     * 获取固定格式的mac地址
     * 44-45-53-54-00-00
     */
    public static String getFormatMacAddr(Context context) {
        String mac = getMacAddr(context);
        if (!TextUtils.isEmpty(mac) && mac.contains(":")) {
            mac = mac.replace(":", "-");
        }
        return mac;
    }

    /**
     * 获取mac地址
     * 对6.0系统可能获取不到(会返回 02:00:00:00:00:00)
     */
    public static String getMacAddr(Context context) {
        String mac = "";
        // 获取wifi管理器
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        mac = wifiInfo.getMacAddress();
        return mac;
    }

    /**
     * 获取mac地址
     */
    public static String getMacAddr() {
        String mac = "02:00:00:00:00:00"; // 6.0系统获取不到时默认返回的值
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("eth0") && !nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return mac;
                }

                StringBuilder macBuffer = new StringBuilder();
                for (byte b : macBytes) {
                    String str = Integer.toHexString(b & 0xFF);
                    macBuffer.append(str.length() == 1 ? 0 + str : str);
                    macBuffer.append(":");
                }
                if (macBuffer.length() > 0) {
                    macBuffer.deleteCharAt(macBuffer.length() - 1);
                }
                mac = macBuffer.toString();
                return mac;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

}
