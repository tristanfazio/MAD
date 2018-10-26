package tfazio.mad_assignment.DataClasses;

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

    public Equipment(String inTitle, String inDescription, int inValue, double inMass, boolean inQuest,boolean inUseable)
    {
        super(inTitle,inDescription,inValue,inUseable);
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