package tfazio.prac04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tfazio.prac04.FactionSchema.FactionTable;

public class FactionDBHelper extends SQLiteOpenHelper
{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "factions.db";

    public FactionDBHelper(Context context)
    {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + FactionTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    FactionTable.Cols.ID + ", " +
                    FactionTable.Cols.NAME + ", " +
                    FactionTable.Cols.STREN + ", " +
                    FactionTable.Cols.RELAT +
                    ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
