package psu.ajm6684.patientmonitoringsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHandler extends SQLiteOpenHelper {
    public databaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

//    super(context, DATABASE_NAME,null,1);
//    SQLiteDatabase db = this.getWrit

    public boolean insertData(String hospital, String firstName, String lastName, String position, String department, String userName, String employeeID){


        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
