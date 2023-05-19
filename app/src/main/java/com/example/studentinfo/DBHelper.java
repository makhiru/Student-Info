package com.example.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Student";
    private static String TABLE_NAME = "Student_information";
    private static String ID = "id";
    private static String NAME = "name";
    private static String F_NAME = "fname";
    private static String ROLL_NO = "rollno";
    private static String STANDARD = "standard";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Quary = "CREATE TABLE "
                + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + F_NAME + " TEXT,"
                + ROLL_NO + " TEXT,"
                + STANDARD + " TEXT)";

        db.execSQL(Quary);
    }

    public void adddata(String name, String fname, String rollno, String standard) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(F_NAME, fname);
        cv.put(ROLL_NO, rollno);
        cv.put(STANDARD, standard);

        database.insert(TABLE_NAME, null, cv);
        database.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<student> showinfo() {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<student> arrstudent = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String fname = cursor.getString(2);
            String rollno = cursor.getString(3);
            String standard = cursor.getString(4);

            student student = new student(name, fname, rollno, standard, id);
            arrstudent.add(student);
        }
        return arrstudent;
    }

    public void deletedata(int id) {

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void updatecontact(student student) {

        SQLiteDatabase database = this.getWritableDatabase();

        String quary = "UPDATE " + TABLE_NAME + " SET "
                + NAME + " = '" + student.getName() + "', "
                + F_NAME + " = '" + student.getFname() + "', "
                + ROLL_NO + " = '" + student.getRollno() + "', "
                + STANDARD + " = '" + student.getStandard() + "' WHERE "
                + ID + " = " + student.getId();

        database.execSQL(quary);
    }
}
