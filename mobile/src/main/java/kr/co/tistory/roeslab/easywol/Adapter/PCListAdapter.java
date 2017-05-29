package kr.co.tistory.roeslab.easywol.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

    public PCListAdapter(Context context) {
        this.mContext = context;
    }

    public void setListData(ArrayList<PCInfoData>list){
        this.mArrayList = list;
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

        viewHolder.mMacTextView.setText(null);
        viewHolder.mNameTextView.setText(null);

        PCInfoData pcInfoData = mArrayList.get(position);
        String name = pcInfoData.getName();
        String mac = pcInfoData.getMac();

//        Log.d(TAG, "name : " + name);
//        Log.d(TAG, "mac : " + mac);

        viewHolder.mNameTextView.setText(name);
        viewHolder.mMacTextView.setText(mac);

        return convertView;
    }

    private class ViewHolder{
        private TextView mNameTextView, mMacTextView;
        private Button mPowerButton;

        public ViewHolder(View convertView) {
            mNameTextView = (TextView)convertView.findViewById(R.id.cell_name_textView);
            mMacTextView = (TextView)convertView.findViewById(R.id.cell_mac_textView);
            mPowerButton = (Button)convertView.findViewById(R.id.cell_power_Button);
        }
    }
}
