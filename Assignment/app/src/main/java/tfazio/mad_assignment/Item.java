package tfazio.mad_assignment;

import android.util.Log;

public abstract class Item {

    //Class variables
    String name;
    String description;
    int value;
    boolean quest;

    public Item()
    {
        name = "Item";
        description = "item template";
        value = 0;
    }

    public Item(String inName, String inDescription, int inValue, boolean inQuest)
    {
        name = inName;
        description = inDescription;
        value = inValue;
        quest = inQuest;

        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value + ", " + quest);
    }

}
