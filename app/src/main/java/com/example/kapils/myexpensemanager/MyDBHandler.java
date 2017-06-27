package com.example.kapils.myexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.security.PublicKey;
import java.text.DateFormat;
import java.util.Date;


public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="transactions.db";

    public final String TABLE_TRANSACTION ="transactions";
    public final String COLUMN_ID ="_id";
    public final String COLUMN_TITLE ="title";
    public final String COLUMN_DESC ="description";
    public final String COLUMN_TYPE ="type";
    public final String COLUMN_CATEGORY ="category";
    public final String COLUMN_AMOUNT ="amount";
    public final String COLUMN_DATE ="date";

    public final String TABLE_CATEGORY ="category";
    public final String COLUMN_CAT_NAME ="catname";
    public final String COLUMN_CAT_ID ="cat_id";

    public final String TABLE_TODOLIST = "todolist";
    public final String COLUMN_TASK_ID = "_id";
    public final String COLUMN_TASK = "task";
    public final String COLUMN_DNT = "dnt";
    public final String COLUMN_STATUS = "status";

    public final String TABLE_NOTES = "notes";
    public final String COLUMN_NOTE_ID = "_id";
    public final String COLUMN_NOTE_TITLE = "title";
    public final String COLUMN_NOTE = "note";
    public final String COLUMN_DT = "dnt";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TRANSACTION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT , "
                + COLUMN_DESC + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_CATEGORY + " TEXT, "
                + COLUMN_AMOUNT + " REAL, "
                + COLUMN_DATE + " TEXT"
                +"); "

                ;

        db.execSQL(query);

        query = "create table "+TABLE_CATEGORY + "("
                +COLUMN_CAT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CAT_NAME + " REAL );";

        db.execSQL(query);


        addCategory("Misc",db);
        addCategory("Automobile",db);
        addCategory("Nice",db);

        query = "create table "+TABLE_TODOLIST + "("
                + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TASK + " TEXT, "
                + COLUMN_DNT + " TEXT, "
                + COLUMN_STATUS + " INTEGER"
                +"); ";
        db.execSQL(query);

        query = "create table "+TABLE_NOTES+" ("
                +COLUMN_NOTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_NOTE_TITLE+" TEXT, "
                +COLUMN_NOTE+" TEXT, "
                +COLUMN_DT+" TEXT"
                +"); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TODOLIST);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }


    public boolean addExpense(String title, String desc, String type, float amount, String cat, String date){
        boolean b = false;

        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_DESC, desc);
            values.put(COLUMN_TYPE, type);
            values.put(COLUMN_AMOUNT, amount);
            values.put(COLUMN_CATEGORY, cat);
            values.put(COLUMN_DATE, date);

            db.insert(TABLE_TRANSACTION, null, values);
            db.close();
            b=true;
        }catch (Exception e) {
            b = false;
        }

        return b;
    }

    public Cursor getAllExpense(){
        String query = "SELECT * FROM "+TABLE_TRANSACTION;

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(query, null);
    }

    public Cursor getAllCategories(){
        String query = "SELECT "+COLUMN_CAT_ID+" as _id,"+COLUMN_CAT_NAME+" FROM "+TABLE_CATEGORY;

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        return c;
    }

    public void addCategory(String cat, SQLiteDatabase db){
        if(db==null){
            db = getWritableDatabase();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAT_NAME,cat);
        db.insert(TABLE_CATEGORY,null,values);
    }

    public boolean addTask(String task, String dnt, int status){
        boolean b = false;

        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_TASK, task);
            values.put(COLUMN_DNT, dnt);
            values.put(COLUMN_STATUS, status);


            db.insert(TABLE_TODOLIST, null, values);
            db.close();
            b=true;
        }catch (Exception e) {
            b = false;
        }

        return b;
    }



    public Cursor getAllTasks(){
        String query = "SELECT * FROM "+TABLE_TODOLIST+" ORDER BY "+COLUMN_TASK_ID+ " ASC";

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(query, null);
    }


    public int deleteTask(String dnt)
    {
         SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_TODOLIST, COLUMN_DNT+" = ?", new String[]{dnt});
    }


    public int updateStatus(String dnt,int state, String task, char choice)
    {
        int result=-1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(choice == 's'){
            contentValues.put(COLUMN_STATUS, state);
            result =  db.update(TABLE_TODOLIST, contentValues, COLUMN_DNT+" =?", new String[]{dnt});}

        if(choice == 't'){
            Log.d("inside func",""+dnt);
            contentValues.put(COLUMN_TASK, task);
            contentValues.put(COLUMN_DNT, DateFormat.getDateTimeInstance().format(new Date()));
            result = db.update(TABLE_TODOLIST, contentValues, COLUMN_DNT+" =?", new String[]{dnt});
        }

        return result;
    }

    public boolean add_dbNote(String title, String note, String dt){
        boolean b =false;
        try{
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_NOTE, note);
            values.put(COLUMN_NOTE_TITLE, title);
            values.put(COLUMN_DT, dt);

            db.insert(TABLE_NOTES, null, values);
            db.close();
            b = true;
        }catch (Exception e){
            b = false;
        }
        return b;
    }

    public Cursor getAllNotes(){
        String query = "SELECT * FROM "+TABLE_NOTES;
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public int deleteNote(String dt)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NOTES, COLUMN_DT+" =?", new String[]{dt});
    }

    public int updateNote(String title, String note, String dt){
        int result = -1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, title);
        values.put(COLUMN_NOTE, note);
        values.put(COLUMN_DT, DateFormat.getDateTimeInstance().format(new Date()));
        result = db.update(TABLE_NOTES, values, COLUMN_DT+" =?", new String[]{dt});
        return result;
    }



    /*public Cursor getDWExpense(){
        String query = "SELECT MID("+COLUMN_DATE+",1,) FROM "+TABLE_TRANSACTION;

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(query, null);
    }*/

   /* public Cursor getExpenseByCat(String cat1, String cat2, String cat3){


        Toast.makeText(, cat1, Toast.LENGTH_SHORT).show();
        String query = "SELECT * FROM "+TABLE_TRANSACTION+" WHERE "+COLUMN_CATEGORY+"="+cat1;

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(query, null);
    }*/
}
