package tfazio.mad_assignment;

import android.util.Log;

import java.util.Random;

public class GameData
{

    //Class variables
    private Area[][] grid;
    private Player player;
    private static GameData instance;
    private int xMax;
    private int yMax;
    private Random random = new Random();

    //default constructor, creates a testgrid
    private GameData()
    {

    }

    private GameData(int x, int y)
    {
        //initialize a x*y grid
        createGrid(x,y);
        player = new Player(x,y);
        player.addEquipment(new Equipment("Test Player Equip","this is a test desc",1,1.0,true,true));
        instance = this;
    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int[] xy)
    {
        //get area from supplied coordinates of gameData
        return grid[xy[0]][xy[1]];
    }

    public int getXMax()
    {
        return xMax;
    }
    public int getYMax()
    {
        return yMax;
    }

    public static GameData getInstance()
    {
        if(instance == null)
        {
            instance = new GameData(5,5);
        }
        return instance;
    }

    public Player getPlayer(){return player;}

    //SETTERS

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        Log.d("DEBUG","\nCreating map grid of size ("+ x +","+y+")");

        grid = new Area[x][y];
        xMax = x-1;
        yMax = y-1;
        boolean oneTown;
        boolean isTown;

        //assign Areas to grid
        //for every row
        oneTown = false;
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                Log.d("DEBUG","\n   Targeting: "+ col+","+row);
                isTown = randomArea();//generate if area is town or wild
                if(isTown)
                {
                    oneTown=true;//if there is atleast one town generated
                }

                if((row==xMax&&col==yMax)&&(!oneTown))//if it's the final row and col position, and no towns generated
                {
                    isTown = true; //next area is town
                }
                grid[row][col] = new Area(isTown,row,col);
                grid[row][col].addItem(new Food("Test Food","some testing description",1,1));
                grid[row][col].addItem(new Equipment("Test Area Equip 1","this is a test desc",1,1.0,true,true));
                grid[row][col].addItem(new Equipment("Test Area Equip 2","this is a test desc",1,1.0,true,false));
            }
        }
    }

    //take a set of coords and check if they are a valid grid position
    public boolean validateCoords(int x,int y)
    {
        //check if coords exist in game map
        //if yes return true, if no return false
        Log.d("DEBUG","validating coords: " + x + "," + y);

        boolean check = false;
        if((x<=xMax && x>=0) && (yMax>=y && y>=0))
        {
            check = true;
        }

        return check;
    }

    //randomize the next area being a town or wilderness, keeps the gap from getting too large
    private boolean randomArea()
    {
        boolean nextArea =false;

        int x = random.nextInt(10);
        if(x<4)
        {
            nextArea = true;
        }

        return nextArea;
    }

    public void restart()
    {

    }


}
