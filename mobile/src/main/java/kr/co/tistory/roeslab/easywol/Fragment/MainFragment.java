package kr.co.tistory.roeslab.easywol.Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.tistory.roeslab.easywol.Adapter.PCListAdapter;
import kr.co.tistory.roeslab.easywol.CommonData.PCInfoData;
import kr.co.tistory.roeslab.easywol.R;
import kr.co.tistory.roeslab.easywol.SqlietDB.DBManager;
import redpig.utility.network.MagicPacket;

import static android.R.attr.name;

/**
 * Created by icwer on 2017-05-20.
 * 메인리스트 프래그먼트111
 */

public class MainFragment extends Fragment implements MagicPacket.OnMagicPacketCallbackListener {
    private final String TAG = "MainFragment";

    private View mView = null;
    private ListView mListView = null;
    private MenuItem mDeleteItem, mSettingItem;

    private PCListAdapter mPcListAdapter = null;
    private DBManager mDbManager = null;
    private ArrayList<PCInfoData> mPCInfoDataArrayList = new ArrayList<>();
    private HashMap<Integer, PCInfoData> mSelectedPCInfoHashMap = new HashMap<>();

    private boolean mEditable = false; //편집모드

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
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionMenu..");
        inflater.inflate(R.menu.main, menu);

        mDeleteItem = menu.findItem(R.id.action_delete);
        mSettingItem = menu.findItem(R.id.action_settings);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.action_delete :
                Log.d(TAG, "menu delete..");

                break;
            case R.id.action_settings :
                Log.d(TAG, "menu settings..");

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
        mPCInfoDataArrayList = mDbManager.selectValues();
        mPcListAdapter.setListData(mPCInfoDataArrayList);
        mPcListAdapter.notifyDataSetChanged();
    }

    /**
     * 편집모드를 체크하고 해당 아이템을 선택하여 Map에 저장합니다.
     * @param view
     * @param position
     */
    private void checkEditableSelectItem(View view, int position){
        PCInfoData data = mPCInfoDataArrayList.get(position);
        RelativeLayout rootLay = (RelativeLayout)view.findViewById(R.id.cell_root_lay);
        if(mEditable){ //편집 모드
            if(data.isCheck()){ //선택 해제
                data.setCheck(false);
                mSelectedPCInfoHashMap.remove(position);
                rootLay.setBackground(null);
            }else{ //선택
                data.setCheck(true);
                mSelectedPCInfoHashMap.put(position, data);
                rootLay.setBackgroundColor(Color.YELLOW);
            }
            rootLay.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

            Log.d(TAG, "HashMap Size : " + mSelectedPCInfoHashMap.size());
        }else{ //비편집 모드
            Toast.makeText(getContext(), "비편집모드 클릭", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 선택된 아이템이 모두 해제되었는지 확인하고 Editable 상태를 변경한다.
     * @return
     */
    private boolean checkSelectedItemEmpty(){
        if(mSelectedPCInfoHashMap.size() != 0){
            return false;
        }else{
            mEditable = false;
            Log.d(TAG, "HashMap Size가 0이므로 편집모드를 해제합니다.");
            return true;
        }
    }

    /**
     * 선택된 모든 아이템을 선택 해제합니다.
     */
    private void allUnSelect(){
        mSelectedPCInfoHashMap.clear();
        for(PCInfoData data : mPCInfoDataArrayList){
            data.setCheck(false);
        }
        mPcListAdapter.notifyDataSetChanged();
    }

    /**
     * OnItemClickListener
     */
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "OnItemClickListener... position : " + position + " id : " + id);
            checkEditableSelectItem(view, position);
            checkSelectedItemEmpty();
        }
    };

    /**
     * OnItemLongClickListener
     */
    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "onItemLongClickListener...");
            mEditable = !mEditable;
            checkEditableSelectItem(view, position);
            Log.d(TAG, "Editable State : " + mEditable);

            if(!mEditable){
                mDeleteItem.setVisible(false);
                allUnSelect();
            }else{
                mDeleteItem.setVisible(true);
            }

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

                    Log.d(TAG, "ipAddress : " + ipAddress);
                    Log.d(TAG, "mac : " + mac);
                    MagicPacket.sendMagicPacket(ipAddress, mac, MainFragment.this);
                    break;

                case R.id.cell_gps_Button : //스마트 GPS 기능
                    Toast.makeText(getContext(), "스마트 WOL을 설정합니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onSuccessSendingMagicPacket() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), name + " PC의 전원을 켰습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFailedSendingMagicPacket(Exception exception) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), name + " PC로 매직패킷의 전송이 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
