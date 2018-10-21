package tfazio.mad_assignment;

import android.util.Log;

public abstract class Item {

    //Class variables
    String name;
    String description;
    int value;
    boolean owned;


    public Item()
    {
        name = "Item";
        description = "item template";
        value = 0;
        owned = false;
    }

    public Item(String inName, String inDescription, int inValue, boolean inOwned)
    {
        name = inName;
        description = inDescription;
        value = inValue;
        owned = inOwned;
        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", ");
    }

    public String getDescription() {return description; }
    public String getName() { return name; }
    public int getValue(){return value;}
    public boolean isOwned() {
        return owned;
    }
}
