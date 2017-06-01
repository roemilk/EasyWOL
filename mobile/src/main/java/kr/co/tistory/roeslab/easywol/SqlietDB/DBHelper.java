package kr.co.tistory.roeslab.easywol.SqlietDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kr.co.tistory.roeslab.easywol.CommonData.DBConstract;

/**
 * Created by Kim YoungHun on 2017-05-31.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final String TAG = "DBHelper";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "DBHelper 생성");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Database Create : " + DBConstract.SQL_CREATE_TABLE);
        db.execSQL(DBConstract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConstract.SQL_DROP_TABLE);

        onCreate(db);
    }
}
