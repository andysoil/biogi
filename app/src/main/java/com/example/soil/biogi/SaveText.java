package com.example.soil.biogi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by soil on 2016/4/29.
 */
public class SaveText extends SQLiteOpenHelper {
    private static final String TAG = SaveText.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_PASSWORD = "pas";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_SEX = "sex";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_CELLPHONE = "cellphone";
    private static final String KEY_MAIL ="mail" ;

    public SaveText(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables

    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE "
                +  TABLE_USER + "("
                +  KEY_ID + " TEXT,"
                +  KEY_NAME + " TEXT,"
                +  KEY_PASSWORD + " TEXT,"
                +  KEY_USERNAME + " TEXT,"
                +  KEY_SEX + " TEXT,"
                +  KEY_BIRTHDAY + " DATE,"
                +  KEY_PHONE + " TEXT,"
                +  KEY_CELLPHONE + " TEXT,"
                +  KEY_MAIL + " TEXT,"
                +  KEY_CREATED_AT + " DATE)" ;

        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String _id,String name, String created_at, String password, String username, String sex, String birthday, String phone, String cellphone, String mail) {

        ContentValues values = new ContentValues();
        values.put(KEY_ID, _id); // Name
        values.put(KEY_NAME, name); // Name
        values.put(KEY_PASSWORD, password); // pas At
        values.put(KEY_USERNAME, username); // Name
        values.put(KEY_SEX, sex); // pas At
        values.put(KEY_BIRTHDAY, birthday); // Created At
        values.put(KEY_PHONE, phone); // Name
        values.put(KEY_CELLPHONE, cellphone); // pas At
        values.put(KEY_CREATED_AT, created_at); // Created At
        values.put(KEY_MAIL,mail) ;
        // Inserting Row
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id+username);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("_id",cursor.getString(0));
            user.put("name", cursor.getString(1));
            user.put("password", cursor.getString(2));
            user.put("username", cursor.getString(3));
            user.put("sex", cursor.getString(4));
            user.put("birthday", cursor.getString(5));
            user.put("phone", cursor.getString(6));
            user.put("cellphone", cursor.getString(7));
            user.put("mail", cursor.getString(8));
            user.put("created_at", cursor.getString(9));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
    public void updatepas(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password); // pas At

        String where = KEY_NAME + "='" + email +"'";
        // Inserting Row

        db.update(TABLE_USER, values, where, null) ;
        db.close(); // Closing database connection

        Log.d(TAG, "update password update into sqlite: ");
    }
    public void updateMemberData( final String inname, final String insex, final String inbirthday,
                                  final String inphone, final String incellphone, final String inmail,final String usename) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, inname);
        values.put(KEY_SEX, insex);
        values.put(KEY_BIRTHDAY, inbirthday);
        values.put(KEY_PHONE, inphone);
        values.put(KEY_CELLPHONE, incellphone);
        values.put(KEY_MAIL, inmail);
        String where = KEY_NAME + "='" + usename +"'";
        // Inserting Row

        db.update(TABLE_USER, values,where, null) ;
        db.close(); // Closing database connection

        Log.d(TAG, "update date update into sqlite: ");
    }

}
