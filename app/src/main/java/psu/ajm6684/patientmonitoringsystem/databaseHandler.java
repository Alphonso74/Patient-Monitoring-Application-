package psu.ajm6684.patientmonitoringsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PatientMonitoringSystem.db";
    public static final String TABLE_NAME = "doctors";
    public static final String COL_1 = "Hospital";
    public static final String COL_2 = "First_Name";
    public static final String COL_3 = "Last_Name";
    public static final String COL_4 = "Position";
    public static final String COL_5 = "Department";
    public static final String COL_6 = "Username";
    public static final String COL_7 = "Employee_ID";

    SQLiteDatabase db;

    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }


    public boolean insertData(String hospital, String firstName, String lastName, String position, String department, String userName, String employeeID){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,hospital);
        contentValues.put(COL_2,firstName);
        contentValues.put(COL_3,lastName);
        contentValues.put(COL_4,position);
        contentValues.put(COL_5,department);
        contentValues.put(COL_6,userName);
        contentValues.put(COL_7,employeeID);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (HOSPITAL TEXT, FIRSTNAME TEXT, LASTNAME TEXT, POSITION TEXT , DEPARTMENT TEXT, USERNAME TEXT,EMPLOYEEID TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
