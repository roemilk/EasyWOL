package kr.co.tistory.roeslab.easywol.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.tistory.roeslab.easywol.CommonData.WifiData;
import kr.co.tistory.roeslab.easywol.R;

/**
 * Created by icwer on 2017-05-28.
 */

public class WifiListAdapter extends BaseAdapter {
    private final String TAG = "WifiListAdapter";

    private Context mContext;
    private ArrayList<WifiData> mWifiArrayList;

    private View.OnClickListener mOnClickListener = null;

    public WifiListAdapter(Context context) {
        this.mContext = context;
    }

    public void setWifiArrayList(ArrayList<WifiData> wifiArrayList){
        this.mWifiArrayList = wifiArrayList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return mWifiArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWifiArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_listview_wifi, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.initHolderView(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        String name = mWifiArrayList.get(position).getName();
        String ipAddress = mWifiArrayList.get(position).getIp();
        String macAddress = mWifiArrayList.get(position).getMac();

        viewHolder.getIpAddressTextView().setText(ipAddress);
        viewHolder.getMacAddressTextView().setText(macAddress);
        viewHolder.getAddButton().setOnClickListener(mOnClickListener);

        return convertView;
    }

    private class ViewHolder{
        private TextView ipAddressTextView, macAddressTextView;
        private ImageButton addButton;

        public void initHolderView(View convertView){
            ipAddressTextView = (TextView)convertView.findViewById(R.id.cell_wifi_ipAdress_textView);
            macAddressTextView = (TextView)convertView.findViewById(R.id.cell_wifi_macAddress_textView);
            addButton = (ImageButton)convertView.findViewById(R.id.cell_wifi_add_Button);
        }

        public TextView getIpAddressTextView() {
            return ipAddressTextView;
        }

        public TextView getMacAddressTextView() {
            return macAddressTextView;
        }

        public ImageButton getAddButton() {
            return addButton;
        }
    }
}
