package kr.co.tistory.roeslab.easywol.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;

import com.andexert.library.RippleView;

import kr.co.tistory.roeslab.easywol.R;

/**
 * Created by icwer on 2017-05-26.
 */

public class DialogCustomWIFIInfo extends Dialog implements View.OnClickListener {
    private final String TAG = "DialogCustomWIFIInfo";

    private Context mContext = null;
    private RippleView mSaveRippleView = null;

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

        mSaveRippleView = (RippleView)findViewById(R.id.input_save_rippleView);
        mSaveRippleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick..");

    }
}
