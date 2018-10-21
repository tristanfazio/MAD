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
        quest = false;
    }

    public Equipment(String inTitle, String inDescription, int inValue, double inMass, boolean inQuest,boolean inOwned)
    {
        super(inTitle,inDescription,inValue,inOwned);
        mass = inMass;
        quest = inQuest;
        Log.d("DEBUG",", "+mass+ ", quest: " + quest );
    }

    public double getMass() {
        return mass;
    }

    public boolean isQuest() {
        return quest;
    }
}