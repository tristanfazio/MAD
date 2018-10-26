package tfazio.mad_assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tfazio.mad_assignment.Database.GameDataSchema.AreaTable;
import tfazio.mad_assignment.Database.GameDataSchema.ItemTable;
import tfazio.mad_assignment.Database.GameDataSchema.PlayerTable;

public class GameDataSQLHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "gamedata.db";

    public GameDataSQLHelper(Context context)
    {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table " + PlayerTable.NAME + "(" +
                PlayerTable.Cols.ID + " primary key, " +
                PlayerTable.Cols.ROWLOC + ", " +
                PlayerTable.Cols.COLLOC + ", " +
                PlayerTable.Cols.CASH + ", " +
                PlayerTable.Cols.HEALTH + ", " +
                PlayerTable.Cols.MASS +
                ")");

        db.execSQL("create table " + AreaTable.NAME + "(" +
                AreaTable.Cols.ID + " primary key, " +
                AreaTable.Cols.ISTOWN + ", " +
                AreaTable.Cols.DESCRIPTION + ", " +
                AreaTable.Cols.STARRED + ", " +
                AreaTable.Cols.EXPLORED + ", " +
                AreaTable.Cols.X + ", " +
                AreaTable.Cols.Y +
                ")");


        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.ID + " primary key," +
                ItemTable.Cols.NAME + ", " +
                ItemTable.Cols.DESCRIPTION + ", " +
                ItemTable.Cols.VALUE + ", " +
                ItemTable.Cols.USEABLE + ", " +
                ItemTable.Cols.NUMBER + ", " +
                ItemTable.Cols.QUEST + ", " +
                ItemTable.Cols.OWNER +
                ")");

    }
    @Override public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {

    }
}
