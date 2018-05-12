package mrc.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 05-01-2018.
 */

public class MyHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";


    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //String CREATE_TABLE_CONTACTS ="CREATE TABLE " + TABLE_CONTACTS + "(" + COLUMN_ID + "INTEGER PRIMARY KEY," +
        //COLUMN_NAME + "TEXT," + ")";

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT"
                + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

    }

    // adding more rows to the database

    public void addRow(Contacts contacts) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contacts.get_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, contentValues);
        db.close();
    }

    public void delRow(Contacts contacts) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_NAME + " = ?",
                new String[]{String.valueOf(contacts.get_name())});
        db.close();
    }

    public List<Contacts> getAllContacts()
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_CONTACTS;
        Cursor cursor=db.rawQuery(query,null);

        List<Contacts> list=new ArrayList<Contacts>();

        if(cursor.moveToFirst())
        {
            do{
                Contacts contacts=new Contacts();
                contacts.set_id(Integer.parseInt(cursor.getString(0)));
                contacts.set_name(cursor.getString(1));
                list.add(contacts);
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return  list;
    }
}
