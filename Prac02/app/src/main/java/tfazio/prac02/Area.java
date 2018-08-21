package tfazio.prac02;
import android.util.Log;

import java.util.List;

public class Area {

    //Class variables
    boolean town;
    List<Item> items;

    //default constructor
    public Area()
    {
        town = true;
        Log.d("DEBUG","Creating Area. Town: " + town);

    }

    //alternate constructor with town/wilderness indication
    public Area(Boolean isTown)
    {
        town = isTown;
        Log.d("DEBUG","Creating Area. Town: " + town);
    }

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
