package kr.co.tistory.roeslab.easywol.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.tistory.roeslab.easywol.Adapter.PCListAdapter;
import kr.co.tistory.roeslab.easywol.CommonData.PCInfoData;
import kr.co.tistory.roeslab.easywol.R;
import kr.co.tistory.roeslab.easywol.SqlietDB.DBManager;
import redpig.utility.network.MagicPacket;

/**
 * Created by icwer on 2017-05-20.
 * 메인리스트 프래그먼트111
 */

public class MainFragment extends Fragment {
    private final String TAG = "MainFragment";

    private View mView = null;
    private ListView mListView = null;
    private PCListAdapter mPcListAdapter = null;
    private DBManager mDbManager = null;

    private ArrayList<PCInfoData> mPCInfoDataArrayList = new ArrayList<>();

    public MainFragment() {
    }

    public static Fragment newInstanceFragment(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated..");

        mListView = (ListView)mView.findViewById(R.id.listView_wol);
        mDbManager = new DBManager(getActivity());
        mPcListAdapter = new PCListAdapter(getActivity());
        mPcListAdapter.setOnClickListener(onClickListener);
        mListView.setAdapter(mPcListAdapter);
        mListView.setOnItemClickListener(onItemClickListener);
        mListView.setOnItemLongClickListener(onItemLongClickListener);
        mListView.setLongClickable(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
        mPCInfoDataArrayList = mDbManager.selectValues();
        mPcListAdapter.setListData(mPCInfoDataArrayList);
        mPcListAdapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "OnItemClickListener...");

        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "onItemLongClickListener...");
            return true;
        }
    };

    /**
     * OnClickListener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick..");
            switch (v.getId()){
                case R.id.cell_power_Button : //전원 켜기 기능
                    int position = (Integer)v.getTag();
                    PCInfoData pcInfoData = mPCInfoDataArrayList.get(position);
                    String name = pcInfoData.getName();
                    String ipAddress = pcInfoData.getIp();
                    String mac = pcInfoData.getMac();
                    MagicPacket.sendMagicPacket(ipAddress, mac);

                    Toast.makeText(getContext(), name + " PC의 전원을 켰습니다.", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.cell_gps_Button : //스마트 GPS 기능
                    Toast.makeText(getContext(), "스마트 WOL을 설정합니다.", Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };
}
