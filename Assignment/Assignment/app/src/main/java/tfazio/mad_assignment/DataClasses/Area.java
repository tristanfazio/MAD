package tfazio.mad_assignment.DataClasses;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Area {

    //Class variables
    private String id;
    boolean town;
    List<Item> items;
    String description;
    boolean starred;
    boolean explored;
    int x,y;

    //default constructor
    public Area()
    {
        town = true;
        items = new ArrayList<Item>();
        description = "";
        starred = false;
        explored =false;

        Log.d("DEBUG","Creating Area. Town: " + town);

    }

    //alternate constructor with town/wilderness indication
    public Area(Boolean isTown,String inDescrip,int inX, int inY)
    {
        id = "" + inX +","+inY;
        town = isTown;
        items = new ArrayList<Item>();
        description = inDescrip;
        starred = false;
        explored = false;
        x=inX;
        y=inY;
        Log.d("DEBUG","\n   Creating Area. Town: " + town);
    }

    //getters
    public String getId(){return id;}
    public List<Item> getItems(){return items;}
    public String getDescription(){return description;}
    public boolean getStarred(){ return starred; }
    public int getX(){return x;}
    public int getY(){return y;}
    public int[] getXY(){return new int[]{x,y};}
    public boolean isExplored()
    {
        return explored;
    }
    public boolean isTown()
    {
        Log.d("DEBUG","Is Town? " + town);
        return town;
    }

    //setters
    public void addItem(Item inItem)
    {

        items.add(inItem);
    }

    public void removeItem(Item inItem)
    {
        items.remove(inItem);
    }

    public void toggleExplored()
    {
        //if not explored, toggle to explored, if explored, do nothing
        if(!explored)
        {
            explored = true;
        }
    }

    public void toggleStarred()
    {
        //if not starred, toggle to starred, if starred, toggle to not starred
        if(!starred)
        {
            starred = true;
        }
        else if(starred)
        {
            starred = false;
        }
    }

    public void setDescription(String inText)
    {
        description = inText;
    }
}
