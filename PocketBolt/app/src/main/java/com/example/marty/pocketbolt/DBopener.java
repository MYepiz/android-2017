package com.example.marty.pocketbolt;

/**
 * Created by MARTY on 10/12/2017.
 */
import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBopener extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "Alex";
    private static final int DATABASE_VERSION = 1;

    public DBopener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
