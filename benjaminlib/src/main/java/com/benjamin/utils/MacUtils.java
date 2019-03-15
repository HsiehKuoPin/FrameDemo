package com.benjamin.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MacUtils {

    /**
     * 获取失败默认返回值
     */
    public static final String ERROR_MAC_STR = "02:00:00:00:00:00";

    // Wifi 管理器
    private static WifiManager mWifiManager;

    /**
     * 实例化WifiManager对象
     *
     * @param context 当前上下文对象
     * @return
     */
    private static WifiManager getInstant(Context context) {
        if (mWifiManager == null) {
            mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }
        return mWifiManager;
    }

    /**
     * 开启wifi
     */
    public static void getStartWifiEnabled() {
        // 判断当前wifi状态是否为开启状态
        if (!mWifiManager.isWifiEnabled()) {
            // 打开wifi 有些设备需要授权
            mWifiManager.setWifiEnabled(true);
        }
    }

    /**
     * 获取手机设备MAC地址
     * MAC地址：物理地址、硬件地址，用来定义网络设备的位置
     * modify by heliquan at 2018年1月17日
     *
     * @param context
     * @return
     */
    public static String getMobileMAC(Context context) {
        mWifiManager = getInstant(context);
        // 如果当前设备系统大于等于6.0 使用下面的方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getAndroidHighVersionMac();
        } else { // 当前设备在6.0以下
            return getAndroidLowVersionMac();
        }
    }

    /**
     * Android 6.0 设备兼容获取mac
     * 兼容原因：从Android 6.0之后，Android 移除了通过WiFi和蓝牙API来在应用程序中可编程的访问本地硬件标示符。
     * 现在WifiInfo.getMacAddress()和BluetoothAdapter.getAddress()方法都将返回：02:00:00:00:00:00
     *
     * @return
     */
    public static String getAndroidHighVersionMac() {
        String str = "";
        String macSerial = "";
        try {
            // 由于Android底层基于Linux系统 可以根据shell获取
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if ("".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
//                e.printStackTrace();
                macSerial = getAndroidVersion7MAC();
            }
        }
        return macSerial;
    }

    /**
     * Android 6.0 以下设备获取mac地址 获取失败默认返回：02:00:00:00:00:00
     *
     * @return
     */
    @NonNull
    private static String getAndroidLowVersionMac() {
        try {
            WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
            String mac = wifiInfo.getMacAddress();
            if (TextUtils.isEmpty(mac)) {
                return ERROR_MAC_STR;
            } else {
                return mac;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mac", "get android low version mac error:" + e.getMessage());
            return ERROR_MAC_STR;
        }
    }

    /**
     * 兼容7.0获取不到的问题
     *
     * @return
     */
    public static String getAndroidVersion7MAC() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            Log.e("mac", "get android version 7.0 mac error:" + e.getMessage());
        }
        return ERROR_MAC_STR;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

    /**
     * 根据IP地址获取MAC地址
     *
     * @return
     */
    public static String getLocalMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    private static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && !ip.getHostAddress().contains(":"))
                        break;
                    else
                        ip = null;
                }
                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

}