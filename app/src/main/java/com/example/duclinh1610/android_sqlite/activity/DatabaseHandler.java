package com.example.duclinh1610.android_sqlite.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duclinh1610.android_sqlite.activity.Student;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE_NUMBER = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE_NUMBER);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    // Adding new Contact
    void addContact(Student contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_PHONE_NUMBER, contact.getPhone_number());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Getting single contact
    private Student getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE_NUMBER },
                KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Student contact = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        //return contact
        return contact;
    }

    //  Getting All Contact
    public ArrayList<Student> getAllContacts(){
        ArrayList<Student> contactList = new ArrayList<Student>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Student contact = new Student();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setAddress(cursor.getString(2));
                contact.setPhone_number(cursor.getString(3));
                // adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contactList
        return contactList;
    }

    // Updating single contact
    public int updateContact(Student contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_PHONE_NUMBER, contact.getPhone_number());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + "=?",
                new String[]{ String.valueOf(contact.getId())});
    }

    // Deleting single contact
    public void  deleteContact(Student contact){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[] { String.valueOf(contact.getId())});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
