package tfazio.mad_assignment;
import android.util.Log;

import java.util.List;

public class Player
{

    //Class variables
    int rowLocation; //which row, therefore y
    int colLocation; //which col, therefore x
    int cash;
    double health;
    double equipmentMass;
    List<Equipment> equipment;

    //default constructor
    public Player()
    {
        Log.d("DEBUG","Creating Player");

        colLocation = 0;
        rowLocation = 0;
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
    }

    //getters
    public int[] getPosition()
    {
        Log.d("DEBUG","Retrieving player position: " + colLocation + ',' + rowLocation);
        return (new int[]{colLocation,rowLocation});
    }

    public double getHealth()
    {
        return health;
    }

    //setters
    public void updatePosition(int x, int y)
    {
        Log.d("DEBUG","Updating Player position to: " + x +"," +y);
        colLocation = x;
        rowLocation = y;
        reduceHealth();
    }

    public void reduceHealth()
    {
        //update health based on formula, always return 0 if drops belows
        double newHealth = Math.max(0.0, health - 5.0 - (equipmentMass / 2.0));
        Log.d("DEBUG","Reducing player health from " + health + " to " + newHealth);
        health = newHealth;
    }

    public void increaseHealth()
    {

    }

}
