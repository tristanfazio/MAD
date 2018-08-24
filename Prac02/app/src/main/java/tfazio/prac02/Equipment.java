package tfazio.prac02;

import android.util.Log;

public class Equipment extends Item {


    //Class variables
    double mass;

    public Equipment()
    {
        super();
        mass = 1.0;
    }

    public Equipment(String inTitle, String inDescription, int inValue, double inMass)
    {
        super(inTitle,inDescription,inValue);
        mass = inMass;
        Log.d("DEBUG",", "+mass);

    }
}