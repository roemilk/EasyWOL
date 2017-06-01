package kr.co.tistory.roeslab.easywol.SqlietDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kr.co.tistory.roeslab.easywol.CommonData.DBConstract;
import kr.co.tistory.roeslab.easywol.CommonData.PCInfoData;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by Kim YoungHun on 2017-05-31.
 */

public class DBManager {
    private Context mContext = null;
    private DBHelper mDbHelper = null;
    private SQLiteDatabase mSqLiteDatabase = null;

    public DBManager(Context context) {
        this.mContext = context;
        this.mDbHelper = new DBHelper(context, DBConstract.DB_NAME, null, DBConstract.DB_VERSION);
        this.mSqLiteDatabase = mDbHelper.getWritableDatabase();
    }

    /**
     * 해당 테이블에 데이터를 삽입합니다.
     * @param name
     * @param ip
     * @param mac
     * @param port
     */
    public void insertValues(String name, String ip, String mac, String port){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstract.COL_NAME, name);
        contentValues.put(DBConstract.COL_IP, ip);
        contentValues.put(DBConstract.COL_MAC, mac);
        contentValues.put(DBConstract.COL_PORT, port);
        mSqLiteDatabase.insert(DBConstract.TABLE_NAME, null, contentValues);
    }

    /**
     * 해당 테이블의 레코드를 조회하여 결과를 리스트형태로 반환한다.
     * @return
     */
    public ArrayList<PCInfoData> selectValues(){
        ArrayList<PCInfoData> pcInfoDataArrayList = new ArrayList<>();

        Cursor cursor = mSqLiteDatabase.rawQuery(DBConstract.SQL_SELECT_TABLE, null);

        PCInfoData pcInfoData = null;
        while(cursor.moveToNext()){
            int no = cursor.getInt(0);
            String name = cursor.getString(1);
            String ip = cursor.getString(2);
            String mac = cursor.getString(3);
            String port = cursor.getString(4);

            Log.d(TAG, "no : " + no + " name : " + name + " ip : " + ip + " mac : " + mac + " port : " + port);

            pcInfoData = new PCInfoData();
            pcInfoData.setNo(no);
            pcInfoData.setName(name);
            pcInfoData.setIp(ip);
            pcInfoData.setMac(mac);
            pcInfoData.setPort(port);

            pcInfoDataArrayList.add(pcInfoData);
        }

        return pcInfoDataArrayList;
    }

    /**
     * 해당 일련번호의 레코드를 삭제한다.
     * @param no
     * @return
     */
    public void deleteValues(int no){
        mSqLiteDatabase.delete(DBConstract.TABLE_NAME, DBConstract.COL_NO + "=" + no, null);
    }
}
