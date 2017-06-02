package kr.co.tistory.roeslab.easywol.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.tistory.roeslab.easywol.CommonData.PCInfoData;
import kr.co.tistory.roeslab.easywol.R;

/**
 * Created by icwer on 2017-05-17.
 */

public class PCListAdapter extends BaseAdapter {
    private final String TAG = "PCListAdapter";

    private Context mContext;
    private ArrayList<PCInfoData> mArrayList = null;
    private View.OnClickListener mOnClickListener = null;

    public PCListAdapter(Context context) {
        this.mContext = context;
    }

    public void setListData(ArrayList<PCInfoData>list){
        this.mArrayList = list;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_listview_pcinfo, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.mNameTextView.setText(null);
        viewHolder.mIpTextView.setText(null);
        viewHolder.mMacTextView.setText(null);
        viewHolder.mGpsTextView.setText(null);

        PCInfoData pcInfoData = mArrayList.get(position);
        String name = pcInfoData.getName();
        String ip = pcInfoData.getIp();
        String mac = pcInfoData.getMac();
        String gps = pcInfoData.getGps();
        String port = pcInfoData.getPort();

        viewHolder.mNameTextView.setText(name);
        viewHolder.mIpTextView.setText(ip);
        viewHolder.mMacTextView.setText(mac);
        viewHolder.mGpsTextView.setText(gps);
        viewHolder.mPowerButton.setTag(position);
        viewHolder.mGpsButton.setTag(position);
        viewHolder.mPowerButton.setOnClickListener(mOnClickListener);
        viewHolder.mGpsButton.setOnClickListener(mOnClickListener);
        return convertView;
    }

    private class ViewHolder{
        private TextView mNameTextView, mIpTextView, mMacTextView, mGpsTextView;
        private ImageButton mPowerButton, mGpsButton;

        public ViewHolder(View convertView) {
            mNameTextView = (TextView)convertView.findViewById(R.id.cell_name_textView);
            mIpTextView = (TextView)convertView.findViewById(R.id.cell_ip_textView);
            mMacTextView = (TextView)convertView.findViewById(R.id.cell_mac_textView);
            mGpsTextView = (TextView)convertView.findViewById(R.id.cell_gps_textView);
            mPowerButton = (ImageButton)convertView.findViewById(R.id.cell_power_Button);
            mGpsButton = (ImageButton)convertView.findViewById(R.id.cell_gps_Button);
        }
    }
}
