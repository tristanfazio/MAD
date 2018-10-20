package tfazio.mad_assignment;

import android.util.Log;

public class Equipment extends Item {


    //Class variables
    double mass;
    boolean quest;

    public Equipment()
    {
        super();
        mass = 1.0;
    }

    public Equipment(String inTitle, String inDescription, int inValue, double inMass, boolean inQuest)
    {
        super(inTitle,inDescription,inValue);
        mass = inMass;
        quest = inQuest;
        Log.d("DEBUG",", "+mass+ ", quest: " + quest );
    }
}