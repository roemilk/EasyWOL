package kr.co.tistory.roeslab.easywol.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.andexert.library.RippleView;

import kr.co.tistory.roeslab.easywol.R;
import kr.co.tistory.roeslab.easywol.SqlietDB.DBManager;

/**
 * Created by icwer on 2017-05-26.
 * DB작업1111122444444444444444
 */

public class DialogCustomWIFIInfo extends Dialog implements View.OnClickListener {
    private final String TAG = "DialogCustomWIFIInfo";

    private Context mContext = null;
    private EditText mNameEditText, mIpEditText, mMacEditText, mPortEditText;
    private RippleView mSaveRippleView = null;

    private DBManager mDbManager = null;

    private View.OnClickListener mListener = null;

    public DialogCustomWIFIInfo(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public DialogCustomWIFIInfo(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_wifiinfo);

        mNameEditText = (EditText)findViewById(R.id.input_name_editText);
        mIpEditText = (EditText)findViewById(R.id.input_ipAddress_editText);
        mMacEditText = (EditText)findViewById(R.id.input_macAddress_editText);
        mPortEditText = (EditText)findViewById(R.id.input_port_editText);
        mSaveRippleView = (RippleView)findViewById(R.id.input_save_rippleView);
        mSaveRippleView.setOnClickListener(this);

        mDbManager = new DBManager(mContext);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mListener = onClickListener;
    }

    private void save(){
        String name = mNameEditText.getText().toString().trim();
        String ip = mIpEditText.getText().toString().trim();
        String mac = mMacEditText.getText().toString().trim();
        String port = mPortEditText.getText().toString().trim();

        if(name.equals("") || ip.equals("") || mac.equals("") || port.equals("")){
            Snackbar.make(getCurrentFocus(), "입력되지 않은 항목이 존재합니다.", Snackbar.LENGTH_SHORT).show();
        }

        mDbManager.insertValues(name, ip, mac, port);
        Log.d(TAG, "데이터 저11121111122장 완료");
    }

    @Override
    public void onClick(View v) {
        save();
        switch (v.getId()){
            case R.id.input_save_rippleView :

                break;
        }
        mListener.onClick(v);
    }
}
