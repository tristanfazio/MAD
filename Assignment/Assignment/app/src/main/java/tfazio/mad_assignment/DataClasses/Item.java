package tfazio.mad_assignment.DataClasses;

import android.util.Log;

public abstract class Item {

    //Class variables
    int id;
    String name;
    String description;
    int value;
    boolean owned;
    Boolean useable;
    boolean equipment;
    private static int nextID=0;

    public Item()
    {

    }

    public Item(String inName, String inDescription, int inValue,boolean inUseable)
    {
        id=nextID++;
        name = inName;
        description = inDescription;
        value = inValue;
        owned = false;
        useable = inUseable;
        equipment = true;
        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", " + "Owned: " + owned);
    }
    public Item(String inName, String inDescription, int inValue)
    {
        id=nextID++;
        name = inName;
        description = inDescription;
        value = inValue;
        owned = false;
        useable = false;
        equipment = false;
        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", " + "Owned: " + owned);
    }

    //getters
    public int getId(){return id;}
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
