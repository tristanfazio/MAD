package tfazio.prac02;

import android.util.Log;

public abstract class Item {

    //Class variables
    String name;
    String description;
    int value;

    public Item()
    {
        name = "Item";
        description = "item template";
        value = 0;
    }

    public Item(String inName, String inDescription, int inValue)
    {
        name = inName;
        description = inDescription;
        value = inValue;

        Log.d("DEBUG","Creating item: "+name + ", " + description + ", " + value);
    }

}
