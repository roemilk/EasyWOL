package kr.co.tistory.roeslab.easywol.CommonData;

/**
 * Created by icwer on 2017-05-17.
 * Name, IP, MAC, Port 정보를 가지는 데이터 클래스
 */

public class PCInfoData {
    private int no;
    private String name;
    private String ip;
    private String mac;
    private String port;
    private String gps;
    private boolean check = false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
