package edu.info.management_parcare_v02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int dbVersion = 1;
    public static final String DATABASE_NAME = "numere_masini.db";
    public static final String TABLE_NAME = "tabel_masini";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NUME";
    public static final String COL_3 = "PRENUME";
    public static final String COL_4 = "NUMAR_DOSAR";
    public static final String COL_5 = "COD_ORGANIGRAMA";
    public static final String COL_6 = "CENTRU_COST";
    public static final String COL_7 = "NUMAR_AUTO_1";
    public static final String COL_8 = "NUMAR_AUTO_2";
    public static final String COL_9 = "NUMAR_AUTO_3";
    SQLiteDatabase db;
    private static final String createTable = "Create Table " + TABLE_NAME + "("
            + COL_1 + " Integer Primary Key AutoIncrement," +
            COL_2 + " Text," +
            COL_3 + " Text," +
            COL_4 + " Text," +
            COL_5 + " Text," +
            COL_6 + " Text," +
            COL_7 + " Text," +
            COL_8 + " Text," +
            COL_9 + " Text" + ");";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, dbVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        this.db = SQLiteDatabase;
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }


    public boolean insertData(String id, String nume, String prenume, String numar_dosar, String cod_organigrama, String centru_cost, String numar_auto_1, String numar_auto_2, String numar_auto_3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nume);
        contentValues.put(COL_3, prenume);
        contentValues.put(COL_4, numar_dosar);
        contentValues.put(COL_5, cod_organigrama);
        contentValues.put(COL_6, centru_cost);
        contentValues.put(COL_7, numar_auto_1);
        contentValues.put(COL_8, numar_auto_2);
        contentValues.put(COL_9, numar_auto_3);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String id, String nume, String prenume, String numar_dosar,String cod_organigrama, String centru_cost, String numar_auto_1, String numar_auto_2, String numar_auto_3  ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_9, numar_auto_3);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean cautaNumar(String cautare) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_7}, COL_7 + "=?", new String[]{cautare}, null, null, null, "1");
        if (cursor.moveToFirst()) {
            return true; // a fost gasit un rand
        }
        return false; // nu a fost gasit un rand

    }
}

