package kr.co.tistory.roeslab.easywol.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tistory.roeslab.easywol.Adapter.WifiListAdapter;
import kr.co.tistory.roeslab.easywol.CommonData.WifiData;
import kr.co.tistory.roeslab.easywol.Dialog.DialogInputPCInfo;
import kr.co.tistory.roeslab.easywol.R;

/**
 * Created by icwer on 2017-05-27.
 */

public class WifiListActivity extends AppCompatActivity {

    private final String TAG = "WifiListActivity";
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 0x001 ;

    private ListView mListView = null;
    private WifiListAdapter mWifiListAdapter = null;

    private ArrayList<WifiData> mWifiDataArrayList = null;
    private WifiManager mWifiManager = null;

    private DialogInputPCInfo mDialogPCInfo = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifilist);

        initTestArrayList();
        init();
        scanAP();
    }

    private void initTestArrayList(){
        mWifiDataArrayList = new ArrayList<>();

        WifiData wifiData = new WifiData();
        wifiData.setIp("111.111.111.111");
        wifiData.setMac("AA:FF:DD:CC");
        wifiData.setName("테스트 네임");
        mWifiDataArrayList.add(wifiData);
    }

    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        mDialogPCInfo = new DialogInputPCInfo(WifiListActivity.this, R.style.Theme_TransparentDialog);
        mDialogPCInfo.setOnClickListener(onClickListener);

        mListView = (ListView)findViewById(R.id.wifi_listView);
        mWifiListAdapter = new WifiListAdapter(this);
        mWifiListAdapter.setWifiArrayList(mWifiDataArrayList);
        mWifiListAdapter.setOnClickListener(onClickListener);
        mListView.setAdapter(mWifiListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wifi_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSeleted..");

        switch(item.getItemId()){
            case android.R.id.home :
                Log.d(TAG, "onOptionsItemSelected home menu..");
                onBackPressed();
                break;

            case R.id.action_bar_wifi_input :
                Log.d(TAG, "onOptionsItemSelected 직접 입력하기 버튼");
                mDialogPCInfo.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void scanWiFi(){
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();

        if(activeNetworkInfo != null){

            if(activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI && activeNetworkInfo.isConnectedOrConnecting()){
                Log.d(TAG, "WiFi Connected..");

            }else if(activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE && activeNetworkInfo.isConnectedOrConnecting()){
                Log.d(TAG, "3G/4G Connected..");
            }else{
                Log.d(TAG, "Not Connected Network...");
            }
        }else{
            Log.d(TAG, "Non Device Network Lan Card...");
        }

        // wifi 정보 가져오기
        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        DhcpInfo dhcpInfo = mWifiManager.getDhcpInfo();

        String name = wifiInfo.getSSID();
        String macAddress = wifiInfo.getMacAddress();
        int wIp = dhcpInfo.ipAddress;
        int wNetmask = dhcpInfo.netmask;
        int wGateway = dhcpInfo.gateway;
        int wDns1 = dhcpInfo.dns1;

        String ip = String.format("%d.%d.%d.%d", (wIp & 0xff), (wIp >> 8 & 0xff), (wIp >> 16 & 0xff), (wIp >> 24 & 0xff));
        String subnet = String.format("%d.%d.%d.%d", (wNetmask & 0xff), (wNetmask >> 8 & 0xff), (wNetmask >> 16 & 0xff), (wNetmask >> 24 & 0xff));
        String gateway = String.format("%d.%d.%d.%d", (wGateway & 0xff), (wGateway >> 8 & 0xff), (wGateway >> 16 & 0xff), (wGateway >> 24 & 0xff));
        String dns = String.format("%d.%d.%d.%d", (wDns1 & 0xff), (wDns1 >> 8 & 0xff), (wDns1 >> 16 & 0xff), (wDns1 >> 24 & 0xff));

        Log.d(TAG, "Name : " + name);
        Log.d(TAG, "macAddress : " + macAddress);
        Log.d(TAG, "IP : " + ip);
        Log.d(TAG, "Subnet : " + subnet);
        Log.d(TAG, "Gateway : " + gateway);
        Log.d(TAG, "DNS : " + dns);
    }

    private void scanAP(){
        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        mWifiManager.startScan();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiReceiver, filter);
    }

    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive.." + intent.getAction());

            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                searchWifi();
            }
        }
    };

    public void searchWifi() {
        Log.d(TAG, "searchWifi..");

        List<ScanResult> apList = mWifiManager.getScanResults();
        Log.d(TAG, "apiList Size : " + apList.size());

        if (mWifiManager.getScanResults() != null) {
            int size = apList.size();
            for (int i = 0; i < size; i++) {
                ScanResult scanResult = (ScanResult) apList.get(i);
                Log.d(TAG, "SSID : " + scanResult.SSID);
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClickListener...");

            switch (v.getId()){
                case R.id.cell_wifi_add_Button :
                    Log.d(TAG, "addButton Click...");
                    mDialogPCInfo.show();
                    break;
            }
        }
    };
}
