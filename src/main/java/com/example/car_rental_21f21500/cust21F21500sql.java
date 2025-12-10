package com.example.car_rental_21f21500;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cust21F21500sql extends SQLiteOpenHelper {

    public static final String ctdb = "CustDB.db";
    public static final String cinfo = "custinfo";
    public static final String ctid = "custid";
    public static final String ctname = "custname";
    public static final String ctpass = "custpass";

    public cust21F21500sql(Context context){
        super(context, ctdb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + cinfo +
                " (" + ctid + " TEXT PRIMARY KEY, " +
                ctname + " TEXT, " +
                ctpass + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_db, int new_db) {
        db.execSQL("DROP TABLE IF EXISTS " + cinfo);
        onCreate(db);
    }

    public boolean addcust(String Customer_ID, String Customername, String Customerpass){
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ctid, Customer_ID);
        contentValues.put(ctname, Customername);
        contentValues.put(ctpass, Customerpass);

        long result = sdb.insert(cinfo, null, contentValues);
        sdb.close();

        return result != -1;
    }


    public String getPass(String custid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + ctpass + " FROM " + cinfo + " WHERE " + ctid + " = ?",
                new String[]{custid}
        );

        if (cursor.moveToFirst()) {
            String Password = cursor.getString(0);
            cursor.close();
            db.close();
            return Password;
        }

        cursor.close();
        db.close();
        return null;
    }

    public Cursor getaccount(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + cinfo, null);
    }
}
