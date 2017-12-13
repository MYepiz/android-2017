package com.example.marty.pocketbolt;

/**
 * Created by MARTY on 10/12/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBaccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBaccess instance;

    private DBaccess(Context context) {
        this.openHelper = new DBopener(context);
    }

    public static DBaccess getInstance(Context context) {
        if (instance == null) {
            instance = new DBaccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /*
     * Read all  from
     */
    public List<String> getHPvalues() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT POTENCIA FROM POTENCIAS_DEL_MOTOR", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getItemGroups() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT DISTINCT GRUPO FROM TIENDA ORDER BY GRUPO", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getGroupItems(String categoria) {
        ArrayList<String> list = new ArrayList<>();
        String[] args = new String[]{categoria};
        Cursor cursor = database.rawQuery("SELECT OPCION FROM TIENDA WHERE GRUPO = ? ORDER BY OPCION", args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Boolean getIsItemAdquired(String opcion) {
        Boolean list = false;
        String[] args = new String[]{opcion};
        Cursor cursor = database.rawQuery("SELECT ADQUIRIDO FROM TIENDA WHERE OPCION = ?", args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list = Boolean.parseBoolean(cursor.getString(0));
            break;
        }
        cursor.close();
        return list;
    }

    public String getItemPrice(String opcion) {
        Double list = 0.00;
        String[] args = new String[]{opcion};
        Cursor cursor = database.rawQuery("SELECT PRECIO FROM TIENDA WHERE OPCION = ?", args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list = Double.parseDouble(cursor.getString(0));
            break;
        }
        cursor.close();
        return String.format("%1$,.2f", list);
    }
}
