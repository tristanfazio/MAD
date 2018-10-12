package tfazio.mad_assignment;

import android.util.Log;

import java.util.Random;

public class GameData
{

    //Class variables
    Area[][] grid;
    Player player;
    static GameData instance;
    private int numTowns;
    private int numWild;
    private int xMax;
    private int yMax;
    Random random = new Random();

    //default constructor, creates a testgrid
    public GameData()
    {
        //initialize a default 2x2 grid
        createGrid(2,2);
        player = new Player();
        instance = this;
    }

    public GameData(int x, int y)
    {
        //initialize a default 2x2 grid
        createGrid(x,y);
        player = new Player();
        instance = this;
    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int x,int y)
    {
        //get area from supplied coordinates of gameData
        return grid[x][y];
    }
    public int getxMax()
    {
        return xMax;
    }
    public int getyMax()
    {
        return yMax;
    }
    public static GameData getInstance(){return instance;}
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
        boolean isTown;

        //assign Areas to grid
        //for every row
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                Log.d("DEBUG","\n   Targeting: "+ col+","+row);
                isTown = randomArea();
                grid[col][row] = new Area(isTown);
                //update trackers
                if(isTown)
                {
                    numTowns++;
                }
                else
                {
                    numWild++;
                }
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
        boolean nextArea = true;

        //check if there is too many of one area
        Log.d("DEBUG","\n   Area Diff = " + (Math.abs(numTowns-numWild)));

        if(Math.abs(numTowns-numWild)>2)
        {

            //one area now has a atleast 3 more than the other
            //set next area to the lowest area
            if(numWild>numTowns)
            {
                //wilderness is greater than towns
                //next area is TRUE for town
                nextArea = true;
                Log.d("DEBUG","\n   Too many wilderness, defaulting to town");
            }
            else if(numTowns>numWild)
            {
                //towns is greater than wilderness
                //next area is FALSE for town
                nextArea = false;
                Log.d("DEBUG","\n   Too many town, defaulting to wilderness");

            }
        }
        else
        {
            //the gap isn't too large, pick a random for next area
            nextArea = random.nextBoolean();
            Log.d("DEBUG","\n   Reasonable difference, randomizing");

        }

        return nextArea;
    }
}
