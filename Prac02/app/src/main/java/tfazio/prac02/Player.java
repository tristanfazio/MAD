package tfazio.prac02;
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
        updatePosition(0,0);
        cash = 100;
        health = 100;
        equipmentMass = 0;
    }

    //getters
    public int[] getPosition()
    {

        return (new int[]{colLocation,rowLocation});
    }

    //setters
    public void updatePosition(int x, int y)
    {
        colLocation = x;
        rowLocation = y;
    }

    public void reduceHealth()
    {
        //update health based on formula, always return 0 if drops belows
        health = Math.max(0.0, health - 5.0 - (equipmentMass / 2.0));
    }

    public void increaseHealth()
    {

    }

}
