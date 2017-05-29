package kr.co.tistory.roeslab.easywol.CommonData;

/**
 * Created by icwer on 2017-05-17.
 * PC 리스트를 관리하는 클래스
 */

public class PCInfoData {
    private String mac;
    private String ip;
    private String name;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
