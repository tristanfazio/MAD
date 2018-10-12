package tfazio.mad_assignment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Area {

    //Class variables
    boolean town;
    List<Item> items;
    String description;
    Boolean starred;
    Boolean explored;



    //default constructor
    public Area()
    {
        town = true;
        items = new ArrayList<Item>();
        description = "An unexplored area";
        starred = false;
        explored =false;

        Log.d("DEBUG","Creating Area. Town: " + town);

    }

    //alternate constructor with town/wilderness indication
    public Area(Boolean isTown)
    {
        town = isTown;
        items = new ArrayList<Item>();
        description = "An unexplored area";
        starred = false;
        explored = false;
        Log.d("DEBUG","Creating Area. Town: " + town);
    }

    public List<Item> getItems(){return items;}
    public String getDescription(){return description;}
    public Boolean getExplored(){ return explored; }
    public Boolean getStarred(){ return starred; }

    public void addItem(Item inItem)
    {

        items.add(inItem);
    }

    public boolean isTown()
    {
        Log.d("DEBUG","Is Town? " + town);
        return town;
    }
}
