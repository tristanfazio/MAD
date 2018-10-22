package tfazio.mad_assignment;
import android.util.Log;

import java.util.ArrayList;
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
        equipment = new ArrayList<>();
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
    public double getEquipmentMass(){return equipmentMass;}
    public int getCash() { return cash; }
    public List<Equipment> getEquipment(){return equipment;};

    //setters
    public void updatePosition(int x, int y)
    {
        Log.d("DEBUG","Updating Player position to: " + x +"," +y);
        colLocation = x;
        rowLocation = y;
        movementHealth();
    }

    public void movementHealth()
    {
        //update health based on formula, always return 0 if drops belows
        double newHealth = Math.max(0.0, health - 5.0 - (equipmentMass / 2.0));
        Log.d("DEBUG","Reducing player health from " + health + " to " + newHealth);
        health = newHealth;
        checkLose();
    }

    public void addEquipment(Equipment inEquip)
    {
        inEquip.toggleOwned();
        equipment.add(inEquip);
    }

    public void removeEquipment(Equipment inEquip)
    {
        inEquip.toggleOwned();
        equipment.remove(inEquip);
    }

    public void updateHealth(double inHealth)
    {
        health = health + inHealth;
        checkLose();
    }

    public void updateMass(double inMass)
    {
        equipmentMass = equipmentMass + inMass;
    }

    public void updateCash(int inCash)
    {
        cash = cash + inCash;
    }

    public void checkLose()
    {
        if(health<=0)
        {
            //activate lose method
            Log.d("DEBUG","\n\n*****GAME OVER*****");

        }
    }

}
