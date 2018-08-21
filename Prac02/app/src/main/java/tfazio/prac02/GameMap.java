package tfazio.prac02;

import android.util.Log;

public class GameMap
{

    //Class variables
    Area[][] grid;
    int xMax;
    int yMax;

    //default constructor, creates a testgrid
    public GameMap()
    {
        //initialize a default 2x2 grid
        createTestGrid();
    }

    //alternate constructor with grid variables
    public GameMap(int x, int y)
    {
        //initialize a grid of x by y
        createGrid(x,y);
    }

    //GETTERS

    //get an Area object from the specified gamemap coordinates
    public Area getArea(int x,int y)
    {
        //get area from supplied coordinates of gameMap
        return grid[x][y];
    }
    //SETTERS

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createGrid(int x, int y)
    {
        //initialize grid
        grid = new Area[x][y];
        xMax = x-1;
        yMax = y-1;
        boolean toggle = true;

        //assign Areas to grid
        //for every row
        for(int row = 0;row < y; row++)
        {
            //for every column
            for(int col = 0;col<x;col++)
            {
                //assign a newly constructed area
                Log.d("DEBUG","Targeting: "+ col+","+row);

                grid[x][y] = new Area(toggle);
                if(toggle)
                {
                    toggle = false;
                }
                else
                {
                    toggle = true;
                }
            }
        }
    }

    //take in grid dimensions, initialize grid 2D array, assign a new Area to each grid position
    private void createTestGrid()
    {
        Log.d("DEBUG","Creating Test Grid of size (2,2)");
        //initialize grid
        grid = new Area[2][2];

        //set dimensions
        xMax = 1;
        yMax = 1;

        /*
        _____________

        | 0,1 | 1,1 |
        | 0,0 | 1,0 |
        _____________
        */

        //assign Area's to grid
        Log.d("DEBUG","Targeting: 0,0");
        grid[0][0] = new Area(true); //bottom left
        Log.d("DEBUG","Targeting: 0,1");
        grid[0][1] = new Area(false);  //top left
        Log.d("DEBUG","Targeting: 1,0");
        grid[1][0] = new Area(false); //bottom right
        Log.d("DEBUG","Targeting: 1,1");
        grid[1][1] = new Area(true); //top right
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
}
