package io.b1ackr0se.remindme.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import io.b1ackr0se.remindme.model.Note;

public class DatabaseHelper extends SQLiteOpenHelper {

    //db version
    private static final int DATABASE_VERSION = 1;

    //db name
    private static final String DATABASE_NAME = "NoteManager";

    //table name
    private static final String TABLE_NOTE = "note";

    //table columns name
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DATE = "date";
    private static final String KEY_PENDING = "pending";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //We create table at first run, and later when ugrading
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CONTENT + " TEXT,"
                + KEY_IMAGE + " TEXT," + KEY_DATE + " TEXT," + KEY_PENDING + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop table note if it exists
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOTE;
        sqLiteDatabase.execSQL(DROP_TABLE);

        //Recreate table
        onCreate(sqLiteDatabase);
    }

    //Adds new note to database and returns the result
    public boolean addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT, note.getContent());
        values.put(KEY_IMAGE, note.getImage());
        values.put(KEY_DATE, note.getDate());
        int pending = note.isPending() ? 1 : 0;
        values.put(KEY_PENDING, pending);

        try {
            db.insertOrThrow(TABLE_NOTE, null, values);
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return true;
    }

    public boolean removeNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NOTE, KEY_ID + " = " + note.getId(), null);
        return rowsAffected > 0;
    }

    public ArrayList<Note> getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NOTE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            ArrayList<Note> list = new ArrayList<>();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
                String image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE));
                String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                int pending = cursor.getInt(cursor.getColumnIndex(KEY_PENDING));
                boolean isPending = pending == 1;

                Note note = new Note();
                note.setId(id);
                note.setContent(content);
                note.setImage(image);
                note.setDate(date);
                note.setPending(isPending);

                list.add(note);
            }
            return list;
        }
        return null;
    }

    public ArrayList<Note> getPendingNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE " + KEY_PENDING + " = 1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            ArrayList<Note> list = new ArrayList<>();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
                String image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE));
                String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                int pending = cursor.getInt(cursor.getColumnIndex(KEY_PENDING));
                boolean isPending = pending == 1;

                Note note = new Note();
                note.setId(id);
                note.setContent(content);
                note.setImage(image);
                note.setDate(date);
                note.setPending(isPending);

                list.add(note);
            }
            return list;
        }
        return null;
    }

    public ArrayList<Note> getFinishedNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE " + KEY_PENDING + " = 0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            ArrayList<Note> list = new ArrayList<>();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
                String image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE));
                String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                int pending = cursor.getInt(cursor.getColumnIndex(KEY_PENDING));
                boolean isPending = pending == 1;

                Note note = new Note();
                note.setId(id);
                note.setContent(content);
                note.setImage(image);
                note.setDate(date);
                note.setPending(isPending);

                list.add(note);
            }
            return list;
        }
        return null;
    }

    public boolean updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_ID, note.getId());
        contentValues.put(KEY_CONTENT, note.getContent());
        contentValues.put(KEY_IMAGE, note.getImage());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_PENDING, note.isPending() ? 1 : 0);

        return db.update(TABLE_NOTE, contentValues, KEY_ID + " = " + note.getId(), null) > 0;

    }
}
