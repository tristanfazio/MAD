package tfazio.mad_assignment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player
{

    //Class variables
    int rowLocation; //which row, therefore y
    int colLocation; //which col, therefore x
    private int cash;
    private double health;
    private double equipmentMass;
    private List<Equipment> equipment;
    GameData gameData;
    private boolean jade;
    private boolean scrap;


    private boolean roadmap;

    //default constructor
    public Player(int xMax, int yMax)
    {
        Log.d("DEBUG","Creating Player");

        //place starting position at random location
        colLocation = new Random().nextInt(xMax);
        rowLocation = new Random().nextInt(yMax);
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
        equipment = new ArrayList<>();
        jade = false;
        scrap = false;
        roadmap = false;
    }

    //getters
    public int[] getPosition()
    {
        Log.d("DEBUG","Retrieving player position: " + colLocation + ',' + rowLocation);
        return (new int[]{colLocation,rowLocation});
    }

    public boolean isJade() {
        return jade;
    }

    public boolean isScrap() {
        return scrap;
    }

    public boolean isRoadmap() {
        return roadmap;
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
        updateMass(inEquip.getMass());
        equipment.add(inEquip);
        if(inEquip.isQuest())
        {
            if(inEquip.getName().equals("Jade Monkey"))
            {
                jade=true;
            }
            else
            if(inEquip.getName().equals("Ice Scrapper"))
            {
                scrap=true;
            }
            else
            if(inEquip.getName().equals("Road Map"))
            {
                roadmap=true;
            }
        }
    }

    public void removeEquipment(Equipment inEquip)
    {
        inEquip.toggleOwned();
        updateMass(-inEquip.getMass());
        equipment.remove(inEquip);
        if(inEquip.isQuest())
        {
            if(inEquip.getName().equals("Jade Monkey"))
            {
                jade=false;
            }
            else
            if(inEquip.getName().equals("Ice Scrapper"))
            {
                scrap=false;
            }
            else
            if(inEquip.getName().equals("Road Map"))
            {
                roadmap=false;
            }
        }
    }

    public void updateHealth(double inHealth)
    {
        health = health + inHealth;
    }

    public void updateMass(double inMass)
    {
        equipmentMass = equipmentMass + inMass;
    }

    public void updateCash(int inCash)
    {
        cash = cash + inCash;
    }

    public boolean checkLose()
    {
        boolean check = false;
        if(health<=0)
        {
            //activate lose method
            System.out.println("\n\nLOSE!!!\n\n");
            check=true;
        }
        return check;
    }

    public boolean checkWin()
    {

        boolean check = false;
        if(roadmap && scrap && jade)
        {
            check = true;
            System.out.println("\n\nWIN!!!\n\n");
        }
        return check;
    }
}
