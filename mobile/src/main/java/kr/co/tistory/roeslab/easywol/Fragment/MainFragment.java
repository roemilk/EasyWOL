package kr.co.tistory.roeslab.easywol.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import kr.co.tistory.roeslab.easywol.Adapter.PCListAdapter;
import kr.co.tistory.roeslab.easywol.CommonData.PCInfoData;
import kr.co.tistory.roeslab.easywol.R;
import kr.co.tistory.roeslab.easywol.SqlietDB.DBManager;

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
        mListView = (ListView)mView.findViewById(R.id.listView_wol);
        mDbManager = new DBManager(getActivity());
        mPCInfoDataArrayList = mDbManager.selectValues();

        mPcListAdapter = new PCListAdapter(getActivity());
        mPcListAdapter.setListData(mPCInfoDataArrayList);
        mListView.setAdapter(mPcListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume...");

//        Log.d(TAG, "PCInfoDataArrayList Size : " + mPCInfoDataArrayList.size());
//
//        mPcListAdapter.notifyDataSetChanged();
    }
}
