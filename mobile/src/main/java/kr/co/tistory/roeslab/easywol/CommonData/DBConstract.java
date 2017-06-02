package kr.co.tistory.roeslab.easywol.CommonData;

/**
 * Created by Kim YoungHun on 2017-05-31.
 * SQLite Databse 상수 정보 정의s
 */

public class DBConstract {

    private DBConstract(){}

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="info.db"; //DB 이름
    public static final String TABLE_NAME = "INFO_T"; //테이블 이름
    public static final String COL_NO = "NO"; //일련번호
    public static final String COL_NAME = "NAME"; //이름
    public static final String COL_IP = "IP"; //아이피
    public static final String COL_MAC = "MAC"; //맥
    public static final String COL_PORT = "PORT"; //포트
    public static final String COL_GPS = "GPS"; //GPS 좌표값

    /**
     * 테이블 생성
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_IP + " TEXT, " + COL_MAC + " TEXT, " + COL_PORT + " TEXT, " +  COL_GPS + " TEXT" + ")";

    /**
     * 테이블 삭제
     */
    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * 테이블 데이터 조회
     */
    public static final String SQL_SELECT_TABLE = "SELECT * FROM " + TABLE_NAME;
}
