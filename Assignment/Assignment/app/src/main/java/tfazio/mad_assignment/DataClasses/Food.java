package tfazio.mad_assignment.DataClasses;

import android.util.Log;

public class Food extends Item {

    //Class Variables
    double health;

    public Food()
    {
        super();
        health = 1;
    }

    public Food(String inTitle, String inDescription, int inValue,double inHealth)
    {
        super(inTitle,inDescription,inValue);
        health = inHealth;
        Log.d("DEBUG",", "+health);

    }

    public double getHealth()
    {
        return health;
    }
}
