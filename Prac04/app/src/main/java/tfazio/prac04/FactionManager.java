package tfazio.prac04;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tfazio.prac04.FactionSchema.FactionTable;

public class FactionManager
{
    private SQLiteDatabase db;
    public FactionManager(Context context)
    {
        this.db = new FactionDBHelper(
                context.getApplicationContext()
                ).getWritableDatabase();

    }

    public void addFaction(Faction faction)
    {
        ContentValues cv = new ContentValues();
        cv.put(FactionTable.Cols.ID,faction.getId());
        cv.put(FactionTable.Cols.NAME,faction.getName());
        cv.put(FactionTable.Cols.STREN,faction.getStrength());
        cv.put(FactionTable.Cols.RELAT,faction.getRelationship());
        db.insert(FactionTable.NAME,null,cv);
    }

    public void updateFaction(Faction faction)
    {
        ContentValues cv = new ContentValues();
        cv.put(FactionTable.Cols.ID,faction.getId());
        cv.put(FactionTable.Cols.NAME,faction.getName());
        cv.put(FactionTable.Cols.STREN,faction.getStrength());
        cv.put(FactionTable.Cols.RELAT,faction.getRelationship());
        String[] whereValue = {String.valueOf(faction.getId())};
        db.update(FactionTable.NAME,cv,FactionTable.Cols.ID + "= ?",whereValue);
    }

    public void removeFaction(Faction faction)
    {
        String[] whereValue = {String.valueOf((faction.getId()))};
        db.delete(FactionTable.NAME,FactionTable.Cols.ID + " = ?", whereValue);
    }


}
