package tfazio.mad_assignment;

import android.util.Log;

public abstract class Item {

    //Class variables
    String name;
    String description;
    int value;
    boolean owned;
    Boolean useable;


    public Item()
    {
        name = "Item";
        description = "item template";
        value = 0;
        owned = false;
        useable = false;
    }

    public Item(String inName, String inDescription, int inValue,boolean inUseable)
    {
        name = inName;
        description = inDescription;
        value = inValue;
        owned = false;
        useable = inUseable;
        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", " + "Owned: " + owned);
    }
    public Item(String inName, String inDescription, int inValue)
    {
        name = inName;
        description = inDescription;
        value = inValue;
        owned = false;
        useable = false;
        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", " + "Owned: " + owned);
    }

    public String getDescription() {return description; }
    public String getName() { return name; }
    public int getValue(){return value;}
    public boolean isOwned() {
        return owned;
    }
    public boolean isUseable(){return useable;}
    public void toggleOwned()
    {
        if(isOwned())
        {
            owned=false;
        }
        else if(!isOwned())
        {
            owned=true;
        }
    }

}
