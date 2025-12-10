package com.example.car_rental_21f21500;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cars21F21500sql extends SQLiteOpenHelper {
    public static final String cdb = "CarDB.db";
    public static final String crdetail = "Cardetail";
    public static final String crid = "CarID";
    public static final String crdy = "totaldays";
    public static final String crrent = "totalpay";
    public static final String crdate = "paydate";
    public static final String phno = "custphno";
    public cars21F21500sql(Context context) {
        super(context, cdb, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + crdetail + " ("
                + crid + " TEXT PRIMARY KEY, "
                + crdy + " TEXT, "
                + crrent + " TEXT, "
                + crdate + " TEXT, "
                + phno + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_db, int new_db) {
        db.execSQL("DROP TABLE IF EXISTS " + crdetail);
        onCreate(db);
    }
    // Adding new car information
    public boolean addcar(String Car_ID, String Totaldays, String TotalPay, String PayDate, String CustPHNO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(crid, Car_ID);
        contentValues.put(crdy, Totaldays);
        contentValues.put(crrent, TotalPay);
        contentValues.put(crdate, PayDate);
        contentValues.put(phno, CustPHNO);

        long result = db.insert(crdetail, null, contentValues);
        db.close();
        return result != -1;
    }
    // car info delete by using ID
    public int deletecar(String CARid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(crdetail, crid + "=?", new String[]{CARid});
    }
    // show car info with customer phone number
    public Cursor showcar(String custtphno) {
        SQLiteDatabase db = this.getReadableDatabase();
        // phone number is given as String
        Cursor cursor = db.rawQuery("SELECT * FROM " + crdetail + " WHERE " + phno + "=?", new String[]{custtphno});
        return cursor;
    }
    // show all car details
    public Cursor SHOWallcarinfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + crdetail, null);
    }
}