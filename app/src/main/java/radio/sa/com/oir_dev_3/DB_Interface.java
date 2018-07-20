package radio.sa.com.oir_dev_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Interface extends SQLiteOpenHelper {


    private static final String TABLE = "STATION_LIST";
    private static final String COL_1 = "STATION_NAME";
    private static final String COL_2 = "STATION_URL";
    String CREATE_CODE = "CREATE TABLE "+TABLE+"( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                COL_1+" TEXT," +
                                                COL_2+" TEXT)";

    public DB_Interface(Context context) {
        super(context, TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CODE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE);
            onCreate(db);
    }

    public boolean add_channel(String name,String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,name);
        cv.put(COL_2,url);
        long result = db.insert(TABLE,null,cv);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor get_the_point(){
        SQLiteDatabase SDB = this.getWritableDatabase();
        String ask = "SELECT * FROM "+TABLE;
        Cursor point = SDB.rawQuery(ask,null);
        return point;
    }

    public Cursor get_channel_id(String channel_name){
        SQLiteDatabase SCB = this.getWritableDatabase();
        String ask_id = "SELECT ID FROM "+TABLE+" WHERE "+COL_1+" = "+"\""+channel_name+"\"";
        Cursor point = SCB.rawQuery(ask_id,null);
        return point;
    }

    public void edit_this(String new_element, int id, String old_element, int elementFlag){
        SQLiteDatabase SEDB = this.getWritableDatabase();
        String query;
        if (elementFlag == 0){
            query = "UPDATE "+TABLE+" SET "+COL_1+" = "+"\""+new_element+"\""+
                           " WHERE "+COL_1+" = "+"\""+old_element+"\""+" AND " +
                           " ID = "+id;
            SEDB.execSQL(query);
        }
        else if(elementFlag == 1)
        {
            query = "UPDATE "+TABLE+" SET "+COL_2+" = "+new_element+
                    " WHERE "+COL_2+" = "+old_element+" AND " +
                    " ID ="+id;
            SEDB.execSQL(query);
        }

    }

    public void delete_this(int c_id){
        SQLiteDatabase SDELDB = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE+" WHERE " +
                       " ID = "+c_id;
        SDELDB.execSQL(query);
    }
}
