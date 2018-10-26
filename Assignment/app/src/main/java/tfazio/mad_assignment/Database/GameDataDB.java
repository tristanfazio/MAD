package tfazio.mad_assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tfazio.mad_assignment.DataClasses.Area;
import tfazio.mad_assignment.DataClasses.Equipment;
import tfazio.mad_assignment.DataClasses.Food;
import tfazio.mad_assignment.DataClasses.Item;
import tfazio.mad_assignment.Database.GameDataSchema.AreaTable;
import tfazio.mad_assignment.Database.GameDataSchema.ItemTable;
import tfazio.mad_assignment.Database.GameDataSchema.PlayerTable;

import tfazio.mad_assignment.DataClasses.Player;

public class GameDataDB
{
    private SQLiteDatabase db;
    public GameDataDB(Context context)
    {
        this.db = new GameDataSQLHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void addPlayer(Player player)
    {
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.ROWLOC, player.getPosition()[1]);
        cv.put(PlayerTable.Cols.COLLOC, player.getPosition()[0]);
        cv.put(PlayerTable.Cols.CASH, player.getCash());
        cv.put(PlayerTable.Cols.HEALTH, player.getHealth());
        cv.put(PlayerTable.Cols.MASS, player.getEquipmentMass());
    }

    public void updatePlayer(Player player)
    {
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.ROWLOC, player.getPosition()[1]);
        cv.put(PlayerTable.Cols.COLLOC, player.getPosition()[0]);
        cv.put(PlayerTable.Cols.CASH, player.getCash());
        cv.put(PlayerTable.Cols.HEALTH, player.getHealth());
        cv.put(PlayerTable.Cols.MASS, player.getEquipmentMass());

        String[] whereValue = {String.valueOf(player.getId())};
        db.update(PlayerTable.NAME,cv,PlayerTable.Cols.ID+"=?",whereValue);
    }

    public void removePlayer(Player player)
    {
        String[] whereValue = {String.valueOf((player.getId()))};
        db.delete(PlayerTable.NAME,PlayerTable.Cols.ID+"= ?", whereValue);
    }

    public void addItem(Item item, int[]coords)
    {
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.Cols.ID, item.getId());
        cv.put(ItemTable.Cols.NAME, item.getName());
        cv.put(ItemTable.Cols.DESCRIPTION, item.getDescription());
        cv.put(ItemTable.Cols.VALUE, item.getValue());
        cv.put(ItemTable.Cols.USEABLE, item.isUseable());
        if(item instanceof Food)
        {
            cv.put(ItemTable.Cols.NUMBER, ((Food) item).getHealth());
        }
        else
        {
            cv.put(ItemTable.Cols.NUMBER, ((Equipment) item).getMass());
        }
        cv.put(ItemTable.Cols.QUEST, item.getDescription());
        if(item.isOwned())
        {
            cv.put(ItemTable.Cols.OWNER,"p");
        }
        else
        {
            cv.put(ItemTable.Cols.OWNER,coords[0]+","+coords[1]);
        }
    }

    public void updateItem(Item item, int[]coords)
    {
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.Cols.ID, item.getId());
        cv.put(ItemTable.Cols.NAME, item.getName());
        cv.put(ItemTable.Cols.DESCRIPTION, item.getDescription());
        cv.put(ItemTable.Cols.VALUE, item.getValue());
        cv.put(ItemTable.Cols.USEABLE, item.isUseable());
        if(item instanceof Food)
        {
            cv.put(ItemTable.Cols.NUMBER, ((Food) item).getHealth());
        }
        else
        {
            cv.put(ItemTable.Cols.NUMBER, ((Equipment) item).getMass());
        }
        cv.put(ItemTable.Cols.QUEST, item.getDescription());
        if(item.isOwned())
        {
            cv.put(ItemTable.Cols.OWNER,"p");
        }
        else
        {
            cv.put(ItemTable.Cols.OWNER,coords[0]+","+coords[1]);
        }


    }

    public void removeItem(Item item)
    {
        String[] whereValue = {String.valueOf((item.getId()))};
        db.delete(ItemTable.NAME,ItemTable.Cols.ID+"= ?", whereValue);

    }

    public void addArea(Area area)
    {
        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.ID, area.getId());
        cv.put(AreaTable.Cols.ISTOWN, area.isTown());
        cv.put(AreaTable.Cols.DESCRIPTION, area.getDescription());
        cv.put(AreaTable.Cols.STARRED, area.getStarred());
        cv.put(AreaTable.Cols.EXPLORED, area.isExplored());
        cv.put(AreaTable.Cols.X, area.getX());
        cv.put(AreaTable.Cols.Y, area.getY());
    }

    public void updateArea(Area area)
    {

        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.ID, area.getId());
        cv.put(AreaTable.Cols.ISTOWN, area.isTown());
        cv.put(AreaTable.Cols.DESCRIPTION, area.getDescription());
        cv.put(AreaTable.Cols.STARRED, area.getStarred());
        cv.put(AreaTable.Cols.EXPLORED, area.isExplored());
        cv.put(AreaTable.Cols.X, area.getX());
        cv.put(AreaTable.Cols.Y, area.getY());

        String[] whereValue = {String.valueOf(area.getId())};
        db.update(AreaTable.NAME,cv,AreaTable.Cols.ID+"=?",whereValue);


    }

    public void removeArea(Area area)
    {
        String[] whereValue = {String.valueOf((area.getId()))};
        db.delete(AreaTable.NAME,AreaTable.Cols.ID+"= ?", whereValue);

    }
}
