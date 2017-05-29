package kr.co.tistory.roeslab.easywol.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.tistory.roeslab.easywol.R;

/**
 * Created by icwer on 2017-05-20.
 * 설정 프래그먼트
 */

public class SettingFragment extends Fragment {
    private final String TAG = "SettingFragment";

    public SettingFragment() {
        super();
    }

    public static Fragment newInstanceFragment(){
        SettingFragment settingFragment = new SettingFragment();

        return settingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}
